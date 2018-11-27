package com.daJiMu.shapes;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * ����ͼ����ĸ��࣬����ͼ�ε�ȫ��������
 * @author 100622161
 *
 */
public abstract class ShapeRoot implements Shape{
	/**
	 * ��ʼλ������(x,y)
	 */
	public int x;
	public int y;
	/**
	 * ����λ������(centerX,centerY)
	 */
	public int centerX;
	public int centerY;
	
	/**
	 * ��ת����
	 */
	public int rotateX;
	public int rotateY;
	
	public boolean isCenterRotate = true;
	/**
	 * ��ת�Ƕ�
	 */
	public int angle = 0;
	/**
	 * �ϵף���ȣ��߶�
	 */
	public int up;
	public int width;
	public int hight;
	/**
	 * ���εĿ�ʼ�Ƕ�startAngle�������Ƕ�arcAngle
	 */
	//public int arcAngle;
	//public int startAngle;
	/**
	 * ���Ʋ�����ͼ��(���κ���������)�ĵ㼯
	 */
	public int[] xPoints;
	public int[] yPoints;
	
	/**
	 * ����ͼ��
	 */
	protected Shape rect;
	/**
	 * ��ת�ȱ仯��ͼ��
	 */
	protected Shape localShape;
	
	/**
	 * ��BASE_AREA�ĽӴ�������ȡ�㼯
	 */
	public Rectangle2D touchArea;
	
	/**
	 * ��״̬�����ʽ������״̬���޸ļ򵥣�ʹ�����
	 */
	public int shapeState = DEFALUT_STATE;
	
	public static final int DEFALUT_STATE = 1001;
	public static final int DROP_STATE = 1002;
	public static final int CROSS_STATE = 1003;
	public static final int STABLE_STATE = 1004;
	
	/**
	 * ���췽������ʼ��ʱָ��
	 * @param width ���
	 * @param hight �߶�
	 */
	public ShapeRoot(int width,int hight){
		this.width = width;
		this.hight = hight;
	}
	/**
	 * ���췽������ʼ��ʱָ��
	 * @param width ���
	 * @param hight �߶�
	 * @param up �ϵ�
	 * @param centerX ��������x
	 * @param centerY ��������y
	 */
	public ShapeRoot(int width, int hight,int up,int centerX, int centerY) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.width = width;
		this.hight = hight;
		this.up = up;
	}
	/**
	 * ���췽������ʼ��ʱָ��
	 * @param width ���
	 * @param hight �߶�
	 * @param centerX ��������x
	 * @param centerY ��������y
	 */
	public ShapeRoot(int width, int hight,int centerX, int centerY) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.width = width;
		this.hight = hight;
	}
	
	public void setRotateCenter(Point2D p) {
		rotateX = (int) p.getX();
		rotateY = (int) p.getY();
	}
	/**
	 * �������һ����״
	 * @param centerX ָ���滭ʱ����������x
	 * @param centerY ָ���滭ʱ����������y
	 * @return
	 */
	public static ShapeRoot randomOne(int centerX,int centerY) {
		ShapeRoot  s = null;
		int num = (int)(Math.random()*10);
		switch(num) {
		case 0:s = new Shape1(60,120,centerX,centerY);		break;//������1
		case 1:s = new Shape2(60,104,centerX,centerY);		break;//��������
		case 2:s = new Shape3(80,60,57,centerX,centerY);	break;//����
		case 3:s = new Shape4(120,60,centerX,centerY);		break;//����ֱ��������
		case 4:s = new Shape5(180,30,centerX,centerY);		break;//������2
		case 5:s = new Shape6(80,69,centerX,centerY);		break;//�ȱ�������
		case 6:s = new Shape5(90,30,centerX,centerY);		break;//������3
		case 7:s = new Shape1(60,60,centerX,centerY);		break;//������
		case 8:s = new Shape7(60,60,centerX,centerY);		break;//��Բ
		case 9:s = new Shape8(120,60,centerX,centerY);		break;//����
		}
		return s;
	}
	/**
	 * �ж�ĳ�㣨���㣩�ڲ��ڸ�ͼ���ڲ������������η���
	 * �÷����ᱻÿ��������д�����������������ת
	 * @param p �����ĵ�
	 * @return
	 */
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
	public boolean contains(Point2D p) {
		if (x < p.getX() && (x+width) > p.getX() && y < p.getY() && (y+hight) > p.getY()) {
			return true;
		}
		return false;
	}
	/**
	 * ��ͼ�����������Լ��Ĳ�����ͼ
	 * @param g ����
	 */
	public abstract void drawShape(Graphics g);
	
	/**
	 * �ж����������Ƿ���ײ
	 * @param rec ������״
	 * @return
	 */
	public boolean intersects(Area area) {
		area.intersect(new Area(this));
		return !area.isEmpty();
	}
	
	@Override
	public String toString() {
		return ""+width+"-"+hight;
	}
	
	/**
	 * ����ǰͼ��ת��Ϊ����
	 * @return
	 */
	public abstract Area toArea();
	
	//���·���Ϊ�̳з�������������
	
	/**
	 * ����һ����ȫ��Χ Shape ������ Rectangle
	 */
	@Override
	public Rectangle getBounds() {
		return localShape.getBounds();
	}

	/**
	 * ����һ���߾��ȵġ��� getBounds ������׼ȷ�� Shape �߽��
	 */
	@Override
	public Rectangle2D getBounds2D() {
		return localShape.getBounds2D();
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
		return localShape.intersects(x, y, w, h);
	}

	/**
	 * ���� Shape �ڲ��Ƿ���ָ�� Rectangle2D(����) �ڲ��ཻ
	 */
	@Override
	public boolean intersects(Rectangle2D r) {
		return localShape.intersects(r);
	}

	/**
	 * ���� Shape �ڲ��Ƿ���ȫ����ָ����������
	 */
	@Override
	public boolean contains(double x, double y, double w, double h) {
		return localShape.contains(x, y, w, h);
	}

	/**
	 * ���� Shape �ڲ��Ƿ���ȫ����ָ���� Rectangle2D(����)
	 */
	@Override
	public boolean contains(Rectangle2D r) {
		return localShape.contains(r);
	}

	/**
	 * ����һ������ Shape �߽�������ṩ�� Shape ����������״�ķ��ʵĵ���������
	 */
	@Override
	public PathIterator getPathIterator(AffineTransform at) {
		return localShape.getPathIterator(at);
	}

	/**
	 * ����һ������ Shape �߽�������ṩ�� Shape ����������״��ƽ����ͼ���ʵĵ���������
	 */
	@Override
	public PathIterator getPathIterator(AffineTransform at, double flatness) {
		return localShape.getPathIterator(at, flatness);
	}
	
}
