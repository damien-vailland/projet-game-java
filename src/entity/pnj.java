package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import main.GamePanel;

/**
 * D�fintition du comportement d'un joueur
 *
 */
public class pnj extends Entity{

	static int nb_student=0;
	GamePanel m_gp;
	private int m_indice_pnj = 0;
	public static List<List<Integer>> m_tab_coordonee_pnj = new ArrayList<>();
	
	/**
	 * Constructeur de Player
	 * @param a_gp GamePanel, pannel principal du jeu
	 * @param a_keyH KeyHandler, gestionnaire des touches 
	 */
	public pnj(GamePanel a_gp,int x,int y) {
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
			m_idleImage = ImageIO.read(getClass().getResource("/player/pnj-" + (m_indice_pnj+1) + ".png"));

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
	
	public static void add_pnj_to_panel(GamePanel a_gp,List<pnj> tab_pnj_1,List<pnj> tab_pnj_2) {
		tab_pnj_1.add(new pnj(a_gp, 700,350));//salle de classe 01
		tab_pnj_1.add(new pnj(a_gp, 1650, 1250));//bureau 
		tab_pnj_1.add(new pnj(a_gp, 2900, 1050));//amphi M
		tab_pnj_1.add(new pnj(a_gp, 500,2214)); //toilette fille gauche
		tab_pnj_1.add(new pnj(a_gp, 2500, 2214)); //toilette garçon droite
		tab_pnj_1.add(new pnj(a_gp, 2200, 2050)); //machine à café
		tab_pnj_1.add(new pnj(a_gp, 2400, 450)); //bureau julien gavard
		
		tab_pnj_2.add(new pnj(a_gp, 700,700)); //salle de classe 102
		tab_pnj_2.add(new pnj(a_gp, 2400, 650));//BDE
	}
	
	public static void add_pnj_to_004(GamePanel a_gp, List<pnj> tab_pnj_004) {
		if(nb_student<28) {
			List<Integer> pair = m_tab_coordonee_pnj.get(main.GamePanel.m_nb_student-1);
			int x = pair.get(0);
			int y = pair.get(1);
	        tab_pnj_004.add(new pnj(a_gp, x, y));
			nb_student+=1;
		}
	}
	public static void ajouterCoordonnees(int x, int y) {
        List<Integer> paire = new ArrayList<>();
        paire.add(x);
        paire.add(y);
        m_tab_coordonee_pnj.add(paire);
    }
	public static void create_tab_coordonnees() {
		ajouterCoordonnees(0,0);
		ajouterCoordonnees(400, 1400);ajouterCoordonnees(400, 1450);ajouterCoordonnees(400, 1500);ajouterCoordonnees(400, 1550);ajouterCoordonnees(400, 1600);ajouterCoordonnees(400, 1650);ajouterCoordonnees(400, 1700);
		ajouterCoordonnees(500, 1400);ajouterCoordonnees(500, 1450);ajouterCoordonnees(500, 1500);ajouterCoordonnees(500, 1550);ajouterCoordonnees(500, 1600);ajouterCoordonnees(500, 1650);ajouterCoordonnees(500, 1700);
		ajouterCoordonnees(600, 1400);ajouterCoordonnees(600, 1450);ajouterCoordonnees(600, 1500);ajouterCoordonnees(600, 1550);ajouterCoordonnees(600, 1600);ajouterCoordonnees(600, 1650);ajouterCoordonnees(600, 1700);
		ajouterCoordonnees(700, 1400);ajouterCoordonnees(700, 1450);ajouterCoordonnees(700, 1500);ajouterCoordonnees(700, 1550);ajouterCoordonnees(700, 1600);ajouterCoordonnees(700, 1650);ajouterCoordonnees(700, 1700);
	}
}
