package paquete;

public class Celda {
	private double p;
	private double n;
	private double r;
	private double informacion;
	
	public Celda (double p, double n, double r) {
		this.p = p;
		this.n = n;
		this.r = r;
		
		double logp;
		double logn;
		if (p == 0) {
			logp = 0;
		}
		else {
			logp = (0 - p) * (Math.log(p) / Math.log(2));
		}
		if (n == 0) {
			logn = 0;
		}
		else {
			logn = n * (Math.log(n) / Math.log(2));
		}
		this.informacion =  logp - logn;
	}
	
	public double getP () {
		return p;
	}
	
	public double getN () {
		return n;
	}
	
	public double getR () {
		return r;
	}
	
	public double getInformacion () {
		return informacion;
	}
}
