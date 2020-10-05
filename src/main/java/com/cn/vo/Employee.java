package com.cn.vo;

public class Employee {
    private int eid,eage;
    private String ename,esex;
    private Department department;
    public Employee(){

    }

    public Employee(int eid, int eage, String ename, String esex, Department department) {
        this.eid = eid;
        this.eage = eage;
        this.ename = ename;
        this.esex = esex;
        this.department = department;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getEage() {
        return eage;
    }

    public void setEage(int eage) {
        this.eage = eage;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEsex() {
        return esex;
    }

    public void setEsex(String esex) {
        this.esex = esex;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eid=" + eid +
                ", eage=" + eage +
                ", ename='" + ename + '\'' +
                ", esex='" + esex + '\'' +
                ", department=" + department.getDepname() +
                '}';
    }
}
