package views.dattruoc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import daos.BangDiaDAO;
import daos.CRUDInterface;
import daos.DanhSachDatTruocDAO;
import entities.BangDia;
import entities.DanhSachDatTruoc;
import entities.DanhSachDatTruocId;
import entities.KhachHang;
import entities.TieuDe;
import impls.BangDiaImpl;
import impls.DanhSachDatTruocImpl;
import impls.KhachHangImpl;
import impls.TieuDeImpl;



public class QuanLyDatTruocPanel extends JPanel implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;

	JLabel lbTieuDeHeThong, lbMaKhachHang, lbTenKhachHang, lbSdt, lbKQSdt, lbTieuDe;
	JLabel lbTenTieuDe, lbMaTieuDe, lbTheLoaiTieuDe, lbTinhTrangTieuDe;
	JLabel lbKQTenTieuDe, lbKQMaTieuDe, lbKQTheLoaiTieuDe, lbKQTinhTrangTieuDe;
	JTextField txtKQTieuDe, txtMaKhachHang, txtTenKhachHang;
	private DefaultTableModel  dtmKhachHangDatDia;
	private JTable tblKhachHangDatDia;
	JButton btnTimKhachHang, btnXacNhanDatTruoc, btnHuyDatTruoc, btnXoaRong, btnDatTruoc, btnXoaRongKH;
	static Font fontChu = new Font("SansSerif", 2, 20);
	private JList jList = createJList();

	int widthScreen, heightScreen;

	public QuanLyDatTruocPanel() {
		setLayout(new BorderLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		widthScreen = (int) screenSize.getWidth();
		heightScreen = (int) screenSize.getHeight();
		guiUI();
		List<JLabel> listLb = Arrays.asList(lbMaKhachHang, lbTenKhachHang, lbSdt, lbKQSdt, lbTieuDe, lbTenTieuDe,
				lbMaTieuDe, lbTheLoaiTieuDe, lbTinhTrangTieuDe, lbKQTenTieuDe, lbKQMaTieuDe, lbKQTheLoaiTieuDe,
				lbKQTinhTrangTieuDe);
		setFontChu(listLb);
		List<JTextField> listTxt = Arrays.asList(txtMaKhachHang, txtTenKhachHang, txtKQTieuDe);
		setFontChuTxt(listTxt);

		initData();
		regisAction();
	}

	private void regisAction() {
		// TODO Auto-generated method stub
		jList.addMouseListener(this);// xong
		btnDatTruoc.addActionListener(this);
		btnXoaRong.addActionListener(this);// xong
		btnTimKhachHang.addActionListener(this);// xong
		btnXoaRongKH.addActionListener(this);// xong
		btnXacNhanDatTruoc.addActionListener(this);
		btnHuyDatTruoc.addActionListener(this);
	}

	private void guiUI() {
		Box bc, b1, b2, bLeft, bRight;
		bc = Box.createVerticalBox();
		add(bc);
		/**
		 * Thông tin khách hàng
		 */
		bc.add(b1 = Box.createHorizontalBox());
		b1.add(lbTieuDeHeThong = new JLabel("Quản lý đặt trước"));
		lbTieuDeHeThong.setFont(new Font("times new roman", 1, 50));
		lbTieuDeHeThong.setForeground(new Color(0xFFAA00));
		b1.add(Box.createHorizontalStrut(widthScreen - 400));
		bc.add(Box.createVerticalStrut(100));
		bc.add(b1 = Box.createHorizontalBox());
		b1.add(Box.createHorizontalStrut(150));
		b1.add(lbMaKhachHang = new JLabel("Mã khách hàng: "));
		b1.add(Box.createHorizontalStrut(20));
		b1.add(txtMaKhachHang = new JTextField(20));
		b1.add(Box.createHorizontalStrut(20));
		b1.add(btnTimKhachHang = new JButton("Tìm kiếm", new ImageIcon(".\\image\\ic_search.png")));
		btnTimKhachHang.setFont(fontChu);
		b1.add(Box.createHorizontalStrut(50));
		b1.add(lbTenKhachHang = new JLabel("Họ tên: "));
		b1.add(Box.createHorizontalStrut(20));
		b1.add(txtTenKhachHang = new JTextField(25));
		b1.add(Box.createHorizontalStrut(50));
		b1.add(lbSdt = new JLabel("Số điện thoại: "));
		b1.add(Box.createHorizontalStrut(20));
		b1.add(lbKQSdt = new JLabel());
		b1.add(Box.createHorizontalStrut(50));
		b1.add(btnXoaRongKH = new JButton("Làm Mới", new ImageIcon(".\\image\\reload.png")));
		b1.add(Box.createHorizontalStrut(150));
		bc.add(b1 = Box.createHorizontalBox());

		/**
		 * danh sách tiêu đề
		 */
		bc.add(Box.createVerticalStrut(20));
		bc.add(b1 = Box.createHorizontalBox());
		b1.add(Box.createHorizontalStrut(100));
		b1.add(bLeft = Box.createVerticalBox());
		bLeft.add(Box.createVerticalStrut(20));

		bLeft.add(b2 = Box.createHorizontalBox());
		b2.add(lbTieuDe = new JLabel("Tên tiêu đề: "));
		b2.add(Box.createHorizontalStrut(10));
		b2.add(txtKQTieuDe = createTextField());
		b2.add(Box.createHorizontalStrut(20));
		bLeft.add(Box.createVerticalStrut(20));
		bLeft.add(new JScrollPane(jList));
		bLeft.add(Box.createVerticalStrut(20));
		bLeft.add(b2 = Box.createHorizontalBox());
		b2.add(lbMaTieuDe = new JLabel("Mã tiêu đề: "));
		b2.add(Box.createHorizontalStrut(10));
		b2.add(lbKQMaTieuDe = new JLabel());
		b2.add(Box.createHorizontalStrut(520));
		bLeft.add(b2 = Box.createHorizontalBox());
		b2.add(lbTenTieuDe = new JLabel("Tên tiêu đề: "));
		b2.add(Box.createHorizontalStrut(10));
		b2.add(lbKQTenTieuDe = new JLabel());
		b2.add(Box.createHorizontalStrut(520));
		bLeft.add(b2 = Box.createHorizontalBox());
		b2.add(lbTheLoaiTieuDe = new JLabel("Thể loại: "));
		b2.add(Box.createHorizontalStrut(10));
		b2.add(lbKQTheLoaiTieuDe = new JLabel());
		b2.add(Box.createHorizontalStrut(520));
		bLeft.add(b2 = Box.createHorizontalBox());
		b2.add(lbTinhTrangTieuDe = new JLabel("Tình trạng hiện tại: "));
		b2.add(Box.createHorizontalStrut(10));
		b2.add(lbKQTinhTrangTieuDe = new JLabel());
		b2.add(Box.createHorizontalStrut(450));

		bLeft.add(Box.createVerticalStrut(50));
		bLeft.add(b2 = Box.createHorizontalBox());
		b2.add(btnXoaRong = new JButton("Làm Mới", new ImageIcon(".\\image\\reload.png")));
		btnXoaRong.setFont(fontChu);
		b2.add(Box.createHorizontalStrut(50));
		b2.add(btnDatTruoc = new JButton("Đặt trước", new ImageIcon(".\\image\\ic_reserve32.png")));
		btnDatTruoc.setFont(fontChu);
		bLeft.add(Box.createVerticalStrut(250));

		TitledBorder tbDiaDaDat = new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Danh sách tiêu đề");
		tbDiaDaDat.setTitleFont(fontChu);
		tbDiaDaDat.setTitleColor(new Color(0xFFAA00));
		bLeft.setBorder(tbDiaDaDat);

		/**
		 * Danh sách khách hàng đã đặt
		 */
		b1.add(bRight = Box.createVerticalBox());
		JScrollPane scrollKhachHangDatDia;
		int colKhachHang[] = { 5, 20, 20, 15, 20, 20, 15 };
		String[] tieuDeKhachHang = "Mã khách hàng; Khách hàng; Số điện thoại;Tiêu đề;Thể loại;Trạng thái;Ngày đặt"
				.split(";");
		// bc.add(b1 = Box.createHorizontalBox());
		dtmKhachHangDatDia = new DefaultTableModel(tieuDeKhachHang, 0);
		tblKhachHangDatDia = new JTable(dtmKhachHangDatDia) {
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
		tblKhachHangDatDia.setRowSelectionAllowed(true);
		tblKhachHangDatDia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblKhachHangDatDia.setAutoCreateRowSorter(true);
		JTableHeader headerKhachHang = tblKhachHangDatDia.getTableHeader();
		headerKhachHang.setBackground(Color.CYAN);
		headerKhachHang.setOpaque(false);
		// xét cứng cột
		tblKhachHangDatDia.getTableHeader().setReorderingAllowed(false);
		// xét độ dài của cột
		for (int i = 0; i < 7; i++) {
			tblKhachHangDatDia.getColumnModel().getColumn(i).setPreferredWidth(colKhachHang[i] * 4);
		}
		DefaultTableCellRenderer centerRendererKhachHang = new DefaultTableCellRenderer();
		centerRendererKhachHang.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < 7; i++) {
			tblKhachHangDatDia.getColumnModel().getColumn(i).setCellRenderer(centerRendererKhachHang);
		}
		bRight.add(scrollKhachHangDatDia = new JScrollPane(tblKhachHangDatDia));

		TitledBorder tbKhachHangDatDia = new TitledBorder(BorderFactory.createLineBorder(Color.BLACK),
				"Danh sách khách hàng đã đặt");
		tbKhachHangDatDia.setTitleFont(fontChu);
		tbKhachHangDatDia.setTitleColor(new Color(0xFFAA00));

		bRight.setBorder(tbKhachHangDatDia);
		bRight.add(Box.createVerticalStrut(20));
		bRight.add(b2 = Box.createHorizontalBox());
		b2.add(Box.createHorizontalStrut(50));
		b2.add(btnXacNhanDatTruoc = new JButton("Xác nhận", new ImageIcon(".\\image\\ic_success.png")));
		btnXacNhanDatTruoc.setFont(fontChu);
		b2.add(Box.createHorizontalStrut(50));
		b2.add(btnHuyDatTruoc = new JButton("Huỷ", new ImageIcon(".\\image\\ic_delete.png")));
		btnHuyDatTruoc.setFont(fontChu);
		b2.add(Box.createHorizontalStrut(50));
		bRight.add(Box.createVerticalStrut(20));

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, bLeft, bRight);
		b1.add(splitPane);
		b1.add(Box.createHorizontalStrut(100));
		
		tblKhachHangDatDia.setRowHeight(80);
		
	}

	private void initData() {
		chuyenTrangThaiTatCaDiaTrongDsDatTruoc();
		hienDataDSKhachHangDatDia();
	}

	private void setFontChu(List<JLabel> listLb) {
		listLb.forEach(lb -> {
			lb.setFont(fontChu);
		});
	}

	private void setFontChuTxt(List<JTextField> listTxt) {
		listTxt.forEach(lb -> {
			lb.setFont(fontChu);
		});
	}

	private ArrayList<String> listTenTieuDe() {
		TieuDeImpl tieuDeDAO = new TieuDeImpl();
		List<TieuDe> listKH = tieuDeDAO.getDanhSach();
		ArrayList<String> listTen = new ArrayList<String>();
		listKH.forEach(tieuDe -> {
			listTen.add(tieuDe.getTen() + " [" + tieuDe.getMa() + "]");
		});
		return listTen;
	}

	private JTextField createTextField() {
		final JTextField field = new JTextField(15);
		field.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				filter();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				filter();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}

			private void filter() {
				String filter = field.getText();
				filterModel((DefaultListModel<String>) jList.getModel(), filter);
			}
		});
		return field;
	}

	private JList createJList() {
		JList list = new JList(createDefaultListModel());
		list.setVisibleRowCount(5);
		return list;
	}

	private ListModel<String> createDefaultListModel() {
		DefaultListModel<String> model = new DefaultListModel<>();
		ArrayList<String> listTenKh = listTenTieuDe();
		for (String s : listTenKh) {
			model.addElement(s);
		}
		return model;
	}

	public void filterModel(DefaultListModel<String> model, String filter) {
		ArrayList<String> listTenKh = listTenTieuDe();
		for (String s : listTenKh) {
			// s=s.trim();
			if (!s.toLowerCase().trim().startsWith(filter.toLowerCase().trim())) {
				if (model.contains(s)) {
					model.removeElement(s);
				}
			} else {
				if (!model.contains(s)) {
					model.addElement(s);
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (src.equals(jList)) {
			if (jList.isEnabled() != false) {
				hienDuLieuTieuDe();
			}
		}
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

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	private void xoaTableDataDSKhachHangDatDia() {
		while (tblKhachHangDatDia.getRowCount() > 0) {
			dtmKhachHangDatDia.removeRow(0);
		}
	}

	private String catChuoi(String s) {
		int startString = s.indexOf(" [");
		int finishString = s.indexOf("]");
		String cmnd = s.substring(startString + 2, finishString);
		return cmnd;
	}

	private void hienDuLieuTieuDe() {
		String s = jList.getSelectedValue().toString();
		if (!s.equals("")) {
			String id = catChuoi(s);
			CRUDInterface<TieuDe> crud = new TieuDeImpl();
			List<TieuDe> listKH = crud.getDanhSach();
			TieuDe td;
			td = crud.timBangMa(Integer.parseInt(id));
			setTextTieuDe(td);
			List<BangDia> listBD = dsBangDiaTheoTieuDe(Integer.parseInt(id));
			// System.out.println("cccc "+listBD);
			if (listBD == null || listBD.size() == 0) {
				lbKQTinhTrangTieuDe.setText("Không có đĩa");
			} else {
				boolean flag = true;
				for (BangDia x : listBD) {
					if (x.getTinhTrang() == BangDia.TRONG_KHO) {
						lbKQTinhTrangTieuDe.setText("Trong kho");
						flag = false;
						break;
					}
				}
				;
				if (flag == true)
					lbKQTinhTrangTieuDe.setText("Không có đĩa");
			}

		} else {
			xoaRong();
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
			txtTenKhachHang.setText("");
			lbKQSdt.setText("");
			lbKQMaTieuDe.setText("");
			JOptionPane.showMessageDialog(this, "Tìm không thấy!");
		} else {
			txtTenKhachHang.setText(kh.getTen());
			lbKQSdt.setText(kh.getSdt());
		}
	}

	private void setTextTieuDe(TieuDe td) {
		lbKQMaTieuDe.setText(td.getMa() + "");
		lbKQTenTieuDe.setText(td.getTen());
		String theLoai = "";
		if (td.getLoai() == TieuDe.GAME) {
			theLoai = "Game";
		} else
			theLoai = "Phim";
		lbKQTheLoaiTieuDe.setText(theLoai);
	}

	private void xoaRong() {
		lbKQMaTieuDe.setText("");
		lbKQTenTieuDe.setText("");
		lbKQTheLoaiTieuDe.setText("");
		lbKQTinhTrangTieuDe.setText("");
	}

	private void xoaRongKH() {
		txtMaKhachHang.setText("");
		txtTenKhachHang.setText("");
		lbKQSdt.setText("");
	}

	private void datTieuDe() {
		// TODO Auto-generated method stub
		String maTieuDe = lbKQMaTieuDe.getText();
		if (maTieuDe.equals("") || maTieuDe == null) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn tiêu đề!");
			return;
		}
		String maKhachHang = txtMaKhachHang.getText().trim();
		if (maKhachHang.equals("") || maKhachHang == null) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng!");
			return;
		}
		CRUDInterface<KhachHang> khachHangDAO = new KhachHangImpl();
		KhachHang kh = khachHangDAO.timBangMa(Integer.parseInt(maKhachHang));
		if (kh == null) {
			JOptionPane.showMessageDialog(this, "Khách hàng không tồn tại!");
			return;
		}
		CRUDInterface<TieuDe> tieuDeDAO = new TieuDeImpl();
		TieuDe td = tieuDeDAO.timBangMa(Integer.parseInt(maTieuDe));
		Date ngayDatDate = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String strDate = dateFormat.format(ngayDatDate);
		int tinhTrangDatTruoc = DanhSachDatTruoc.CHUA_XAC_NHAN;
		String trangThaiTieuDe = "";
		if (lbKQTinhTrangTieuDe.getText().equals("Không có đĩa")) {
			tinhTrangDatTruoc = DanhSachDatTruoc.DANG_CHO;
			trangThaiTieuDe = "Đang chờ đĩa";
		}
		if (lbKQTinhTrangTieuDe.getText().equals("Trong kho")) {
			tinhTrangDatTruoc = DanhSachDatTruoc.CHUA_XAC_NHAN;
			trangThaiTieuDe = "Chưa xác nhận";
		}

		CRUDInterface<DanhSachDatTruoc> daCrudInterface = new DanhSachDatTruocImpl();
		DanhSachDatTruoc dsdt = new DanhSachDatTruoc(kh, td, ngayDatDate, tinhTrangDatTruoc);
		System.out.println("dòng 474: " + dsdt);
		
		DanhSachDatTruocDAO dsDatTruocDAO = new DanhSachDatTruocImpl();
		if (dsDatTruocDAO.timTheoTieuDeVaKhachHang(td.getMa(), kh.getMa()) != null) {
			JOptionPane.showMessageDialog(this, "Bạn đã đặt tiêu đề này rồi!");
			return;
		}
		System.out.println("dòng 480: " + trangThaiTieuDe);
		String[] rowData = { kh.getMa() + "", kh.getTen() + "", kh.getSdt() + "", td.getTen() + " [" + td.getMa() + "]",
				getTheLoai(td.getLoai()), trangThaiTieuDe, strDate };
		dtmKhachHangDatDia.addRow(rowData);
		daCrudInterface.them(dsdt);

		JOptionPane.showMessageDialog(this, "Đặt trước thành công!");
	}

	private void hienDataDSKhachHangDatDia() {
		xoaTableDataDSKhachHangDatDia();
		DanhSachDatTruocDAO daCrudInterface = new DanhSachDatTruocImpl();
		List<DanhSachDatTruoc> listDSDT = daCrudInterface.getDanhSachKHDatTruoc();

		for(DanhSachDatTruoc dsdt : listDSDT) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			String strDate = dateFormat.format(dsdt.getNgayDatTruoc());
			String[] rowData = { dsdt.getKhachHang().getMa() + "", dsdt.getKhachHang().getTen() + "",
					dsdt.getKhachHang().getSdt() + "",
					dsdt.getTieuDe().getTen() + " [" + dsdt.getTieuDe().getMa() + "]",
					getTheLoai(dsdt.getTieuDe().getLoai()), getTrangThaiTieuDe(dsdt.getTinhTrang()), strDate };
			dtmKhachHangDatDia.addRow(rowData);
		}
	}

	private String getTheLoai(int loai) {
		String theLoai = "";
		if (loai == TieuDe.GAME) {
			theLoai = "Game";
		} else
			theLoai = "Phim";
		return theLoai;
	}

	private String getTrangThaiTieuDe(int loai) {
		String theLoai = "";
		if (loai == DanhSachDatTruoc.CHUA_XAC_NHAN) {
			theLoai = "Chưa xác nhận";
		}
		if (loai == DanhSachDatTruoc.DANG_CHO) {
			theLoai = "Đang chờ đĩa";
		}
		return theLoai;
	}

	private List<BangDia> dsBangDiaTheoTieuDe(int maTieuDe) {
		BangDiaDAO tdDao = new BangDiaImpl();
		return tdDao.timTinhTrangTieuDe(maTieuDe);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (src.equals(btnXoaRong)) {
			xoaRong();
		}
		if (src.equals(btnTimKhachHang)) {
			timKhachHang();
		}
		if (src.equals(btnXoaRongKH)) {
			xoaRongKH();
		}
		if (src.equals(btnDatTruoc)) {
			datTieuDe();
			// chuyenTrangThaiTatCaDiaTrongDsDatTruoc();
			// hienDataDSKhachHangDatDia();
		}
		if (src.equals(btnXacNhanDatTruoc)) {
			// if(tblKhachHangDatDia.get)
			xacNhanDatTruoc();
		}
		/**
		 * 6C
		 * 
		 */
		if(src.equals(btnHuyDatTruoc)) {
			int row = tblKhachHangDatDia.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Chọn đơn đặt trước cần huỷ!");
				return;
			}
			String tieuDe = tblKhachHangDatDia.getValueAt(row, 3).toString();
			String idTieuDe = catChuoi(tieuDe);
			String idKhachHang = tblKhachHangDatDia.getValueAt(row, 0).toString();
			
			if (JOptionPane.showConfirmDialog(this,
					"Huỷ đặt đĩa cho khách hàng " + dtmKhachHangDatDia.getValueAt(row, 1).toString() + "?",
					"Cảnh Báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				CRUDInterface<DanhSachDatTruoc> crudDSDT = new DanhSachDatTruocImpl();
				crudDSDT.xoa(new DanhSachDatTruoc(new DanhSachDatTruocId(Integer.parseInt(idTieuDe),Integer.parseInt(idKhachHang))));
				hienDataDSKhachHangDatDia();
				JOptionPane.showMessageDialog(this,"Huỷ thành công!");
				
			}
		}
	}
	/**
	 * 6A
	 * 
	 */
	private void xacNhanDatTruoc() {
		int row = tblKhachHangDatDia.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Chọn đơn đặt trước cần xác nhận!");
			return;
		}
		if(checkIndexTieuDe()==true) {
			return;
		}
		if (JOptionPane.showConfirmDialog(this,
				"Xác nhận đặt đĩa cho khách hàng " + dtmKhachHangDatDia.getValueAt(row, 1).toString() + "?",
				"Cảnh Báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			String trangThai = dtmKhachHangDatDia.getValueAt(row, 5).toString();
			if (trangThai.equals("Chưa xác nhận")) {
				CRUDInterface<BangDia> crudBangDia = new BangDiaImpl();
				BangDiaDAO bangDiaDAO = new BangDiaImpl();
				String tieuDe = dtmKhachHangDatDia.getValueAt(row, 3).toString();
				String id = catChuoi(tieuDe);
				List<BangDia> listBD = bangDiaDAO.dsTieuDeCoBangDiaTrongKho(Integer.parseInt(id));
				System.out.println("dòng 551: row: " + row + " " + listBD);
				if (listBD.size() > 0) {
					BangDia bd = listBD.get(0);
					bd.setId_khachHang_hienTai(Integer.parseInt(dtmKhachHangDatDia.getValueAt(row, 0).toString()));
					bd.setTinhTrang(BangDia.DA_DAT);
					crudBangDia.sua(bd);
					DanhSachDatTruocDAO dsDanhSachDatTruocDAO = new DanhSachDatTruocImpl();
					DanhSachDatTruoc dsdt = dsDanhSachDatTruocDAO.timTheoTieuDeVaKhachHang(Integer.parseInt(id),
							Integer.parseInt(dtmKhachHangDatDia.getValueAt(row, 0).toString()));
					dsdt.setTinhTrang(DanhSachDatTruoc.DA_XAC_NHAN);
					CRUDInterface<DanhSachDatTruoc> dsCrudInterface = new DanhSachDatTruocImpl();
					dsCrudInterface.sua(dsdt);

					chuyenTrangThaiTatCaDiaTrongDsDatTruoc();
					hienDuLieuTieuDe();
					hienDataDSKhachHangDatDia();
					JOptionPane.showMessageDialog(this, "Xác nhận thành công!");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Băng đĩa có tiêu đề này trong hệ thống không có sẵn!");
				return;
			}
		}
	}
	/**
	 * 6B
	 * 
	 */
	private void chuyenTrangThaiTatCaDiaTrongDsDatTruoc() {
		DanhSachDatTruocDAO daCrudInterface = new DanhSachDatTruocImpl();
		List<DanhSachDatTruoc> listDSDT = daCrudInterface.getDanhSachKHDatTruoc();
		listDSDT.forEach(dsdt -> {
			BangDiaDAO bangDiaDAO = new BangDiaImpl();
			List<BangDia> listBD = bangDiaDAO.dsTieuDeCoBangDiaTrongKho(dsdt.getTieuDe().getMa());
			if (listBD.size() > 0) {
				if (dsdt.getTinhTrang() == DanhSachDatTruoc.DANG_CHO) {
					dsdt.setTinhTrang(DanhSachDatTruoc.CHUA_XAC_NHAN);
					CRUDInterface<DanhSachDatTruoc> crudDS = new DanhSachDatTruocImpl();
					crudDS.sua(dsdt);
				}
			} else {
				dsdt.setTinhTrang(DanhSachDatTruoc.DANG_CHO);
				CRUDInterface<DanhSachDatTruoc> crudDS = new DanhSachDatTruocImpl();
				crudDS.sua(dsdt);
			}
		});
	}
	private boolean checkIndexTieuDe() {
		DanhSachDatTruocDAO daCrudInterface = new DanhSachDatTruocImpl();
		List<DanhSachDatTruoc> listDSDT = daCrudInterface.getDanhSachKHDatTruoc();
		
		List<TieuDeKhachHangModel> listIndexTieuDes = new ArrayList<TieuDeKhachHangModel>();
		for(DanhSachDatTruoc dsdt : listDSDT) {
			TieuDeKhachHangModel checkTieuDeKH = timTieuDeKHTheoMaTieuDe(listIndexTieuDes, dsdt.getTieuDe().getMa());			
			TieuDeKhachHangModel tieuDe_KhachHang = new TieuDeKhachHangModel();
			tieuDe_KhachHang.setMaTieuDe(dsdt.getTieuDe().getMa());
			tieuDe_KhachHang.setMaKhachHang(dsdt.getKhachHang().getMa());
			
			if(checkTieuDeKH == null) {
				tieuDe_KhachHang.setViTri(1);
				listIndexTieuDes.add(tieuDe_KhachHang);
			}else {
				int vTri = checkTieuDeKH.getViTri()+1;
				tieuDe_KhachHang.setViTri(vTri);
				listIndexTieuDes.add(tieuDe_KhachHang);
			}
		}		
		int row = tblKhachHangDatDia.getSelectedRow();
		String tieuDe = tblKhachHangDatDia.getValueAt(row, 3).toString();
		String idTieuDe = catChuoi(tieuDe);
		String idKhachHang = tblKhachHangDatDia.getValueAt(row, 0).toString();
		TieuDeKhachHangModel modelTDKHTim = timTieuDeKHTheoMaTieuDeVaMaKhachHang(listIndexTieuDes, Integer.parseInt(idTieuDe), Integer.parseInt(idKhachHang));
		
		if(modelTDKHTim.getViTri()!=1) {
			JOptionPane.showMessageDialog(this, "Vui lòng đặt trước theo thứ tự ngày đặt của tiêu đề!");
			return true;
		}
		return false;
	}
	private TieuDeKhachHangModel timTieuDeKHTheoMaTieuDe(List<TieuDeKhachHangModel> list,int maTieuDe) {
		TieuDeKhachHangModel tieuDe_KhachHang = new TieuDeKhachHangModel();
		for(TieuDeKhachHangModel x : list) {
			if(x.getMaTieuDe()==maTieuDe) {
				tieuDe_KhachHang = x;
			}
		}
		return tieuDe_KhachHang;
	}
	private TieuDeKhachHangModel timTieuDeKHTheoMaTieuDeVaMaKhachHang(List<TieuDeKhachHangModel> list,int maTieuDe,int maKH) {
		TieuDeKhachHangModel tieuDe_KhachHang = new TieuDeKhachHangModel();
		for(TieuDeKhachHangModel x : list) {
			if(x.getMaTieuDe()==maTieuDe && x.getMaKhachHang()==maKH) {
				tieuDe_KhachHang = x;
			}
		}
		return tieuDe_KhachHang;
	}
}
class TieuDeKhachHangModel {
	private int maTieuDe;
	private int maKhachHang;
	private int viTri;
	
	
	public int getViTri() {
		return viTri;
	}
	public void setViTri(int viTri) {
		this.viTri = viTri;
	}
	public int getMaTieuDe() {
		return maTieuDe;
	}
	public void setMaTieuDe(int maTieuDe) {
		this.maTieuDe = maTieuDe;
	}

	public int getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(int maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	
	public TieuDeKhachHangModel(int maTieuDe, int maKhachHang) {
		super();
		this.maTieuDe = maTieuDe;
		this.maKhachHang = maKhachHang;
	}
	public TieuDeKhachHangModel(int maTieuDe) {
		super();
		this.maTieuDe = maTieuDe;
	}
	public TieuDeKhachHangModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "maTieuDe=" + maTieuDe + ", maKhachHang=" + maKhachHang + ", viTri=" + viTri+"\n";
	}
	
	
}
