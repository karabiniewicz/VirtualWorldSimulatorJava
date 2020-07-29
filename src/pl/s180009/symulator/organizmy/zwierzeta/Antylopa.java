package pl.s180009.symulator.organizmy.zwierzeta;

import pl.s180009.symulator.organizmy.Organizm;
import pl.s180009.symulator.pozycja.Pozycja;
import pl.s180009.symulator.swiat.Swiat;

import java.awt.*;

public class Antylopa extends Zwierze {
    private static final Color kolor = new Color(140, 139, 30);
    private static final int sila = 4;
    private static final int inicjatywa = 4;
    private static final int zakresAntylopy = 2;
    private static final double szansaAntylopy = DOMYSLNA_SZANSA;

    public Antylopa(Swiat swiat, Pozycja pozycja, Point punkt) {
        super(swiat, pozycja, punkt, kolor, sila, inicjatywa, zakresAntylopy, szansaAntylopy);
        this.zaladujZdjecie("zdjecia/Antylopa.jpg");
    }

    @Override
    public Organizm stworzDziecko() {
        return new Antylopa(swiat, wezPozycje(), wezPunkt());
    }

    @Override
    protected boolean czyTenSamGatunek(Organizm organizm) {
        return organizm instanceof Antylopa;
    }

    @Override
    protected boolean wykonajUnik(Organizm atakujacy) {
        Pozycja wolnaBocznaPozycje = this.wezPozycje().wezWolnaBocznaPozycje();

        if (wolnaBocznaPozycje == null)
            return false;

        return this.wykonajRuch(wolnaBocznaPozycje);
    }

    @Override
    public String wezNazwe() {
        return "Antylopa";
    }
}