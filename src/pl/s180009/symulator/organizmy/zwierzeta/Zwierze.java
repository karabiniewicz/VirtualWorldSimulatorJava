package pl.s180009.symulator.organizmy.zwierzeta;

import pl.s180009.symulator.pozycja.Pozycja;
import pl.s180009.symulator.organizmy.Organizm;
import pl.s180009.symulator.swiat.Swiat;

import java.awt.*;

public abstract class Zwierze extends Organizm {
    protected static int DOMYSLNY_WIEK_ROZMNAZANIA = 4;
    protected static int DOMYSLNY_ZASIEG = 1;
    protected static int DOMYSLNA_SZANSA = 1;
    protected int zasiegRuchu;
    protected double szansaWykonaniaRuchu;

    public Zwierze(Swiat swiat, Pozycja pozycja, Point punkt, Color kolor, int sila, int inicjatywa) {
        this(swiat, pozycja, punkt, kolor, sila, inicjatywa, DOMYSLNY_ZASIEG, DOMYSLNA_SZANSA);
    }

    public Zwierze(Swiat swiat, Pozycja pozycja, Point punkt, Color kolor,
                   int sila, int inicjatywa, int zasiegRuchu, double szansaWykonaniaRuchu) {
        super(swiat, pozycja, punkt, kolor, sila, inicjatywa);
        this.zasiegRuchu = zasiegRuchu;
        this.szansaWykonaniaRuchu = szansaWykonaniaRuchu;
    }

    @Override
    public void akcja() {
        this.aktualizujWiek();

        if (czyZyje() && czyWykonalRuch()) {
            for (int i = 0; i < zasiegRuchu; ++i) {
                if (!wykonajRuch())
                    break;
            }
        }
    }

    protected Pozycja wezDocelowaPozycje() {
        return this.wezPozycje().wezlosowoZajetaPozycje();
    }

    protected boolean wykonajRuch() {
        Pozycja ostateczna = wezDocelowaPozycje();
        if (ostateczna == null)
            return false;
        if (ostateczna.czyPusta()) {
            this.wykonajRuch(ostateczna);
            return true;
        } else if (czyTenSamGatunek(ostateczna.wezOrganizm())) {
            rozmnozSie(ostateczna.wezOrganizm());
            return false;
        } else if (ostateczna.wezOrganizm().kolizja(this)) {
            wykonajRuch(ostateczna);
            return false;
        }
        return false;
    }

    protected abstract boolean czyTenSamGatunek(Organizm organizm);

    protected boolean rozmnozSie(Organizm partner) {
        if ((this.wezWiek() < DOMYSLNY_WIEK_ROZMNAZANIA) || (partner.wezWiek() < DOMYSLNY_WIEK_ROZMNAZANIA))
            return false;

        Pozycja pozycja;
        int chance = generuj.nextInt(2);
        if (chance == 0) {
            pozycja = this.wezPozycje().wezWolnaBocznaPozycje();
            if (pozycja == null)
                pozycja = partner.wezPozycje().wezWolnaBocznaPozycje();
        } else {
            pozycja = partner.wezPozycje().wezWolnaBocznaPozycje();
            if (pozycja == null)
                pozycja = this.wezPozycje().wezWolnaBocznaPozycje();
        }

        if (pozycja == null)
            return false;

        Organizm dziecko = this.stworzDziecko();
        swiat.dodajOrganizm(dziecko);
        dziecko.ustawPozycje(pozycja);
        dziecko.ustawPunkt(pozycja.wezPunkt());
        pozycja.ustawOrganizm(dziecko);
        return true;
    }

    protected boolean czyWykonalRuch() {
        double szansa = generuj.nextDouble();
        return szansa <= szansaWykonaniaRuchu;
    }

    protected boolean wykonajUnik(Organizm atakujacy) {
        return false;
    }

    @Override
    public boolean kolizja(Organizm organizm) {
        if (this.wezSile() > organizm.wezSile()) {
            if (!organizm.czyOdbilAtak(this))
                organizm.smierc(this);
            return false;
        } else {
            if (this.wykonajUnik(organizm)) {
                swiat.dodajWydarzenie(swiat.wezWydarzenia().unik(this, organizm));
                return true;
            } else if (this.czyOdbilAtak(organizm)) {
                swiat.dodajWydarzenie(swiat.wezWydarzenia().odbicieAtaku(this, organizm));
                return false;
            } else {
                this.smierc(organizm);
                return true;
            }
        }
    }
}


