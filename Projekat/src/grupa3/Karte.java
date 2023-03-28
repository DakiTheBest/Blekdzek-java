package grupa3;

import java.util.*;

public class Karte {
    public String[] broj = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "J", "Q", "KR"}; // A - Aš, J - Žandar, Q - Kraljica, KR - Kralj
    public String[] vrsta = {"P", "T", "H", "K"}; // Pik, Tref, Herc, Karo

    public String nasumicnaKarta(String[] broj, String[] vrsta) {
        Random random = new Random();
        int brojIndex = random.nextInt(broj.length);
        int vrstaIndex = random.nextInt(vrsta.length);

		return broj[brojIndex] + " " + vrsta[vrstaIndex];
    }

    public int nabaviVrijednost(String karta, int Rez) {
		String rank = karta.substring(0, karta.length() - 2); // Uzima kartu bez vrste karte i space-a (zbog toga je -2)
        if (rank.equals("A") && Rez <= 10) {
            return 11;
        } else if (rank.equals("A") && Rez > 10) {
            return 1;
        } else if (rank.equals("J") || rank.equals("Q") || rank.equals("KR")) {
            return 10;
        } else {
            return Integer.parseInt(rank); // Vraća vrijednost zavisnu od broja karte
        }
    }
}
