package grupa3;

import java.util.*;

public class Kredit {
    int Stanje = 100;

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

        System.out.println("Novo stanje: "+Stanje);
        Thread.sleep(1500);
    }
}
