package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Gestionnaire d'�v�nements (touche clavier)
 *
 */
public class KeyHandler implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// r�cup�re le code du boutton appuy�
		int code = e.getKeyCode();
		System.out.println(code);
	}

	// Exemple: bander arc en appuyant sur une touche, fleche envoyer quand on lache
	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
