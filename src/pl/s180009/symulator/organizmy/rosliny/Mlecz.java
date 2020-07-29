package pl.s180009.symulator.organizmy.rosliny;

import pl.s180009.symulator.organizmy.Organizm;
import pl.s180009.symulator.pozycja.Pozycja;
import pl.s180009.symulator.swiat.Swiat;

import java.awt.*;

public class Mlecz extends Roslina {
    private final static int sila = 0;
    private final static Color kolor = Color.YELLOW;
    private final static int szansaMleczu = 3;

    public Mlecz(Swiat swiat, Pozycja pozycja, Point punkt) {
        super(swiat, pozycja, punkt, kolor, sila);
        this.zaladujZdjecie("zdjecia/Mlecz.jpg");
    }

    @Override
    public void akcja() {
        for (int i = 0; i < szansaMleczu; ++i)
            super.akcja();
    }

    @Override
    public Organizm stworzDziecko() {
        return new Mlecz(swiat, this.wezPozycje(), this.wezPunkt());
    }

    @Override
    public String wezNazwe() {
        return "Mlecz";
    }
}

