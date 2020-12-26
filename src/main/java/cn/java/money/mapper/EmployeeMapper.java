package cn.java.money.mapper;

import cn.java.money.entity.Employee;

public interface EmployeeMapper {

    Employee getEmployeeById(Integer id);

    /*
     * mybatis 允许增删改直接定义以下类型的返回值
     * Integer,int,Long,long, Boolean,boolean, void
     */
    Integer addEmployee(Employee employee);

    Integer updateEmployee(Employee employee);

    Integer deleteEmployee(Integer id);
}
