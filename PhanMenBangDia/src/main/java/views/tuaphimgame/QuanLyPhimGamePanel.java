package views.tuaphimgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import entities.TieuDe;
import impls.TieuDeImpl;
import views.MainScreen;
import views.dangnhap.GUILogin;

public class QuanLyPhimGamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField ten_txt;
	private JTextField ma_txt;
	private JTable table;
	private JButton xoaTieuDe_btn, suaTieuDe_btn, addTieuDe_btn, canceladdTieuDe_btn;

	private boolean check = false;

	private List<TieuDe> list = new ArrayList<TieuDe>();

	private DefaultTableModel model;

	@SuppressWarnings("rawtypes")
	private JComboBox loaiBox;

	private CRUDInterface<TieuDe> dao = new TieuDeImpl();

	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	public QuanLyPhimGamePanel() {
		setBounds(0, 0, 1200, 1100);
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);

		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_3 = new JLabel("Quản lý tiêu đề");
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

		JLabel lblNewLabel_1_1 = new JLabel("Mã tựa: ");
		lblNewLabel_1_1.setPreferredSize(new Dimension(50, 25));
		panel_2_1.add(lblNewLabel_1_1);

		ma_txt = new JTextField();
		ma_txt.setPreferredSize(new Dimension(250, 25));
		panel_2_1.add(ma_txt);

		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);

		JLabel lblNewLabel_1_2 = new JLabel("Loại: ");
		panel_5.add(lblNewLabel_1_2);
		lblNewLabel_1_2.setPreferredSize(new Dimension(50, 25));

		loaiBox = new JComboBox();
		loaiBox.setMaximumRowCount(2);
		panel_5.add(loaiBox);
		loaiBox.setEnabled(false);
		loaiBox.setBorder(null);
		loaiBox.setPreferredSize(new Dimension(250, 25));
		loaiBox.setModel(new DefaultComboBoxModel(new String[] { "Phim", "Game" }));

		loaiBox.setSelectedIndex(-1);

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

		model = new DefaultTableModel(new Object[][] {}, new String[] { "Mã tựa", "Tên tựa", "Loại" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					String.class, String.class };

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
		for (int i = 0; i < 3; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane.setViewportView(table);
		panel_3.setBorder(BorderFactory.createEmptyBorder(2, 15, 0, 0));

		xoaTieuDe_btn.setEnabled(false);
		suaTieuDe_btn.setEnabled(false);

		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2);

		JLabel lblNewLabel_1 = new JLabel("Tên tựa: ");
		panel_2.add(lblNewLabel_1);

		lblNewLabel_1.setPreferredSize(new Dimension(50, 25));

		ten_txt = new JTextField();
		panel_2.add(ten_txt);
		ten_txt.setPreferredSize(new Dimension(250, 25));

		ten_txt.setEnabled(false);

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
					ten_txt.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					ma_txt.setText(table.getValueAt(table.getSelectedRow(), 0).toString());

					ten_txt.setEnabled(true);
					loaiBox.setEnabled(true);

					if (table.getValueAt(table.getSelectedRow(), 2).toString().equals("Phim"))
						loaiBox.setSelectedItem("Phim");
					else
						loaiBox.setSelectedItem("Game");

					suaTieuDe_btn.setEnabled(true);
					xoaTieuDe_btn.setEnabled(true);
					canceladdTieuDe_btn.setVisible(false);
					addTieuDe_btn.setText("Thêm");
					check=false;
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

			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getClickCount() == 2) {
					new DetailDialog(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString())).show();
				}
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
						ma_txt.setText("");
						ma_txt.setEnabled(false);
						loaiBox.setEnabled(true);
						ten_txt.setEnabled(true);
						ten_txt.setText("");

						loaiBox.setSelectedIndex(-1);

						suaTieuDe_btn.setEnabled(false);
						xoaTieuDe_btn.setEnabled(false);

						addTieuDe_btn.setText("Xác nhận");

						canceladdTieuDe_btn.setVisible(true);
						check = true;
					} else {

						if (dao.them(new TieuDe(ten_txt.getText().toString(), loaiBox.getSelectedIndex()))) {
							JOptionPane.showMessageDialog(null, "Thêm tiêu đề thành công!");

							loadDataToTable();

							backToDefault();
						} else
							JOptionPane.showMessageDialog(null, "Thêm tiêu đề không thành công!");
						addTieuDe_btn.setText("Thêm");
						canceladdTieuDe_btn.doClick();
					}

				}
				
			}
		});

		canceladdTieuDe_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ma_txt.setText("");

				ma_txt.setEnabled(true);
				ten_txt.setText("");

				loaiBox.setSelectedIndex(-1);

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
				if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa tiêu đề?", null,
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					TieuDe tieuDe = new TieuDe(ten_txt.getText(), loaiBox.getSelectedIndex());

					tieuDe.setMa(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));

					if (dao.sua(tieuDe)) {
						loadDataToTable();
						JOptionPane.showMessageDialog(null, "Sửa tiêu đề có tên " + tieuDe.getTen() + " thành công!");
						backToDefault();

					} else
						JOptionPane.showMessageDialog(null,
								"Sửa tiêu đề có tên " + tieuDe.getTen() + " không thành công!");
				}
			}
		});

		xoaTieuDe_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa tiêu đề?", null,
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					TieuDe tieuDe = new TieuDe(table.getValueAt(table.getSelectedRow(), 1).toString(),
							table.getValueAt(table.getSelectedRow(), 2).toString().equals("Phim") ? TieuDe.PHIM
									: TieuDe.GAME);

					tieuDe.setMa(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));

					if (dao.xoa(tieuDe)) {
						loadDataToTable();
						JOptionPane.showMessageDialog(null, "Xóa tiêu đề có tên " + tieuDe.getTen() + " thành công!");
						backToDefault();
					} else
						JOptionPane.showMessageDialog(null,
								"Xóa tiêu đề có tên " + tieuDe.getTen() + " không thành công!");
				}
			}
		});

		ten_txt.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
					suaTieuDe_btn.doClick();
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		;
	}

	public void loadDataToTable() {
		list = dao.getDanhSach();

		for (int i = table.getRowCount() - 1; i >= 0; i--)
			model.removeRow(i);

		for (TieuDe tieuDe : list) {
			Object[] o = new String[] { tieuDe.getMa() + "", tieuDe.getTen(),
					tieuDe.getLoai() == TieuDe.PHIM ? "Phim" : "Game" };
			model.addRow(o);
		}
	}

	public void backToDefault() {
		xoaTieuDe_btn.setEnabled(false);
		suaTieuDe_btn.setEnabled(false);

		canceladdTieuDe_btn.setVisible(false);

		check = false;

		addTieuDe_btn.setText("Thêm");

		ten_txt.setText("");
		ma_txt.setText("");
		loaiBox.setSelectedIndex(-1);

		ten_txt.setEnabled(false);
		ma_txt.setEnabled(true);

		loaiBox.setEnabled(false);

		table.clearSelection();
	}

}
