package main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Font;
import java.awt.FontFormatException;

public class Ui {
	GamePanel gp; 
	Graphics2D g2;
	Font maruMonica, purisaB; 
	public boolean messageOn=false; 
	public String message="";
	int messageCounter=0; 
	public boolean gameFinished=false; 
	public String CurrentDialogue="";
	public int commandeNum; 
	
	
	
	public Ui(GamePanel gp) {
		this.gp=gp;
		try {
			InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
			maruMonica=Font.createFont(Font.TRUETYPE_FONT, is); 
			is=getClass().getResourceAsStream("/font/Purisa Bold.ttf"); 
			purisaB=Font.createFont(Font.TRUETYPE_FONT, is); 
			}catch(FontFormatException e) {
				e.printStackTrace(); 
			}catch (IOException e) {
				e.printStackTrace();
				}
		}
	public void showMessage(String text) {
		message=text; 
		messageOn=true; 
	}
	public void draw(Graphics2D g2) {
		this.g2=g2; 
		g2.setFont(maruMonica); 
		g2.setRenderingHint(RenderingHint.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);
		if (gp.gameState==gp.titleState) {
			drawTitleScreen(); 
			
		}
		
	}


	
	
	

}
