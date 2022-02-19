package views.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

public class Toast extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4238793850685472939L;
	// JWindow
	JWindow w;

	Toast(JComponent caller, String s) {
		w = new JWindow();

		// make the background transparent
		w.setBackground(new Color(0, 0, 0, 0));

		Window window = SwingUtilities.getWindowAncestor(caller);
		int x = window.getLocationOnScreen().x + window.getWidth() / 2 - getWidth() / 2;
		int y = window.getLocationOnScreen().y + (int) ((double) window.getHeight() * 0.75) - getHeight() / 2;
		// create a panel
		JPanel p = new JPanel() {
			private static final long serialVersionUID = -6032647199039899129L;

			public void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D) g;
				int wid = g.getFontMetrics().stringWidth(s);
				int hei = g.getFontMetrics().getHeight();

				// draw the boundary of the toast and fill it
//				g.setColor(Color.black);
//				g.fillRect(10, 10, wid + 30, hei + 10);
				g.fillRoundRect(10, 10, wid + 30, hei + 10, 10, 10);
//				g.setColor(Color.black);
//				g.drawRect(10, 10, wid + 30, hei + 10);

				// set the color of text
				g.setColor(new Color(255, 255, 255, 240));
				g.drawString(s, 25, 27);
				int t = 250;

				// draw the shadow of the toast
				for (int i = 0; i < 4; i++) {
					t -= 60;
					g.setColor(new Color(0, 0, 0, t));
//					g.drawRect(10 - i, 10 - i, wid + 30 + i * 2, hei + 10 + i * 2);
					g2d.drawRoundRect(10 - i, 10 - i, wid + 30 + i * 2, hei + 10 + i * 2, 10, 10);
				}
			}
		};

		w.add(p);
		w.setLocation(x, y);
		w.setSize(300, 100);
	}

	public static Toast makeText(JComponent caller, String s) {
		return new Toast(caller, s);
	}

	// function to pop up the toast
	public void showtoast() {
		try {
			w.setOpacity(1);
			w.setVisible(true);

			// wait for some time
			Thread.sleep(2000);

			// make the message disappear slowly
			for (double d = 1.0; d > 0.2; d -= 0.1) {
				Thread.sleep(100);
				w.setOpacity((float) d);
			}

			// set the visibility to false
			w.setVisible(false);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
