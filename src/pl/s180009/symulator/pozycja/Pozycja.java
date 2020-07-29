package pl.s180009.symulator.pozycja;

import pl.s180009.symulator.kierunek.Kierunek;
import pl.s180009.symulator.organizmy.Organizm;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public abstract class Pozycja {

    private Organizm organizm;
    private Point punkt;
    protected Pozycja[] pozycjeBoczne;
    private static Random losowa = new Random();

    public Pozycja(Point punkt, Organizm organizm) {
        this.punkt = punkt;
        this.organizm = organizm;
    }

    public boolean czyPusta() {
        return organizm == null;
    }

    public void ustawOrganizm(Organizm organizm) {
        this.organizm = organizm;
    }

    public Organizm wezOrganizm() {
        return organizm;
    }

    public void ustawPunkt(Point punkt) {
        this.punkt = punkt;
    }

    public Point wezPunkt() {
        return punkt;
    }

    public abstract Pozycja wezDanaPozycje(Kierunek kierunek);

    public abstract void ustawDanaPozycje(Pozycja pozycja, Kierunek kierunek);

    public abstract void ustawPozycjeBoczne(Pozycja[] pozycjeBoczne);

    public Pozycja[] wezPozycjeBoczne() {
        return new Pozycja[0];
    }

    public Pozycja[] wezZajetePozycjeBoczne() {
        LinkedList<Pozycja> lista = new LinkedList<>();
        Pozycja pozycjeBoczne[] = wezPozycjeBoczne();
        for (Pozycja Pozycja : pozycjeBoczne) {
            if (Pozycja != null && !Pozycja.czyPusta()) {
                lista.add(Pozycja);
            }
        }
        return lista.toArray(new Pozycja[0]);
    }

    public Pozycja wezlosowoZajetaPozycje() {
        while (true) {
            int i = losowa.nextInt(pozycjeBoczne.length);
            if (pozycjeBoczne[i] != null)
                return pozycjeBoczne[i];
        }
    }

    public boolean czyWolnaBocznaPozycja() {
        for (Pozycja Pozycja : pozycjeBoczne) {
            if (Pozycja != null && Pozycja.czyPusta())
                return true;
        }
        return false;
    }

    public boolean czySlabszyBocznyOrganiz(int sila) {
        for (Pozycja Pozycja : pozycjeBoczne) {
            if (Pozycja != null) {
                if (Pozycja.czyPusta())
                    return true;
                else if (Pozycja.wezOrganizm().wezSile() < sila)
                    return true;
            }
        }
        return false;
    }

    public Pozycja wezWolnaBocznaPozycje() {

        if (!czyWolnaBocznaPozycja())
            return null;
        while (true) {
            int i = losowa.nextInt(pozycjeBoczne.length);
            Pozycja Pozycja = pozycjeBoczne[i];
            if (Pozycja == null)
                continue;
            if (Pozycja.czyPusta())
                return Pozycja;
        }
    }

    public Pozycja wezSlabszyBocznyOrganiz(int sila) {
        if (!czySlabszyBocznyOrganiz(sila))
            return null;
        while (true) {
            int i = losowa.nextInt(pozycjeBoczne.length);
            Pozycja Pozycja = pozycjeBoczne[i];
            if (Pozycja == null)
                continue;
            if (Pozycja.czyPusta() || Pozycja.wezOrganizm().wezSile() < sila)
                return Pozycja;
        }
    }

    public String toString() {
        return "Pozycja [" + (int) wezPunkt().getX() + "," + (int) wezPunkt().getY() + "] ( "
                + organizm + " )";
    }
}
