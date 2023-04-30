package com.ggl.connect.four.view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ggl.connect.four.model.ConnectFourModel;

public class ControlPanel {
	
	private final ConnectFourFrame view;
	
	private final ConnectFourModel model;
	
	private final JPanel panel;
	
	private JButton startGameButton;
	
	private JTextField redWinsField, yellowWinsField;

	public ControlPanel(ConnectFourFrame view, ConnectFourModel model) {
		this.view = view;
		this.model = model;
		this.panel = createControlPanel();
	}
	
	private JPanel createControlPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
		Font labelFont = panel.getFont().deriveFont(Font.BOLD, 16f);
		Font textFont = panel.getFont().deriveFont(Font.PLAIN, 16f);
		
		JLabel label = new JLabel("Yellow Wins:");
		label.setFont(labelFont);
		panel.add(label);
		
		yellowWinsField = new JTextField(4);
		yellowWinsField.setEditable(false);
		yellowWinsField.setFont(textFont);
		panel.add(yellowWinsField);
		
		panel.add(Box.createHorizontalStrut(25));
		
		startGameButton = new JButton("Restart Game");
		startGameButton.setEnabled(false);
		startGameButton.setFont(labelFont);
		panel.add(startGameButton);
		
		panel.add(Box.createHorizontalStrut(35));
		
		label = new JLabel("Red Wins:");
		label.setFont(labelFont);
		panel.add(label);
		
		redWinsField = new JTextField(4);
		redWinsField.setEditable(false);
		redWinsField.setFont(textFont);
		panel.add(redWinsField);
		
		updateControlPanel();
		
		startGameButton.addActionListener(event -> {
			model.resetGame();
			view.setWinningPieces(new Point[0]);
			view.repaint();
			startGameButton.setEnabled(false);
		});
		
		return panel;
	}
	
	public void updateControlPanel() {
		yellowWinsField.setText(Integer.toString(model.getYellowWins()));
		redWinsField.setText(Integer.toString(model.getRedWins()));
	}

	public JButton getStartGameButton() {
		return startGameButton;
	}

	public JPanel getPanel() {
		return panel;
	}

}
