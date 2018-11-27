package com.daJiMu.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;

public class Shape4 extends ShapeRoot {
	
	//shape4 ����ֱ��������
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
	 * @param width ָ�����
	 * @param hight ָ���߶�
	 * @param centerX ����λ��x
	 * @param centerY ����λ��y
	 */
	public Shape4(int width,int hight,int centerX,int centerY) {
		super(width,hight,centerX,centerY);
		x = centerX - width / 2;
		y = centerY - 3 * hight / 5;
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
	
	/**
	 * �ж�ĳ���Ƿ���ͼ����
	 * �������ĺ�Ŀ��㣬�����ж���ÿ�����Ƿ��ཻ
	 */
	public boolean contains(Point2D p) {
		return localShape.contains(p);
		/*//����תĿ���
		*//**
		 * ������Ϊԭ�㽨��������ϵ
		 * ��������ʽ��ʾ��p������
		 *//*
		Double ro1 = p.distance(centerX,centerY);
		Double thx1 = Math.atan((p.getY() - centerY)/(p.getX() - centerX));
		*//**
		 * ת�����p'��ļ�����
		 *//*
		Double ro2 = ro1;
		Double thx2 = thx1 - Math.toRadians(angle);
		*//**
		 * ���������µĵ�ת����ֱ�����꣬���ƶ�ԭ��
		 *//*
		Double xi = ro2 * Math.cos(thx2) + centerX;
		Double yi = ro2 * Math.sin(thx2) + centerY;
		
		//����Ŀ���һ���������ĵ���
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
}
