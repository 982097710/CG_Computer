package top.ifrom.view;

import top.ifrom.Listener.DrawPanelMouseListener;
import top.ifrom.algorithm.CirclePoints;
import top.ifrom.algorithm.DDALine;
import top.ifrom.model.CommandModel;
import top.ifrom.model.DotDataModel;
import top.ifrom.model.DotModel;
import top.ifrom.model.DotQueueDataModel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

/**
 * Created by xiaohongqi on 2016/10/3.
 */
public class DrawBoard extends JPanel {
    private  int WIDTH;
    private  int HEIGHT;
    private DotDataModel dotData;
    private  int N;
    private  DotDataModel dotData_old;
    private DotQueueDataModel dotQueueDataModel; // 这个点模型,的生成是根据DrawBoard 实时存在的。
    private CommandModel commandModel; // 命令模型,通过命令模型,来决定调用做什么样的操作。
    private  MainWindowPanel mainWindowPanel; // 获取到 MainWindowPanel 的引用;
    public DrawBoard(JPanel mainPanel){
        System.out.println("有参化画板构造生成");
        // 进行Jpanel 的属性的定制
        this.WIDTH =  this.getWidth();
        this.HEIGHT =  this.getHeight();
        this.setBackground(Color.black);// 设置空间的颜色为黑色。
        this.setToolTipText("绘图画板界面");
        this.setPreferredSize(new Dimension(mainPanel.getWidth()-20, mainPanel.getHeight()-40));
        N =20;
        //this.setDotDataModel(mainPanel.getWidth()-20, mainPanel.getHeight()-40, 20); // 进行初始化的点模型的密度是20 为其中的值的情况。
        // 画板内数据组件的构造
        this.dotData = new DotDataModel();
        this.dotQueueDataModel = new DotQueueDataModel();
        this.commandModel = new CommandModel();
        //输出调试参数
        System.out.println("主面板初始尺寸:"+mainPanel.getWidth()+"*"+mainPanel.getHeight());

    }

    public DrawBoard(){
        // DrawBoard 无参化构造
        WIDTH = 0;
        HEIGHT = 0;
        dotData = new DotDataModel();
        this.commandModel = new CommandModel();
    }

    public CommandModel getCommandModel() {
        return commandModel; // 使得其他的函数模型进行得到,命令模型。
    }

    public DotQueueDataModel getDotQueueDataModel() {
        return dotQueueDataModel; // 进行发回点队列模型提供给其他类进行操作使用。
    }

    public  boolean setMainWindowPanel(MainWindowPanel mainWindowPanel){
        this.mainWindowPanel = mainWindowPanel;
        //进行对视图,添加鼠标监听事件,进行相应用户的操作,目测要把DrawBoard 这个对象进行传递进去,注意自身的综合能力和素质。
        DrawPanelMouseListener listener = new DrawPanelMouseListener(this, this.mainWindowPanel);
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
        this.addMouseWheelListener(listener);
        // 程序控制台输出
        this.mainWindowPanel.getConsoleTextArea().append("画板加载完毕.\n");
        return true; // 进行设置MianWindowPanel
    }

    public  boolean setN(int n){
        this.N = n;
        return  true;
    }
    //设置点模型
    public boolean setDotDataModel(int n){
        dotData_old =  this.dotData;
        this.dotData =  new DotDataModel(this.getWidth(), this.getHeight(), n);
        reBuildDotData(this.dotData, this.dotData_old);
        return true;
    }

