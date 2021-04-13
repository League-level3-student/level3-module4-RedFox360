package _03_Hangman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

import javax.swing.*;

public class Hangman implements KeyListener {
	JFrame frame;
	JPanel panel;
	JLabel label, livesLabel;
	Stack<String> words = new Stack<String>();
	int lives = 10;

	public static void main(String[] args) {
		new Hangman();
	}

	Hangman() {
		frame = new JFrame("Hangman");
		panel = new JPanel();
		label = new JLabel("Starting hangman...");
		livesLabel = new JLabel("Lives: " + lives);
		frame.setVisible(true);
		panel.add(label);
		panel.add(livesLabel);
		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int k = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter a number"));

		for (int i = 0; i < k; i++) {
			String randomLine = Utilities.readRandomLineFromFile("dictionary.txt");
			if (!words.contains(randomLine))
				words.push(randomLine);
		}
		String last = words.lastElement();
		label.setText("");
		for (int i = 0; i < last.length(); i++) {
			label.setText(label.getText() + "_");
		}

		frame.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		String last = words.lastElement();
		char key = arg0.getKeyChar();
		boolean doesnt = true;
		for (int i = 0; i < last.length(); i++) {
			if (key == last.charAt(i)) {
				doesnt = false;
				char[] text = label.getText().toCharArray();
				text[i] = last.charAt(i);
				label.setText(new String(text));
			}
		}
		if (!label.getText().contains("_")) {
			JOptionPane.showMessageDialog(frame, "You guessed the word! Continue...");
			words.pop();
			if (words.empty()) System.exit(0);
			lives = 10;
			livesLabel.setText("Lives: " + lives);
			String newLast = words.lastElement();
			label.setText("");
			for (int i = 0; i < newLast.length(); i++) {
				label.setText(label.getText() + "_");
			}
		}
		if (doesnt) {
			lives--;
			livesLabel.setText("Lives: " + lives);
			if (lives == 0) {
				JOptionPane.showMessageDialog(frame, "You ran out of lives! The word was " + last);
				System.exit(0);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}