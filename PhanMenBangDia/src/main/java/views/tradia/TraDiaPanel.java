package views.tradia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import daos.CRUDInterface;
import entities.BangDia;
import entities.ChiTietPhieuThue;
import entities.KhachHang;
import impls.BangDiaImpl;
import impls.ChiTietPhieuThueImpl;
import impls.KhachHangImpl;
import views.phitrehan.GUIThanhToanPhiTreHan;

@SuppressWarnings("serial")
public class TraDiaPanel extends JPanel {
	private JTextField maKH_txt;
	private JTextField tenKH_txt;
	private JTextField sodt_txt;
	private JTextField diaChi_txt;
	private JTextField maDia_txt;
	private JTable table;
	private DefaultTableModel model;
	private JButton traDia_btn;
	private JButton soTienNo_btn;

	private CRUDInterface<KhachHang> khachHangDao = new KhachHangImpl();

	private List<ChiTietPhieuThue> list = new ArrayList<ChiTietPhieuThue>();

	private DecimalFormat format = new DecimalFormat("#,###");

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private ChiTietPhieuThueImpl ctptDAO = new ChiTietPhieuThueImpl();
	public static int maKH;

	public TraDiaPanel() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		add(panel_3, BorderLayout.SOUTH);

		panel_3.setLayout(new BorderLayout(0, 0));

		soTienNo_btn = new JButton("Số tiền nợ: 0đ", new ImageIcon(".\\image\\ic_pay.png"));
		panel_3.add(soTienNo_btn, BorderLayout.WEST);

		traDia_btn = new JButton("Trả đĩa", new ImageIcon(".\\image\\ic_rent.png"));
		panel_3.add(traDia_btn, BorderLayout.EAST);

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_3 = new JLabel("Trả đĩa");
		lblNewLabel_3.setBorder(BorderFactory.createEmptyBorder(2, 15, 0, 0));
		panel.add(lblNewLabel_3, BorderLayout.NORTH);
		lblNewLabel_3.setFont(new Font("times new roman", Font.BOLD, 50));
		lblNewLabel_3.setForeground(new Color(0xFFAA00));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("Mã khách hàng: ");
		panel_1.add(lblNewLabel);

		maKH_txt = new JTextField();
		panel_1.add(maKH_txt);
		maKH_txt.setColumns(10);

		JLabel lblTnKhchHng = new JLabel("Tên khách hàng: ");
		panel_1.add(lblTnKhchHng);

		tenKH_txt = new JTextField();
		tenKH_txt.setEditable(false);
		panel_1.add(tenKH_txt);
		tenKH_txt.setColumns(25);

		JLabel lblSinThoi = new JLabel("Số điện thoại: ");
		panel_1.add(lblSinThoi);

		sodt_txt = new JTextField();
		sodt_txt.setEditable(false);
		sodt_txt.setColumns(10);
		panel_1.add(sodt_txt);

		JLabel lblaCh = new JLabel("Địa chỉ: ");
		panel_1.add(lblaCh);

