package paquete;

import java.util.ArrayList;

/**
 * Clase que representa una tabla de datos
 * @author Alberto
 *
 */
public class Tabla {
	/**
	 * Filas de la tabla
	 */
	private int filas;
	/**
	 * columnas de la tabla
	 */
	private int columnas;
	/**
	 * Tabla
	 */
	private ArrayList<ArrayList<String>> tabla;
	
	private static ArrayList<ArrayList<String>> opcionesAtributo;
	private static ArrayList<String> atributosIniciales;
	
	/**
	 * Constructor de una tabla vacia
	 */
	public Tabla () {
		filas = 0;
		columnas = 0;
		tabla = new ArrayList<ArrayList<String>>();
	}
	
	/**
	 * Constructor de una tabla dada por par�metro
	 * @param datos datos de la tabla
	 * @param atributos atributos de la tabla de datos
	 */
	public Tabla (ArrayList<ArrayList<String>> datos, ArrayList<String> atributos) {
		filas = datos.size() + 1;
		columnas = datos.get(0).size();
		
		tabla = new ArrayList<ArrayList<String>>();
		tabla.add(atributos);
		tabla.addAll(datos);
		obtenerPosibilidadesIniciales();
	}
	
	public void obtenerPosibilidadesIniciales () {
		ArrayList<ArrayList<String>> aux = new ArrayList<ArrayList<String>>();
		
		for (int i = 0; i < tabla.get(0).size(); ++i) {
			aux.add(this.getPosibilidades(tabla.get(0).get(i)));
		}
		opcionesAtributo = aux;
		atributosIniciales = tabla.get(0);
		System.out.println();
	}
	
	/**
	 * A�ade los atributos a una tabla
	 * @param atributos atributos de la tabla
	 */
	public void a�adirAtributos (ArrayList<String> atributos) {
		if (filas == 0) filas++;
		columnas = atributos.size();
		
		if(tabla.size() > 0) {
			tabla.remove(0);
			tabla.add(0, atributos);
		}
		else {
			tabla.add(atributos);
		}
	}
	
	/**
	 * Devuelve el numero de datos de la tabla
	 * @return numero de datos de la tabla
	 */
	public int getNumDatos () {
		return tabla.size() - 1;
	}
	
	/**
	 * A�ade una nueva fila de datos a la tabla
	 * @param dato array de strings con los datos correspondiente a los atributos
	 */
	public void a�adirDato (ArrayList<String> dato) {
		tabla.add(dato);
		filas++;
	}
	
	/**
	 * Obtiene una fila de datos de la tabla
	 * @param posicion posicion del dato que se quiere obtener (se a�ade 1 a la posicion ya que en la tabla la primera fila es de atributos)
	 * @return
	 */
	public ArrayList<String> getDato (int posicion) {
		if(posicion < tabla.size() && posicion >= 1) {
			return tabla.get(posicion);
		}
		return null;
	}
	
	/**
	 * Obtiene el nombre de un atributo en una posicion dada
	 * @param posicion posicion del atributo que se quiere obtener
	 * @return el valor del atributo o null si la posicion no es valida
	 */
	public String getNombreAtributo (int posicion) {
		if(posicion < tabla.get(0).size() && posicion >= 0) {
			return tabla.get(0).get(posicion);
		}
		return null;
	}
	
	/**
	 * Devuelve la posicion que ocupa en la tabla un determinado atributo
	 * @param atributo atributo a buscar
	 * @return la posicion del atributo o -1 si no se encuentra
	 */
	public int getPosicionAtributo (String atributo) {
		for(int i = 0; i < tabla.get(0).size(); ++i) {
			if(atributo.equals(tabla.get(0).get(i))) {
				return i;
			}		
		}
		return -1;
	}
	
	/**
	 * Devuelve la posicion que ocupa en la tabla un determinado atributo
	 * @param atributo atributo a buscar
	 * @return la posicion del atributo o -1 si no se encuentra
	 */
	private int getPosicionAtributoInicial (String atributo) {
		for(int i = 0; i < atributosIniciales.size(); ++i) {
			if(atributo.equals(atributosIniciales.get(i))) {
				return i;
			}		
		}
		return -1;
	}
	
	/**
	 * Devuelve todos los atributos de una tabla
	 * @return atributos de una tabla
	 */
	public ArrayList<String> getAtributos () {
		return tabla.get(0);
	}
	
	/**
	 * Devuelve un valor de un determinado atributo de un dato
	 * @param dato posicion del dato que se est� buscando
	 * @param atributo atributo del cual se quiere saber su valor
	 * @return valor del atributo de ese dato o null si no se encuentra o el dato no es valido
	 */
	public String getValorDato (int dato, String atributo) {
		int pos = this.getPosicionAtributo(atributo);
		if(pos == -1 || dato >= tabla.size() || dato < 1) return null;
		
		return tabla.get(dato).get(pos);
	}
	
	/**
	 * Devuelve si la tabla tiene datos o no (aparte de los atributos)
	 * @return true o false, indicando si tiene o no atributos
	 */
	public boolean tieneDatos () {
		return tabla.size() > 1;
	}
	
