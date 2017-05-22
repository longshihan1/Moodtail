package com.longshihan.commonlibrary.model;

/**
 * @author longshihan
 * @time 2017/5/17 11:17
 * @des downlaodview的
 */

public class MovePoint {
  public   float startX;
  public   float moveX;
  public   float moveY;
  public   float radius;
  public   boolean isDraw;

    public MovePoint(float startX, float moveY, float radius) {
        this.startX = startX;
        this.moveY = moveY;
        this.radius = radius;
    }
}
