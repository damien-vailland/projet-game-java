package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import main.GamePanel;

public class coins extends Object{

	public static int nb_max_coins = 7;
	public static int nb_coins=0;
	
	public coins(GamePanel a_gp,int x,int y) {
		m_name = "coins";
		try {
			m_Image = ImageIO.read(getClass().getResource("/tiles/coins.png"));
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

	public static void ajouterCoordonnees(List<List<Integer>> liste, int x, int y) {
        List<Integer> paire = new ArrayList<>();
        paire.add(x);
        paire.add(y);
        liste.add(paire);
    }
	
	public static void add_Coins_to_panel(GamePanel a_gp,List<coins> tab_coins, List<List<Integer>> coordonee_coin) {
		if(nb_coins<nb_max_coins) {
			for (List<Integer> coordonnees : coordonee_coin) {
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
}