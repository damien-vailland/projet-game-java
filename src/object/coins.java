package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import main.GamePanel;

public class coins extends Object{

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
		int nb_max_coins = 10;
		int nb_coins=0;
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
	
	public static void add_Coins_to_panel(List<coins> m_coins) {
//		if(nbcoins<nb_max_coins) {
			for (List<int> coordonnees : m_coordonee_coin) {
				
			}
		}
	}
}