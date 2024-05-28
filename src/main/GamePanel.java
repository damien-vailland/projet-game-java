package main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;

import entity.Player;
import entity.add_teachers;
import entity.add_students;
import entity.pnj;
import entity.coins;
import entity.Craie;
import tile.TileManager;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel principal du jeu contenant la map principale
 *
 */
public class GamePanel extends JPanel implements Runnable{
	
	//Param�tres de l'�cran
	final int ORIGINAL_TILE_SIZE = 16; 							// une tuile de taille 16x16
	final int SCALE = 3; 										// �chelle utilis�e pour agrandir l'affichage
	public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; 	// 48x48

	public final int MAX_SCREEN_COL = 73;
	public final int MAX_SCREEN_ROW = 51; 					 	// ces valeurs donnent une r�solution 4:3

	public final int SCREEN_WIDTH = 1280 ; // 768 pixels
	public final int SCREEN_HEIGHT = 720 ;	// 576 pixels
	public int scrollOffsetX = -1000;
	public int scrollOffsetY = -500;

	// FPS : taux de rafraichissement
	int m_FPS;
	
	// Cr�ation des diff�rentes instances (Player, KeyHandler, TileManager, GameThread ...)
	KeyHandler m_keyH;
	Thread m_gameThread;
	public static Player m_player;
	List<pnj> m_tab_pnj_1 = new ArrayList<>();
	List<pnj> m_tab_pnj_2 = new ArrayList<>();
	List<coins> m_tab_coins = new ArrayList<>();
	List<Craie> m_tab_craies;
	Craie m_craie;
	List<Object> inventaire;
	List<List<Integer>> m_coordonee_coin = new ArrayList<>();
	TileManager m_tileM;
	add_teachers m_add_prof;
	add_students m_add_eleve;
	public static int m_nb_teacher=0;
	public static int m_nb_student=0;
	
	String currentMonth = "Septembre";
	
	/**
	 * Constructeur
	 */
	public GamePanel() {
		m_FPS = 60;				
		m_keyH = new KeyHandler(this);
		m_player = new Player(this, m_keyH);
		inventaire = new ArrayList<>();
		m_tab_craies = new ArrayList<>();
		m_craie = new Craie(this, 700,1000);
		m_tab_craies.add(m_craie);
		m_tileM = new TileManager(this);

		entity.pnj.add_pnj_to_panel(this,m_tab_pnj_1,m_tab_pnj_2);
		
		entity.coins.create_tab_coordonnees();
		entity.coins.add_Coins_to_panel(this,m_tab_coins);
		
		m_add_prof = new add_teachers(this,1150, 1550);
		m_add_eleve = new add_students(this,1150, 1850);
		
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(m_keyH);
		this.setFocusable(true);
	}
	
	/**
	 * Lancement du thread principal
	 */
	public int gameState=1;
	public final int playState =1; 
	public final int pauseState=2; 
	public void startGameThread() {
		m_gameThread = new Thread(this);
		m_gameThread.start();
		
	}
	
	public void run() {
		
		double drawInterval = 1000000000/m_FPS; // rafraichissement chaque 0.0166666 secondes
		double nextDrawTime = System.nanoTime() + drawInterval; 
		
		while(m_gameThread != null) { //Tant que le thread du jeu est actif
			
			//Permet de mettre � jour les diff�rentes variables du jeu
			this.update();
			
			//Dessine sur l'�cran le personnage et la map avec les nouvelles informations. la m�thode "paintComponent" doit obligatoirement �tre appel�e avec "repaint()"
			this.repaint();
			
			//Calcule le temps de pause du thread
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long)remainingTime);
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	/**
	 * Mise � jour des donn�es des entit�s
	 */
	public void update() {
		if (gameState==playState) {
		m_player.update(m_tileM.isWall(640, 375) && m_tileM.isWall(640, 400),
						m_tileM.isWall(670, 375) && m_tileM.isWall(670, 400),
						m_tileM.isWall(640,375) && m_tileM.isWall(670,375),
						m_tileM.isWall(640,400) && m_tileM.isWall(670,400))
		;
		
		m_tileM.doorUpdate();
		m_tileM.stairsUpdate(650, 380);
		collectCoins();
		entity.add_teachers.ajout_prof();
		entity.add_students.ajout_eleve();
	}
		else if (gameState==pauseState) {
			//jeu arrêté
		}
		}
	
