package com.daJiMu.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;

public class Shape4 extends ShapeRoot {
	
	//shape4 等腰直角三角形
	/**
	 *      * 
	 *    * * * 
	 * * * * * * *
	 */
	{		
		xPoints = new int[]{width/2 + x,0 + x,width + x};
		yPoints = new int[]{0 + y,hight + y,hight + y};
		rect = new Polygon(xPoints,yPoints,3);
		localShape = AffineTransform.getRotateInstance(Math.toRadians(angle), centerX, centerY).createTransformedShape(rect);
	}
	
	/**
	 * 
	 * @param width 指定宽度
	 * @param hight 指定高度
	 * @param centerX 中心位置x
	 * @param centerY 中心位置y
	 */
	public Shape4(int width,int hight,int centerX,int centerY) {
		super(width,hight,centerX,centerY);
		x = centerX - width / 2;
		y = centerY - 3 * hight / 5;
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
	 * 判断某点是否在图形内
	 * 连接中心和目标点，依次判断与每条边是否相交
	 */
	public boolean contains(Point2D p) {
		return localShape.contains(p);
		/*//先旋转目标点
		*//**
		 * 以重心为原点建立极坐标系
		 * 极坐标形式表示的p点坐标
		 *//*
		Double ro1 = p.distance(centerX,centerY);
		Double thx1 = Math.atan((p.getY() - centerY)/(p.getX() - centerX));
		*//**
		 * 转换后的p'点的极坐标
		 *//*
		Double ro2 = ro1;
		Double thx2 = thx1 - Math.toRadians(angle);
		*//**
		 * 将极坐标下的点转换到直角坐标，并移动原点
		 *//*
		Double xi = ro2 * Math.cos(thx2) + centerX;
		Double yi = ro2 * Math.sin(thx2) + centerY;
		
		//创建目标点一条连接中心的线
		Line2D line = new Line2D.Double(xi,yi,centerX,centerY);
		for(int i=0;i<xPoints.length;i++) {
			int tari = i+1;
			if (tari == xPoints.length) {
				tari = 0;
			}
			if (line.intersectsLine(xPoints[i],yPoints[i],xPoints[tari],yPoints[tari])) {
				return false;
			}
		}
		return true;*/
	}

	@Override
	public void drawShape(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.LIGHT_GRAY);
		x = centerX - width / 2;
		y = centerY - 3 * hight / 5;
		/**
		 * @version 1.0
		 * g2.rotate(Math.toRadians(angle),centerX,centerY);
		 * g2.fillPolygon(xPoints, yPoints, 3);//使用fill方法，则创建的图形颜色为实心
		 * g2.rotate(Math.toRadians(-angle),centerX,centerY);
		 */
		xPoints = new int[]{width/2 + x,0 + x,width + x};
		yPoints = new int[]{0 + y,hight + y,hight + y};
		rect = new Polygon(xPoints,yPoints,3);
		localShape = AffineTransform.getRotateInstance(Math.toRadians(angle), centerX, centerY).createTransformedShape(rect);
		g2.fill(localShape);
		g2.setColor(Color.BLACK);	
	}
}
