package top.ifrom.Listener;

import top.ifrom.model.DotDataModel;
import top.ifrom.model.DotModel;
import top.ifrom.model.DotQueueDataModel;
import top.ifrom.view.DrawBoard;
import top.ifrom.view.MainWindowPanel;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import java.awt.event.*;

/**
 * Created by xiaohongqi on 2016/10/3.
 */
public class DrawPanelMouseListener  implements MouseMotionListener , MouseListener , MouseWheelListener{
    private DrawBoard drawBoard;
    private MainWindowPanel mainWindowPanel; // MainWindowPanel 的引用;
    private DotQueueDataModel dotQueueDataModel;

    public DrawPanelMouseListener(){
        System.out.println("鼠标监听事件无参化构造");
        this.drawBoard = null; //因为Java 其中存在着,构造细节,其中的存在着差异。
        this.mainWindowPanel = null;
        this.dotQueueDataModel = null;
    }
    // 监听器,有参构造函数;
    public DrawPanelMouseListener(DrawBoard drawBoard, MainWindowPanel mainWindowPanel){
        System.out.println("鼠标监听事件初始化成功");
        this.drawBoard = drawBoard;
        this.mainWindowPanel = mainWindowPanel;
        this.dotQueueDataModel = drawBoard.getDotQueueDataModel();

    }
    // 通过实现接口,实现类似于多重继承的思想;
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //DotQueueDataModel dotQueueDataModel = this.drawBoard.getDotQueueDataModel();
        // 通过鼠标左键,进行监视鼠标的点击事件。现在进行实现基本的。
        // 其实,通过测试发现,通过点击事件来进行确定,真的是很是有问题。
        /*
        DotDataModel data =  this.drawBoard.getDotDataModel();
        int set_x =  e.getX()/data.getStep_x();
        if (set_x >= data.getN()){
            set_x=data.getN()-1;
        }
        int set_y =  e.getY()/data.getStep_y();
        if (set_y >= data.getN()){
            set_y = data.getN()-1;
        }

        if (set_x < 0){
            set_x= 0;
        }

        if (set_y < 0){
            set_y = 0;
        }
        if (e.getButton() == MouseEvent.BUTTON1){

            // 进行在点上画线
            if (!data.getNodeByIndex(set_x, set_y)){
                data.setNodes(set_x, set_y);
                // 进行控制台状态输出;
                this.mainWindowPanel.getConsoleTextArea().append("绘制点,坐标:(" + set_x +"," + set_y + ").\n");
            }
        }

        */
        DotDataModel data =  this.drawBoard.getDotDataModel();
        int set_x =  e.getX()/data.getStep_x();
        if (set_x >= data.getN()){
            set_x=data.getN()-1;
        }
        int set_y =  e.getY()/data.getStep_y();
        if (set_y >= data.getN()){
            set_y = data.getN()-1;
        }

        if (set_x < 0){
            set_x= 0;
        }

        if (set_y < 0){
            set_y = 0;
        }
        if (e.getButton() == MouseEvent.BUTTON1){
            //System.out.println("调用鼠标Button1");
            // 通过实际的功能进行测试,反而是通过拖拽进行点的移动更能满足题目中要求的情况。
            // 而真正点的精度,可以通过限定点的范围来进行确定。
            /*
            if (!data.getNodeByIndex(set_x, set_y)){
                data.setNodes(set_x, set_y);
                // 进行控制台状态输出;
                this.mainWindowPanel.getConsoleTextArea().append("绘制点,坐标:(" + set_x +"," + set_y + ").\n");
            }
            */
            // 因为这其中涉及到了画点的原因,所以在这里只能通过画板模型来进行决定,到底是选择什么功能。
            //因为考虑到轨迹的重合的可能性。所以其中要把鼠标的每个点都进行添加到队列之中。
            DotModel dotModel =  new DotModel(set_x, set_y); // 通过进行生成点的情况,进行实际生成点的操作。
            this.dotQueueDataModel.addDot(dotModel); // 进行添加点到点队列之中。
            // 进行调用DrawBoard 的命令函数,来进行通过通过点的绘制的工作。
            this.drawBoard.doFunction(); // 通过点模型和数据模型来执行相应的操作。
        }

