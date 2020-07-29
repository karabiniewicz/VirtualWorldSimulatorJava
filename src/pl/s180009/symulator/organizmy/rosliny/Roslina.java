package pl.s180009.symulator.organizmy.rosliny;

import pl.s180009.symulator.organizmy.Organizm;
import pl.s180009.symulator.pozycja.Pozycja;
import pl.s180009.symulator.swiat.Swiat;

import java.awt.*;

public abstract class Roslina extends Organizm {

    private final static int inicjatywa = 0;
    protected double szansaRozsiania = 0.1;

    public Roslina(Swiat swiat, Pozycja pozycja, Point punkt, Color kolor, int sila) {
        super(swiat, pozycja, punkt, kolor, sila, inicjatywa);

    }

    @Override
    public void akcja() {
        if (!czyZyje())
            return;
        probaRozsiewu();

    }

    @Override
    public boolean kolizja(Organizm organizm) {
        this.smierc(organizm);
        return true;
    }

    protected boolean probaRozsiewu() {
        double szansa = generuj.nextDouble();
        if (!(szansa <= szansaRozsiania))
            return false;

        Pozycja pozycja = this.wezPozycje().wezWolnaBocznaPozycje();
        if (pozycja == null)
            return false;

        Organizm dziecko = this.stworzDziecko();
        dziecko.ustawPozycje(pozycja);
        dziecko.ustawPunkt(pozycja.wezPunkt());
        pozycja.ustawOrganizm(dziecko);
        swiat.dodajOrganizm(dziecko);
        return true;
    }
}
