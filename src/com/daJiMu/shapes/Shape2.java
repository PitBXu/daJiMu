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
	
	//shape2 ��������
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
	 * @param width ָ�����
	 * @param hight ָ���߶�
	 * @param centerX ����λ��x
	 * @param centerY ����λ��y
	 */
	public Shape2(int width,int hight,int centerX,int centerY) {
		super(width,hight,centerX,centerY);
		x = centerX - width / 2;
		y = centerY - hight / 2;
		//rect = new Ellipse2D.Double(x,y,(width+hight)/2,(width+hight)/2);
		/**
		 * �°汾ʹ��AffineTransform��������������ת��add�õ��������εķ�ʽ
		 * ���ַ�ʽ��ʵ��׼ȷ�ı߽��жϣ�Ҳ������תĿ���������λ���ж�
		 */
		rect = new Rectangle2D.Double(x,y,width,hight);
		lsp1 = AffineTransform.getRotateInstance(Math.toRadians(angle), centerX, centerY).createTransformedShape(rect);
		lsp2 = AffineTransform.getRotateInstance(Math.toRadians(angle-60), centerX, centerY).createTransformedShape(rect);
		lsp3 = AffineTransform.getRotateInstance(Math.toRadians(angle-120), centerX, centerY).createTransformedShape(rect);
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
		tar = new Area(lsp1);
		tar.add(new Area(lsp2));
		tar.add(new Area(lsp3));
		return tar;
	}
	
	/**
	 * �ж�ĳ���Ƿ�������������
	 * �������ĺ�Ŀ��㣬�����ж���ÿ�����Ƿ��ཻ
	 */
	public boolean contains(Point2D p) {
		//�°汾ʹ��AffineTransform��������������ת��add�õ��������εķ�ʽ�������жϵ�
		return lsp1.contains(p) || lsp2.contains(p) || lsp3.contains(p);
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
		 * g2.fillPolygon(xPoints, yPoints, 6);//ʹ��fill�������򴴽���ͼ����ɫΪʵ��
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
