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

	public static boolean gauche=false,droite=false,haut=false,bas=false; 
	static int pourcentage_energy;
	int score;
	public static int salaire=0;
	public static int m_coins=40;
	
    private BufferedImage[][] m_idleImages = new BufferedImage[4][4];
	int m_indice = 0;
	int m_delay_anim = 0;
	int m_direction = 0;
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
	}

	
	/**
	 * R�cup�ration de l'image du personnage
	 */
	public void getPlayerImage() {
		//gestion des expections 
            try {
                m_idleImages[0][0] = ImageIO.read(getClass().getResource("/player/main-1.png"));
                m_idleImages[0][1] = ImageIO.read(getClass().getResource("/player/main-2.png"));
                m_idleImages[0][2] = ImageIO.read(getClass().getResource("/player/main-1.png"));
                m_idleImages[0][3] = ImageIO.read(getClass().getResource("/player/main-4.png"));
                m_idleImages[1][0] = ImageIO.read(getClass().getResource("/player/main-left-1.png"));
                m_idleImages[1][1] = ImageIO.read(getClass().getResource("/player/main-left-2.png"));
                m_idleImages[1][2] = ImageIO.read(getClass().getResource("/player/main-left-1.png"));
                m_idleImages[1][3] = ImageIO.read(getClass().getResource("/player/main-left-4.png"));
                m_idleImages[2][0] = ImageIO.read(getClass().getResource("/player/main-right-1.png"));
                m_idleImages[2][1] = ImageIO.read(getClass().getResource("/player/main-right-2.png"));
                m_idleImages[2][2] = ImageIO.read(getClass().getResource("/player/main-right-1.png"));
                m_idleImages[2][3] = ImageIO.read(getClass().getResource("/player/main-right-4.png"));
                m_idleImages[3][0] = ImageIO.read(getClass().getResource("/player/main-back-1.png"));
                m_idleImages[3][1] = ImageIO.read(getClass().getResource("/player/main-back-2.png"));
                m_idleImages[3][2] = ImageIO.read(getClass().getResource("/player/main-back-1.png"));
                m_idleImages[3][3] = ImageIO.read(getClass().getResource("/player/main-back-4.png"));
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
		if(gauche || haut || bas || droite) {
			if(m_delay_anim >= m_speed ) {
				if(m_indice >= 3) {
					m_indice = 0;
				} else {
					m_indice++;
				}
				m_delay_anim=0;
			} else {
				m_delay_anim++;
			}
		}
		if(gauche) {
			m_direction=1;
			if(!murG) {
				m_gp.scrollOffsetX += m_speed;
			}
		}
		if(droite) {
			m_direction=2;
			if(!murD) {
				m_gp.scrollOffsetX -=  m_speed;
			}
		}
		if(haut){
			m_direction=3;
			if(!murH) {
				m_gp.scrollOffsetY += m_speed;
			}
		}
		if(bas){
			m_direction=0;
			if(!murB) {
				m_gp.scrollOffsetY -=  m_speed;
			}
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
	
	public void updateScore(int x) {
			score += x;
	}
	
	
	/**
	 * Affichage du l'image du joueur dans la fen�tre du jeu
	 * @param a_g2 Graphics2D 
	 */
	public void draw(Graphics2D a_g2) {
		// r�cup�re l'image du joueur
		BufferedImage l_image = m_idleImages[m_direction][m_indice];
		// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		a_g2.drawImage(l_image, m_x, m_y, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
	}
	
	public boolean checkCollision(int X, int Y, int Size) {
	    int playerLeft = m_x;
	    int playerRight = m_x + m_gp.TILE_SIZE;
	    int playerTop = m_y;
	    int playerBottom = m_y + m_gp.TILE_SIZE;

	    int Left = X + m_gp.scrollOffsetX;
	    int Right = X + m_gp.scrollOffsetX + Size;
	    int Top = Y + m_gp.scrollOffsetY;
	    int Bottom = Y + m_gp.scrollOffsetY + Size;

	    return !(playerLeft >= Right || playerRight <= Left || playerTop >= Bottom || playerBottom <= Top);
	}

	
}
