

public class Feld {
	public int x;
	public int y;
	
	public Figur obj;
	
	

public String toString () {
	return "Feld: " + x + ", " + y;
}
	
Feld (int xin, int yin){
	x=xin;
	y=yin;
	
	obj = null;
}

//Für das Initialisieren der Felder mit Figur
Feld (int xin, int yin, Figur pObj){
	x=xin;
	y=yin;
	obj = pObj;
}

//Copy-Konstruktor
Feld (Feld copyFeld){
	x=copyFeld.x;
	y=copyFeld.y;
	if (copyFeld.obj != null) {
		obj = new Figur (copyFeld.obj);} else {
			obj = null;
		}
}
	
	
}
