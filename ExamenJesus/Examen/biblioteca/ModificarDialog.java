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
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ModificarDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfISBN;
	private JTextField tfAUTOR;
	private JTextField tfTITULO;


	public ModificarDialog(Libro libro, ArrayList<Libro> estanteria) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblLibro = new JLabel("LIBRO");
		lblLibro.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibro.setBounds(10, 11, 414, 14);
		contentPanel.add(lblLibro);
		
		JLabel lblNewLabel = new JLabel("ISBN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 58, 104, 14);
		contentPanel.add(lblNewLabel);
		
		tfISBN = new JTextField();
		tfISBN.setBounds(124, 55, 300, 20);
		contentPanel.add(tfISBN);
		tfISBN.setColumns(10);
		
		tfISBN.setEnabled(false);
		tfISBN.setText(libro.getiSBN().toString());
		
		JLabel lblAutor = new JLabel("AUTOR");
		lblAutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutor.setBounds(10, 103, 104, 14);
		contentPanel.add(lblAutor);
		
		tfAUTOR = new JTextField();
		tfAUTOR.setColumns(10);
		tfAUTOR.setBounds(124, 100, 300, 20);
		contentPanel.add(tfAUTOR);
		
		tfAUTOR.setText(libro.getAutor().toString());		
		
		JLabel lblTitulo = new JLabel("TITULO");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 148, 104, 14);
		contentPanel.add(lblTitulo);
		
		tfTITULO = new JTextField();
		tfTITULO.setColumns(10);
		tfTITULO.setBounds(124, 145, 300, 20);
		contentPanel.add(tfTITULO);
		
		tfTITULO.setText(libro.getTitulo().toString());
		
		JButton btnNewButton = new JButton("MODIFICAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				String autor = tfAUTOR.getText().toString().toUpperCase();
				String titulo = tfTITULO.getText().toString().toUpperCase();
				
				int res=JOptionPane.showConfirmDialog(null, "¿Desea realizar el cambio?");
								
				if(JOptionPane.OK_OPTION==res){
				
					
					for(Libro libroBusq: estanteria){
						
						if(libroBusq.equals(libro)){
						
							libroBusq.setAutor(autor);
							libroBusq.setTitulo(titulo);
							
							break;
						}
											
					}
							
					JOptionPane.showMessageDialog(null, "Modificacion realizada");
					
				}else if(JOptionPane.NO_OPTION==res){
					
					JOptionPane.showMessageDialog(null, "Modificacion rechazada");
				}
			}
		});
		btnNewButton.setBounds(10, 195, 414, 23);
		contentPanel.add(btnNewButton);
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
				
				JButton btnNewButton_1 = new JButton("Volver");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						JBiblioteca.refrescar();
						
						dispose();
					}
				});
				buttonPane.add(btnNewButton_1);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
