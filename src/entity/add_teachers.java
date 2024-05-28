package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import main.GamePanel;

public class add_teachers extends Entity{

	public static GamePanel m_gp;
	public static boolean nouveau_prof;
	
	public add_teachers(GamePanel a_gp,int x,int y) {
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/object/facture.png"));
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
	
	public static void ajout_prof() {
		if(nouveau_prof && entity.Player.m_coins>=300) {
			entity.Player.AddCoins(-300);
			entity.Player.pourcentage_energy+=10;
			entity.Player.salaire+=10;
			main.GamePanel.m_nb_teacher+=1;
			nouveau_prof=false;
		}
		else if (nouveau_prof) {
			nouveau_prof=false;
		}
	}
}