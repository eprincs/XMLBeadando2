package dombeadando;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Kapcsolat {
	
	private int cicaId;
	private int gondozoId;
	private Cica cica;
	private Gondozo gondozo;
	
	public int getCicaId() {
		return cicaId;
	}
	public void setCicaId(int cicaId) {
		this.cicaId = cicaId;
	}
	public int getGondozoId() {
		return gondozoId;
	}
	public void setGondozoId(int gondozoId) {
		this.gondozoId = gondozoId;
	}
	public Cica getCica() {
		return cica;
	}
	public void setCica(Cica cica) {
		this.cica = cica;
	}
	public Gondozo getGondozo() {
		return gondozo;
	}
	public void setGondozo(Gondozo gondozo) {
		this.gondozo = gondozo;
	}
	
	public static Kapcsolat create(Node node) {
        Kapcsolat kapcsolat = new Kapcsolat();

        Element element = (Element) node;
        kapcsolat.cicaId = Integer.parseInt(element.getAttribute("cica"));
        kapcsolat.gondozoId = Integer.parseInt(element.getAttribute("gondozo"));
        return kapcsolat;
    }
}
