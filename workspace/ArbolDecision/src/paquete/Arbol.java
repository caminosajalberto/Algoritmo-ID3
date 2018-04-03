package paquete;

import java.util.ArrayList;

public class Arbol {
	private ArrayList<Nodo> arbol;
	
	public Arbol() {
		arbol = new ArrayList<Nodo>();
	}
	
	public Arbol (Nodo nodoInicial) {
		arbol = new ArrayList<Nodo>();
		arbol.add(nodoInicial);
	}
	
	public void addNodo (Nodo n) {
		arbol.add(n);
	}
	
	public Nodo getNodo(int posicion) {
		if (posicion >= 0 && posicion < arbol.size()){
			return arbol.get(posicion);
		}
		return null;
	}
	
	public Nodo getNodoInicial () {
		return arbol.get(0);
	}
	
	public int size () {
		return arbol.size();
	}
	
	public String arbolToJSON () {
		String JSON = "[\n";
		for (int i = 0; i < arbol.size(); ++i) {
			JSON += arbol.get(i).nodoToJSON();
			if(i != arbol.size() - 1) {
				JSON += ",";
			}
		}
		JSON += "]";
		return JSON;
	}
}
