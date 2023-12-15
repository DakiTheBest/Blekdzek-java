package grupa3;

import java.nio.file.*;
import java.util.*;

public class Kredit {
    private String Ime;
    private long Stanje;
    private int Godine;

    public Kredit() {
        // Čitanje imena i vrijednosti kredita iz datoteke
        Path filePath = Paths.get(System.getProperty("user.home"), "Desktop", "nalog.txt");
        try {
            List<String> lines = Files.readAllLines(filePath);
            if (!lines.isEmpty()) {
                // Ako je prvi red neprazan, tretiramo ga kao ime, drugi kao godine i treći kao kredit
                if (lines.size() >= 3) {
                    Ime = lines.get(0);
                    Godine = Integer.parseInt(lines.get(1));
                    Stanje = Long.parseLong(lines.get(2));
                } else {
                    System.err.println("Neispravan format datoteke nalog.txt. Molimo, unesite ime i broj kredita na ovaj nacin:\n\nIme\nGodine\nKredit\n");
                    System.exit(1);
                }
                if (Stanje == 0) {
                    Godine = 17; // Mod bez kredita ako se počinje sa 0 kredita
                }
            } else {
                System.err.println("Datoteka nalog.txt je prazna. Molim vas unesite podatke na ovaj nacin: \n\nIme\nGodine\nKredit\n");
                System.exit(1);
            }
        } catch (NoSuchFileException e) { // Pisanje pravila ako nalog.txt ne postoji i ostale greške
            System.err.println("\nBlekdzek");
			System.err.println("\nAsov moze biti vrijedan 1 ili 11; vrijednost asova se ne mijenja nakon izvlacenja.\nDiler vuce dok ne dobije 17 ili vise.\n A - As, J - Zandar, Q - Kraljica, KR - Kralj.\n P - Pik, T - Tref, H - Herc, K - Karo."); // \n novi red
			System.err.println("\nUnesite svoje ime, godine i broj (odvojeni novim redom) izmedju 1 i 100000 u desktop datoteku \"nalog.txt\" kako biste zapoceli igru!\nNe mozete da igrate sa kreditima ako ste maloljetni.\n");
            System.exit(1);
        } catch (NumberFormatException e) {
            System.err.println("Neispravan format datoteke nalog.txt. Molimo, unesite ime i broj kredita na ovaj nacin:\n\nIme\nGodine\nKredit\n");
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Doslo je do greske prilikom citanja datoteke nalog.txt.\n");
            e.printStackTrace();
            System.exit(1);
        }

        // Provjera podataka
        if (Stanje > 100000) {
            System.err.println("Granica naloga je 100 000 kredita. Molimo vas unesite broj između 1 i 100 000.\n");
            System.exit(1);
        }

        if (Godine > 120) {
            System.err.println("Molimo vas upisite ljudske godine u drugom redu, tako da nalog izgleda ovako:\n\nIme\nGodine\nKredit\n");
            System.exit(1);
        }
    }

    // Getter metode za pristup privatnim varijablama
    public String getIme() {
        return Ime;
    }

    public long getStanje() {
        return Stanje;
    }

    public int getGodine() {
        return Godine;
    }

    public int ulog() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nKoliko zelite da ulozite? Unesite 'sve' ako zelite da igrate sa svim kreditima, ili 0 ako igrate bez njih.");
        String unos = scanner.nextLine();
    
        if ("sve".equalsIgnoreCase(unos)) {
            return (int) Stanje; // Uloži sve kredite
        } else {
            try {
                int ulog = Integer.parseInt(unos);
                if (ulog < 0) {
                    System.out.println("Unesite pozitivan broj ili \"sve\".");
                    return ulog();
                }
                return ulog;
            } catch (NumberFormatException e) {
                System.out.println("Neispravan unos. Molimo, unesite pozitivan broj ili 'sve'.");
                Thread.sleep(1500);
                return ulog();
            }
        }
    }
    

    public void procjena(int ulog, int pobjednik) throws InterruptedException {
        if (pobjednik == 1) {
            if (ulog > Stanje) {
                System.out.println("Otkriveno je da ste ulozili više nego sto ste imali, tako da ste duplirali trenutno stanje.");
                Stanje = Stanje * 2;
            } else {
                Stanje = Stanje + ulog;
            }
        } else if (pobjednik == 2) {
            Stanje = Stanje - ulog;
        }

        System.out.println("Novo stanje: " + Stanje);
        Thread.sleep(1500);
    }
}
