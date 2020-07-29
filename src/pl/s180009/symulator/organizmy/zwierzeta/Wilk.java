package pl.s180009.symulator.organizmy.zwierzeta;

import pl.s180009.symulator.organizmy.Organizm;
import pl.s180009.symulator.pozycja.Pozycja;
import pl.s180009.symulator.swiat.Swiat;

import java.awt.*;

public class Wilk extends Zwierze {
    private static final Color kolor = new Color(111, 111, 111);
    private static final int sila = 9;
    private static final int inicjatywa = 5;

    public Wilk(Swiat swiat, Pozycja pozycja, Point punkt) {
        super(swiat, pozycja, punkt, kolor, sila, inicjatywa);
        this.zaladujZdjecie("zdjecia/Wilk3.jpg");
    }

    @Override
    public Organizm stworzDziecko() {
        return new Wilk(swiat, wezPozycje(), wezPunkt());
    }

    @Override
    protected boolean czyTenSamGatunek(Organizm organizm) {
        return organizm instanceof Wilk;
    }

    @Override
    public String wezNazwe() {
        return "Wilk";
    }
}
