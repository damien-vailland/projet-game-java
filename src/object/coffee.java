package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class coffee extends Object{
	public coffee(GamePanel a_gp,int x,int y) {
		m_name = "coffee";
		try {
			m_Image = ImageIO.read(getClass().getResource("/tiles/coffee-1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		m_x=x;
		m_y=y;
		m_gp=a_gp;
	}

	public void draw(Graphics2D a_g2) {
		// r�cup�re l'image du joueur
		BufferedImage l_image = m_Image;
		int screenX = m_x + m_gp.scrollOffsetX;
	    int screenY = m_y + m_gp.scrollOffsetY;
		// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		a_g2.drawImage(l_image, screenX, screenY, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
}

}
