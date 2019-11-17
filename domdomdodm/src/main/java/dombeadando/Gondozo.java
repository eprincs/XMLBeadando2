package dombeadando;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Gondozo {
	
	private int kartyaszam;
	private String nev;
	private String telefonszam;
	public int getKartyaszam() {
		return kartyaszam;
	}
	public void setKartyaszam(int kartyaszam) {
		this.kartyaszam = kartyaszam;
	}
	public String getNev() {
		return nev;
	}
	public void setNev(String nev) {
		this.nev = nev;
	}
	public String getTelefonszam() {
		return telefonszam;
	}
	public void setTelefonszam(String telefonszam) {
		this.telefonszam = telefonszam;
	}
	
	public static Gondozo create(Node node) {
        Gondozo gondozo = new Gondozo();

        Element element = (Element) node;
        gondozo.kartyaszam = Integer.parseInt(element.getAttribute("kartyaszam"));
        gondozo.nev = element.getAttribute("nev");
        gondozo.telefonszam = element.getAttribute("telefonszam");
        return gondozo;
    }
}
