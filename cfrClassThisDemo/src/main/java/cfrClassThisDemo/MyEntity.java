package cfrClassThisDemo;

public class MyEntity {
    public void test() {
        System.out.println(MyEntity.this.getClass().getName()); // cfrClassThisDemo.MyEntity
    }
}
