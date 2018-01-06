package com.helldefender.communityfairs.model.entity;

/**
 * Created by Helldefender on 2017/12/25 for CommunityFairs.
 * Function:
 * Description:
 */

public class OuterClass {
    private String language;
    private String region;

    private void getPrivateMethod() {

    }

    private void setLanguage(OuterClass outerClass) {
        this.language = outerClass.language; //当前对象实例。被声明为 private 的方法、变量和构造方法只能被所属类访问，并且类和接口不能声明为 private。
    }

    private int getInnerClassPrivateFields() {
        return InnerClass.getNum(new InnerClass());
    }

    public static String getLanguage(OuterClass outerClass) {
        //声明为private的方法、变量、构造方法只能被所属类访问。
        return outerClass.language; //为什么都是对象实例在外部类中可以使用写法：对象实例.私有成员变量。被声明为 private 的方法、变量和构造方法只能被所属类访问，并且类和接口不能声明为 private。
        //为什么仅仅可以在这个类中可以这样写
    }

    public static void getRegion(OuterClass outerClass) {
        outerClass.getPrivateMethod();
    }

    public static class InnerClass {
        private OuterClass outerClass;

        private int num;

        private void getOuterClassPrivateFields() {
            OuterClass.getLanguage(outerClass); //使用类名调用静态方法，传入外部类对象实例
        }

        //内部类不能含有静态方法； 编译器是如何实现的？创建对象实例,不使用静态方法 //只有静态内部类才能够含有静态方法
        //内部类相当于一个语法糖的结果，内部类在编译过后就是一个单独的类了
        public static int getNum(InnerClass innerClass) {
            return innerClass.num;
        }

        //JVM规范则在大多数时候都把每个Class都看作等价于top-level的，也就是说不关心enclosing / inner class之间的嵌套关系。
        // 对JVM来说，enclosing class和inner class在大部分情况下都是“不相关的两个类”，
        // 所以它们之间相互是不能访问对方的private/protected成员的。

    }
}
