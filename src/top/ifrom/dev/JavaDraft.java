package top.ifrom.dev;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

/**
 * Created by xiaohongqi on 2016/10/2.
 */
public class JavaDraft {
    public static void main(String[] args) {
        System.out.println("模块化测试函数编写完成");
        JFrame mainWindow = new JFrame("函数绘图测试界面");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(640, 480);
        Toolkit kit  = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        int x =  (width- 640)/2; int y = (height-480)/2;
        mainWindow.setLocation(x, y);
        mainWindow.setContentPane(new Hello2DPanel()); // 进行界面的初始化的情况
        mainWindow.setVisible(true);
    }
}


class  Hello2DPanel extends JPanel{
    public Hello2DPanel(){
        this.setPreferredSize(new Dimension(640, 480)); // 设置窗口首选大小,这个是窗口首选的情况;
        // 这个分辨率,是合适的显示的分辨率;
    }

    // 重写组件绘图函数
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D  g2 =  (Graphics2D) g; //  进行强制装换Graphics 2D 对象,来进行对象的适配;
        g2.setColor(Color.blue);
        Ellipse2D e =  new Ellipse2D.Double(-100, -50, 200, 100);
        AffineTransform tr = new AffineTransform();
        tr.rotate(Math.PI/ 6.0); // 设置装置变换;
        Shape shape = tr.createTransformedShape(e);
        g2.translate(300, 200);
        g2.scale(2, 2);
        g2.draw(shape);
        g2.drawString("Hello 2D", 0, 0); //  绘制字符串
    }

}










