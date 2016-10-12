package top.ifrom.Listener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by xiaohongqi on 2016/9/30.
 */
public class MenuFileExitListener implements ActionListener{
    private JFrame frame;
    public MenuFileExitListener(JFrame frame){
        this.frame = frame;
    }
    public void actionPerformed(ActionEvent Event){
        int i = JOptionPane.showConfirmDialog(null, "是否确定退出程序?", "退出程序", JOptionPane.YES_NO_CANCEL_OPTION);
        if(i== 0){
            frame.dispose();
            System.exit(0); // 系统进行终止进程
        }else{
            System.out.println("程序退出进程中断");
        }
    }
}
