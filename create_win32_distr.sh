#!/bin/bash

ant -f build.xml
ant -f win32.xml
ant -f create_exe32.xml
