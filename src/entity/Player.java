package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

/**
 * D�fintition du comportement d'un joueur
 *
 */
public class Player extends Entity{

	GamePanel m_gp;
	KeyHandler m_keyH;

	public int m_boxG,m_boxD,m_boxH,m_boxB;
	public static boolean gauche=false,droite=false,haut=false,bas=false; 
	static int pourcentage_energy;
	int score;
	static int m_coins=40;
	public static int salaire=0;
	
	/**
	 * Constructeur de Player
	 * @param a_gp GamePanel, pannel principal du jeu
	 * @param a_keyH KeyHandler, gestionnaire des touches 
	 */
	public Player(GamePanel a_gp, KeyHandler a_keyH) {
		this.m_gp = a_gp;
		this.m_keyH = a_keyH;
		this.pourcentage_energy = 50;
		this.score = 0;
		this.setDefaultValues();
		this.getPlayerImage();
	}
	
	/**
	 * Initialisation des donn�es membres avec des valeurs par d�faut
	 */
	protected void setDefaultValues() {
		m_x = 632;
		m_y = 352;
		m_speed = 4;
		
		m_boxG = 592;
		m_boxD = 688;
		m_boxH = 400;
		m_boxB=800;
	}

	
	/**
	 * R�cup�ration de l'image du personnage
	 */
	public void getPlayerImage() {
		//gestion des expections 
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/player/main-1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getPourcentageEnergy() {
	    return pourcentage_energy;
	}
	
	public int getCoin() {
		return m_coins;
	}
	
	public int getScore() {
		return score;
	}
	public static void AddCoins(int coin) {
		m_coins += coin;
	}
	
	/**
	 * Mise � jour des donn�es du joueur
	 */
	public void update(boolean murG, boolean murD, boolean murH, boolean murB) {
		if(gauche && !murG) {
			m_gp.scrollOffsetX += m_speed;
		}
		if(droite && !murD) {
			m_gp.scrollOffsetX -=  m_speed;
		}
		if(haut && !murH){
			m_gp.scrollOffsetY += m_speed;
		}
		if(bas && !murB){
			m_gp.scrollOffsetY -=  m_speed;
		}
	}
	
	
	public void updatePourcentageEnergy(int x) {
		if (pourcentage_energy + x < 100) {
			pourcentage_energy += x;
		}
		else {
			pourcentage_energy = 100;
		}
	}
	
	
	/**
	 * Affichage du l'image du joueur dans la fen�tre du jeu
	 * @param a_g2 Graphics2D 
	 */
	public void draw(Graphics2D a_g2) {
		// r�cup�re l'image du joueur
		BufferedImage l_image = m_idleImage;
		// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		a_g2.drawImage(l_image, m_x, m_y, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
	}
	
	public boolean checkCollision(int coinX, int coinY, int coinSize) {
	    int playerLeft = m_x;
	    int playerRight = m_x + m_gp.TILE_SIZE;
	    int playerTop = m_y;
	    int playerBottom = m_y + m_gp.TILE_SIZE;

	    int coinLeft = coinX + m_gp.scrollOffsetX;
	    int coinRight = coinX + m_gp.scrollOffsetX + coinSize;
	    int coinTop = coinY + m_gp.scrollOffsetY;
	    int coinBottom = coinY + m_gp.scrollOffsetY + coinSize;

	    return !(playerLeft >= coinRight || playerRight <= coinLeft || playerTop >= coinBottom || playerBottom <= coinTop);
	}

	
}
