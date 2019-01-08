## SpringBoot整合ElasticSearch ##

软件环境有：Eclipse(安装好STS)，Java1.8(SpringBoot版本要求JDK1.8以上)

### 一、创建SpringBoot项目 ###

1. 打开Eclipse，点击File->New->Spring Start Project，输入以下信息，点击next

![](https://i.imgur.com/9Jg6R1d.png)

2. 在窗口Available中查找Web和Elasticsearch依赖，并选中，点击Finish

![](https://i.imgur.com/HAuhcvA.png)

以上SpringBoot项目创建完成

二、对项目进行配置

1. 打开src/main/resource下的application.properties文件，输入以下配置
		
		# 服务端口
		server.port=8888
		# 服务名
		server.servlet.context-path=/elastic
		# 不严格匹配请求后缀
		spring.mvc.pathmatch.use-suffix-pattern=true
		#ElasticSearch节点
		spring.data.elasticsearch.cluster-nodes = 192.168.0.113:9300

2. 具体实现及说明，参考项目代码