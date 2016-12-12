package biblioteca;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class JBiblioteca extends JFrame {

	private JPanel contentPane;
	private JLabel lblBiblioteca;
	private JTextField tf_isbn;
	private JTextField tf_autor;
	private static JTable table;

	static Biblioteca biblio = new Biblioteca();
	static Prestamos prest=new Prestamos();
	static ArrayList<DatosPrestamos> prest2;

	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					biblio.recuperarLibros();
					prest.recuperarAlquileres();
					JBiblioteca frame = new JBiblioteca();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JBiblioteca() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblBiblioteca = new JLabel("BIBLIOTECA");
		lblBiblioteca.setHorizontalAlignment(SwingConstants.CENTER);
		lblBiblioteca.setBounds(10, 11, 414, 14);
		contentPane.add(lblBiblioteca);

		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setBounds(20, 39, 42, 14);
		contentPane.add(lblIsbn);

		tf_isbn = new JTextField();
		tf_isbn.setBounds(72, 36, 86, 20);
		contentPane.add(tf_isbn);
		tf_isbn.setColumns(10);

		JLabel lblAutor = new JLabel("AUTOR:");
		lblAutor.setBounds(282, 39, 46, 14);
		contentPane.add(lblAutor);

		tf_autor = new JTextField();
		tf_autor.setBounds(338, 36, 86, 20);
		contentPane.add(tf_autor);
		tf_autor.setColumns(10);

		JButton bt_consultar = new JButton("CONSULTAR");
		bt_consultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (!tf_isbn.getText().toString().equals("")) {

					Libro libro = new Libro(tf_isbn.getText().toString().toUpperCase(),false);
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					int numcol = table.getModel().getColumnCount();
					Object[] fila = new Object[numcol];
					while (model.getRowCount() > 0) {

						model.removeRow(0);

					}
					Libro libroResult = new Libro();
					libroResult = biblio.buscarISBN(libro);
					fila[0] = libroResult.getAutor();
					fila[1] = libroResult.getTitulo();
					fila[2] = libroResult.getiSBN();
					fila[3] = libroResult.getEstado();
					model.addRow(fila);

				} else if (!tf_autor.getText().toString().equals("")) {

					ArrayList<Libro> libroBusq = new ArrayList<Libro>();
					Libro libro = new Libro(tf_autor.getText().toString().toUpperCase(), "", "",false);
					biblio.buscarAutor(libro, libroBusq);
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					int numcol = table.getModel().getColumnCount();
					Object[] fila = new Object[numcol];
					vaciar();
					for (Libro libroResult : libroBusq) {

						fila[0] = libroResult.getAutor();
						fila[1] = libroResult.getTitulo();
						fila[2] = libroResult.getiSBN();
						model.addRow(fila);

					}
				} else {

					refrescar();

				}
			}
		});
		bt_consultar.setBounds(10, 65, 414, 23);
		contentPane.add(bt_consultar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 99, 414, 116);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"AUTOR", "TITULO", "ISBN", "ESTADO"
			}
		));
		scrollPane.setViewportView(table);

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int numcol = table.getModel().getColumnCount();
		Object[] fila = new Object[numcol];
		for (Libro a : biblio.estanteria) {

			fila[0] = a.getAutor();
			fila[1] = a.getTitulo();
			fila[2] = a.getiSBN();
			fila[3] = a.getEstado();
			model.addRow(fila);

		}

		JButton bt_modificar = new JButton("MODIFICAR");
		bt_modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int fila = table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				String autor = (String) model.getValueAt(fila, 0);
				String titulo = (String) model.getValueAt(fila, 1);
				String ISBN = (String) model.getValueAt(fila, 2);
				boolean estado= (boolean) model.getValueAt(fila, 3);
				Libro libroBusq = new Libro(autor, titulo, ISBN, estado);

				ModificarDialog ventana = new ModificarDialog(libroBusq, biblio.estanteria);
				ventana.setVisible(true);

			}
		});
		bt_modificar.setBounds(10, 228, 129, 23);
		contentPane.add(bt_modificar);

		JButton bt_añadir = new JButton("A\u00D1ADIR");
		bt_añadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AnniadirDialog ventana = new AnniadirDialog(biblio);
				ventana.setVisible(true);

			}
		});
		bt_añadir.setBounds(302, 228, 122, 23);
		contentPane.add(bt_añadir);

		JButton bt_eliminar = new JButton("ELIMINAR");
		bt_eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int res = JOptionPane.showConfirmDialog(null, "¿Desea eliminar este libro?");

				if (JOptionPane.OK_OPTION == res) {
					int pos = table.getSelectedRow();
					biblio.estanteria.remove(pos);
					refrescar();
					JOptionPane.showMessageDialog(null, "Libro eliminado");

				} else if (JOptionPane.NO_OPTION == res) {
					JOptionPane.showMessageDialog(null, "Eliminacion rechazada");
				}

			}
		});
		bt_eliminar.setBounds(149, 228, 143, 23);
		contentPane.add(bt_eliminar);

		JButton btnNewButton = new JButton("SALIR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				biblio.archivarLibros();
				prest.archivarPrestamos();
				dispose();
			}
		});
		btnNewButton.setBounds(149, 258, 143, 23);
		contentPane.add(btnNewButton);
		
		JButton btnAlquileres = new JButton("ALQUILERES");
		btnAlquileres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int fila = table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				String autor = (String) model.getValueAt(fila, 0);
				String titulo = (String) model.getValueAt(fila, 1);
				String ISBN = (String) model.getValueAt(fila, 2);
				boolean estado= (boolean) model.getValueAt(fila, 3);
				Libro libroBusq = new Libro(autor, titulo, ISBN, estado);
				
				PrestamosDialog ventana = new PrestamosDialog(libroBusq, biblio.estanteria, prest.Prestamos);
				ventana.setVisible(true);
				
			}
		});
		btnAlquileres.setBounds(10, 258, 129, 23);
		contentPane.add(btnAlquileres);
		
		JButton btnDevuelto = new JButton("DEVUELTO");
		btnDevuelto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int fila = table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				String autor = (String) model.getValueAt(fila, 0);
				String titulo = (String) model.getValueAt(fila, 1);
				String ISBN = (String) model.getValueAt(fila, 2);
				boolean estado= (boolean) model.getValueAt(fila, 3);
				Libro libroBusq = new Libro(autor, titulo, ISBN, estado);
				
				//Libro libro, ArrayList<Libro> estanteria, ArrayList<Prestamos> prestamo, ArrayList<DatosPrestamos> prest
				devolverDialog ventana = new devolverDialog(libroBusq, biblio.estanteria, prest);
				ventana.setVisible(true);
				
			}
		});
		btnDevuelto.setBounds(302, 258, 122, 23);
		contentPane.add(btnDevuelto);
	}

	public static void vaciar() {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while (model.getRowCount() > 0) {

			model.removeRow(0);

		}

	}

	public static void refrescar() {
		
		vaciar();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int numcol = table.getModel().getColumnCount();
		Object[] fila = new Object[numcol];
		for (Libro a : biblio.estanteria) {

			fila[0] = a.getAutor();
			fila[1] = a.getTitulo();
			fila[2] = a.getiSBN();
			fila[3] = a.getEstado();
			model.addRow(fila);

		}

	}
}
