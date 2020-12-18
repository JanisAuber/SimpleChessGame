
public class Brett implements Cloneable {

	//0= Schwarz 1= Weiﬂ
		public Figur [][] bauerArr = {
				{
			new Figur (0,6,false, "B06", "*B*"),
			new Figur (1,6,false, "B16", "*B*"),
			new Figur (2,6,false, "B26", "*B*"),
			new Figur (3,6,false, "B36", "*B*"),
			new Figur (4,6,false, "B46", "*B*"),
			new Figur (5,6,false, "B56", "*B*"),
			new Figur (6,6,false, "B66", "*B*"),
			new Figur (7,6,false, "B76", "*B*")
		},
				{
			new Figur (0,1,true, "B01", "-B-"),
			new Figur (1,1,true, "B11", "-B-"),
			new Figur (2,1,true, "B21", "-B-"),
			new Figur (3,1,true, "B31", "-B-"),
			new Figur (4,1,true, "B41", "-B-"),
			new Figur (5,1,true, "B51", "-B-"),
			new Figur (6,1,true, "B61", "-B-"),
			new Figur (7,1,true, "B71", "-B-")
		}
		};
		public Figur [][] turmArr = {
				{
			new Figur (0,7,false, "T07", "*T*"),
			new Figur (7,7,false, "T77", "*T*"),
			},
				{
			new Figur (0,0,true, "T00", "-T-"),
			new Figur (7,0,true, "T70", "-T-"),
			}
		};
		public Figur [][] pferdArr = {
				{
			new Figur (1,7,false, "P17", "*P*"),
			new Figur (6,7,false, "P67", "*P*")
			},
				{
			new Figur (1,0,true, "P10", "-P-"),
			new Figur (6,0,true, "P60", "-P-")
			}
		};
		public Figur [][] lauferArr = {
				{
			new Figur (2,7,false, "L27", "*L*"),
			new Figur (5,7,false, "L57", "*L*")
			},
				{
			new Figur (2,0,true, "L20", "-L-"),
			new Figur (5,0,true, "L50", "-L-")
			}
		};
		public Figur [] dameArr = {
			new Figur (4,7,false, "D47", "*D*"),
			new Figur (4,0,true, "D40", "-D-")
		};
		public Figur [] konigArr = {
				new Figur (3,7,false, "D37", "*K*"),
				new Figur (3,0,true, "D30", "-K-")
			};
		
		
		public Feld [][] felderArr = {
				//Reihen 0,1,6 und 7 sollten noch mit dem entsprechenden Konstruktor mit einer Figur belegt werden.
				{new Feld (0,0, turmArr[1][0]), new Feld (0,1, bauerArr [1][0]), new Feld (0,2), new Feld (0,3),
				new Feld (0,4), new Feld (0,5), new Feld (0,6, bauerArr [0][0]), new Feld (0,7, turmArr [0][0])},
				
				{new Feld (1,0, pferdArr[1][0]), new Feld (1,1, bauerArr [1][1]),new Feld (1,2), new Feld (1,3),
				new Feld (1,4), new Feld (1,5), new Feld (1,6, bauerArr [0][1]), new Feld (1,7, pferdArr[0][0])},
				
				{new Feld (2,0, lauferArr [1][0]),  new Feld (2,1, bauerArr [1][2]), new Feld (2,2),  new Feld (2,3),
				new Feld (2,4), new Feld (2,5), new Feld (2,6, bauerArr [0][2]), new Feld (2,7, lauferArr [0][0])},
				
				{new Feld (3,0, konigArr [1]), new Feld (3,1, bauerArr [1][3]),  new Feld (3,2), new Feld (3,3), 
				new Feld (3,4), new Feld (3,5), new Feld (3,6, bauerArr [0][3]), new Feld (3,7, konigArr[0])},
				
				{new Feld (4,0, dameArr [1]), new Feld (4,1, bauerArr [1][4]), new Feld (4,2), new Feld (4,3),
				new Feld (4,4), new Feld (4,5), new Feld (4,6, bauerArr [0][4]), new Feld (4,7, dameArr [0])},
				
				{new Feld (5,0, lauferArr [1][1]), new Feld (5,1, bauerArr [1][5]), new Feld (5,2), new Feld (5,3), 
				new Feld (5,4), new Feld (5,5), new Feld (5,6, bauerArr [0][5]), new Feld (5,7, lauferArr [0][1])},
				
				{new Feld (6,0, pferdArr[1][1]), new Feld (6,1, bauerArr [1][6]),  new Feld (6,2), new Feld (6,3), 
				new Feld (6,4), new Feld (6,5), new Feld (6,6, bauerArr [0][6]), new Feld (6,7, pferdArr[0][1])},
				
				{new Feld (7,0, turmArr [1][1]), new Feld (7,1, bauerArr [1][7]), new Feld (7,2), new Feld (7,3),
				new Feld (7,4), new Feld (7,5), new Feld (7,6, bauerArr [0][7]), new Feld (7,7, turmArr [0][1])},
		};
	
Brett(){
	
}

Brett (Brett copyBrett){
	for (int i=0; i<8; i++) {
		for (int j=0; j<8; j++) {
			this.felderArr [i][j] = new Feld (copyBrett.felderArr[i][j]);
		}
	}
}


public void refreshAllPosFieldsIgnKing () {
	for (int xC = 0; xC<8; xC++) {
		for (int yC = 0; yC<8; yC++) {
			if (felderArr [xC][yC].obj != null)
				felderArr [xC][yC].obj.refreshPosFieldsIgnKing(this);
		}
	}
}

	
	
	
}
