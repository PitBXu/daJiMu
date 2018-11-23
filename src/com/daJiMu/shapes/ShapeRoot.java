package com.daJiMu.shapes;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Area;
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
	 * ��ת�Ƕ�
	 */
	public int angle = 0;
	/**
	 * �ϵף����ȣ��߶�
	 */
	public int up;
	public int width;
	public int hight;
	/**
	 * ���εĿ�ʼ�Ƕ�startAngle�������Ƕ�arcAngle
	 */
	public int arcAngle;
	public int startAngle;
	/**
	 * ���Ʋ�����ͼ��(���κ���������)�ĵ㼯
	 */
	public int[] xPoints;
	public int[] yPoints;
	
	protected Rectangle2D rect;
	
	/**
	 * �������ø�ͼ���Ƿ�Ӧ������
	 */
	public boolean isDrop = false;
	
	/**
	 * �������ø�ͼ���Ƿ������ж��ཻ
	 */
	public boolean isTrack = false;
	/**
	 * ���췽������ʼ��ʱָ��
	 * @param width ����
	 * @param hight �߶�
	 */
	public ShapeRoot(int width,int hight){
		this.width = width;
		this.hight = hight;
	}
	/**
	 * ���췽������ʼ��ʱָ��
	 * @param width ����
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
	 * @param width ����
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
		case 0:s = new Shape1(60,120,centerX,centerY);break;//������1
		case 1:s = new Shape2(120,104,centerX,centerY);break;//��������
		case 2:s = new Shape3(80,120,57,centerX,centerY);break;//����
		case 3:s = new Shape4(120,60,centerX,centerY);break;//����ֱ��������
		case 4:s = new Shape5(180,30,centerX,centerY);break;//������2
		case 5:s = new Shape6(80,69,centerX,centerY);break;//�ȱ�������
		case 6:s = new Shape5(90,30,centerX,centerY);break;//������3
		case 7:s = new Shape1(60,60,centerX,centerY);break;//������
		case 8:s = new Shape7(60,60,centerX,centerY);break;//��Բ
		case 9:s = new Shape8(120,60,centerX,centerY);break;//����
		}
		return s;
	}
	/**
	 * �ж�ĳ�㣨���㣩�ڲ��ڸ�ͼ���ڲ������������η���
	 * @param p �����ĵ�
	 * @return
	 */
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
	 * �ж���������״�Ƿ���ײ
	 * @param rec ������״
	 * @return
	 */
	public boolean intersects(ShapeRoot rec) {
		return false;
	}
	
	@Override
	public String toString() {
		return ""+width+"-"+hight;
	}
	
}