package views.baocao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import views.dangnhap.GUILogin;

public class BaoCaoPanel extends JPanel implements ActionListener {
	JTabbedPane tabbedPane;
	public JButton btnQLKH,btnQLBD;
	static Font fontChu = new Font("SansSerif", 2, 20);
	JLabel lbTieuDeHeThong;
	JPanel pnMain = new JPanel();
	private JPanel childPanel;
	
	public BaoCaoPanel() {
		initData();
		guiUI();
		regisAction();
	}

	private void regisAction() {
		// TODO Auto-generated method stub
		btnQLBD.addActionListener(this);
		btnQLKH.addActionListener(this);
	}

	private void initData() {
		// TODO Auto-generated method stub

	}

	private void guiUI() {
		// TODO Auto-generated method stub
		Box bc, b1, b2, bLeft, bRight;
		bc = Box.createHorizontalBox();
		add(bc);
	    bc.add(Box.createHorizontalStrut(20));
		bc.add(b1 = Box.createVerticalBox());
			JPanel box = new JPanel(new GridLayout(6, 1));
			b1.add(Box.createVerticalStrut(50));
			btnQLKH = new JButton("Báo cáo khách hàng", new ImageIcon(".\\image\\ic_customer_report.png"));
			btnQLKH.setFont(fontChu);
			b1.add(Box.createVerticalStrut(30));
			btnQLBD = new JButton("Báo cáo băng đĩa", new ImageIcon(".\\image\\ic_dvd.png"));
			btnQLBD.setFont(fontChu);
			box.add(lbTieuDeHeThong = new JLabel("Báo cáo"));
			lbTieuDeHeThong.setFont(new Font("times new roman", 1, 50));
			lbTieuDeHeThong.setForeground(new Color(0xFFAA00));
			box.add(btnQLKH);
			box.add(new JPanel());
			box.add(btnQLBD);
			box.add(new JPanel());
			box.add(new JPanel());
			b1.add(box);


		 bc.add(Box.createHorizontalStrut(20));
		 JSeparator s = new JSeparator();   
	     s.setOrientation(SwingConstants.VERTICAL); 
	     bc.add(s);
	     
	     bc.add(Box.createHorizontalStrut(20));
	   //  bc.add(b1 = Box.createHorizontalBox());
	     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int width = screenSize.width;
			int height = screenSize.height;
			bc.add(pnMain, BorderLayout.CENTER);//thay đổi pnMain thành GUI khác
				pnMain.setBackground(new Color(153, 204, 255));
				
				 // pnMain.setBounds(0, 0, 5000, 5000); 
				  pnMain.setPreferredSize(new
				  Dimension(width-520, height-150)); 
				  pnMain.setLayout(new BorderLayout(0,0)); 
				  pnMain.setSize(width-520,height-150);
	   
	       if(GUILogin.logged==false) {
	    	   btnQLBD.setEnabled(false);
	    	   btnQLKH.setEnabled(false);
	       }else {
	    	   btnQLBD.setEnabled(true);
	    	   btnQLKH.setEnabled(true);
	       }
	}
	public void showPanel(JPanel panel) {
		childPanel = panel;
		pnMain.removeAll();
		pnMain.add(childPanel);
		pnMain.validate();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (src.equals(btnQLBD)) {
			showPanel(new BaoCaoBangDiaPanel(this));
		}
		if(src.equals(btnQLKH)) {
			System.out.println("aaa");
			showPanel(new BaoCaoKhachHangPanel(this));
		}
	}


}
