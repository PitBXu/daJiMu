package com.daJiMu.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public class Shape8 extends ShapeRoot {
	
	RectangularShape rect1;
	RectangularShape rect2;
	
	Shape lsp1;
	Shape lsp2;
	
	Area tar;
	
	/**
	 * 
	 * @param width 指定宽度
	 * @param hight 指定高度
	 * @param centerX 中心位置x
	 * @param centerY 中心位置y
	 */
	public Shape8(int width,int hight,int centerX,int centerY) {
		super(width,hight,centerX,centerY);
		x = centerX - width / 2;
		y = centerY - hight / 3;
		//矩形
		rect1 = new Rectangle2D.Double(x,y,width,hight);
		//圆
		rect2 = new Ellipse2D.Double(x+30,y+30,hight,hight);
		//包装
		lsp1 = AffineTransform.getRotateInstance(Math.toRadians(angle), centerX, centerY).createTransformedShape(rect1);
		lsp2 = AffineTransform.getRotateInstance(Math.toRadians(angle), centerX, centerY).createTransformedShape(rect2);
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
	 * 判断某个点是否在图形内
	 */
	public boolean contains(Point2D p) {
		return lsp1.contains(p) && !lsp2.contains(p);
	}
	
	/**
	 * 将目标图形转化为区域
	 */
	@Override
	public Area toArea() {
		tar = new Area(lsp1);
		tar.subtract(new Area(lsp2));
		return tar;
	}

	@Override
	public void drawShape(Graphics g) {
		/**
		 * @version 1.0
		 * Area a = new Area(rect1);
		 * a.subtract(new Area(rect2));
		 * g2.rotate(Math.toRadians(angle),centerX,centerY);
		 * g2.rotate(Math.toRadians(-angle),centerX,centerY);
		 * g2.fill(a);
		 */
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.GREEN);
		x = centerX - width / 2;
		y = centerY - hight / 3;
		
		rect1 = new Rectangle2D.Double(x,y,width,hight);
		rect2 = new Ellipse2D.Double(x+30,y+30,hight,hight);
		lsp1 = AffineTransform.getRotateInstance(Math.toRadians(angle), centerX, centerY).createTransformedShape(rect1);
		lsp2 = AffineTransform.getRotateInstance(Math.toRadians(angle), centerX, centerY).createTransformedShape(rect2);
		
		tar = new Area(lsp1);
		tar.subtract(new Area(lsp2));
		g2.fill(tar);
		
		g2.setColor(Color.BLACK);
	}
}
