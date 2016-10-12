package top.ifrom.Listener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

/**
 * Created by xiaohongqi on 2016/9/30.
 */
public class MenuHelpAboutListener implements ActionListener{
    public MenuHelpAboutListener(){

    }
    public void actionPerformed(ActionEvent Event){
        Desktop desktop =  Desktop.getDesktop();
        try{
            desktop.browse(new URI("http://pages.ifrom.top/Blog/notes/CGabout"));
        }catch(Exception e){
            e.printStackTrace(); // 打印栈路径
        }
    }
}
