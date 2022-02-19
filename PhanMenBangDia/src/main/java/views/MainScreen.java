package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import views.bangdia.QuanLyBangDiaPanel;
import views.baocao.BaoCaoPanel;
import views.dangnhap.GUILogin;
import views.dattruoc.QuanLyDatTruocPanel;
import views.khachhang.QuanLyKhachHangPanel;
import views.phitrehan.QuanLyPhiTreHanPanel;
import views.thuedia.ThueDiaPanel;
import views.tradia.TraDiaPanel;
import views.tuaphimgame.QuanLyPhimGamePanel;


public class MainScreen extends JFrame implements ChangeListener{
	JTabbedPane tabbedPane;
	public static int checkLogin = 0;
	public  static final int login = 1;
	public static final int logout = 0;

	public static BaoCaoPanel baoCaoPanel;
	public static QuanLyPhimGamePanel quanLyPhimGamePanel;
	
	QuanLyDatTruocPanel quanLyDatTruocPanel;

	public MainScreen() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		setLocationRelativeTo(null);
		// setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Quản Lý Băng Đĩa");
		UI();
	}

	private void UI() {
		tabbedPane = new JTabbedPane() {

			private static final long serialVersionUID = 1L;
			
			@Override
			public Dimension getPreferredSize() {
				int tabsWidth = 0;

                for (int i = 0; i < getTabCount(); i++) {
                    tabsWidth += getBoundsAt(i).width;
                }

                Dimension preferred = super.getPreferredSize();

                preferred.width = Math.max(preferred.width, tabsWidth);

                return preferred;
			}
			
		};
		JPanel panel1 = createJPanel("Chào mừng đến phần mềm Quản Lý Băng Đĩa");
		tabbedPane.addTab("", new ImageIcon("image\\ic_home.png"),panel1, "Gồm công việc thêm, xoá, sửa khách hàng trong hệ thống!");
		tabbedPane.addTab("Quản Lý Khách Hàng", new ImageIcon("image\\ic_qlkh.png"), new QuanLyKhachHangPanel(), "Gồm công việc thêm, xoá, sửa khách hàng trong hệ thống!");
		tabbedPane.addTab("Quản Lý Đĩa", new ImageIcon("image\\ic_disk.png"), new QuanLyBangDiaPanel(), "Gồm công việc thêm, xoá, sửa đĩa trong hệ thống!");
		tabbedPane.addTab("Quản Lý Tiêu Đề", new ImageIcon("image\\ic_game.png"),quanLyPhimGamePanel= new QuanLyPhimGamePanel(), "click to show panel 3");
		tabbedPane.addTab("Trả Đĩa", new ImageIcon("image\\ic_pay.png"),new TraDiaPanel(), "Trả Đĩa");
		tabbedPane.addTab("Thuê Đĩa", new ImageIcon("image\\ic_rent.png"),new ThueDiaPanel(), "Thuê Đĩa");
		tabbedPane.addTab("Quản Lý Phí Trễ Hạn", new ImageIcon("image\\ic_game.png"),new QuanLyPhiTreHanPanel(), "Quản Lý Phí Trễ Hạn");
		tabbedPane.addTab("Quản lý Đặt Trước", new ImageIcon("image\\ic_reserved.png"),quanLyDatTruocPanel = new QuanLyDatTruocPanel(), "Quản lý Đặt Trước");
		tabbedPane.addTab("Báo Cáo", new ImageIcon("image\\ic_report.png"),baoCaoPanel = new BaoCaoPanel(), "Báo Cáo");

		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);// ALT - 1
		add(tabbedPane);
		tabbedPane.addChangeListener(this);
	}

	private JPanel createJPanel(String text) {
		JPanel panel = new JPanel(new GridLayout(1, 1));
		JLabel lb = new JLabel(text);
		lb.setFont(new Font("times new roman", Font.BOLD, 50));
		lb.setForeground(new Color(0xFFAA00));
		lb.setHorizontalAlignment(JLabel.CENTER);
		panel.add(lb);
		return panel;
	}

	private JPanel createPane1() {
		JPanel panel = new JPanel();
		panel.add(new JScrollPane(createTextArea(10, 40)));
		return panel;
	}

	private JTextArea createTextArea(int row, int col) {
		JTextArea ta = new JTextArea(row, col);
		ta.setWrapStyleWord(true);
		ta.setLineWrap(true);
		ta.setForeground(Color.BLUE);
		return ta;
	}
	
	
	GUILogin guiLogin;
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

		int currentTabIndex = tabbedPane.getSelectedIndex();
		final boolean bIsVisible = tabbedPane.isEnabledAt( currentTabIndex );
		if ( bIsVisible && currentTabIndex==8) {
		    // Do stuff with myPanel
			baoCaoPanel.removeAll();
			baoCaoPanel.add(new BaoCaoPanel());
			baoCaoPanel.validate();
			
		}
		if ( bIsVisible && currentTabIndex==7) {
		    // Do stuff with myPanel
			baoCaoPanel.removeAll();
			baoCaoPanel.add(new QuanLyDatTruocPanel());
			baoCaoPanel.validate();
			
		}
		if(currentTabIndex == 8) {
			if(checkLogin==MainScreen.logout) {
				guiLogin = new GUILogin();
			}
		}else {
			if( guiLogin!=null) {
				if(guiLogin.isDisplayable())
					guiLogin.dispose();
			}

		}
	}

}
