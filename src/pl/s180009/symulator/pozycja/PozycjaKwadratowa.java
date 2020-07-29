package pl.s180009.symulator.pozycja;

import pl.s180009.symulator.kierunek.Kierunek;
import pl.s180009.symulator.organizmy.Organizm;

import java.awt.*;

public class PozycjaKwadratowa extends Pozycja {

    public PozycjaKwadratowa(Point punkt, Organizm organizm) {
        super(punkt, organizm);
        pozycjeBoczne = new PozycjaKwadratowa[4];
    }

    @Override
    public Pozycja wezDanaPozycje(Kierunek kierunek) {
        return pozycjeBoczne[kierunek.naLiczbe()];
    }

    @Override
    public void ustawDanaPozycje(Pozycja pozycja, Kierunek kierunek) {
        this.pozycjeBoczne[kierunek.naLiczbe()] = pozycja;
    }

    @Override
    public Pozycja[] wezPozycjeBoczne() {
        return pozycjeBoczne;
    }

    @Override
    public void ustawPozycjeBoczne(Pozycja[] pozycjeBoczne) {
        this.pozycjeBoczne = pozycjeBoczne;
    }
}
