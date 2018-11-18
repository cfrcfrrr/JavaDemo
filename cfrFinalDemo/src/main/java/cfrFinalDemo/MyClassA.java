package cfrFinalDemo;

public class MyClassA {
    private static final String myClassStr = "aaa";
    private final String myObjectStr = "bbb";
    private final MyClassB myClassB = MyClassB.getInstance();
    private String finalAfterFirstAssignment = null;

    public static String getMyClassStr() {
        return myClassStr;
    }

    public String getMyObjectStr() {
        return myObjectStr;
    }

    public MyClassB getMyClassB() {
        return myClassB;
    }

    public String getFinalAfterFirstAssignment() {
        return finalAfterFirstAssignment;
    }

    public void setFinalAfterFirstAssignment(String finalAfterFirstAssignment) {
        if(this.finalAfterFirstAssignment == null) {
            this.finalAfterFirstAssignment = finalAfterFirstAssignment;
        }
    }
}
