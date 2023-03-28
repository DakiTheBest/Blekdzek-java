package grupa3;

import java.util.*;

public class Blekdzek {
	public static void main(String[] args) throws InterruptedException { // throws dio zbog thread.sleep funkcije u kodu
	    try (Scanner scanner = new Scanner(System.in)) { // try kako nebi davalo error (nepotrebno)
			Karte Karte = new Karte();

			System.out.println("Grupa 3 - Blekdzek");
			System.out.println("Asov moze biti vrijedan 1 ili 11; vrijednost asova se ne mijenja nakon izvlacenja.\n A - As, J - Zandar, Q - Kraljica, KR - Kralj.\n P - Pik, T - Tref, H - Herc, K - Karo."); // \n novi red
            Thread.sleep(2000);

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
			    int igracRez1 = Karte.nabaviVrijednost(igracKarta1, igracRez0);
				int dilerRez1 = Karte.nabaviVrijednost(dilerKarta1, dilerRez0);
				int igracRez = igracRez1 + Karte.nabaviVrijednost(igracKarta2, igracRez1);
				int dilerRez = dilerRez1 + Karte.nabaviVrijednost(dilerKarta2, dilerRez1);


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
			            int vrijednostKarte = Karte.nabaviVrijednost(novaKarta, igracRez);
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
						String novaKarta = Karte.nasumicnaKarta(Karte.broj, Karte.vrsta);
						int vrijednostKarte = Karte.nabaviVrijednost(novaKarta, dilerRez);
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
}