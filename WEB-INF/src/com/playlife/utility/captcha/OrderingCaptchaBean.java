package com.playlife.utility.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.AttributedString;

import javax.imageio.ImageIO;

import com.playlife.utility.Random;

public class OrderingCaptchaBean {
	public String answer; 
	public String order;
	public String path ;
	
	public OrderingCaptchaBean(String order, String path){
		this.order = order;
		randomize();
		this.path = path;
	}
	
	private void randomize(){
		this.answer = this.order;
		int trails = Math.abs(Random.randomInt()) % 1000;
		for (int i = 0; i < trails; i++){
			int pos_first = Math.abs(Random.randomInt()) % answer.length();
			int pos_end = Math.abs(Random.randomInt()) % answer.length();
			if (pos_first == pos_end)
				continue;
			
			this.swap(pos_first, pos_end);
		}
	}
	
	private void swap(int pos_first, int pos_end){
		char tmp = this.answer.charAt(pos_first);
		StringBuilder builder = new StringBuilder(this.answer);
		builder.setCharAt(pos_first, this.answer.charAt(pos_end));
		builder.setCharAt(pos_end, tmp);
		this.answer = builder.toString();
	}
	
	public ByteArrayOutputStream getOutput(int pos){
		ByteArrayOutputStream byte_arr_output = new ByteArrayOutputStream();
		BufferedImage bufferedImage = new BufferedImage(40, 40, BufferedImage.TYPE_BYTE_GRAY);
		Image background;

		try {
			background = ImageIO.read(new BufferedInputStream(new FileInputStream(path+"\\img\\captcha_background.jpg")));
		
			Graphics2D graphic = bufferedImage.createGraphics();
		
			graphic.drawImage(background, 0, 0, null);
			
			graphic.setFont(new Font("dialog", Font.BOLD, 20));
			AttributedString attributedString = new AttributedString(answer.charAt(pos)+"");
			attributedString.addAttribute(TextAttribute.FOREGROUND, Color.WHITE, 0, 1);
			graphic.drawString(attributedString.getIterator(), 16, 22);
			
			ImageIO.write(bufferedImage, "jpeg", byte_arr_output);
		} catch (FileNotFoundException ex) {

		} catch (IOException ex) {

		}
		return byte_arr_output;
	}
	
	public boolean check(String sessionID, String reorder){	
		int i = 0;
		for (char ch_reorder : reorder.toCharArray()){
			if (order.charAt(i) != answer.charAt(Integer.parseInt(ch_reorder+"")))
				return false;
			i++;
		}
		
		return true;
	}
}
