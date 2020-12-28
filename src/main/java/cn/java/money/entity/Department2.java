package cn.java.money.entity;

import java.util.List;

public class Department2 {
    private Integer id;
    private String deptName;
    // 部门下的所有员工
    private List<Employee2> employee2s;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<Employee2> getEmployee2s() {
        return employee2s;
    }

    public void setEmployee2s(List<Employee2> employee2s) {
        this.employee2s = employee2s;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Department2{");
        sb.append("id=").append(id);
        sb.append(", deptName='").append(deptName).append('\'');
        sb.append(", employee2s=").append(employee2s);
        sb.append('}');
        return sb.toString();
    }
}
