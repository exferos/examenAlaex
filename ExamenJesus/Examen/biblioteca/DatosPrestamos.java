package biblioteca;

import java.io.Serializable;

public class DatosPrestamos implements Serializable{
	
	private String socio, libro;
	
	private FechaAlquileres f1;
	private FechaAlquileres f2;
	
	public DatosPrestamos(String socio, String libro, FechaAlquileres f1, FechaAlquileres f2) {

		this.socio = socio;
		this.libro = libro;
		this.f1 = f1;
		this.f2 = f2;
		
	}
	
	public DatosPrestamos(String socio, String libro, FechaAlquileres f1) {

		this.socio = socio;
		this.libro = libro;
		this.f1=f1;

		
	}
	
	

	public String getSocio() {
		return socio;
	}

	public void setSocio(String socio) {
		this.socio = socio;
	}

	public String getLibro() {
		return libro;
	}

	public void setLibro(String libro) {
		this.libro = libro;
	}

	public FechaAlquileres getF1() {
		return f1;
	}

	public void setF1(FechaAlquileres f1) {
		this.f1 = f1;
	}

	public FechaAlquileres getF2() {
		return f2;
	}

	public void setF2(FechaAlquileres f2) {
		this.f2 = f2;
	}

	@Override
	public String toString() {
		return "DatosPrestamos [socio=" + socio + ", libro=" + libro + ", f1=" + f1 + ", f2=" + f2 + "]";
	}



	
	
	
	
}
