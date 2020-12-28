package cn.java.money.mybatis.test;

import cn.java.money.entity.Department2;
import cn.java.money.entity.Employee;
import cn.java.money.entity.Employee2;
import cn.java.money.mapper.DepartmentMapper;
import cn.java.money.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBatisTest {

    @Test
    public void test1() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 参数1： namespace+sqlid
        // 参数2：参数对象
        // 用这种方式调用的话，我们在mapper文件中的namespace和sql id是可以随意写的
        Employee employee = sqlSession.selectOne("cn.java.money.mapper.EmployeeMapper.selectEmp", 1);
        System.out.println(employee);
        sqlSession.close();
    }

    /*
     *  SqlSession 代表和数据库的一次会话，用完必须关闭
     *  SqlSession 和 Connection 一样都是非线程安全的。每次使用都应该去获取新的对象。
     *  mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象
     *      EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
     */

    @Test
    public void test2() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

       /* DataSource dataSource = new PooledDataSource( "com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/mybatis?characterEncoding=UTF-8&amp;&amp;serverTimezone=GMT","root","123456");
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(EmployeeMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);*/

        // 不会自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 将xml和接口绑定， 获取接口的实现对象
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.getEmployeeById(1);
        System.out.println(employee);

        sqlSession.close();
    }


    @Test
    public void test3() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        // 将xml和接口绑定， 获取接口的实现对象
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        // 增加
        Employee employee = new Employee(null, "tom", "tom@qq.com", "男");
        mapper.addEmployee(employee);
        // 修改
        employee.setGender("女");
        mapper.updateEmployee(employee);
        // 查询
        Employee employee2 = mapper.getEmployeeById(employee.getId());
        System.out.println(employee2);
        //删除
        mapper.deleteEmployee(employee.getId());

        sqlSession.close();
    }

    @Test
    public void test4() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        // 将xml和接口绑定， 获取接口的实现对象
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        // 查询
        Employee employee2 = mapper.getEmployeeByIdAndLastName(5, "tom");
        System.out.println(employee2);

        sqlSession.close();
    }

    @Test
    public void test5() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        // 将xml和接口绑定， 获取接口的实现对象
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        // 查询
        Employee employee = new Employee();
        employee.setId(5);
        employee.setLastName("tom");
        Employee employee2 = mapper.getEmployeeByIdAndLastName2(employee);
        System.out.println(employee2);

        sqlSession.close();
    }

    @Test
    public void test6() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        // 将xml和接口绑定， 获取接口的实现对象
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        // 查询
        Map map = new HashMap();
        map.put("id", 5);
        map.put("lastName", "tom");
        Employee employee2 = mapper.getEmployeeByIdAndLastName3(map);
        System.out.println(employee2);

        sqlSession.close();
    }

    @Test
    public void test7() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        // 将xml和接口绑定， 获取接口的实现对象
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> employees = mapper.getEmployeeByLastName("tom");
        System.out.println(employees);
        sqlSession.close();
    }

    @Test
    public void test8() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        // 将xml和接口绑定， 获取接口的实现对象
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Map<String, Object> employeeByIdReturnMap = mapper.getEmployeeByIdReturnMap(2);
        System.out.println(employeeByIdReturnMap);
        sqlSession.close();
    }

    @Test
    public void test9() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        // 将xml和接口绑定， 获取接口的实现对象
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Map<Integer, Employee> employeeByIdReturnMap = mapper.getEmployeeByLastNameReturnMap("tom");
        System.out.println(employeeByIdReturnMap);
        sqlSession.close();
    }

    @Test
    public void test10() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        // 将xml和接口绑定， 获取接口的实现对象
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> list = mapper.getEmployeeByLastName2("tom");
        System.out.println(list);
        sqlSession.close();
    }

    @Test
    public void test11() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        // 将xml和接口绑定， 获取接口的实现对象
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee2 empAndDept = mapper.getEmpAndDept(2);
        System.out.println(empAndDept);
        System.out.println(empAndDept.getDepartment().getDeptName());
        sqlSession.close();
    }

    @Test
    public void test12() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        // 将xml和接口绑定， 获取接口的实现对象
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee2 empAndDept = mapper.getEmpAndDept2(2);
        System.out.println(empAndDept);
        System.out.println(empAndDept.getDepartment().getDeptName());
        sqlSession.close();
    }

    /*
     * 延时加载测试
     */
    @Test
    public void test13() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        // 将xml和接口绑定， 获取接口的实现对象
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee2 empAndDept = mapper.getEmpAndDept2(2);
        //懒加载，如果只是获取employee相关的属性值就不会查询 select属性指定的sql
        //<association property="department" select="cn.java.money.mapper.DepartmentMapper.getDepartmentById" column="dep_id"/>
        System.out.println(empAndDept.getEmail());
        sqlSession.close();
    }

    @Test
    public void test14() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        // 将xml和接口绑定， 获取接口的实现对象
        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
        Department2  department2 = mapper.getDepartmentByIdPluse(1);
        System.out.println(department2);
        sqlSession.close();
    }

    @Test
    public void test15() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        // 将xml和接口绑定， 获取接口的实现对象
        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
        Department2  department2 = mapper.getDepartmentByIdStep(1);
        System.out.println(department2);
        sqlSession.close();
    }

    @Test
    public void test16() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        // 将xml和接口绑定， 获取接口的实现对象
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee2 empAndDept = mapper.getEmpAndDept3(5);
        System.out.println(empAndDept);
        sqlSession.close();
    }
}
