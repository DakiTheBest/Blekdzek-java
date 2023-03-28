package grupa3;

import java.util.*;

public class Blekdzek {
	public static void main(String[] args) throws InterruptedException { // throws dio zbog thread.sleep funkcije u kodu
	    try (Scanner scanner = new Scanner(System.in)) { // try kako nebi davalo error (nepotrebno)
			Karte Karte = new Karte();

			System.out.println("Grupa 3 - Blekdzek");
			System.out.println("Igra je nerijesena ako su oba igraca pukla ili su vrijednosti njihovih spilova jednake.\n A - As, ZH - Zandar, Q - Kraljica, KR - Kralj\n P - Pik, T - Tref, H - Herc, K - Karo"); // \n novi red

			// Loop igre
			while (true) {
			    System.out.println("\nKreiranje nove partije..."); // \n pravi novi red zbog vise mogućih partija

			    // Prve karte
			    String igracKarta1 = Karte.nasumicnaKarta(Karte.broj, Karte.vrsta);
			    String dilerKarta1 = Karte.nasumicnaKarta(Karte.broj, Karte.vrsta);
			    String igracKarta2 = Karte.nasumicnaKarta(Karte.broj, Karte.vrsta);
				String dilerKarta2 = Karte.nasumicnaKarta(Karte.broj, Karte.vrsta);
				int igracRez0 = 0;
				int dilerRez0 = 0;
				boolean ashov1 = false; // Bitan za odredjivanje da li je asov 1 ili 11
				boolean ashov2 = false; // Za dilera
				int x = 0; // takodje za asova
				int y = 0; // ^^^^^^^^^^^^^^^^
			    int igracRez = Karte.nabaviVrijednost(igracKarta1, igracRez0, ashov1) + Karte.nabaviVrijednost(igracKarta2, igracRez0, ashov1);
			    int dilerRez = Karte.nabaviVrijednost(dilerKarta1, dilerRez0, ashov2) + Karte.nabaviVrijednost(dilerKarta2, dilerRez0, ashov2);

			    System.out.println("Vase karte: " + igracKarta1 + ", " + igracKarta2 + " (Vrijednost: " + igracRez + ")");
			    System.out.println("Karta dilera: " + dilerKarta1 + " [?]\n");
				int pukao = 0; // Određuje je li igrač pukao, bitno za određivanje pobjednika
				int brojKarata = 2; // Prati broj karata kako bi diler vadio isti broj kao i igrac
			
			    // Potez igraca
			    while (igracRez < 21) {
					Thread.sleep(1000); //1s cekanja
			        System.out.print("Zelite li da vucete ili stanete? (V/S)\n");
			        String input = scanner.nextLine().toLowerCase();

			        if (input.equals("v")) {
			            String novaKarta = Karte.nasumicnaKarta(Karte.broj, Karte.vrsta);
			            int vrijednostKarte = Karte.nabaviVrijednost(novaKarta, igracRez, ashov1);
			            igracRez += vrijednostKarte;
						brojKarata += 1;

						if (igracRez > 21) {
							igracRez = Karte.ashovFix(ashov1, x, igracRez);
						}

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
						String novaKarta = Karte.nasumicnaKarta(Karte.broj, Karte.vrsta);
						int vrijednostKarte = Karte.nabaviVrijednost(novaKarta, dilerRez, ashov2);
						dilerRez += vrijednostKarte;

						if (dilerRez > 21) {
							dilerRez = Karte.ashovFix(ashov2, y, dilerRez);
						}

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
}