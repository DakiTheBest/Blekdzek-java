package grupa3;

import java.util.*;

public class Rezultat extends Test {
    int Rezultat1 = 0;
    int Rezultat2 = 0;

    public String Score(int pobjednik) {
        if (pobjednik == 1) {
            Rezultat1++;
        } else if (pobjednik == 2) {
            Rezultat2++;
        }

        return ("Vi: "+Rezultat1+" - Kompjuter: "+Rezultat2);
    }
}