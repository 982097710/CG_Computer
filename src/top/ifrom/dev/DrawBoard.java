package top.ifrom.dev;

import javax.swing.*;
import java.awt.*;

/**
 * Created by xiaohongqi on 2016/10/3.
 */
public class DrawBoard extends JPanel {

    public  DrawBoard(JPanel upPanel){
        super();
        // 进行Jpanel 的属性的定制
        this.setBackground(Color.black);// 设置空间的颜色为黑色。
        this.setToolTipText("绘图画板界面");
        //System.out.println("上级空间的尺寸:" + upPanel.getWidth() + upPanel.getHeight());
        this.setPreferredSize(new Dimension(upPanel.getWidth(), upPanel.getHeight()));
        System.out.println("绘图板控件初始化");
        JButton btn =  new JButton("点我");
        this.add(btn); // 这个方法很是凑效,通过使用画图板的重绘的功能进行了最终的网格构造的情况。
    }
}
