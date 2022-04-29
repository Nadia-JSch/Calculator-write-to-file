package task1;

import javax.swing.JOptionPane;
import java.io.*;

public class Calculator {

	// create the calculation methods
	// addition method
	public static double addition(double num1, double num2) {
		return num1 + num2;
	}
	
	// subtraction method
	public static double subtraction(double num1, double num2) {
		return num1 - num2;
	}
	
	// multiplication method 
	public static double multiply(double num1, double num2) {
		return num1 * num2;
	}
	
	// division method
	public static double divide(double num1, double num2) {
		return num1 / num2;
	}
	
	// main method 
	public static void main(String[] args) {
		
		// declare variables to hold input
		Double input1 = null;
		Double input2 = null;
		String operation = null;
		PrintWriter pw;
		String fullSum;
		
		// == get the first number ==
		// keep requesting input until it's valid (can be parsed into a double)
		while (input1 == null) {
			
			// ask for input and read from console
			String scanned1 = JOptionPane.showInputDialog("Please enter the first number: ");
			
			
			// take input as String and try parse to a double to prevent errors
			// if it fails the variable will remain as null and the while loop continues
			try {
				input1 = Double.parseDouble(scanned1);
				
			// if there isn't a match print a message and request input again
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, scanned1 + " is not a valid number");
			} 
		}
		
		// == get the operation to perform ==
		// while loop to continue until user enters a valid operation
		Boolean isValidOperation = false;
		while (!isValidOperation) {
			// request input and read from console
			operation = JOptionPane.showInputDialog("""
					Enter an operator: 
					+ \t(add)
					- \t(subtract) 
					* \t(multiply) 
					/ \t(divide)
					""");
			
			// prevent error if user closes the dialogue box before entering a valid operator
			if (operation != null) {
			// check if the user input matches one of the operations and if so end the while loop
			switch (operation) {
				case "+", "-", "/", "*" -> isValidOperation = true;
				
				// if there isn't a match display a message
				default -> JOptionPane.showMessageDialog(null, operation + " is not a valid operation");
				}
			}
		}
		
		// == get the second number ==
		// keep requesting input until it's one that can be parsed to a double
		while (input2 == null) {
			String scanned2 = JOptionPane.showInputDialog("Please enter a second number");
			try {
				input2 = Double.parseDouble(scanned2);
			
			// print error message if input can't be parsed
			} catch (Exception f) {
				JOptionPane.showMessageDialog(null, scanned2 + " is not a valid number");
			} 
		}
		
		// === try block for writing to file	
		try {
			// create writer object and file and add true argument to write on a new line
			pw = new PrintWriter(new FileWriter("output.txt", true));
			
			
			// === main logic to call corresponding calculation method ===
			if (operation.equals("+")) {
				// store sum in a variable to display to user and write to file
				fullSum = input1 + " + " + input2 + " = " + addition(input1, input2);
				
				JOptionPane.showMessageDialog(null, fullSum);
				pw.write(fullSum + "\r\n");
			}
			else if (operation.equals("-")) {
				fullSum = input1 + " - " + input2 + " = " + subtraction(input1, input2);
				
				JOptionPane.showMessageDialog(null, fullSum);
				pw.write(fullSum + "\r\n");
				
			}
			else if (operation.equals("*")) {
				fullSum = input1 + " * " + input2 + " = " + multiply(input1, input2);
				
				JOptionPane.showMessageDialog(null, fullSum);
				pw.write(fullSum + "\r\n");
			}
			
			else if (operation.equals("/")) {
				fullSum = input1 + " / " + input2 + " = " + divide(input1, input2);
				
				JOptionPane.showMessageDialog(null, fullSum);
				pw.write(fullSum + "\r\n");
			}
			
			// display error message if something goes wrong
			else {
				JOptionPane.showMessageDialog(null, "Please try again");
			}
			
			// close the file object
			pw.close();
		}
		// display error message and trace back if there's an exception
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error writing to file");
		}
	}
}