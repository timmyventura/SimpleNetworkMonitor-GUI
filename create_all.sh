#!/bin/bash

ant -f clean.xml
ant -f build.xml
ant -f linux.xml
ant -f win32.xml
ant -f win64.xml

