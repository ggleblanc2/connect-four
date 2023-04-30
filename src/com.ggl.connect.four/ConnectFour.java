package com.ggl.connect.four;

import javax.swing.SwingUtilities;

import com.ggl.connect.four.model.ConnectFourModel;
import com.ggl.connect.four.view.ConnectFourFrame;

public class ConnectFour implements Runnable {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new ConnectFour());
	}

	@Override
	public void run() {
		new ConnectFourFrame(new ConnectFourModel());
	}

}
