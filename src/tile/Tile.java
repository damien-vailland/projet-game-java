package tile;

import java.awt.image.BufferedImage;

/**
 * 
 * Element graphique de la carte
 */
public class Tile {
	public BufferedImage m_image;		//image
	public boolean m_collision;			//d�but de gestion de collision entre �l�ments
	
	Tile(boolean collision){
		m_collision = collision;
	}
	
	public boolean getCollision(){
		return m_collision;
	}
}
