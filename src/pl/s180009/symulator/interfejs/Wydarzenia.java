package pl.s180009.symulator.interfejs;

import pl.s180009.symulator.organizmy.Organizm;

import javax.swing.*;
import java.awt.*;

public class Wydarzenia {
    private int SZEROKOSC_WYDARZEN, KOLEJNA_LINIA = 20, WYSOKOSC_POCZATKOWA = 10;
    private int wysokosc;
    private int szerokosc;
    private PanelGlowny panel;

    public void ustawPanelWydarzen(PanelGlowny panelGlowny) {
        panel = panelGlowny;
        wysokosc = WYSOKOSC_POCZATKOWA;
        szerokosc = panel.wezSzerokoscPanelu();
        SZEROKOSC_WYDARZEN = panelGlowny.wezSzerokoscWydarzen();
    }

    public JLabel przypnijAutora() {
        JLabel autor = new JLabel("Adam Karabiniewicz, 180009", JLabel.CENTER);
        autor.setFont(new Font("Impact", Font.PLAIN, 20));
        autor.setOpaque(true);
        autor.setBackground(Color.gray);
        autor.setForeground(Color.darkGray);
        autor.setBounds(szerokosc, wysokosc, SZEROKOSC_WYDARZEN, 25);
        wysokosc += KOLEJNA_LINIA;
        panel.add(autor);
        return autor;
    }

    public JLabel przypnijWydarzenia() {
        JLabel wydarzenia = new JLabel("Wydarzenia:", JLabel.CENTER);
        wydarzenia.setBounds(szerokosc, wysokosc, SZEROKOSC_WYDARZEN, 25);
        wysokosc += KOLEJNA_LINIA;
        panel.add(wydarzenia);
        return wydarzenia;
    }

    public JLabel nowaTura(int tura) {
        JLabel wydarzenie = new JLabel("Rozpoczęto runda " + tura, JLabel.CENTER);
        wydarzenie.setBounds(szerokosc, wysokosc, SZEROKOSC_WYDARZEN, 25);
        wysokosc += KOLEJNA_LINIA;
        panel.add(wydarzenie);
        return wydarzenie;
    }

    public JLabel kliknietaPozycja(int pozycjaX, int pozycjaY) {
        JLabel wydarzenie = new JLabel("Kliknieta pozycja: (" + pozycjaX + "," + pozycjaY + ")", JLabel.CENTER);
        wydarzenie.setBounds(szerokosc, wysokosc, SZEROKOSC_WYDARZEN, 25);
        wysokosc += KOLEJNA_LINIA;
        panel.add(wydarzenie);
        return wydarzenie;
    }

    public JLabel smierc(Organizm zaAtakowany, Organizm atakujacy) {
        JLabel wydarzenie = new JLabel(zaAtakowany + " został zabity przez " + atakujacy, JLabel.CENTER);
        wydarzenie.setOpaque(true);
        wydarzenie.setBackground(Color.RED);
        wydarzenie.setBounds(szerokosc, wysokosc, SZEROKOSC_WYDARZEN, 25);
        wysokosc += KOLEJNA_LINIA;
        panel.add(wydarzenie);
        return wydarzenie;
    }

    public JLabel unik(Organizm zaAtakowany, Organizm atakujacy) {
        JLabel wydarzenie = new JLabel(zaAtakowany + " wykonał unik przed " + atakujacy, JLabel.CENTER);
        wydarzenie.setOpaque(true);
        wydarzenie.setBackground(Color.CYAN);
        wydarzenie.setBounds(szerokosc, wysokosc, SZEROKOSC_WYDARZEN, 25);
        wysokosc += KOLEJNA_LINIA;
        panel.add(wydarzenie);
        return wydarzenie;
    }

    public JLabel odbicieAtaku(Organizm zaAtakowany, Organizm atakujacy) {
        JLabel wydarzenie = new JLabel(zaAtakowany + " odbił atak " + atakujacy, JLabel.CENTER);
        wydarzenie.setOpaque(true);
        wydarzenie.setBackground(Color.CYAN);
        wydarzenie.setBounds(szerokosc, wysokosc, SZEROKOSC_WYDARZEN, 25);
        wysokosc += KOLEJNA_LINIA;
        panel.add(wydarzenie);
        return wydarzenie;
    }

    public JLabel aktywacjaZdolnosciSpecjalnej() {
        JLabel wydarzenie = new JLabel("Czlowiek aktywował zdolność specjalną!", JLabel.CENTER);
        wydarzenie.setOpaque(true);
        wydarzenie.setBackground(Color.green);
        wydarzenie.setBounds(szerokosc, wysokosc, SZEROKOSC_WYDARZEN, 25);
        wysokosc += KOLEJNA_LINIA;
        panel.add(wydarzenie);
        return wydarzenie;
    }

    public JLabel zakonczenieZdolnosciSpecjalnej() {
        JLabel wydarzenie = new JLabel("Czlowiek zakończył zdolność specjalną", JLabel.CENTER);
        wydarzenie.setOpaque(true);
        wydarzenie.setBackground(Color.PINK);
        wydarzenie.setBounds(szerokosc, wysokosc, SZEROKOSC_WYDARZEN, 25);
        wysokosc += KOLEJNA_LINIA;
        panel.add(wydarzenie);
        return wydarzenie;
    }

    public JLabel mozliwoscZdolnosciSpecjalnej() {
        JLabel wydarzenie = new JLabel("Zdolność specjalna człowieka gotowa!", JLabel.CENTER);
        wydarzenie.setOpaque(true);
        wydarzenie.setBackground(Color.PINK);
        wydarzenie.setBounds(szerokosc, wysokosc, SZEROKOSC_WYDARZEN, 25);
        wysokosc += KOLEJNA_LINIA;
        panel.add(wydarzenie);
        return wydarzenie;
    }

    public JLabel zapisanoSwiat() {
        JLabel wydarzenie = new JLabel("Świat został zapisany!", JLabel.CENTER);
        wydarzenie.setOpaque(true);
        wydarzenie.setBackground(Color.green);
        wydarzenie.setForeground(Color.WHITE);
        wydarzenie.setBounds(szerokosc, wysokosc, SZEROKOSC_WYDARZEN, 25);
        wysokosc += KOLEJNA_LINIA;
        panel.add(wydarzenie);
        return wydarzenie;
    }

    public JLabel wczytanoSwiat() {
        JLabel wczytanie = new JLabel("Świat został wczytany!", JLabel.CENTER);
        wczytanie.setOpaque(true);
        wczytanie.setBackground(Color.green);
        wczytanie.setForeground(Color.WHITE);
        wczytanie.setBounds(szerokosc, wysokosc, SZEROKOSC_WYDARZEN, 25);
        wysokosc += KOLEJNA_LINIA;
        panel.add(wczytanie);
        return wczytanie;
    }

    public boolean czyWyczyscicWydarzenia() {
        if (wysokosc > panel.wezWysokosc() / 2) {
            wysokosc = WYSOKOSC_POCZATKOWA + 2 * KOLEJNA_LINIA;
            return true;
        }
        return false;
    }
}

