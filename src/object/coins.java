package object;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import main.GamePanel;

public class coins extends Object{

	public static int nb_max_coins = 7;
	public static int nb_coins=0;
	public static List<List<Integer>> m_coordonee_coin = new ArrayList<>();
	
	public coins(GamePanel a_gp,int x,int y) {
		m_name = "coins";
		try {
			m_Image = ImageIO.read(getClass().getResource("/object/coins.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		m_x=x;
		m_y=y;
		m_gp=a_gp;
	}


	public static void ajouterCoordonnees(List<List<Integer>> liste, int x, int y) {
        List<Integer> paire = new ArrayList<>();
        paire.add(x);
        paire.add(y);
        liste.add(paire);
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
		ajouterCoordonnees(m_coordonee_coin, 1350, 580); //salle début
		ajouterCoordonnees(m_coordonee_coin, 580, 800); //salle 02
		ajouterCoordonnees(m_coordonee_coin, 1230, 1350); //bureau a gauche
		ajouterCoordonnees(m_coordonee_coin, 2100, 450); //entree hall
		ajouterCoordonnees(m_coordonee_coin, 2400, 650); //administration
		ajouterCoordonnees(m_coordonee_coin, 3150, 200); // amphi L
		ajouterCoordonnees(m_coordonee_coin, 2300,2100); //bulle
	}
}