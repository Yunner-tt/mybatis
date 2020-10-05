# Mybatis实验(已更新多表查询)
### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;写在前面： 因为在学习mybatis，为了更好的学习与交流，我把我学习过程中的心得与体会，放出来与大家一起分享交流进步。
># 使用环境(java13、idea2020)；本次实验在maven项目下运行
[employee]: https://github.com/Yunner-tt/mybatis/blob/master/picture/Employee.png
[department]: https://github.com/Yunner-tt/mybatis/blob/master/picture/Department.png
># 数据库结构
>### 数据库的结构以及内容(为了反映级联删除，这里仅仅就是新建了表，两张表没有建立外键关系)
>>![employee]
>>![department]

># 内容上有如下：
>># 查询
>>> * getAll()//获得所有员工信息
>>> * getDepart()//获得所有部门信息
>>> * getAllbyDepartment(String depname)//通过部门名字查询该部门下所有员工信息
>>> * getbyId(int id)//通过id获取员工
>>> * selectone_1(int age,String sex);//方法一顺序传入法
>>> * selectone_2(@Param("age") int age,@Param("sex") String sex)//方法二@param注解法
>>> * selectone_3(Map<String, Object> params)//方法三map集合映射法
>>> * selectone_4(Employee emp)//方法四javabean传入法
>># 插入
>>> * insert(Employee emp)//给员工表增加记录
>>> * insert_more(List<Employee> list)//给员工表增加多条记录
>>> * inset_department(Department department)//给部门表增加记录
>># 删除
>>> * delet(Employee employee)//删除一条员工记录
>>> * delet_more(List<Employee> list)//删除多条员工记录
>>> * ~~deletbyDepid(int depid)//按照部门的id删除~~
>>> * delet_dep(String depname)//根据部门的名字删除部门
>>> * truncateTable(String tablename)//根据表名删除表
>># 修改
>>> * update(Employee emp)//修改员工数据(这里只写了修改名字 下同)
>>> * update_more(List<Employee> list)//修改多条员工数据

># 总结：
>## &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;这次实验主要为了解掌握mybatis的配置，然后就是熟练掌握增删改查功能，能够使用动态sql进行批量操作，使用association或collection进行级联查询。

># 已知不足：
> * ## 动态SQL没有全部使用到，很多语句没有了解使用的方法
> * ## 没有建立Servuce层，进行级联删除的时候显得测试代码有点乱

># 查阅资料：
> * ## [多表查询](https://blog.csdn.net/weixin_38088097/article/details/106319734)
> * ## [动态SQL](https://mybatis.org/mybatis-3/zh/dynamic-sql.html)
>> #### 都是转载的，侵删。