package top.ifrom.Listener;

import top.ifrom.view.FunctionsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by xiaohongqi on 2016/9/30.
 */
public class MenuFuncAllListener implements ActionListener {
    public MenuFuncAllListener (){
        System.out.println("函数功能展示监视器生成");

    }
     public void actionPerformed(ActionEvent event){
        System.out.println("动作执行");
         FunctionsView funcShowView  = new FunctionsView(); // 声明函数功能展示界面
     }
}
