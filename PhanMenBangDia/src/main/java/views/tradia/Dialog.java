package views.tradia;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Dialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
private JLabel tenDia_lbl, lblNewLabel_2;
	/**
	 * Launch the application.
	 */
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
	public Dialog() {
		setBounds(100, 100, 450, 210);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JLabel lblNewLabel = new JLabel("Tên đĩa: ");
				panel.add(lblNewLabel, BorderLayout.WEST);
			}
			{
				tenDia_lbl = new JLabel("Tên đĩa nè");
				panel.add(tenDia_lbl, BorderLayout.CENTER);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.SOUTH);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JLabel lblNgyThu = new JLabel("Ngày thuê: ");
				panel.add(lblNgyThu, BorderLayout.WEST);
			}
			{
				JLabel ngayThue_lbl = new JLabel("Tên đĩa nè");
				panel.add(ngayThue_lbl, BorderLayout.CENTER);
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.SOUTH);
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblNewLabel = new JLabel("Tên đĩa: ");
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
				JButton okButton = new JButton("Okay");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.EAST);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.SOUTH);
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblNewLabel_1 = new JLabel("Tổng tiền");
					lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
					panel_1.add(lblNewLabel_1, BorderLayout.NORTH);
				}
				{
					JLabel lblNewLabel_2 = new JLabel("50.000đ");
					panel_1.add(lblNewLabel_2, BorderLayout.EAST);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.NORTH);
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblNewLabel_1 = new JLabel("Tiền thuê");
					lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
					panel_1.add(lblNewLabel_1, BorderLayout.NORTH);
				}
				{
					lblNewLabel_2 = new JLabel("50.000đ");
					panel_1.add(lblNewLabel_2, BorderLayout.EAST);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.CENTER);
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblNewLabel_1 = new JLabel("Tiền nợ");
					lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
					panel_1.add(lblNewLabel_1, BorderLayout.NORTH);
				}
				{
					JLabel lblNewLabel_2 = new JLabel("50.000đ");
					panel_1.add(lblNewLabel_2, BorderLayout.EAST);
				}
			}
		}
	}

}
