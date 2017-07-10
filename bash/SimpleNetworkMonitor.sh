#!/bin/bash



JAVA_ORACLE='Java(TM)'
JAVA_OPENJDK='OpenJDK'
DIRECTORY='.';
PATHS=$0

CHECK_JAVA_VERSION_ARCH=`java -version 2>&1 | awk '/Bit/{print $3}'`	
CHECK_JAVA_VERSION=`java -version 2>&1 | grep 'version "1.8.*"' | wc -l`



define_path(){

if [ "${PATHS:0:1}" = '/' ]; then
  DIRECTORY="${PATHS%/*}"
else
 TEMP="$PWD/${PATHS}"
 DIRECTORY="${TEMP%/*}"
fi

}

install_java(){

    echo "You have not installed java version 8 instance"
    echo "Download/Update java, please type sudo password and wait, while java version 8 installed/update"
    sudo apt-get update
    sudo apt-get -y install software-properties-common
    sudo add-apt-repository "deb http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main"
    sudo apt-get update
    sudo apt-get -y install oracle-java8-installer
    sudo apt-get -y install oracle-java8-set-default

}


define_path

if [ ${CHECK_JAVA_VERSION} != 0 ]; then
  
 echo "Java version 8 instance already installed"

 if [ `java -version 2>&1 | grep ${JAVA_ORACLE} | wc -l` != 0 ]; then
   
 echo "Java version 8 developed by Oracle"
   
    if [ ${CHECK_JAVA_VERSION_ARCH} == '64-Bit' ]; then

      echo "Java version 8 64 bit architecture"
      echo "Run application..."
      sudo java -Djava.library.path=$DIRECTORY/library/lin64 -jar $DIRECTORY/SimpleNetworkMonitor.jar

    else

      echo "Java version 8 32 bit architecture"
      echo "Run application..."
      sudo java -Djava.library.path=$DIRECTORY/library/lin32 -jar $DIRECTORY/SimpleNetworkMonitor.jar

    fi

 elif [ `java -version 2>&1 | grep ${JAVA_OPENJDK} | wc -l` != 0 ]; then
     
   echo "Java version 8 developed by OpenJDK"
     
    if [ ${CHECK_JAVA_VERSION_ARCH} == '64-Bit' ]; then

      echo "Java version 8 64 bit architecture"
      echo "Run application..."

      sudo java -Djava.library.path=$DIRECTORY/library/lin64 -jar $DIRECTORY/SimpleNetworkMonitor.jar

    else

      echo "Java version 8 32 bit architecture"
      echo "Run application..."

      sudo java -Djava.library.path=$DIRECTORY/library/lin32 -jar $DIRECTORY/SimpleNetworkMonitor.jar

    fi

  fi

else
    install_java
fi




