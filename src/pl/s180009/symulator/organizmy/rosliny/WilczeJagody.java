package pl.s180009.symulator.organizmy.rosliny;

import pl.s180009.symulator.organizmy.Organizm;
import pl.s180009.symulator.pozycja.Pozycja;
import pl.s180009.symulator.swiat.Swiat;

import java.awt.*;

public class WilczeJagody extends RoslinaTrujaca {
    private static final int sila = 99;
    private static final Color kolor = new Color(88, 30, 128);

    public WilczeJagody(Swiat swiat, Pozycja pozycja, Point punkt) {
        super(swiat, pozycja, punkt, kolor, sila);
        this.zaladujZdjecie("zdjecia/WilczeJagody.jpg");
    }

    @Override
    public Organizm stworzDziecko() {
        return new WilczeJagody(swiat, wezPozycje(), wezPunkt());
    }

    @Override
    public String wezNazwe() {
        return "WilczeJagody";
    }
}
