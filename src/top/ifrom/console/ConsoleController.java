package top.ifrom.console;

import top.ifrom.view.DrawBoard;
import top.ifrom.view.MainWindowPanel;

import javax.swing.*;
import java.io.*;

/**
 * Created by xiaohongqi on 2016/10/4.
 */
public class ConsoleController {
    private  MainWindowPanel mainWindowPanel; //进行主窗口的绘制
    private DrawBoard drawBoard;

    public ConsoleController(){
        System.out.println("控制台控制器构造无参数构造函数");
    }

    public ConsoleController(MainWindowPanel mainWindowPanel){
        this.mainWindowPanel = mainWindowPanel;
        this.drawBoard = this.mainWindowPanel.getDrawBoard();
        // 现在是很是难搞清楚一件事情,到底是哪个对象先生成,到底是哪个对象后生成。
        // 现在这种关系睡很像是局部顺序执行,真个应用的功能离散化执行。
    }

    public boolean doCommand(String command){
        //在这里面,进行功能的分发;
        String[]  commands =  command.split(":", 2);
        if(commands[0].equals("OS")){
            //看来进行字符串数值的比较只能是通过equal 来进行比较数值了。
            doSystemCommand(commands[1]); // 进行执行系统命令;
        }else{
            doSoftCommand(command); // 进行执行软件命令;
        }
        //System.out.println("->"+ commands[0] + " " + commands[1] + "\n");
        return true;
    }

    private  boolean doSoftCommand(String command){
        //System.out.println(command);// 进行在控制台进行输出命令,进行调试
        if(command.trim().equals("clear")){
            this.mainWindowPanel.getConsoleTextArea().setText("");
        }else if (command.trim().equals("help")){
            String fileContent = new String(); // 原来常量String 的类型会进行报错
            // 下面这段代码,是重复的代码;
            try {
                File file = new File("Functions/"+"help.xyw");
                if(file.isFile() && file.exists()){
                    InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
                    BufferedReader reader = new BufferedReader(read);
                    String  line ; // 进行生命文件的读取的一行的内容
                    while ((line = reader.readLine()) != null){
                        fileContent += line;
                        fileContent += "\n";
                    }
                    //System.out.println("输出文件内容"+ fileContent);
                    read.close(); // 进行关闭文件输入流
                }else{
                    System.out.println("文件打开错误");
                    this.mainWindowPanel.getConsoleTextArea().append("->缺少命令帮助组件.\n");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            this.mainWindowPanel.getConsoleTextArea().append(fileContent);
        }
        else{
            this.mainWindowPanel.getConsoleTextArea().append("->命令没有进行整合,请使用「help」查看所有命令.\n");
        }
        return  true;
    }

    private  boolean doSystemCommand(String command){
        this.mainWindowPanel.getConsoleTextArea().append("->对不起占时不支持执行系统命令" +
                "只能支持软件内部的API\n");
        /*
        //System.out.print("执行系统命令" + command + "\n");
        try {
            Runtime rt = Runtime.getRuntime();
            Process p  = null;
            String osName = System.getProperty("os.name");
            String []cmd = new String[3];
            if (osName.equals("Mac OS X") || osName.equals("Linux")){
                p  = rt.exec(command);
            }else{
                cmd[0] = "command.com";
                cmd[1] = "/C";
                cmd[2] = command;
                p =  rt.exec(cmd);
            }
                // 错误信息
                StreamGobbler err = new StreamGobbler(p.getErrorStream(), "ERROR", this.mainWindowPanel.getConsoleTextArea());
                // 输出信息
                StreamGobbler out = new StreamGobbler(p.getErrorStream(), "OUTPUT", this.mainWindowPanel.getConsoleTextArea());
                // 进行线程生成
                err.start();
                out.start();
                int exitValue = p.waitFor();
                //  进行返回退出的进程码
                this.mainWindowPanel.getConsoleTextArea().append("Process exitValue="+ exitValue +"\n");

        }catch (Exception e){
            e.printStackTrace();
        }

        //System.out.println(osName);
        return true;
        */

        return true;
    }
}

class StreamGobbler extends Thread{
    InputStream is;
    String type;
    JTextArea consoleArea;
    public StreamGobbler(InputStream is, String type, JTextArea consoleArea){
        this.is = is;
        this.type = type;
        this.consoleArea = consoleArea; //控制台输出界面
    }

    public  void run(){
        try{
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            this.consoleArea.append(this.type+ ">\n");
            //System.out.println(this.type+ ">\n");
            while ((line = br.readLine()) !=null){
                // 输出到控制台的范围上
                this.consoleArea.append(line + "\n");
                //System.out.println(line + "\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}