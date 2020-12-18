import java.awt.Point;
import java.util.Arrays;

public class Figur{
	int x;
	int y;
	boolean farbe;
	String name;
	String typ;
	boolean [][] posFieldsIgnKing = new boolean [8][8];
	boolean bewegt = false;
	boolean enPassantPosXPlus = false;
	boolean enPassantPosXMinus = false;
	
	
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isFarbe() {
		return farbe;
	}
	public void setFarbe(boolean pFarbe) {
		farbe = pFarbe;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	

	Figur (
			int xIn,
			int yIn,
			boolean farbeIn,
			String nameIn,
			String typIn
			)
	{
		this.setName(nameIn);
		this.setX(xIn);
		this.setY(yIn);
		this.setFarbe(farbeIn);
		this.setTyp(typIn);
	}
	
	Figur (Figur copyFigur){
		
		this.setName(copyFigur.name);
		this.setX(copyFigur.x);
		this.setY(copyFigur.y);
		this.setFarbe(copyFigur.farbe);
		this.setTyp(copyFigur.typ);
	}
	


public void refreshPosFieldsIgnKing (Brett pBrett) {
	//System.out.println(this.name + ".refreshPosFieldsIgnKing() <<");
	boolean [][] tempBoolArr = new boolean [8][8];
	for (int xC = 0; xC<8; xC++) {
		for (int yC = 0; yC<8; yC++) {
			tempBoolArr [xC][yC] = false;
		}
	}
	if (this.typ == "*B*" || this.typ == "-B-") {
		if (this.farbe) {
			//Zwei nach vorne - weiß
			if (this.y == 1 && MainChess.getFigur(this.x, this.y+1, pBrett) == null 
					&& MainChess.getFigur(this.x, this.y+2, pBrett) == null) {
				tempBoolArr [this.x][this.y+2] = true;
			}
			//Eins nach vorne - weiß
			if (MainChess.getFigur(this.x, this.y+1, pBrett) == null) {
				tempBoolArr [this.x][this.y+1] = true;
			}
			//Links nach vorne - weiß
			if (MainChess.getFigur(this.x - 1, this.y+1, pBrett) != null && MainChess.getFigur(this.x - 1, this.y+1, pBrett).farbe != this.farbe) {
				tempBoolArr [this.x-1][this.y+1] = true;
			}
			//Rechts nach vorne - weiß
			if (MainChess.getFigur(this.x + 1, this.y+1, pBrett) != null && MainChess.getFigur(this.x + 1, this.y + 1, pBrett).farbe != this.farbe) {
				tempBoolArr [this.x+1][this.y+1] = true;
			}
		}
		else if (!this.farbe) {
			//Zwei nach vorne - schwarz
			if (this.y == 6 && MainChess.getFigur(this.x, this.y-1, pBrett) == null 
					&& MainChess.getFigur(this.x, this.y-2, pBrett) == null) {
				tempBoolArr [this.x][this.y-2] = true;
				}
			//Eins nach vorne - schwarz
			if (MainChess.getFigur(this.x, this.y-1, pBrett) == null) {
				tempBoolArr [this.x][this.y-1] = true;
			}
			//Rechts nach vorne - schwarz
			if (MainChess.getFigur(this.x - 1, this.y-1, pBrett) != null && MainChess.getFigur(this.x - 1, this.y-1, pBrett).farbe != this.farbe) {
				tempBoolArr [this.x-1][this.y-1] = true;
			}
			//Links nach vorne - schwarz
			if (MainChess.getFigur(this.x + 1, this.y-1, pBrett) != null && MainChess.getFigur(this.x + 1, this.y-1, pBrett).farbe != this.farbe) {
				tempBoolArr [this.x+1][this.y-1] = true;
			}
		}
		this.posFieldsIgnKing = tempBoolArr;
	}
	
	else if (this.typ == "*P*" || this.typ == "-P-") {
		//Pferd
		int [][] legalArray = new int [8][2];
		
		legalArray [0][0] = this.x+1;
		legalArray [0][1] = this.y+2;
		
		legalArray [1][0] = this.x+2;
		legalArray [1][1] = this.y+1;
		
		legalArray [2][0] = this.x+2;
		legalArray [2][1] = this.y-1;
		
		legalArray [3][0] = this.x+1;
		legalArray [3][1] = this.y-2;
		
		legalArray [4][0] = this.x-1;
		legalArray [4][1] = this.y-2;
		
		legalArray [5][0] = this.x-2;
		legalArray [5][1] = this.y-1;
		
		legalArray [6][0] = this.x-2;
		legalArray [6][1] = this.y+1;
		
		legalArray [7][0] = this.x-1;
		legalArray [7][1] = this.y+2;
		
		for (int xC=0; xC<8; xC++) {
			if (legalArray[xC][0]<8 && legalArray[xC][0] >= 0 
					&& legalArray [xC][1]<8 && legalArray [xC][1]>=0) {
				if (pBrett.felderArr[legalArray [xC][0]][legalArray [xC][1]].obj != null) {
				if(pBrett.felderArr[legalArray [xC][0]][legalArray [xC][1]].obj.farbe != this.farbe) {
			tempBoolArr [legalArray[xC][0]][legalArray [xC][1]] = true;
					}
				}else {
				tempBoolArr [legalArray[xC][0]][legalArray [xC][1]] = true;
				}
			}
		}
	this.posFieldsIgnKing = tempBoolArr;
	}
	
	else if (this.typ == "*T*" || this.typ == "-T-") {
		boolean colisionXplus = false;
		boolean colisionXminus= false;
		boolean colisionYplus = false;
		boolean colisionYminus = false;
		//Der Algoritmus muss von der Figur ausgehend solange Felder true schalten, 
		//bis er ein besetztes Feld erreicht, welches als letztes true geschaltet wird.
		for (int xC = 1; xC<8; xC++) {
			if (!colisionXplus && this.x + xC < 8) {
			if (pBrett.felderArr [this.x+xC][this.y].obj == null)
				tempBoolArr [this.x + xC][this.y] = true;
			if (pBrett.felderArr [this.x+xC][this.y].obj != null) {
				if (pBrett.felderArr [this.x+xC][this.y].obj.farbe == !this.farbe)
					tempBoolArr [this.x + xC][this.y] = true;
			}
			colisionXplus = !(pBrett.felderArr [this.x+xC][this.y].obj == null);
			}
		}
		for (int xC = 1; xC<8; xC++) {
			if (!colisionXminus && this.x - xC >= 0) {
				if (pBrett.felderArr [this.x-xC][this.y].obj == null)
					tempBoolArr [this.x - xC][this.y] = true;
				if (pBrett.felderArr [this.x-xC][this.y].obj != null) {
					if (pBrett.felderArr [this.x-xC][this.y].obj.farbe == !this.farbe)
						tempBoolArr [this.x - xC][this.y] = true;
				}
			colisionXminus = !(pBrett.felderArr [this.x-xC][this.y].obj == null);
			}
		}
		for (int yC = 1; yC<8; yC++) {
			if (!colisionYplus && this.y + yC < 8) {
				if (pBrett.felderArr [this.x][this.y+yC].obj == null)
					tempBoolArr [this.x][this.y+yC] = true;
				if (pBrett.felderArr [this.x][this.y+yC].obj != null) {
					if (pBrett.felderArr [this.x][this.y+yC].obj.farbe == !this.farbe)
						tempBoolArr [this.x][this.y+yC] = true;
				}
			colisionYplus = !(pBrett.felderArr [this.x][this.y + yC].obj == null);
			}
		}
		for (int yC = 1; yC<8; yC++) {
			if (!colisionYminus && this.y - yC >=0) {
				if (pBrett.felderArr [this.x][this.y-yC].obj == null)
					tempBoolArr [this.x][this.y-yC] = true;
				if (pBrett.felderArr [this.x][this.y-yC].obj != null) {
					if (pBrett.felderArr [this.x][this.y-yC].obj.farbe == !this.farbe)
						tempBoolArr [this.x][this.y-yC] = true;
				}
			colisionYminus = !(pBrett.felderArr [this.x][this.y - yC].obj == null);
		}
	}
		this.posFieldsIgnKing = tempBoolArr;
}
	else if (this.typ == "*L*" || this.typ == "-L-") {
		//Läufer
		
		//Oben rechts
		boolean colision = false;
		for (int i=1; i<8; i++) {
			Point pTemp = new Point (this.x + i,this.y + i);
			if (!colision) {
			if (pTemp.x<8 && pTemp.y <8) {
				if (pBrett.felderArr [pTemp.x][pTemp.y].obj == null)
					tempBoolArr [pTemp.x][pTemp.y] = true;
				if (pBrett.felderArr [pTemp.x][pTemp.y].obj != null) {
					if (pBrett.felderArr [pTemp.x][pTemp.y].obj.farbe != this.farbe)
						tempBoolArr [pTemp.x][pTemp.y] = true;
				}
				if (pBrett.felderArr [pTemp.x][pTemp.y].obj != null)
					colision = true;
				}
			}
		}
		//Unten rechts
		colision = false;
		for (int i=1; i<8; i++) {
			Point pTemp = new Point (this.x + i,this.y - i);
			if (!colision) {
			if (pTemp.x<8 && pTemp.y >= 0) {
				if (pBrett.felderArr [pTemp.x][pTemp.y].obj == null)
					tempBoolArr [pTemp.x][pTemp.y] = true;
				if (pBrett.felderArr [pTemp.x][pTemp.y].obj != null) {
					if (pBrett.felderArr [pTemp.x][pTemp.y].obj.farbe != this.farbe)
						tempBoolArr [pTemp.x][pTemp.y] = true;
				}
				if (pBrett.felderArr [pTemp.x][pTemp.y].obj != null)
					colision = true;
				}
			}
		}
		//Unten links
		colision = false;
		for (int i=1; i<8; i++) {
			Point pTemp = new Point (this.x - i,this.y - i);
			if (!colision) {
			if (pTemp.x>=0 && pTemp.y >= 0) {
				if (pBrett.felderArr [pTemp.x][pTemp.y].obj == null)
					tempBoolArr [pTemp.x][pTemp.y] = true;
				if (pBrett.felderArr [pTemp.x][pTemp.y].obj != null) {
					if (pBrett.felderArr [pTemp.x][pTemp.y].obj.farbe != this.farbe)
						tempBoolArr [pTemp.x][pTemp.y] = true;
				}
				if (pBrett.felderArr [pTemp.x][pTemp.y].obj != null)
					colision = true;
				}
			}
		}
		//Oben links
		colision = false;
		for (int i=1; i<8; i++) {
			Point pTemp = new Point (this.x - i,this.y + i);
			if (!colision) {
			if (pTemp.x>=0 && pTemp.y < 8) {
				if (pBrett.felderArr [pTemp.x][pTemp.y].obj == null)
					tempBoolArr [pTemp.x][pTemp.y] = true;
				if (pBrett.felderArr [pTemp.x][pTemp.y].obj != null) {
					if (pBrett.felderArr [pTemp.x][pTemp.y].obj.farbe != this.farbe)
						tempBoolArr [pTemp.x][pTemp.y] = true;
				}
				if (pBrett.felderArr [pTemp.x][pTemp.y].obj != null)
					colision = true;
				}
			}
		}
		this.posFieldsIgnKing = tempBoolArr;
	}
	else if (this.typ == "*K*" || this.typ == "-K-") {
		int [][] legalArray = new int [8][2];
		
		legalArray [0][0] = this.x+1;
		legalArray [0][1] = this.y+1;
		
		legalArray [1][0] = this.x+1;
		legalArray [1][1] = this.y;
		
		legalArray [2][0] = this.x+1;
		legalArray [2][1] = this.y-1;
		
		legalArray [3][0] = this.x;
		legalArray [3][1] = this.y-1;
		
		legalArray [4][0] = this.x-1;
		legalArray [4][1] = this.y-1;
		
		legalArray [5][0] = this.x-1;
		legalArray [5][1] = this.y;
		
		legalArray [6][0] = this.x-1;
		legalArray [6][1] = this.y+1;
		
		legalArray [7][0] = this.x;
		legalArray [7][1] = this.y+1;
		
		for (int xC=0; xC<8; xC++) {
			if (legalArray[xC][0] >= 0 && legalArray[xC][0] < 8 && legalArray[xC][1] >= 0 && legalArray[xC][1] < 8) {
				if (pBrett.felderArr [legalArray[xC][0]][legalArray[xC][1]].obj != null) {
					if (pBrett.felderArr [legalArray[xC][0]][legalArray[xC][1]].obj.farbe != this.farbe) {
						tempBoolArr [legalArray[xC][0]][legalArray [xC][1]] = true;
						}
					} else if (pBrett.felderArr [legalArray[xC][0]][legalArray[xC][1]].obj == null) {
						tempBoolArr [legalArray[xC][0]][legalArray [xC][1]] = true;
					}
				}
	}
	this.posFieldsIgnKing = tempBoolArr;
	}
	
	else if (this.typ == "*D*" || this.typ == "-D-") {
		//Dame
		
		//Oben rechts
				boolean colision = false;
				for (int i=1; i<8; i++) {
					Point pTemp = new Point (this.x + i,this.y + i);
					if (!colision) {
					if (pTemp.x<8 && pTemp.y <8) {
						if (pBrett.felderArr [pTemp.x][pTemp.y].obj == null)
							tempBoolArr [pTemp.x][pTemp.y] = true;
						if (pBrett.felderArr [pTemp.x][pTemp.y].obj != null) {
							if (pBrett.felderArr [pTemp.x][pTemp.y].obj.farbe != this.farbe)
								tempBoolArr [pTemp.x][pTemp.y] = true;
						}
						if (pBrett.felderArr [pTemp.x][pTemp.y].obj != null)
							colision = true;
						}
					}
				}
				//Unten rechts
				colision = false;
				for (int i=1; i<8; i++) {
					Point pTemp = new Point (this.x + i,this.y - i);
					if (!colision) {
					if (pTemp.x<8 && pTemp.y >= 0) {
						if (pBrett.felderArr [pTemp.x][pTemp.y].obj == null)
							tempBoolArr [pTemp.x][pTemp.y] = true;
						if (pBrett.felderArr [pTemp.x][pTemp.y].obj != null) {
							if (pBrett.felderArr [pTemp.x][pTemp.y].obj.farbe != this.farbe)
								tempBoolArr [pTemp.x][pTemp.y] = true;
						}
						if (pBrett.felderArr [pTemp.x][pTemp.y].obj != null)
							colision = true;
						}
					}
				}
				//Unten links
				colision = false;
				for (int i=1; i<8; i++) {
					Point pTemp = new Point (this.x - i,this.y - i);
					if (!colision) {
					if (pTemp.x>=0 && pTemp.y >= 0) {
						if (pBrett.felderArr [pTemp.x][pTemp.y].obj == null)
							tempBoolArr [pTemp.x][pTemp.y] = true;
						if (pBrett.felderArr [pTemp.x][pTemp.y].obj != null) {
							if (pBrett.felderArr [pTemp.x][pTemp.y].obj.farbe != this.farbe)
								tempBoolArr [pTemp.x][pTemp.y] = true;
						}
						if (pBrett.felderArr [pTemp.x][pTemp.y].obj != null)
							colision = true;
						}
					}
				}
				//Oben links
				colision = false;
				for (int i=1; i<8; i++) {
					Point pTemp = new Point (this.x - i,this.y + i);
					if (!colision) {
					if (pTemp.x>=0 && pTemp.y < 8) {
						if (pBrett.felderArr [pTemp.x][pTemp.y].obj == null)
							tempBoolArr [pTemp.x][pTemp.y] = true;
						if (pBrett.felderArr [pTemp.x][pTemp.y].obj != null) {
							if (pBrett.felderArr [pTemp.x][pTemp.y].obj.farbe != this.farbe)
								tempBoolArr [pTemp.x][pTemp.y] = true;
						}
						if (pBrett.felderArr [pTemp.x][pTemp.y].obj != null)
							colision = true;
						}
					}
				}
		
		boolean colisionXplus = false;
		boolean colisionXminus= false;
		boolean colisionYplus = false;
		boolean colisionYminus = false;
		//Der Algoritmus muss von der Figur ausgehend solange Felder true schalten, 
		//bis er ein besetztes Feld erreicht, welches als letztes true geschaltet wird.
		for (int xC = 1; xC<8; xC++) {
			if (!colisionXplus && this.x + xC < 8) {
			if (pBrett.felderArr [this.x+xC][this.y].obj == null)
				tempBoolArr [this.x + xC][this.y] = true;
			if (pBrett.felderArr [this.x+xC][this.y].obj != null) {
				if (pBrett.felderArr [this.x+xC][this.y].obj.farbe == !this.farbe)
					tempBoolArr [this.x + xC][this.y] = true;
			}
			colisionXplus = !(pBrett.felderArr [this.x+xC][this.y].obj == null);
			}
		}
		for (int xC = 1; xC<8; xC++) {
			if (!colisionXminus && this.x - xC >= 0) {
				if (pBrett.felderArr [this.x-xC][this.y].obj == null)
					tempBoolArr [this.x - xC][this.y] = true;
				if (pBrett.felderArr [this.x-xC][this.y].obj != null) {
					if (pBrett.felderArr [this.x-xC][this.y].obj.farbe == !this.farbe)
						tempBoolArr [this.x - xC][this.y] = true;
				}
			colisionXminus = !(pBrett.felderArr [this.x-xC][this.y].obj == null);
			}
		}
		for (int yC = 1; yC<8; yC++) {
			if (!colisionYplus && this.y + yC < 8) {
				if (pBrett.felderArr [this.x][this.y+yC].obj == null)
					tempBoolArr [this.x][this.y+yC] = true;
				if (pBrett.felderArr [this.x][this.y+yC].obj != null) {
					if (pBrett.felderArr [this.x][this.y+yC].obj.farbe == !this.farbe)
						tempBoolArr [this.x][this.y+yC] = true;
				}
			colisionYplus = !(pBrett.felderArr [this.x][this.y + yC].obj == null);
			}
		}
		for (int yC = 1; yC<8; yC++) {
			if (!colisionYminus && this.y - yC >=0) {
				if (pBrett.felderArr [this.x][this.y-yC].obj == null)
					tempBoolArr [this.x][this.y-yC] = true;
				if (pBrett.felderArr [this.x][this.y-yC].obj != null) {
					if (pBrett.felderArr [this.x][this.y-yC].obj.farbe == !this.farbe)
						tempBoolArr [this.x][this.y-yC] = true;
				}
			colisionYminus = !(pBrett.felderArr [this.x][this.y - yC].obj == null);
		}
	}
		this.posFieldsIgnKing = tempBoolArr;
	
}
	/*
System.out.println("Ergebnis:");
for (int i=0; i<8; i++) {
	System.out.println("X = " + i);
		System.out.println(Boolean.toString(this.posFieldsIgnKing[i][0]) + 
				Boolean.toString(this.posFieldsIgnKing[i][1]) + Boolean.toString(this.posFieldsIgnKing[i][2]) + 
				Boolean.toString(this.posFieldsIgnKing[i][3]));
		System.out.println(Boolean.toString(this.posFieldsIgnKing[i][4]) + 
				Boolean.toString(this.posFieldsIgnKing[i][5]) + Boolean.toString(this.posFieldsIgnKing[i][6]) + 
				Boolean.toString(this.posFieldsIgnKing[i][7]));
}
System.out.println(">>"); */
}

public boolean isPosField (int pX, int pY, Brett pBrett) {

this.refreshPosFieldsIgnKing(pBrett);
if (this.posFieldsIgnKing[pX][pY]) {
	return true;
} else return false;
}

}

