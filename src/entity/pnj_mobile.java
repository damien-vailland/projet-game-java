package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import main.GamePanel;

public class pnj_mobile extends Entity {

	  GamePanel m_gp;
	    int m_startX, m_startY, m_targetX, m_targetY;
	    boolean movingToTarget = true;
	    public boolean pause = true;
	    private BufferedImage[][] m_idleImages = new BufferedImage[2][4];
	    private int m_indice = 0;
	    private int m_delay_anim = 0;
	    private int m_direction = 0;

	    public pnj_mobile(GamePanel a_gp, int startX, int startY, int targetX, int targetY) {
	        this.m_x = startX;
	        this.m_y = startY;
	        this.m_startX = startX;
	        this.m_startY = startY;
	        this.m_targetX = targetX;
	        this.m_targetY = targetY;
	        this.m_gp = a_gp;
	        this.setDefaultValues();
	        this.getPlayerImage();
	    }

    protected void setDefaultValues() {
        m_speed = 1; // Définissez une vitesse par défaut
    }

    public void getPlayerImage() {
        try {
            m_idleImages[0][0] = ImageIO.read(getClass().getResource("/player/pnj-2-front-1.png"));
            m_idleImages[0][1] = ImageIO.read(getClass().getResource("/player/pnj-2-front-2.png"));
            m_idleImages[0][2] = ImageIO.read(getClass().getResource("/player/pnj-2-front-1.png"));
            m_idleImages[0][3] = ImageIO.read(getClass().getResource("/player/pnj-2-front-4.png"));
            m_idleImages[1][0] = ImageIO.read(getClass().getResource("/player/pnj-2-back-1.png"));
            m_idleImages[1][1] = ImageIO.read(getClass().getResource("/player/pnj-2-back-2.png"));
            m_idleImages[1][2] = ImageIO.read(getClass().getResource("/player/pnj-2-back-1.png"));
            m_idleImages[1][3] = ImageIO.read(getClass().getResource("/player/pnj-2-back-4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
    	if (pause) {
            if (movingToTarget) {
            	m_direction=1;
                moveTowards(m_targetX, m_targetY);
                if (m_x == m_targetX && m_y == m_targetY) {
                    movingToTarget = false;
                }
            } else {
                moveTowards(m_startX, m_startY);
            	m_direction=0;
                if (m_x == m_startX && m_y == m_startY) {
                    movingToTarget = true;
                }
            }
    	}
    }

     void moveTowards(int targetX, int targetY) {
		if(m_delay_anim >= 5 ) {
			if(m_indice >= 3) {
				m_indice = 0;
			} else {
				m_indice++;
			}
				m_delay_anim=0;
			} else {
				m_delay_anim++;
		}
        if (m_x < targetX) {
            m_x += m_speed;
            if (m_x > targetX) m_x = targetX;
        } else if (m_x > targetX) {
            m_x -= m_speed;
            if (m_x < targetX) m_x = targetX;
        }

        if (m_y < targetY) {
            m_y += m_speed;
            if (m_y > targetY) m_y = targetY;
        } else if (m_y > targetY) {
            m_y -= m_speed;
            if (m_y < targetY) m_y = targetY;
        }
    }

    public void draw(Graphics2D a_g2) {
		BufferedImage l_image = m_idleImages[m_direction][m_indice];
        int screenX = m_x + m_gp.scrollOffsetX;
        int screenY = m_y + m_gp.scrollOffsetY;
        a_g2.drawImage(l_image, screenX, screenY, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
    }

}