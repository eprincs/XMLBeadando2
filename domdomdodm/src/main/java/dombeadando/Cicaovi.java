package dombeadando;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Cicaovi {

	private static final String CICAOVI_TAG = "cicaovi";
	private static final String CICA_TAG = "cica";
	private static final String GONDOZO_TAG = "gondozo";
	private static final String TULAJDONOS_TAG = "tulajdonos";
	private static final String IGAZGATO_TAG = "igazgato";
	private static final String KAPCSOLAT_TAG = "kapcsolat";

	private Document root;
	private List<Cica> cicak;
	private List<Gondozo> gondozok;
	private List<Tulajdonos> tulajdonosok;
	private List<Igazgato> igazgatok;
	private List<Kapcsolat> kapcsolatok;

	public Cicaovi(Document root, List<Cica> cicak, List<Gondozo> gondozok, List<Tulajdonos> tulajdonosok,
			List<Igazgato> igazgatok, List<Kapcsolat> kapcsolatok) {
		this.root = root;
		this.cicak = cicak;
		this.gondozok = gondozok;
		this.tulajdonosok = tulajdonosok;
		this.igazgatok = igazgatok;
		this.kapcsolatok = kapcsolatok;
	}

	public static Cicaovi create(Document document) {
		Element root = document.getDocumentElement();
		if (!root.getNodeName().equals(CICAOVI_TAG)) {
			throw new IllegalArgumentException("nem cicaovi a gyökérelem");
		}

		NodeList nodeList = root.getElementsByTagName(KAPCSOLAT_TAG);
		List<Kapcsolat> kapcsolatok = new ArrayList<>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			kapcsolatok.add(Kapcsolat.create(node));
		}

		nodeList = root.getElementsByTagName(CICA_TAG);
		List<Cica> cicak = new ArrayList<>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			cicak.add(Cica.create(node));
		}

		nodeList = root.getElementsByTagName(TULAJDONOS_TAG);
		List<Tulajdonos> tulajdonosok = new ArrayList<>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			tulajdonosok.add(Tulajdonos.create(node));
		}
		nodeList = root.getElementsByTagName(IGAZGATO_TAG);
		List<Igazgato> igazgatok = new ArrayList<>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			igazgatok.add(Igazgato.create(node));
		}
		nodeList = root.getElementsByTagName(GONDOZO_TAG);
		List<Gondozo> gondozok = new ArrayList<>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			gondozok.add(Gondozo.create(node));
		}

		Cicaovi cicaovi = new Cicaovi(document, cicak, gondozok, tulajdonosok, igazgatok, kapcsolatok);

		cicak.forEach(cica -> cica.setTulaj(cicaovi.getTulajById(cica.getTulajdonos())));
		kapcsolatok.forEach(kapcsolat -> kapcsolat.setCica(cicaovi.getCicaById(kapcsolat.getCicaId())));
		kapcsolatok.forEach(kapcsolat -> kapcsolat.setGondozo(cicaovi.getGondozoById(kapcsolat.getGondozoId())));
		
		
		return cicaovi;
	}

	public Cica getCicaById(int kod) {
		return cicak.stream().filter(cica -> cica.getKod() == kod).findFirst().orElseGet(Cica::new);
	}

	public Gondozo getGondozoById(int kartyaszam) {
		return gondozok.stream().filter(gondozo -> gondozo.getKartyaszam() == kartyaszam).findFirst()
				.orElseGet(Gondozo::new);
	}

	public Tulajdonos getTulajById(String szigszam) {
		return tulajdonosok.stream().filter(tulajdonos -> tulajdonos.getSzigszam().equals(szigszam)).findFirst()
				.orElseGet(Tulajdonos::new);
	}

	public void addCica(Cica cica) {
		Element element = root.createElement(CICA_TAG);
		element.setAttribute("kod", Integer.toString(cica.getKod()));
		element.setAttribute("tulajdonos", cica.getTulaj().getSzigszam());
		element.setAttribute("nev", cica.getNev());
		element.setAttribute("szin", cica.getSzin());
		element.setAttribute("kor", Integer.toString(cica.getKor()));

		root.getDocumentElement().appendChild(element);
		cicak.add(cica);
	}

	public void addGondozo(Gondozo gondozo) {
		Element element = root.createElement(GONDOZO_TAG);
		element.setAttribute("kartyaszam", Integer.toString(gondozo.getKartyaszam()));
		element.setAttribute("nev", gondozo.getNev());
		element.setAttribute("telefonszam", gondozo.getTelefonszam());

		root.getDocumentElement().appendChild(element);
		gondozok.add(gondozo);
	}

	public void addIgazgato(Igazgato igazgato) {
		Element element = root.createElement(IGAZGATO_TAG);
		element.setAttribute("szigszam", igazgato.getSzigszam());
		element.setAttribute("nev", igazgato.getNev());

		root.getDocumentElement().appendChild(element);
		igazgatok.add(igazgato);
	}

	public void addTulajdonos(Tulajdonos tulajdonos) {
		Element element = root.createElement(TULAJDONOS_TAG);
		element.setAttribute("szigszam", tulajdonos.getSzigszam());
		element.setAttribute("nev", tulajdonos.getNev());

		root.getDocumentElement().appendChild(element);
		tulajdonosok.add(tulajdonos);
	}

	public void addKapcsolat(Kapcsolat kapcsolat) {
		Element element = root.createElement(KAPCSOLAT_TAG);
		element.setAttribute("cica", Integer.toString(kapcsolat.getCica().getKod()));
		element.setAttribute("gondozo", Integer.toString(kapcsolat.getGondozo().getKartyaszam()));

		root.getDocumentElement().appendChild(element);
		kapcsolatok.add(kapcsolat);
	}

	public Cica searchCicaByKod(int kod) {
		for (Cica cica : cicak) {
			if (cica.getKod() == kod) {
				return cica;
			}
		}
		return null;
	}

	public Gondozo searchGondozoByKartyaszam(int kartyaszam) {
		for (Gondozo gondozo : gondozok) {
			if (gondozo.getKartyaszam() == kartyaszam) {
				return gondozo;
			}
		}
		return null;
	}

	public Igazgato searchIgazgatoBySzigszam(String szigszam) {
		for (Igazgato igazgato : igazgatok) {
			if (igazgato.getSzigszam().equals(szigszam)) {
				return igazgato;
			}
		}
		return null;
	}

	public Tulajdonos searchTulajdonosBySzigszam(String szigszam) {
		for (Tulajdonos tulajdonos : tulajdonosok) {
			if (tulajdonos.getSzigszam().equals(szigszam)) {
				return tulajdonos;
			}
		}
		return null;
	}

	public Tulajdonos searchTulajdonosByCica(Cica cica) {
		for (Tulajdonos tulaj : tulajdonosok) {
			System.out.println(cica.getTulaj().getSzigszam());
			System.out.println(tulaj.getSzigszam());
			if (tulaj.equals(cica.getTulaj())) {
				return tulaj;
			}
		}
		return null;
	}

	public List<Cica> searchCicakByTulajdonos(Tulajdonos tulajdonos) {
		List<Cica> cicakok = new ArrayList<>();
		for (Cica cica : cicak) {
			if (tulajdonos.equals(cica.getTulaj())) {
				cicakok.add(cica);
			}
		}
		return cicakok;
	}

	public List<Cica> searchCicakByGondozo(Gondozo gondozo) {
		List<Cica> cicakok = new ArrayList<>();
		for (Cica cica : cicak) {
			for (Kapcsolat kapcsolat : kapcsolatok) {
				if (kapcsolat.getGondozoId() == gondozo.getKartyaszam() && kapcsolat.getCicaId() == cica.getKod()) {
					cicakok.add(cica);
				}
			}
		}
		return cicakok;
	}

	public List<Gondozo> searchGondozokByCica(Cica cica) {
		List<Gondozo> gondozocskak = new ArrayList<>();
		for (Gondozo gondozo : gondozok) {
			for (Kapcsolat kapcsolat : kapcsolatok) {
				if (kapcsolat.getGondozoId() == gondozo.getKartyaszam() && kapcsolat.getCicaId() == cica.getKod()) {
					gondozocskak.add(gondozo);
				}
			}
		}
		return gondozocskak;
	}

	public Boolean checkCicaId(String kod) {
		for (Cica cica : cicak) {
			if (Integer.toString(cica.getKod()).equals(kod)) {
				return false;
			}
		}
		return true;
	}

	public Boolean checkCicaIdAndGondozoId(String gondozoid, String cicaid) {
		for (Kapcsolat kapcsolat : kapcsolatok) {
			if(Integer.toString(kapcsolat.getCicaId()).equals(cicaid) && (Integer.toString(kapcsolat.getGondozoId()).equals(gondozoid)))
				return true;
		}
		return false;
	};
	public Boolean checkGondozoId(String kartyaszam) {
		for (Gondozo gondozo : gondozok) {
			if (Integer.toString(gondozo.getKartyaszam()).equals(kartyaszam)) {
				return false;
			}
		}
		return true;
	}

	public Boolean checkTulajId(String szigszam) {
		for (Tulajdonos tulaj : tulajdonosok) {
		
			if (tulaj.getSzigszam().equals(szigszam)) {
				
				return false;
			}
		}
		return true;
	}

	public Boolean checkIgazgatoId(String szigszam) {
		for (Igazgato igazgato : igazgatok) {
			if (igazgato.getSzigszam().equals(szigszam)) {
				return false;
			}
		}
		return true;
	}

	public void persist(String pathname) throws TransformerException {
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		DOMSource source = new DOMSource(root);
		StreamResult result = new StreamResult(new File(pathname));
		transformer.transform(source, result);
	}

}
