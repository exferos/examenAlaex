package biblioteca;

import java.io.Serializable;

public class FechaAlquileres implements Serializable{
	
	public int dd;
	public int mm;
	public int aa;
	public String fecha;
	
	public FechaAlquileres(String dd, String mm, String aa) {
		this.dd = Integer.parseInt(dd);
		this.mm = Integer.parseInt(mm);
		this.aa = Integer.parseInt(aa);
	}
	
	public FechaAlquileres(){
		
	}

	public int getDd() {return dd;}
	public void setDd(int dd) {this.dd = dd;}

	public int getMm() {return mm;}
	public void setMm(int mm) {this.mm = mm;}

	public int getAa() {return aa;}
	public void setAa(int aa) {this.aa = aa;}

	@Override
	public String toString() {
		fecha=dd+"/"+mm+"/"+aa;
		return fecha;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FechaAlquileres other = (FechaAlquileres) obj;
		if (aa != other.aa)
			return false;
		if (dd != other.dd)
			return false;
		if (mm != other.mm)
			return false;
		return true;
	}
	
	public int compararFecha(Object obj){
		
		if(obj.equals(fecha)){
			return 0;
		}else{
			return -1;
		}
		
	}
	
}
