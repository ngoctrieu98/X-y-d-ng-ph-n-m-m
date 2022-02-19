package views.dangnhap;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

import views.MainScreen;

public class GUISplashScreen extends JFrame {
	private JProgressBar progressBar;
	private JLabel lblLogo;
	private JLabel lblName1;
	private JLabel lblName2;
	private JLabel lblDeverloper;

	public GUISplashScreen() {
		this.setSize(500, 250);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
		this.setResizable(false);
		this.setUndecorated(true);
		this.addUI();
	}

	private void addUI() {
		final Box b = Box.createVerticalBox();
		final Box b2 = Box.createHorizontalBox();
		final Box b3 = Box.createVerticalBox();
		final Box b4 = Box.createVerticalBox();
		final Box b5 = Box.createHorizontalBox();
		final Box b6 = Box.createHorizontalBox();
		(this.lblLogo = new JLabel(new ImageIcon(".\\image\\ic_logo.png")))
				.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK),
						this.lblLogo.getBorder()));
		b3.add(this.lblLogo);
		this.lblName1 = new JLabel("VIDEO RENTAL STORE SYSTEM ");
		this.lblName2 = new JLabel("Version 1");
		final Font font = new Font("Monospaced", 3, 13);
		this.lblName1.setFont(font);
		this.lblName2.setFont(font);
		b4.add(this.lblName1);
		b4.add(this.lblName2);
		b2.add(b3);
		b2.add(Box.createRigidArea(new Dimension(2, 50)));
		b2.add(b4);
		(this.progressBar = new JProgressBar()).setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		this.progressBar.setStringPainted(true);
		this.progressBar.setForeground(Color.BLACK);
		this.progressBar.setBackground(Color.YELLOW);
		b5.setMaximumSize(new Dimension(400, 70));
		b5.add(this.progressBar);
		(this.lblDeverloper = new JLabel("Chương trình phát triển bởi nhóm 14"))
				.setFont(font);
		b6.add(this.lblDeverloper);
		b6.add(Box.createHorizontalGlue());
		b.add(Box.createVerticalStrut(50));
		b.add(b2);
		b.add(b5);
		b.add(b6);
		this.add(b);
		b.setBackground(new Color(250, 250, 250));
		b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		b.setOpaque(true);
	}

	public String runProgressbar() {
		int x = 0;
		while (x <= 100) {
			this.progressBar.setValue(x);
			++x;
			try {
				Thread.sleep(10);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		return "done";
	} 

	public static void main(final String[] args) {
		try {
			GUISplashScreen gui = new GUISplashScreen();
			UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
			gui.setVisible(true);
			MainScreen mainScreen = new MainScreen();
			if (gui.runProgressbar().equals("done")) {
				gui.dispose();
				mainScreen.setVisible(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	
		
	}
}