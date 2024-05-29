package main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe principale du jeu
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

        // Ajout du panel du jeu
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel, BorderLayout.CENTER);

        // Démarrage du thread principal
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.startGameThread();

        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int currentMonthIndex = (int) ((System.currentTimeMillis() - startTime) / monthDuration);
                if (currentMonthIndex >= months.length || GamePanel.m_player.getPourcentageSatisfaction() <= 0) {
                    ((Timer) evt.getSource()).stop();
                    window.remove(gamePanel);

                    JPanel endPanel = new JPanel();
                    endPanel.setBackground(Color.BLACK);
                    endPanel.setLayout(new BoxLayout(endPanel, BoxLayout.Y_AXIS)); //Utiliser BoxLayout pour aligner les composants verticalement

                    JLabel endLabel = new JLabel("Fin de la partie");
                    endLabel.setForeground(Color.WHITE);
                    endLabel.setFont(new Font("Arial", Font.BOLD, 30));
                    endLabel.setAlignmentX(Component.CENTER_ALIGNMENT); //Centrer horizontalement

                    JLabel endLabel2 = new JLabel("Score: " + String.valueOf(GamePanel.m_player.getScore()));
                    endLabel2.setForeground(Color.WHITE);
                    endLabel2.setFont(new Font("Arial", Font.PLAIN, 20));
                    endLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);

                    JLabel endLabel3 = new JLabel("Satisfaction: " + String.valueOf(GamePanel.m_player.getPourcentageSatisfaction())+"%");
                    endLabel3.setForeground(Color.WHITE);
                    endLabel3.setFont(new Font("Arial", Font.PLAIN, 20));
                    endLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);
                    
                    JLabel endLabel4 = new JLabel("Argent: "+ String.valueOf(GamePanel.m_player.getCoin())+"€");
                    endLabel4.setForeground(Color.WHITE);
                    endLabel4.setFont(new Font("Arial", Font.PLAIN, 20));
                    endLabel4.setAlignmentX(Component.CENTER_ALIGNMENT);

                    endPanel.add(Box.createRigidArea(new Dimension(0, 250))); //Espace entre le haut et le premier label
                    endPanel.add(endLabel);
                    endPanel.add(Box.createRigidArea(new Dimension(0, 20))); //Espace entre les labels
                    endPanel.add(endLabel2);
                    endPanel.add(Box.createRigidArea(new Dimension(0, 20))); 
                    endPanel.add(endLabel3);
                    endPanel.add(Box.createRigidArea(new Dimension(0, 20))); 
                    endPanel.add(endLabel4);
                    
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
