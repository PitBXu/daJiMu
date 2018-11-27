package com.daJiMu.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Shape6 extends ShapeRoot {
	
	//shape4 �ȱ�������
	/**
	 *   *
	 *  ***
	 * *****
	 */
	{
		xPoints = new int[]{width/2 + x,0 + x,width + x};
		yPoints = new int[]{0 + y,hight + y,hight + y};
		rect = new Polygon(xPoints,yPoints,3);
		localShape = AffineTransform.getRotateInstance(Math.toRadians(angle), centerX, centerY).createTransformedShape(rect);
	}
	
	/**
	 * 
	 * @param width ָ�����
	 * @param hight ָ���߶�
	 * @param centerX ����λ��x
	 * @param centerY ����λ��y
	 */
	public Shape6(int width,int hight,int centerX,int centerY) {
		super(width,hight,centerX,centerY);
		x = centerX - width / 2;
		y = centerY - 2 * hight / 3;
	}
	
	/**
	 * �ж������������Ƿ���ײ
	 * @param rec
	 * @return
	 */
	public boolean intersects(ShapeRoot rec) {
		Area a = this.toArea();
		a.intersect(new Area(rec));
		return !a.isEmpty();
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
		rect = new Rectangle2D.Double(x,y,width,hight);
		g2.setColor(Color.ORANGE);
		x = centerX - width / 2;
		y = centerY - 2 * hight / 3;
		/**
		 * @version 1.0
		 * g2.rotate(Math.toRadians(angle),centerX,centerY);
		 * g2.fillPolygon(xPoints, yPoints, 3);//ʹ��fill�������򴴽���ͼ����ɫΪʵ��
		 * g2.rotate(Math.toRadians(-angle),centerX,centerY);
		 */	
		xPoints = new int[]{width/2 + x,0 + x,width + x};
		yPoints = new int[]{0 + y,hight + y,hight + y};
		rect = new Polygon(xPoints,yPoints,3);
		localShape = AffineTransform.getRotateInstance(Math.toRadians(angle), centerX, centerY).createTransformedShape(rect);
		g2.fill(localShape);
		g2.setColor(Color.BLACK);
	}

	public boolean contains(Point2D p) {
		return localShape.contains(p);
	}
}
