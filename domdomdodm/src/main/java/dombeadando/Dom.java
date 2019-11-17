package dombeadando;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Dom {

	public static void main(String[] args)
			throws ParserConfigurationException, SAXException, IOException, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse("src/main/resources/Cicaovi.xml");

		Cicaovi cicaovi = Cicaovi.create(document);

		boolean run = true;

		while (run) {
			
			System.out.println("Menu");
			System.out.println("====================================");
			System.out.println("[1] Adatok felvetele");
			System.out.println("[2] Adat lekerdezese");
			System.out.println("[3] Adat mentese");
			System.out.println("[4] Kilepes");
			System.out.println("_____________________________________");
			Scanner sc = new Scanner(System.in);
			int menu = sc.nextInt();

			switch (menu) {
			case 1:
				System.out.println("Menu/ Adatok felvetele");
				System.out.println("====================================");
				System.out.println("[1] Uj cica");
				System.out.println("[2] Uj gondozo");
				System.out.println("[3] Uj igazgato");
				System.out.println("[4] Uj tulaj");
				System.out.println("[5] Uj kapcsolat (cica es gondozo kozott)");
				System.out.println("[6] Kilepes");
				System.out.println("_____________________________________");

				int felvetel = sc.nextInt();

				switch (felvetel) {
				case 1:
					Cica cica = new Cica();
					String[] cicaadatok = {"kod", "tulajdonos", "nev", "szin", "kor"};
					List<String> lista = new ArrayList<>();
					String felvesz = sc.nextLine();
					for(String adat : cicaadatok) {
						System.out.println("Adja meg az alabbi adatot: " + adat);
						String ujadat = sc.nextLine();
						lista.add(ujadat);
					}
					while(!cicaovi.checkCicaId(lista.get(0))) {
							System.out.println("Van mar ilyen id, adjon meg masikat!");
							String ujadat = sc.nextLine();
							lista.set(0, ujadat);
					}
					while(cicaovi.checkTulajId(lista.get(1))) {
							System.out.println("Nincs ilyen tulaj, adjon meg masikat!");
							String ujadat = sc.nextLine();
							lista.set(1, ujadat);
					}
					cica.setKod(Integer.parseInt(lista.get(0)));
					cica.setTulaj(cicaovi.getTulajById(lista.get(1)));
					cica.setNev(lista.get(2));
					cica.setSzin(lista.get(3));
					cica.setKor(Integer.parseInt(lista.get(4)));
					cicaovi.addCica(cica);
					break;
				case 2:
					Gondozo gondozo = new Gondozo();
					String[] gondozoadatok = {"kartyaszam", "nev", "telefonszam"};
					List<String> gondozolista = new ArrayList<>();
					String felvesz2 = sc.nextLine();
					for(String adat : gondozoadatok) {
						System.out.println("Adja meg az alabbi adatot: " + adat);
						String ujadat = sc.nextLine();
						gondozolista.add(ujadat);
					}
					while(!cicaovi.checkGondozoId(gondozolista.get(0))) {
							System.out.println("Van mar ilyen id, adjon meg masikat!");
							String ujadat = sc.nextLine();
							gondozolista.set(0, ujadat);
					}
					
					gondozo.setKartyaszam(Integer.parseInt(gondozolista.get(0)));
					gondozo.setNev(gondozolista.get(1));
					gondozo.setTelefonszam(gondozolista.get(2));
					cicaovi.addGondozo(gondozo);
					break;
				case 3:
					Igazgato igazgato = new Igazgato();
					String[] igazgatoadatok = {"szigszam", "nev"};
					List<String> igazgatolista = new ArrayList<>();
					String felvesz3 = sc.nextLine();
					for(String adat : igazgatoadatok) {
						System.out.println("Adja meg az alabbi adatot: " + adat);
						String ujadat = sc.nextLine();
						igazgatolista.add(ujadat);
					}
					while(!cicaovi.checkIgazgatoId(igazgatolista.get(0))) {
							System.out.println("Van mar ilyen id, adjon meg masikat!");
							String ujadat = sc.nextLine();
							igazgatolista.set(0, ujadat);
					}
					
					igazgato.setSzigszam(igazgatolista.get(0));
					igazgato.setNev(igazgatolista.get(1));
					cicaovi.addIgazgato(igazgato);

					break;
				case 4:
					Tulajdonos tulajdonos = new Tulajdonos();
					String[] tulajdonosadatok = {"szigszam", "nev"};
					List<String> tulajdonoslista = new ArrayList<>();
					String felvesz4 = sc.nextLine();
					for(String adat : tulajdonosadatok) {
						System.out.println("Adja meg az alabbi adatot: " + adat);
						String ujadat = sc.nextLine();
						tulajdonoslista.add(ujadat);
					}
					while(!cicaovi.checkTulajId(tulajdonoslista.get(0))) {
							System.out.println("Van mar ilyen id, adjon meg masikat!");
							String ujadat = sc.nextLine();
							tulajdonoslista.set(0, ujadat);
					}
					
					tulajdonos.setSzigszam(tulajdonoslista.get(0));
					tulajdonos.setNev(tulajdonoslista.get(1));
					cicaovi.addTulajdonos(tulajdonos);

					break;
				case 5:
					Kapcsolat kapcsolat = new Kapcsolat();
					String[] kapcsolatadatok = {"cicaid", "gondozoid"};
					List<String> kapcsolatlista = new ArrayList<>();
					String felvesz5 = sc.nextLine();
					for(String adat : kapcsolatadatok) {
						System.out.println("Adja meg az alabbi adatot: " + adat);
						String ujadat = sc.nextLine();
						kapcsolatlista.add(ujadat);
					}
					while(cicaovi.checkGondozoId(kapcsolatlista.get(1))) {
							System.out.println("Nincs meg ilyen gondozoid, adjon meg masikat!");
							String ujadat = sc.nextLine();
							kapcsolatlista.set(1, ujadat);
					}
					while(cicaovi.checkCicaId(kapcsolatlista.get(0))) {
						System.out.println("Nincs meg ilyen cicaid, adjon meg masikat!");
						String ujadat = sc.nextLine();
						kapcsolatlista.set(0, ujadat);
				}
					while(cicaovi.checkCicaIdAndGondozoId(kapcsolatlista.get(1), kapcsolatlista.get(0))) {
						System.out.println("Ehhez a gondozohoz " + kapcsolatlista.get(1) +  " mar tartozik ez a cica " + kapcsolatlista.get(0) + "! Adj meg masik gondozoid-t!");
						String ujadat = sc.nextLine();
						kapcsolatlista.set(1, ujadat);
					}
					kapcsolat.setCica(cicaovi.searchCicaByKod(Integer.parseInt(kapcsolatlista.get(0))));
					kapcsolat.setGondozo(cicaovi.searchGondozoByKartyaszam(Integer.parseInt(kapcsolatlista.get(1))));
					cicaovi.addKapcsolat(kapcsolat);
					
					break;
				case 6:
					break;
				default:
					System.out.println("Rossz input!");
					break;
				}
				break;
			case 2:
				System.out.println("Menu/ Adatok lekerdezese");
				System.out.println("====================================");
				System.out.println("[1] Igazgato keresese id alapjan");
				System.out.println("Tulajdonos keresese");
				System.out.println("	[2] id alapjan");
				System.out.println("	[3] tulajdonos alapjan");
				System.out.println("Cica(k) keresese");
				System.out.println("	[4] id alapjan");
				System.out.println("	[5] tulajdonos alapjan");
				System.out.println("	[6] gondozo alapjan");
				System.out.println("Gondozo(k) keresese");
				System.out.println("	[7] id alapjan");
				System.out.println("	[8] cica alapjan");
				System.out.println("[9] Kilepes");
				System.out.println("_____________________________________");
				int lekerdezes = sc.nextInt();
				switch (lekerdezes) {
				case 1:
					String igazgatolekerdezes = sc.nextLine();
					System.out.println("Kerem az igazgato szemelyi igazolvany szamat (pl. 654321AS): ");
					String igazgatoid = sc.nextLine();
					Igazgato igazgato = new Igazgato();
					igazgato = cicaovi.searchIgazgatoBySzigszam(igazgatoid);
					System.out.println("Igazgato adatai");
					System.out.println("=============================");
					System.out.println("Szigszam: " + igazgato.getSzigszam());
					System.out.println("Nev: " + igazgato.getNev());
					System.out.println("_____________________________________");
					break;
				case 2:
					String tulajlekerdezes = sc.nextLine();
					System.out.println("Kerem a tulajdonos szemelyi igazolvany szamat (pl. 254321AS): ");
					String tulajid = sc.nextLine();
					Tulajdonos tulaj = new Tulajdonos();
					tulaj = cicaovi.searchTulajdonosBySzigszam(tulajid);
					System.out.println("Tulajdonos adatai");
					System.out.println("=============================");
					System.out.println("Szigszam: " + tulaj.getSzigszam());
					System.out.println("Nev: " + tulaj.getNev());
					System.out.println("_____________________________________");
					break;
				case 3:
					String tulajlekerdezes2 = sc.nextLine();
					System.out.println("Kerem a cica kodjat (pl. 2): ");
					int tulajid2 = sc.nextInt();
					Tulajdonos tulaj2 = new Tulajdonos();
					tulaj2 = cicaovi.searchTulajdonosByCica(cicaovi.searchCicaByKod(tulajid2));
					System.out.println(cicaovi.searchCicaByKod(tulajid2));
					System.out.println("Tulajdonos adatai");
					System.out.println("=============================");
					System.out.println("Szigszam: " + tulaj2.getSzigszam());
					System.out.println("Nev: " + tulaj2.getNev());
					System.out.println("_____________________________________");
					break;
				case 4:
					String cicalekerdezes = sc.nextLine();
					System.out.println("Kerem a cica kodjat (pl. 2): ");
					int cicaid = sc.nextInt();
					Cica cica = new Cica();
					cica = cicaovi.searchCicaByKod(cicaid);
					System.out.println("Cica adatai");
					System.out.println("=============================");
					System.out.println("Kod: " + cica.getKod());
					System.out.println("Tulajdonos: " + cica.getTulajdonos());
					System.out.println("Nev: " + cica.getNev());
					System.out.println("Szin: " + cica.getSzin());
					System.out.println("Kor: " + cica.getKor());
					System.out.println("_____________________________________");
					break;
				case 5:
					String cicalekerdezes2 = sc.nextLine();
					System.out.println("Kerem a cicak tulajdonosanak szigszamat (pl. 254321AS): ");
					String cicaid2 = sc.nextLine();
					List<Cica> cicak;
					cicak = cicaovi.searchCicakByTulajdonos(cicaovi.getTulajById((cicaid2)));
					System.out.println("Cicak adatai");
					System.out.println("=============================");
					for(Cica cicus : cicak) {
						System.out.println("Kod: " + cicus.getKod());
						System.out.println("Tulajdonos: " + cicus.getTulajdonos());
						System.out.println("Nev: " + cicus.getNev());
						System.out.println("Szin: " + cicus.getSzin());
						System.out.println("Kor: " + cicus.getKor());
						System.out.println("_____________________________________");
					}

					break;
				case 6:
					String cicalekerdezes3 = sc.nextLine();
					System.out.println("Kerem a cicak gondozojanak kartyaszamat (pl. 42): ");
					String cicaid3 = sc.nextLine();
					List<Cica> cicak2;
					cicak2 = cicaovi.searchCicakByGondozo(cicaovi.getGondozoById(Integer.parseInt(cicaid3)));
					System.out.println("Cicak adatai");
					System.out.println("=============================");
					for(Cica cicus : cicak2) {
						System.out.println("Kod: " + cicus.getKod());
						System.out.println("Tulajdonos: " + cicus.getTulajdonos());
						System.out.println("Nev: " + cicus.getNev());
						System.out.println("Szin: " + cicus.getSzin());
						System.out.println("Kor: " + cicus.getKor());
						System.out.println("_____________________________________");
					}
					break;
				case 7:
					String gondozolekerdezes = sc.nextLine();
					System.out.println("Kerem az gondozo kartyaszamat (pl. 42): ");
					int gondozoid = sc.nextInt();
					Gondozo gondozo = new Gondozo();
					gondozo = cicaovi.searchGondozoByKartyaszam(gondozoid);
					System.out.println("Gondozo adatai");
					System.out.println("=============================");
					System.out.println("Kartyaszam: " + gondozo.getKartyaszam());
					System.out.println("Nev: " + gondozo.getNev());
					System.out.println("Telefonszam: " + gondozo.getTelefonszam());
					System.out.println("_____________________________________");
					break;
					
				case 8:
					String gondozolekerdezes2 = sc.nextLine();
					System.out.println("Kerem a gondozok cicajanak kodjat (pl. 2): ");
					int gondozoid2 = sc.nextInt();
					List<Gondozo> gondozok;
					gondozok = cicaovi.searchGondozokByCica(cicaovi.getCicaById(gondozoid2));
					System.out.println("Gondozok adatai");
					System.out.println("=============================");
					for(Gondozo gondozocska : gondozok) {
						System.out.println("Kartyaszam: " + gondozocska.getKartyaszam());
						System.out.println("Nev: " + gondozocska.getNev());
						System.out.println("Telefonszam: " + gondozocska.getTelefonszam());
						System.out.println("_____________________________________");
					}

					break;
				case 9:
					break;

				default:
					System.out.println("Rossz input!");
					break;
				}
				break;
			case 3:
				cicaovi.persist("src/main/resources/Cicaovi.xml");
				System.out.println("Kesz!");
				break;
			case 4:
				run = false;
				break;
			default:
				System.out.println("Rossz input!");
				break;
			}
		}

	}

}