		diaChi_txt = new JTextField();
		diaChi_txt.setEditable(false);
		diaChi_txt.setColumns(30);
		panel_1.add(diaChi_txt);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);

		JLabel lblNewLabel_1 = new JLabel("Mã đĩa: ");
		panel_2.add(lblNewLabel_1);

		maDia_txt = new JTextField();
		panel_2.add(maDia_txt);
		maDia_txt.setColumns(55);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable() {
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
		table.setRowHeight(50);
		model = new DefaultTableModel(new Object[][] {}, new String[] { "Mã đĩa", "Tựa", "Loại đĩa", "Ngày thuê",
				"Ngày trả", "Ngày phải trả", "Tình trạng", "Phí trễ hạn" }) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					String.class, String.class, String.class };

			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};

		table.setModel(model);

		table.setAutoCreateRowSorter(true);
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.CYAN);
		header.setOpaque(false);
		// xét cứng cột
		table.getTableHeader().setReorderingAllowed(false);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < 6; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		traDia_btn.setEnabled(false);

		scrollPane.setViewportView(table);
		maDia_txt.setEditable(false);
		eventKeyDown();
	}

	private void eventKeyDown() {
		maKH_txt.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char keyChar = e.getKeyChar();
				if (!(Character.isDigit(keyChar) || keyChar == KeyEvent.VK_DELETE
						|| keyChar == KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				for (int i = model.getRowCount() - 1; i >= 0; i--)
					model.removeRow(i);

				if (!maKH_txt.getText().trim().equalsIgnoreCase("")) {
					list = getListByKHID();
					maDia_txt.setEditable(true);

					KhachHang kh = khachHangDao.timBangMa(Integer.parseInt(maKH_txt.getText()));
					if(kh!=null)
					{
						diaChi_txt.setText(kh.getDiaChi());
						tenKH_txt.setText(kh.getTen());
						sodt_txt.setText(kh.getSdt());
					}

					for (ChiTietPhieuThue chiTietPhieuThue : list) {

						String loaiDia = "Phim";
						if (chiTietPhieuThue.getBangDia().getMaTieuDe().getLoai() == 1)
							loaiDia = "Game";

						Date ngayPhaiTra = new Date(chiTietPhieuThue.getPhieuThue().getNgayThue().getTime()
								+ Duration.ofDays(chiTietPhieuThue.getBangDia().getSoNgayDuocThue()).toMillis());

						String tinhTrang = "";

						if (chiTietPhieuThue.getTinhTrang() == ChiTietPhieuThue.NO)
							tinhTrang = "Nợ";
						else if (chiTietPhieuThue.getTinhTrang() == ChiTietPhieuThue.KHONG_NO)
							tinhTrang = "Không nợ";
						else if (chiTietPhieuThue.getTinhTrang() == ChiTietPhieuThue.DANG_THUE)
							tinhTrang = "Đang thuê";

						String ngayTra = "Chưa trả";
						if (chiTietPhieuThue.getNgayTra() != null)
							ngayTra = dateFormat.format(chiTietPhieuThue.getNgayTra());

						Object[] o = new String[] { chiTietPhieuThue.getBangDia().getMa() + "",
								chiTietPhieuThue.getBangDia().getMaTieuDe().getTen(), loaiDia,
								dateFormat.format(chiTietPhieuThue.getPhieuThue().getNgayThue()), ngayTra,
								dateFormat.format(ngayPhaiTra), tinhTrang,
								chiTietPhieuThue.getBangDia().getPhiTreHan() + "đ" };
						model.addRow(o);
					}
					if(kh!=null)
					{
					soTienNo_btn
							.setText("Số tiền nợ: "
									+ format.format(khachHangDao
											.timBangMa(Integer.parseInt(maKH_txt.getText().toString())).getPhiTreHan())
									+ "đ");
					}

				} else {
					maDia_txt.setEditable(false);
					diaChi_txt.setText("");
					tenKH_txt.setText("");
					sodt_txt.setText("");

					soTienNo_btn.setText("Số tiền nợ: 0đ");
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});

		maDia_txt.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				list = getListByDiaID();

				for (int i = model.getRowCount() - 1; i >= 0; i--)
					model.removeRow(i);

				for (ChiTietPhieuThue chiTietPhieuThue : list) {

					String loaiDia = "Phim";
					if (chiTietPhieuThue.getBangDia().getMaTieuDe().getLoai() == 1)
						loaiDia = "Game";

					Date ngayPhaiTra = new Date(chiTietPhieuThue.getPhieuThue().getNgayThue().getTime()
							+ Duration.ofDays(chiTietPhieuThue.getBangDia().getSoNgayDuocThue()).toMillis());

					String tinhTrang = "";

					if (chiTietPhieuThue.getTinhTrang() == ChiTietPhieuThue.NO)
						tinhTrang = "Nợ";
					else if (chiTietPhieuThue.getTinhTrang() == ChiTietPhieuThue.KHONG_NO)
						tinhTrang = "Không nợ";
					else if (chiTietPhieuThue.getTinhTrang() == ChiTietPhieuThue.DANG_THUE)
						tinhTrang = "Đang thuê";

					String ngayTra = "Chưa trả";
					if (chiTietPhieuThue.getNgayTra() != null)
						ngayTra = dateFormat.format(chiTietPhieuThue.getNgayTra());

					Object[] o = new String[] { chiTietPhieuThue.getBangDia().getMa() + "",
							chiTietPhieuThue.getBangDia().getMaTieuDe().getTen(), loaiDia,
							dateFormat.format(chiTietPhieuThue.getPhieuThue().getNgayThue()), ngayTra,
							dateFormat.format(ngayPhaiTra), tinhTrang,
							chiTietPhieuThue.getBangDia().getPhiTreHan() + "đ" };
					model.addRow(o);
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});

		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				if (table.getSelectedRow() != -1)
					traDia_btn.setEnabled(true);
				else
					traDia_btn.setEnabled(false);

				if (model.getValueAt(table.getSelectedRow(), 4).toString().trim().equalsIgnoreCase("Chưa trả"))
					traDia_btn.setEnabled(true);
				else
					traDia_btn.setEnabled(false);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		traDia_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				new Dialog().show();
				int i = table.getSelectedRow();

				ChiTietPhieuThue chiTietPhieuThue = list.get(i);

				chiTietPhieuThue.setNgayTra(new Date());

				Date ngayPhaiTra = new Date(chiTietPhieuThue.getPhieuThue().getNgayThue().getTime()
						+ Duration.ofDays(chiTietPhieuThue.getBangDia().getSoNgayDuocThue()).toMillis());
				if (ngayPhaiTra.before(chiTietPhieuThue.getNgayTra())) {

					ToastMessage message = new ToastMessage(
							"Trả bị trễ hạn, nợ: " + chiTietPhieuThue.getBangDia().getPhiTreHan());
					message.setLocation(getWidth() - 400, getHeight() - 1);
					message.setColor(new Color(0, 0, 0, 250));
					message.setBackground(new Color(255, 0, 0, 250));
					message.display();

					chiTietPhieuThue.setTinhTrang(ChiTietPhieuThue.NO);

					model.setValueAt("Nợ", table.getSelectedRow(), 6);

					KhachHang kh = khachHangDao.timBangMa(Integer.parseInt(maKH_txt.getText()));

					kh.setPhiTreHan(kh.getPhiTreHan() + chiTietPhieuThue.getBangDia().getPhiTreHan());
					khachHangDao.sua(kh);

					model.setValueAt(dateFormat.format(new Date()), table.getSelectedRow(), 4);

					soTienNo_btn
							.setText("Số tiền nợ: "
									+ format.format(khachHangDao
											.timBangMa(Integer.parseInt(maKH_txt.getText().toString())).getPhiTreHan())
									+ "đ");
//				if(list.get(i).get)
				} else {

					ToastMessage message = new ToastMessage("Đã trả đĩa");
					message.setLocation(getWidth() - 400, getHeight() - 1);
					message.setBackground(new Color(0, 255, 0, 250));
					message.display();

					chiTietPhieuThue.setTinhTrang(ChiTietPhieuThue.KHONG_NO);

					model.setValueAt("Không nợ", table.getSelectedRow(), 6);

					model.setValueAt(dateFormat.format(new Date()), table.getSelectedRow(), 4);
				}

				ctptDAO.sua(chiTietPhieuThue);

				BangDiaImpl bangDiaDao = new BangDiaImpl();

				BangDia bd = new BangDia();

				bd = chiTietPhieuThue.getBangDia();

				bd.setTinhTrang(BangDia.TRONG_KHO);

				bangDiaDao.sua(bd);

			}
		});

		soTienNo_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub\
				if (maKH_txt.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập mã khách hàng!");
					maKH_txt.requestFocus();
					return;
				}

				if (khachHangDao.timBangMa(Integer.parseInt(maKH_txt.getText())).getPhiTreHan() == 0) {
					JOptionPane.showMessageDialog(null, "Khách hàng không có nợ!");
					return;
				}

				maKH = Integer.parseInt(maKH_txt.getText().trim());
				GUIThanhToanPhiTreHan guiThanhToanPhiTreHan = new GUIThanhToanPhiTreHan(
						Integer.parseInt(maKH_txt.getText().trim()));
				guiThanhToanPhiTreHan.setMaKH(Integer.parseInt(maKH_txt.getText().trim()));

			}
		});
	}

	private List<ChiTietPhieuThue> getListByKHID() {
		if (!maKH_txt.getText().trim().equalsIgnoreCase(""))
			return ctptDAO.getDanhSachByKhachHangIDDangThue(Integer.parseInt(maKH_txt.getText()));
		else
			return null;
	}

	private List<ChiTietPhieuThue> getListByDiaID() {
		if (!maDia_txt.getText().trim().equalsIgnoreCase(""))
			return ctptDAO.getDanhSachByKhachHangIDVaDiaIDDangThue(Integer.parseInt(maKH_txt.getText()),
					Integer.parseInt(maDia_txt.getText()));
		else
			return getListByKHID();
	}

	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days); // minus number would decrement the days
		return cal.getTime();
	}

}

@SuppressWarnings("serial")
class ToastMessage extends JFrame {
	JLabel mess;

	public ToastMessage(final String message) {
		setUndecorated(true);
		setLayout(new GridBagLayout());
		setBackground(new Color(0, 0, 0, 250));
		setLocationRelativeTo(null);
		setSize(350, 90);
		mess = new JLabel(message);
		add(mess);

		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
			}
		});
	}

	public void display() {
		try {
			setOpacity(1);
			setVisible(true);
			Thread.sleep(2000);

			// hide the toast message in slow motion
			for (double d = 1.0; d > 0.2; d -= 0.1) {
				Thread.sleep(100);
				setOpacity((float) d);
			}

			// set the visibility to false
			setVisible(false);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void setColor(Color c) {
		mess.setForeground(c);
	}
}
