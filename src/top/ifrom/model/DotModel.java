package top.ifrom.model;

/**
 * Created by xiaohongqi on 2016/10/9.
 */
public class DotModel {
    private  int x;
    private  int y;//  点的横纵坐标

    // 其他属性,像点的颜色等可以进行继续扩展。
    public DotModel(){
        // 点的无参化的构造函数
        this.x = 0;
        this.y = 0;
    }

    public DotModel(int x, int y){
        this.x = x;
        this.y = y; // 进行点的坐标的赋值的情况。
    }

    @Override
    public boolean equals(Object obj) {
        // 进行覆盖父对象的函数
        boolean result =  false;
        DotModel tmp = (DotModel) obj;
        if (this.x == tmp.getX() && this.y == tmp.getY()){
            result = true;
        }else {
            result = false;
        }
        return result; // 进行判断两个函数重载的函数
    }

    //get 函数
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //set函数
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
