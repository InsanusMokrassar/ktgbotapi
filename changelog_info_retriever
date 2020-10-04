#!/bin/bash

function parse() {
    version=$1

    while IFS= read -r line && [ -z "`echo $line | grep -e "^#\+ $version"`" ]
    do
        : # do nothing
    done

    while IFS= read -r line && [ -z "`echo $line | grep -e "^#\+"`" ]
    do
        echo "$line"
    done
}

version=$1
file=$2

if [ -n "$file" ]; then
  parse $version < "$file"
else
  parse $version
fi