	/**
	 * Funcion que devuelve si la decisi�n en la tabla es todo "s�" o todo "no", en cuyo caso devuelve si o no, o null si no todos son si o no
	 * @return Valor positivo o negativo de decisi�n, o null si no son todos iguales
	 */
	public String getValorDecisionTabla () {
		if(tabla.size() <= 1) {
			return "No hay datos";
		}
		String decision = tabla.get(1).get(tabla.get(1).size() - 1);
		for(int i = 2; i < tabla.size(); ++i) {
			if (!decision.equals(tabla.get(i).get(tabla.get(i).size() - 1))) {
				return null;
			}
		}
		return decision;
	}
	
	/**
	 * Devuelve los distintos valores que toma un atributo en concreto en los datos
	 * @param atributo atributo a buscar
	 * @return array de Strings con las posibilidades que toma ese atributo
	 */
	private ArrayList<String> getPosibilidades (String atributo) {
		ArrayList<String> aux = new ArrayList<String>();
		int posicion = this.getPosicionAtributo(atributo);
		
		for (int i = 1; i < tabla.size(); ++i) {
			boolean encontrado = false;
			for(int j = 0; j < aux.size(); ++j) {
				if(aux.get(j).equals(tabla.get(i).get(posicion))) {
					encontrado = true;
				}
			}
			if(!encontrado) {
				aux.add(tabla.get(i).get(posicion));
			}
		}
		return aux;
	}
	
	/**
	 * Devuelve los distintos valores que toma un atributo en concreto en los datos iniciales
	 * @param atributo atributo a buscar
	 * @return array de Strings con las posibilidades que toma ese atributo
	 */
	public ArrayList<String> getPosibilidadesAtributo (String atributo) {
		int pos = this.getPosicionAtributoInicial(atributo);
		return opcionesAtributo.get(pos);
	}
	
	/**
	 * Devuelve el numero de apariciones de un determinado valor de un atributo
	 * @param atributo atributo en cuesti�n
	 * @param valor valor que toma el atributo
	 * @return numero de apariciones de ese valor en un atributo
	 */
	public double numAparicionesValorAtributo(String atributo, String valor) {
		int posicion = this.getPosicionAtributo(atributo);
		int contador = 0;
		
		for (int i = 1; i < tabla.size(); ++i){
			if(tabla.get(i).get(posicion).equals(valor)) {
				contador++;
			}
		}
		return contador;
	}
	
	/**
	 * Devuelve el numero de apariciones de un valor en un atributo de la tabla y si adem�s la decisi�n es positiva
	 * @param atributo
	 * @param valor
	 * @return
	 */
	public double numAparicionesValorAtributoPositivo (String atributo, String valor) {
		int posicion = this.getPosicionAtributo(atributo);
		int contador = 0;
		
		for (int i = 1; i < tabla.size(); ++i){
			if(tabla.get(i).get(posicion).equals(valor) && tabla.get(i).get(tabla.get(i).size() - 1).equals("si")) {
				contador++;
			}
		}
		return contador;
	}
	
	/**
	 * Calcula el m�rito de los atributos de la tabla y devuelve el que menor m�rito tenga
	 * @return atributo con menor merito
	 */
	public String atributoMenorMerito () {
		ArrayList <String> atributos = (ArrayList<String>) tabla.get(0).clone();
		atributos.remove(atributos.size() - 1);
		double minMerito = Double.POSITIVE_INFINITY;
		String nombreAtributo = null;
		
		for (String atributo : atributos) {
			ArrayList<String> valores = this.getPosibilidades(atributo);
			ArrayList<Celda> celdas = new ArrayList<Celda>();
			for (String valor : valores ) {
				double numAp = this.numAparicionesValorAtributo(atributo, valor);
				double p = this.numAparicionesValorAtributoPositivo(atributo, valor) / numAp;
				double n = (numAp - this.numAparicionesValorAtributoPositivo(atributo, valor)) / numAp;
				double r = numAp / this.getNumDatos();
				celdas.add(new Celda (p, n, r));
			}
			double merito = 0;
			for (Celda c : celdas) {
				merito += c.getR() * c.getInformacion();
			}
			if (merito < minMerito) {
				minMerito = merito;
				nombreAtributo = atributo;
			}
		}
		return nombreAtributo;
		
	}
	
	/**
	 * Construye una nueva tabla eliminando el atributo pasado por par�metro y con el valor del atributo pasado
	 * @param atributo atributo que hay que eliminar de la tabla
	 * @param valor valor del atributo a coger para la nueva tabla
	 * @return nueva tabla
	 */
	public Tabla getNuevaTabla (String atributo, String valor) {
		Tabla nueva = new Tabla();
		ArrayList<String> atributos = (ArrayList<String>) tabla.get(0).clone();
		int posAtributo = this.getPosicionAtributo(atributo);
		atributos.remove(posAtributo);
		
		nueva.a�adirAtributos(atributos);
		
		for (int i = 1; i < tabla.size(); ++i) {
			ArrayList<String> dato = new ArrayList<String>();
			int auxPos = 0;
			boolean aniadir = false;
			for(String value : tabla.get(i)) {
				if (!tabla.get(0).get(auxPos).equals(atributo)) {
					if(tabla.get(i).get(posAtributo).equals(valor)) {
						dato.add(value);
						aniadir = true;
					}
				}
				auxPos++;
			}
			if (aniadir) { nueva.a�adirDato(dato); }
		}
		return nueva;
		
	}

}
