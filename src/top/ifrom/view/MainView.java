package top.ifrom.view;

import sun.applet.Main;
import top.ifrom.Listener.*;
import top.ifrom.dev.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by xiaohongqi on 2016/9/30.
 */
public class MainView extends JFrame {
    private static int WIDTH = 999; // 设置window 的宽
    private static int HEIGHT = 650; // 设置window 的长
    private static String WINDOWNAME = "计算机图形学"; // 设置窗口名称
    private DrawBoard drawBoard;
    private MainWindowPanel mainWindowPanel;
    private JTextArea consoleTextArea;
    public MainView(){
        this.setTitle(WINDOWNAME);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT); // 设置控制面板的宽和高;
        //this.pack(); // 打包
        // 设置画板元素为屏幕中心
        Toolkit kit  = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        int x =  (width- WIDTH)/2; int y = (height-HEIGHT)/2;
        this.setLocation(x, y);
        // 设置图形界面菜单部分
        JMenuBar menu =  new JMenuBar();
        setMenuBar(menu);
        MainWindowPanel mainWindowPanel = new MainWindowPanel();
        this.mainWindowPanel = mainWindowPanel;
        this.consoleTextArea = mainWindowPanel.getConsoleTextArea();
        this.setContentPane(mainWindowPanel.AllPanel);
        this.setVisible(true);
        this.setJMenuBar(menu);
        //设置DrawBoard
        JPanel mainPanel = mainWindowPanel.getMainPanel();
        this.drawBoard =  new DrawBoard(mainPanel); // 进行得到主面板
        this.drawBoard.setMainWindowPanel(mainWindowPanel);
        mainWindowPanel.setDrawBoard(drawBoard);
        mainPanel.add(drawBoard);
        this.setPreferredSize(new Dimension(1000, 650));
        this.addComponentListener(new ComponentAdapter() {
            /**
             * Invoked when the component's size changes.
             *
             * @param e
             */
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                //drawBoard.setSize(mainPanel.getWidth(), mainPanel.getHeight());
                drawBoard.setPreferredSize(new Dimension(mainPanel.getWidth()-20, mainPanel.getHeight()-40));
                //进行画板的重绘
                // 进行DrawBoard 的重绘的操作
                drawBoard.repaint();
                drawBoard.repaint();// 进行第二次重新绘制。
                // 控制台参数输出
                //System.out.println("画板窗体重绘尺寸:" + drawBoard.getWidth() + "*" + drawBoard.getHeight());
                consoleTextArea.append("窗体重绘尺寸:" + drawBoard.getWidth() + "*" + drawBoard.getHeight()+"\n");
                mainWindowPanel.getStatus_Window_Stat().setText("重绘中");
                mainWindowPanel.getStatus_Window_Stat().setForeground(Color.BLUE);
            }
            @Override
            public void componentMoved(ComponentEvent e) {
                super.componentMoved(e);
                mainWindowPanel.getStatus_Window_Stat().setText("移动中");
                mainWindowPanel.getStatus_Window_Stat().setForeground(Color.DARK_GRAY);
            }

        });

        this.addWindowListener(new WindowAdapter() {
            /**
             * Invoked when the Window is set to be the focused Window, which means
             * that the Window, or one of its subcomponents, will receive keyboard
             * events.
             *
             * @param e
             * @since 1.4
             */
            @Override
            public void windowGainedFocus(WindowEvent e) {
                super.windowGainedFocus(e);
                mainWindowPanel.getStatus_Window_Stat().setText("运行");
                mainWindowPanel.getStatus_Window_Stat().setForeground(Color.GREEN);
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                super.windowLostFocus(e);
                mainWindowPanel.getStatus_Window_Stat().setText("占停");
                mainWindowPanel.getStatus_Window_Stat().setForeground(Color.RED);
            }
        });

        // 进行软件控制台输出
        this.consoleTextArea.append("程序主窗口生成.\n");
    }
    private boolean setMenuBar( JMenuBar menu){
        // 设置Menu 函数
        JMenu menu_file =  new JMenu("文件");
        JMenu menu_edit =  new JMenu("编辑");
        JMenu menu_func =  new JMenu("功能");
        JMenu menu_help =  new JMenu("帮助");
        menu.add(menu_file);
        menu.add(menu_edit);
        menu.add(menu_func); // 设置菜单项;
        menu.add(menu_help); // 设置帮助项;
        // 设置菜单子项;
        JMenuItem menu_file_new  = new JMenuItem("新建");
        JMenuItem menu_file_save = new JMenuItem("保存");
        JMenuItem menu_file_recent =  new JMenuItem("最近文件");
        JMenuItem menu_file_exit =  new JMenuItem("退出");
        menu_file_exit.addActionListener(new MenuFileExitListener(this));
        menu_file.add(menu_file_new);
        menu_file.add(menu_file_save);
        menu_file.add(menu_file_recent);
        menu_file.addSeparator();
        menu_file.add(menu_file_exit); // 设置第一个文件的选项
        JMenuItem menu_edit_copy =  new JMenuItem("拷贝");
        JMenuItem menu_edit_paste = new JMenuItem("粘贴");
        menu_edit.add(menu_edit_copy);
        menu_edit.add(menu_edit_paste);
        JMenuItem menu_func_all = new JMenuItem("功能预览");
        menu_func_all.addActionListener(new MenuFuncAllListener());
        menu_func.add(menu_func_all);
        JMenuItem menu_help_about =  new JMenuItem("关于");
        menu_help_about.addActionListener(new MenuHelpAboutListener());
        JMenuItem menu_help_all =  new JMenuItem("详细帮助");
        menu_help_all.addActionListener(new MenuHelpAllListener());
        JMenuItem menu_help_support = new JMenuItem("技术支持");
        menu_help_support.addActionListener(new MenuHelpSupportListener());
        menu_help.add(menu_help_about);
        menu_help.add(menu_help_all);
        menu_help.addSeparator();
        menu_help.add(menu_help_support);// 进行支持的设置
        return true;
    }

    public MainWindowPanel getMainWindowPanel(){
        return this.mainWindowPanel;
    }
}
