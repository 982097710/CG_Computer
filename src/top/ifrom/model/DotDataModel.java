package top.ifrom.model;

/**
 * Created by xiaohongqi on 2016/10/3.
 * 这是一个点的数据的model用来进行程序点集模型的初始化的作用。
 */
public class DotDataModel {
    private int n = 1;
    private boolean[][] nodes; // 所有点的数组
    private int width;
    private int height; //通过点模型进行记录对象的长和宽
    private int step_x; // x 方向的步进
    private int step_y; // y 方向的步进
    // 这里存在着点模型的重复定义,利用Java 虚拟机的垃圾回收的机制
    public  DotDataModel(){
        // 点模型无参数初始化
        nodes = new boolean[n][n];
        width = 0;
        height = 0;
        step_x = 0;
        step_y = 0;
    }

    public DotDataModel(int width, int height, int n){
        this.n = n;
        nodes = new boolean[n][n]; // 这里,因为Java 默认是数据模型是初始化的时候为零的状态。
        this.width = width;
        this.height = height; // 进行长和宽两种不同的尺寸的设定
        step_x = (int) (((double)this.width)/n);
        step_y =(int) (((double)this.height)/n); // 通过截取的方法,进行求解。
    }

    public int getN(){
        return  this.n; // 得到网格精度
    }

    public  boolean[][] getAllNodes(){
        // 得到所有电的引用
        return  nodes;
    }

    public boolean getNodeByIndex(int x, int y){
        // 通过索引值,来进行得到其中的某一点的bool值
        return nodes[x][y];
    }

    // 注意此时的数据模型的坐标点在显示界面的左上角。
    public  boolean setNodes(int x, int y){
        if (x<0 || y < 0 || x >=n || y >= n){
            return false;
        }
        this.nodes[x][y] = true;
        return  true;
    }
    public  boolean setNodesFalse(int x, int y){
        this.nodes[x][y] = false;
        return  true;
    }
    public int getStep_x(){
        return  this.step_x;
    }

    public int getStep_y(){
        return  this.step_y;
    }

    public static void main(String[] args) {
        DotDataModel data  =  new DotDataModel();
        data.setNodes(0, 0); // 测试数组设定函数的可行性
        System.out.println(data.getAllNodes()[0][0]); // 果然,默认所有的boolean 的值,为false;
    }
}
