package pl.s180009.symulator.organizmy.rosliny;

import pl.s180009.symulator.organizmy.Organizm;
import pl.s180009.symulator.pozycja.Pozycja;
import pl.s180009.symulator.swiat.Swiat;

import java.awt.*;

public class Trawa extends Roslina {
    private final static int sila = 0;
    private static final Color kolor = Color.GREEN;

    public Trawa(Swiat swiat, Pozycja pozycja, Point punkt) {
        super(swiat, pozycja, punkt, kolor, sila);
        this.zaladujZdjecie("zdjecia/Trawa.jpg");
    }

    @Override
    public Organizm stworzDziecko() {
        return new Trawa(swiat, this.wezPozycje(), this.wezPunkt());
    }

    @Override
    public String wezNazwe() {
        return "Trawa";
    }
}
