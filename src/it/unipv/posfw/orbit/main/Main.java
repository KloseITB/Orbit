package it.unipv.posfw.orbit.main;

import java.awt.EventQueue;

import it.unipv.posfw.orbit.gui.*;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PublishingPage frame = new PublishingPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
