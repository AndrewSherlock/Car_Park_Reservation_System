import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;

/**
 * @author Andrew, Ken Project -Team wrecked-
 * @version 2.1
 * Date 3-17
 * This Class is making the GUI and Implanting actionListeners 
 *
 */
public class CarParkReservationController extends JFrame implements ActionListener{

	
	CardLayout card = new CardLayout();
	JPanel cardsArea;
	DatabaseController db = new DatabaseController();
	MenuController menu;
	
	JTextField userNameField;
	JPasswordField passwordField;
	JLabel userNameLabel;
	JButton login;
	
	public CarParkReservationController()
	{
		getContentPane().add(topOfScreenInformation(), BorderLayout.NORTH);
		String[] opt = {"Home","Reserve","Map","Register","Login"};
		getContentPane().add(createCardPanel(), BorderLayout.CENTER);
		menu = new MenuController(false,opt, cardsArea, false, this);
		getContentPane().add(menu, BorderLayout.WEST);
		getContentPane().add(buildLowerPanel(), BorderLayout.SOUTH);
		setSize(800, 650);
		setVisible(true);
	}
		
	JPanel topOfScreenInformation()
	{
		JPanel topContainer = new JPanel();
		topContainer.setLayout(new BorderLayout());
		
		topContainer.add(userInfoPanel(), BorderLayout.EAST);
		topContainer.add(buildHeader());
		return topContainer;
	}
	
	JPanel buildLowerPanel()
	{
		JPanel lowerPanel = new JPanel();
		JLabel bottomInformation = new JLabel("bottom text");
		bottomInformation.setHorizontalAlignment(JLabel.CENTER);
		bottomInformation.setFont(new Font("Merriweather",Font.BOLD, 20));
		lowerPanel.add(bottomInformation);
		return lowerPanel;
	}
	
	JPanel userInfoPanel()
	{
		JPanel userInformation = new JPanel();
		userNameLabel = new JLabel("Not Signed In");
		userNameLabel.setHorizontalAlignment(JLabel.RIGHT);
		
		userInformation.add(userNameLabel);
		return userInformation;
	}
	
	JPanel buildHeader()
	{
		JPanel header = new JPanel();
		JLabel heading = new JLabel("Car Park Reservation System");
		heading.setFont(new Font("Merriweather",Font.BOLD, 40));
		heading.setHorizontalAlignment(JLabel.CENTER);
		header.add(heading);
		return header;
	}
	
	JPanel createCardPanel()
	{
		JPanel cardContainer = new JPanel();
		cardContainer.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		cardsArea = new JPanel();
		cardsArea.setLayout(card);
		cardsArea.add(openingCard(), "OpeningCard");
		cardsArea.add(loginScreen(), "LoginScreen");
		cardsArea.add(new RegisterSystem(false, cardsArea), "RegisterScreen");
		
		cardContainer.add(cardsArea);
		return cardContainer;
		
	}
	
	JPanel openingCard()
	{
		JPanel openingCard = new JPanel();
		openingCard.setLayout(new BorderLayout());
		
		JLabel welcome = new JLabel("Welcome to E-Parks");
		welcome.setFont(new Font("Merriweather",Font.BOLD, 25));
		welcome.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel imageLabel = new JLabel();
		imageLabel.setHorizontalAlignment(JLabel.CENTER);
		Icon icon = new ImageIcon("apple-touch-icon.png");
		imageLabel.setIcon(icon);
		
		openingCard.add(welcome, BorderLayout.NORTH);
		openingCard.add(imageLabel);
		
		return openingCard;
	}
	
	JPanel loginScreen()
	{
		JPanel loginScreen = new JPanel();
		//loginScreen.setLayout(new BoxLayout(loginScreen, BoxLayout.Y_AXIS));
		
		JPanel loginInformationContainer = new JPanel();
		loginInformationContainer.setLayout(new GridLayout(3,2));
		loginInformationContainer.add(new JLabel("User Name : "));
		
		userNameField = new JTextField(20);
		loginInformationContainer.add(userNameField);
		
		loginInformationContainer.add(new JLabel("Password : "));
		passwordField = new JPasswordField(20);
		loginInformationContainer.add(passwordField);
		loginInformationContainer.add(loginButtons());
		
		loginScreen.add(loginInformationContainer);
		
		return loginScreen; 
	}
	
	JPanel loginButtons()
	{
		JPanel buttonContainer = new JPanel();
		buttonContainer.setLayout(new BoxLayout(buttonContainer,BoxLayout.X_AXIS));
		
		JButton confirm = new JButton("Login User");
		confirm.addActionListener(this);
		
		JButton reset = new JButton("Reset");
		reset.addActionListener(this);
		
		buttonContainer.add(confirm);
		buttonContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		buttonContainer.add(reset);
		
		return buttonContainer;
	}
	
	

	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if(e.getActionCommand() == "Login" && userNameLabel.getText() == "Not Signed In")
		{				
			card.show(cardsArea, "LoginScreen");
		}
		
		if(e.getActionCommand() == "Register" && userNameLabel.getText() == "Not Signed In")
		{
			card.show(cardsArea, "Register");
		}
		
		if(e.getActionCommand() == "Login User")
		{
			String pass = UtilityFunctions.buildPasswordToString(passwordField.getPassword());
			String loggedInUser = db.LoginUser(userNameField.getText(), pass);
			
			if(loggedInUser == null)
			{
				JOptionPane.showMessageDialog(getContentPane(), "Login Failed, Wrong Username or password. Please Try Again","Login Failed", JOptionPane.WARNING_MESSAGE);
			} else{
				JOptionPane.showMessageDialog(getContentPane(), "Welcome " + loggedInUser + ", You were successfully logged in","Logged in successfully", JOptionPane.DEFAULT_OPTION);
				userNameLabel.setText(loggedInUser);
				card.show(cardsArea, "OpeningCard");
				updateMenuWithLogin();
			}
		}
		
		if(e.getActionCommand() == "Logout")
		{
			JOptionPane.showMessageDialog(getContentPane(), "Goodbye " + userNameLabel.getText() + ", You were logged out successfully","Thank you", JOptionPane.DEFAULT_OPTION);
			userNameLabel.setText("Not Signed In");
			login.setText("Login");			
		}
	}
	
	void updateMenuWithLogin()
	{
	
		String opt[] = {"Home","Reserve","Map","Account","Logout"};
		getContentPane().remove(menu);
		menu = new MenuController(true, opt, cardsArea, db.checkForStaffPrivilage(userNameLabel.getText()), this);
		getContentPane().add(menu, BorderLayout.WEST);
		getContentPane().repaint();
		getContentPane().validate();
	}
	
	public void updateMenuWithLogout()
	{
		JOptionPane.showMessageDialog(getContentPane(), "Goodbye " + userNameLabel.getText() + ", You were logged out successfully","Thank you", JOptionPane.DEFAULT_OPTION);
		userNameLabel.setText("Not Signed In");	
		String opt[] = {"Home","Reserve","Map","Account","Login"};
		getContentPane().remove(menu);
		menu = new MenuController(false, opt, cardsArea, false, this);
		getContentPane().add(menu, BorderLayout.WEST);
		getContentPane().repaint();
		getContentPane().validate();
		
	}
}
