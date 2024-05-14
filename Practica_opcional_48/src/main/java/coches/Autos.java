package coches;

import java.util.ArrayList;

public class Autos{

	private ArrayList<Auto> autos;
	public Autos(){}
	public Autos(ArrayList<Auto> auto){
		this.autos = auto;
	}
	
	@Override
	public String toString() {
		return "Autos [auto=" + autos + "]";
	}
	
	public ArrayList<Auto> getAutos() {
		return autos;
	}
	public void setAutos(ArrayList<Auto> auto) {
		this.autos = auto;
	}
	
	public void imprimirAutos() {
		for(int i=0;i<autos.size();i++) {
            autos.get(i).imprimirAuto();
        }
	}
	
	public void añadirAutos(Auto nuevoauto) {
		this.autos.add(nuevoauto);
	}
}
