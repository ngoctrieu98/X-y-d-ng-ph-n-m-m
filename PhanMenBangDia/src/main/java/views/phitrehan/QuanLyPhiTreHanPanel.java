package views.phitrehan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import entities.ChiTietPhieuThue;
import entities.KhachHang;
import impls.BangDiaImpl;
import impls.ChiTietPhieuThueImpl;
import impls.KhachHangImpl;
import service.NoService;
import views.MainScreen;
import views.dangnhap.GUILogin;


public class QuanLyPhiTreHanPanel extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;

	private static final String SO_TIEN_NO = "Số tiền nợ: ";

	private JLabel lbTieuDe, lbMaKhachHang, lbTenKhachHang;
	private JTextField txtMaKhachHang, txtTenKhachHang;
	private Box bTong, bTieuDe, bThongTinKhachHang, bBang, bThanhToanVaHuy, bChucNang, bThanhToan, bHuy;
	private String[] headerKhachHang, headerChiTietPhiTreHan;
	private Font fontTableHeader;
	private DefaultTableModel dtmKH, dtmPhiTreHan;
	private JTable tblKH, tblPhiTreHan;
	private JScrollPane scpKH, scpPhiTreHan;
	private JButton btnHuyPhiTreHen, btnThanhToanPhiTreHan, btnKhachHang, btnClear;
	private DecimalFormat df = new DecimalFormat("#,### VNĐ");

	private KhachHangImpl khDAO;
	private NoService noService;
	private BangDiaImpl bdDAO;
	private ChiTietPhieuThueImpl ctptDAO;

	public QuanLyPhiTreHanPanel() {
		setLayout(new BorderLayout());
		init();
		UI();
		settingData();
		listener();
	}

	/**
	 * Khởi tạo các thành phần
	 */
	private void init() {
		headerKhachHang = new String[] { "Mã khách hàng", "Tên khách hàng", "Tổng phí trễ hạn" };
		headerChiTietPhiTreHan = new String[] { "Mã đĩa", "TỰa", "Loại đĩa", "Ngày thuê", "Ngày trả", "Phí trễ hạn" };
		fontTableHeader = new Font("SansSerif", 3, 20);

		// Khởi tạo component
		bTong = Box.createVerticalBox();
		bTieuDe = Box.createHorizontalBox();
		bThongTinKhachHang = Box.createHorizontalBox();
		bChucNang = Box.createHorizontalBox();
		bBang = Box.createHorizontalBox();
		bThanhToanVaHuy = Box.createHorizontalBox();
		bThanhToan = Box.createVerticalBox();
		bHuy = Box.createVerticalBox();

		// Khởi tạo đối tượng trong từng component
		lbTieuDe = new JLabel("Quản lý phí trễ hạn");
		lbMaKhachHang = new JLabel("Mã khách hàng: ");
		txtMaKhachHang = new JTextField(20);
		txtMaKhachHang.setMaximumSize(txtMaKhachHang.getPreferredSize());
		lbTenKhachHang = new JLabel("Tên khách hàng: ");
		txtTenKhachHang = new JTextField(25);
		txtTenKhachHang.setMaximumSize(txtTenKhachHang.getPreferredSize());
		btnClear = new JButton("Làm mới", new ImageIcon(".\\image\\cancel.png"));
		btnKhachHang = new JButton("Tìm khách hàng", new ImageIcon(".\\image\\ic_search.png"));
		btnThanhToanPhiTreHan = new JButton("Số tiền nợ: 0đ", new ImageIcon(".\\image\\ic_pay.png"));
		btnHuyPhiTreHen = new JButton("HUỶ", new ImageIcon(".\\image\\ic_delete.png"));

		// Service
		khDAO = new KhachHangImpl();
		noService = new NoService();
		bdDAO = new BangDiaImpl();
		ctptDAO = new ChiTietPhieuThueImpl();
	}

	private void UI() {
		tieuDe();
		maKhachHang();
		tenKhachHang();
		chucNang();
		bangKH();
		bangPTH();
		thanhToan();
		huy();

		bTong.add(bTieuDe);
		bTong.add(Box.createVerticalStrut(10));
		bTong.add(bThongTinKhachHang);
		bTong.add(Box.createVerticalStrut(10));
		bTong.add(bChucNang);
		bTong.add(Box.createVerticalStrut(10));
		bTong.add(bBang);
		bTong.add(Box.createVerticalStrut(10));
		bTong.add(bThanhToanVaHuy);
		bTong.add(Box.createVerticalStrut(10));
		add(bTong);
	}

	private void addListener() {
		btnHuyPhiTreHen.addActionListener(this);

	}

	/*
	 * Tiêu đề
	 */
	private void tieuDe() {
		lbTieuDe.setFont(new Font("times new roman", 1, 50));
		lbTieuDe.setForeground(new Color(0xFFAA00));
		bTieuDe.add(Box.createHorizontalStrut(10));
		bTieuDe.add(lbTieuDe);
		bTieuDe.add(Box.createHorizontalGlue());
	}

	/*
	 * Mã khách hàng
	 */
	private void maKhachHang() {
		bThongTinKhachHang.add(Box.createHorizontalStrut(10));
		bThongTinKhachHang.add(lbMaKhachHang);
		bThongTinKhachHang.add(txtMaKhachHang);
		bThongTinKhachHang.add(Box.createHorizontalStrut(10));
	}

	/**
	 * Tên khách hàng
	 */
	private void tenKhachHang() {
		bThongTinKhachHang.add(lbTenKhachHang);
		bThongTinKhachHang.add(txtTenKhachHang);
		bThongTinKhachHang.add(Box.createHorizontalGlue());
	}

	/**
	 * Chức năng
	 */
	private void chucNang() {
		bChucNang.add(Box.createHorizontalGlue());
		bChucNang.add(btnKhachHang);
		bChucNang.add(Box.createHorizontalStrut(50));
		bChucNang.add(btnClear);
		bChucNang.add(Box.createHorizontalGlue());
		btnClear.setPreferredSize(btnKhachHang.getPreferredSize());
	}

	/*
	 * Bảng khách hàng
	 */
	private void bangKH() {
		dtmKH = new DefaultTableModel(headerKhachHang, 0);
		tblKH = new JTable(dtmKH) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}

			@Override
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
		scpKH = new JScrollPane(tblKH);
		TitledBorder tbKH = new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Danh sách khách hàng");
		tbKH.setTitleFont(new Font(tbKH.getTitleFont().getFontName(), Font.TRUETYPE_FONT, 20));
		scpKH.setBorder(tbKH);
		settingTableKH(fontTableHeader);
	}

	private void settingTableKH(Font font) {
		tblKH.getTableHeader().setFont(font);
		tblKH.getTableHeader().setReorderingAllowed(false);
		tblKH.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		int[] columnsWidth = { 150, 150 };
		int i = 0;
		for (int width : columnsWidth) {
			TableColumn column = tblKH.getColumnModel().getColumn(i++);
			column.setMinWidth(width);
			column.setMaxWidth(width);
			column.setPreferredWidth(width);
			column.setResizable(false);
		}
		tblKH.setRowHeight(30);
		tblKH.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	/*
	 * Bảng phí trễ hạn
	 */
	private void bangPTH() {
		dtmPhiTreHan = new DefaultTableModel(headerChiTietPhiTreHan, 0);
		tblPhiTreHan = new JTable(dtmPhiTreHan) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}

			@Override
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
		scpPhiTreHan = new JScrollPane(tblPhiTreHan);
		TitledBorder tbPhiTreHan = new TitledBorder(BorderFactory.createLineBorder(Color.BLACK),
				"Chi tiết phí trễ hạn");
		tbPhiTreHan.setTitleFont(new Font(tbPhiTreHan.getTitleFont().getFontName(), Font.TRUETYPE_FONT, 20));
		scpPhiTreHan.setBorder(tbPhiTreHan);
		settingTablePTH(fontTableHeader);
		bBang.add(Box.createHorizontalStrut(10));
		bBang.add(scpKH);
		bBang.add(Box.createHorizontalStrut(10));
		bBang.add(scpPhiTreHan);
		bBang.add(Box.createHorizontalStrut(10));

	}

	private void settingTablePTH(Font font) {
		tblPhiTreHan.getTableHeader().setFont(font);
		tblPhiTreHan.getTableHeader().setReorderingAllowed(false);
		tblPhiTreHan.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		int[] columnsWidth = { 150, 150, 150 };
		int i = 0;
		for (int width : columnsWidth) {
			TableColumn column = tblPhiTreHan.getColumnModel().getColumn(i++);
			column.setMinWidth(width);
			column.setMaxWidth(width);
			column.setPreferredWidth(width);
			column.setResizable(false);
		}
		tblPhiTreHan.setRowHeight(30);
		tblPhiTreHan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	/*
	 * Thanh toán
	 */
	private void thanhToan() {
		bThanhToan.add(btnThanhToanPhiTreHan);
		btnThanhToanPhiTreHan.setAlignmentX(Component.CENTER_ALIGNMENT);
	}

	private void huy() {
		bHuy.add(btnHuyPhiTreHen);
		btnHuyPhiTreHen.setPreferredSize(btnThanhToanPhiTreHan.getPreferredSize());
		btnHuyPhiTreHen.setAlignmentX(Component.CENTER_ALIGNMENT);
		bThanhToanVaHuy.add(Box.createHorizontalGlue());
		bThanhToanVaHuy.add(bThanhToan);
		bThanhToanVaHuy.add(Box.createHorizontalStrut(100));
		bThanhToanVaHuy.add(bHuy);
		bThanhToanVaHuy.add(Box.createHorizontalGlue());
	}

	/**
	 * Mặc định
	 */
	private void settingData() {
		txtTenKhachHang.setEditable(false);
		btnClear.setEnabled(false);
		btnHuyPhiTreHen.setEnabled(false);
		btnThanhToanPhiTreHan.setEnabled(false);
		btnThanhToanPhiTreHan.setText(SO_TIEN_NO + df.format(0));
		defaultTableKH();
	}

	private void listener() {
		btnKhachHang.addActionListener(this);
		btnClear.addActionListener(this);
		btnThanhToanPhiTreHan.addActionListener(this);
		btnHuyPhiTreHen.addActionListener(this);
		tblKH.addMouseListener(this);
		tblPhiTreHan.addMouseListener(this);
		txtMaKhachHangKeyListener();
	}
	private void xoaDataPhiTreHan() {
		while (tblPhiTreHan.getRowCount() > 0) {
			dtmPhiTreHan.removeRow(0);
		}
	}
	/**
	 * Chỉ nhập số
	 */
	private void txtMaKhachHangKeyListener() {
		txtMaKhachHang.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (!(Character.isDigit(keyChar) || keyChar == KeyEvent.VK_DELETE
						|| keyChar == KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
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

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(tblPhiTreHan)) {
			int index = tblPhiTreHan.getSelectedRow();
			if (index != -1) {
				btnHuyPhiTreHen.setEnabled(true);
			} else {
				btnHuyPhiTreHen.setEnabled(false);
			}
		}
		if (o.equals(tblKH)) {
			xoaDataPhiTreHan();
			int index = tblKH.getSelectedRow();
			System.out.println(index);
			if (index != -1) {
				txtMaKhachHang.setText(tblKH.getValueAt(index, 0).toString());
				txtTenKhachHang.setText(tblKH.getValueAt(index, 1).toString());
				List<ChiTietPhieuThue> ctpt = ctptDAO.getDSNoByKhachHangID(Integer.parseInt(tblKH.getValueAt(index, 0).toString()));
				System.out.println(tblKH.getValueAt(index, 0).toString());
				for (ChiTietPhieuThue x : ctpt) {
					String[] rowData = { String.valueOf(x.getBangDia().getMa()), x.getBangDia().getMaTieuDe().getTen(),
							x.getBangDia().getMaTieuDe().getLoai() == 0 ? "Phim" : "Game",
							x.getPhieuThue().getNgayThue().toString(), String.valueOf(x.getNgayTra()),
							String.valueOf(x.getBangDia().getPhiTreHan()) };
					dtmPhiTreHan.addRow(rowData);
				}
				int no = noService.xemNo(Integer.parseInt(tblKH.getValueAt(index, 0).toString()));
				btnThanhToanPhiTreHan.setText(SO_TIEN_NO+df.format(no));
				btnThanhToanPhiTreHan.setEnabled(true);
			} else {
				dtmPhiTreHan.setRowCount(0);
			}
		}
	}
	private void getDataNoByIDKh() {
		xoaDataPhiTreHan();
		int index = tblKH.getSelectedRow();
		List<ChiTietPhieuThue> ctpt = ctptDAO.getDSNoByKhachHangID(Integer.parseInt(tblKH.getValueAt(index, 0).toString()));
		System.out.println(tblKH.getValueAt(index, 0).toString());
		for (ChiTietPhieuThue x : ctpt) {
			String[] rowData = { String.valueOf(x.getBangDia().getMa()), x.getBangDia().getMaTieuDe().getTen(),
					x.getBangDia().getMaTieuDe().getLoai() == 0 ? "Phim" : "Game",
					x.getPhieuThue().getNgayThue().toString(), String.valueOf(x.getNgayTra()),
					String.valueOf(x.getBangDia().getPhiTreHan()) };
			dtmPhiTreHan.addRow(rowData);
		}
		int no = noService.xemNo(Integer.parseInt(tblKH.getValueAt(index, 0).toString()));
		btnThanhToanPhiTreHan.setText(SO_TIEN_NO+df.format(no));
		btnThanhToanPhiTreHan.setEnabled(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		KhachHang kh = null;
		if (o == btnKhachHang) {
			String maKH = txtMaKhachHang.getText();
			if (maKH.equals("")) {
				JOptionPane.showMessageDialog(this, "Mã khách hàng trống");
				txtMaKhachHang.requestFocus(true);
			} else {
				kh = khDAO.timBangMa(Integer.parseInt(maKH));
				if (kh == null) {
					JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng");
					txtMaKhachHang.setText("");
					txtMaKhachHang.requestFocus();
				} else {
					txtMaKhachHang.setEditable(false);
					txtTenKhachHang.setText(kh.getTen());

					btnClear.setEnabled(true);
					KhachHang findedKH = khDAO.timBangMa(Integer.parseInt(maKH));
					if (findedKH != null) {
						updateTableKH(findedKH);
					}
					int no = noService.xemNo(Integer.parseInt(tblKH.getValueAt(tblKH.getSelectedRow(), 0).toString()));
					btnThanhToanPhiTreHan.setText(SO_TIEN_NO + df.format(no));
					if (no > 0) {
						btnThanhToanPhiTreHan.setEnabled(true);
					}
				}
			}
		}
		if (o == btnClear) {
			int clearOption = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn huỷ phiên làm việc",
					"Huỷ quản lý phí trễ hạn", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (clearOption == JOptionPane.YES_OPTION) {
				clearSection();
			}
		}
		if (o == btnThanhToanPhiTreHan) {
			if (txtMaKhachHang.getText().toString().equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập mã khách hàng!");
				txtMaKhachHang.requestFocus();
				return;
			}

			if (khDAO.timBangMa(Integer.parseInt(txtMaKhachHang.getText())).getPhiTreHan() == 0) {
				JOptionPane.showMessageDialog(null, "Khách hàng không có nợ!");
				return;
			}
			GUIThanhToanPhiTreHan guiThanhToanPhiTreHan = new GUIThanhToanPhiTreHan(Integer.parseInt(tblKH.getValueAt(tblKH.getSelectedRow(), 0).toString()));
			guiThanhToanPhiTreHan.setMaKH(Integer.parseInt(txtMaKhachHang.getText().trim()));
		}
		if (o == btnHuyPhiTreHen) {
			GUILogin guiLogin = null;
			if(MainScreen.checkLogin == MainScreen.logout) {
				guiLogin = new GUILogin();
			}else {
				int index = tblPhiTreHan.getSelectedRow();
				if (index != -1) {
					List<ChiTietPhieuThue> ctpt = ctptDAO.getDSNoByKhachHangID(Integer.parseInt(tblKH.getValueAt(tblKH.getSelectedRow(), 0).toString()));
					if (JOptionPane.showConfirmDialog(this,
							"Huỷ phí trễ hạn này?",
							"Cảnh Báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						ctptDAO.xoa(ctpt.get(index));
						getDataNoByIDKh();
						btnHuyPhiTreHen.setEnabled(false);
						noService.huyNo(Integer.parseInt(tblKH.getValueAt(tblKH.getSelectedRow(), 0).toString()), 
								Integer.parseInt(tblPhiTreHan.getValueAt(tblPhiTreHan.getSelectedRow(), 5).toString()));
						btnThanhToanPhiTreHan.setText(SO_TIEN_NO + df.format(noService.xemNo(Integer.parseInt(tblKH.getValueAt(tblKH.getSelectedRow(), 0).toString()))));
						defaultTableKH();
						JOptionPane.showMessageDialog(this, "Huỷ thành công!");
					}
				}
			}
		}
	}

	private void updateTableKH(KhachHang kh) {
		dtmKH.setRowCount(0);
		String[] rowData = { String.valueOf(kh.getMa()), kh.getTen(), df.format(kh.getPhiTreHan()) };
		dtmKH.addRow(rowData);
	}

	/**
	 * Nguyên bản
	 */
	private void clearSection() {
		txtMaKhachHang.setText("");
		txtTenKhachHang.setText("");
		txtMaKhachHang.setEditable(true);
		txtMaKhachHang.requestFocus();
		dtmPhiTreHan.setRowCount(0);
		dtmKH.setRowCount(0);
		defaultTableKH();
		btnHuyPhiTreHen.setEnabled(false);
		btnThanhToanPhiTreHan.setEnabled(false);
		btnThanhToanPhiTreHan.setText(SO_TIEN_NO + df.format(0));
		btnClear.setEnabled(false);
	}
	private void xoaTBKH() {
		while (tblKH.getRowCount() > 0) {
			dtmKH.removeRow(0);
		}
	}
	private void defaultTableKH() {
		xoaTBKH();
		List<KhachHang> list = khDAO.getDanhSach();
		list.forEach(x -> {
			String[] rowData = { String.valueOf(x.getMa()), x.getTen(), String.valueOf(x.getPhiTreHan()) };
			dtmKH.addRow(rowData);
		});
	}
}
