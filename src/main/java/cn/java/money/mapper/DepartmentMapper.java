package cn.java.money.mapper;

import cn.java.money.entity.Department;
import cn.java.money.entity.Department2;

public interface DepartmentMapper {

    Department getDepartmentById(Integer id);

    //包含部门下的员工
    Department2 getDepartmentByIdPluse(Integer id);

    Department2 getDepartmentByIdStep(Integer id);

}
