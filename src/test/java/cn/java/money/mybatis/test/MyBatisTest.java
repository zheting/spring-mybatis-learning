package cn.java.money.mybatis.test;

import cn.java.money.entity.Employee;
import cn.java.money.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

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


        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 将xml和接口绑定， 获取接口的实现对象
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.getEmployeeById(1);
        System.out.println(employee);

        sqlSession.close();
    }


}
