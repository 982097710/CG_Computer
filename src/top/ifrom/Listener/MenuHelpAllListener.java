package top.ifrom.Listener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

/**
 * Created by xiaohongqi on 2016/9/30.
 */
public class MenuHelpAllListener implements ActionListener {
    public MenuHelpAllListener(){
        System.out.println("菜单监听器生成");
    }
    public void actionPerformed(ActionEvent event){
        Desktop desktop =  Desktop.getDesktop();
        try{
            desktop.browse(new URI("http://pages.ifrom.top/Blog/notes/CGAllHelps"));
        }catch(Exception e){
            e.printStackTrace(); // 打印栈路径
        }
    }
}
