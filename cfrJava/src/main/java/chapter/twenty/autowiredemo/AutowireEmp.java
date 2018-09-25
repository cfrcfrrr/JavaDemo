package chapter.twenty.autowiredemo;

public class AutowireEmp {
	private int empno;
	private AutowireDept dept;
	public AutowireEmp() {
	}
	public AutowireEmp(AutowireDept dept) {
		this.dept = dept;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public AutowireDept getDept() {
		return dept;
	}
	public void setDept(AutowireDept dept) {
		this.dept = dept;
	}
}
