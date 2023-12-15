package grupa3;

public class Rezultat extends Test {
    int Rezultat1 = 0;
    int Rezultat2 = 0;
    Kredit Kredit = new Kredit();

    public String Score(int pobjednik) {
        if (pobjednik == 1) {
            Rezultat1++;
        } else if (pobjednik == 2) {
            Rezultat2++;
        }

        return (Kredit.getIme()+": "+Rezultat1+" - Kompjuter: "+Rezultat2);
    }
}