#!/bin/bash
set -e

rm -rf out
mkdir -p out

javac -d out $(find src -name "*.java")
jar cfe jcommit.jar org.jcommit.Main -C out . -C src/org/jcommit/gui/resource .