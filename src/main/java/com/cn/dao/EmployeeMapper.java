package com.cn.dao;

import com.cn.vo.Department;
import com.cn.vo.Employee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {

    public List<Employee> getAll();//得到所有的员工记录
    public List<Department> getDepart();//得到所有部门信息
    public List<Employee> getAllbyDepartment(String depname);//按照部门名字返回该部门下的员工记录
    public Employee getbyId(int id);//通过ID获取员工信息


    /*按age和sex查找，两个个参数，方法一顺序传入法*/
    public List<Employee> selectone_1(int age,String sex);
    /*按age和sex查找，两个个参数，方法二@param注解法*/
    public List<Employee> selectone_2(@Param("age") int age,@Param("sex") String sex);
    /*按age和sex查找，两个个参数，方法三map集合映射法*/
    public List<Employee> selectone_3(Map<String, Object> params);
    /*按age和sex查找，两个个参数，方法四javabean传入法*/
    public List<Employee> selectone_4(Employee emp);

    /*增加数据*/
    public void insert(Employee emp);//增加员工数据
    public void insert_more(List<Employee> list);//批量增加员工数据
    public int inset_department(Department department);//插入部门表记录

    /*删除数据*/
    public void delet(Employee employee);//按照id删除相应的员工数据
    public void delet_more(List<Employee> list);//批量删除
    public void deletbyDepid(int depid);//按照部门的id删除（这个暂时不用，但是也保留这个接口）
    public void delet_dep(String depname);//根据部门名字删除部门（如果需要删除该部门下员工信息，这里有两种方法：一、直接在mysql中绑定外键的删除时属性为CASCADE 二、编写多个sql语句，先执行子数据表的删除，然后执行目标数据表的删除）
    public void truncateTable(String tablename);//truncate快速删除表，只删除表内数据，不删除表结构（不能删除带有外键约束的表，否则只能通过delet删除）eg:drop删除会删除表结构

    /*修改数据*/
    public void update(Employee emp);//只目前只做了修改姓名，需要自己可以加
    public void update_more(List<Employee> list);//只目前只做了修改姓名，需要自己可以加

}
