package views.thuedia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import entities.BangDia;
import entities.ChiTietPhieuThue;
import entities.DanhSachDatTruoc;
import entities.KhachHang;
import entities.PhieuThue;
import impls.BangDiaImpl;
import impls.ChiTietPhieuThueImpl;
import impls.DanhSachDatTruocImpl;
import impls.KhachHangImpl;
import impls.PhieuThueImpl;
import service.NoService;
import views.component.LimitLengthJTF;
import views.phitrehan.GUIThanhToanPhiTreHan;

@SuppressWarnings("serial")
public class ThueDiaPanel extends JPanel implements ActionListener, MouseListener {

	private static final String TONG_TIEN_THUE = "Tổng tiền thuê: ";
	private static final String TONG_TIEN_NO = "Tổng tiền nợ: ";

	private JLabel lbTieuDe, lbMaKhachHang, lbTenKhachHang, lbSoDienThoai, lbDiaChi, lbMaDia, lbTongNo, lbTongThue;
	private Box bTong, bTieuDe, bThongTinKhachHang, bDia, bNoVaThue, bNo, bThue, bChucNang;
	private JTextField txtMaKhachHang, txtTenKhachHang, txtSoDienThoai, txtDiaChi, txtMaDia;
	private JButton btnThanhToanNo, btnThanhToanThue, btnKhachHang, btnDia, btnXoaDia, btnClear;
	private DefaultTableModel dtmDiaThue;
	private JTable tblDiaThue;
	private String[] headerDia;
	private JScrollPane scpDiaThue;
	private Font fontTableHeader;
	private DecimalFormat df = new DecimalFormat("#,### VNĐ");
	public static int maKH;
	private KhachHangImpl khDAO;
	private BangDiaImpl bdDAO;
	private PhieuThueImpl ptDAO;
	private ChiTietPhieuThueImpl ctptDAO;
	private DanhSachDatTruocImpl dsdtDAO;
	private NoService noService;

	public ThueDiaPanel() {
		setLayout(new BorderLayout());
		init();
		UI();
	}

	private void init() {
		headerDia = new String[] { "Mã đĩa", "Tựa", "Loại đĩa", "Số ngày thuê", "Giá thuê" };
		fontTableHeader = new Font("SansSerif", 3, 20);

		// Khởi tạo component
		bTong = Box.createVerticalBox();
		bTieuDe = Box.createHorizontalBox();
		bThongTinKhachHang = Box.createHorizontalBox();
		bDia = Box.createHorizontalBox();
		bChucNang = Box.createHorizontalBox();
		bNoVaThue = Box.createHorizontalBox();
		bNo = Box.createVerticalBox();
		bThue = Box.createVerticalBox();

		// Khởi tạo đối tượng trong từng component
		lbTieuDe = new JLabel("Thuê đĩa");
		lbMaKhachHang = new JLabel("Mã khách hàng: ");
		txtMaKhachHang = new JTextField(10);
		txtMaKhachHang.setDocument(new LimitLengthJTF(8));
		lbTenKhachHang = new JLabel("Tên khách hàng: ");
		txtTenKhachHang = new JTextField(25);
		lbSoDienThoai = new JLabel("Số điện thoại: ");
		txtSoDienThoai = new JTextField(10);
		lbDiaChi = new JLabel("Địa chỉ: ");
		txtDiaChi = new JTextField(25);
		lbMaDia = new JLabel("Mã đĩa: ");
		txtMaDia = new JTextField(20);
		txtMaDia.setDocument(new LimitLengthJTF(8));
		btnKhachHang = new JButton("Kiểm tra khách hàng");
		btnDia = new JButton("Kiểm tra băng đĩa");
		btnXoaDia = new JButton("Huỷ đĩa",new ImageIcon(".\\image\\delete.png"));
		btnClear = new JButton("Làm mới",new ImageIcon(".\\image\\cancel.png"));
		lbTongNo = new JLabel(TONG_TIEN_NO);
		btnThanhToanNo = new JButton("Thanh toán nợ",new ImageIcon(".\\image\\ic_pay.png"));
		lbTongThue = new JLabel(TONG_TIEN_THUE);
		btnThanhToanThue = new JButton("Thanh toán thuê",new ImageIcon(".\\image\\ic_rent.png"));

		// Service
		khDAO = new KhachHangImpl();
		bdDAO = new BangDiaImpl();
		ptDAO = new PhieuThueImpl();
		ctptDAO = new ChiTietPhieuThueImpl();
		noService = new NoService();
	}

