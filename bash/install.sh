#!/bin/bash

#Installation script

JAVA_ORACLE='Java(TM)'
JAVA_OPENJDK='OpenJDK'

   sudo apt-get update
   echo "Install net-tools packet"
   sudo apt-get -y install net-tools
   echo "Install libpcap-dev library"
   sudo apt-get -y install libpcap-dev
   sudo apt-get update
   sudo apt-get -y install software-properties-common
   sudo add-apt-repository "deb http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main"
   sudo apt-get update
   #echo "Install oracle java 8 instance"
   if [ `java -version 2>&1 | grep ${JAVA_ORACLE} | wc -l` != 0 ]; then
   echo "Install oracle java 8 instance"
   sudo apt-get -y install oracle-java8-installer
   elif [ `java -version 2>&1 | grep ${JAVA_OPENJDK} | wc -l` != 0 ]; then
   echo "Install openjdk java 8 instance"
   sudo apt-get -y install openjdk-8-jre 
   fi
   echo "Success!"
