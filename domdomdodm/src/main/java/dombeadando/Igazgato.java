package dombeadando;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Igazgato {
	
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
	
	public static Igazgato create(Node node) {
        Igazgato igazgato = new Igazgato();

        Element element = (Element) node;
        igazgato.szigszam = element.getAttribute("szigszam");
        igazgato.nev = element.getAttribute("nev");
        return igazgato;
    }

}
