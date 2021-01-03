#!/bin/sh

set -x

buildParam="-Dmaven.test.skip=true -P prod"
if [ "$*"x != x ]; then
        buildParam="$*"
        echo $buildParam |grep '\-Dfindbugs.skip=true'
        if [ "$?" != 0 ]; then
            buildParam=$(echo ' -Dfindbugs.skip=true '  $buildParam)
        fi
fi
echo 'clean and package'
mvn clean
mvn package -X -U $buildParam
mkdir output
mkdir output/bin
mkdir output/logs
mkdir output/conf
mkdir -p output/webapps/ROOT

mkdir /tmp/ehcache
chmod 777 /tmp/ehcache

echo 'cp file'
cp -r ./skywing output/
cp -r ./aioc-manager/target/aioc-manager.war output/webapps/ROOT/
cp -r ./bin/* output/bin/

cp -r ./conf/* output/conf/

cd output/webapps/ROOT
jar -xvf aioc-manager.war
rm -f aioc-manager.war
echo 'build end.'