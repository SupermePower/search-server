# KIBANA 安装下载

## 下载地址
>> https://www.newbe.pro/Mirrors/Mirrors-Kibana/

+ 配置
   - 进入kibana yml文件
       ````
       vim kibana.yml
       ````
   
    - 编辑配置信息
       ````
       #把localhost修改成真实ip
       server.host：“XXX” 
       #此kibana服务的名称
       Server.name：“XXX” 
       #ES访问路由,master节点
       elasticsearch.url： “http://XXX:9200” 
       #节点监控加密配置，flase不加密
       xpack.security.enabled: false 
       ````
 + 启动
    - 进入 bin 目录下,启动
        ````
        ./kibana 
        ````