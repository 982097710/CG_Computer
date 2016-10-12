package top.ifrom.algorithm;

import top.ifrom.model.DotModel;

import java.util.Vector;

/**
 * Created by xiaohongqi on 2016/10/9.
 */
public class DDALine {
    private DotModel begin;
    private DotModel end;
    private Vector<DotModel> vector; // 其中线模型也是自己也是要进行维护一定德的Vector 矢量。

    public  DDALine(DotModel begin, DotModel end){
        // 使用DDA,通过数值微分的情况,进行画直线
        this.begin  = begin;
        this.end = end; // 进行其实点对象的设定和进行结束点的对象的设定的情况。
        this.vector =  new Vector<DotModel>(); //通过DotModel 对象来进行初始化。
        this.vector.add(begin);
        System.out.println("起点:" + begin.getX()+","+begin.getY() + " 终点:" + end.getX() + "," + end.getY());//Debug 数据
        DDA(); // 进行使用DAA算法。调用私有函数。
        this.vector.add(end);
    }

    private  boolean DDA(){ //通过数值微分的算法,来进行画线。
        int deltaX =  Math.abs(end.getX()- begin.getX());
        int deltaY =  Math.abs(end.getY()- begin.getY());
        int dY = end.getY()- begin.getY();
        int dX = end.getX()- begin.getX();
        float x = begin.getX();
        float y = begin.getY();
        int epsl = 0;
        float xIncre = 0; float yIncre = 0;
        if (deltaX == 0){
            //System.out.println("x 等于0"); // debug 数据
            if (deltaY == 0){
                // 这个不做任何操作
            }else{
                for (int i = 1; i < deltaY; i++){
                    int x_i = this.vector.get(i-1).getX();
                    int y_i = this.vector.get(i-1).getY();
                    int tmp_Y = y_i + 1;
                    DotModel tmp = new DotModel(x_i, tmp_Y);
                    this.vector.add(tmp); // 进行处理分母为零的操作的情况。
                }
            }
        }else{
            if(deltaX > deltaY){
                epsl =  deltaX;
            }else{
                epsl =  deltaY;
            }
            xIncre = (float)dX/(float)epsl;
            yIncre = (float)dY/(float)epsl;
            for (int k = 0; k <=epsl; k++){
                DotModel tmp =  new DotModel(((int)(x+0.5)), ((int)(y+0.5)));
                this.vector.add(tmp);
                x += xIncre;
                y += yIncre;
            }
        }
        return true;
    }

    public Vector<DotModel> getVector(){
        return this.vector; // 通过返回函数进行返回自己生成的点集的情况。
    }

}
