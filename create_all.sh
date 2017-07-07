#!/bin/bash

ant -f clean.xml
ant -f build.xml
ant -f linux.xml
ant -f create_lin.xml
ant -f win32.xml
ant -f create_exe32.xml
ant -f win64.xml
ant -f create_exe64.xml

