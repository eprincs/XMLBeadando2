package dombeadando;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Tulajdonos {
	
	private String szigszam;
	private String nev;
	
	public String getSzigszam() {
		return szigszam;
	}
	public void setSzigszam(String szigszam) {
		this.szigszam = szigszam;
	}
	public String getNev() {
		return nev;
	}
	public void setNev(String nev) {
		this.nev = nev;
	}
	
	public static Tulajdonos create(Node node) {
        Tulajdonos tulajdonos = new Tulajdonos();

        Element element = (Element) node;
        tulajdonos.szigszam = element.getAttribute("szigszam");
        tulajdonos.nev = element.getAttribute("nev");
        return tulajdonos;
    }
}
