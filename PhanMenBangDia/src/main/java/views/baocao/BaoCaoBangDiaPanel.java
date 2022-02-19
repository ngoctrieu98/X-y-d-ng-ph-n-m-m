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
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import daos.BangDiaDAO;
import daos.CRUDInterface;
import daos.DanhSachDatTruocDAO;
import entities.BangDia;
import entities.DanhSachDatTruoc;
import entities.TieuDe;
import impls.BangDiaImpl;
import impls.DanhSachDatTruocImpl;
import impls.TieuDeImpl;

public class BaoCaoBangDiaPanel extends JPanel implements ActionListener,MouseListener {
	private JPanel child, pNor;
	JTable tblTieuDe;
	DefaultTableModel deftblTieuDe;
	JLabel lblTitle, lbMaTieuDe;
	JTextField txtMaTieuDe;
	JButton btnTimTieuDe;
	static Font fontChu = new Font("SansSerif", 2, 20);

	public BaoCaoBangDiaPanel(BaoCaoPanel parent) {
		child = parent;
		Box bc, b1, b2, bLeft, bRight;
		bc = Box.createVerticalBox();
		//System.out.println(child.getWidth() + " " + child.getHeight());
		bc.setPreferredSize(new Dimension(child.getWidth() - 500, child.getHeight() - 50));
		// bc.setSize(new Dimension(child.getWidth(),child.getHeight()));
		add(bc);
		// TitledBorder tbDiaDaDat = new
		// TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Danh sách tiêu
		// đề");
		// bc.setBorder(tbDiaDaDat);
		pNor = new JPanel();
		pNor.add(lblTitle = new JLabel("Báo Cáo Tiêu Đề"));
		lblTitle.setFont(new Font("times new roman", Font.BOLD, 50));
		lblTitle.setForeground(new Color(0xFFAA00));
		bc.add(pNor);
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");			
		bc.add(Box.createVerticalStrut(50));
		bc.add(b1 = Box.createHorizontalBox());
			b1.add(Box.createHorizontalStrut(150));
			b1.add(lbMaTieuDe = new JLabel("Ngày hiện tại: "));
			lbMaTieuDe.setFont(fontChu);
			b1.add(new JLabel(dateFormat.format(date)));
			b1.add(Box.createHorizontalStrut(100));
			
		bc.add(Box.createVerticalStrut(50));
		bc.add(b1 = Box.createHorizontalBox());
			b1.add(Box.createHorizontalStrut(400));
			b1.add(lbMaTieuDe = new JLabel("Mã tiêu đề: "));
			lbMaTieuDe.setFont(fontChu);
			b1.add(Box.createHorizontalStrut(20));
			b1.add(txtMaTieuDe = new JTextField(20));
			b1.add(Box.createHorizontalStrut(20));
			b1.add(btnTimTieuDe = new JButton("Tìm kiếm", new ImageIcon(".\\image\\ic_search.png")));
			btnTimTieuDe.setFont(fontChu);
			b1.add(Box.createHorizontalStrut(400));
		JScrollPane scroll;
		bc.add(Box.createVerticalStrut(100));
		int col[] = { 2, 20, 20, 20, 20, 20,20};
		// setColumnWidth(col);
		String[] tieuDe = "Mã;Tiêu Đề;Loại;Số Lượng Đang Thuê;Số Lượng Trong Kho;Số Lượng Đã Đặt; Số Lượng Chưa Xác Nhận; Số Lượng Đã Xác Nhận; Số Lượng Đang Chờ".split(";");
		bc.add(b1 = Box.createHorizontalBox());
		b1.add(b2= Box.createVerticalBox());
		deftblTieuDe = new DefaultTableModel(tieuDe, 0);
		tblTieuDe = new JTable(deftblTieuDe) {
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
			tblTieuDe.getColumnModel().getColumn(i).setCellRenderer(centerRendererKhachHang);
		}
		b2.add(scroll = new JScrollPane(tblTieuDe));
		tblTieuDe.setAutoCreateRowSorter(true);
		JTableHeader header = tblTieuDe.getTableHeader();
		header.setBackground(Color.CYAN);
		header.setOpaque(false);
		// xét cứng cột
		tblTieuDe.getTableHeader().setReorderingAllowed(false);
		TitledBorder tbDsKH = new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Danh sách tiêu đề");
		tbDsKH.setTitleFont(fontChu);
		tbDsKH.setTitleColor(new Color(0xFFAA00));
		b2.setBorder(tbDsKH);
		bc.add(Box.createVerticalStrut(200));


		getDataTieuDe();
		tblTieuDe.addMouseListener(this);
		btnTimTieuDe.addActionListener(this);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	private void xoaDataTableKhachHang() {
		while (tblTieuDe.getRowCount() > 0) {
			deftblTieuDe.removeRow(0);
		}
	}

	private void getDataTieuDe() {
		// TODO Auto-generated method stub
		xoaDataTableKhachHang();
		CRUDInterface<TieuDe> crudInterface = new TieuDeImpl();
		List<TieuDe> listTD = crudInterface.getDanhSach();
		for (TieuDe td : listTD) {
			// DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			String loai ="";
			if(td.getLoai()==TieuDe.GAME) {
				loai = "Game";
			}else if(td.getLoai()==TieuDe.PHIM) {
				loai = "Phim";
			}
			int daDat = 0,dangThue =0,trongKho = 0;
			BangDiaDAO bangDiaDAO = new BangDiaImpl();
			List<BangDia> listBangDia = bangDiaDAO.timTinhTrangTieuDe(td.getMa());
			for(BangDia bd : listBangDia) {
				if(bd.getTinhTrang()==BangDia.DA_DAT) {
					daDat++;
				}else if(bd.getTinhTrang()==BangDia.DIA_DANG_THUE) {
					dangThue++;
				}else if(bd.getTinhTrang()==BangDia.TRONG_KHO) {
					trongKho++;
				}
			}		
			System.out.println("huhuhu "+dangThue);
			int daXN = 0,dangCho =0,chuaXN = 0;
			DanhSachDatTruocDAO dsDanhSachDatTruocDAO = new DanhSachDatTruocImpl();
			List<DanhSachDatTruoc> listDsDT = dsDanhSachDatTruocDAO.timDSDTTheoMaTieuDe(td.getMa());
			for(DanhSachDatTruoc dsdt : listDsDT) {
				if(dsdt.getTinhTrang() == DanhSachDatTruoc.CHUA_XAC_NHAN) {
					chuaXN++;
				}else if(dsdt.getTinhTrang() == DanhSachDatTruoc.DA_XAC_NHAN) {
					daXN++;
				}else if(dsdt.getTinhTrang() == DanhSachDatTruoc.DANG_CHO) {
					dangCho++;
				}
			}
			String[] rowData = { td.getMa() + "", td.getTen() + "", loai, dangThue+"",trongKho+"",daDat+"",chuaXN+"",daXN+"",dangCho+""};
			deftblTieuDe.addRow(rowData);
		}
	}


}
