import java.awt.Point;

public class ZugLogik {
	public static int gameState = 0;
	public static int xTempKn = 0;
	public static int yTempKn = 0;
	public static void knopfGedr (int xKnopf, int yKnopf) {
		System.out.println("Knopf gedrückt: ");
		System.out.println("X: " + xKnopf + " Y: " + yKnopf);
		//System.out.println("Knopf: " +x +", "+y);
		switch (gameState) {
		case 0:
			if (MainChess.getFigur(xKnopf, yKnopf, MainChess.mainBrett)!= null) {
			if (MainChess.getFigur(xKnopf, yKnopf, MainChess.mainBrett).farbe) {
			xTempKn = xKnopf;
			yTempKn = yKnopf;
			gameState = 1;
			GuiChess.setAnzeige("Weiß hat Figur gewählt.");
			}
			}
			break;
		case 1:
			
			if(ZugLogik.zug(gameState, xTempKn, yTempKn, xKnopf, yKnopf)){
				gameState = 2;
				GuiChess.setAnzeige("Schwarz ist am Zug.");
				if (isMate (true)) {
					gameState = 4;
					GuiChess.setAnzeige("GAME OVER - Schwarz gewinnt.");
					GuiChess.refresh();
				}
				} else {
					gameState = 0;
					GuiChess.setAnzeige("Nicht erlaubter Zug. Weiß zieht erneut.");
				}
			
			GuiChess.refresh();
			for (int i=0; i<8; i++) {
				for (int j=0; j<8; j++) {
					if (MainChess.mainBrett.felderArr [i][j].obj != null) {
						if (MainChess.mainBrett.felderArr [i][j].obj.farbe) {
						MainChess.mainBrett.felderArr [i][j].obj.enPassantPosXPlus = false;
						MainChess.mainBrett.felderArr [i][j].obj.enPassantPosXMinus = false;
						}
					}
				}
			}
			break;
		case 2:
			if (MainChess.getFigur(xKnopf, yKnopf, MainChess.mainBrett)!= null) {
			if (!MainChess.getFigur(xKnopf, yKnopf, MainChess.mainBrett).farbe) {
			xTempKn = xKnopf;
			yTempKn = yKnopf;
			gameState = 3;
			GuiChess.setAnzeige("Schwarz hat Figur gewählt.");
			}
			}
			break;
		case 3:
			if(ZugLogik.zug(gameState, xTempKn, yTempKn, xKnopf, yKnopf)){
				gameState = 0;
				GuiChess.setAnzeige("Weiß ist am Zug.");
				if (isMate (true)) {
					gameState = 4;
					GuiChess.setAnzeige("GAME OVER - Weiß gewinnt.");
					GuiChess.refresh();
				}
				} else {
					gameState = 2;
					GuiChess.setAnzeige("Nicht erlaubter Zug. Schwarz zieht erneut.");
				}
			GuiChess.refresh();
			for (int i=0; i<8; i++) {
				for (int j=0; j<8; j++) {
					if (MainChess.mainBrett.felderArr [i][j].obj != null) {
						if (!MainChess.mainBrett.felderArr [i][j].obj.farbe) {
						MainChess.mainBrett.felderArr [i][j].obj.enPassantPosXPlus = false;
						MainChess.mainBrett.felderArr [i][j].obj.enPassantPosXMinus = false;
						}
					}
				}
			}
			break;
		case 4:
			break;
		}
		
	}
	
public static boolean zug (int gameState, int ausgangsX, int ausgangsY, int zielX, int zielY) {
	System.out.println("zug (gameState: " + gameState + " aausgangsX: " + ausgangsX + " ausgangsY: " + ausgangsY + " zielX: " + zielX + " zielY: " + zielY + ")");
	Figur ausgangsFig = MainChess.mainBrett.felderArr [ausgangsX][ausgangsY].obj;
	//Ein Zug muss folgende Vorraussetzungen erfüllen: 
	//1. Die Figur muss auf diese Weiße ziehen können
	//2. Es dürfen keine Figuren im Weg sein, außer beim Pferd
	//3. Der eigene König darf zu Ende des Zuges nicht im Schach stehen
//Castle:
if(ausgangsFig != null) {
	if (ausgangsFig.typ == "-K-" || ausgangsFig.typ == "*K*") {
		if (ausgangsFig.bewegt == false) {
			if (zielX > 5) {
				if (ausgangsFig.farbe) {
					if (MainChess.mainBrett.felderArr [4][0].obj == null 
							&& MainChess.mainBrett.felderArr [5][0].obj == null
							&& MainChess.mainBrett.felderArr [6][0].obj == null
							&& MainChess.mainBrett.felderArr [7][0].obj.typ == "-T-"
							&& MainChess.mainBrett.felderArr [7][0].obj.bewegt == false) {
						if (kingSafe (ausgangsFig, 5, 0, MainChess.mainBrett, true, false)) {
							MainChess.castle(true, true, MainChess.mainBrett);
							return true;
						}
					}
				} else if (!ausgangsFig.farbe) {
					if (MainChess.mainBrett.felderArr [4][7].obj == null 
							&& MainChess.mainBrett.felderArr [5][7].obj == null
							&& MainChess.mainBrett.felderArr [6][7].obj == null
							&& MainChess.mainBrett.felderArr [7][7].obj.typ == "*T*"
							&& MainChess.mainBrett.felderArr [7][7].obj.bewegt == false) {
						if (kingSafe (ausgangsFig, 5, 7, MainChess.mainBrett, true, false)) {
							MainChess.castle(false, true, MainChess.mainBrett);
							return true;
						}
				}
			}}
			if (zielX < 2) {
				if (ausgangsFig.farbe) {
					if (MainChess.mainBrett.felderArr [1][0].obj == null 
							&& MainChess.mainBrett.felderArr [2][0].obj == null
							&& MainChess.mainBrett.felderArr [0][0].obj.typ == "-T-"
							&& MainChess.mainBrett.felderArr [0][0].obj.bewegt == false) {
						if (kingSafe (ausgangsFig, 1, 0, MainChess.mainBrett, true, false)) {
							MainChess.castle(true, false, MainChess.mainBrett);
							return true;
						}
					}
				} else if (!ausgangsFig.farbe) {
					if (MainChess.mainBrett.felderArr [1][7].obj == null 
							&& MainChess.mainBrett.felderArr [2][7].obj == null
							&& MainChess.mainBrett.felderArr [0][7].obj.typ == "*T*"
							&& MainChess.mainBrett.felderArr [0][7].obj.bewegt == false) {
						if (kingSafe (ausgangsFig, 1, 7, MainChess.mainBrett, true, false)) {
							MainChess.castle(false, false, MainChess.mainBrett);
							return true;
						
					}
				}
			}
		}
	}
}
//en passant:
if (ausgangsFig.typ == "-B-" || ausgangsFig.typ == "*B*") {
	if (ausgangsFig.y == 4 && ausgangsFig.farbe) {
		if (ausgangsFig.enPassantPosXPlus) {
			if (zielY == 5 && zielX == ausgangsFig.x + 1) {
				if (kingSafe (ausgangsFig, zielX, zielY, MainChess.mainBrett, false, true)) {
					MainChess.enPassant(ausgangsFig, zielX, zielY, MainChess.mainBrett);
					return true;
				}
			}
		}
		if (ausgangsFig.enPassantPosXMinus) {
			if (zielY == 5 && zielX == ausgangsFig.x - 1) {
				if (kingSafe (ausgangsFig, zielX, zielY, MainChess.mainBrett, false, true)) {
					MainChess.enPassant(ausgangsFig, zielX, zielY, MainChess.mainBrett);
					return true;
				}
			}
		}
	}
	if (ausgangsFig.y == 3 && !ausgangsFig.farbe) {
		if (ausgangsFig.enPassantPosXPlus) {
			if (zielY == 2 && zielX == ausgangsFig.x + 1) {
				if (kingSafe (ausgangsFig, zielX, zielY, MainChess.mainBrett, false, true)) {
					MainChess.enPassant(ausgangsFig, zielX, zielY, MainChess.mainBrett);
					return true;
				}
			}
		}
		if (ausgangsFig.enPassantPosXMinus) {
			if (zielY == 2 && zielX == ausgangsFig.x - 1) {
				if (kingSafe (ausgangsFig, zielX, zielY, MainChess.mainBrett, false, true)) {
					MainChess.enPassant(ausgangsFig, zielX, zielY, MainChess.mainBrett);
					return true;
				}
			}
		}
	}
}
	
	
//Standardzug:
if(ausgangsFig != null) {
	if (ausgangsFig.isPosField(zielX, zielY, MainChess.mainBrett)){
		if (kingSafe(ausgangsFig, zielX, zielY, MainChess.mainBrett, false, false)) {
			MainChess.replaceFigur(ausgangsX, ausgangsY, zielX, zielY, MainChess.mainBrett);
			return true;
			} else return false;
		} else return false;
	} 
} return false;
}

public static boolean kingSafe (Figur pFigur, int zielX, int zielY, Brett pBrett, boolean castle, boolean enPassant) {
	//Legales Feld für eine Figur. Es wird berücksichtigt, dass nicht ins Schach 
		//gezogen werden darf.
	//1. Es wird eine Kopie des Bretts erstellt.
	//2. Der zu prüfende Zug wird auf dem temporären Brett ausgeführt.
	//3. Nun wird Brett.refreshAllPosFieldsIgnKing ausgeführt.
	//4. Bei allen Zügen, die gegnerische Figuren auf den eigenen König machen können, 
		//muss überprüft werden, ob der König von dieser Farbe danach im Schach steht.
	//Fazit: Wenn der eigene König bei keiner Figur des gegnerischen Teams ein 
		//mögliches Feld ist, ist der Zug erlaubt.
	Brett testBrett = new Brett (pBrett);
	if (enPassant) {
		MainChess.enPassant(testBrett.felderArr [pFigur.x][pFigur.y].obj, zielX, zielY, testBrett);
	}
	
	if (castle) {
		boolean longside = false;
		if (zielX > 5)
			longside = true;
		MainChess.castle(pFigur.farbe, longside, testBrett);
	}
	if (!castle && !enPassant) {
	MainChess.replaceFigur(pFigur.x, pFigur.y, zielX, zielY, testBrett);
	}
	testBrett.refreshAllPosFieldsIgnKing();
	Figur testKönig = null;
	if (pFigur.farbe) {
		for (int i=0; i<8; i++) {
			for (int j=0; j< 8; j++) {
				if (MainChess.getFigur(i, j, testBrett)!= null) {
					if (MainChess.getFigur(i, j, testBrett).typ == "-K-")
						testKönig = MainChess.getFigur(i, j, testBrett);
				}
			}
		}
	} else 
	if (!pFigur.farbe) {
		for (int i=0; i<8; i++) {
			for (int j=0; j< 8; j++) {
				if (MainChess.getFigur(i, j, testBrett)!= null) {
					if (MainChess.getFigur(i, j, testBrett).typ == "*K*")
						testKönig = MainChess.getFigur(i, j, testBrett);
				}
			}
		}
	}
	for (int i=0; i<8; i++) {
		for (int j=0; j< 8; j++) {
			if (MainChess.getFigur(i, j, testBrett) != null) {
				if (MainChess.getFigur(i, j, testBrett).farbe != pFigur.farbe) {
					if (MainChess.getFigur(i, j, testBrett).isPosField(testKönig.x, testKönig.y, testBrett)) {
						return false;
					}
				}
			}
		}
	}
	
	return true;
}

static boolean isMate (boolean pFarbe) {
	Brett testBrett = new Brett (MainChess.mainBrett);
	testBrett.refreshAllPosFieldsIgnKing();
	Figur testKönig = null;
	if (pFarbe) {
		for (int i=0; i<8; i++) {
			for (int j=0; j< 8; j++) {
				if (MainChess.getFigur(i, j, testBrett)!= null) {
					if (MainChess.getFigur(i, j, testBrett).typ == "-K-")
						testKönig = MainChess.getFigur(i, j, testBrett);
				}
			}
		}
	} else 
	if (!pFarbe) {
		for (int i=0; i<8; i++) {
			for (int j=0; j< 8; j++) {
				if (MainChess.getFigur(i, j, testBrett)!= null) {
					if (MainChess.getFigur(i, j, testBrett).typ == "*K*")
						testKönig = MainChess.getFigur(i, j, testBrett);
				}
			}
		}
	}
	boolean isCheck = false;
	for (int i=0; i<8; i++) {
		for (int j=0; j< 8; j++) {
			if (MainChess.getFigur(i, j, testBrett) != null) {
				if (MainChess.getFigur(i, j, testBrett).farbe != pFarbe) {
					if (MainChess.getFigur(i, j, testBrett).isPosField(testKönig.x, testKönig.y, testBrett)) {
						isCheck = true;
					}
				}
			}
		}
	}
	
	if (isCheck == false)
		return false;
	if (isCheck == true) {
		boolean posField = false;
		for (int i=0; i<8; i++) {
			for (int j=0; j< 8; j++) {
				if (MainChess.getFigur(i, j, testBrett) != null) {
					if (MainChess.getFigur(i, j, testBrett).farbe == pFarbe) {
						for (int k=0; k<8; k++) {
							for (int l=0; l< 8; l++) {
								if (MainChess.getFigur(i, j, testBrett).isPosField(k, l, testBrett)) {
									if (ZugLogik.kingSafe(MainChess.getFigur(i, j, testBrett), k, l, testBrett, false, false)){
										posField = true;
									}
								}
							}
						}
					}
				}
			}
		}
		if (posField)
			return false;
	}
	return true;
}

}
