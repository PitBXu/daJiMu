package com.daJiMu.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Shape2 extends ShapeRoot {
	
	//shape2 正六边型
	Shape lsp1;
	Shape lsp2;
	Shape lsp3;
	Area tar;
	/**
	 *  ***
	 * *****
	 *  ***
	 */
	{
		//xPoints = new int[]{width/4 + x,0 + x,width/4 + x,3*width/4 + x,width + x,3*width/4 + x};
		//yPoints = new int[]{0 + y,hight/2 + y,hight + y,hight + y,hight/2 + y,0 + y};
	}
	
	/**
	 * 
	 * @param width 指定宽度
	 * @param hight 指定高度
	 * @param centerX 中心位置x
	 * @param centerY 中心位置y
	 */
	public Shape2(int width,int hight,int centerX,int centerY) {
		super(width,hight,centerX,centerY);
		x = centerX - width / 2;
		y = centerY - hight / 2;
		//rect = new Ellipse2D.Double(x,y,(width+hight)/2,(width+hight)/2);
		/**
		 * 新版本使用AffineTransform进行三个矩形旋转后add得到正六边形的方式
		 * 这种方式能实现准确的边界判断，也不用旋转目标点进行鼠标位置判断
		 */
		rect = new Rectangle2D.Double(x,y,width,hight);
		lsp1 = AffineTransform.getRotateInstance(Math.toRadians(angle), centerX, centerY).createTransformedShape(rect);
		lsp2 = AffineTransform.getRotateInstance(Math.toRadians(angle-60), centerX, centerY).createTransformedShape(rect);
		lsp3 = AffineTransform.getRotateInstance(Math.toRadians(angle-120), centerX, centerY).createTransformedShape(rect);
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
		tar = new Area(lsp1);
		tar.add(new Area(lsp2));
		tar.add(new Area(lsp3));
		return tar;
	}
	
	/**
	 * 判断某点是否在正六边形内
	 * 连接中心和目标点，依次判断与每条边是否相交
	 */
	public boolean contains(Point2D p) {
		//新版本使用AffineTransform进行三个矩形旋转后add得到正六边形的方式，简化了判断点
		return lsp1.contains(p) || lsp2.contains(p) || lsp3.contains(p);
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
		//Rectangle2D rect = new Rectangle2D.Double(x,y,width,hight);
		Graphics2D g2 = (Graphics2D) g;
		rect = new Rectangle2D.Double(x,y,width,hight);
		g2.setColor(Color.CYAN);
		x = centerX - width / 2;
		y = centerY - hight / 2;
		/**
		 * @version 1.0
		 * g2.rotate(Math.toRadians(angle),centerX,centerY);
		 * xPoints = new int[]{width/4 + x,0 + x,width/4 + x,3*width/4 + x,width + x,3*width/4 + x};
		 * yPoints = new int[]{0 + y,hight/2 + y,hight + y,hight + y,hight/2 + y,0 + y};
		 * g2.fillPolygon(xPoints, yPoints, 6);//使用fill方法，则创建的图形颜色为实心
		 * g2.rotate(Math.toRadians(-angle),centerX,centerY);
		 */
		lsp1 = AffineTransform.getRotateInstance(Math.toRadians(angle), centerX, centerY).createTransformedShape(rect);
		lsp2 = AffineTransform.getRotateInstance(Math.toRadians(angle-60), centerX, centerY).createTransformedShape(rect);
		lsp3 = AffineTransform.getRotateInstance(Math.toRadians(angle+60), centerX, centerY).createTransformedShape(rect);
		tar = new Area(lsp1);
		tar.add(new Area(lsp2));
		tar.add(new Area(lsp3));
		g2.fill(tar);
		g2.setColor(Color.BLACK);
	}
}
