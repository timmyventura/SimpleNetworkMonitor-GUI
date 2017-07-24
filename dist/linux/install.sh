#!/bin/bash

#Installation script

JAVA_ORACLE='Java(TM)'
JAVA_OPENJDK='OpenJDK'

DIRECTORY='.';
PATHS=$0
LOG_FILE='properties/log4j.properties'

NAME='simple_network_monitor.desktop'
NAME_SCRIPT='SimpleNetworkMonitor.sh'

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
echo 'Keywords=Simple;Monitor' >> "$DIRECTORY/$NAME"
echo "Exec=gksu $DIRECTORY/SimpleNetworkMonitor.sh" >> "$DIRECTORY/$NAME"
echo 'Terminal=false' >> "$DIRECTORY/$NAME"
echo 'Icon=/usr/share/pixmaps/javaws.png' >> "$DIRECTORY/$NAME"
echo 'Type=Application' >> "$DIRECTORY/$NAME"
echo 'NoDisplay=false' >> "$DIRECTORY/$NAME"

echo "Use runnable $NAME_SCRIPT script"
sudo chmod +x "$DIRECTORY/$NAME_SCRIPT"

echo "Copy icon to share directory"
sudo cp "$DIRECTORY/$NAME" /usr/share/applications

}
 
   sudo apt-get update
   echo "Install net-tools packet"
   sudo apt-get -y install net-tools
   echo "Install libpcap-dev library"
   sudo apt-get -y install libpcap-dev
   sudo apt-get update
   sudo apt-get -y install software-properties-common
   sudo add-apt-repository "deb http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main"
   sudo apt-get update
   echo "Install oracle java 8 instance"
   if [ `java -version 2>&1 | grep ${JAVA_ORACLE} | wc -l` != 0 ]; then
   echo "Install oracle java 8 instance"
   sudo apt-get -y install oracle-java8-installer
   elif [ `java -version 2>&1 | grep ${JAVA_OPENJDK} | wc -l` != 0 ]; then
   echo "Install openjdk java 8 instance"
   sudo add-apt-repository "deb http://http.debian.net/debian jessie-backports main"
   sudo apt-get update
   sudo apt-get install -t jessie-backports openjdk-8-jdk
   if [ `sudo update-java-alternatives --list | grep 1.8 | wc -l` != 0 ]; then
   sudo update-java-alternatives --set java-1.8.0-openjdk-*
   fi
  fi 
  
   define_path
   create_icon
   define_log_path

   echo "Success!"
