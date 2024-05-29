package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import main.GamePanel;

public class coins extends Entity{

	public static int nb_max_coins = 7;
	public static int nb_coins=0;
	public static List<List<Integer>> m_coordonee_coin = new ArrayList<>();
	public GamePanel m_gp;
    private BufferedImage[] m_idleImages = new BufferedImage[8];
	int m_indice = 0;
	int m_delay_anim = 0;
	
	public coins(GamePanel a_gp,int x,int y) {
		try {
//			m_idleImage = ImageIO.read(getClass().getResource("/object/coins.png"));
            m_idleImages[0] = ImageIO.read(getClass().getResource("/object/coins.png"));
            m_idleImages[1] = ImageIO.read(getClass().getResource("/object/coins-1.png"));
            m_idleImages[2] = ImageIO.read(getClass().getResource("/object/coins-2.png"));
            m_idleImages[3] = ImageIO.read(getClass().getResource("/object/coins-3.png"));
            m_idleImages[4] = ImageIO.read(getClass().getResource("/object/coins-4.png"));
            m_idleImages[5] = ImageIO.read(getClass().getResource("/object/coins-3.png"));
            m_idleImages[6] = ImageIO.read(getClass().getResource("/object/coins-2.png"));
            m_idleImages[7] = ImageIO.read(getClass().getResource("/object/coins-1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		m_x=x;
		m_y=y;
		m_gp=a_gp;
	}


	public static void ajouterCoordonnees(int x, int y) {
        List<Integer> paire = new ArrayList<>();
        paire.add(x);
        paire.add(y);
        m_coordonee_coin.add(paire);
    }
	
	public static void add_Coins_to_panel(GamePanel a_gp,List<coins> tab_coins) {
		if(nb_coins<nb_max_coins) {
			for (List<Integer> coordonnees : m_coordonee_coin) {
				boolean coordonneesExistantes = false;
	            for (coins coin : tab_coins) {
	                if(coin.m_x == coordonnees.get(0) && coin.m_y == coordonnees.get(1)) {
	                    coordonneesExistantes = true;
	                    break;
	                }
	            }
	            if (!coordonneesExistantes) {
					tab_coins.add(new coins(a_gp,coordonnees.get(0),coordonnees.get(1)));
					nb_coins+=1;
				}
			}
		}
	}
	
	public static void create_tab_coordonnees() {
		ajouterCoordonnees(1350, 580); //salle début
		ajouterCoordonnees(580, 800); //salle 02
		ajouterCoordonnees(1230, 1350); //bureau a gauche
		ajouterCoordonnees(2100, 450); //entree hall
		ajouterCoordonnees(2400, 650); //administration
		ajouterCoordonnees(3150, 200); // amphi L
		ajouterCoordonnees(2300,2100); //bulle
	}
	
	public void draw(Graphics2D a_g2) {
		// r�cup�re l'image du joueur
		if(m_delay_anim >= 4 ) {
				if(m_indice >= 7) {
					m_indice = 0;
				} else {
					m_indice++;
				}
				m_delay_anim=0;
		} else {
			m_delay_anim++;
		}
		BufferedImage l_image = m_idleImages[m_indice];
		int screenX = m_x + m_gp.scrollOffsetX;
	    int screenY = m_y + m_gp.scrollOffsetY;
	    a_g2.drawImage(l_image, screenX, screenY, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
	}
}