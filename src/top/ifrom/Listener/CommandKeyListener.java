package top.ifrom.Listener;

import top.ifrom.console.ConsoleController;
import top.ifrom.view.MainWindowPanel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by xiaohongqi on 2016/10/4.
 */
public class CommandKeyListener implements KeyListener{
    private MainWindowPanel mainWindowPanel;
    private JTextField commandField;
    private JTextArea consoleArea;

    public  CommandKeyListener(){
        System.out.println("CommandKeyListener 无参数构造函数");

    }

    public  CommandKeyListener(MainWindowPanel mainWindowPanel){
        this.mainWindowPanel = mainWindowPanel;
        this.commandField = mainWindowPanel.getCommandField();
        this.consoleArea = mainWindowPanel.getConsoleTextArea(); // 进行获得关键的控件引用。
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // 进行keyPress 监听Enter,来进行执行,JTextField 的相关的操作。
        if (e.getKeyCode() ==  KeyEvent.VK_ENTER){
            //System.out.print("按下了回车键");
            // 得到命令行中的命令使用,清空命令行,并把相应的语句添加到ConsoleArea 中。
            String command  = this.commandField.getText();
            this.consoleArea.append(">>" + command + "\n");
            this.commandField.setText(""); // 进行TextField 清空。
            ConsoleController consoleController = new ConsoleController(this.mainWindowPanel); // 进行初始化命令控制类
            //通过命令显示命令行的操作。
            consoleController.doCommand(command);
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println("键盘被释放");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //System.out.println("键盘");
    }
}
