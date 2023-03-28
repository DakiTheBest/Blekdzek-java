package grupa3;

import java.util.*;

public class Blekdzek {
	public static void main(String[] args) throws InterruptedException { // throws dio zbog thread.sleep funkcije u kodu
	    try (Scanner scanner = new Scanner(System.in)) { // try kako nebi davalo error (nepotrebno)
			String[] karta = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "ZH", "Q", "KR"}; // A - Aš, Ž - Žandar, Q - Kraljica, K - Kralj
			String[] vrsta = {"P", "T", "H", "K"}; // Pik, Tref, Herc, Karo

			System.out.println("Grupa 3 - Blekdzek");
			System.out.println("Igra je nerijesena ako su oba igraca pukla ili su vrijednosti njihovih spilova jednake.\n A - As, ZH - Zandar, Q - Kraljica, KR - Kralj\n P - Pik, T - Tref, H - Herc, K - Karo"); // \n novi red

			// Loop igre
			while (true) {
			    System.out.println("\nKreiranje nove partije..."); // \n pravi novi red zbog vise mogućih partija

			    // Prve karte
			    String igracKarta1 = nasumicnaKarta(karta, vrsta);
			    String dilerKarta1 = nasumicnaKarta(karta, vrsta);
			    String igracKarta2 = nasumicnaKarta(karta, vrsta);
				String dilerKarta2 = nasumicnaKarta(karta, vrsta);
			    int igracRez = nabaviVrijednost(igracKarta1) + nabaviVrijednost(igracKarta2);
			    int dilerRez = nabaviVrijednost(dilerKarta1) + nabaviVrijednost(dilerKarta2);

			    System.out.println("Vase karte: " + igracKarta1 + ", " + igracKarta2 + " (Vrijednost: " + igracRez + ")");
			    System.out.println("Karta dilera: " + dilerKarta1 + " [?]");
				int pukao = 0; // Određuje je li igrač pukao, bitno za određivanje pobjednika
				int brojKarata = 2; // Prati broj karata kako bi diler vadio isti broj kao i igrac

			    // Potez igraca
			    while (igracRez < 21) {
					Thread.sleep(1000); //1s cekanja
			        System.out.print("Zelite li da vucete ili stanete? (V/S)");
			        String input = scanner.nextLine().toLowerCase();

			        if (input.equals("v")) {
			            String novaKarta = nasumicnaKarta(karta, vrsta);
			            int vrijednostKarte = nabaviVrijednost(novaKarta);
			            igracRez += vrijednostKarte;
						brojKarata += 1;

			            System.out.println("Izvukli ste " + novaKarta + " (Ukupna vrijednost: " + igracRez + ").");

			            if (igracRez > 21) {
							Thread.sleep(1000); //1s cekanja
			                System.out.println("Pukli ste!");
							pukao = 1;
							break;
			            }
			        } else if (input.equals("s")) {
			            System.out.println("Stajete sa [" + igracRez + "] poena.");
						Thread.sleep(1000); //1s cekanja
			            break;
			        } else {
			            System.out.println("Molim vas ukucajte V ili S.");
			        }
			    }

			    // Dilerov špil
					System.out.println("\nDiler otkriva svoju drugu kartu: " + dilerKarta2 + " (Ukupna vrijednost: " + dilerRez + ")");

					for (int i = 2; i < brojKarata; i++) {
						Thread.sleep(1500); // Čeka 1.5s kako bi simuliralo pravo vađenje karata
						String novaKarta = nasumicnaKarta(karta, vrsta);
						int vrijednostKarte = nabaviVrijednost(novaKarta);
						dilerRez += vrijednostKarte;

						System.out.println("Diler vuce " + novaKarta + " (Ukupna vrijednost: " + dilerRez + ")");

						if (dilerRez > 21) {
							System.out.println("Diler je pukao!");
							pukao = 2;
							break;
						}
					}

			    // Odluci pobjednika
			    if (igracRez <= 21 && (dilerRez > 21 || igracRez > dilerRez) && (pukao == 0 || pukao == 2)) {
			        System.out.println("\nVi pobjedjujete!");
			    } else if ( dilerRez <= 21 && dilerRez > igracRez || pukao == 1) {
			        System.out.println("\nKuca pobjedjuje!");
			    } else {
					System.out.println("\nNerijeseno!");
				}

			    // Igraj opet
			    System.out.print("Zelite li da igrate ponovo? (Unesite \"da\" za novu partiju)");
			    String input = scanner.nextLine().toLowerCase();

			    if (!input.equals("da")) {
					System.out.println("\nHvala vam na igranju.");
			        break;
			    }
			}
		}
    }

    public static String nasumicnaKarta(String[] karta, String[] vrsta) {
        Random random = new Random();
        int kartaIndex = random.nextInt(karta.length);
        int vrstaIndex = random.nextInt(vrsta.length);

		return karta[kartaIndex] + " " + vrsta[vrstaIndex];
    }
    
    public static int nabaviVrijednost(String karta) {
		String rank = karta.substring(0, karta.length() - 2); // Uzima kartu bez vrste karte i space-a (zbog toga je -2)
        if (rank.equals("A")) {
            return 1;
        } else if (rank.equals("ZH") || rank.equals("Q") || rank.equals("KR")) {
            return 10;
        } else {
            return Integer.parseInt(rank); // Vraća vrijednost zavisnu od broja karte
        }
    }
}