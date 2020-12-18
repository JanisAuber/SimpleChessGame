import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class GuiChess extends JFrame{
	
public static JButton [][] knopfArr = {
		{
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
		},
		{
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			
		},
		{
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
		},
		{
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
		},
		{
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
		},
		{
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
		},
		{
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
		},
		{
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
			new JButton("....."),
		}
};
private static JTextArea anzeige;

public static void setAnzeige (String inhalt) {
	anzeige.setText(inhalt);
}

GuiChess(){
	setTitle ("Schach");
	getContentPane().setBackground(Color.black);
	setDefaultCloseOperation (EXIT_ON_CLOSE);
	setLayout (new FlowLayout());
	setSize (550, 670);
	setResizable (false);
	initComp();
	boolean blackOrWhite = true;
	for (int i=0; i<knopfArr.length; i++) {
		for (int j=0; j<knopfArr[i].length; j++) {
			if (blackOrWhite) {
			knopfArr[j][i].setBackground(Color.lightGray);
			} else {
			knopfArr[j][i].setBackground(new Color (157, 155, 153));
			}
			knopfArr[j][i].setPreferredSize(new Dimension(55, 55));
			add(knopfArr[j][i]);
			blackOrWhite = !blackOrWhite;
		}
		blackOrWhite = !blackOrWhite;
	}
	add (anzeige);
	anzeige.setBackground(new Color (170, 200, 150));
	setLocationRelativeTo (null);
	setVisible (true);
	//Font bigger:
	// get the current font
	  Font f = anzeige.getFont();
	// create a new, smaller font from the current font
	 Font f2 = new Font(f.getFontName(), f.getStyle(), f.getSize()+24);
	// set the new font in the editing area
	  anzeige.setFont(f2);
}
private static void initComp () {
	anzeige = new JTextArea (3,10);
	anzeige.setLineWrap(true);
	anzeige.setWrapStyleWord(true);
	setAnzeige ("Weiß beginnt.");
	
	for (int i=0; i<knopfArr.length; i++) {
		for (int j=0; j<knopfArr[i].length; j++) {
			int tempI=i;
			int tempJ=j;
	knopfArr [i][j].addActionListener(new ActionListener () {
		@Override
		public void actionPerformed(ActionEvent e) {
			ZugLogik.knopfGedr(tempI,tempJ);
			}
		});
	
		}
	}
}
public static void refresh() {
	for (int xC = 0; xC<8; xC++) {
		for (int yC = 0; yC<8; yC++) {
			if(MainChess.mainBrett.felderArr[xC][yC].obj != null) {
			knopfArr[xC][yC].setText(MainChess.mainBrett.felderArr[xC][yC].obj.typ);
		}else {
			knopfArr[xC][yC].setText("");
		}
		}
	}
}

}

