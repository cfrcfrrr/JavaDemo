package chapter.twenty.pnamespacedemo;

public class PNamespaceEmp {
	private String ename;
	private int empno;
	private PNamespaceDept dept;
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public PNamespaceDept getDept() {
		return dept;
	}
	public void setDept(PNamespaceDept dept) {
		this.dept = dept;
	}
}
