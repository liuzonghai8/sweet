#新建父工程
1、家里maven父工程

#添加相关依赖
2、添加依赖

#创建注册中心工程
3、新建module  注册中心sea-registry

3.1 添加相关依赖

3.2 编写启动和添加启动注解、注册中心服务的注解

3.3 编写配件文件 配置注册中心服务

#创建网关工程
4、新建module sea-gateway

4.1、添加相关依赖

4.2 编写启动和添加启动注解、网关的注解

4.3 编写配件文件 配件网关服务zuul、ribbon、 hystrix 及注册客户

#创建通用用户权限服务工程
5、新建module sea-upms 父工程

5.1、新建sea-upms-interface 接口工程

5.2新建 sea-upms-service 服务工程

5.2.1 添加依赖、启动项

#创建通用common工程
6、新建sweet-common工程，提供工具




