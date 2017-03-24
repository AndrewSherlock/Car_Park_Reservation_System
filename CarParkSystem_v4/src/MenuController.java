import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.*;
/**
 * @author Andrew, Ken Project -Team wrecked-
 * @version 2.1
 * Date 3-17
 * This Class Assists with the menu
 *
 */

public class MenuController extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	boolean isLoggedIn;
	String[] optArray;
	JPanel cardsArea;
	CarParkReservationController mainController;
	
	JButton home, login, register, reserveButton, mapButton, regUser, carParkDetails;
	
	/**
	 * This method controls the menu
	 * @param isLoggedIn boolean
	 * @param optArray a string array
	 * @param cards 	is a JPanel 
	 * @param isStaff is a boolean
	 * @param mainController reference to main controller
	 */
	public MenuController(boolean isLoggedIn, String[] optArray, JPanel cards, boolean isStaff, CarParkReservationController mainController)
	{
		this.isLoggedIn = isLoggedIn;
		this.optArray = optArray;
		this.cardsArea = cards;	
		this.mainController = mainController;
				
		JPanel buttonHolder = new JPanel();
		buttonHolder.setLayout(new BoxLayout(buttonHolder,BoxLayout.Y_AXIS));
		buttonHolder.add(Box.createRigidArea(new Dimension(0,45)));
		buttonHolder.add(home = buildButton(optArray[0]));
		buttonHolder.add(Box.createRigidArea(new Dimension(0,12)));
		buttonHolder.add(reserveButton = buildButton(optArray[1]));
		buttonHolder.add(Box.createRigidArea(new Dimension(0,12)));
		buttonHolder.add( mapButton = buildButton(optArray[2]) );
		buttonHolder.add(Box.createRigidArea(new Dimension(0,12)));
		buttonHolder.add(register = buildButton(optArray[3]));
		
		
		if(isStaff){
			buttonHolder.add(Box.createRigidArea(new Dimension(0,12)));
			buttonHolder.add(regUser = buildButton("Register User"));
			buttonHolder.add(Box.createRigidArea(new Dimension(0,12)));
			buttonHolder.add( carParkDetails = buildButton("Car Park Details") );
		}
		buttonHolder.add(Box.createRigidArea(new Dimension(0,12)));
		buttonHolder.add( login = buildButton(optArray[4]) );
		this.add(buttonHolder);
	}
	
	/**
	 * @param buttonName
	 * @return button
	 */
	JButton buildButton(String buttonName)
	{
		JButton button = setButtonDefaults();
		button.setText(buttonName);
		return button;
	}
	
	JButton setButtonDefaults()
	{
		JButton button = new JButton();
		button.addActionListener(this);
		button.setMinimumSize(new Dimension(300,40));
		button.setMaximumSize(new Dimension(300,40));
		button.setFont(new Font("Merriweather",Font.BOLD, 25));
		return button;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		CardLayout cards = (CardLayout)cardsArea.getLayout();
		this.getParent().getParent().getParent().getParent();
		
		if(e.getActionCommand() == "Login")
		{
			cards.show(cardsArea,"LoginScreen");
		} else if(e.getActionCommand() == "Register")
		{
			cards.show(cardsArea, "RegisterScreen");
		} else if(e.getActionCommand() == "Logout")
		{
			mainController.updateMenuWithLogout();
		}		
	}	
}


 