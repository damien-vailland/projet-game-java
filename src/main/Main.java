package main;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import entity.Player;

import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("ESIR1 - Projet Prog");
		window.setLayout(new BorderLayout());


		Dimension windowSize = new Dimension(1280, 720); 
		window.setSize(windowSize);
		window.setPreferredSize(windowSize);

		//Ajout du panel du jeu 
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel, BorderLayout.CENTER);

		//d�marrage du thread principal
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		gamePanel.startGameThread();	

		Timer timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int currentMonthIndex =(int) ((System.currentTimeMillis() - startTime)/monthDuration);
				if (currentMonthIndex >= months.length || GamePanel.m_player.getPourcentageSatisfaction() <= 0) {
					((Timer) evt.getSource()).stop();
					window.remove(gamePanel);
					JPanel endPanel = new JPanel();
					endPanel.setBackground(Color.BLACK);
					JLabel endLabel = new JLabel("Fin de la partie");
					endLabel.setForeground(Color.WHITE);
					endLabel.setFont(new Font("Arial", Font.BOLD, 30));
					endPanel.add(endLabel, BorderLayout.CENTER);
					window.add(endPanel, BorderLayout.CENTER);
					window.revalidate();
					window.repaint();
				} else {
					gamePanel.updateCurrentMonth(startTime, monthDuration, months);
				}
			}
		});
		timer.start();    
	}
}
