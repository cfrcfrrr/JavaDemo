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
		return "����Ա���룺" + this.aid + "������Ա���룺 " + this.password;
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
		return "��ɫ���룺" + this.rid + "����ɫ���ƣ�" + this.title;
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
		return "����룺" + this.gid + "�������ƣ�" + this.title;
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
		return "��ϵ��ţ�" + this.aid + "����ϵ���ƣ�" + this.title + "����ϵ��ַ��" + this.url;
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
		Role r1 = new Role(1, "ϵͳ����Ա");
		Role r2 = new Role(2, "��Ϣ����Ա");
		Group g1 = new Group(10, "��Ϣ����");
		Group g2 = new Group(11, "�û�����");
		Group g3 = new Group(12, "���ݹ���");
		Group g4 = new Group(13, "�ӿڹ���");
		Group g5 = new Group(14, "���ݹ���");
		Action ac1 = new Action(1001, "���ŷ���", "-");
		Action ac2 = new Action(1002, "�����б�", "-");
		Action ac3 = new Action(1003, "�������", "-");
		Action ac4 = new Action(1004, "�����û�", "-");
		Action ac5 = new Action(1005, "��¼��־", "-");
		Action ac6 = new Action(1006, "��Ա����", "-");
		Action ac7 = new Action(1007, "��������", "-");
		Action ac8 = new Action(1008, "��˾����", "-");
		Action ac9 = new Action(1009, "��������", "-");
		Action ac10 = new Action(1010, "xx����", "-");
		Action ac11 = new Action(1011, "yy��������", "-");
		Action ac12 = new Action(1012, "zz��������", "-");
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
