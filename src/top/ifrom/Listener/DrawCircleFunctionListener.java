package top.ifrom.Listener;

import top.ifrom.model.CommandModel;
import top.ifrom.view.DrawBoard;
import top.ifrom.view.MainWindowPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by xiaohongqi on 2016/10/9.
 */
public class DrawCircleFunctionListener implements ActionListener {

    private MainWindowPanel mainWindowPanel;
    private DrawBoard drawBoard;
    private CommandModel commandModel;

    public DrawCircleFunctionListener(){
        // 画圆八分法画出圆形;
        this.mainWindowPanel = null;
        this.drawBoard = null;
        this.commandModel = null;
    }

    public  DrawCircleFunctionListener(MainWindowPanel mainWindowPanel){
        this.mainWindowPanel = mainWindowPanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // 进行执行鼠标点击的效果
        this.commandModel = this.mainWindowPanel.getDrawBoard().getCommandModel(); //
        // 这里面存在着一个对象生成的先后的关系,要充分理解对象的构造的顺序,不然会造成bug.
        this.drawBoard = this.mainWindowPanel.getDrawBoard();
        if(this.commandModel.getCommand() !=  CommandModel.YUAN){
            this.commandModel.setCommand(CommandModel.YUAN);
            // 因为这里面我已经进行设置了新的功能,那这边就是要进行清除点。
            this.mainWindowPanel.clearDrawBoard(); // 进行清除画板上所有的点。
            this.mainWindowPanel.getConsoleTextArea().append("设置画圆操作\n");
        }else{
            this.commandModel.setCommand(CommandModel.NULLCHOICE);
            this.mainWindowPanel.getConsoleTextArea().append("取消画圆操作,左键画点的功能取消。\n");
        }
    }
}
