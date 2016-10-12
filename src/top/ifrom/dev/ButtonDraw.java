package top.ifrom.dev;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

/**
 * Created by xiaohongqi on 2016/10/2.
 */
public class ButtonDraw {


    private JPanel AllPanel;
    private JPanel FunctionsPanel;
    private JPanel MainPanel;
    private JButton Button_func_1;
    private JButton Button_func_2;
    private JPanel BashData;
    private JSpinner ColSpinner;
    private JTextArea BashTextArea;
    public JFrame mainWindow; // 进行设置主窗口,来让其变成全局对象来进行控制界面的具体的外观。
    private DrawBoard drawBoard; // 这个是上级传过来的引用;

    public ButtonDraw() {
        $$$setupUI$$$();
        BashTextArea = new JTextArea();
        BashTextArea.setBackground(Color.RED);
        BashTextArea.setLineWrap(true);
        //BashData.add(BashTextArea, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        //this.BashData.remove(this.BashTextArea);
        JScrollPane tmp = new JScrollPane(BashTextArea);
        tmp.setBackground(Color.RED);
        tmp.setForeground(Color.black);
        //
        //tmp.add(BashTextArea, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        //BashData.add(tmp, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        BashData.add(tmp, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        //this.BashData.add(tmp);
        //tmp.add(BashTextArea);
        Button_func_1.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             *
             * 通过设置,按钮的控制的工作。来进行那绘制。
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel superPanel = MainPanel; // 进行获得主面板的引用。
                mainWindow.setSize(800, 600); // 进行设置两个尺寸,来进行显示图像。
                Graphics2D g2 = (Graphics2D) superPanel.getGraphics(); //  进行强制装换Graphics 2D 对象,来进行对象的适配;
                g2.setColor(Color.blue);
                Ellipse2D e2 = new Ellipse2D.Double(-100, -50, 200, 100);
                AffineTransform tr = new AffineTransform();
                tr.rotate(Math.PI / 6.0); // 设置装置变换;
                Shape shape = tr.createTransformedShape(e2);
                g2.translate(300, 200);
                g2.scale(2, 2);
                g2.draw(shape);
                g2.drawString("Hello 2D", 0, 0); //  绘制字符串
            }
        });
        Button_func_2.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             *
             * 来通过第二个函数,来进行测试函数的绘制的情况;
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.setSize(800, 600);
                JPanel superPanel = MainPanel; // 进行获得主面板的引用。
                Graphics2D g2 = (Graphics2D) superPanel.getGraphics(); //  进行强制装换Graphics 2D 对象,来进行对象的适配;
                g2.setColor(Color.blue);
                Ellipse2D e2 = new Ellipse2D.Double(-100, -100, 200, 100);
                AffineTransform tr = new AffineTransform();
                tr.rotate(Math.PI / 6.0); // 设置装置变换;
                Shape shape = tr.createTransformedShape(e2);
                g2.translate(300, 200);
                g2.scale(2, 2);
                g2.draw(shape);
                g2.drawString("你好世界", 0, 0); //  绘制字符串
            }
        });
    }

    public JPanel getMainPanel() {
        System.out.println("进行获取MianPanel操作");
        return this.MainPanel;
    }

    public boolean setDrawBoard(DrawBoard drawBoard) {
        this.drawBoard = drawBoard;
        return true; // 通过返回bool 值来进行确定控件设置成功
    }

    public static void main(String[] args) {
        System.out.println("超计画板测试控件");
        JFrame mainWindow = new JFrame("画板元素重测试");
        mainWindow.setSize(800, 600);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ButtonDraw buttonDraw = new ButtonDraw();
        buttonDraw.mainWindow = mainWindow;
        mainWindow.setContentPane(buttonDraw.AllPanel);
        mainWindow.setVisible(true); // 进行设置,mainWindow 的可见性
        // 进行传递到DrawBoard 内部的属性;
        JPanel mainDrawPanel = buttonDraw.getMainPanel();
        DrawBoard drawBoard = new DrawBoard(mainDrawPanel);
        mainDrawPanel.add(drawBoard);
        buttonDraw.setDrawBoard(drawBoard); // 进行把DrawBoard 的引用进行传递下去;
        mainDrawPanel.repaint();
        // DrawBoard 进行传参完毕,这时DrawBoard 应该是和mainWindow 一样的顶级控件;
        // 参数传上来,还要进行传下去;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        // 进行通过 Custom 的方式,进行的控件的构造的方式
        MainPanel = new JPanel();
        BashData = new JPanel();
        //BashTextArea = new JTextArea();
//        JScrollPane pane = new JScrollPane();
//
//        BashData.add(pane);
//        JTextArea textArea = new JTextArea();
//        pane.add(textArea);
        //DrawBoard MainDrawPanel = new DrawBoard(MainPanel);
        //MainPanel.add(MainDrawPanel, BorderLayout.CENTER);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        AllPanel = new JPanel();
        AllPanel.setLayout(new BorderLayout(0, 0));
        FunctionsPanel = new JPanel();
        FunctionsPanel.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        AllPanel.add(FunctionsPanel, BorderLayout.WEST);
        FunctionsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "功能"));
        Button_func_1 = new JButton();
        Button_func_1.setText("画线");
        FunctionsPanel.add(Button_func_1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        FunctionsPanel.add(spacer1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        Button_func_2 = new JButton();
        Button_func_2.setText("画圆");
        FunctionsPanel.add(Button_func_2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ColSpinner = new JSpinner();
        FunctionsPanel.add(ColSpinner, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AllPanel.add(MainPanel, BorderLayout.CENTER);
        MainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "超级画板"));
        BashData = new JPanel();
        BashData.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        AllPanel.add(BashData, BorderLayout.EAST);
        BashData.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "控制台输出"));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return AllPanel;
    }
}
