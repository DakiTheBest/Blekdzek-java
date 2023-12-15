package grupa3;

import java.util.*;

public class Test {
	public static void main(String[] args) throws InterruptedException { // throws dio zbog thread.sleep funkcije u kodu
	    try (Scanner scanner = new Scanner(System.in)) { // try kako nebi davalo error (nepotrebno)
			Karte Karte = new Karte();
			Rezultat Rezultat = new Rezultat();
			Kredit Kredit = new Kredit();

			System.out.println("\nDobro dosli, "+Kredit.getIme()+"!");
			Thread.sleep(1000);
			System.out.println("\n"+Rezultat.Score(0));
			if (Kredit.getGodine() >= 18) {
				System.out.println("Kredit: "+Kredit.getStanje());
			}
		

			// Loop igre
			while (true) {
				int gameKredit = 0;

				if (Kredit.getGodine() >= 18) {
				    gameKredit = Kredit.ulog();
				    System.out.println("Ulozen kredit: "+gameKredit);
				}
			
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


			    System.out.println("\nVase karte: " + igracKarta1 + ", " + igracKarta2 + " (Vrijednost: " + igracRez + ")");
			    System.out.println("Karta dilera: " + dilerKarta1 + " [?]\n");
				int pukao = 0; // Određuje je li igrač pukao, bitno za određivanje pobjednika
			
			    // Potez igraca
			    while (igracRez <= 21) {
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
							Thread.sleep(500);
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
						Thread.sleep(1500); // Čeka 1.5s kako bi simuliralo pravo vađenje karata
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
							break;
						}

						
					}

			    Thread.sleep(1500);  // Sačekaj pa odluči pobjednika
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

				if (Kredit.getGodine() >= 18) {
					Kredit.procjena(gameKredit, pobjednik);

					if (Kredit.getStanje() == 0) {
					    System.out.println("Nemate vise kredita! Vise srece drugi put.");
					break;
				    } else if (Kredit.getStanje() < 0) {
					    System.out.println("Usli ste u dug! Dobar pokusaj...");
					    break;
				    }
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