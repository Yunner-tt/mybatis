package com.cn.vo;

import java.util.List;

public class Department {
    private int depid;
    private String depname;
    private List<Employee> employees;

    public Department() {
    }

    public Department(int depid, String depname) {
        this.depid = depid;
        this.depname = depname;
    }

    public Department(int depid, String depname, List<Employee> employees) {
        this.depid = depid;
        this.depname = depname;
        this.employees = employees;
    }

    public int getDepid() {
        return depid;
    }

    public void setDepid(int depid) {
        this.depid = depid;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "depid=" + depid +
                ", depname='" + depname + '\'' +
                ", employees=" + employees +
                '}';
    }
}
