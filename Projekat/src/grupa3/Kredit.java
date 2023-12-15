package grupa3;

import java.nio.file.*;
import java.util.*;

public class Kredit {
    long Stanje;

    public Kredit() {
        // Čitanje vrijednosti kredita iz datoteke
        Path filePath = Paths.get(System.getProperty("user.home"), "Desktop", "kredit.txt");
        try {
            List<String> lines = Files.readAllLines(filePath);
            if (!lines.isEmpty()) {
                Stanje = Long.parseLong(lines.get(0));
            } else {
                System.err.println("Datoteka kredit.txt je prazna. Molim vas unesite početni iznos kredita.");
                System.exit(1);
            }
        } catch (NoSuchFileException e) {
            System.err.println("Grupa 3 - Blekdzek");
			System.err.println("Asov moze biti vrijedan 1 ili 11; vrijednost asova se ne mijenja nakon izvlacenja.\nDiler vuce dok ne dobije 17 ili vise.\n A - As, J - Zandar, Q - Kraljica, KR - Kralj.\n P - Pik, T - Tref, H - Herc, K - Karo."); // \n novi red
			System.err.println("Unesite broj izmedju 1 i 5000 u datoteku kredit.txt kako biste zapoceli igru!");
            System.exit(1);
        } catch (NumberFormatException e) {
            System.err.println("Neuspjesno parsiranje broja iz datoteke kredit.txt. Provjerite ispravnost formata.");
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Doslo je do greske prilikom citanja datoteke kredit.txt.");
            e.printStackTrace();
            System.exit(1);
        }
        if (Stanje > 5000) {
            System.err.println("Granica naloga je 5000 kredita. Molimo vas unesite broj izmedju 1 i 5000.");
            System.exit(1);
        }
    }

    public int ulog() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nKoliko zelite da ulozite? Unesite 0 ako ne zelite da igrate sa kreditima.");
        int ulog = scanner.nextInt();

        return ulog;
    }

    public void procjena(int ulog, int pobjednik) throws InterruptedException {
        if (pobjednik == 1) {
            if (ulog > Stanje) {
                System.out.println("Otkriveno je da ste ulozili vise nego sto ste imali, tako da ste duplirali trenutno stanje.");
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
