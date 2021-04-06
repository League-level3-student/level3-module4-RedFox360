package _03_Hangman;

import java.util.Stack;

import javax.swing.*;

public class Hangman {
	JFrame frame;
	JPanel panel;
	JLabel label;
	Stack<String> words = new Stack<String>();
	public static void main(String[] args) {
		new Hangman();
	}
	Hangman() {
		frame = new JFrame("Hangman");
		panel = new JPanel();
		label = new JLabel("Starting hangman...");
		frame.setVisible(true);
		panel.add(label);
		frame.add(panel);
		frame.pack();
		int k = Integer.parseInt(JOptionPane.showInputDialog(frame,"Enter a number"));
		
		for(int i = 0; i < k; i++) {
			words.push(Utilities.readRandomLineFromFile("dictionary.txt"));
		}
	}
}