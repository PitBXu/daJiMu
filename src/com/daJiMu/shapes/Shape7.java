package com.daJiMu.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Point2D;

public class Shape7 extends ShapeRoot {	
	{
		rect = new Arc2D.Double(x, y, width, hight, 0, 180, Arc2D.CHORD);
		localShape = AffineTransform.getRotateInstance(Math.toRadians(angle), centerX, centerY).createTransformedShape(rect);
	}
	
	//shape7 半圆
	/**
	 * 采用 合成逻辑 -> 交叉逻辑 -> 绘制逻辑
	 */
	
	/**
	 * 
	 * @param width 指定宽度
	 * @param hight 指定高度
	 * @param centerX 中心位置x
	 * @param centerY 中心位置y
	 */
	public Shape7(int width,int hight,int centerX,int centerY) {
		super(width,hight,centerX,centerY);
		x = centerX - width / 2;
		y = centerY - hight / 4;
	}
	
	/**
	 * 判断与其他域是否相撞
	 * @param rec 参数形状
	 * @return
	 */
	public boolean intersects(Area area) {
		Area as = this.toArea();
		as.intersect(area);
		if (!as.isEmpty()) {
			touchArea = as.getBounds2D();
		}
		return !as.isEmpty();
	}
	
	/**
	 * 将目标图形转化为区域
	 */
	@Override
	public Area toArea() {
		return new Area(localShape);
	}
	
	@Override
	public void drawShape(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		/**
		 * @version 1.0
		 * rect1 = new Rectangle2D.Double(x,y+hight,width,hight);
		 * rect2 = new Ellipse2D.Double(x,y,width,hight*2);
		 * Area a = new Area(rect2);
		 * a.subtract(new Area(rect1));
		 * g2.rotate(Math.toRadians(angle),centerX,centerY);
		 * g2.fill(a);
		 * g2.rotate(Math.toRadians(-angle),centerX,centerY);
		 */
		g2.setColor(Color.PINK);
		x = centerX - width / 2;
		y = centerY - hight / 4;
		rect = new Arc2D.Double(x, y, width, hight, 0, 180, Arc2D.CHORD);
		localShape = AffineTransform.getRotateInstance(Math.toRadians(angle), centerX, centerY).createTransformedShape(rect);
		//画一个半圆
		g2.fill(localShape);
		g2.setColor(Color.BLACK);
	}
	
	@Override
	public boolean contains(Point2D p) {
		return localShape.contains(p);
	}
}
