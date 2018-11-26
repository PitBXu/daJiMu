package com.daJiMu.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Shape2 extends ShapeRoot {
	
	//shape2 ��������
	/**
	 *  ***
	 * *****
	 *  ***
	 */
	{
		xPoints = new int[]{width/4 + x,0 + x,width/4 + x,3*width/4 + x,width + x,3*width/4 + x};
		yPoints = new int[]{0 + y,hight/2 + y,hight + y,hight + y,hight/2 + y,0 + y};
	}
	
	/**
	 * 
	 * @param width ָ�����
	 * @param hight ָ���߶�
	 * @param centerX ����λ��x
	 * @param centerY ����λ��y
	 */
	public Shape2(int width,int hight,int centerX,int centerY) {
		super(width,hight,centerX,centerY);
		x = centerX - width / 2;
		y = centerY - hight / 2;
		rect = new Ellipse2D.Double(x,y,(width+hight)/2,(width+hight)/2);
		localShape = AffineTransform.getRotateInstance(Math.toRadians(angle), centerX, centerY).createTransformedShape(rect);
	}
	
	/**
	 * �ж����������Ƿ���ײ
	 * @param rec ������״
	 * @return
	 */
	public boolean intersects(Area area) {
		Area as = this.toArea();
		as.intersect(area);
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
	 * �ж�ĳ���Ƿ�������������
	 * �������ĺ�Ŀ��㣬�����ж���ÿ�����Ƿ��ཻ
	 */
	public boolean contains(Point2D p) {
		//����תĿ���
		/**
		 * ������Ϊԭ�㽨��������ϵ
		 * ��������ʽ��ʾ��p������
		 */
		Double ro1 = p.distance(centerX,centerY);
		Double thx1 = Math.atan((p.getY() - centerY)/(p.getX() - centerX));
		/**
		 * ת�����p'��ļ�����
		 */
		Double ro2 = ro1;
		Double thx2 = thx1 - Math.toRadians(angle);
		/**
		 * ���������µĵ�ת����ֱ�����꣬���ƶ�ԭ��
		 */
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
		return true;
	}


	@Override
	public void drawShape(Graphics g) {
		//Rectangle2D rect = new Rectangle2D.Double(x,y,width,hight);
		Graphics2D g2 = (Graphics2D) g;
		rect = new Ellipse2D.Double(x,y,(width+hight)/2,(width+hight)/2);
		g2.setColor(Color.CYAN);
		x = centerX - width / 2;
		y = centerY - hight / 2;
		g2.rotate(Math.toRadians(angle),centerX,centerY);
		xPoints = new int[]{width/4 + x,0 + x,width/4 + x,3*width/4 + x,width + x,3*width/4 + x};
		yPoints = new int[]{0 + y,hight/2 + y,hight + y,hight + y,hight/2 + y,0 + y};
		
		localShape = AffineTransform.getRotateInstance(Math.toRadians(angle), centerX, centerY).createTransformedShape(rect);
		//g2.fill(localShape);
		
		g2.fillPolygon(xPoints, yPoints, 6);//ʹ��fill�������򴴽���ͼ����ɫΪʵ��
		g2.setColor(Color.BLACK);	
		g2.rotate(Math.toRadians(-angle),centerX,centerY);
	}
	
	/**
	 * ����һ����ȫ��Χ Shape ������ Rectangle
	 */
	@Override
	public Rectangle getBounds() {
		return rect.getBounds();
	}

	/**
	 * ����һ���߾��ȵġ��� getBounds ������׼ȷ�� Shape �߽��
	 */
	@Override
	public Rectangle2D getBounds2D() {
		return rect.getBounds2D();
	}

	/**
	 * ����ָ�������Ƿ��� Shape �ı߽���
	 */
	@Override
	public boolean contains(double x, double y) {
		return this.contains(new Point2D.Double(x,y));
	}

	/**
	 * ���� Shape �ڲ��Ƿ���ָ������������ڲ��ཻ
	 */
	@Override
	public boolean intersects(double x, double y, double w, double h) {
		return rect.intersects(x, y, w, h);
	}

	/**
	 * ���� Shape �ڲ��Ƿ���ָ�� Rectangle2D(����) �ڲ��ཻ
	 */
	@Override
	public boolean intersects(Rectangle2D r) {
		return rect.intersects(r);
	}

	/**
	 * ���� Shape �ڲ��Ƿ���ȫ����ָ����������
	 */
	@Override
	public boolean contains(double x, double y, double w, double h) {
		return rect.contains(x, y, w, h);
	}

	/**
	 * ���� Shape �ڲ��Ƿ���ȫ����ָ���� Rectangle2D(����)
	 */
	@Override
	public boolean contains(Rectangle2D r) {
		return rect.contains(r);
	}

	/**
	 * ����һ������ Shape �߽�������ṩ�� Shape ����������״�ķ��ʵĵ���������
	 */
	@Override
	public PathIterator getPathIterator(AffineTransform at) {
		return rect.getPathIterator(at);
	}

	/**
	 * ����һ������ Shape �߽�������ṩ�� Shape ����������״��ƽ����ͼ���ʵĵ���������
	 */
	@Override
	public PathIterator getPathIterator(AffineTransform at, double flatness) {
		return rect.getPathIterator(at, flatness);
	}
}
