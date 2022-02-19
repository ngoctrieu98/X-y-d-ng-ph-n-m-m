package views.tuaphimgame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import daos.BangDiaDAO;
import daos.CRUDInterface;
import entities.BangDia;
import entities.TieuDe;
import impls.BangDiaImpl;
import impls.TieuDeImpl;
import views.tradia.Dialog;

public class DetailDialog extends Dialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JScrollPane scrollPane;

	private DefaultTableModel model;
	private BangDiaDAO dao = new BangDiaImpl();

	private List<BangDia> list = new ArrayList<BangDia>();
	private CRUDInterface<TieuDe> daoTieuDe = new TieuDeImpl();

	public static void main(String[] args) {
		try {
			Dialog dialog = new Dialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DetailDialog(int maTieuDe) {
		setBounds(100, 100, 735, 501);
		getContentPane().setLayout(new BorderLayout());

		setTitle("Mã tiêu đề: " + maTieuDe + " - Tên tiêu đề: " + daoTieuDe.timBangMa(maTieuDe).getTen());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new BorderLayout(0, 0));
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.SOUTH);
			panel.setLayout(new BorderLayout(0, 0));
			{
				scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{

					model = new DefaultTableModel(new Object[][] {},
							new String[] { "Đang có trên kệ", "Đang được thuê", "Đang được đặt" });
					table = new JTable();
					table.setModel(model);

					table.getColumnModel().getColumn(1).setPreferredWidth(120);
					table.getColumnModel().getColumn(2).setPreferredWidth(128);
					scrollPane.setViewportView(table);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.NORTH);
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblNewLabel = new JLabel("Tên tựa đề: ");
					panel_1.add(lblNewLabel, BorderLayout.WEST);
				}
				{
					JLabel tenDia_lbl = new JLabel("Tên đĩa nè");
					panel_1.add(tenDia_lbl, BorderLayout.CENTER);
				}

				panel_1.setVisible(false);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						dispose();
					}
				});
			}
		}
		getData(maTieuDe);

	}

	public void getData(int maTieuDe) {
		Object[] o = new String[] { dao.dsTieuDeCoBangDiaTrongKho(maTieuDe).size() + "",
				dao.dsTieuDeDangDuocThue(maTieuDe).size() + "", dao.dsTieuDeDangDuocDat(maTieuDe).size() + "" };
		model.addRow(o);
	}

}