	private void UI() {

		/**
		 * UI
		 */
		tieuDe();
		maKhachHang();
		tenKhachHang();
		soDienThoai();
		diaChi();
		maDia();
		chucNang();
		diaThue();
		no();
		thue();

		/**
		 * cấu hình giao diện
		 */
		settingData();

		listener();

		bTong.add(bTieuDe);
		bTong.add(Box.createVerticalStrut(10));
		bTong.add(bThongTinKhachHang);
		bTong.add(Box.createVerticalStrut(10));
		bTong.add(bDia);
		bTong.add(Box.createVerticalStrut(10));
		bTong.add(bChucNang);
		bTong.add(Box.createVerticalStrut(10));
		bTong.add(scpDiaThue);
		bTong.add(Box.createVerticalStrut(10));
		bTong.add(bNoVaThue);
		bTong.add(Box.createVerticalStrut(10));
		add(bTong);
	}

	/**
	 * Tiêu đề
	 */
	private void tieuDe() {
		lbTieuDe.setFont(new Font("times new roman", 1, 50));
		lbTieuDe.setForeground(new Color(0xFFAA00));
		bTieuDe.add(Box.createHorizontalStrut(10));
		bTieuDe.add(lbTieuDe);
		bTieuDe.add(Box.createHorizontalGlue());
	}

	/**
	 * Mã khách hàng
	 */
	private void maKhachHang() {
		bThongTinKhachHang.add(Box.createHorizontalStrut(10));
		bThongTinKhachHang.add(lbMaKhachHang);
		bThongTinKhachHang.add(txtMaKhachHang);
		txtMaKhachHang.setMaximumSize(new Dimension(200,25));
		txtMaKhachHang.setPreferredSize(new Dimension(200,25));
		bThongTinKhachHang.add(Box.createHorizontalStrut(10));
	}

	/**
	 * Tên khách hàng
	 */
	private void tenKhachHang() {
		bThongTinKhachHang.add(lbTenKhachHang);
		bThongTinKhachHang.add(txtTenKhachHang);

		txtTenKhachHang.setMaximumSize(new Dimension(200,25));
		txtTenKhachHang.setPreferredSize(new Dimension(200,25));
		bThongTinKhachHang.add(Box.createHorizontalStrut(10));
	}

	/**
	 * Số điện thoại
	 */
	private void soDienThoai() {
		bThongTinKhachHang.add(lbSoDienThoai);
		bThongTinKhachHang.add(txtSoDienThoai);
		txtSoDienThoai.setMaximumSize(new Dimension(200,25));
		txtSoDienThoai.setPreferredSize(new Dimension(200,25));
		bThongTinKhachHang.add(Box.createHorizontalStrut(10));
	}

	/**
	 * Địa chỉ
	 */
	private void diaChi() {
		bThongTinKhachHang.add(lbDiaChi);
		bThongTinKhachHang.add(txtDiaChi);
		txtDiaChi.setMaximumSize(new Dimension(200,25));
		txtDiaChi.setPreferredSize(new Dimension(200,25));
		bThongTinKhachHang.add(Box.createHorizontalStrut(10));
	}

	/**
	 * Mã đĩa
	 */
	private void maDia() {
		bDia.add(Box.createHorizontalStrut(500));
		bDia.add(lbMaDia);
		bDia.add(txtMaDia);
		txtMaDia.setMaximumSize(new Dimension(200,25));
		txtMaDia.setPreferredSize(new Dimension(200,25));
		bDia.add(Box.createHorizontalStrut(500));
	}

	/**
	 * Chức năng
	 */
	private void chucNang() {
		bChucNang.add(Box.createHorizontalGlue());
		bChucNang.add(btnKhachHang);
		bChucNang.add(Box.createHorizontalStrut(50));
		bChucNang.add(btnDia);
		bChucNang.add(Box.createHorizontalStrut(50));
		bChucNang.add(btnXoaDia);
		bChucNang.add(Box.createHorizontalStrut(50));
		bChucNang.add(btnClear);
		bChucNang.add(Box.createHorizontalGlue());
		btnDia.setPreferredSize(btnKhachHang.getPreferredSize());
		btnClear.setPreferredSize(btnKhachHang.getPreferredSize());
		btnXoaDia.setPreferredSize(btnKhachHang.getPreferredSize());
	}

