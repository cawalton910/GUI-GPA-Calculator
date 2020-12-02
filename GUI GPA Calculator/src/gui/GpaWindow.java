/**
 * ---------------------------------------------------------------------------
 * File name: GpaWindow.java
 * Project name: GUI GPA Calculator
 * ---------------------------------------------------------------------------
 * Creator's name and email: Chase Walton, waltonca@etsu.edu
 * Course:  CSCI 1260
 * Creation Date: Nov 17, 2020
 * ---------------------------------------------------------------------------
 */

package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
/**
 * Class the manages and creates a GPA Calculator
 *
 * <hr>
 * Date created: Nov 17, 2020
 * <hr>
 * @author Chase Walton
 */
public class GpaWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel upper;				//Upper panel. Consists of input/output
	private JPanel lower;				//Lower Panel. Consists the two buttons
	private JLabel creditsEarnedLabel;
	private JLabel gpaLabel;
	private JLabel hoursCompletedLabel;
	private JTextField creditsEarnedText;
	private JTextField gpaText;
	private JTextField hoursCompletedText;
	private JButton calcButton;
	private JButton quitButton;			
	private final int WINDOW_WIDTH = 240;		//Windows Width
	private final int WINDOW_HEIGHT = 180;		//Windows Height
	private DecimalFormat gpaFormatter = new DecimalFormat("#.##");	//Formats the GPA result to (x.xx)
	
	/**
	 * Constructor        
	 *
	 * <hr>
	 * Date created: Nov 17, 2020 
	 *
	 * 
	 */
	public GpaWindow() {
		super("GPA");							//call the super constructor and names the pane GPA
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);			//sets window size
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setLayout(new BorderLayout());			//sets the layout to a Border Layout
		buildUpper();							//creates the upper panel
		add(upper, BorderLayout.CENTER);		//adds the upper panel to the border layout
		buildLower();							//creates the lower panel
		add(lower, BorderLayout.SOUTH);			//adds the lower panel to the border layout
		
		
		getRootPane().setDefaultButton(calcButton);		//sets the default button the Calculate button
		setLocationRelativeTo(null);					//centers the window
		setVisible(true);								//sets to visible
		
	}
	
	/**
	 * Creates the upper panel, 3x2 grid layout with 3 text fields and 3 labels.      
	 *
	 * <hr>
	 * Date created: Nov 17, 2020
	 *
	 * <hr>
	 */
	private void buildUpper() {
		upper = new JPanel();									//Create the upper panel.
		upper.setLayout(new GridLayout(3,2));					//Set the layout to a 3x2 Grid Layout.
		creditsEarnedLabel = new JLabel("Credits Earned");		//New JLabel for Credits Earned
		creditsEarnedText = new JTextField(10);					//New TextField for credits earned input.
		creditsEarnedText.addFocusListener(new creditsEarnedFocus());	//Manages the focus
		hoursCompletedLabel = new JLabel("Hours Completed");	//New JLabel for Hours
		hoursCompletedText = new JTextField(10);				//New TextField for Hours
		hoursCompletedText.addFocusListener(new hoursCompletedFocus());		//Manages the focus
		gpaLabel = new JLabel("GPA");		//new JLabel for GPA
		gpaText  = new JTextField(10);		//new TextField for GPA
		gpaText.setEditable(false);			//Set editable to false
		upper.add(creditsEarnedLabel);		//add Label to panel
		upper.add(creditsEarnedText);		//add TextField to panel
		upper.add(hoursCompletedLabel);		//add Label to panel
		upper.add(hoursCompletedText);		//add TextField to panel
		upper.add(gpaLabel);				//add Label to panel
		upper.add(gpaText);					//add TextField to panel
		creditsEarnedText.setHorizontalAlignment(JTextField.RIGHT);		//Set the cursor to the right side
		hoursCompletedText.setHorizontalAlignment(JTextField.RIGHT);	//Set the cursor to the right side
		gpaText.setHorizontalAlignment(JTextField.RIGHT);				//Set the cursor to the right side
		setLocationRelativeTo(null);		//center panel
		setVisible(true);					//set visible
		setResizable(false);				//set resizable to false
		
	}
	
	/**
	 * Creates the lower panel, flow layout with two buttons
	 * <hr>
	 * Date created: Nov 17, 2020
	 *
	 * <hr>
	 */
	private void buildLower() {
		lower = new JPanel();					//New JPanel
		lower.setLayout(new FlowLayout());		//Sets the layout to a Flow Layout
		calcButton = new JButton("Calculate");	//Creates the button Calculate
		calcButton.addActionListener(new calcButtonListener());		//Adds the calculate action listener to the button
		quitButton = new JButton("Quit");		//Creates the Quit button/
		quitButton.addActionListener(new quitButtonListener());		//Adds the quit action listener to the Quit button
		
		lower.add(calcButton);	//adds the calculate button to the panel
		lower.add(quitButton);	//adds the quit button to the panel
		setLocationRelativeTo(null);		//center panel
		setVisible(true);					//set to visible
	}
	
	/**
	 * Action Listener for the Calculate button.
	 *
	 * <hr>
	 * Date created: Nov 17, 2020
	 * <hr>
	 * @author Chase Walton
	 */
	private class calcButtonListener implements ActionListener{
		public void actionPerformed (ActionEvent e) {
			
			try {
				double gpa = 0.0;
				int credits = Integer.parseInt(creditsEarnedText.getText()); //Retrieves the inputed credits and stores in "credits"
				int hours = Integer.parseInt(hoursCompletedText.getText());  //Retrieves the inputed hours and stores in "hours"
				if(hours!=0) {			//If statement that prevents a divide by zero error.	
					gpa = ((double) credits)/hours;
				}//end if
				else {
					gpa = 0;			//If a divide by zero error will occur set the gpa to 0
				}//end else
				if(gpa>4 || gpa<0) {	//If the Gpa calculated is not in the range of 0-4, this code will be executed
					JOptionPane.showMessageDialog(null,"Hours and credits yield invalid GPA");
				}//end if
				gpaText.setText(String.valueOf(gpaFormatter.format(gpa)));	//formats the result with the Decimal Formatter gpaFormatter and converts to a String. Sets the value to the gpa Text Field
			}//end try
			catch(Exception f) {		//If any input error may occur execute this code
				JOptionPane.showMessageDialog(null,"Please correct the data and try again");
			}//end catch
		}//end actionPerformed
	}//end calcButtonListener
	
	/**
	 * Action Listener for the Quit Button. Closes the program with a goodbye message
	 *
	 * <hr>
	 * Date created: Nov 17, 2020
	 * <hr>
	 * @author Chase Walton
	 */
	private class quitButtonListener implements ActionListener{
		public void actionPerformed (ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Goodbye");
			System.exit(0);										//System exit
		}//end actionPerformed
	}//end quitButtonListner
	
	/**
	 * Focus Listener for the Credits Earned Text field
	 *
	 * <hr>
	 * Date created: Nov 17, 2020
	 * <hr>
	 * @author Chase Walton
	 */
	private class creditsEarnedFocus implements FocusListener{
		public void focusGained(FocusEvent arg0) {
			creditsEarnedText.selectAll();					//Selects the text field
		}//end focusGained
		public void focusLost(FocusEvent arg0) {
			
		}//end focusLsot
	}//end creditsEarnedFocus
		
	/**
	 * Focus Listener for the Hours Completed Text Field
	 *
	 * <hr>
	 * Date created: Nov 17, 2020
	 * <hr>
	 * @author Chase Walton
	 */
	private class hoursCompletedFocus implements FocusListener{
		public void focusGained(FocusEvent arg0) {
			hoursCompletedText.selectAll();					//Selects the text field
		}//end focusGained
		public void focusLost(FocusEvent arg0) {
			
		}//end focusLost
	}//end hoursCompletedFocus

}//end class
