package views.phitrehan;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import daos.CRUDInterface;
import daos.ChiTietPhieuThueDAO;
import entities.ChiTietPhieuThue;
import entities.KhachHang;
import impls.ChiTietPhieuThueImpl;
import impls.KhachHangImpl;
import views.tradia.TraDiaPanel;

public class GUIThanhToanPhiTreHan extends JFrame implements ActionListener{
	JLabel lbTieuDe;
	//KhachHang
	 JLabel lbTen,lbMa,lbSdt,lbDiaChi;
	 JTextField txtTen, txtMa, txtSdt, txtDiaChi;
	//Tien No
	JLabel lbTongTienNo, lbSoTienTra,lbTienDu,lbDonVi,lbTienNo,lbTienDuFill;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = screenSize.width;
	int height = screenSize.height;
	JButton btnTra, btnXemChiTiet;
	Font fontLb = new Font("SansSerif", Font.HANGING_BASELINE, 20);
	
	JTextField txtTienTra;
	int maKH;
	public GUIThanhToanPhiTreHan(int maKH) {
		this.maKH = maKH;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 600;
		int height = 700;
		setSize(width, height);
		setLocationRelativeTo(null);
		
		Box bc,b1,b2,bc2;
		bc = Box.createVerticalBox();
		add(bc);
		bc.add(Box.createVerticalStrut(30));
		bc.add(b1 = Box.createHorizontalBox());
			b1.add(lbTieuDe = new JLabel("Thanh Toán Phí Trễ Hạn"));
			Font font1 = new Font("SansSerif", Font.BOLD, 30);
			lbTieuDe.setFont(new Font("times new roman", Font.BOLD, 50));
			lbTieuDe.setForeground(new Color(0xFFAA00));
			b1.add(Box.createHorizontalStrut(width-700));
			
			//Thôgn tin khách hàng
		bc.add(Box.createVerticalStrut(15));
		bc.add(bc2 = Box.createHorizontalBox());
			bc2.add(Box.createHorizontalStrut(50));
			bc2.add(b1 = Box.createVerticalBox());
			b1.setBorder(BorderFactory.createLineBorder(Color.red));
			TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED),
					"Thông tin khách hàng");
				b1.setBorder(title);
				b1.add(b2 = Box.createHorizontalBox());
					b2.add(Box.createHorizontalStrut(20));
					b2.add(lbMa = new JLabel("Mã khách hàng:"));
					b2.add(Box.createHorizontalStrut(15));
					b2.add(txtMa = new JTextField());
					b2.add(Box.createHorizontalStrut(20));
				b1.add(Box.createVerticalStrut(15));
				b1.add(b2 = Box.createHorizontalBox());
					b2.add(Box.createHorizontalStrut(20));
					b2.add(lbTen = new JLabel("Tên khách hàng:"));
					b2.add(Box.createHorizontalStrut(10));
					b2.add(txtTen = new JTextField());
					b2.add(Box.createHorizontalStrut(20));
				b1.add(Box.createVerticalStrut(15));
				b1.add(b2 = Box.createHorizontalBox());
					b2.add(Box.createHorizontalStrut(20));
					b2.add(lbSdt = new JLabel("Số điện thoại:"));
					b2.add(Box.createHorizontalStrut(35));
					b2.add(txtSdt = new JTextField());
					b2.add(Box.createHorizontalStrut(20));
				b1.add(Box.createVerticalStrut(15));
				b1.add(b2 = Box.createHorizontalBox());
					b2.add(Box.createHorizontalStrut(20));
					b2.add(lbDiaChi = new JLabel("Địa chỉ:"));
					b2.add(Box.createHorizontalStrut(88));
					b2.add(txtDiaChi = new JTextField());
					b2.add(Box.createHorizontalStrut(20));
				b1.add(Box.createVerticalStrut(15));
			bc2.add(Box.createHorizontalStrut(50));

		bc.add(Box.createVerticalStrut(15));
		bc.add(b1 = Box.createVerticalBox());
			b1.add(b2 = Box.createHorizontalBox());
			//	b2.add(Box.createHorizontalStrut(100));
				b2.add(lbTongTienNo = new JLabel("Tổng tiền nợ: "));
				b2.add(Box.createHorizontalStrut(5));
				b2.add(lbTienNo = new JLabel("000.000"));
				b2.add(Box.createHorizontalStrut(5));
				b2.add(lbDonVi = new JLabel("VNĐ"));
				lbDonVi.setFont(fontLb);
				b2.add(Box.createHorizontalStrut(140));


			b1.add(Box.createVerticalStrut(10));
			b1.add(b2 = Box.createHorizontalBox());
				b2.add(Box.createHorizontalStrut(100));
				b2.add(lbSoTienTra = new JLabel("Số tiền trả: "));
				b2.add(Box.createHorizontalStrut(5));
				b2.add(txtTienTra = new JTextField());
				b2.add(Box.createHorizontalStrut(5));
				b2.add(lbDonVi = new JLabel("VNĐ"));
				lbDonVi.setFont(fontLb);
				b2.add(Box.createHorizontalStrut(200));

			b1.add(Box.createVerticalStrut(10));
			b1.add(b2 = Box.createHorizontalBox());
				b2.add(Box.createHorizontalStrut(350));
				b2.add(btnTra = new JButton("Trả", new ImageIcon(".\\image\\ic_pay.png")));
			//	b2.add(Box.createHorizontalStrut(100));
			b1.add(Box.createVerticalStrut(10));
			b1.add(b2 = Box.createHorizontalBox());
				b2.add(lbTienDu = new JLabel("Tiền dư: "));
				b2.add(Box.createHorizontalStrut(5));
				b2.add(lbTienDuFill = new JLabel("0.000"));
				b2.add(Box.createHorizontalStrut(5));
				b2.add(lbDonVi = new JLabel("VNĐ"));
				b2.add(Box.createHorizontalStrut(200));
		bc.add(Box.createVerticalStrut(20));
		bc.add(b1 = Box.createVerticalBox());
		b1.add(b2 = Box.createHorizontalBox());
			b2.add(Box.createHorizontalStrut(150));
			b2.add(btnXemChiTiet = new JButton("Xem Chi Tiết", new ImageIcon(".\\image\\ic_detail.png")));
			b2.add(Box.createHorizontalStrut(150));
		
		bc.add(Box.createVerticalStrut(50));
		
		txtSdt.setEnabled(false);
		txtTen.setEnabled(false);
		txtDiaChi.setEnabled(false);
		//Set Font Label
		SetFontLabel(Arrays.asList(lbMa,lbTienDuFill,lbTen,lbDiaChi,lbSdt,lbTienDu,lbTienNo,lbSoTienTra,lbTongTienNo,lbDonVi));
		setVisible(true);
		showData();
		btnTra.addActionListener(this);
	}
	private void showData() {
		// TODO Auto-generated method stub
		//int maKH = TraDiaPanel.maKH;
		//int maKH = Integer.parseInt(txtMa.getText().toString());
		CRUDInterface<KhachHang> crudInterface = new KhachHangImpl();
		KhachHang kh = crudInterface.timBangMa(maKH);
		txtMa.setText(kh.getMa()+"");
		txtDiaChi.setText(kh.getDiaChi()+"");
		txtTen.setText(kh.getTen()+"");
		txtSdt.setText(kh.getSdt()+"");
		lbTienNo.setText(kh.getPhiTreHan()+"");
	}
	private void SetFontLabel(List<JLabel> listLabel) {
		listLabel.forEach(x->{
			x.setFont(fontLb);
		});
	}
	
	public void setMaKH(int ma) {
		txtMa.setText(ma+"");
	}
	private void traTienNo() {
		ChiTietPhieuThueDAO chiTietPhieuThueDAO = new ChiTietPhieuThueImpl();
		CRUDInterface<ChiTietPhieuThue> crudInterfaceCTPT = new ChiTietPhieuThueImpl();
		List<ChiTietPhieuThue> listCTPT  = chiTietPhieuThueDAO.getDSNoByKhachHangID(TraDiaPanel.maKH);
		int sum = 0, tienDu = 0;
		int maKH = TraDiaPanel.maKH;
		CRUDInterface<KhachHang> crudInterface = new KhachHangImpl();
		KhachHang kh = crudInterface.timBangMa(maKH);
		
		int tienTra = Integer.parseInt(txtTienTra.getText().toString());
		for(ChiTietPhieuThue ctpt : listCTPT) {
			sum+= ctpt.getBangDia().getPhiTreHan();
			if(sum <= tienTra) {
				tienDu = tienTra - sum;
				kh.setPhiTreHan(kh.getPhiTreHan()-sum);
				crudInterface.sua(kh);
				ctpt.setTinhTrang(ChiTietPhieuThue.KHONG_NO);
				crudInterfaceCTPT.sua(ctpt);
				JOptionPane.showMessageDialog(this, "Trả thành công");
			}
		}
		lbTienDuFill.setText(tienDu+"");
		
//		int[] val = new int[10000];
//		//KhachHang[] val1 = new KhachHang[10000];
//		int[] wt = new int[100000];
//		int i =0;
//		for(ChiTietPhieuThue ctpt : listCTPT) {
//			wt[i] = ctpt.getBangDia().getPhiTreHan();
//			val[i++] = 1;
//		}
//		System.out.println("hhuuhu: " +knapSack(Integer.parseInt(txtTienTra.getText().toString()), wt, val, val.length));
//		
		
	}
	public int knapSack(int W, int wt[], int val[], int n) {
		int i, w;
		int K[][] = new int[n + 1][W + 1];
		// Build table K[][] in bottom up manner
		for (i = 0; i <= n; i++) {
			for (w = 0; w <= W; w++) {
				if (i == 0 || w == 0)
					K[i][w] = 0;
				else if (wt[i - 1] <= w)
					K[i][w] = Math.max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);
				else
					K[i][w] = K[i - 1][w];
			}
		}

		return K[n][W];
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if(src.equals(btnTra)) {
			if(txtTienTra.getText().toString().equals("")) {
				JOptionPane.showMessageDialog(this, "Tiền trả không được rỗng!");
				txtTienTra.requestFocus();
				return;
			}
			if (!txtTienTra.getText().toString().trim().matches("[0-9]*")) {
				JOptionPane.showMessageDialog(this, "Mã là số!");
				txtTienTra.requestFocus();
				return;
			}
			traTienNo();
		}
	}

}
