package dombeadando;



import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Cica {
	
	private int kod;
	private String tulajdonos;
	private String szin;
	private String nev; 
	private int kor;
	private Tulajdonos tulaj;
	public int getKod() {
		return kod;
	}
	public void setKod(int kod) {
		this.kod = kod;
	}
	public String getTulajdonos() {
		return tulajdonos;
	}
	public void setTulajdonos(String tulajdonos) {
		this.tulajdonos = tulajdonos;
	}
	public String getSzin() {
		return szin;
	}
	public void setSzin(String szin) {
		this.szin = szin;
	}
	public String getNev() {
		return nev;
	}
	public void setNev(String nev) {
		this.nev = nev;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public Tulajdonos getTulaj() {
		return tulaj;
	}
	public void setTulaj(Tulajdonos tulaj) {
		this.tulaj = tulaj;
	}

	public static Cica create(Node node) {
        Cica cica = new Cica();

        Element element = (Element) node;
        cica.kod = Integer.parseInt(element.getAttribute("kod"));
        cica.tulajdonos = element.getAttribute("tulajdonos");
        cica.szin = element.getAttribute("szin");
        cica.nev = element.getAttribute("nev");
        cica.kor = Integer.parseInt(element.getAttribute("kor"));
        return cica;
    }
}
