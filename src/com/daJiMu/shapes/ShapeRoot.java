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
 * 所有图形类的父类，包含图形的全部参数等
 * @author 100622161
 *
 */
public abstract class ShapeRoot implements Shape{
	/**
	 * 起始位置坐标(x,y)
	 */
	public int x;
	public int y;
	/**
	 * 重心位置坐标(centerX,centerY)
	 */
	public int centerX;
	public int centerY;
	
	/**
	 * 旋转中心
	 */
	public int rotateX;
	public int rotateY;
	
	public boolean isCenterRotate = true;
	/**
	 * 旋转角度
	 */
	public int angle = 0;
	/**
	 * 上底，宽度，高度
	 */
	public int up;
	public int width;
	public int hight;
	/**
	 * 扇形的开始角度startAngle，持续角度arcAngle
	 */
	//public int arcAngle;
	//public int startAngle;
	/**
	 * 绘制不规则图形(梯形和正六边形)的点集
	 */
	public int[] xPoints;
	public int[] yPoints;
	
	/**
	 * 基础图形
	 */
	protected Shape rect;
	/**
	 * 旋转等变化后图形
	 */
	protected Shape localShape;
	
	/**
	 * 与BASE_AREA的接触区域，提取点集
	 */
	public Rectangle2D touchArea;
	
	/**
	 * 用状态码的形式来保存状态，修改简单，使用灵活
	 */
	public int shapeState = DEFALUT_STATE;
	
	public static final int DEFALUT_STATE = 1001;
	public static final int DROP_STATE = 1002;
	public static final int CROSS_STATE = 1003;
	public static final int STABLE_STATE = 1004;
	
	/**
	 * 构造方法，初始化时指定
	 * @param width 宽度
	 * @param hight 高度
	 */
	public ShapeRoot(int width,int hight){
		this.width = width;
		this.hight = hight;
	}
	/**
	 * 构造方法，初始化时指定
	 * @param width 宽度
	 * @param hight 高度
	 * @param up 上底
	 * @param centerX 重心坐标x
	 * @param centerY 重心坐标y
	 */
	public ShapeRoot(int width, int hight,int up,int centerX, int centerY) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.width = width;
		this.hight = hight;
		this.up = up;
	}
	/**
	 * 构造方法，初始化时指定
	 * @param width 宽度
	 * @param hight 高度
	 * @param centerX 重心坐标x
	 * @param centerY 重心坐标y
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
	 * 随机生成一个形状
	 * @param centerX 指定绘画时的重心坐标x
	 * @param centerY 指定绘画时的重心坐标y
	 * @return
	 */
	public static ShapeRoot randomOne(int centerX,int centerY) {
		ShapeRoot  s = null;
		int num = (int)(Math.random()*10);
		switch(num) {
		case 0:s = new Shape1(60,120,centerX,centerY);		break;//长方形1
		case 1:s = new Shape2(60,104,centerX,centerY);		break;//正六边形
		case 2:s = new Shape3(80,60,57,centerX,centerY);	break;//梯型
		case 3:s = new Shape4(120,60,centerX,centerY);		break;//等腰直角三角形
		case 4:s = new Shape5(180,30,centerX,centerY);		break;//长方形2
		case 5:s = new Shape6(80,69,centerX,centerY);		break;//等边三角形
		case 6:s = new Shape5(90,30,centerX,centerY);		break;//长方形3
		case 7:s = new Shape1(60,60,centerX,centerY);		break;//正方形
		case 8:s = new Shape7(60,60,centerX,centerY);		break;//半圆
		case 9:s = new Shape8(120,60,centerX,centerY);		break;//拱桥
		}
		return s;
	}
	/**
	 * 判断某点（鼠标点）在不在该图形内部，用于鼠标变形方法
	 * 该方法会被每个子类重写，用于做特征点的旋转
	 * @param p 待检测的点
	 * @return
	 */
	/*//先旋转目标点
	*//**
	 * 以重心为原点建立极坐标系
	 * 极坐标形式表示的p点坐标
	 *//*
	Double ro1 = p.distance(centerX,centerY);
	Double thx1 = Math.atan((p.getY() - centerY)/(p.getX() - centerX));
	*//**
	 * 转换后的p'点的极坐标
	 *//*
	Double ro2 = ro1;
	Double thx2 = thx1 - Math.toRadians(angle);
	*//**
	 * 将极坐标下的点转换到直角坐标，并移动原点
	 *//*
	Double xi = ro2 * Math.cos(thx2) + centerX;
	Double yi = ro2 * Math.sin(thx2) + centerY;
	
	//创建目标点一条连接中心的线
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
	 * 画图方法，按照自己的参数画图
	 * @param g 画笔
	 */
	public abstract void drawShape(Graphics g);
	
	/**
	 * 判断与其他域是否相撞
	 * @param rec 参数形状
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
	 * 将当前图形转化为区域
	 * @return
	 */
	public abstract Area toArea();
	
	//以下方法为继承方法，基本无用
	
	/**
	 * 返回一个完全包围 Shape 的整型 Rectangle
	 */
	@Override
	public Rectangle getBounds() {
		return localShape.getBounds();
	}

	/**
	 * 返回一个高精度的、比 getBounds 方法更准确的 Shape 边界框
	 */
	@Override
	public Rectangle2D getBounds2D() {
		return localShape.getBounds2D();
	}

	/**
	 * 测试指定坐标是否在 Shape 的边界内
	 */
	@Override
	public boolean contains(double x, double y) {
		return this.contains(new Point2D.Double(x,y));
	}

	/**
	 * 测试 Shape 内部是否与指定矩形区域的内部相交
	 */
	@Override
	public boolean intersects(double x, double y, double w, double h) {
		return localShape.intersects(x, y, w, h);
	}

	/**
	 * 测试 Shape 内部是否与指定 Rectangle2D(矩形) 内部相交
	 */
	@Override
	public boolean intersects(Rectangle2D r) {
		return localShape.intersects(r);
	}

	/**
	 * 测试 Shape 内部是否完全包含指定矩形区域
	 */
	@Override
	public boolean contains(double x, double y, double w, double h) {
		return localShape.contains(x, y, w, h);
	}

	/**
	 * 测试 Shape 内部是否完全包含指定的 Rectangle2D(矩形)
	 */
	@Override
	public boolean contains(Rectangle2D r) {
		return localShape.contains(r);
	}

	/**
	 * 返回一个沿着 Shape 边界迭代并提供对 Shape 轮廓几何形状的访问的迭代器对象
	 */
	@Override
	public PathIterator getPathIterator(AffineTransform at) {
		return localShape.getPathIterator(at);
	}

	/**
	 * 返回一个沿着 Shape 边界迭代并提供对 Shape 轮廓几何形状的平面视图访问的迭代器对象
	 */
	@Override
	public PathIterator getPathIterator(AffineTransform at, double flatness) {
		return localShape.getPathIterator(at, flatness);
	}
	
}
