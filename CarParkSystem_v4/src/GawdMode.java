

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GawdMode extends JFrame {

	JTextArea innerText= new JTextArea();
	
	GawdMode (){ 
	Container pane = getContentPane();

	JPanel panelInside = new JPanel();
	
	panelInside = buildframe(10,10);

	pane.add(panelInside);
	
	setSize(650,650);
	setVisible(true);
	setTitle("OverView");
	
	}

	public JPanel buildframe(int x, int y){
		JPanel panelsquare = new JPanel(new GridLayout(x,y));
		for(int row = 0; row < x; row++){
			for(int column = 0; column < y; column++){ 
				JPanel panelHigh = new JPanel();

				innerText = new JTextArea(1,1);
				innerText.setFont(new Font("Calibri", Font.PLAIN, 25));

				panelHigh.add(innerText);
				panelsquare.add(panelHigh);
				innerText.setText(" ");


				panelHigh.setBorder(BorderFactory.createLineBorder(Color.black));
			}
		}

		return panelsquare;
	}


		public static void main(String[] args) {
			GawdMode derek = new GawdMode();
			}

}
