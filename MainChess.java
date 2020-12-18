

public class MainChess {
	//0 = Weiﬂ Auswahl	 1 = Weiﬂ Ziel 	2 = Schwarz Auswahl 	3 = Schwarz Ziehl 	4 = Game Over
	
	public static Brett mainBrett = new Brett ();
	
	
	
	
	
	public static void readAllFields () {
			for (int i=0; i<mainBrett.felderArr.length; i++) {
				for (int j=0; j<mainBrett.felderArr[i].length; j++) {
					System.out.println(mainBrett.felderArr[i][j]);
				}
			}
	}
	
	
	public static void castle (boolean farbe, boolean longside, Brett pBrett) {
		if (farbe) {
			if (longside) {
				//Kˆnig
				getFigur(3, 0, pBrett).x= 5;
				Figur tempFig = pBrett.felderArr[3][0].obj;
				pBrett.felderArr[3][0].obj = null;
				pBrett.felderArr [5][0].obj = tempFig;
				//Turm
				getFigur(7, 0, pBrett).x= 4;
				tempFig = pBrett.felderArr[7][0].obj;
				pBrett.felderArr[7][0].obj = null;
				pBrett.felderArr [4][0].obj = tempFig;
			} else if (!longside) {
				//Kˆnig
				getFigur(3, 0, pBrett).x= 1;
				Figur tempFig = pBrett.felderArr[3][0].obj;
				pBrett.felderArr[3][0].obj = null;
				pBrett.felderArr [1][0].obj = tempFig;
				//Turm
				getFigur(0, 0, pBrett).x= 2;
				tempFig = pBrett.felderArr[0][0].obj;
				pBrett.felderArr[0][0].obj = null;
				pBrett.felderArr [2][0].obj = tempFig;
			}
		} else if (!farbe) {
			if (longside) {
				//Kˆnig
				getFigur(3, 7, pBrett).x= 5;
				Figur tempFig = pBrett.felderArr[3][7].obj;
				pBrett.felderArr[3][7].obj = null;
				pBrett.felderArr [5][7].obj = tempFig;
				//Turm
				getFigur(7, 7, pBrett).x= 4;
				tempFig = pBrett.felderArr[7][7].obj;
				pBrett.felderArr[7][7].obj = null;
				pBrett.felderArr [4][7].obj = tempFig;
			} else if (!longside) {
				//Kˆnig
				getFigur(3, 7, pBrett).x= 1;
				Figur tempFig = pBrett.felderArr[3][7].obj;
				pBrett.felderArr[3][7].obj = null;
				pBrett.felderArr [1][7].obj = tempFig;
				//Turm
				getFigur(0, 7, pBrett).x= 2;
				tempFig = pBrett.felderArr[0][7].obj;
				pBrett.felderArr[0][7].obj = null;
				pBrett.felderArr [2][7].obj = tempFig;
			}
		}
	}
	public static void enPassant (Figur pFigur, int zielX, int zielY, Brett pBrett) {
		replaceFigur (pFigur.x, pFigur.y, zielX, zielY, pBrett);
		if (zielY == 2) {
			pBrett.felderArr [pFigur.x][3].obj = null;
		}
		if (zielY == 5) {
			pBrett.felderArr [pFigur.x][4].obj = null;
		}
	}
	public static void replaceFigur(int xAusgang, int yAusgang, int xZiel, int yZiel, Brett pBrett) {
		//en passant Flag
		if (getFigur(xAusgang, yAusgang, pBrett).typ == "-B-" || getFigur(xAusgang, yAusgang, pBrett).typ == "*B*") {
			if (getFigur(xAusgang, yAusgang, pBrett).farbe) {
				if (yAusgang == 1) {
					if (yZiel == 3) {
						if (xAusgang > 0) {
							if (pBrett.felderArr [xAusgang - 1] [3].obj != null)
								pBrett.felderArr [xAusgang - 1] [3].obj.enPassantPosXPlus = true;
							}
						if (xAusgang < 7) {
							if (pBrett.felderArr [xAusgang + 1] [3].obj != null)
								pBrett.felderArr [xAusgang + 1] [3].obj.enPassantPosXMinus = true;
						}
					}
				}
			} else if (!getFigur(xAusgang, yAusgang, pBrett).farbe) {
				if (yAusgang == 6) {
					if (yZiel == 4) {
						if (xAusgang > 0) {
							if (pBrett.felderArr [xAusgang - 1] [4].obj != null)
								pBrett.felderArr [xAusgang - 1] [4].obj.enPassantPosXPlus = true;
							}
						if (xAusgang < 7) {
							if (pBrett.felderArr [xAusgang + 1] [4].obj != null)
								pBrett.felderArr [xAusgang + 1] [4].obj.enPassantPosXMinus = true;
						}
					}
				}
			}
		}
		//Standardteil
		try {
		getFigur(xZiel, yZiel, pBrett).x= 66;
		getFigur(xZiel, yZiel, pBrett).y= 66;
		} catch (NullPointerException e){
			//Figur zieht auf ein leeres Feld
		}
		getFigur(xAusgang, yAusgang, pBrett).x= xZiel;
		getFigur(xAusgang, yAusgang, pBrett).y= yZiel;
		Figur tempFig = pBrett.felderArr[xAusgang][yAusgang].obj;
		pBrett.felderArr[xAusgang][yAusgang].obj = null;
		pBrett.felderArr [xZiel][yZiel].obj = tempFig;
		getFigur(xZiel, yZiel, pBrett).bewegt = true;
		//Bauer in letzte Reihe wird zu Dame
		if (getFigur(xZiel, yZiel, pBrett).typ == "-B-" && getFigur(xZiel, yZiel, pBrett).y == 7) {
			getFigur(xZiel, yZiel, pBrett).typ = "-D-";
		}
		if (getFigur(xZiel, yZiel, pBrett).typ == "*B*" && getFigur(xZiel, yZiel, pBrett).y == 0) {
			getFigur(xZiel, yZiel, pBrett).typ = "*D*";
		}
	}
	
	public static Figur getFigur(int x, int y, Brett pBrett) {
		if(x<8 && x>=0 && y<8 && y>=0) {
		if(pBrett.felderArr[x][y].obj!=null) {
		return pBrett.felderArr[x][y].obj;
		}else 
			return null;
		}else
			return null;
	}
	
	
	public static void main(String[] args) {
		GuiChess guiC = new GuiChess ();
		GuiChess.refresh();
		
	}
	
	

}
