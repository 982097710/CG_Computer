package top.ifrom.Listener;

import top.ifrom.console.ConsoleController;
import top.ifrom.view.MainWindowPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by xiaohongqi on 2016/10/4.
 */
public class ConsoleSubmitListener implements ActionListener {
    private MainWindowPanel mainWindowPanel;
    public  ConsoleSubmitListener(MainWindowPanel mainWindowPanel){
        this.mainWindowPanel = mainWindowPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 进行监听提交按钮,实现同CommandKeyListener
        String command  = this.mainWindowPanel.getCommandField().getText();
        this.mainWindowPanel.getConsoleTextArea().append(">>" + command + "\n");
        this.mainWindowPanel.getCommandField().setText(""); // 进行TextField 清空。
        ConsoleController consoleController = new ConsoleController(this.mainWindowPanel);
        consoleController.doCommand(command);
        // 下面对于Command 功能的解析执行和分发的过程,都是通过ConsoleController 这个控制器来进行实现的。
    }
}
