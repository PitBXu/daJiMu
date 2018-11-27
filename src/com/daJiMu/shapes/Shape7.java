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
	
	//shape7 ��Բ
	/**
	 * ���� �ϳ��߼� -> �����߼� -> �����߼�
	 */
	
	/**
	 * 
	 * @param width ָ�����
	 * @param hight ָ���߶�
	 * @param centerX ����λ��x
	 * @param centerY ����λ��y
	 */
	public Shape7(int width,int hight,int centerX,int centerY) {
		super(width,hight,centerX,centerY);
		x = centerX - width / 2;
		y = centerY - hight / 4;
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
	 * ��Ŀ��ͼ��ת��Ϊ����
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
		//��һ����Բ
		g2.fill(localShape);
		g2.setColor(Color.BLACK);
	}
	
	@Override
	public boolean contains(Point2D p) {
		return localShape.contains(p);
	}
}
