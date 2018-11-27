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
	 * @param width ָ�����
	 * @param hight ָ���߶�
	 * @param centerX ����λ��x
	 * @param centerY ����λ��y
	 */
	public Shape8(int width,int hight,int centerX,int centerY) {
		super(width,hight,centerX,centerY);
		x = centerX - width / 2;
		y = centerY - hight / 3;
		//����
		rect1 = new Rectangle2D.Double(x,y,width,hight);
		//Բ
		rect2 = new Ellipse2D.Double(x+30,y+30,hight,hight);
		//��װ
		lsp1 = AffineTransform.getRotateInstance(Math.toRadians(angle), centerX, centerY).createTransformedShape(rect1);
		lsp2 = AffineTransform.getRotateInstance(Math.toRadians(angle), centerX, centerY).createTransformedShape(rect2);
	}
	
	/**
	 * �ж����������Ƿ���ײ
	 * @param rec ������״
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
	 * �ж�ĳ�����Ƿ���ͼ����
	 */
	public boolean contains(Point2D p) {
		return lsp1.contains(p) && !lsp2.contains(p);
	}
	
	/**
	 * ��Ŀ��ͼ��ת��Ϊ����
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
