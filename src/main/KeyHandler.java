package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.add_teachers;

/**
 * Gestionnaire d'�v�nements (touche clavier)
 *
 */

public class KeyHandler implements KeyListener{
	GamePanel m_gp;
	public KeyHandler(GamePanel m_gp) {
		this.m_gp=m_gp; 
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// r�cup�re le code du boutton appuy�
		
		int code = e.getKeyCode();
		if (code==80) {
			if (m_gp.gameState==m_gp.playState) {
				m_gp.gameState=m_gp.pauseState;
			}
			else if (m_gp.gameState==m_gp.pauseState) {
				m_gp.gameState=m_gp.playState;
			}
		}
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
		if(code==69) {
			tile.TileManager.m_use=true;
			entity.add_teachers.nouveau_prof=true;
			entity.add_students.nouvel_eleve=true;
		}
		if(code==65) {
			tile.TileManager.m_act=true;
		}
	}

	// Exemple: bander arc en appuyant sur une touche, fleche envoyer quand on lache
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