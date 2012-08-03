package com.playlife.utility.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.AttributedString;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TestCaptcha extends JPanel{


		  public TestCaptcha() {

		  }

		  public void paint(Graphics g) {
		    super.paintComponent(g);
		    Graphics2D g2d = (Graphics2D) g;

			BufferedImage bufferedImage = new BufferedImage(400, 300, BufferedImage.TYPE_INT_ARGB);
			Image background = null;
			try {
				background = ImageIO.read(new BufferedInputStream(new FileInputStream("C:\\workspace\\PlayTheLife\\img\\captcha_background.jpg")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Graphics2D graphic = bufferedImage.createGraphics();
		
			graphic.drawImage(background, 0, 0, null);

			graphic.setFont(new Font("LucidaSans", Font.BOLD, 200));
			AttributedString attributedString = new AttributedString("test");
			attributedString.addAttribute(TextAttribute.FOREGROUND, Color.red, 0, 1);
			graphic.drawString(attributedString.getIterator(), 80, 80);
		  }

		  public static void main(String[] args) {
		    JFrame frame = new JFrame("FontSizeAnimation");
		    frame.add(new TestCaptcha());
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setSize(400, 300);
		    frame.setLocationRelativeTo(null);
		    frame.setVisible(true);
		  }

		}