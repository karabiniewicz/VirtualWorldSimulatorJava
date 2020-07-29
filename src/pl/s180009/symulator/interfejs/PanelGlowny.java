package pl.s180009.symulator.interfejs;

import pl.s180009.symulator.swiat.Swiat;

import javax.swing.*;
import java.awt.*;

public class PanelGlowny extends JPanel {
    private static final int X_WYDARZEN = 350;
    private static final int R_KOMORKI = 40, R_OBRAMOWANIA = 5;
    private static final int ANCHOR_X = 18, ANCHOR_Y = 63;

    private int szerokosc, wysokosc;
    private int panelSzerokosc;

    private Swiat swiat;

    public PanelGlowny(Swiat swiat) {
        panelSzerokosc = swiat.wezSzerokosc() * R_KOMORKI + 2 * R_OBRAMOWANIA;
        wysokosc = swiat.wezWysokosc() * R_KOMORKI + 2 * R_OBRAMOWANIA;
        szerokosc = panelSzerokosc + X_WYDARZEN;
        this.swiat = swiat;
        setLayout(null);
        stworzPanelGlowny();
    }

    private void stworzPanelGlowny() {
        this.setPreferredSize(new Dimension(szerokosc, wysokosc));
    }

    @Override
    protected void paintComponent(Graphics grafika) {
        super.paintComponent(grafika);

        rysujObramowanie(grafika);
        swiat.rysujSwiat(grafika, R_KOMORKI, R_OBRAMOWANIA, R_OBRAMOWANIA);
    }

    private void rysujObramowanie(Graphics grafika) {

        grafika.setColor(Color.lightGray);

        for (int i = R_OBRAMOWANIA; i <= panelSzerokosc - R_OBRAMOWANIA; i += R_KOMORKI)
            grafika.drawLine(i, R_OBRAMOWANIA, i, wysokosc - R_OBRAMOWANIA);

        for (int i = R_OBRAMOWANIA; i <= wysokosc - R_OBRAMOWANIA; i += R_KOMORKI)
            grafika.drawLine(R_OBRAMOWANIA, i, panelSzerokosc - R_OBRAMOWANIA, i);
    }

    public int wezSzerokosc() {
        return szerokosc;
    }

    public int wezWysokosc() {
        return wysokosc;
    }

    public int wezSzerokoscPanelu() {
        return panelSzerokosc;
    }

    public static int wezPozycjePaneluX() {
        return ANCHOR_X;
    }

    public static int wezPozycjePaneluY() {
        return ANCHOR_Y;
    }

    public static int wezRozmiarKomorki() {
        return R_KOMORKI;
    }

    public int wezSzerokoscWydarzen() {
        return X_WYDARZEN;
    }

    public void ustawSzerokosc(int szerokosc) {
        panelSzerokosc = szerokosc * R_KOMORKI + 2 * R_OBRAMOWANIA;
        this.szerokosc = panelSzerokosc + X_WYDARZEN;
    }

    public void ustawWysokosc(int wysokosc) {
        this.wysokosc = wysokosc * R_KOMORKI + 2 * R_OBRAMOWANIA;
    }
}
