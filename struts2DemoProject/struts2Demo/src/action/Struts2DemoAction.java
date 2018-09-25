package action;

import com.opensymphony.xwork2.ActionSupport;

public class Struts2DemoAction extends ActionSupport  {
    @Override
    public String execute() throws Exception {
        System.out.println("---execute---");
        return "success";
    }

    public String action1(){
        System.out.println("---action1---");
        return "ok";
    }
    public String action2(){
        System.out.println("---action2---");
        return "success";
    }
    public String action3(){
        System.out.println("---action3---");
        return "success";
    }
    public String action4(){
        System.out.println("---action4---");
        return "success";
    }
    public String test(){
        System.out.println("---test---");
        return "success";
    }
}
