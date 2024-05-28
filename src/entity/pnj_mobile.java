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
            m_idleImage = ImageIO.read(getClass().getResource("/tiles/pnj.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
    	if (pause) {
            if (movingToTarget) {
                moveTowards(m_targetX, m_targetY);
                if (m_x == m_targetX && m_y == m_targetY) {
                    movingToTarget = false;
                }
            } else {
                moveTowards(m_startX, m_startY);
                if (m_x == m_startX && m_y == m_startY) {
                    movingToTarget = true;
                }
            }
    	}
    }

     public void moveTowards(int targetX, int targetY) {
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
        BufferedImage l_image = m_idleImage;
        int screenX = m_x + m_gp.scrollOffsetX;
        int screenY = m_y + m_gp.scrollOffsetY;
        a_g2.drawImage(l_image, screenX, screenY, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
    }

}