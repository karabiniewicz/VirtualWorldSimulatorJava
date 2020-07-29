package pl.s180009.symulator.organizmy;

import pl.s180009.symulator.pozycja.Pozycja;
import pl.s180009.symulator.swiat.Swiat;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public abstract class Organizm {
    private int sila;
    private int inicjatywa;
    private int wiek;
    private boolean czyZyje;
    private Color kolor;
    private Point punkt;
    private Pozycja pozycja;
    private BufferedImage zdjecie;

    protected Swiat swiat;
    protected static Random generuj = new Random();

    public Organizm(Swiat swiat, Pozycja pozycja, Point punkt, Color kolor, int sila, int inicjatywa) {
        this.swiat = swiat;
        this.pozycja = pozycja;
        this.punkt = punkt;
        this.kolor = kolor;
        this.sila = sila;
        this.inicjatywa = inicjatywa;
        this.czyZyje = true;
        this.wiek = 0;
    }

    public abstract void akcja();

    public abstract boolean kolizja(Organizm organizm);

    protected void aktualizujWiek() {
        ++wiek;
    }

    public int wezWiek() {
        return wiek;
    }

    protected BufferedImage wezZdjecie() {
        return zdjecie;
    }

    protected BufferedImage zaladujZdjecie(String sciezka) {
        try {
            zdjecie = ImageIO.read(new File(sciezka));
        } catch (IOException ex) {
            System.out.println("Nie mozna załadować danej scieżki: " + sciezka);
            System.exit(1);
        }
        return null;
    }

    public Pozycja wezPozycje() {
        return pozycja;
    }

    public void ustawPozycje(Pozycja pozycja) {
        this.pozycja = pozycja;
    }

    public boolean wykonajRuch(Pozycja pozycja) {
        if (!pozycja.czyPusta())
            return false;
        this.wezPozycje().ustawOrganizm(null);
        this.ustawPozycje(pozycja);
        pozycja.ustawOrganizm(this);
        this.punkt = pozycja.wezPunkt();
        return true;
    }

    public Color wezKolor() {
        return kolor;
    }

    public boolean czyZyje() {
        return czyZyje;
    }

    public void narysujPostac(Graphics grafika, int rozmiarKomorki, int zmianaX, int zmianaY) {
        if (!czyZyje())
            return;
        int x = zmianaX + punkt.x * rozmiarKomorki;
        int y = zmianaY + punkt.y * rozmiarKomorki;

        BufferedImage zdjecie = this.wezZdjecie();
        if (zdjecie == null) {
            grafika.setColor(kolor);
            grafika.fillRect(x + 1, y + 1, rozmiarKomorki - 1, rozmiarKomorki - 1);
        } else {
            TexturePaint ksztalt = new TexturePaint(zdjecie,
                    new Rectangle(x + 1, y + 1, rozmiarKomorki - 2, rozmiarKomorki - 2));
            Graphics2D g2d = (Graphics2D) grafika.create();
            g2d.setPaint(ksztalt);
            g2d.fillRect(x + 1, y + 1, rozmiarKomorki - 1, rozmiarKomorki - 1);
        }
    }

    public Point wezPunkt() {
        return punkt;
    }

    public void ustawPunkt(Point punkt) {
        this.punkt = punkt;
    }

    public int wezSile() {
        return sila;
    }

    public void zwiekszSile(int sila) {
        this.sila += sila;
    }

    public int wezInicjatywe() {
        return inicjatywa;
    }

    public boolean smierc(Organizm organizm) {
        swiat.dodajWydarzenie(swiat.wezWydarzenia().smierc(this, organizm));
        this.czyZyje = false;
        this.pozycja.ustawOrganizm(null);
        this.pozycja = null;
        return true;
    }

    public boolean czyOdbilAtak(Organizm organizm) {
        return false;
    }

    public String toString() {
        return this.wezNazwe() + "[" + (int) wezPunkt().getX() + "," + (int) wezPunkt().getY()
                + "](" + wezSile() + ")";
    }

    public abstract String wezNazwe();

    public abstract Organizm stworzDziecko();
}
