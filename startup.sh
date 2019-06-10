#!/bin/bash
port=8088
#根据端口号查询对应的pid
pid=$(netstat -nlp | grep :$port | awk '{print $7}' | awk -F"/" '{ print $1 }');

echo "pid=$pid"
#杀掉对应的进程，如果pid不存在，则不执行
if [  -n  "$pid"  ];  then
    echo "正在执行kill命令..."
    if kill  -9  $pid; then
       echo "正在执行nohup命令..."
       nohup java -jar good-learning-success.jar &
       echo "执行成功"
    fi
else
   echo "找不到pid，直接运行nohup命令"
   nohup java -jar good-learning-success.jar &
   echo "执行成功"
fi
#echo "查看端口执行情况："
#netstat -auptl | grep 9090