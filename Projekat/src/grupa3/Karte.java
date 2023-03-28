package grupa3;

import java.util.*;

public class Karte {
    public String[] broj = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "ZH", "Q", "KR"}; // A - Aš, Ž - Žandar, Q - Kraljica, K - Kralj
    public String[] vrsta = {"P", "T", "H", "K"}; // Pik, Tref, Herc, Karo

    public String nasumicnaKarta(String[] broj, String[] vrsta) {
        Random random = new Random();
        int brojIndex = random.nextInt(broj.length);
        int vrstaIndex = random.nextInt(vrsta.length);

		return broj[brojIndex] + " " + vrsta[vrstaIndex];
    }

    public int nabaviVrijednost(String karta, int Rez, boolean ashov) {
		String rank = karta.substring(0, karta.length() - 2); // Uzima kartu bez vrste karte i space-a (zbog toga je -2)
        if (rank.equals("A") && Rez <= 10) {
            ashov = true;
            return 11;
        } else if (rank.equals("A") && Rez > 10) {
            return 1;
        } else if (rank.equals("ZH") || rank.equals("Q") || rank.equals("KR")) {
            return 10;
        } else {
            return Integer.parseInt(rank); // Vraća vrijednost zavisnu od broja karte
        }
    }

    public int ashovFix(boolean ashov, int x, int rez) {
        if (ashov && x == 0) {
            x = x + 1;
            int g = rez - 10;
            return g;
        } else {
            return rez;  
        }
    }
}
