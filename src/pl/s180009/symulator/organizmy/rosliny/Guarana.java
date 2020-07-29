package pl.s180009.symulator.organizmy.rosliny;

import pl.s180009.symulator.organizmy.Organizm;
import pl.s180009.symulator.pozycja.Pozycja;
import pl.s180009.symulator.swiat.Swiat;

import java.awt.*;

public class Guarana extends Roslina {
    private static final int sila = 0;
    private static final Color kolor = Color.RED;
    private static final int wzrostSily = 3;

    public Guarana(Swiat swiat, Pozycja pozycja, Point punkt) {
        super(swiat, pozycja, punkt, kolor, sila);
        this.zaladujZdjecie("zdjecia/Guarana.jpg");
    }

    @Override
    public Organizm stworzDziecko() {
        return new Guarana(swiat, this.wezPozycje(), this.wezPunkt());
    }

    @Override
    public boolean kolizja(Organizm organizm) {
        organizm.zwiekszSile(wzrostSily);
        return super.kolizja(organizm);
    }

    @Override
    public String wezNazwe() {
        return "Guarana";
    }
}
