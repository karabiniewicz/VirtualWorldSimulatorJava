package pl.s180009.symulator.organizmy.zwierzeta;

import pl.s180009.symulator.organizmy.Organizm;
import pl.s180009.symulator.pozycja.Pozycja;
import pl.s180009.symulator.swiat.Swiat;

import java.awt.*;

public class Lis extends Zwierze {
    private static final Color kolor = new Color(192, 98, 49);
    private static final int sila = 3;
    private static final int inicjatywa = 7;

    public Lis(Swiat swiat, Pozycja pozycja, Point punkt) {
        super(swiat, pozycja, punkt, kolor, sila, inicjatywa);
        this.zaladujZdjecie("zdjecia/Lis.jpg");
    }

    @Override
    public Organizm stworzDziecko() {
        return new Lis(swiat, wezPozycje(), wezPunkt());
    }

    @Override
    protected boolean czyTenSamGatunek(Organizm organizm) {
        return organizm instanceof Lis;
    }

    @Override
    public String wezNazwe() {
        return "Lis";
    }

    @Override
    protected Pozycja wezDocelowaPozycje() {
        return this.wezPozycje().wezSlabszyBocznyOrganiz(this.wezSile());
    }
}
