package pl.s180009.symulator.organizmy.zwierzeta;

import pl.s180009.symulator.organizmy.Organizm;
import pl.s180009.symulator.pozycja.Pozycja;
import pl.s180009.symulator.swiat.Swiat;

import java.awt.*;

public class Owca extends Zwierze {
    private static final Color kolor = new Color(216, 213, 198);
    private static final int sila = 4;
    private static final int inicjatywa = 4;

    public Owca(Swiat swiat, Pozycja pozycja, Point punkt) {
        this(swiat, pozycja, punkt, kolor, sila, inicjatywa);
    }

    public Owca(Swiat swiat, Pozycja pozycja, Point punkt, Color kolor, int sila, int inicjatywa) {
        super(swiat, pozycja, punkt, kolor, sila, inicjatywa);
        this.zaladujZdjecie("zdjecia/Owca.jpg");
    }

    @Override
    public Organizm stworzDziecko() {
        return new Owca(swiat, wezPozycje(), wezPunkt());
    }

    @Override
    protected boolean czyTenSamGatunek(Organizm organizm) {
        return organizm instanceof Owca;
    }

    @Override
    public String wezNazwe() {
        return "Owca";
    }
}

