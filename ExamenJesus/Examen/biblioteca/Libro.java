package biblioteca;

import java.io.Serializable;

public class Libro implements Serializable{
	
	private String autor;
	private String titulo;
	private String iSBN;
	private boolean estado;
	
	public Libro(String autor, String titulo, String iSBN, boolean estado){
		this.autor=autor;
		this.titulo=titulo;
		this.iSBN=iSBN;
		this.estado=estado;
	}
	
	public Libro(String iSBN, boolean estado){
		this.iSBN=iSBN;
		this.estado=estado;
	}

	
	public Libro() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return autor+", "+titulo+", "+iSBN+", "+estado;
	}

	public String getAutor() {return autor;}
	public void setAutor(String autor) {this.autor = autor;}

	public String getTitulo() {return titulo;}
	public void setTitulo(String titulo) {this.titulo = titulo;}

	public String getiSBN() {return iSBN;}
	public void setiSBN(String iSBN) {this.iSBN = iSBN;}
	
	public boolean getEstado(){return estado;}
	public void setEstado(boolean estado){this.estado=estado;}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		if (iSBN == null) {
			if (other.iSBN != null)
				return false;
		} else if (!iSBN.equals(other.iSBN))
			return false;
		
		return true;
	}
	
	public boolean equals2(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		
		return true;
	}
	
}
