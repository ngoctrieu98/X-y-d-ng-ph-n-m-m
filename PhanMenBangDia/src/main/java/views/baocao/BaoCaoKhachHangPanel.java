package views.baocao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import daos.BangDiaDAO;
import daos.CRUDInterface;
import daos.ChiTietPhieuThueDAO;
import daos.KhachHangDAO;
import daos.PhieuThueDAO;
import entities.ChiTietPhieuThue;
import entities.KhachHang;
import entities.PhieuThue;
import impls.BangDiaImpl;
import impls.ChiTietPhieuThueImpl;
import impls.KhachHangImpl;
import impls.PhieuThueImpl;

public class BaoCaoKhachHangPanel extends JPanel implements MouseListener, ActionListener {
	private JPanel child, pNor;
	JTable tblKH, tblBangDia;
	DefaultTableModel defTblKH, defTblBD;
	JLabel lblTitle, lbMaKhachHang;
	JTextField txtMaKhachHang;
	JButton btnTimKhachHang, btnListKHBDQuaHan, btnListKHNoChuaTra,btnListAllKH;
	static Font fontChu = new Font("SansSerif", 2, 20);

	public BaoCaoKhachHangPanel(BaoCaoPanel parent) {
		child = parent;
		Box bc, b1, b2, bLeft, bRight;
		bc = Box.createVerticalBox();
		System.out.println(child.getWidth() + " " + child.getHeight());
		bc.setPreferredSize(new Dimension(child.getWidth() - 500, child.getHeight() - 50));
		add(bc);
		pNor = new JPanel();
		pNor.add(lblTitle = new JLabel("Báo Cáo Khách Hàng"));
		lblTitle.setFont(new Font("times new roman", Font.BOLD, 50));
		lblTitle.setForeground(new Color(0xFFAA00));
		bc.add(pNor);
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");			
		bc.add(Box.createVerticalStrut(50));
		bc.add(b1 = Box.createHorizontalBox());
			b1.add(Box.createHorizontalStrut(150));
			b1.add(lbMaKhachHang = new JLabel("Ngày hiện tại: "));
			lbMaKhachHang.setFont(fontChu);
			b1.add(new JLabel(dateFormat.format(date)));
			b1.add(Box.createHorizontalStrut(100));
			b1.add(lbMaKhachHang = new JLabel("Mã khách hàng: "));
			lbMaKhachHang.setFont(fontChu);
			b1.add(Box.createHorizontalStrut(20));
			b1.add(txtMaKhachHang = new JTextField(20));
			b1.add(Box.createHorizontalStrut(20));
			b1.add(btnTimKhachHang = new JButton("Tìm kiếm", new ImageIcon(".\\image\\ic_search.png")));
			btnTimKhachHang.setFont(fontChu);
			b1.add(Box.createHorizontalStrut(150));
		bc.add(Box.createVerticalStrut(50));
		bc.add(b1 = Box.createHorizontalBox());
			b1.add(Box.createHorizontalStrut(100));
			b1.add(btnListKHBDQuaHan = new JButton("<html>Danh sách khách hàng <br/> có một hoặc nhiều mặt hàng quá hạn</html>", new ImageIcon(".\\image\\ic_listreport.png")));
			btnListKHBDQuaHan.setFont(fontChu);
			b1.add(Box.createHorizontalStrut(50));
			b1.add(btnListAllKH = new JButton("<html>Danh sách tất cả<br/> khách hàng</html>", new ImageIcon(".\\image\\ic_listcustomer.png")));
			btnListAllKH.setFont(fontChu);
			b1.add(Box.createHorizontalStrut(50));
			b1.add(btnListKHNoChuaTra = new JButton("<html>Danh sách khách hàng <br/> có một hoặc nhiều khoản phí trả chậm</html>", new ImageIcon(".\\image\\ic_listphitrehan.png")));
			btnListKHNoChuaTra.setFont(fontChu);
			b1.add(Box.createHorizontalStrut(100));

		JScrollPane scroll;
		bc.add(Box.createVerticalStrut(100));
		int col[] = { 5, 20, 20, 15, 10, 10 };
		// setColumnWidth(col);
		String[] tieuDe = "Mã Khách Hàng;Tên Khách Hàng;Số Điện Thoại;Địa Chỉ;Tổng Đĩa Thuê;Tổng Nợ".split(";");
		bc.add(b1 = Box.createHorizontalBox());
		b1.add(b2= Box.createVerticalBox());
		defTblKH = new DefaultTableModel(tieuDe, 0);
		tblKH = new JTable(defTblKH) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component c = super.prepareRenderer(renderer, row, col);
				if (row % 2 == 0 && !isCellSelected(row, col)) {
					c.setBackground(Color.decode("#F1F1F1"));
				} else if (!isCellSelected(row, col)) {
					c.setBackground(Color.decode("#D7F1FF"));
				} else {
					c.setBackground(Color.decode("#25C883"));
				}
				return c;
			}
		};
		DefaultTableCellRenderer centerRendererKhachHang = new DefaultTableCellRenderer();
		centerRendererKhachHang.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < col.length; i++) {
			tblKH.getColumnModel().getColumn(i).setCellRenderer(centerRendererKhachHang);
		}
		b2.add(scroll = new JScrollPane(tblKH));
		tblKH.setAutoCreateRowSorter(true);
		JTableHeader header = tblKH.getTableHeader();
		header.setBackground(Color.CYAN);
		header.setOpaque(false);
		// xét cứng cột
		tblKH.getTableHeader().setReorderingAllowed(false);
		TitledBorder tbDsKH = new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Danh sách khách hàng");
		tbDsKH.setTitleFont(fontChu);
		tbDsKH.setTitleColor(new Color(0xFFAA00));
		b2.setBorder(tbDsKH);
		
		
		// table 2
		b1.add(Box.createHorizontalStrut(20));
		b1.add(b2=Box.createVerticalBox());
		JScrollPane scroll2;
		int col2[] = { 5, 20, 20, 15, 20 };
		// setColumnWidth(col);
		String[] tieuDe2 = "Mã Phiếu Thuê;Tổng Tiền;Ngày Đặt;Tên Tiêu Đề;Ngày Trả Thật;Ngày Hết Hạn;Tình Trạng"
				.split(";");
		defTblBD = new DefaultTableModel(tieuDe2, 0);
		tblBangDia = new JTable(defTblBD) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component c = super.prepareRenderer(renderer, row, col);
				if (row % 2 == 0 && !isCellSelected(row, col)) {
					c.setBackground(Color.decode("#F1F1F1"));
				} else if (!isCellSelected(row, col)) {
					c.setBackground(Color.decode("#D7F1FF"));
				} else {
					c.setBackground(Color.decode("#25C883"));
				}
				return c;
			}
		};
		DefaultTableCellRenderer centerRendererBD = new DefaultTableCellRenderer();
		centerRendererBD.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < col2.length; i++) {
			tblBangDia.getColumnModel().getColumn(i).setCellRenderer(centerRendererBD);
		}
		b2.add(scroll = new JScrollPane(tblBangDia));
		tblBangDia.setAutoCreateRowSorter(true);
		JTableHeader header2 = tblBangDia.getTableHeader();
		header.setBackground(Color.CYAN);
		header.setOpaque(false);
		// xét cứng cột
		tblBangDia.getTableHeader().setReorderingAllowed(false);
		
		TitledBorder tbDsBD = new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Danh sách băng đĩa tương ứng");
		tbDsBD.setTitleFont(fontChu);
		tbDsBD.setTitleColor(new Color(0xFFAA00));
		b2.setBorder(tbDsBD);
		bc.add(Box.createVerticalStrut(100));

		tblKH.setRowHeight(50);
		tblBangDia.setRowHeight(50);

		tblKH.setRowSelectionAllowed(true);
		tblKH.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tblBangDia.setRowSelectionAllowed(true);
		tblBangDia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		getDataKhachHang();
		tblKH.addMouseListener(this);
		btnTimKhachHang.addActionListener(this);
		btnListAllKH.addActionListener(this);
		btnListKHBDQuaHan.addActionListener(this);
		btnListKHNoChuaTra.addActionListener(this);
	}

	private void xoaDataTableKhachHang() {
		while (tblKH.getRowCount() > 0) {
			defTblKH.removeRow(0);
		}
	}

	private void getDataKhachHang() {
		// TODO Auto-generated method stub
		xoaDataTableKhachHang();
		CRUDInterface<KhachHang> crudInterface = new KhachHangImpl();
		List<KhachHang> listKH = crudInterface.getDanhSach();
		for (KhachHang kh : listKH) {
			BangDiaDAO bangDiaDAO = new BangDiaImpl();
			int count = bangDiaDAO.getCountBDTheoKhachHang(kh.getMa());
			String[] rowData = { kh.getMa() + "", kh.getTen() + "", kh.getSdt() + "", kh.getDiaChi() + "", count + "",
					kh.getPhiTreHan() + "" };
			defTblKH.addRow(rowData);
		}
	}

	private void xoaDataTableBangDia() {
		while (tblBangDia.getRowCount() > 0) {
			defTblBD.removeRow(0);
		}
	}

	private void getDataBangDiaTheoKhachHang(int maKhachHang) {
		xoaDataTableBangDia();
		PhieuThueDAO phieuThueDAO = new PhieuThueImpl();
		List<PhieuThue> listPhieuThue = phieuThueDAO.listPhieuThueTheoMaKhachHang(maKhachHang);
		for (PhieuThue phThue : listPhieuThue) {
			//Vector<String> rowData = new Vector<String>();
			String[] rowData = new String[ 10 ];
			rowData[0] = phThue.getMa()+"";
			rowData[1] = phThue.getTongTien() + "";
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			rowData[2] = dateFormat.format(phThue.getNgayThue());
			ChiTietPhieuThueDAO ctptDAO = new ChiTietPhieuThueImpl();
			List<ChiTietPhieuThue> listCTPT = ctptDAO.listChiTietPhieuThueTheoPhieuThue(phThue.getMa());
			for (ChiTietPhieuThue ctpt : listCTPT) {
				rowData[3] = ctpt.getBangDia().getMaTieuDe().getTen() + "";
				if (ctpt.getNgayTra() == null)
					rowData[4] = "";
				else
					rowData[4] = dateFormat.format(ctpt.getNgayTra());

				Date ngayPhaiTra = new Date(ctpt.getPhieuThue().getNgayThue().getTime()
						+ Duration.ofDays(ctpt.getBangDia().getSoNgayDuocThue()).toMillis());
				rowData[5] = dateFormat.format(ngayPhaiTra);

				int tinhTrang = ctpt.getTinhTrang();
				if (tinhTrang == ChiTietPhieuThue.NO) {
					rowData[6] = "Nợ phí trễ hạn";
				} else if (tinhTrang == ChiTietPhieuThue.KHONG_NO) {
					rowData[6] = "Đã thanh toán";
				} else if (tinhTrang == ChiTietPhieuThue.DANG_THUE) {
					rowData[6] = "Đang thuê";
				}
				defTblBD.addRow(rowData);
			}
		}

	}
	private void getAllListKHCoNoBangDia() {
		xoaDataTableBangDia();
		xoaDataTableKhachHang();
		CRUDInterface<KhachHang> crudInterface = new KhachHangImpl();
		List<KhachHang> listKH = crudInterface.getDanhSach();
		for (KhachHang kh : listKH) {
			BangDiaDAO bangDiaDAO = new BangDiaImpl();
			int count = bangDiaDAO.getCountBDTheoKhachHang(kh.getMa());
			PhieuThueDAO phieuThueDAO = new PhieuThueImpl();
			List<PhieuThue> listPhieuThue = phieuThueDAO.listPhieuThueTheoMaKhachHang(kh.getMa());
			for (PhieuThue phThue : listPhieuThue) {
				//Vector<String> rowData = new Vector<String>();
				ChiTietPhieuThueDAO ctptDAO = new ChiTietPhieuThueImpl();
				List<ChiTietPhieuThue> listCTPT = ctptDAO.listChiTietPhieuThueTheoPhieuThue(phThue.getMa());
				for (ChiTietPhieuThue ctpt : listCTPT) {
					int tinhTrang = ctpt.getTinhTrang();
					if (tinhTrang == ChiTietPhieuThue.NO) {
						String[] rowData = { kh.getMa() + "", kh.getTen() + "", kh.getSdt() + "", kh.getDiaChi() + "", count + "",
								kh.getPhiTreHan() + "" };
						defTblKH.addRow(rowData);
					}
				}
			}
		}
		
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (src.equals(tblKH)) {
			int row = tblKH.getSelectedRow();
			String maKh = tblKH.getValueAt(row, 0).toString();
			getDataBangDiaTheoKhachHang(Integer.parseInt(maKh));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (src.equals(btnTimKhachHang)) {
			timKhachHang();
		}else if(src.equals(btnListAllKH)) {
			getDataKhachHang();
		}else if(src.equals(btnListKHNoChuaTra)) {
			getDataKhachHangCoPhiTreHan();
		}else if(src.equals(btnListKHBDQuaHan)) {
			getAllListKHCoNoBangDia();
		}
	}

	private void getDataKhachHangCoPhiTreHan() {
		// TODO Auto-generated method stub
		xoaDataTableKhachHang();
		xoaDataTableBangDia();
		KhachHangDAO khachHangDAO = new KhachHangImpl();
		List<KhachHang> listKhachHangCoPhiTreHan = khachHangDAO.listKhachHangCoPhiTreHan();
		for (KhachHang kh : listKhachHangCoPhiTreHan) {
			String[] rowData = { kh.getMa() + "", kh.getTen() + "", kh.getSdt() + "", kh.getDiaChi() + "", 0 + "",
					kh.getPhiTreHan() + "" };
			defTblKH.addRow(rowData);
		}
		
	}

	private void timKhachHang() {
		// TODO Auto-generated method stub
		String id = txtMaKhachHang.getText().trim();
		if (!id.trim().matches("[0-9]*") || id.equals("")) {
			JOptionPane.showMessageDialog(this, "Mã là số!");
			txtMaKhachHang.requestFocus();
			return;
		}
		CRUDInterface<KhachHang> crud = new KhachHangImpl();
		KhachHang kh = crud.timBangMa(Integer.parseInt(id));
		if (kh == null) {
			JOptionPane.showMessageDialog(this, "Tìm không thấy!");
		} else {
			getDataKhachHang(kh);
		}
	}

	private void getDataKhachHang(KhachHang kh) {
		// TODO Auto-generated method stub
		xoaDataTableKhachHang();
		xoaDataTableBangDia();
		String[] rowData = { kh.getMa() + "", kh.getTen() + "", kh.getSdt() + "", kh.getDiaChi() + "", 0 + "",
				kh.getPhiTreHan() + "" };
		defTblKH.addRow(rowData);

	}

}