        if (e.getButton() == MouseEvent.BUTTON2){
            if (!data.getNodeByIndex(set_x, set_y)){
                data.setNodes(set_x, set_y);
                // 进行控制台状态输出;
                this.mainWindowPanel.getConsoleTextArea().append("绘制点,坐标:(" + set_x +"," + set_y + ").\n");
            }
        }
        if (e.getButton() == MouseEvent.BUTTON3){
            if(data.getNodeByIndex(set_x, set_y)){
                // 这个擦除操作的设置,真的是有双关的作用。
                data.setNodesFalse(set_x, set_y);
                //this.mainWindowPanel.getConsoleTextArea().append("擦除点,坐标:(" + set_x +"," + set_y + ").\n");
                //this.dotQueueDataModel.removeLast();
                DotModel tmp = new DotModel(set_x, set_y);
                this.dotQueueDataModel.removeDot(tmp);
                this.drawBoard.doFunction();
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }


    @Override
    public void mouseDragged(MouseEvent e) {
        // 鼠标中键担任画笔的功能;
        // 鼠标左键没有功能,鼠标右键担任橡皮的功能;
        // 鼠标滚轮,担任画笔的功能;

        //System.out.println("调用鼠标拖拽函数");

        DotDataModel data =  this.drawBoard.getDotDataModel();
        int set_x =  e.getX()/data.getStep_x();
        if (set_x >= data.getN()){
            set_x=data.getN()-1;
        }
        int set_y =  e.getY()/data.getStep_y();
        if (set_y >= data.getN()){
            set_y = data.getN()-1;
        }

        if (set_x < 0){
            set_x= 0;
        }

        if (set_y < 0){
            set_y = 0;
        }
        if (e.getButton() == MouseEvent.BUTTON1){
            //System.out.println("调用鼠标Button1");
            // 通过实际的功能进行测试,反而是通过拖拽进行点的移动更能满足题目中要求的情况。
            // 而真正点的精度,可以通过限定点的范围来进行确定。
            /*
            if (!data.getNodeByIndex(set_x, set_y)){
                data.setNodes(set_x, set_y);
                // 进行控制台状态输出;
                this.mainWindowPanel.getConsoleTextArea().append("绘制点,坐标:(" + set_x +"," + set_y + ").\n");
            }
            */
            // 因为这其中涉及到了画点的原因,所以在这里只能通过画板模型来进行决定,到底是选择什么功能。
            //因为考虑到轨迹的重合的可能性。所以其中要把鼠标的每个点都进行添加到队列之中。
            DotModel dotModel =  new DotModel(set_x, set_y); // 通过进行生成点的情况,进行实际生成点的操作。
            this.dotQueueDataModel.addDot(dotModel); // 进行添加点到点队列之中。
            // 进行调用DrawBoard 的命令函数,来进行通过通过点的绘制的工作。
            this.drawBoard.doFunction(); // 通过点模型和数据模型来执行相应的操作。
        }

        //if (e.getButton() == 1){
            if (!data.getNodeByIndex(set_x, set_y)){
                data.setNodes(set_x, set_y);
                // 进行控制台状态输出;
                this.mainWindowPanel.getConsoleTextArea().append("绘制点,坐标:(" + set_x +"," + set_y + ").\n");
            }
        //}
        /*
        if (e.getButton() == 2){
            if(data.getNodeByIndex(set_x, set_y)){
                // 这个擦除操作的设置,真的是有双关的作用。
                data.setNodesFalse(set_x, set_y);
                //this.mainWindowPanel.getConsoleTextArea().append("擦除点,坐标:(" + set_x +"," + set_y + ").\n");
                //this.dotQueueDataModel.removeLast();
                DotModel tmp = new DotModel(set_x, set_y);
                this.dotQueueDataModel.removeDot(tmp);
                this.drawBoard.doFunction();
            }
        }
        */

        //进行改变软件界面下面的状态条;
        JLabel status_text = mainWindowPanel.getStatus_Text();
        status_text.setText("鼠标坐标位置(" +e.getX()+"," + e.getY() + ")" );
        this.drawBoard.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        JLabel status_text = mainWindowPanel.getStatus_Text();
        status_text.setText("鼠标坐标位置(" +e.getX()+"," + e.getY() + ")" );
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        JSpinner spin = mainWindowPanel.getColSpinner();
        if (e.getWheelRotation() == 1){
            //System.out.println("鼠标滚轮向前");
            int value = (int)spin.getValue();
            spin.setValue(value+2);
        }
        if (e.getWheelRotation() == -1){
            //System.out.println("鼠标滚轮向后");
            int value = (int)spin.getValue();
            spin.setValue(value-2);
        }
    }
}
