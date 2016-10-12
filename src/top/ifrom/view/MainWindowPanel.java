package top.ifrom.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import top.ifrom.Listener.*;
import top.ifrom.console.ConsoleController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;

/**
 * Created by xiaohongqi on 2016/10/2.
 * 这个是用来进行生成主函数内部的板块
 */
public class MainWindowPanel {
    public JPanel AllPanel;
    private JPanel FunctionsPanel;
    private JButton Buttion_func_1;
    private JPanel StatusPanel;
    private JPanel MainPanel;
    private JLabel Status_Title;
    private JProgressBar Status_Progress;
    private JLabel Status_Text;
    private JLabel Status_Title2;
    private JButton Buttion_func_2;
    private JButton Button_func_3;
    private JButton Button_func_4;
    private JLabel Status_Window_Stat;
    private JButton Button_func_5;
    private JPanel ConsolePanel;
    private JTextArea ConsoleTextArea;
    private JSpinner ColSpinner;
    private JButton ConsoleSaveButton;
    private JPanel CommandPanel;
    private JTextField CommandField;
    private JButton CommandEnter;
    private DrawBoard drawBoard;
    private JScrollPane scrollPane;

    public MainWindowPanel() {
        //this.DrawBoard = new DrawBoard();
        $$$setupUI$$$();
        // 通过代码的重写,实现了ScrollPanel 的功能;
        ConsoleTextArea.setBackground(new Color(-1250068));
        ConsoleTextArea.setLineWrap(true);
        ConsoleTextArea.setText("");
        ConsoleTextArea.setToolTipText("控制输出界面");
        this.scrollPane = new JScrollPane(ConsoleTextArea);
        ConsolePanel.add(scrollPane, BorderLayout.CENTER);
        // 添加ConsoleTextArea 监听器
        ConsoleTextArea.getDocument().addDocumentListener(new ConsoleTextDocumentListener(this));
        //this.MainPanel.add(this.DrawBoard);
        CommandField.addKeyListener(new CommandKeyListener(this)); // 尽心添加键盘相应
        ColSpinner.addChangeListener(new ChangeListener() {
            /**
             * Invoked when the target of the listener has changed its state.
             *
             * @param e a ChangeEvent object
             */
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = (int) ((JSpinner) e.getSource()).getValue();
                if (value <= 0 || value >= 150) {
                    ((JSpinner) e.getSource()).setValue(1);
                    value = 1; // 通过这个监听函数,来进行对 value 的范围进行限制
                }
                ConsoleTextArea.append("设置网格精度为:" + value + "\n");
                drawBoard.setN(value);
                drawBoard.repaint();
            }
        });
//        Buttion_func_1.addActionListener(new ActionListener() {
//            /**
//             * Invoked when an action occurs.
//             *
//             * @param e
//             */
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                drawBoard.getDotDataModel().setNodes(2, 2);
//                drawBoard.repaint();
//            }
//        });
        Button_func_5.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("清除所有数据点");
                /*
                for (int i = 0; i < drawBoard.getDotDataModel().getN(); i++) {
                    Arrays.fill(drawBoard.getDotDataModel().getAllNodes()[i], false);
                }
                drawBoard.repaint(); // 通过清空所有的数组,来进行清除画布。
                drawBoard.getDotQueueDataModel().clearAllDots();
                ConsoleTextArea.append("清除绘图点队列中所有的点\n");
                ConsoleTextArea.append("清除画板中所有的点\n");
                */
                clearDrawBoard();
            }
        });
        // 程序控制台状态输出
        this.ConsoleTextArea.append("程序主控板组件生成.\n");
        ConsoleSaveButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // 保存日志按钮响应事件
                JFileChooser fileChooser = new JFileChooser("."); // 通过使用Swing 的JFileChooser 来进行文件保存。
                fileChooser.setDialogTitle("保存日志");
                fileChooser.setToolTipText("保存日志文件选择器");
                FileOutputStream fileOutputStream = null; // 文件输入流
                int result = 0; // 返回文件对话框的返回值
                File file = null;
                result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    if (file != null) {
                        try {
                            fileOutputStream = new FileOutputStream(file);

                        } catch (FileNotFoundException event) {
                            ConsoleTextArea.append("文件没有找到\n");
                        }
                        String content = "控制台输保存内容:\n";
                        content += ConsoleTextArea.getText();
                        try {
                            fileOutputStream.write(content.getBytes());
                            ConsoleTextArea.setText("");
                        } catch (IOException ioe) {
                            ConsoleTextArea.append("外部文件写入错误\n");
                        } finally {
                            try {
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                            } catch (IOException ioe2) {
                                ConsoleTextArea.append("文件关闭错误\n");
                            }
                        }
                    }
                    ConsoleTextArea.append("日志成功保存在:" + file.getName() + "\n");
                } else if (result == JFileChooser.CANCEL_OPTION) {
                    ConsoleTextArea.append("保存日志进程结束\n");
                }
            }
        });
        CommandEnter.addActionListener(new ConsoleSubmitListener(this));
        Buttion_func_1.addActionListener(new DrawLineFunctionListener(this)); // 进行尝试第一个划线的功能。
        Button_func_3.addActionListener(new DrawCircleFunctionListener(this));// 进行画圆功能的实现。
    }
    public boolean clearDrawBoard() {
        for (int i = 0; i < drawBoard.getDotDataModel().getN(); i++) {
            Arrays.fill(drawBoard.getDotDataModel().getAllNodes()[i], false);
        }
        drawBoard.repaint(); // 通过清空所有的数组,来进行清除画布。
        drawBoard.getDotQueueDataModel().clearAllDots();
        ConsoleTextArea.append("清除绘图点队列中所有的点\n");
        ConsoleTextArea.append("清除画板中所有的点\n");
        return true;
    }

    public DrawBoard getDrawBoard() {
        // 进行返回DrawBoard 对象,实现画板的功能。
        return drawBoard;
    }

    public JTextField getCommandField() {
        return this.CommandField; // 返回控制窗口
    }

    public JProgressBar getStatus_Progress() {
        return Status_Progress; // 创建进度条
    }

    public JLabel getStatus_Window_Stat() {
        return this.Status_Window_Stat;
    }

    public JScrollPane getScrollPane() {
        // 返回scrollPane 用来进行实时定位。
        return this.scrollPane;
    }

    public JSpinner getColSpinner() {
        return ColSpinner;
    }

    public JLabel getStatus_Text() {
        return Status_Text;
    }

    public JPanel getMainPanel() {
        System.out.println("成功获取主面板");
        return MainPanel;
    }

    public boolean setDrawBoard(DrawBoard drawBoard) {
        this.drawBoard = drawBoard;
        return true; // 进行设置主面板
    }

    public JTextArea getConsoleTextArea() {
        return this.ConsoleTextArea;
    }

    // MainWindowPanel 模块测试主函数
    public static void main(String[] args) {
        MainWindowPanel test = new MainWindowPanel();
        System.out.println("生成MainDesigner!");
        JFrame mainWindow = new JFrame("Designer 窗口");
        mainWindow.setContentPane(test.AllPanel);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //mainWindow.pack();
        mainWindow.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        MainPanel = new JPanel(); // 因为这个要在这里进行声明所以后来才能进行对其进行对象操作。
        ColSpinner = new JSpinner();
        ColSpinner.setValue(20);
        ColSpinner.setBorder(BorderFactory.createTitledBorder("网格精度"));
        Status_Text = new JLabel();
        ConsoleTextArea = new JTextArea();
        Status_Window_Stat = new JLabel();
        Status_Progress = new JProgressBar();
        CommandField = new JTextField();
        Buttion_func_1 = new JButton();
        Button_func_3 = new JButton(); // 八分法画圆。
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
        FunctionsPanel.setLayout(new GridLayoutManager(8, 1, new Insets(0, 0, 0, 0), -1, -1));
        FunctionsPanel.setToolTipText("功能面板");
        AllPanel.add(FunctionsPanel, BorderLayout.WEST);
        FunctionsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "主控"));
        Buttion_func_1.setText("直线");
        FunctionsPanel.add(Buttion_func_1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        FunctionsPanel.add(spacer1, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        Buttion_func_2 = new JButton();
        Buttion_func_2.setHideActionText(false);
        Buttion_func_2.setText("Bresenham");
        Buttion_func_2.setToolTipText("使用中点Bresenham的方法,来进行画直线。");
        FunctionsPanel.add(Buttion_func_2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Button_func_3.setText("圆");
        Button_func_3.setToolTipText("画圆");
        FunctionsPanel.add(Button_func_3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Button_func_4 = new JButton();
        Button_func_4.setText("拓展");
        Button_func_4.setToolTipText("拓展功能,进行开源共同开发");
        FunctionsPanel.add(Button_func_4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Button_func_5 = new JButton();
        Button_func_5.setBackground(new Color(-1250068));
        Button_func_5.setForeground(new Color(-1088759));
        Button_func_5.setText("清除数据");
        Button_func_5.setToolTipText("清除主控界面上所有的点");
        FunctionsPanel.add(Button_func_5, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ColSpinner.setToolTipText("设置网格密度");
        FunctionsPanel.add(ColSpinner, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ConsoleSaveButton = new JButton();
        ConsoleSaveButton.setText("保存日志");
        ConsoleSaveButton.setToolTipText("保存控制台输出的内容");
        FunctionsPanel.add(ConsoleSaveButton, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        StatusPanel = new JPanel();
        StatusPanel.setLayout(new GridLayoutManager(1, 6, new Insets(0, 0, 0, 0), -1, -1));
        StatusPanel.setToolTipText("状态栏区域");
        AllPanel.add(StatusPanel, BorderLayout.SOUTH);
        StatusPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "状态"));
        Status_Title = new JLabel();
        Status_Title.setText("指针最近点坐标:");
        StatusPanel.add(Status_Title, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        StatusPanel.add(spacer2, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        Status_Progress.setForeground(new Color(-6099176));
        Status_Progress.setStringPainted(true);
        Status_Progress.setToolTipText("操作处理进度条");
        Status_Progress.setValue(10);
        StatusPanel.add(Status_Progress, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Status_Text.setText("坐标显示单元");
        StatusPanel.add(Status_Text, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Status_Title2 = new JLabel();
        Status_Title2.setText("进度:");
        StatusPanel.add(Status_Title2, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Status_Window_Stat.setText("运行中");
        Status_Window_Stat.setToolTipText("徐耀文-20144732-计算机科学与技术");
        StatusPanel.add(Status_Window_Stat, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        MainPanel.setToolTipText("超级画板区域");
        AllPanel.add(MainPanel, BorderLayout.CENTER);
        MainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "主控界面", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font(MainPanel.getFont().getName(), MainPanel.getFont().getStyle(), MainPanel.getFont().getSize()), new Color(-16777216)));
        ConsolePanel = new JPanel();
        ConsolePanel.setLayout(new BorderLayout(0, 0));
        ConsolePanel.setAutoscrolls(true);
        ConsolePanel.setMinimumSize(new Dimension(300, -1));
        ConsolePanel.setPreferredSize(new Dimension(250, 88));
        ConsolePanel.setVisible(true);
        AllPanel.add(ConsolePanel, BorderLayout.EAST);
        ConsolePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "控制台输出"));
        CommandPanel = new JPanel();
        CommandPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        ConsolePanel.add(CommandPanel, BorderLayout.SOUTH);
        CommandPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        CommandEnter = new JButton();
        CommandEnter.setText("提交");
        CommandEnter.setToolTipText("提交命令行命令");
        CommandPanel.add(CommandEnter, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CommandField.setToolTipText("命令行");
        CommandPanel.add(CommandField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return AllPanel;
    }
}
