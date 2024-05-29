package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import main.GamePanel;

/**
 * D�fintition du comportement d'un joueur
 *
 */
public class toilet extends Entity{

	GamePanel m_gp;
	
	/**
	 * Constructeur de Player
	 * @param a_gp GamePanel, pannel principal du jeu
	 * @param a_keyH KeyHandler, gestionnaire des touches 
	 */
	public toilet(GamePanel a_gp,int x,int y) {
		this.m_x=x;
		this.m_y=y;
		this.m_gp = a_gp;
		this.setDefaultValues();
		this.getPlayerImage();
	}
	
	/**
	 * Initialisation des donn�es membres avec des valeurs par d�faut
	 */
	protected void setDefaultValues() {
		m_speed=0;
	}

	
	/**
	 * R�cup�ration de l'image du personnage
	 */
	public void getPlayerImage() {
		//gestion des expections 
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/object/toilet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Affichage du l'image du joueur dans la fen�tre du jeu
	 * @param a_g2 Graphics2D 
	 */
	public void draw(Graphics2D a_g2) {
		// r�cup�re l'image du joueur
		BufferedImage l_image = m_idleImage;
		int screenX = m_x + m_gp.scrollOffsetX;
        int screenY = m_y + m_gp.scrollOffsetY;
		// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		a_g2.drawImage(l_image, screenX, screenY, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
	}
	
	public static void add_toilet_to_panel(GamePanel a_gp,List<toilet> tab_toilet) {
		tab_toilet.add(new toilet(a_gp, 500,2302)); 
		tab_toilet.add(new toilet(a_gp, 700,2302)); 
		tab_toilet.add(new toilet(a_gp, 600,2302)); 
		
		tab_toilet.add(new toilet(a_gp, 350,2302)); 
		tab_toilet.add(new toilet(a_gp, 250,2302)); 
		tab_toilet.add(new toilet(a_gp, 150,2302)); 
		
		tab_toilet.add(new toilet(a_gp, 2450,2302));
		tab_toilet.add(new toilet(a_gp, 2550,2302)); 
		
		tab_toilet.add(new toilet(a_gp, 2250,2302)); 
		tab_toilet.add(new toilet(a_gp, 2150,2302)); 
	}
	
}