    private boolean reBuildDotData(DotDataModel newDotData, DotDataModel oldDotData){
        int N_old = oldDotData.getN();
        int N_new = newDotData.getN();
        //  进行点的重建
        int N = 0;
        if (N_old <= N_new){
            N = N_old;
        }else{
            N = N_new;
        }
        for (int i =0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (oldDotData.getNodeByIndex(i, j)){
                    newDotData.setNodes(i, j); // 进行重建
                }
            }
        }// 进行点的重建的过程
        return true; // 进行点的模型的重建
    }

    //得到点模型
    public DotDataModel getDotDataModel(){
        return  this.dotData;
    }

    //重写Java组件绘制方法。
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D painter = (Graphics2D) g;
        this.setDotDataModel(N);
        this.drawGrid(painter); // 进行重新绘制网格的操作;
        this.drowNodes(painter); // 进行网格上点的绘制;


    }

    // 进行模型绘制的重新操作
    private boolean drawGrid(Graphics2D painter){
        painter.setColor(Color.GRAY);
        int p_x = 0; int p_y = 0;
        DotDataModel data =  this.dotData;
        for (int i =0 ; i <= data.getN(); i++){
            // 进行网格的绘制。
            painter.drawLine(0, p_y, this.getWidth(), p_y);
            painter.drawLine(p_x, 0, p_x, this.getHeight());
            p_x += data.getStep_x();
            p_y += data.getStep_y();
        }
        return  true;
    }

    private boolean drowNodes(Graphics2D painter){
        painter.setColor(Color.GREEN);
        DotDataModel data =  this.dotData; // 得到点的数据模型;
        int i = 0; int j = 0;
        //data.setNodes(1, 1);
        for (i = 0; i <data.getN(); i++){
            for (j = 0; j < data.getN(); j++){
                // 进行每个点的值的判断,再以每个点为圆心画圆;
                if(data.getNodeByIndex(i, j)){
                    int x =  i * data.getStep_x();
                    int y =  j * data.getStep_y();
                    int r_x  = (int) (data.getStep_x()* 0.4);
                    int r_y  = (int) (data.getStep_y()* 0.4);
                    painter.fillOval(x-r_x, y-r_y, 2*r_x, 2*r_y); // 进行画实心点
                }
            }
        }
        return true;
    }

    private boolean clearDrawBoard(){
        for (int i = 0; i < this.getDotDataModel().getN(); i++) {
            Arrays.fill(this.getDotDataModel().getAllNodes()[i], false);
        }
        this.repaint(); // 通过清空所有的数组,来进行清除画布。
        //this.mainWindowPanel.getConsoleTextArea().append("清除数据点\n");
        return true; // 通过此函数,来进行清空drawboard;
    }

    public  boolean doFunction(){
        // 通过题目中的命令的模式,来进行执行相应的命令的操作。
        //System.out.println("进行执行相应的命令,来进行执行对应的操作."); // 进行Debug 的语句
        if (this.commandModel.getCommand() == CommandModel.NULLCHOICE){
            //System.out.println("您正处在随机画线的状态");
            //是随机进行画点的操作,并且在这个功能中,您可以对画板进行综合操作。
            this.mainWindowPanel.getConsoleTextArea().append("请选择左侧功能键,进行绘制操作。\n");
        }

        if (this.commandModel.getCommand() == CommandModel.ZHIXIAN){
            //这是画直线的操作。每次画直线就是先清除其中的点,然后再根据直线返回的数组,来进行绘制其余的点。
            clearDrawBoard(); // 先进行画图,然后通过VectorModel 来进行画图。
            // 进行画第一个点的情况
            DotModel tmp0 = this.getDotQueueDataModel().getDotAtIndex(0);
            this.getDotDataModel().setNodes(tmp0.getX(), tmp0.getY());
            for (int i = 1; i<this.getDotQueueDataModel().getSize(); i++){
                DotModel begin = this.getDotQueueDataModel().getDotAtIndex(i-1);
                DotModel end = this.getDotQueueDataModel().getDotAtIndex(i);
                DDALine line =  new DDALine(begin, end);
                Vector<DotModel>  dots =  line.getVector();
                for (int j = 0; j < dots.size(); j++){
                    DotModel inlineDot =  dots.get(j);
                    this.getDotDataModel().setNodes(inlineDot.getX(), inlineDot.getY());
                }
                //this.getDotDataModel().setNodes(tmp.getX(), tmp.getY());
            }
            DotModel tmpn = this.getDotQueueDataModel().getDotAtIndex(this.dotQueueDataModel.getSize()-1);
            this.getDotDataModel().setNodes(tmpn.getX(), tmpn.getY());
            //基本上通过这种情况,来进行控制的情况。
            this.repaint(); // 进行模型的重绘。
        }

        if(this.commandModel.getCommand() ==  CommandModel.YUAN){
            //System.out.println("正在进行画圆的操作");
            clearDrawBoard();
            // 进行画圆的操作。
            DotModel tmp0 = this.getDotQueueDataModel().getDotAtIndex(0);
            this.getDotDataModel().setNodes(tmp0.getX(), tmp0.getY());
            for(int i = 1; i < this.getDotQueueDataModel().getSize(); i++){
                DotModel origin = this.getDotQueueDataModel().getDotAtIndex(i-1);
                DotModel point  =  this.getDotQueueDataModel().getDotAtIndex(i);
                CirclePoints circlePoints = new CirclePoints(origin, point);
                Vector<DotModel> dots =  circlePoints.getVector();
                int ox =  origin.getX();
                int oy =  origin.getY();
                for (int j =0 ;j <dots.size(); j++){
                    DotModel onlineDot = dots.get(j);
                    int x =  onlineDot.getX();
                    int y =  onlineDot.getY();
                    this.getDotDataModel().setNodes(x+ox, y+oy);
                    this.getDotDataModel().setNodes(y+ox, x+oy);
                    this.getDotDataModel().setNodes(-x+ox, y+oy);
                    this.getDotDataModel().setNodes(y+ox, -x+oy);
                    this.getDotDataModel().setNodes(x+ox, -y+oy);
                    this.getDotDataModel().setNodes(-y+ox, x+oy);
                    this.getDotDataModel().setNodes(-x+ox, -y+oy);
                    this.getDotDataModel().setNodes(-y+ox, -x+oy);
                }
            }
        }
        return  true;
    }
}
