package action;

import service.Struts2TransactionDemoService;

public class Struts2TransactionDemoAction {
    public String test() throws Exception {
        System.out.println("action...");
        new Struts2TransactionDemoService().test();
        return "success";
    }
}
