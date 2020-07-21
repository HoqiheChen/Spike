# 一、Spike技术栈
前端：Thymeleaf + Bootstrap + JQuery

后端：SpringBoot + JSR303 + Mybatis

中间件：RabbitMQ + Redis +Druid

# 二、功能模块

## 1.用户登录模块

1）两次MD5

​	用户端：Password=MD5(明文+固定salt)

​	服务端：Password=MD5(用户输入+随机salt)

2）JSR303参数校验+全局异常处理器

3）分布式Session

## 2.秒杀模块

1）数据库设计

商品表

秒杀商品表

订单表

秒杀订单表

2）商品列表页

3）商品详情页

4）订单详情页

## 3.JMeter压测

1）JMeter入门



2）自定义变量模拟多用户



3）JMeter命令行使用



4）Redis压测工具redis-benchmark



5)Spring Boot 打war包

