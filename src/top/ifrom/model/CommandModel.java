package top.ifrom.model;

/**
 * Created by xiaohongqi on 2016/10/9.
 */
public class CommandModel {
    private  int  command;
    // 定义类中常量
    public  static final  int NULLCHOICE = -1; // 表示可以在屏幕上进行随意修改。
    public  static final  int ZHIXIAN =  0; // 表示在屏幕上进行画直线的功能。
    public  static final  int YUAN =  1;


    public  CommandModel(){
        // CommandModel 的无参化构造函数
        this.command =  -1;
    }

    public int getCommand() {
        // 通过这个函数,来进行返回当前的命令
        return command;
    }

    public void setCommand(int command) {
        // 通过这个函数,来进行设置当前的命令的模式。
        this.command = command;
    }
}
