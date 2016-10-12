package top.ifrom.view;

import top.ifrom.Listener.FuncItemListener;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Created by xiaohongqi on 2016/9/30.
 */
public class FunctionsView extends JDialog {
    static final String Name =  "功能预览";
    static final int WIDTH = 350;
    static final int HEIGHT = 600; // 设置界面宽度
    private JPanel tmp;
    public FunctionsView(){
        System.out.println("功能展示页面生成");
        this.setTitle(Name);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        Toolkit kit  = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        int x =  (width- WIDTH)/2; int y = (height-HEIGHT)/2;
        this.setLocation(x, y);
        GridBagLayout lay = new GridBagLayout(); // 创建网格布局
        this.setLayout(lay);
        setLay(lay); // 自己本身的函数进行设置,布局的情况
        this.setVisible(true);
    }

    private  boolean setLay(GridBagLayout layout){
        GridBagConstraints constrains = new GridBagConstraints(); // 进行内部的布局的设置;
        constrains.fill = GridBagConstraints.BOTH;
        //constrains.anchor = GridBagConstraints.NORTHWEST;
        // 进行声明其
        Vector functions =  new Vector();
        functions.addElement("鼠标");
        functions.addElement("控制台"); // 进行控制台标签的选择;
        functions.addElement("功能三"); // 进行设置下来列表中的内容;
        JComboBox funcsCombo =  new JComboBox(functions);
        funcsCombo.setBorder(BorderFactory.createTitledBorder("功能选项"));
        //TextArea 的具体参数设置;
        String info = "这是软件功能的介绍模块:\n   通过下拉菜单,来进行查看相应的函数的功能。\n   注意:这个说明页面会在选择后消失!";
        JTextArea detailsText = new JTextArea(info);
        funcsCombo.addItemListener(new FuncItemListener(detailsText)); // 进行设置下来列表,控制事件来确定最终的事件的情况。
        detailsText.setLineWrap(true);
        detailsText.setEditable(false);
        JScrollPane ScrPane = new JScrollPane(detailsText,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); // 设置,显示范围;
        //ScrPane.add(detailsText);
        constrains.gridwidth = 0;
        constrains.weightx = 1;
        constrains.weighty = 0;
        layout.setConstraints(funcsCombo, constrains);
        this.add(funcsCombo);
        constrains.gridwidth = 0;
        constrains.weighty = 1;
        constrains.weightx = 0;
        layout.setConstraints(ScrPane, constrains);
        this.add(ScrPane);
        return true; // 进行返回波尔值,确定函数的执行成功
    }


}
