import com.cn.dao.EmployeeMapper;
import com.cn.vo.Department;
import com.cn.vo.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    public SqlSessionFactory sqlSessionFactory;
    public SqlSession session;
    public EmployeeMapper testUser;

    @Before
    public void Init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");//Resources为mybatis的工具类，用来
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession(true);
        testUser = session.getMapper(EmployeeMapper.class);
    }
    @After
    public void desory() {
        session.close();
    }

    @Test
    public void testss(){
        System.out.println("-----------------------------------------");
        List<Department> ls = new ArrayList<Department>();
        ls = testUser.getDepart();
        for ( Department tmp : ls) {
            System.out.println(tmp);
        };
    }

    @Test
    public void test_selectAll() {
        System.out.println("------------------------查询全部员工--------------------------------------");
        List<Employee> ss = new ArrayList<Employee>();
        ss = testUser.getAll();
        for (Employee temp: ss) {
            System.out.println(temp);
        }
    }
    @Test
    public void test_selectAll_Dep(){
        System.out.println("------------------------查询全部部门--------------------------------------");
        List<Department> ss = new ArrayList<Department>();
        ss = testUser.getDepart();
        for (Department temp: ss) {
            System.out.println(temp);
        }
    }
    @Test
    public void test_selectOne() {
        System.out.println("------------------------按照年龄性别查询的四种方式--------------------------------------");
        Map<String, Object> mp = new HashMap<String, Object>();//这里key给的是string类型，value值给的object，这样就相当于命名变量，String是名字，value对应具体值，可以是int类型的id，也可以是String类型的name
        mp.put("age", 22);
        mp.put("sex", "男");

        Employee ep = new Employee();
        ep.setEsex("男");
        ep.setEage(24);

        List<Employee> ls = new ArrayList<Employee>();

        ls = testUser.selectone_1(21, "女");//按年龄性别查找：方法一顺序导入
        System.out.println("--------------------按年龄性别查找：方法一顺序导入--------------------------------");
        for (Employee employee : ls) {
            System.out.println(employee);
        }
        ls = testUser.selectone_2(23, "男");//按年龄性别查找：方法二注解导入
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
    public void test_one() {//插入数据(符合要求的数据，也就是符合主外键要求的数据，正常添加)
        System.out.println("------------------------插入一条员工信息--------------------------------------");
        System.out.println("--------------------原库------------------------------------------");
        test_selectAll();
        System.out.println("--------------------增加后------------------------------------------");
        Department dep = new Department(1, "技术部门");
        Employee employee = new Employee(10, 24, "王大哈", "男", dep);
        testUser.insert(employee);
        test_selectAll();
        System.out.println("--------------------修改后------------------------------------------");
        employee.setEname("王大傻");
        testUser.update(employee);
        test_selectAll();
        System.out.println("--------------------删除后------------------------------------------");
        testUser.delet(employee);
        test_selectAll();
    }

    @Test
    public void test_more() {//插入多条数据(符合要求的数据，也就是符合主外键要求的数据，正常添加)
        System.out.println("------------------------插入多条员工信息--------------------------------------");
        System.out.println("--------------------原库------------------------------------------");
        test_selectAll();
        System.out.println("--------------------增加后------------------------------------------");
        Department dep = new Department(1, "技术部门");
        Employee employee = new Employee(11, 24, "王大哈11", "男", dep);
        Employee employee1 = new Employee(12, 24, "王大哈11", "男", dep);
        Employee employee2 = new Employee(13, 24, "王大哈11", "男", dep);
        List<Employee> ls = new ArrayList<Employee>();
        ls.add(employee);
        ls.add(employee1);
        ls.add(employee2);
        testUser.insert_more(ls);
        test_selectAll();
        System.out.println("--------------------修改后------------------------------------------");
        ls.get(0).setEname("多次增加0");
        ls.get(1).setEname("多次增加1");
        ls.get(2).setEname("多次增加2");
        testUser.update_more(ls);
        test_selectAll();
        System.out.println("--------------------删除后------------------------------------------");
        testUser.delet_more(ls);
        test_selectAll();
    }


    @Test
    public void delete_cascad() {//级联删除测试,删除部门时删除本地员工
        System.out.println("------------------------级联删除--------------------------------------");
        Department temp_department = new Department(10,"级联测试部门");
        Employee temp_employee = new Employee(15, 24, "级联测试人员", "男", temp_department);
        testUser.inset_department(temp_department);
        testUser.insert(temp_employee);
        test_selectAll();
        System.out.println("-----------------------------");
        test_selectAll_Dep();
        cascadingDelete(temp_department);
        System.out.println("-------------删除后----------------");
        test_selectAll();
        System.out.println("-----------------------------");
        test_selectAll_Dep();
    }

    @Test
    public void insert_creat() {
        System.out.println("------------------------新增同时创建--------------------------------------");
        System.out.println("---------------原来的两个表内容--------------");
        test_selectAll();
        System.out.println("-----------------------------");
        test_selectAll_Dep();
        Department dep = new Department(10, "测试新增部门");
        Employee employee = new Employee(11, 24, "测试新增员工", "王大哈", dep);
        insertt(employee);
        System.out.println("---------------新增后的两个表的内容--------------");
        test_selectAll();
        System.out.println("-----------------------------");
        test_selectAll_Dep();

        //测试完成后删除内容
        cascadingDelete(dep);
    }


    /*这里没有做service层，但是因为需要完成级联删除等等，所以在这里临时建立几个函数充当service的功能。*/
    public void cascadingDelete(Department department) {//级联删除，检查employee，如果
        List<Employee> ls = new ArrayList<Employee>();
        ls = testUser.getAllbyDepartment(department.getDepname());//新增加了getdepart()可以在获取所有部门的同时，获取到该部门下所有的员工
        if (ls.isEmpty()) testUser.delet_dep(department.getDepname());
        else {
            testUser.delet_more(ls);
            testUser.delet_dep(department.getDepname());
        }
    }

    public void insertt(Employee employee) {//插入二号方法，插入时检测部门信息，如果不存在相应的部门，创建该部门
        List<Department> ls = new ArrayList<Department>();
        ls = testUser.getDepart();
        boolean p = false;
        for (Department temp : ls) {
            if (temp.getDepid() == employee.getDepartment().getDepid()) p = true;
        }
        if (!p) {
            testUser.inset_department(employee.getDepartment());
        }
        testUser.insert(employee);
    }

    public void inserttt(Employee employee) {//插入一号方法，插入时检测部门信息，如果不存在相应的部门，控制台弹出提示
        List<Department> ls = new ArrayList<Department>();
        ls = testUser.getDepart();
        boolean p = false;
        for (Department temp : ls) {
            if (temp.getDepid() == employee.getEid()) p = true;
        }
        if (!p) {
            System.out.println("部门存在，出错误了");
        } else {
            testUser.insert(employee);
        }
    }
}