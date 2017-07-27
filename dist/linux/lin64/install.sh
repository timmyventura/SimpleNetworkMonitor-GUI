#!/bin/bash

#Installation script

######Define path to direcory SimpleNetworkMonitor#######
DIRECTORY='.';
PATHS=$0
NEW_JAVA_PATH="/usr/lib/jvm/jdk-8-141"
#######Define log path ################################
LOG_FILE='properties/log4j.properties'

############Define name of run shell script and icon############
NAME='simple_network_monitor.desktop'
NAME_SCRIPT='SimpleNetworkMonitor.sh'
JAR_FILENAME='SimpleNetworkMonitor.jar'

##########Define download link, bit system###########################
BASE_URL_8="http://download.oracle.com/otn-pub/java/jdk/8u141-b15/336fa29ff2bb4ef291e347e091f7f4a7/jdk-8u141"
JDK_VERSION=`echo $BASE_URL_8 | rev | cut -d "/" -f1 | rev`
BIT_SYSTEM=`uname -m`
LIN_32="-linux-i586.tar.gz"
LIN_64="-linux-x64.tar.gz"
declare -A linbit=( ['x86_64']=${LIN_64} ['i686']=${LIN_32} ['i586']=${LIN_32})


define_path(){

echo "Define application path"

if [ "${PATHS:0:1}" = '/' ]; then
  DIRECTORY="${PATHS%/*}"
else
 TEMP="$PWD/${PATHS}"
 DIRECTORY="${TEMP%/*}"
fi

}

define_log_path(){

echo "Define log path"
echo '# Set the name of the file' >> "$DIRECTORY/$LOG_FILE"
echo "log4j.appender.FILE.File=$DIRECTORY/logs/log.txt" >> "$DIRECTORY/$LOG_FILE"

}

create_icon(){
 
echo "Create icon"

echo '[Desktop Entry]' >> "$DIRECTORY/$NAME"
echo 'Name=SimpleNetworkMonitor' >> "$DIRECTORY/$NAME"
echo 'Comment=A simple network monitor' >> "$DIRECTORY/$NAME"
echo 'GenericName=SNM' >> "$DIRECTORY/$NAME"
echo 'Keywords=Simple; Monitor' >> "$DIRECTORY/$NAME"
echo "Exec=sudo $DIRECTORY/SimpleNetworkMonitor.sh" >> "$DIRECTORY/$NAME"
echo 'Terminal=false' >> "$DIRECTORY/$NAME"
echo 'Icon=/usr/share/pixmaps/javaws.png' >> "$DIRECTORY/$NAME"
echo 'Type=Application' >> "$DIRECTORY/$NAME"
echo 'NoDisplay=false' >> "$DIRECTORY/$NAME"

echo "Do runnable $NAME_SCRIPT script"
sudo chmod +x "$DIRECTORY/$NAME_SCRIPT"

echo "Do runnable $NAME icon"
sudo chmod +x "$DIRECTORY/$NAME"

echo "Do runnable $JAR_FILENAME file"
sudo chmod +x "$DIRECTORY/$JAR_FILENAME"

echo "Copy icon to share directory"
sudo cp "$DIRECTORY/$NAME" /usr/share/applications

}

install_java(){

echo "Install Java 8 Oracle Instance"

platform=${linbit[$BIT_SYSTEM]}

CURRENT_DIR=`pwd`

sudo mkdir $NEW_JAVA_PATH
cd $NEW_JAVA_PATH

sudo wget -c -O "$JDK_VERSION$platform" --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" "${BASE_URL_8}${platform}"

sudo tar -zxvf $JDK_VERSION$platform

cd jdk1.8.0_141/

sudo update-alternatives --install /usr/bin/java java $NEW_JAVA_PATH/jdk1.8.0_141/bin/java 100
sudo update-alternatives --set java $NEW_JAVA_PATH/jdk1.8.0_141/bin/java

export JAVA_HOME=$NEW_JAVA_PATH/jdk1.8.0_141/	
export JRE_HOME=$NEW_JAVA_PATH/jdk1.8.0_141/jre 	
export PATH=$PATH:$NEW_JAVA_PATH/jdk1.8.0_141/bin:$NEW_JAVA_PATH/jdk1.8.0_141/jre/bin


sudo rm ../$JDK_VERSION$platform

cd $CURRENT_DIR

}

checking_java_version(){

if [ `java -version 2>&1 | grep 'version "1.9.*"' | wc -l` -ne 0 ]; then
echo "You have newest java version"
elif [ `java -version 2>&1 | grep 1.8.0 | wc -l` -ne 0 ]; then
  echo "You already have java version 8 instance"
else
 install_java
fi

}
 
   sudo apt-get update
   echo "Install net-tools packet"
   sudo apt-get -y install net-tools
   echo "Install libpcap-dev library"
   sudo apt-get -y install libpcap-dev
   checking_java_version 
   define_path
   create_icon
   define_log_path
   echo "Success!"
