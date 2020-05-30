# elasticsearch集群部署

## 用户创建
>> ES 不允许使用root启动，故需要创建用户，三台机器均需创建
* 创建用户组
>> groupadd elasticsearch
* 创建用户
>> useradd elastic
* 设置密码
>> passwd elastic
* 设置密码为
>> elasticsearch
* 授权
>> chown -R elastic:elasticsearch /usr/local/elasticsearch/
* 切换用户
>> su elastic
## elasticsearch配置
* 集群名称
>> cluster.name: my-cluster
* 节点名称
>> node.name: master
* 数据存放地址
>> path.data: /usr/local/elasticsearch/data
* 日志存放地址
>> path.logs: /usr/local/elasticsearch/logs
* 锁定内存，防止es swap会降低性能
>> bootstrap.memory_lock: true
* 设置访问IP
>> network.host: 0.0.0.0
* 设置端口
>> http.post: 9200
* 集群成员
>> discovery.zen.ping.unicast.hosts: ["IP","IP","IP"]
* 3台机器允许2台互为备选主
>> discovery.zen.minimum_master_nodes: 2
* 适用于centos系统
>> bootstrap.system.call_filter: false
* 等待来自节点的ping响应时间，60s
>> client.transport.ping.timeout: 60s
* 允许选举时间处理速度慢或者拥赛网络的情况下的调整（较高的值保证故障少的几率）
>> discovery.zen.ping.timeout: 120s
* 修改JVM参数，jvm.options,建议为机器内存的一半
>> -Xmx 512m
>> -Xmx 512m
* 修改limits.conf文件

>> vim /etc/security/limits.conf

追加

>> \* soft nofile 65535

>> \* hard nofile 65535

>> \* soft memlock unlimited

>> \* hard memlock unlimited

>> elastic soft memlock unlimited

>> elastic soft memlock unlimited

* 编辑 
>> vim /etc/sysctl.conf

>> vm.max_map_count=655360

>> sysctl -p
* 后台启动
>> ./elasticsearch -d
