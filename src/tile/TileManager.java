package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

/**
 * 
 * Gestionnaire des tiles du jeu
 *
 */
public class TileManager {
	GamePanel m_gp;			//panel du jeu principal
	Tile[] m_tile;			//tableau de toutes les tiles possibles dans le jeu
	int m_maxTiles = 30;	//nombre maximum de tiles chargeable dans le jeu
	int m_mapTileNum[][];	//r�partition des tiles dans la carte du jeu
	public static boolean m_use=false;
	public static boolean m_act=false;
	public int m_mapChoose = 1;
	/**
	 * Constructeur
	 * @param gp
	 */
	public TileManager(GamePanel gp) {
		this.m_gp =  gp;
		m_tile = new Tile[m_maxTiles];
		m_mapTileNum = new int[gp.MAX_SCREEN_COL][gp.MAX_SCREEN_ROW];
		this.getTileImage();
		this.loadMap("/maps/map.txt");
	}
	
	/**
	 * Chargement de toutes les tuiles du jeu en associant un chiffre à une image
	 */
	public void getTileImage() {
		try {
			m_tile[0] = new Tile(false);
			m_tile[0].m_image = ImageIO.read(getClass().getResource("/tiles/GRASS.png"));
			
			m_tile[1] = new Tile(true);
			m_tile[1].m_image = ImageIO.read(getClass().getResource("/tiles/wall_h.png"));
			
			m_tile[11] = new Tile(true);
			m_tile[11].m_image = ImageIO.read(getClass().getResource("/tiles/wall_v.png"));

			m_tile[12] = new Tile(true);
			m_tile[12].m_image = ImageIO.read(getClass().getResource("/tiles/wall_v_2.png"));
			
			m_tile[13] = new Tile(true);
			m_tile[13].m_image = ImageIO.read(getClass().getResource("/tiles/wall_corner_bl.png"));
			
			m_tile[14] = new Tile(true);
			m_tile[14].m_image = ImageIO.read(getClass().getResource("/tiles/wall_corner_br.png"));
			
			m_tile[15] = new Tile(true);
			m_tile[15].m_image = ImageIO.read(getClass().getResource("/tiles/wall_corner_bru.png"));
			
			m_tile[16] = new Tile(true);
			m_tile[16].m_image = ImageIO.read(getClass().getResource("/tiles/wall_corner_blu.png"));
			
			m_tile[17] = new Tile(true);
			m_tile[17].m_image = ImageIO.read(getClass().getResource("/tiles/wall_corner_ur.png"));

			m_tile[19] = new Tile(true);
			m_tile[19].m_image = ImageIO.read(getClass().getResource("/tiles/wall_corner_rlb.png"));

			m_tile[20] = new Tile(true);
			m_tile[20].m_image = ImageIO.read(getClass().getResource("/tiles/wall_corner_rlu.png"));

			m_tile[21] = new Tile(true);
			m_tile[21].m_image = ImageIO.read(getClass().getResource("/tiles/wall_corner_ul.png"));
			
			m_tile[3] = new Tile(true);
			m_tile[3].m_image = ImageIO.read(getClass().getResource("/tiles/door_h.png"));

			m_tile[25] = new Tile(true);
			m_tile[25].m_image = ImageIO.read(getClass().getResource("/tiles/door_v.png"));
			
			m_tile[28] = new Tile(false);
			m_tile[28].m_image = ImageIO.read(getClass().getResource("/tiles/door_h_opened.png"));
			
			m_tile[29] = new Tile(false);
			m_tile[29].m_image = ImageIO.read(getClass().getResource("/tiles/door_v_opened.png"));

			m_tile[6] = new Tile(false);
			m_tile[6].m_image = ImageIO.read(getClass().getResource("/tiles/stairs_1.png"));
			
			m_tile[18] = new Tile(false);
			m_tile[18].m_image = ImageIO.read(getClass().getResource("/tiles/stairs_2.png"));
			
			m_tile[24] = new Tile(false);
			m_tile[24].m_image = ImageIO.read(getClass().getResource("/tiles/stairs_3.png"));

			m_tile[22] = new Tile(false);
			m_tile[22].m_image = ImageIO.read(getClass().getResource("/tiles/stairs_4.png"));
			
			m_tile[4] = new Tile(false);
			m_tile[4].m_image = ImageIO.read(getClass().getResource("/tiles/floor.png"));

			m_tile[23] = new Tile(false);
			m_tile[23].m_image = ImageIO.read(getClass().getResource("/tiles/grass_2.png"));
			
			m_tile[2] = new Tile(true);
			m_tile[2].m_image = ImageIO.read(getClass().getResource("/tiles/coffee-1.png"));
			
			m_tile[23] = new Tile(true);
			m_tile[23].m_image = ImageIO.read(getClass().getResource("/tiles/coffee-2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public boolean isWall(int x, int y) {
		int tileX = (-m_gp.scrollOffsetX + x) / m_gp.TILE_SIZE ;
		int tileY = (-m_gp.scrollOffsetY + y ) / m_gp.TILE_SIZE;
		return m_tile[m_mapTileNum[tileX][tileY]].m_collision;
	}
	
	public void stairsUpdate(int x, int y) {
		int tileX = (-m_gp.scrollOffsetX + x) / m_gp.TILE_SIZE ;
		int tileY = (-m_gp.scrollOffsetY + y ) / m_gp.TILE_SIZE;
		
		if(m_mapTileNum[tileX][tileY] == 6 || m_mapTileNum[tileX][tileY] == 18) {
			if(m_mapChoose==1) {
				m_mapChoose=2;
				this.loadMap("/maps/map2.txt");
			} else {
				m_mapChoose=1;
				this.loadMap("/maps/map.txt");
			}
		}
	}
	
	public void coffeeUpdate() {
		//La machine à café casse à partir de octobre jusqu'à ce qu'elle soit réparée
		if(m_gp.currentMonth!="Septembre" && !m_gp.reparationPossible() && m_gp.machineReparee==false) {
			m_mapTileNum[46][44]=23;
		} else {
			m_mapTileNum[46][44]=2;
		}
	}
	
	//Tester si la machine à café est cassée
	public boolean breakCoffee(){
		return m_mapTileNum[46][44]==23;
	}
	
	//Tester si le personnage est devant la machine à café cassée
	public boolean behindBreakCoffee(){
			//Points autour du personnage pour détecter les trucs
			int tileX = (-m_gp.scrollOffsetX + 650) / m_gp.TILE_SIZE ;
			int tileY = (-m_gp.scrollOffsetY + 400 ) / m_gp.TILE_SIZE;
			return(m_mapTileNum[tileX][tileY] == 23);		
	}
	
	//Tester si le personnage appuie sur a pour réparer la machine à café
	public boolean reparationCoffee() {
		if(behindBreakCoffee()) {
			return(m_act);
		}
		return false;
	}
	
	public void doorUpdate() {
		if(m_use) {
			//Points autour du personnage pour détecter les trucs
			int tileX = (-m_gp.scrollOffsetX + 640) / m_gp.TILE_SIZE ;
			int tileY = (-m_gp.scrollOffsetY + 380 ) / m_gp.TILE_SIZE;
			
			if(m_mapTileNum[tileX][tileY] == 3) {
				if(m_mapTileNum[tileX - 1][tileY] == 1) {
					m_mapTileNum[tileX][tileY]=28;
				} else {
					m_mapTileNum[tileX][tileY]=29;
				}
				m_use=false;
			}
			
			else if(m_mapTileNum[tileX][tileY] == 28 || m_mapTileNum[tileX][tileY] == 29) {
				m_mapTileNum[tileX][tileY]=3;
				m_use=false;
			}
			
			tileX = (-m_gp.scrollOffsetX + 670) / m_gp.TILE_SIZE ;
			if(m_mapTileNum[tileX][tileY] == 3) {
				if(m_mapTileNum[tileX - 1][tileY] == 1) {
					m_mapTileNum[tileX][tileY]=28;
				} else {
					m_mapTileNum[tileX][tileY]=29;
					}
				m_use=false;
			}
			else if(m_mapTileNum[tileX][tileY] == 28 || m_mapTileNum[tileX][tileY] == 29) {
				m_mapTileNum[tileX][tileY]=3;
				m_use=false;
			}
			
			tileX = (-m_gp.scrollOffsetX + 650) / m_gp.TILE_SIZE ;
			tileY = (-m_gp.scrollOffsetY + 375 ) / m_gp.TILE_SIZE;
			if(m_mapTileNum[tileX][tileY] == 3) {
				if(m_mapTileNum[tileX - 1][tileY] == 1) {
					m_mapTileNum[tileX][tileY]=28;
				} else {
					m_mapTileNum[tileX][tileY]=29;
				}
				m_use=false;
			}
			else if(m_mapTileNum[tileX][tileY] == 28 || m_mapTileNum[tileX][tileY] == 29) {
				m_mapTileNum[tileX][tileY]=3;
				m_use=false;
			}
			
			tileY = (-m_gp.scrollOffsetY + 400 ) / m_gp.TILE_SIZE;
			if(m_mapTileNum[tileX][tileY] == 3) {
				if(m_mapTileNum[tileX - 1][tileY] == 1) {
					m_mapTileNum[tileX][tileY]=28;
				} else {
					m_mapTileNum[tileX][tileY]=29;
				}
				m_use=false;
			}
			else if(m_mapTileNum[tileX][tileY] == 28 || m_mapTileNum[tileX][tileY] == 29) {
				m_mapTileNum[tileX][tileY]=3;
				m_use=false;
			}
		}
	}
	
	/**
	 * Lecture du fichier txt contenant la map et chargement des tuiles correspondantes.
	 */
	public void loadMap(String filePath) {
		//charger le fichier txt de la map
		try {
			
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
			int col = 0;
			int row = 0;
			
			// Parcourir le fichier txt pour r�cup�rer les valeurs
			while (col < m_gp.MAX_SCREEN_COL && row < m_gp.MAX_SCREEN_ROW) {
				String line = br.readLine();
				while (col < m_gp.MAX_SCREEN_COL) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					m_mapTileNum [col][row] = num;
					col++;
				}
				if (col == m_gp.MAX_SCREEN_COL) {
					col = 0;
					row ++;
				}
			}
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	IL FAUT ENFT QUE JE FASSE UNE FONCTION QUI VERIFIE LE PIXEL PRECISEMENT LE FRATE

	
	/**
	 * Affichage de la carte avec les diff�rentes tuiles
	 * @param g2
	 */
	public void draw(Graphics2D g2) {
		int x = m_gp.scrollOffsetX;
		int y = m_gp.scrollOffsetY;
		for(int row=0;row < m_gp.MAX_SCREEN_ROW;row++) {
			for(int col=0;col < m_gp.MAX_SCREEN_COL;col++) {
				int tileNum = m_mapTileNum[col][row];
				if(tileNum == 1) {
					if(row < m_gp.MAX_SCREEN_ROW-1 && col < m_gp.MAX_SCREEN_COL-1) {
						if((m_mapTileNum[col][row - 1] == 1 || m_mapTileNum[col][row - 1] == 3 || m_mapTileNum[col][row - 1] == 29)
						&& (m_mapTileNum[col][row + 1] == 1 || m_mapTileNum[col][row + 1] == 3 || m_mapTileNum[col][row + 1] == 29)
						&& m_mapTileNum[col - 1][row] != 1
						&& m_mapTileNum[col + 1][row] != 1) {
							tileNum = 11;
						}

						if((m_mapTileNum[col][row - 1] == 1 || m_mapTileNum[col][row - 1] == 3 || m_mapTileNum[col][row - 1] == 29)
						&& (m_mapTileNum[col][row + 1] != 1 && m_mapTileNum[col][row + 1] != 3 && m_mapTileNum[col][row + 1] != 29)
						&& m_mapTileNum[col - 1][row] != 1
						&& m_mapTileNum[col + 1][row] != 1) {
							tileNum = 12;
						}
						
						if(m_mapTileNum[col][row - 1] != 1 
						&& m_mapTileNum[col][row + 1] == 1
						&& m_mapTileNum[col - 1][row] == 1
						&& m_mapTileNum[col + 1][row] != 1) {
							tileNum = 13;
						}
						if(m_mapTileNum[col][row - 1] != 1 
						&& (m_mapTileNum[col][row + 1] == 1)
						&& m_mapTileNum[col - 1][row] != 1
						&& m_mapTileNum[col + 1][row] == 1) {
							tileNum = 14;
						}
						if(m_mapTileNum[col][row - 1] == 1 
						&& m_mapTileNum[col][row + 1] == 1
						&& m_mapTileNum[col - 1][row] != 1
						&& m_mapTileNum[col + 1][row] == 1) {
							tileNum = 15;
						}
						if(m_mapTileNum[col][row - 1] == 1 
						&& m_mapTileNum[col][row + 1] == 1
						&& m_mapTileNum[col - 1][row] == 1
						&& m_mapTileNum[col + 1][row] != 1) {
							tileNum = 16;
						}
						if(m_mapTileNum[col][row - 1] == 1 
						&& m_mapTileNum[col][row + 1] != 1
						&& m_mapTileNum[col - 1][row] != 1
						&& m_mapTileNum[col + 1][row] == 1) {
							tileNum = 17;
						}
						
						if(m_mapTileNum[col][row - 1] != 1 
						&& m_mapTileNum[col][row + 1] == 1
						&& m_mapTileNum[col - 1][row] == 1
						&& m_mapTileNum[col + 1][row] == 1) {
							tileNum = 19;
						}
						if(m_mapTileNum[col][row - 1] == 1 
						&& m_mapTileNum[col][row + 1] != 1
						&& m_mapTileNum[col - 1][row] == 1
						&& m_mapTileNum[col + 1][row] == 1) {
							tileNum = 20;
						}

						if(m_mapTileNum[col][row - 1] == 1 
						&& m_mapTileNum[col][row + 1] != 1
						&& m_mapTileNum[col - 1][row] == 1
						&& m_mapTileNum[col + 1][row] != 1
						&& m_mapTileNum[col + 1][row] != 3 && m_mapTileNum[col + 1][row] != 28 ) {
							tileNum = 21;
						}	
					}
				}
				if(tileNum == 3) {
					if(row < m_gp.MAX_SCREEN_ROW-1 && col < m_gp.MAX_SCREEN_COL-1) {
						if(m_mapTileNum[col][row - 1] == 1 
							&& m_mapTileNum[col][row + 1] == 1
							&& m_mapTileNum[col - 1][row] != 1
							&& m_mapTileNum[col + 1][row] != 1) {
								tileNum = 25;
							}	
					}
				}
				if(tileNum == 6) {
					if(m_mapChoose != 1) {
						tileNum = 22;
					}
					
					if(row < m_gp.MAX_SCREEN_ROW-1 && col < m_gp.MAX_SCREEN_COL-1) {
						if(m_mapTileNum[col][row + 1] == 6) {
							if(m_mapChoose == 1) {
								tileNum = 18;
							} else {
								tileNum = 24;
							}
						}
					}
				}
//				if(tileNum == 0) {
//					if(m_mapChoose != 1) {
//						tileNum = 23;
//					}
//				}
				g2.drawImage(m_tile[tileNum].m_image, x, y, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
				x += m_gp.TILE_SIZE;
			}
			x = m_gp.scrollOffsetX;
			y += m_gp.TILE_SIZE;
		}
		
//		while (col < m_gp.MAX_SCREEN_COL && row < m_gp.MAX_SCREE_ROW) {
//			int tileNum = m_mapTileNum[col][row];
//			
//			g2.drawImage(m_tile[tileNum].m_image, x, y, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
//			col ++;
//			x += m_gp.TILE_SIZE;
//			if (col == m_gp.MAX_SCREEN_COL) {
//				col = 0;
//				row ++;
//				x = 0;
//				y += m_gp.TILE_SIZE;
//			}
//		}
		
	}
}