	/**
	 * Affichage des �l�ments
	 */
	public void drawEnergyBar(Graphics2D g2) {
	    int energyBarWidth = 200; // Largeur totale de la barre d'énergie
	    int energyBarHeight = 20; // Hauteur de la barre d'énergie
	    int x = 10; // Position X de la barre d'énergie
	    int y = 10; // Position Y de la barre d'énergie

	    // Calculer la largeur de la barre d'énergie en fonction de l'énergie du joueur
	    int currentEnergyWidth = (int) (energyBarWidth * (m_player.getPourcentageEnergy() / 100.0));

	    // Dessiner l'arrière-plan de la barre d'énergie (en gris)
	    g2.setColor(Color.GRAY);
	    g2.fillRect(x, y, energyBarWidth, energyBarHeight);

	    // Dessiner la barre d'énergie actuelle (en vert)
	    g2.setColor(Color.GREEN);
	    g2.fillRect(x, y, currentEnergyWidth, energyBarHeight);

	    // Dessiner le contour de la barre d'énergie
	    g2.setColor(Color.BLACK);
	    g2.drawRect(x, y, energyBarWidth, energyBarHeight);
	    
	    g2.setColor(Color.BLACK);
	    String text = "Energie";
	    FontMetrics metrics = g2.getFontMetrics(g2.getFont());
	    int textX = x + (energyBarWidth - metrics.stringWidth(text)) / 2;
	    int textY = y + ((energyBarHeight - metrics.getHeight()) / 2) + metrics.getAscent();
	    g2.drawString(text, textX, textY);
	}
	
	// Affichage de la barre d'argent
	public void drawCoin(Graphics2D g2) {
	    int coinBarWidth = 200; // Largeur totale de la barre d'argent
	    int coinBarHeight = 20; // Hauteur de la barre d'argent
	    int x = 250; // Position X de la barre d'énergie
	    int y = 10; // Position Y de la barre d'énergie

	    // Arrière-plan de la barre d'argent
	    g2.setColor(Color.YELLOW);
	    g2.fillRect(x, y, coinBarWidth, coinBarHeight);

	    // Dessiner le contour de la barre d'argent 
	    g2.setColor(Color.BLACK);
	    g2.drawRect(x, y, coinBarWidth, coinBarHeight);
	    
	    g2.setColor(Color.BLACK);
	    int coinValue = m_player.getCoin(); 
	    String text = String.valueOf(coinValue)+"€"; // Convertir l'entier en chaîne de caractères
	    FontMetrics metrics = g2.getFontMetrics(g2.getFont());
	    int textWidth = metrics.stringWidth(text);
	    int textX = x + (coinBarWidth - textWidth) / 2;
	    int textY = y + ((coinBarHeight - metrics.getHeight()) / 2) + metrics.getAscent();
	    g2.drawString(text, textX, textY);

	}
	
	public void drawCurrentMonth(Graphics2D g2, String currentMonth) {
	    int x = 1000; // Position X pour le mois (à droite)
	    int y = 25; // Position Y pour le mois
	    g2.setColor(Color.WHITE);
	    g2.setFont(new Font("Arial", Font.BOLD, 20));
	    g2.drawString(currentMonth, x, y);
	}
	
    public void updateCurrentMonth(long startTime, long monthDuration, String[] months) {
        int currentMonthIndex = (int) ((System.currentTimeMillis() - startTime) / monthDuration);
        if (currentMonth != months[currentMonthIndex]) {
        	currentMonth = months[currentMonthIndex];
            m_player.updatePourcentageEnergy(-5);
            entity.coins.add_Coins_to_panel(this,m_tab_coins);
			entity.Player.AddCoins(entity.Player.salaire);
        }
    }
	
    public void drawScore(Graphics2D g2) {
    	int x = 800; // Position X pour le mois (à droite)
 	    int y = 25 ; // Position Y pour le mois
 	    g2.setColor(Color.WHITE);
 	    g2.setFont(new Font("Arial", Font.BOLD, 20));
 	    g2.drawString("Score : " + m_player.getScore(), x, y);
    }
    

