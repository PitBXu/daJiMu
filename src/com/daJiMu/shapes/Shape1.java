package com.daJiMu.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Shape1 extends ShapeRoot {
	//shape1�����泤����
	/**
	 * 
	 * @param width ָ�����
	 * @param hight ָ���߶�
	 * @param centerX ����λ��x
	 * @param centerY ����λ��y
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
	 * �ж�ĳ�����Ƿ��ھ�����
	 */
	@Override
	public boolean contains(Point2D p) {
		//����תĿ���
		/**
		 * ������Ϊԭ�㽨��������ϵ
		 * ��������ʽ��ʾ��p������
		 */
		//Double ro1 = p.distance(centerX,centerY);
		//Double thx1 = Math.atan((p.getY() - centerY)/(p.getX() - centerX));
		/**
		 * ת�����p'��ļ�����
		 */
		//Double ro2 = ro1;
		//Double thx2 = thx1 - Math.toRadians(angle);
		/**
		 * ���������µĵ�ת����ֱ�����꣬���ƶ�ԭ��
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
		
		//��ת���ʣ�����ǰͼ��
		//g2.rotate(Math.toRadians(angle),centerX,centerY);
		//ʹ��fill�������򴴽���ͼ����ɫΪʵ��	
		//g2.fill(rect);
		//ÿ�λ���ͼ��Ҫ�ѻ�����ת������������滭��ͼ�ζ�ת��
		//g2.rotate(Math.toRadians(-angle),centerX,centerY);
		g2.setColor(Color.BLACK);
	}
}
