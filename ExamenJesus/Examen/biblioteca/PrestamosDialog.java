package biblioteca;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrestamosDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tf_libro;
	private JTextField tf_dd_salida;
	private JTextField tf_mm_salida;
	private JTextField tf_aa_salida;
	private JTextField tf_dd_devolucion;
	private JTextField tf_mm_devolucion;
	private JTextField tf_aa_devolucion;
	private JTextField tf_socio;
	private JTextField tf_alquiler;


	/**
	 * Create the dialog.
	 */
	public PrestamosDialog(Libro libro, ArrayList<Libro> estanteria, ArrayList<Prestamos> prestamo) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblLibro = new JLabel("LIBRO");
			lblLibro.setHorizontalAlignment(SwingConstants.CENTER);
			lblLibro.setBounds(10, 11, 105, 14);
			contentPanel.add(lblLibro);
		}
		{
			tf_libro = new JTextField();
			tf_libro.setBounds(125, 8, 299, 20);
			contentPanel.add(tf_libro);
			tf_libro.setColumns(10);
			
			tf_libro.setText(libro.getTitulo().toString());
			tf_libro.setEnabled(false);
		}
		{
			JLabel lblFechaDeSalida = new JLabel("FECHA DE SALIDA");
			lblFechaDeSalida.setBounds(208, 60, 216, 14);
			contentPanel.add(lblFechaDeSalida);
		}
		{
			JLabel lblFechaDeDevolucion = new JLabel("FECHA DE DEVOLUCION");
			lblFechaDeDevolucion.setBounds(208, 133, 216, 14);
			contentPanel.add(lblFechaDeDevolucion);
		}
		{
			tf_dd_salida = new JTextField();
			tf_dd_salida.setBounds(208, 91, 42, 20);
			contentPanel.add(tf_dd_salida);
			tf_dd_salida.setColumns(10);
		}
		{
			tf_mm_salida = new JTextField();
			tf_mm_salida.setBounds(303, 91, 42, 20);
			contentPanel.add(tf_mm_salida);
			tf_mm_salida.setColumns(10);
		}
		{
			tf_aa_salida = new JTextField();
			tf_aa_salida.setBounds(382, 91, 42, 20);
			contentPanel.add(tf_aa_salida);
			tf_aa_salida.setColumns(10);
		}
		{
			tf_dd_devolucion = new JTextField();
			tf_dd_devolucion.setColumns(10);
			tf_dd_devolucion.setBounds(208, 158, 42, 20);
			contentPanel.add(tf_dd_devolucion);
		}
		{
			tf_mm_devolucion = new JTextField();
			tf_mm_devolucion.setColumns(10);
			tf_mm_devolucion.setBounds(303, 158, 42, 20);
			contentPanel.add(tf_mm_devolucion);
		}
		{
			tf_aa_devolucion = new JTextField();
			tf_aa_devolucion.setColumns(10);
			tf_aa_devolucion.setBounds(382, 158, 42, 20);
			contentPanel.add(tf_aa_devolucion);
		}
		{
			JLabel label = new JLabel("/");
			label.setBounds(354, 94, 18, 14);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("/");
			label.setBounds(354, 161, 18, 14);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("/");
			label.setBounds(275, 161, 18, 14);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("/");
			label.setBounds(275, 94, 18, 14);
			contentPanel.add(label);
		}
		{
			JLabel lblSocio = new JLabel("SOCIO");
			lblSocio.setBounds(10, 94, 46, 14);
			contentPanel.add(lblSocio);
		}
		{
			JLabel lblNAlquiler = new JLabel("N\u00BA ALQUILER");
			lblNAlquiler.setBounds(10, 122, 69, 14);
			contentPanel.add(lblNAlquiler);
			
		}
		{
			tf_socio = new JTextField();
			tf_socio.setBounds(89, 91, 86, 20);
			contentPanel.add(tf_socio);
			tf_socio.setColumns(10);
		}
		{
			tf_alquiler = new JTextField();
			tf_alquiler.setBounds(89, 119, 86, 20);
			contentPanel.add(tf_alquiler);
			tf_alquiler.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton bt_alquiler = new JButton("ALQUILAR");
				bt_alquiler.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
							String socio=tf_socio.getText().toString().toUpperCase();
							String titulo=tf_libro.getText().toString().toUpperCase();
							
							FechaAlquileres f1 = new FechaAlquileres(tf_dd_salida.getText().toString(),tf_mm_salida.getText().toString(),tf_aa_salida.getText().toString());
							FechaAlquileres f2 = new FechaAlquileres(tf_dd_devolucion.getText().toString(),tf_mm_devolucion.getText().toString(),tf_aa_devolucion.getText().toString());
							DatosPrestamos prestamo=new DatosPrestamos(socio,titulo,f1,f2);
							
							
							if(Prestamos.insertarPrestamo(prestamo)){
								JOptionPane.showMessageDialog(null, "Alqiler añadido", "Añadir", JOptionPane.INFORMATION_MESSAGE);
								estanteria.remove(libro);
								libro.setEstado(false);
								estanteria.add(libro);
								
								
								
								System.out.println(libro.toString());
								
								
							}else{
								JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
							}
						
						
						
					}
				});
				{
					JButton btnNewButton = new JButton("VER PRESTAMOS");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							MostrarAlquileresDialog ventana = new MostrarAlquileresDialog();
							ventana.setVisible(true);
							
						}
					});
					buttonPane.add(btnNewButton);
				}
				bt_alquiler.setActionCommand("OK");
				buttonPane.add(bt_alquiler);
				getRootPane().setDefaultButton(bt_alquiler);
			}
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
