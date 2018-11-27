package com.daJiMu.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Shape3 extends ShapeRoot{
	
	//shape3 ����
	/**
	 *  ***
	 * *****
	 */
	//Path2D p = new Path2D.Double();
	{	
		xPoints = new int[]{(width - up)/2 + x,0 + x,width + x,(width + up)/2 + x};
		yPoints = new int[]{0 + y,hight + y,hight + y,0 + y};
		rect = new Polygon(xPoints,yPoints,4);
		localShape = AffineTransform.getRotateInstance(Math.toRadians(angle), centerX, centerY).createTransformedShape(rect);
	}
	
	/**
	 * 
	 * @param width ָ�����
	 * @param hight ָ���߶�
	 * @param centerX ����λ��x
	 * @param centerY ����λ��y
	 */
	public Shape3(int width,int hight,int up,int centerX,int centerY) {
		super(width,hight,up,centerX,centerY);
		x = centerX - width / 2;
		y = centerY - 3 * hight / 5;
	}
		
	/**
	 * �ж����������Ƿ���ײ
	 * @param area ������״
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
	
	public boolean contains(Point2D p) {
		return localShape.contains(p);
	}

	@Override
	public void drawShape(Graphics g) {
		//Rectangle2D rect = new Rectangle2D.Double(x,y,width,hight);
		Graphics2D g2 = (Graphics2D) g;
		rect = new Rectangle2D.Double(x,y,width,hight);
		g2.setColor(Color.DARK_GRAY);
		x = centerX - width / 2;
		y = centerY - 3 * hight / 5;
		/**
		 * @version 1.0
		 * g2.rotate(Math.toRadians(angle),centerX,centerY);
		 * g2.fillPolygon(xPoints, yPoints, 4);//ʹ��fill�������򴴽���ͼ����ɫΪʵ��
		 * g2.rotate(Math.toRadians(-angle),centerX,centerY);
		 */
		xPoints = new int[]{(width - up)/2 + x,0 + x,width + x,(width + up)/2 + x};
		yPoints = new int[]{0 + y,hight + y,hight + y,0 + y};
		/**
		 * @version 1.0
		 * p.moveTo(xPoints[0], yPoints[0]);
		 * for(int i=0;i<xPoints.length;i++) {
		 *	p.lineTo(xPoints[i], yPoints[i]);
		 * }
		 * p.closePath();
		 * g2.fill(p);
		 */
		rect = new Polygon(xPoints,yPoints,4);
		localShape = AffineTransform.getRotateInstance(Math.toRadians(angle), centerX, centerY).createTransformedShape(rect);
		g2.fill(localShape);
		g2.setColor(Color.BLACK);
	}
}
