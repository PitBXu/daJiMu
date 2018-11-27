package com.daJiMu.Test;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

import com.daJiMu.shapes.ShapeRoot;

public class Test {
	
	public static Double wi;
	public static Double he;

	static DrawComponent drp = new DrawComponent();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			JFrame frame = new JFrame();
			frame.add(drp);
			frame.setTitle("��ͼ");
			
			//�õ���Ļ�Ĵ�С
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize = kit.getScreenSize();
			
			wi = screenSize.getWidth();
			he = screenSize.getHeight();
			
			// 3:���ô��ڵĳߴ�
			frame.setSize(wi.intValue(),he.intValue());

			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//����Ϊ�ɼ�
			frame.setVisible(true);
			// 4:���ô��ھ���
			frame.setLocationRelativeTo(null);
			
			// �������̼����¼�
			keyListener(frame);
			
			
			
		});
	}
	public static void keyListener(JFrame frame) {
		KeyListener l = new KeyAdapter() {
			/*
			 * KeyPressed() �Ǽ��̰�ť ����ȥ�����õķ���
			 */
			public void keyPressed(KeyEvent e) {
				// ��ȡһ�¼��ӵĴ���
				int code = e.getKeyCode();
				switch (code) {
				case KeyEvent.VK_Q:
					if (DrawComponent.currentShape.shapeState != ShapeRoot.STABLE_STATE) {
						turnRight(DrawComponent.currentShape.angle);
					}
					break;
				case KeyEvent.VK_E:
					if (DrawComponent.currentShape.shapeState != ShapeRoot.STABLE_STATE) {
						turnLeft(DrawComponent.currentShape.angle);
					}
					break;
				}
			}
		};
		// �����Ӽ����¼�����
		frame.addKeyListener(l);
		// ���������óɽ���
		frame.requestFocus();
	}
	public static void turnRight(int degress) {
		degress --;
		degress = (degress + 360) % 360;
		drp.angleChange(degress);
	}
	public static void turnLeft(int degress) {
		degress ++;
		degress = (degress + 360) % 360;
		drp.angleChange(degress);
	}
}