	/**
	 * Đĩa thuê
	 */
	private void diaThue() {
		dtmDiaThue = new DefaultTableModel(headerDia, 0);
		tblDiaThue = new JTable(dtmDiaThue) {
			@Override
			public boolean isCellEditable(int row, int column) {
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
		scpDiaThue = new JScrollPane(tblDiaThue);
		TitledBorder tbDiaThue = new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Danh sách đĩa thuê");
		tbDiaThue.setTitleFont(new Font(tbDiaThue.getTitleFont().getFontName(), Font.TRUETYPE_FONT, 20));
		scpDiaThue.setBorder(tbDiaThue);
		settingTableDiaThue(fontTableHeader);
	}

	public void settingTableDiaThue(final Font font) {
		tblDiaThue.getTableHeader().setFont(font);
		tblDiaThue.getTableHeader().setReorderingAllowed(false);
		tblDiaThue.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		int[] columnsWidth = { 150, 150, 150 };
		int i = 0;
		for (int width : columnsWidth) {
			TableColumn column = tblDiaThue.getColumnModel().getColumn(i++);
			column.setMinWidth(width);
			column.setMaxWidth(width);
			column.setPreferredWidth(width);
			column.setResizable(false);
		}
		tblDiaThue.setRowHeight(30);
		tblDiaThue.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	/**
	 * Nợ
	 */
	private void no() {
		bNo.add(lbTongNo);
		bNo.add(btnThanhToanNo);
		lbTongNo.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnThanhToanNo.setAlignmentX(Component.CENTER_ALIGNMENT);
		bNoVaThue.add(Box.createHorizontalGlue());
		bNoVaThue.add(bNo);
		bNoVaThue.add(Box.createHorizontalStrut(100));
	}

	/**
	 * Thuê
	 */
	private void thue() {
		bThue.add(lbTongThue);
		bThue.add(btnThanhToanThue);
		bNoVaThue.add(bThue);
		lbTongThue.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnThanhToanThue.setAlignmentX(Component.CENTER_ALIGNMENT);
		bNoVaThue.add(Box.createHorizontalGlue());
	}

	/**
	 * Mặc định
	 */
	private void settingData() {
		txtTenKhachHang.setEditable(false);
		txtSoDienThoai.setEditable(false);
		txtDiaChi.setEditable(false);
		txtMaDia.setEditable(false);
		btnDia.setEnabled(false);
		btnXoaDia.setEnabled(false);
		btnThanhToanNo.setEnabled(false);
		btnThanhToanThue.setEnabled(false);
		btnClear.setEnabled(false);
	}

	/**
	 * Keylistener
	 */
	private void listener() {
		btnKhachHang.addActionListener(this);
		btnDia.addActionListener(this);
		btnXoaDia.addActionListener(this);
		btnClear.addActionListener(this);
		btnThanhToanThue.addActionListener(this);
		btnThanhToanNo.addActionListener(this);
		tblDiaThue.addMouseListener(this);
		txtMaKhachHangKeyListener();
		txtMaDiaKeyListener();
	}

	@SuppressWarnings("null")
	@Override
	public void actionPerformed(ActionEvent e) {
		KhachHang kh = null;
		BangDia bd = null;
		Object o = e.getSource();
		// btnKhachHang
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
				} else
					txtMaKhachHang.setEditable(false);
				txtTenKhachHang.setText(kh.getTen());
				txtSoDienThoai.setText(kh.getSdt());
				txtDiaChi.setText(kh.getDiaChi());
				txtMaDia.setEditable(true);
				btnDia.setEnabled(true);
				btnKhachHang.setEnabled(false);
				txtMaDia.requestFocus();
				btnClear.setEnabled(true);
				List<BangDia> dsBangDiaDatTruocThanhCong = bdDAO.dsBangDiaDaDatTruocThanhCong(kh.getMa());
				if (!dsBangDiaDatTruocThanhCong.isEmpty()) {
					updateTableNeuCoDatTruoc(dsBangDiaDatTruocThanhCong);
					setlbTongThue();
				}
				int no = noService.xemNo(Integer.parseInt(maKH));
				lbTongNo.setText(TONG_TIEN_NO + df.format(no));
				if (no > 0) {
					btnThanhToanNo.setEnabled(true);
				}
			}
		}
		// btnDia
		if (o == btnDia) {
			String maDia = txtMaDia.getText();
			if (maDia.equals("")) {
				JOptionPane.showMessageDialog(this, "Mã băng đĩa trống");
				txtMaDia.requestFocus(true);
			} else if (!checkDataIsExits(maDia)) {
				bd = bdDAO.timBangDiaCoTheThue(Integer.parseInt(maDia));
				if (bd == null) {
					BangDia newBD  = bdDAO.timBangMa(Integer.parseInt(maDia));
					if(newBD==null) {
						JOptionPane.showMessageDialog(this,"Tìm không thấy!");
					}else {
						int tinhTrang = bdDAO.timBangMa(Integer.parseInt(maDia)).getTinhTrang();
						if (tinhTrang == -1) {
							JOptionPane.showMessageDialog(this, "Không tìm thấy băng đĩa");
						} else if (tinhTrang == BangDia.DA_DAT) {
							JOptionPane.showMessageDialog(this, "Đĩa đã được đặt trước");
						} else {
							JOptionPane.showMessageDialog(this, "Đĩa đang được thuê");
						}
					}
				} else {
					updateTableDiaThue(bd);
					setlbTongThue();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Đã có băng đĩa trong danh sách");
			}
			txtMaDia.setText("");
			txtMaDia.requestFocus(true);
		}
		// btnXoaDia
		if (o == btnXoaDia) {
			int index = tblDiaThue.getSelectedRow();
			if (index != -1) {
				int removeRow = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn xoá", "Xoá đĩa thuê",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (removeRow == JOptionPane.YES_OPTION) {
					dtmDiaThue.removeRow(index);
					BangDia removeBangDia = bdDAO
							.timBangMa(Integer.parseInt(tblDiaThue.getValueAt(index, 0).toString()));
					if (BangDia.DA_DAT == removeBangDia.getTinhTrang()) {
						dsdtDAO = new DanhSachDatTruocImpl();
						DanhSachDatTruoc removeDSDT = dsdtDAO.timTheoTieuDeVaKhachHang(
								removeBangDia.getMaTieuDe().getMa(), removeBangDia.getId_khachHang_hienTai());
						dsdtDAO.xoa(removeDSDT);
						removeBangDia.setTinhTrang(BangDia.TRONG_KHO);
						Integer id_kh = null;
						removeBangDia.setId_khachHang_hienTai(id_kh);
					}
					setlbTongThue();
					if (tblDiaThue.getRowCount() == 0) {
						btnThanhToanThue.setEnabled(false);
					}
				} else {
					tblDiaThue.clearSelection();
					btnXoaDia.setEnabled(false);
				}
			}
		}
		// btnClear
		if (o == btnClear) {
			int clearOption = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn huỷ phiên làm việc", "Huỷ thuê đĩa",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (clearOption == JOptionPane.YES_OPTION) {
				clearSection();
				btnClear.setEnabled(false);
			}
		}
		// btnThanhToanThue
		if (o == btnThanhToanThue) {
			Object m = JOptionPane.showInputDialog(this, "Nhập tiền khách hàng đưa", "Thanh toán phiếu thuê",
					JOptionPane.OK_CANCEL_OPTION, new ImageIcon("image\\ic_rent.png"), null, updateTongTien());
			if (m == null) {
				JOptionPane.showMessageDialog(this, "Bạn đã huỷ thanh toán phiếu thuê");
			} else {
				int tien = Integer.parseInt(m.toString()) - updateTongTien();
				if (tien < 0) {
					JOptionPane.showMessageDialog(this, "Khách hàng đưa thiếu " + df.format(Math.abs(tien)));
				} else {
					if (tien > 0) {
						JOptionPane.showMessageDialog(this, "Tiền thối lại là " + df.format(tien));
					}
					kh = khDAO.timBangMa(Integer.parseInt(txtMaKhachHang.getText().toString()));
					PhieuThue pt = new PhieuThue(new Date(), kh, updateTongTien());
					boolean themPT = ptDAO.them(pt);
					if (themPT) {
						int j = 0;
						String[] ctptKhongThanhCong = {};
						for (int i = 0; i < tblDiaThue.getRowCount(); i++) {
							BangDia bdRow = bdDAO.timBangMa(Integer.parseInt(tblDiaThue.getValueAt(i, 0).toString()));
							System.out.println(bdRow);
							if (bdRow.getTinhTrang() == BangDia.DA_DAT) {
								dsdtDAO = new DanhSachDatTruocImpl();
								System.out.println("476 : "+bdRow.getMaTieuDe().getMa() +" "+bdRow.getId_khachHang_hienTai());
								DanhSachDatTruoc removeDSDT = dsdtDAO.timTheoTieuDeVaKhachHang(bdRow.getMaTieuDe().getMa(), bdRow.getId_khachHang_hienTai());
								System.out.println("479: "+removeDSDT);
								dsdtDAO.xoa(removeDSDT);
							}
							ChiTietPhieuThue ctpt = new ChiTietPhieuThue(pt, bdRow, ChiTietPhieuThue.DANG_THUE);
							boolean themCTPT = ctptDAO.them(ctpt);
							if (themCTPT) {
								bdRow.setTinhTrang(BangDia.DIA_DANG_THUE);
								bdRow.setId_khachHang_hienTai(Integer.parseInt(txtMaKhachHang.getText().toString()));
								bdDAO.sua(bdRow);
							} else {
								ctptKhongThanhCong[j++] = String.valueOf(ctpt.getBangDia().getMa());
							}
						}
						if (ctptKhongThanhCong.length > 0) {
							for (int i = 0; i < ctptKhongThanhCong.length; i++) {
								JOptionPane.showMessageDialog(this,
										"Thêm " + ctptKhongThanhCong[i] + " không thành công");
							}
						} else {
							JOptionPane.showMessageDialog(this, "Thanh toán đĩa thuê thành công");
							clearSection();
						}
					} else {
						JOptionPane.showMessageDialog(this, "Thanh toán không thành công");
					}
				}
			}
		}
		// btnThanhToanNo
		if (o.equals(btnThanhToanNo)) {
			maKH = Integer.parseInt(txtMaKhachHang.getText().trim());
			GUIThanhToanPhiTreHan guiThanhToanPhiTreHan = new GUIThanhToanPhiTreHan(Integer.parseInt(txtMaKhachHang.getText().trim()));
			guiThanhToanPhiTreHan.setMaKH(Integer.parseInt(txtMaKhachHang.getText().trim()));
		}
	}

	/**
	 * Nguyên bản
	 */
	private void clearSection() {
		dtmDiaThue.setRowCount(0);
		btnDia.setEnabled(false);
		btnXoaDia.setEnabled(false);
		btnThanhToanNo.setEnabled(false);
		btnThanhToanThue.setEnabled(false);
		btnKhachHang.setEnabled(true);
		btnClear.setEnabled(false);
		txtMaKhachHang.setText("");
		txtTenKhachHang.setText("");
		txtSoDienThoai.setText("");
		txtDiaChi.setText("");
		txtMaDia.setText("");
		txtMaDia.setEditable(false);
		txtMaKhachHang.setEditable(true);
		txtMaKhachHang.requestFocus();
		lbTongNo.setText(TONG_TIEN_NO);
		lbTongThue.setText(TONG_TIEN_THUE);
	}

	/**
	 * Hiển thị danh sách đĩa đặt trước
	 * 
	 * @param list
	 */
	private void updateTableNeuCoDatTruoc(List<BangDia> list) {
		list.forEach(x -> {
			String[] rowData = { String.valueOf(x.getMa()), x.getMaTieuDe().getTen(),
					x.getMaTieuDe().getLoai() == 0 ? "Phim" : "Game", String.valueOf(x.getSoNgayDuocThue()),
					String.valueOf(x.getPhiThue()) };
			dtmDiaThue.addRow(rowData);
		});
	}

	/**
	 * Cập nhật tiền khi thêm xoá dòng trong bảng
	 */
	private int updateTongTien() {
		int rowCount = tblDiaThue.getRowCount();
		int tongTien = 0;
		if (rowCount > 0) {
			for (int i = 0; i < rowCount; i++) {
				tongTien += Integer.parseInt(tblDiaThue.getValueAt(i, 4).toString());
			}
		}
		return tongTien;
	}

	private void setlbTongThue() {
		lbTongThue.setText(TONG_TIEN_THUE + df.format(updateTongTien()));
		btnThanhToanThue.setEnabled(true);
	}

	/**
	 * Kiểm tra dữ liệu đã có trong bảng chưa
	 * 
	 * @param maBD
	 * @return
	 */
	private boolean checkDataIsExits(String maBD) {
		for (int i = 0; i < dtmDiaThue.getRowCount(); i++) {
			if (dtmDiaThue.getValueAt(i, 0).equals(maBD)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Cập nhật bảng nếu thêm thành công
	 * 
	 * @param bd
	 */
	private void updateTableDiaThue(BangDia bd) {
		String[] rowData = { String.valueOf(bd.getMa()), bd.getMaTieuDe().getTen(),
				bd.getMaTieuDe().getLoai() == 0 ? "Phim" : "Game", String.valueOf(bd.getSoNgayDuocThue()),
				String.valueOf(bd.getPhiThue()) };
		dtmDiaThue.addRow(rowData);
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

	/**
	 * Chỉ nhập số
	 */
	private void txtMaDiaKeyListener() {
		txtMaDia.addKeyListener(new KeyListener() {

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
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		Object o = arg0.getSource();
		if (o.equals(tblDiaThue)) {
			int index = tblDiaThue.getSelectedRow();
			if (index >= 0) {
				btnXoaDia.setEnabled(true);
			} else {
				btnXoaDia.setEnabled(false);
			}
		}
	}
}