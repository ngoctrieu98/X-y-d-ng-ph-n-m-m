package views.khachhang;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import entities.KhachHang;
import entities.TieuDe;
import impls.KhachHangImpl;
import impls.TieuDeImpl;
import views.MainScreen;
import views.dangnhap.GUILogin;



public class QuanLyKhachHangPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField ma_txt, phiTreHan_txt, phiThue_txt;
	private JTable table;
	private JButton xoaTieuDe_btn, suaTieuDe_btn, addTieuDe_btn, canceladdTieuDe_btn;

	private boolean check = false;

	private List<KhachHang> list = new ArrayList<KhachHang>();

	private DefaultTableModel model;

	private KhachHangImpl dao = new KhachHangImpl();

	private TieuDeImpl tieuDeDao = new TieuDeImpl();

	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	public QuanLyKhachHangPanel() {
		setBounds(0, 0, 1200, 1100);
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);

		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_3 = new JLabel("Quản lý khách hàng");
		panel.add(lblNewLabel_3, BorderLayout.NORTH);

		lblNewLabel_3.setBorder(BorderFactory.createEmptyBorder(2, 15, 0, 0));
		panel.add(lblNewLabel_3, BorderLayout.NORTH);
		lblNewLabel_3.setFont(new Font("times new roman", Font.BOLD, 50));
		lblNewLabel_3.setForeground(new Color(0xFFAA00));

		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_2_1 = new JPanel();
		panel_3.add(panel_2_1);

		JLabel lblNewLabel_1_1 = new JLabel("Mã đĩa: ");
		lblNewLabel_1_1.setPreferredSize(new Dimension(50, 25));
		panel_2_1.add(lblNewLabel_1_1);

		ma_txt = new JTextField();
		ma_txt.setPreferredSize(new Dimension(150, 25));
		panel_2_1.add(ma_txt);

		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);

		Object[] tuaDia = new String[tieuDeDao.getDanhSach().size()];
		int p = 0;
		for (TieuDe tieuDe : tieuDeDao.getDanhSach()) {
			tuaDia[p++] = tieuDe.getTen();
		}

		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2);

		JLabel lblNewLabel_1 = new JLabel("Số điện thoại: ");
		lblNewLabel_1.setPreferredSize(new Dimension(70, 25));
		panel_2.add(lblNewLabel_1);

		phiThue_txt = new JTextField();
		panel_2.add(phiThue_txt);
		phiThue_txt.setPreferredSize(new Dimension(100, 25));

		JPanel panel_2_2 = new JPanel();
		panel_3.add(panel_2_2);

		JLabel lblNewLabel_1_3 = new JLabel("Địa chỉ: ");
		lblNewLabel_1_3.setPreferredSize(new Dimension(75, 25));
		panel_2_2.add(lblNewLabel_1_3);

		phiTreHan_txt = new JTextField();
		phiTreHan_txt.setPreferredSize(new Dimension(100, 25));
		panel_2_2.add(phiTreHan_txt);

		JPanel panel_2_2_1 = new JPanel();
		panel_3.add(panel_2_2_1);


		JPanel panel_4 = new JPanel();
		add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));

		addTieuDe_btn = new JButton("Thêm",new ImageIcon(".\\image\\add.png"));
		//addTieuDe_btn.setMinimumSize(new Dimension(100, 23));
		//addTieuDe_btn.setMaximumSize(new Dimension(100, 23));
		panel_4.add(addTieuDe_btn);

		canceladdTieuDe_btn = new JButton("Hủy",new ImageIcon(".\\image\\cancel.png"));
		//canceladdTieuDe_btn.setMinimumSize(new Dimension(100, 23));
		//canceladdTieuDe_btn.setMaximumSize(new Dimension(100, 23));
		panel_4.add(canceladdTieuDe_btn);

		canceladdTieuDe_btn.setVisible(false);

		suaTieuDe_btn = new JButton("Sửa",new ImageIcon(".\\image\\edit.png"));
		//suaTieuDe_btn.setMinimumSize(new Dimension(100, 23));
		//suaTieuDe_btn.setMaximumSize(new Dimension(100, 23));
		panel_4.add(suaTieuDe_btn);

		xoaTieuDe_btn = new JButton("Xóa",new ImageIcon(".\\image\\delete.png"));
		//xoaTieuDe_btn.setMinimumSize(new Dimension(100, 23));
		//xoaTieuDe_btn.setMaximumSize(new Dimension(100, 23));
		panel_4.add(xoaTieuDe_btn);

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
		model = new DefaultTableModel(new Object[][] {}, new String[] { "Mã khách hàng", "Tên khách hàng", "Số điện thoại",
				"Địa chỉ" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					String.class };

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
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i <4 ; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		backToDefault();

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane.setViewportView(table);
		panel_3.setBorder(BorderFactory.createEmptyBorder(2, 15, 0, 0));

		xoaTieuDe_btn.setEnabled(false);
		suaTieuDe_btn.setEnabled(false);

		loadDataToTable();
		eventButton();
	}

	public void eventButton() {
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

				if (table.getSelectedRow() == -1) {
					xoaTieuDe_btn.setEnabled(false);
					suaTieuDe_btn.setEnabled(false);
				} else {
					xoaTieuDe_btn.setEnabled(true);
					suaTieuDe_btn.setEnabled(true);

					ma_txt.setEnabled(false);

					ma_txt.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					phiThue_txt.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					phiTreHan_txt.setText(table.getValueAt(table.getSelectedRow(), 3).toString());

					phiThue_txt.setEnabled(true);
					phiTreHan_txt.setEnabled(true);

					suaTieuDe_btn.setEnabled(true);
					xoaTieuDe_btn.setEnabled(true);
					canceladdTieuDe_btn.setVisible(false);
					addTieuDe_btn.setText("Thêm");
					check = false;
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		addTieuDe_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				GUILogin guiLogin = null;
				if(MainScreen.checkLogin == MainScreen.logout) {
					guiLogin = new GUILogin();
				}else {
					if (!check) {
						table.clearSelection();
						ma_txt.setText("");

						ma_txt.setEnabled(false);
						phiThue_txt.setText("");
						phiTreHan_txt.setText("");

						phiThue_txt.setEnabled(true);
						phiTreHan_txt.setEnabled(true);

						suaTieuDe_btn.setEnabled(false);
						xoaTieuDe_btn.setEnabled(false);

						addTieuDe_btn.setText("Xác nhận");

						canceladdTieuDe_btn.setVisible(true);
						check = true;
					} else {
						boolean themThanhCong = true;

						String dulieu = JOptionPane.showInputDialog("Chọn số lượng đĩa muốn thêm");

						for (int i = 0; i < Integer.parseInt(dulieu); i++) {
							//BangDia bangDia = new BangDia();
							KhachHang kh = new KhachHang();
							//bangDia.setMaTieuDe(tieuDeDao.timBangTen(loaiBox.getSelectedItem().toString()));
							//bangDia.setPhiThue(Integer.parseInt(phiThue_txt.getText()));
							//bangDia.setPhiTreHan(Integer.parseInt(phiTreHan_txt.getText()));
							//bangDia.setSoNgayDuocThue(Integer.parseInt(soNgayDuocThue_txt.getText()));
							//bangDia.setTinhTrang(BangDia.TRONG_KHO);

							if (dao.them(kh)) {
							} else
								themThanhCong = false;

							System.out.println(dao.them(kh));
						}
						addTieuDe_btn.setText("Thêm");

						if (themThanhCong) {
							check = false;
							loadDataToTable();
							JOptionPane.showMessageDialog(null, "Thêm đĩa thành công!");
							backToDefault();
						} else
							JOptionPane.showMessageDialog(null, "Thêm đĩa không thành công!");

					}

				}
				}
				
		});

		canceladdTieuDe_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ma_txt.setText("");

				ma_txt.setEnabled(false);
				phiThue_txt.setText("");
				phiTreHan_txt.setText("");

				phiThue_txt.setEnabled(true);
				phiTreHan_txt.setEnabled(true);
				suaTieuDe_btn.setEnabled(false);
				xoaTieuDe_btn.setEnabled(false);

				addTieuDe_btn.setText("Thêm");

				canceladdTieuDe_btn.setVisible(false);

				check = false;
			}
		});

		suaTieuDe_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa băng đĩa?", null,
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					/*KhachHang khachHang = dao
							.timBangMa(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));

					khachHang.setSoNgayDuocThue(Integer.parseInt(soNgayDuocThue_txt.getText()));

					khachHang.setPhiThue(Integer.parseInt(phiThue_txt.getText()));

					khachHang.setPhiTreHan(Integer.parseInt(phiTreHan_txt.getText()));

					List<BangDia> dsCTPTChuaBangDia = new ArrayList<BangDia>();

					for (ChiTietPhieuThue ctpt : new ChiTietPhieuThueImpl().getDanhSach())
						dsCTPTChuaBangDia.add(ctpt.getBangDia());

					if (dsCTPTChuaBangDia.contains(bangDia))
						JOptionPane.showMessageDialog(null, "Đĩa đang thuê không được sửa!");
					else if (dao.sua(bangDia)) {
						loadDataToTable();
						JOptionPane.showMessageDialog(null, "Sửa đĩa thành công!");
						backToDefault();

					} else
						JOptionPane.showMessageDialog(null, "Sửa đĩa không thành công!");*/
				}
			}
		});

		xoaTieuDe_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa tiêu đề?", null,
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					/*BangDia bangDia = dao
							.timBangMa(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));

					List<BangDia> dsCTPTChuaBangDia = new ArrayList<BangDia>();

					for (ChiTietPhieuThue ctpt : new ChiTietPhieuThueImpl().getDanhSach())
						dsCTPTChuaBangDia.add(ctpt.getBangDia());

					if (dsCTPTChuaBangDia.contains(bangDia))
						JOptionPane.showMessageDialog(null, "Đĩa đang thuê không được xóa!");
					else if (dao.xoa(bangDia)) {
						loadDataToTable();
						JOptionPane.showMessageDialog(null, "Xóa đĩa thành công!");
						backToDefault();
					} else
						JOptionPane.showMessageDialog(null, "Xóa đĩa không thành công!");*/
				}
			}
		});

	}

	public void loadDataToTable() {
		list = dao.getDanhSach();

		for (int i = table.getRowCount() - 1; i >= 0; i--)
			model.removeRow(i);

		for (KhachHang kh : list) {
			Object[] o = new String[] {kh.getMa()+"",kh.getTen(),kh.getSdt(),kh.getDiaChi()};
			model.addRow(o);
		}
	}

	public void backToDefault() {
		xoaTieuDe_btn.setEnabled(false);
		suaTieuDe_btn.setEnabled(false);

		canceladdTieuDe_btn.setVisible(false);

		check = false;

		addTieuDe_btn.setText("Thêm");

		phiThue_txt.setText("");
		phiTreHan_txt.setText("");
		ma_txt.setText("");

		phiThue_txt.setEnabled(false);
		phiTreHan_txt.setEnabled(false);
		ma_txt.setEnabled(true);

		table.clearSelection();
	}

}
