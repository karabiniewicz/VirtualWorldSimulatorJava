package pl.s180009.symulator.organizmy.zwierzeta;

import pl.s180009.symulator.kierunek.Kierunek;
import pl.s180009.symulator.kierunek.KierunekKwadrat;
import pl.s180009.symulator.organizmy.Organizm;
import pl.s180009.symulator.pozycja.Pozycja;
import pl.s180009.symulator.swiat.Swiat;

import java.awt.*;

public class Czlowiek extends Zwierze {
    private static final Color kolor = new Color(255, 188, 159);
    private static final int sila = 5;
    private static final int inicjatywa = 4;
    private static final int czasZdolnosci = 5;
    private static final int blokadaZdolnosci = 5;
    private int pozostalyCzasZdolnosci;
    private int pozostalyCzasBlokady;

    private Kierunek kierunek;

    public Czlowiek(Swiat swiat, Pozycja pozycja, Point punkt) {
        super(swiat, pozycja, punkt, kolor, sila, inicjatywa);
        this.zaladujZdjecie("zdjecia/Czlowiek.jpg");
        pozostalyCzasZdolnosci = 0;
        pozostalyCzasBlokady = 0;
        kierunek = KierunekKwadrat.GORA;
    }

    @Override
    public Organizm stworzDziecko() {
        return new Czlowiek(swiat, wezPozycje(), wezPunkt());
    }

    @Override
    public String wezNazwe() {
        return "Czlowiek";
    }

    @Override
    protected boolean czyTenSamGatunek(Organizm organizm) {
        return organizm instanceof Czlowiek;
    }

    @Override
    protected Pozycja wezDocelowaPozycje() {
        return this.wezPozycje().wezDanaPozycje(kierunek);
    }

    private void zdolnoscSpecjalna() {
        if (pozostalyCzasBlokady == 0)
            return;
        Pozycja pozycje[] = this.wezPozycje().wezZajetePozycjeBoczne();
        for (Pozycja pozycja : pozycje)
            pozycja.wezOrganizm().smierc(this);

    }

    public boolean aktywacjaZdolnosci() {
        if (pozostalyCzasZdolnosci > 0 || pozostalyCzasBlokady > 0)
            return false;
        pozostalyCzasZdolnosci = blokadaZdolnosci;
        pozostalyCzasBlokady = czasZdolnosci;
        swiat.dodajWydarzenie(swiat.wezWydarzenia().aktywacjaZdolnosciSpecjalnej());
        zdolnoscSpecjalna();
        return true;
    }

    public void ustawKierunek(Kierunek kierunek) {
        this.kierunek = kierunek;
    }

    private void turaZdolnosci() {
        if (pozostalyCzasBlokady > 0) {
            --pozostalyCzasBlokady;
            if (pozostalyCzasBlokady == 0)
                swiat.dodajWydarzenie(swiat.wezWydarzenia().zakonczenieZdolnosciSpecjalnej());
        } else if (pozostalyCzasZdolnosci > 0) {
            --pozostalyCzasZdolnosci;
            if (pozostalyCzasZdolnosci == 0) {
                swiat.dodajWydarzenie(swiat.wezWydarzenia().mozliwoscZdolnosciSpecjalnej());
            }
        }
    }

    @Override
    public void akcja() {
        if (!czyZyje())
            return;
        zdolnoscSpecjalna();
        super.akcja();
        zdolnoscSpecjalna();
        turaZdolnosci();
    }

    @Override
    public String toString() {
        return super.toString() + "<" + this.pozostalyCzasBlokady + "," + this.pozostalyCzasZdolnosci + ">";
    }

    public void ustawPozostalyCzasZdolnosci(int pozostalyCzasZdolnosci) {
        this.pozostalyCzasZdolnosci = pozostalyCzasZdolnosci;
    }

    public void ustawPozostalyCzasBlokady(int pozostalyCzasBlokady) {
        this.pozostalyCzasBlokady = pozostalyCzasBlokady;
    }
}
