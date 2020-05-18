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