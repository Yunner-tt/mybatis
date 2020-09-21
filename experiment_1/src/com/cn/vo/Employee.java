package com.cn.vo;

public class Employee {
	
	private int eno,eage,depid;
	private String ename,esex;
	public Employee() {
		super();
	}

	public Employee(int eno, int eage, int depid, String ename, String esex) {
		super();
		this.eno = eno;
		this.eage = eage;
		this.depid = depid;
		this.ename = ename;
		this.esex = esex;
	}



	public int getEno() {
		return eno;
	}
	public void setEno(int eno) {
		this.eno = eno;
	}
	public int getEage() {
		return eage;
	}
	public void setEage(int eage) {
		this.eage = eage;
	}
	public int getDepid() {
		return depid;
	}
	public void setDepid(int depid) {
		this.depid = depid;
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
	

	@Override
	public String toString() {
		return "Employee [eno=" + eno + ", eage=" + eage + ", depid=" + depid + ", ename=" + ename + ", esex=" + esex
				+ "]";
	}


}
