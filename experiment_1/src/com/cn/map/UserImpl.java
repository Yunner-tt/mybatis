package com.cn.map;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

import com.cn.vo.Employee;


public interface UserImpl {
	/*查找全部，无参*/
	public List<Employee> selectAll();
	public List<Employee> selectAll_bydep(String depname);
	/*按id查找，一个参数*/
	public Employee selectbyid(int id);
	
	/*按age和sex查找，两个个参数，方法一顺序传入法*/
	public List<Employee> selectone_1(int age,String sex);
	/*按age和sex查找，两个个参数，方法二@param注解法*/
	public List<Employee> selectone_2(@Param("age") int age,@Param("sex") String sex);
	/*按age和sex查找，两个个参数，方法三map集合映射法*/
	public List<Employee> selectone_3(Map<String, Object> params);
	/*按age和sex查找，两个个参数，方法四javabean传入法*/
	public List<Employee> selectone_4(Employee emp);
	
	/*增加数据*/
	public void insert(Employee emp);
	public void insert_more(List<Employee> list);
	public int inset_department(Map<String, Object> params);//插入部门表记录
	/*删除数据*/
	public void delet(int id);
	public void delet_more(List<Integer> list);//批量删除
	public void delet_dep(String depname);//根据部门名字删除部门同时删除该部门下员工信息，这里有三种方法：一、直接在mysql中绑定外键的删除时属性为CASCADE 二、就是本方法 三、编写多个sql语句，先执行子数据表的删除，然后执行目标数据表的删除
	public void truncateTable(String tablename);//truncate快速删除表，只删除表内数据，不删除表结构（不能删除带有外键约束的表，否则只能通过delet删除）eg:drop删除会删除表结构
	/*修改数据*/
	public void update(Employee emp);
	public void update_more(List<Employee> list);


	
}
