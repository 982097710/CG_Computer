package top.ifrom.Listener;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;

/**
 * Created by xiaohongqi on 2016/9/30.
 */
public class FuncItemListener implements ItemListener {
    private JTextArea detailText; // 接受上一层,传递过来的JTextArea 对象。
    public FuncItemListener(JTextArea detailText){
        System.out.println("功能函数下拉列表监视器生成");
        this.detailText = detailText;
    }

    public  void itemStateChanged(ItemEvent event){
        if(event.getStateChange() ==  ItemEvent.SELECTED){
            // 当用户的选择改变的时候,进行修改TextArea 的功能说明的部分。
            System.out.println("你选择的状态已经改变");
            //System.out.println(event.getItem()); // 显示状态
            String fileName  = null;
            if(event.getItem() == "鼠标"){
                //detailText.setText("你选择了功能一");
                fileName = "Mouse.xyw";
            }
            if (event.getItem() == "控制台"){
                //detailText.setText("你选择了功能二");
                fileName = "console.xyw";
            }
            if(event.getItem() == "功能三"){
                //detailText.setText("你选择了功能三");
                fileName = "func3.xyw";
            }
            //  这一块,用来进行测试系统命令,来进行设计的;
//            try {
//                Runtime.getRuntime().exec("open .");
//            }
//            catch (Exception e){
//                e.printStackTrace(); // 进行打印出函数的调用栈
//            }
            // 进行读取文件中的内容
            String fileContent = new String(); // 原来常量String 的类型会进行报错
            try {
                File file = new File("Functions/"+fileName);
                if(file.isFile() && file.exists()){
                    InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
                    BufferedReader reader = new BufferedReader(read);
                    String  line ; // 进行生命文件的读取的一行的内容
                    while ((line = reader.readLine()) != null){
                        fileContent += line;
                        fileContent += "\n";
                    }
                    System.out.println("输出文件内容"+ fileContent);
                    read.close(); // 进行关闭文件输入流
                }else{
                    System.out.println("文件打开错误");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            detailText.setText(fileContent); // 进行配置文件函数
        }
    }
}
