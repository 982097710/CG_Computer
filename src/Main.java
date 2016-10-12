import top.ifrom.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        System.out.println("计算机图形学主程序运行");
        // -- 针对mac os 进行优化的系统UI属性;
        //System.setProperty("com.apple.macos.useScreenMenuBar", "true");
        //System.setProperty("apple.awt.application.name", "计算机图形学");
        MainView mainView =  new MainView();
        JTextArea consoleTextArea = mainView.getMainWindowPanel().getConsoleTextArea();
        consoleTextArea.append("计算机图形学软件初始化完成\n");
        mainView.setSize(1010, 650); // 通过这个语句,完成窗体强制重绘的功能;
        // 程序完全终结,后期可能进行功能配置的情况;

        // 进行全局定时器
        Timer timer =  new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = mainView.getMainWindowPanel().getStatus_Progress().getValue();
                if(value >=100){
                    value = 0;
                }else{
                    value++;
                }
                mainView.getMainWindowPanel().getStatus_Progress().setValue(value);
                mainView.getMainWindowPanel().getStatus_Window_Stat().setText("运行中");
                mainView.getMainWindowPanel().getStatus_Window_Stat().setForeground(Color.RED);
            }
        });
        //timer.start();
        timer.stop(); // 这个接口之后看怎么用。
    }
}