	/**
	 * Affichage des �l�ments
	 */
    public void drawPauseScreen( Graphics2D g2) {
    	String m_text="GAME PAUSED"; 
    	int length=(int)g2.getFontMetrics().getStringBounds(m_text, g2).getWidth(); 
    	int x=450;
    	int y=400;
    	g2.setColor(Color.red);
    	g2.setFont(new Font("Arial", Font.BOLD, 50));
    	g2.drawString(m_text, x, y);
    	
    }

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		m_tileM.draw(g2);
		m_player.draw(g2);
		drawEnergyBar(g2);
		drawCurrentMonth(g2, currentMonth);
		drawScore(g2);
		drawCoin(g2);
		DialoguePNJ(g2);
		g2.drawString("Professeur : "+m_nb_teacher, 0, 100);
		g2.drawString("Élève : "+m_nb_student, 0, 125);

		if (m_tileM.m_mapChoose == 1) {
			for (pnj pnj:m_tab_pnj_1) {
				pnj.draw(g2);
			}
			for (coins coin:m_tab_coins) {
				coin.draw(g2);
			}
			m_add_prof.draw(g2);
			m_add_eleve.draw(g2);
		}
		if (gameState==pauseState) {
			drawPauseScreen( g2);
		}
		if (m_tileM.m_mapChoose == 2) {
			for (Craie craie : m_tab_craies) {
				craie.draw(g2);
		    }
			for (pnj pnj:m_tab_pnj_2) {
				pnj.draw(g2);
			}
		}
		
		collectCraie();
		g2.dispose();
	}
	
	public void collectCoins() {
	    List<coins> collectedCoins = new ArrayList<>();
	    for (coins coin : m_tab_coins) {
	        if (m_player.checkCollision(coin.m_x, coin.m_y, TILE_SIZE)) {
	            collectedCoins.add(coin);
	            entity.Player.AddCoins(100);
	            entity.coins.nb_coins-=1;
	        }
	    }
	    m_tab_coins.removeAll(collectedCoins);
	}
	
	public void collectCraie() {
        List<Craie> collectedCraies = new ArrayList<>();
        for (Craie craie : m_tab_craies) {
            if (m_player.checkCollision(craie.m_x, craie.m_y, TILE_SIZE)) {
                collectedCraies.add(craie);
                inventaire.add(craie);
            }
        }
        m_tab_craies.removeAll(collectedCraies);
    }
	
	public void DialoguePNJ(Graphics2D g2) {
		g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 12));
        
		if (m_player.checkCollision(m_tab_pnj_1.get(0).m_x, m_tab_pnj_1.get(0).m_y, TILE_SIZE)) {
			boolean var = true;
			if (var) {
				g2.drawString("Tu peux aller me chercher une craie dans la salle 103 ?", m_player.m_x, m_player.m_y - 10);
			}
			if (m_tileM.m_use && inventaire.contains(m_craie) ) {
				inventaire.remove(m_craie);
				entity.Player.AddCoins(100);
				var = false;
				g2.drawString("Merci beaucoup pour ces craies !", m_player.m_x, m_player.m_y - 10);
			}
			
		}
		if (m_player.checkCollision(m_tab_pnj_1.get(1).m_x, m_tab_pnj_1.get(1).m_y, TILE_SIZE)) {
			g2.drawString("PNJ bureau", m_player.m_x, m_player.m_y - 10);
		}
		if (m_player.checkCollision(m_tab_pnj_1.get(2).m_x, m_tab_pnj_1.get(2).m_y, TILE_SIZE)) {
			g2.drawString("AMPHI M", m_player.m_x, m_player.m_y - 10);
		}
		
		if (m_player.checkCollision(m_tab_pnj_2.get(1).m_x, m_tab_pnj_2.get(1).m_y, TILE_SIZE)) {
			g2.drawString("Peux tu aller me chercher les clefs dans le bureau en bas ? \n Pour ouvrir le local", m_player.m_x, m_player.m_y - 10);
		}
		
		if (m_player.checkCollision(m_add_prof.m_x, m_add_prof.m_y, TILE_SIZE)) {
			g2.drawString("Appuyez sur E pour ajouter un nouveau professeur !", m_player.m_x, m_player.m_y - 20);
			g2.drawString("-300€", m_player.m_x, m_player.m_y - 20 + g2.getFontMetrics().getHeight());
		}
		if (m_player.checkCollision(m_add_eleve.m_x, m_add_eleve.m_y, TILE_SIZE)) {
			g2.drawString("Appuyez sur E pour ajouter un nouvel élève !", m_player.m_x, m_player.m_y - 20);
			g2.drawString("+50€", m_player.m_x, m_player.m_y - 20 + g2.getFontMetrics().getHeight());
		}
	}

	
}
