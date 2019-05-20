## 前后端整合

:date:04-29

后端初始时没有用户，使用InitService创建一个超级管理员，再完成教师、学生等信息的添加（一般通过Excel表格导入）

用户的权限（角色）像token一样，会被保存到sessionStroage

后端的权限不再是简单的1和2，而是一个字符串（哈希生成的），这样可以防止伪造权限

:date:05-20

后端中的一些疑问：

1. service和repository区别，还有controller

   实现的功能貌似重叠？个人理解是repository实现增删改查，service实现业务逻辑，然而在我们的后端中，`homeworkservice`下有两个list---Homeworks的方法，其中直接调用了repository中的查询方法，那这个service有何存在的必要呢？直接注入repository岂不更简单

2. 本项目中有两个拦截器，如何判断先经过哪一个呢，是按声明的顺序吗

3. `LoginController`中为何在response头部塞入token之后，要设置一个role呢，记录的权限对应的哈希值

4. 将student和teacher用一个`User`类型表示，有何好处？

5. 应用刚开始启动时为何要设置时区呢

注意事项：

1. Map.of()方法，中的值不能为null
2. test已编写完毕，执行顺序`logincontroller.http`得到token，再用`admincontroller`添加课程后添加作业，再用`usercontroller`查看课程和作业信息