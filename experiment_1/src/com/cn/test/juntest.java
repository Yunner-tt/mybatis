package com.cn.test;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cn.map.UserImpl;
import com.cn.vo.Employee;

public class juntest {
	public InputStream inputStream;
	public SqlSessionFactory sqlSessionFactory;
	public SqlSession session;
	public UserImpl testUser;
	@Before
	public void Init() throws IOException {
		inputStream = Resources.getResourceAsStream("mybatis.xml");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		session = sqlSessionFactory.openSession(true);
		testUser = session.getMapper(UserImpl.class);
	}
	@Test
	public void show_all() {//查找所有信息
		List<Employee> ls = new ArrayList<Employee>();
		ls = testUser.selectAll();
		for (Employee employee : ls) {
			System.out.println(employee);
		}
	}
	@Test
	public void show_bydep() {//根据部门查询（部门名字）
		List<Employee> ls = new ArrayList<Employee>();
		ls = testUser.selectAll_bydep("技术部门");
		for (Employee employee : ls) {
			System.out.println(employee);
		}
	}
	@Test
	public void select_byid() {//按照id查找
		Employee employee = new Employee();
		employee = testUser.selectbyid(3);
		System.out.println(employee);
	}
	@Test
	public void select_byidname() {//按照id和sex查找，四种方法
		
		Map<String, Object> mp = new HashMap<String, Object>();//这里key给的是string类型，value值给的object，这样就相当于命名变量，String是名字，value对应具体值，可以是int类型的id，也可以是String类型的name
		mp.put("age",28 );
		mp.put("sex", "男");
		
		Employee ep = new Employee();
		ep.setEsex("男");
		ep.setEage(24);
		
		List<Employee> ls = new ArrayList<Employee>();
		
		ls = testUser.selectone_1(30, "女");//按年龄性别查找：方法一顺序导入
		System.out.println("--------------------按年龄性别查找：方法一顺序导入--------------------------------");
		for (Employee employee : ls) {
			System.out.println(employee);
		}
		ls = testUser.selectone_2(40, "男");//按年龄性别查找：方法二注解导入
		System.out.println("--------------------按年龄性别查找：方法二注解导入--------------------------------");
		for (Employee employee : ls) {
			System.out.println(employee);
		}
		ls = testUser.selectone_3(mp);//按年龄性别查找：方法三Map形式导入
		System.out.println("--------------------按年龄性别查找：方法三Map形式导入--------------------------------");
		for (Employee employee : ls) {
			System.out.println(employee);
		}
		ls = testUser.selectone_4(ep);//按年龄性别查找：方法四javabean对象形式导入
		System.out.println("--------------------按年龄性别查找：方法四javabean对象形式导入--------------------------------");
		for (Employee employee : ls) {
			System.out.println(employee);
		}
	}
	@Test
	public void insert() {//插入数据(符合要求的数据，也就是符合主外键要求的数据，正常添加)
		show_all();
		Employee employee = new Employee(10,24,5,"王大哈","男");
		testUser.insert(employee);
		System.out.println("--------------------修改后------------------------------------------");
		show_all();
	}
	@Test
	public void insert_more() {//插入多条数据(符合要求的数据，也就是符合主外键要求的数据，正常添加)
		show_all();
		Employee employee = new Employee(11,24,5,"王大哈11","男");
		Employee employee1 = new Employee(12,24,5,"王大哈12","男");
		Employee employee2 = new Employee(13,24,5,"王大哈13","男");
		List<Employee> ls = new ArrayList<Employee>();
		ls.add(employee);
		ls.add(employee1);
		ls.add(employee2);
		testUser.insert_more(ls);;
		System.out.println("--------------------修改后------------------------------------------");
		show_all();
	}
	@Test
	public void update() {//修改单条数据（简化测试只修改姓名，这条sql语句很简单，需要修改所有可以自己加）
		show_all();
		Employee employee = new Employee(10,24,5,"刘大娘","男");
		testUser.update(employee);;
		System.out.println("--------------------修改后------------------------------------------");
		show_all();
	}
	@Test
	public void update_more() {//修改多条数据（简化测试只修改姓名，这条sql语句很简单，需要修改所有可以自己加）
		show_all();
		Employee employee = new Employee(11,24,5,"刘大娘11","男");
		Employee employee1 = new Employee(12,24,5,"刘大娘12","男");
		Employee employee2 = new Employee(13,24,5,"刘大娘13","男");
		List<Employee> ls = new ArrayList<Employee>();
		ls.add(employee);
		ls.add(employee1);
		ls.add(employee2);
		testUser.update_more(ls);
		System.out.println("--------------------修改后------------------------------------------");
		show_all();
	}
	@Test
	public void delete() {//删除单条数据（根据id删除）
		show_all();
		List<Integer> ls = new ArrayList<Integer>();
		ls.add(11);
		ls.add(12);
		ls.add(13);
		testUser.delet_more(ls);
		System.out.println("--------------------修改后------------------------------------------");
		show_all();
	}
	@Test
	public void delete_dep() {//删除部门信息，会顺带删除部门下员工信息，这里我用的是mysql里面外键的删除时属性设置为CASCADE，让数据库自己进行级联删除
		testUser.delet_dep("销售部门");
	}
	@Test
	public void truncate() {//这里的truncate清空数据库的方法比delete快很多，但是这个办法不能触发数据库触发器，所以这里对带有外键以及触发器的表无法删除，想完成操作，新建一个表，随便加一点数据，然后在下面的语句中填入表的名字，观察删除情况
		testUser.truncateTable("sd");//这里的参数填写表的名字
	}
	@After
	public void desory() {
		session.close();
	}

}
