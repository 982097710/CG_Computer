package top.ifrom.model;

import java.util.Vector;

/**
 * Created by xiaohongqi on 2016/10/9.
 */
public class DotQueueDataModel {
    // 使用组合编写对象的方式,来进行实现无参化的构造的对象。
    private Vector<DotModel> vector; //添加鼠标每次点击的点。
    public  DotQueueDataModel(){
        //System.out.println("无参化Dots 队列对象构造");
        this.vector = new Vector<DotModel>(); //  尽心初始化点模型的向量。
    }

    public boolean addDot(DotModel activedDot){
        this.vector.add(activedDot); // 进行把或的点进行加进去。
        return true;
    }

    public DotModel getFirstDot(){
        return  this.vector.firstElement();
    }

    public DotModel getLastDot(){
        return this.vector.lastElement();// 进行得到最后一个点的情况。
    }

    public DotModel getDotAtIndex(int index){
        return  this.vector.get(index); // 进行通过索引来进行得到点集的模型
    }
    /*
    public  DotQueueDataModel(){
        System.out.println("有参化Dots 队列构造");
    }
    */

    public boolean removeDot(DotModel dotModel){
        this.vector.remove(dotModel);
        return  true;
    }

    public int getSize(){
        return this.vector.size(); // 进行得到向量的长度的大小,通过组合变量的方式。
    }

    public  boolean clearAllDots(){
        this.vector.removeAllElements();// 进行移除所有的点
        return  true; // 进行清除所有的点
    }
}
