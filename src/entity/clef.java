package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import main.GamePanel;

public class clef extends Entity{

	public GamePanel m_gp;
	
	public clef(GamePanel a_gp,int x,int y) {
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/object/clef.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		m_x=x;
		m_y=y;
		m_gp=a_gp;
	}
	
	public void draw(Graphics2D a_g2) {
		// r�cup�re l'image du joueur
		BufferedImage l_image = m_idleImage;
		int screenX = m_x + m_gp.scrollOffsetX;
	    int screenY = m_y + m_gp.scrollOffsetY;
		// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		a_g2.drawImage(l_image, screenX, screenY, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
	}
}