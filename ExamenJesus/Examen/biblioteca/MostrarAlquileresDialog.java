package biblioteca;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MostrarAlquileresDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	static Prestamos prest=new Prestamos();
	
	/**
	 * Create the dialog.
	 */
	public MostrarAlquileresDialog() {
		setBounds(100, 100, 516, 345);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 480, 207);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"SOCIO", "FECHA_SALIDA", "TITULO"
			}
		));
		
		
		table.getColumnModel().getColumn(0).setPreferredWidth(46);
		table.getColumnModel().getColumn(1).setPreferredWidth(101);
		table.getColumnModel().getColumn(2).setPreferredWidth(118);
		table.getColumnModel().getColumn(3).setPreferredWidth(88);
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int numcol = table.getModel().getColumnCount();
		Object[] fila = new Object[numcol];
		for (DatosPrestamos a : prest.prestamos) {

			fila[0] = a.getSocio();
			fila[1] = a.getF1().getMm();
			fila[2] = a.getF1().getDd();
			fila[3] = a.getLibro();
			model.addRow(fila);

		}

		
	}
}
