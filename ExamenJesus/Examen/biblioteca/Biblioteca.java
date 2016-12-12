package biblioteca;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Biblioteca {

	ArrayList<Libro> estanteria;

	public Biblioteca() {

		estanteria = new ArrayList<Libro>();

	}

	public boolean insertarLibro(Libro libroNuevo) {

		if (estanteria.contains(libroNuevo)) {

			return false;

		} else {

			estanteria.add(libroNuevo);
			return true;

		}

	}

	final static String nomfich = "10_Libros.obj";

	void recuperarLibros() {
		File fich = new File(nomfich);
		try {
			if (fich.exists()) {
				System.out.println("El fichero existe.");
				FileInputStream fe = new FileInputStream(fich);
				ObjectInputStream fie = new ObjectInputStream(fe);
				Libro a = (Libro) fie.readObject();
				while (a != null) {
					estanteria.add(a);
					System.out.println("En loop.");
					System.out.println(a);
					a = (Libro) fie.readObject();
				}
				fie.close();
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Error de tipo de objeto no compatible.");
		} catch (Exception e) {
			System.out.println("*** Fin de fichero.");
		}
	}

	void archivarLibros() {
		FileOutputStream f = null;
		try {
			f = new FileOutputStream(nomfich);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			ObjectOutputStream fis = new ObjectOutputStream(f);
			for (Libro a : estanteria)
				fis.writeObject(a);
			System.out.println("Se ha escrito " + estanteria.size() + " Libroes en la estanteria.");
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void mostrarestanteriaLibroes(ArrayList<Libro> estanteria) {
		System.out.println("\n\nestanteria de Libroes");
		for (Libro a : estanteria) {
			System.out.println(a + "\n");
		}
	}

	public boolean borrarLibro(Libro libroElim) {

		if (estanteria.contains(libroElim)) {

			for (Libro libro : estanteria) {

				if (libroElim.equals(libro)) {

					int posic = estanteria.indexOf(libro);

					estanteria.remove(posic);

					return true;
				}

			}

		}

		return false;

	}

	public Libro buscarISBN(Libro libroBusq) {

		if (estanteria.contains(libroBusq)) {

			for (Libro libro : estanteria) {

				if (libro.equals(libroBusq)) {

					return libro;

				}

			}

		}

		return null;

	}

	public void buscarAutor(Libro libroBusq, ArrayList<Libro> estanteriaBusq) {



		for (Libro libro : estanteria) {

			if (libro.equals2(libroBusq)) {

				estanteriaBusq.add(libro);

			}

		}

	}

	public ArrayList<Libro> buscarmodific(Libro libroBusq) {

		ArrayList<Libro> estanteriaBusq = new ArrayList<Libro>();

		for (Libro libro : estanteria) {

			if (libro.equals2(libroBusq)) {

				estanteriaBusq.add(libro);

			}

		}

		return estanteriaBusq;

	}

	public boolean modificarLibro(Libro libroModif) {

		for (Libro libro : estanteria) {

			if (libro.equals(libroModif)) {

				libro.setAutor(libroModif.getAutor());
				libro.setTitulo(libroModif.getTitulo());
				return true;
			}

		}

		return false;
	}

}
