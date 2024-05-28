package object;

import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import main.GamePanel;

public class toilet extends Object{
	
	public toilet(GamePanel a_gp,int x,int y) {
		m_name = "toilet";
		try {
			m_Image = ImageIO.read(getClass().getResource("/object/toilet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		m_x=x;
		m_y=y;
		m_gp=a_gp;
	}

	public static void add_toilet_to_panel(GamePanel a_gp,List<toilet> tab_toilets) {
		
	}
}