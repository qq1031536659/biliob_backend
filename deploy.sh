#!/usr/bin/env bash
source /etc/profile
cd /biliob_test/biliob_backend
git checkout main
git pull
mvn -DfailIfNoTests=false -Dtest package
pid=$(ps -ef | grep biliob | grep .jar | grep -v grep | awk '{print $2}')
if [[ -n ${pid} ]]
then
  kill -9 ${pid}
fi
nohup java -jar ./target/biliob*.jar 1>log.out 2>&1 &
