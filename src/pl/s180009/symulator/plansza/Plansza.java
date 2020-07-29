package pl.s180009.symulator.plansza;

import pl.s180009.symulator.kierunek.Kierunek;
import pl.s180009.symulator.pozycja.Pozycja;

import java.awt.*;

public abstract class Plansza {

    protected int szerokosc, wysokosc;
    protected Pozycja[][] plansza;

    public Plansza(int szerokosc, int wysokosc) {
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
    }

    public abstract void stworzPlansze();

    public int wezWysokosc() {
        return wysokosc;
    }

    public int wezSzerokosc() {
        return szerokosc;
    }

    public Pozycja wezPozycje(Point punkt) {
        return plansza[(int) punkt.getX()][(int) punkt.getY()];
    }

    public Pozycja wezPozycje(int x, int y) {
        if (x < 0 || y < 0 || x >= this.szerokosc || y >= this.wysokosc)
            return null;
        return plansza[x][y];
    }

    public Pozycja wezDanaPozycje(Point punkt, Kierunek kierunek) {
        return wezPozycje(punkt).wezDanaPozycje(kierunek);
    }

    public Pozycja[] wezPozycjeBoczne(Point punkt) {
        return wezPozycje(punkt).wezPozycjeBoczne();
    }

    public void stanPoczatkowy() {
        this.plansza = null;
        this.szerokosc = 0;
        this.wysokosc = 0;
    }

    public void ustawRozmiar(int szerokosc, int wysokosc) {
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.stworzPlansze();
    }
}