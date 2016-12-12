package biblioteca;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class AnniadirDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tf_isbn;
	private JTextField tf_autor;
	private JTextField tf_titulo;
	private JLabel lblEstado;
	private JCheckBox cb_disponible;

	

	/**
	 * Create the dialog.
	 */
	public AnniadirDialog(Biblioteca biblio) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("LIBRO");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBounds(10, 11, 414, 14);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("ISBN");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBounds(10, 36, 104, 14);
			contentPanel.add(label);
		}
		{
			tf_isbn = new JTextField();
			tf_isbn.setColumns(10);
			tf_isbn.setBounds(124, 36, 300, 20);
			contentPanel.add(tf_isbn);
		}
		{
			JLabel label = new JLabel("AUTOR");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBounds(10, 70, 104, 14);
			contentPanel.add(label);
		}
		{
			tf_autor = new JTextField();
			tf_autor.setColumns(10);
			tf_autor.setBounds(124, 67, 300, 20);
			contentPanel.add(tf_autor);
		}
		{
			JLabel label = new JLabel("TITULO");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBounds(10, 101, 104, 14);
			contentPanel.add(label);
		}
		{
			tf_titulo = new JTextField();
			tf_titulo.setColumns(10);
			tf_titulo.setBounds(124, 98, 300, 20);
			contentPanel.add(tf_titulo);
		}
		{
			JButton btnAadir = new JButton("A\u00D1ADIR");
			btnAadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					String autor=tf_autor.getText().toString().toUpperCase();
					String titulo=tf_titulo.getText().toString().toUpperCase();
					String iSBN=tf_isbn.getText().toString().toUpperCase();
					boolean estado=cb_disponible.isSelected();
					
					Libro libro=new Libro(autor,titulo,iSBN,estado);
					
					if(biblio.insertarLibro(libro)==true){
						JOptionPane.showMessageDialog(null, "Libro añadido", "Añadir", JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "Error al añadir el libro, ISBN existente", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					tf_autor.setText("");
					tf_titulo.setText("");
					tf_isbn.setText("");
					cb_disponible.setSelected(false);
					
				}
			});
			btnAadir.setBounds(10, 195, 414, 23);
			contentPanel.add(btnAadir);
		}
		
		lblEstado = new JLabel("ESTADO");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setBounds(10, 132, 104, 14);
		contentPanel.add(lblEstado);
		
		cb_disponible = new JCheckBox("DISPONIBLE");
		cb_disponible.setBounds(124, 128, 97, 23);
		contentPanel.add(cb_disponible);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						JBiblioteca.refrescar();
						
						dispose();
						
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
