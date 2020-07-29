package pl.s180009.symulator.organizmy.zwierzeta;

import pl.s180009.symulator.organizmy.Organizm;
import pl.s180009.symulator.pozycja.Pozycja;
import pl.s180009.symulator.swiat.Swiat;

import java.awt.*;

public class Zolw extends Zwierze {
    private static final Color kolor = new Color(1, 96, 7);
    private static final int sila = 2;
    private static final int inicjatywa = 1;
    private static final int zakresZolwia = DOMYSLNY_ZASIEG;
    private static final double szansaRuchuZolwia = 0.25;
    private static final int silaOdparciaAtaku = 5;

    public Zolw(Swiat swiat, Pozycja pozycja, Point punkt) {
        super(swiat, pozycja, punkt, kolor, sila, inicjatywa, zakresZolwia, szansaRuchuZolwia);
        this.zaladujZdjecie("zdjecia/Zolw.jpg");
    }

    @Override
    public Organizm stworzDziecko() {
        return new Zolw(swiat, wezPozycje(), wezPunkt());
    }

    @Override
    protected boolean czyTenSamGatunek(Organizm organism) {
        return organism instanceof Zolw;
    }

    @Override
    public boolean czyOdbilAtak(Organizm organism) {
        return silaOdparciaAtaku > organism.wezSile();
    }

    @Override
    public String wezNazwe() {
        return "Zolw";
    }
}
