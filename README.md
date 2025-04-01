# REST API 博客项目

功能：

1. 用户管理
2. 博客管理
3. 评论管理

运行：
```shell
mvn clean package -DSkipTests
java -jar .\target\springboot-trail-0.0.1-SNAPSHOT.jar --spring.profiles.active=local
```

todo：

1. ~~删除上级资源的同时需要删除下级资源~~
2. ~~使用专门的结构体映射请求体~~

特性：
1. 删除父资源，子资源连带删除；
2. 修改/删除博客或者评论需要传入当前的用户id，只有和博客或者评论的创建者id一致才能执行操作；
3. 删除用户时会同时删除用户下的博客、评论、点赞，但是博客或者评论的点赞数量不变