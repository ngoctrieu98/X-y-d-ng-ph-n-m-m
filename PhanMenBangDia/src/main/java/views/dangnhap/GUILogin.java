package views.dangnhap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Base64;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import views.MainScreen;
import views.baocao.BaoCaoPanel;
import views.tuaphimgame.QuanLyPhimGamePanel;


public class GUILogin extends JFrame implements ActionListener,KeyListener{
	public static boolean logged =false;
	JLabel lbllogOn,lbluseName,lblpassWord,lblh1;
	JTextField txtuserName;
	JPasswordField txtpassWord;
	JButton btnlogOn,btnExit;
	public static int maNV;
	public GUILogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\image\\logo 	.png"));
		setTitle("Quản Lý Khách Sạn");
		setSize(600,385);
		setLocationRelativeTo(null);
        setAlwaysOnTop( true );
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel pNorth;
		add(pNorth = new JPanel(),BorderLayout.NORTH);
			pNorth.setBorder(BorderFactory.createLineBorder(Color.red));
			pNorth.add(lbllogOn = new JLabel("Truy Cập Tài Khoản Admin"));
			Font font1 = new Font("SansSerif", Font.BOLD, 30);
			lbllogOn.setFont(font1);
		JPanel pTrai;
		add(pTrai = new JPanel(),BorderLayout.WEST);
			pTrai.add(lblh1 = new JLabel(new ImageIcon(".\\image\\key.png")));
			pTrai.setBorder(BorderFactory.createLineBorder(Color.red));
			
		Box bp =Box.createVerticalBox();
		add(bp,BorderLayout.CENTER);
		bp.add(Box.createVerticalStrut(80));
		bp.setBorder(BorderFactory.createLineBorder(Color.red));
		bp.add(Box.createVerticalStrut(5));
	
		Box b1= Box.createHorizontalBox();
		bp.add(b1);
		b1.add(Box.createHorizontalStrut(10));
		b1.add(lbluseName = new JLabel("Tài Khoản"));
		b1.add(Box.createHorizontalStrut(10));
		b1.add(txtuserName = new JTextField());
		b1.add(Box.createHorizontalStrut(50));
		
		bp.add(Box.createVerticalStrut(15));
		Box b2= Box.createHorizontalBox();
		bp.add(b2);
		b2.add(Box.createHorizontalStrut(10));
		b2.add(lblpassWord = new JLabel("Mật Khẩu"));
		b2.add(Box.createHorizontalStrut(13));
		b2.add(txtpassWord = new JPasswordField());
		b2.add(Box.createHorizontalStrut(50));
		
		bp.add(Box.createVerticalStrut(80));
		
		Box b3 = Box.createVerticalBox();
		add(b3,BorderLayout.SOUTH);
		b3.add(Box.createVerticalStrut(10));
		Box bd = Box.createHorizontalBox();
		b3.add(bd);
		bd.add(Box.createHorizontalStrut(100));
		b3.setBorder(BorderFactory.createLineBorder(Color.red));
		bd.add(btnlogOn = new JButton("Đăng Nhập",new ImageIcon(".\\image\\login.png")));
		btnlogOn.setMaximumSize(getPreferredSize());
		bd.add(Box.createHorizontalStrut(10));
		bd.add(btnExit = new JButton("Thoát",new ImageIcon(".\\image\\logout.png")));
		btnExit.setMaximumSize(getPreferredSize());
		bd.add(Box.createHorizontalStrut(100));
		b3.add(Box.createVerticalStrut(10));
			
		
		//Đăng Ký Sự Kiện
		btnlogOn.addActionListener(this);
		btnExit.addActionListener(this);
		txtpassWord.addKeyListener(this);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
    		new GUILogin();
        }catch (Exception e) {
        	e.printStackTrace();
		}
	}

	
	//0 là QUản lý - 1 là nhân viên
	public void Logon () {
		try{
			String b= new String(txtpassWord.getPassword());
			byte[] bytes = b.getBytes("UTF-8");
			String encoded = Base64.getEncoder().encodeToString(bytes);
			if(Pattern.matches("(^admin)",txtuserName.getText()))
				if(b.equalsIgnoreCase("admin")) {
					//GUIMenu guiMenu = new GUIMenu(0);
					MainScreen.checkLogin = MainScreen.login;
					logged = true;
					MainScreen.baoCaoPanel.removeAll();
					MainScreen.baoCaoPanel.add(new BaoCaoPanel());
					MainScreen.baoCaoPanel.validate();
					
					MainScreen.quanLyPhimGamePanel.removeAll();
					MainScreen.quanLyPhimGamePanel.add(new QuanLyPhimGamePanel());
					MainScreen.quanLyPhimGamePanel.validate();

					dispose();
					return;
				}
				else
					JOptionPane.showMessageDialog(this,"Nhập Sai Mật Khẩu");
			else {
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,"Không Được Để Trống");
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnExit))
			this.dispose();
		if(o.equals(btnlogOn))
			Logon();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			Logon();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
