#!/usr/bin/env bash

# Either runs a normal './gradlew build' or, if this CI run is because
# of a tagged commit on the RPTools repository, runs
# './gradlew bintrayUpload' instead.

# For JDK9, should add:
#   _JAVA_OPTIONS="${JAVA_OPTIONS} --illegal-access=permit"
# .. in order to get rid of the warning messages sent to the console.
#
# Not needed at the current time, though, since the RPTools
# libraries can be built using JDK8.

set -vex

if [ "X$TRAVIS_TAG" = "X" ] || [ "X$TRAVIS_REPO_SLUG" != "RPTools/rplib" ]; then
    ./gradlew --no-daemon build
else
    ./gradlew --no-daemon bintrayUpload \
        "-Dtag=$TRAVIS_TAG" \
        "-Dpublish=true" \
        "-Duser=$BINTRAY_USER" "-Dkey=$BINTRAY_KEY"
fi
