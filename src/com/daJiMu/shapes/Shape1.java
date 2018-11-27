package com.daJiMu.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Shape1 extends ShapeRoot {
	//shape1，竖版长方形
	/**
	 * 
	 * @param width 指定宽度
	 * @param hight 指定高度
	 * @param centerX 中心位置x
	 * @param centerY 中心位置y
	 */
	public Shape1(int width,int hight,int centerX,int centerY) {
		super(width,hight,centerX,centerY);
		x = centerX - width / 2;
		y = centerY - hight / 2;
		rect = new Rectangle2D.Double(x,y,width,hight);
		localShape = AffineTransform.getRotateInstance(Math.toRadians(angle), centerX, centerY).createTransformedShape(rect);
		rotateX = centerX;
		rotateY = centerY;
	}
	
	public void init() {
		rect = new Rectangle2D.Double(x,y,width,hight);
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
	
	/**
	 * 判断某个点是否在矩形内
	 */
	@Override
	public boolean contains(Point2D p) {
		//先旋转目标点
		/**
		 * 以重心为原点建立极坐标系
		 * 极坐标形式表示的p点坐标
		 */
		//Double ro1 = p.distance(centerX,centerY);
		//Double thx1 = Math.atan((p.getY() - centerY)/(p.getX() - centerX));
		/**
		 * 转换后的p'点的极坐标
		 */
		//Double ro2 = ro1;
		//Double thx2 = thx1 - Math.toRadians(angle);
		/**
		 * 将极坐标下的点转换到直角坐标，并移动原点
		 */
		//Double xi = ro2 * Math.cos(thx2) + centerX;
		//Double yi = ro2 * Math.sin(thx2) + centerY;
		
		//return rect.contains(new Point2D.Double(xi,yi));
		return localShape.contains(p);
	}

	@Override
	public void drawShape(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		init();
		g2.setColor(Color.BLUE);
		x = centerX - width / 2;
		y = centerY - hight / 2;
		
		if (isCenterRotate) {
			rotateX = centerX;
			rotateY = centerY;
		}
		
		localShape = AffineTransform.getRotateInstance(Math.toRadians(angle), rotateX, rotateY).createTransformedShape(rect);
        g2.fill(localShape);
		
		//旋转画笔，画当前图形
		//g2.rotate(Math.toRadians(angle),centerX,centerY);
		//使用fill方法，则创建的图形颜色为实心	
		//g2.fill(rect);
		//每次画完图形要把画笔旋转回来，否则后面画的图形都转了
		//g2.rotate(Math.toRadians(-angle),centerX,centerY);
		g2.setColor(Color.BLACK);
	}
}
