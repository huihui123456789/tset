package com.xxx;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;
public class CreateQRCode{
	public static void main(String[] args)throws IOException{
	Qrcode qrcode = new Qrcode();
	String content = "逢考必过";
	String conten1 = "BEGIN:VCARD\r\n" +
	                 "EN:姓名:董淑慧\r\n"+
	                 "ORG:公司:河北科技师范学院\r\n"+
	                 "TITLE: 大美女\r\n" +
	                 "TEL;WORK;VOICE:123456789\r\n"+
	                 "TEL; HOME ;VOICE:123456789\r\n"+
	                 "TEL; CELL; VOICE:123456789\r\n"+
	                 "ADRIWORK:崇皇島金夢海湾- -卓元1504\r\n"+
	                 "ADR; HOME:海港区海云天G4-1 -15041\r\n"+
	                 "URL:http://www ,dijiaxueshe .com\r\n"+
	                 " EMAIL; HOME :532231254gg. com\r\n" +
	                 "PHOTO;VALUE-uri:http://www,dijiaxueshe . com/ imag!" +
	                 "END:VCARD";
	
	

	System.out.println("前version:"+qrcode.getQrcodeVersion());
	boolean[][] calQrcode = qrcode.calQrcode(conten1.getBytes("utf-8"));
	int version = qrcode.getQrcodeVersion();
	System.out.println("后version:"+version);
	int imgSize = 67 +(version-1)*12;
	BufferedImage bufferedImage = new BufferedImage(imgSize,imgSize,BufferedImage.TYPE_INT_RGB);
	Graphics2D gs = bufferedImage.createGraphics();
	gs.setBackground(Color.WHITE);
	gs.setColor(Color.BLACK);
	gs.clearRect(0, 0, imgSize, imgSize);
	int pixoff=2;
	String startRGB="255,0,0";
	String endRGB="0,0,255";
	int startR=0;
	int startG=0;
	int startB=0;
	if(null!=startRGB){
		String[]split=startRGB.split(",");
		startR=Integer.valueOf(split[0]);
		startG=Integer.valueOf(split[1]);
		startB=Integer.valueOf(split[2]);
	}
	int endR=0;
	int endG=0;
	int endB=0;
	if(null!=endRGB){
		String[]split=endRGB.split(",");
		endR=Integer.valueOf(split[0]);
		endG=Integer.valueOf(split[1]);
		endB=Integer.valueOf(split[2]);
	}
	for (int i=0;i<calQrcode.length;i++){
		for(int j=0;j<calQrcode[i].length;j++){
			if(calQrcode[i][j]){
				int r=startR+(endR-startR)*(i+1)/calQrcode.length;
				int g=startG+(endG-startG)*(i+1)/calQrcode.length;
				int b=startB+(endB-startB)*(i+1)/calQrcode.length;
				if(r>=255){
					r=255;
				}
				if(r>=255){
					r=255;
				}
				if(r>=255){
					r=255;
				}
				Color color=new Color(r,g,b);
				gs.setColor(color);
			    gs.fillRect(3*i+pixoff, 3*j+pixoff, 3, 3);
			}
		}
	}

	BufferedImage logo = ImageIO.read(new File("D:/logo.jpg"));
	int logoSize = imgSize/4;
	int o =(imgSize-logoSize)/2;
	gs.drawImage(logo, o, o, logoSize, logoSize, null);
	gs.dispose();
	bufferedImage.flush();
	ImageIO.write(bufferedImage, "png", new File("D:/QRcode.png"));
	System.out.println("输出完毕");
}
}