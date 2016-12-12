package biblioteca;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class devolverDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField;
	

	/**
	 * Create the dialog.
	 */
	public devolverDialog(Libro libro, ArrayList<Libro> estanteria, Prestamos prest) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("titulo");
		lblNewLabel_1.setBounds(10, 63, 46, 14);
		contentPanel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(148, 60, 86, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("fecha de devolucion");
		lblNewLabel_2.setBounds(10, 102, 101, 14);
		contentPanel.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(148, 99, 86, 20);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblFechaDevuelto = new JLabel("fecha devuelto");
		lblFechaDevuelto.setBounds(10, 149, 101, 14);
		contentPanel.add(lblFechaDevuelto);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(148, 146, 86, 20);
		textField_1.setText(libro.getTitulo().toString());
		
		DatosPrestamos p1=new DatosPrestamos(null, null, null);
		
//		for(Prestamos p : prest){
//			if(p.buscarLibro(p, libro.getTitulo())) p1=p;
//		}
		
		if(p1.getLibro()==null){
			JOptionPane.showMessageDialog(null, "no se encontro libro");
			return;
		}
		
		textField_1.setText(p1.getF2().toString());
		
		contentPanel.add(textField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
											
						libro.setEstado(true);
						JOptionPane.showMessageDialog(null, "Libro devuelto");
						JOptionPane.showMessageDialog(null, "libro devuelto en");
						JBiblioteca.refrescar();
						dispose();
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						dispose();
						
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
