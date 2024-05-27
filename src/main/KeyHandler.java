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
		if(code==37) {
			entity.Player.gauche=true;
		}
		if(code==38) {
			entity.Player.haut=true;
		}
		if(code==39) {
			entity.Player.droite=true;
		}
		if(code==40) {
			entity.Player.bas=true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if(code==37) {
			entity.Player.gauche=false;
		}
		if(code==38) {
			entity.Player.haut=false;
		}
		if(code==39) {
			entity.Player.droite=false;
		}
		if(code==40) {
			entity.Player.bas=false;
		}
	}

}
;