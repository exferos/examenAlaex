package biblioteca;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Prestamos {

	static ArrayList<DatosPrestamos> prestamos;
	private int cont;

	public Prestamos() {

		prestamos = new ArrayList<DatosPrestamos>();

	}

	public static boolean insertarPrestamo(DatosPrestamos prestamoNuevo) {

		if (prestamos.contains(prestamoNuevo)) {

			return false;

		} else {

			prestamos.add(prestamoNuevo);
			return true;

		}

	}
	
	final static String nomfich = "Prestamos09.obj";
	protected static final ArrayList<Prestamos> Prestamos = null;

	void recuperarAlquileres() {
		File fich = new File(nomfich);
		try {
			if (fich.exists()) {
				System.out.println("*******************************");
				System.out.println("El fichero existe.");
				FileInputStream fe = new FileInputStream(fich);
				ObjectInputStream fie = new ObjectInputStream(fe);
				DatosPrestamos a = (DatosPrestamos) fie.readObject();
				while (a != null) {
					prestamos.add(a);
					System.out.println("En loop.");
					System.out.println(a);
					a = (DatosPrestamos) fie.readObject();
					cont++;
				}
				fie.close();
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Error de tipo de objeto no compatible.");
		} catch (Exception e) {
			System.out.println("*** Fin de fichero.");
		}
	}
	
	void archivarPrestamos() {
		FileOutputStream f = null;
		try {
			f = new FileOutputStream(nomfich);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			ObjectOutputStream fis = new ObjectOutputStream(f);
			for (DatosPrestamos a : prestamos)
				fis.writeObject(a);
			System.out.println("Se ha escrito " + prestamos.size() + " Prestamos en el archivo.");
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
//	public boolean buscarLibro(Prestamos obj, String titulo) {
//		if(obj.getLibro().equals(titulo)) return true;
//		return false;
//	}
		
	
}
