package top.ifrom.dev;

import javax.swing.*;

/**
 * Created by xiaohongqi on 2016/9/29.
 */
public class MainWindow {
    static final int WIDTH =  800;
    static final int HEIGHT  = 600;
    static final String WINDOWNAME = "计算机图形学";
    public static  void main(String[] args ){
        // 系统配置部分
        System.out.println("计算机图形学设计,窗体文件"); // 控制台输出语句
        // -- 针对mac os 进行优化的系统UI属性;
        System.setProperty("com.apple.macos.useScreenMenuBar", "true");
        System.setProperty("apple.awt.application.name", "计算机图形学");

        // 顶层容器,设置部分;
        JFrame mainWindow =  new JFrame(WINDOWNAME);
        mainWindow.setSize(WIDTH, HEIGHT);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //中间容器设置的设置的部分
        JPanel pane = new JPanel();
        mainWindow.setContentPane(pane);

        // 设置菜单部分
        JMenuBar menu = new JMenuBar();
        mainWindow.setJMenuBar(menu);
        JMenu menu_file =  new JMenu("文件");
        JMenu menu_edit =  new JMenu("编辑");
        JMenu menu_help =  new JMenu("帮助");
        menu.add(menu_file);
        menu.add(menu_edit);
        menu.add(menu_help); //添加帮助菜单
        JMenuItem menu_file_new = new JMenuItem("新建");
        JMenuItem menu_file_exit = new JMenuItem("退出");
        menu_file.add(menu_file_new);
        menu_file.add(menu_file_exit);

        //pane 内组件声明设置部分;
        JButton button  = new JButton("Start");
        pane.add(button);
        button.setVisible(true);

        // 组件可视化设置部分;
        mainWindow.setVisible(true); // 设置窗口可见
    }
}
