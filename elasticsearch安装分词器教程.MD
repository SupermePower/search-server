# 分词器安装
>> https://github.com/medcl/
* 下载对应的分词器，不同版本的分词器对应不通的git分支
* maven打包，将target/releases/.zip包上传到elasticsearch下的plugins文件夹下
* 在plugins文件夹下创建ik文件夹，将zip包移动到此目录
* 移动完成后解压zip包
* 确认plugin-descriptor.properties文件下elasticsearch.version是否与运行的es版本一致，不一致需进行修改