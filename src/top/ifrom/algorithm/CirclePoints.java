package top.ifrom.algorithm;

import top.ifrom.model.DotModel;

import java.util.Vector;

/**
 * Created by xiaohongqi on 2016/10/9.
 */
public class CirclePoints {
    // 进行画圆的操作。
    // 利用八分法画圆。
    private  DotModel origin; //  原点
    private  DotModel point; // 参考点
    private Vector<DotModel> vector;
    private  int r;

    public  CirclePoints(DotModel origin, DotModel point){
        this.origin = origin;
        this.point = point;
        int deltaX = origin.getX()-point.getX();
        int deltaY = origin.getY() - point.getY();
        this.vector = new Vector<DotModel>(); // 进行可变形向量的声明构造
        r =  (int)Math.sqrt(((double) (deltaX*deltaX + deltaY * deltaY)));
        run(); // 进行主函数的运行。
    }
    // 通过x,y 来进行平移进行控制。
    private  boolean run(){
        //MidBresenCircle算法。
        int x = 0; int y = r; int d = 1 - r;
        while (x <= y){
            DotModel tmp =  new DotModel(x, y);
            this.vector.add(tmp); // 进行加入半圆的效果。
            if (d < 0){
                d += 2*x + 3;
            }else{
                d += 2*(x-y)+5;
                y--;
            }
            x++;
        }
        return true; // 进行主算法的控制。
    }

    public Vector<DotModel> getVector() {
        return vector; // 返回顶点向量。
    }
}
