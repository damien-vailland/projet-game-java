package main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.*;

/**
 * 
 * Classe principale du jeu
 *
 */
public class Main {

	public static void main(String[] args) {
		
		long monthDuration = 6 * 1000;
		long startTime = System.currentTimeMillis();
		String[] months = {"Septembre", "Octobre", "Novembre", "Décembre", "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet"};
		int currentMonthIndex =(int) ((System.currentTimeMillis() - startTime)/monthDuration);
		String date_a_afficher = months[currentMonthIndex];
		
		//Fen�tre de lancement du jeu
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("ESIR1 - Projet Prog");
		
		//Ajout du panel du jeu 
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel, BorderLayout.CENTER);
		
		// Ajout du label pour afficher le mois
        JLabel dateLabel = new JLabel();
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(dateLabel, BorderLayout.EAST);
        window.add(topPanel, BorderLayout.NORTH);
        
        //d�marrage du thread principal
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		gamePanel.startGameThread();	
		
		while (date_a_afficher != "Juillet") {			
			currentMonthIndex =(int) ((System.currentTimeMillis() - startTime)/monthDuration);
			date_a_afficher = months[currentMonthIndex];
			dateLabel.setText(date_a_afficher);
		}
		
		window.remove(gamePanel);
        JPanel endPanel = new JPanel();
        endPanel.setBackground(Color.BLACK);
        JLabel endLabel = new JLabel("Fin de la partie");
        endLabel.setForeground(Color.WHITE);
        endLabel.setFont(new Font("Arial", Font.BOLD, 30));
        endPanel.add(endLabel);
        window.add(endPanel, BorderLayout.CENTER);
        window.revalidate();
        window.repaint();
		
	}

}
