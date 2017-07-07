#!/bin/bash

ant -f build.xml
ant -f win64.xml
ant -f create_exe64.xml
