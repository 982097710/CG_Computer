package top.ifrom.Listener;

import top.ifrom.view.MainWindowPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * Created by xiaohongqi on 2016/10/4.
 */
public class ConsoleTextDocumentListener implements DocumentListener {
    private  MainWindowPanel mainWindowPanel;

    public ConsoleTextDocumentListener(MainWindowPanel mainWindowPanel){
        this.mainWindowPanel = mainWindowPanel; // 进行确定MainWindowPanel 的使用的方法
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        //System.out.println("JTextArea 内容发生改变");
        JScrollPane scrollPane =  this.mainWindowPanel.getScrollPane();
        int height = 15;
        int lineCount = this.mainWindowPanel.getConsoleTextArea().getLineCount()*height;
        if(lineCount >= 300){
            Point p = new Point();
            p.setLocation(0, this.mainWindowPanel.getConsoleTextArea().getLineCount()*height);
            scrollPane.getViewport().setViewPosition(p);
        }
        //进行重新定义位置;
    }
    @Override
    public void removeUpdate(DocumentEvent e) {

    }
}
