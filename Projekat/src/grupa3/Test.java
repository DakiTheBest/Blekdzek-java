package grupa3;

import java.util.*;

public class Test {
	public static void main(String[] args) throws InterruptedException { // throws dio zbog thread.sleep funkcije u kodu
	    try (Scanner scanner = new Scanner(System.in)) { // try kako nebi davalo error (nepotrebno)
			Karte Karte = new Karte();
			Rezultat Rezultat = new Rezultat();
			Kredit Kredit = new Kredit();

			System.out.println("Grupa 3 - Blekdzek");
			System.out.println("Asov moze biti vrijedan 1 ili 11; vrijednost asova se ne mijenja nakon izvlacenja.\nDiler vuce dok ne dobije 17 ili vise.\n A - As, J - Zandar, Q - Kraljica, KR - Kralj.\n P - Pik, T - Tref, H - Herc, K - Karo."); // \n novi red
			System.out.println("Pocinjete sa 100 kredita, nakon dobijene partije ulozeni kredit se duplira. Pokusajte da dodjete do 1000 :)");
            Thread.sleep(2000);

			System.out.println("\n"+Rezultat.Score(0));
			System.out.println("Kredit: "+Kredit.Stanje);

			// Loop igre
			while (true) {
				int gameKredit = Kredit.ulog();
				System.out.println("Ulozen kredit: "+gameKredit);
			

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
				int pobjednik = 0;


			    System.out.println("Vase karte: " + igracKarta1 + ", " + igracKarta2 + " (Vrijednost: " + igracRez + ")");
			    System.out.println("Karta dilera: " + dilerKarta1 + " [?]\n");
				int pukao = 0; // Određuje je li igrač pukao, bitno za određivanje pobjednika
			
			    // Potez igraca
			    while (igracRez < 21) {
					Thread.sleep(1000); //1s cekanja
			        System.out.print("Zelite li da vucete ili stanete? (V/S)\n");
			        String input = scanner.nextLine().toLowerCase();

			        if (input.equals("v")) {
			            String novaKarta = Karte.nasumicnaKarta(Karte.broj, Karte.vrsta);
			            int vrijednostKarte = Karte.nabaviVrijednost(novaKarta, igracRez);
			            igracRez += vrijednostKarte;

			            System.out.println("Izvukli ste " + novaKarta + " (Ukupna vrijednost: " + igracRez + ").");

			            if (igracRez > 21) {
			                System.out.println("Pukli ste!");
							pukao = 1;
							Thread.sleep(1000);
							break;
			            } else if (igracRez == 21) {
							System.out.println("Najbolja ruka!");
							Thread.sleep(1500);
						}

						

			        } else if (input.equals("s")) {
			            System.out.println("Stajete sa [" + igracRez + "] poena.");
						Thread.sleep(1500); //1s cekanja
			            break;
			        } else {
			            System.out.println("Molim vas ukucajte V ili S.");
			        }
			    }

			    // Dilerov špil
					System.out.println("\nDiler otkriva svoju drugu kartu: " + dilerKarta2 + " (Ukupna vrijednost: " + dilerRez + ")");

					while (dilerRez < 17) {
						Thread.sleep(2000); // Čeka 2s kako bi simuliralo pravo vađenje karata
						String novaKarta = Karte.nasumicnaKarta(Karte.broj, Karte.vrsta);
						int vrijednostKarte = Karte.nabaviVrijednost(novaKarta, dilerRez);
						dilerRez += vrijednostKarte;

						System.out.println("Diler vuce " + novaKarta + " (Ukupna vrijednost: " + dilerRez + ")");

						if (dilerRez == 21) {
							System.out.println("Najbolja ruka!");
						    Thread.sleep(1500);
					    } else if (dilerRez > 21) {
							System.out.println("Diler je pukao!");
							pukao = 2;
							Thread.sleep(1000);
							break;
						}

						
					}

			    // Odluci pobjednika
			    if (igracRez <= 21 && (dilerRez > 21 || igracRez > dilerRez) && (pukao == 0 || pukao == 2)) {
			        System.out.println("\nVi pobjedjujete!");
					pobjednik = 1;
			    } else if ( dilerRez <= 21 && dilerRez > igracRez || pukao == 1) {
			        System.out.println("\nKuca pobjedjuje!");
					pobjednik = 2;
			    } else {
					System.out.println("\nNerijeseno!");
					pobjednik = 0;
				}

			
	            System.out.println(Rezultat.Score(pobjednik));
				Kredit.procjena(gameKredit, pobjednik);
				
			
				if (Kredit.Stanje == 0) {
					System.out.println("Nemate vise kredita! Vise srece drugi put.");
					break;
				} else if (Kredit.Stanje < 0) {
					System.out.println("Usli ste u dug! Dobar pokusaj...");
					break;
				}
				
			    // Igraj opet
			    System.out.print("\nZelite li da igrate ponovo? (Unesite \"da\" za novu partiju)\n");
			    String input = scanner.nextLine().toLowerCase();

			    if (!input.equals("da")) {
					System.out.println("\nHvala vam na igranju.");
			        break;
			    }
			}
		}
    }
}