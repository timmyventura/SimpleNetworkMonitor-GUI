#!/bin/bash

DIRECTORY='.';
PATHS=$0

define_path(){

echo "Define application path"

if [ "${PATHS:0:1}" = '/' ]; then
  DIRECTORY="${PATHS%/*}"
else
 TEMP="$PWD/${PATHS}"
 DIRECTORY="${TEMP%/*}"
fi

}

define_path
`echo $DIRECTORY/install.sh`
