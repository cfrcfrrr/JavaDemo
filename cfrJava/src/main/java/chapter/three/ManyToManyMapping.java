package chapter.three;

class Admin {
	private String aid;
	private String password;
	private Role role;
	public Admin(String aid, String password) {
		this.aid = aid;
		this.password = password;
	}
	
	public String getInfo() {
		return "管理员密码：" + this.aid + "，管理员密码： " + this.password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}

class Role {
	private int rid;
	private String title;
	private Admin admins [];
	private Group groups [];
	public Role(int rid, String title) {
		this.rid = rid;
		this.title = title;
	}
	public String getInfo() {
		return "角色编码：" + this.rid + "，角色名称：" + this.title;
	}
	public Admin[] getAdmins() {
		return this.admins;
	}
	public void setAdmins(Admin admins[]) {
		this.admins = admins;
	}
	public void setGroups(Group groups[]) {
		this.groups = groups;
	}
	public Group [] getGroups() {
		return this.groups;
	}
}

class Group {
	private int gid;
	private String title;
	private Role roles [];
	private Action actions [];
	public Group(int gid, String title) {
		this.gid = gid;
		this.title = title;
	}
	public String getInfo() {
		return "组编码：" + this.gid + "，组名称：" + this.title;
	}
	public Role[] getRoles() {
		return roles;
	}
	public void setRoles(Role roles[]) {
		this.roles = roles;
	}
	public Action[] getActions() {
		return actions;
	}
	public void setActions(Action actions[]) {
		this.actions = actions;
	}
}

class Action {
	private int aid;
	private String title;
	private String url;
	private Group group;
	public Action(int aid, String title, String url) {
		this.aid = aid;
		this.title = title;
		this.url = url;
	}
	public String getInfo() {
		return "关系编号：" + this.aid + "，关系名称：" + this.title + "，关系地址：" + this.url;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
}
public class ManyToManyMapping {

	public static void main(String[] args) {
		Admin ad1 = new Admin("admin", "administrator");
		Admin ad2 = new Admin("guset", "guset");
		Admin ad3 = new Admin("cfrcfrrr", "god");
		Role r1 = new Role(1, "系统管理员");
		Role r2 = new Role(2, "信息管理员");
		Group g1 = new Group(10, "信息管理");
		Group g2 = new Group(11, "用户管理");
		Group g3 = new Group(12, "数据管理");
		Group g4 = new Group(13, "接口管理");
		Group g5 = new Group(14, "备份管理");
		Action ac1 = new Action(1001, "新闻发布", "-");
		Action ac2 = new Action(1002, "新闻列表", "-");
		Action ac3 = new Action(1003, "新闻审核", "-");
		Action ac4 = new Action(1004, "增加用户", "-");
		Action ac5 = new Action(1005, "登录日志", "-");
		Action ac6 = new Action(1006, "雇员数据", "-");
		Action ac7 = new Action(1007, "部门数据", "-");
		Action ac8 = new Action(1008, "公司数据", "-");
		Action ac9 = new Action(1009, "部门数据", "-");
		Action ac10 = new Action(1010, "xx数据", "-");
		Action ac11 = new Action(1011, "yy部门数据", "-");
		Action ac12 = new Action(1012, "zz部门数据", "-");
		ad1.setRole(r1);
		ad2.setRole(r2);
		ad3.setRole(r2);
		r1.setAdmins(new Admin[] {ad1});
		r2.setAdmins(new Admin[] {ad2, ad3});
		r1.setGroups(new Group[] {g1, g2, g3, g4, g5});
		r2.setGroups(new Group[] {g1, g2});
		g1.setRoles(new Role[] {r1, r2});
		g2.setRoles(new Role[] {r1, r2});
		g3.setRoles(new Role[] {r1});
		g4.setRoles(new Role[] {r1});
		g5.setRoles(new Role[] {r1});
		g1.setActions(new Action[] {ac1, ac2, ac3});
		g2.setActions(new Action[] {ac4, ac5, ac6});
		g3.setActions(new Action[] {ac7, ac8, ac9});
		g4.setActions(new Action[] {ac10, ac11});
		g5.setActions(new Action[] {ac12, ac12});
		ac1.setGroup(g1);
		ac2.setGroup(g1);
		ac3.setGroup(g1);
		ac4.setGroup(g2);
		ac5.setGroup(g2);
		ac6.setGroup(g2);
		ac7.setGroup(g3);
		ac8.setGroup(g3);
		ac9.setGroup(g4);
		ac10.setGroup(g4);
		ac11.setGroup(g5);
		ac12.setGroup(g5);
		System.out.println(ad1.getInfo());
		System.out.println(ad1.getRole().getInfo());
		for (int x = 0; x < ad1.getRole().getGroups().length; x ++) {
			System.out.println(ad1.getRole().getGroups()[x].getInfo());
			for (int y = 0; y < ad1.getRole().getGroups()[x].getActions().length; y ++) {
				System.out.println(ad1.getRole().getGroups()[x].getActions()[y].getInfo());
			}
		}
		System.out.println();
	}

}
