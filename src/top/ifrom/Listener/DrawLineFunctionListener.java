package top.ifrom.Listener;

import top.ifrom.model.CommandModel;
import top.ifrom.view.DrawBoard;
import top.ifrom.view.MainWindowPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by xiaohongqi on 2016/10/8.
 */
public class DrawLineFunctionListener implements ActionListener {
    private  MainWindowPanel mainWindowPanel; // 主窗口控件
    private DrawBoard drawBoard; //画板控件
    private CommandModel commandModel; // 命令模型
    public  DrawLineFunctionListener(){
        // 画直线的无参化构造方法。
        this.mainWindowPanel = null;
        this.drawBoard =  null;
        this.commandModel = null;
    }

    public DrawLineFunctionListener(MainWindowPanel mainWindowPanel){
        // 进行初始化构造的画板的情况
        this.mainWindowPanel = mainWindowPanel;
        // 注意其中的先后的关系。
        //this.drawBoard = mainWindowPanel.getDrawBoard(); // 进行得到画板的DrawBoard 的实例化的控制对象
//        this.commandModel = drawBoard.getCommandModel(); // 进行通过drawBoard 得到CommandModel
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 进行监听点阵的时间,进行在显示的界面上进行加上点
        //System.out.println("正在进行画直线");
        this.commandModel = this.mainWindowPanel.getDrawBoard().getCommandModel(); //
        // 这里面存在着一个对象生成的先后的关系,要充分理解对象的构造的顺序,不然会造成bug.
        this.drawBoard = this.mainWindowPanel.getDrawBoard();
        if(this.commandModel.getCommand() !=  CommandModel.ZHIXIAN){
            this.commandModel.setCommand(CommandModel.ZHIXIAN);
            // 因为这里面我已经进行设置了新的功能,那这边就是要进行清除点。
            this.mainWindowPanel.clearDrawBoard(); // 进行清除画板上所有的点。
            this.mainWindowPanel.getConsoleTextArea().append("设置画直线操作\n");
        }else{
            this.commandModel.setCommand(CommandModel.NULLCHOICE);
            this.mainWindowPanel.getConsoleTextArea().append("取消画直线操作,左键画点的功能取消。\n");
        }
    }
}
