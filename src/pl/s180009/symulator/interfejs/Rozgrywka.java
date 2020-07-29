package pl.s180009.symulator.interfejs;

import pl.s180009.symulator.plansza.RodzajPlanszy;
import pl.s180009.symulator.pozycja.Pozycja;
import pl.s180009.symulator.organizmy.Organizm;
import pl.s180009.symulator.organizmy.rosliny.*;
import pl.s180009.symulator.organizmy.zwierzeta.*;
import pl.s180009.symulator.swiat.Swiat;

import javax.swing.*;
import java.awt.event.*;

public class Rozgrywka extends JFrame implements KeyListener, MouseListener, ActionListener {

    private Swiat swiat;
    private Pozycja pozycjaKlikniecia;
    private PanelGlowny panelGlowny;
    private JPopupMenu menuPostaci;

    JMenuBar menuPasek;
    JMenu pasekSwiat, swiatNowy;
    JMenuItem nowyNaKracie, nowyNaSzeciennych, zapiszSwiat, wczytajSwiat, zakonczSwiat, kolejnaTura;
    JMenuItem wilczeJagody, barszczSosnowskiego, trawa, mlecz, guarana;
    JMenuItem antylopa, cyberOwca, lis, owca, wilk, zolw;

    public Rozgrywka(Swiat swiat) {
        this.swiat = swiat;
        this.menuPasek = null;
        this.panelGlowny = new PanelGlowny(swiat);
        add(panelGlowny);
        aktualizujPanel();
        addMouseListener(this);
        addKeyListener(this);
        stworzRozgrywke();
    }

    private void stworzRozgrywke() {
        menuRozgrywki();
        menuKlikniecia();
        setLocationRelativeTo(null);
        setTitle("Symulacja Świata");
        swiat.aktualizujWydarzenia(panelGlowny);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void menuRozgrywki() {

        menuPasek = new JMenuBar();
        setJMenuBar(menuPasek);

        pasekSwiat = new JMenu(" ŚWIAT ");
        menuPasek.add(pasekSwiat);

        kolejnaTura = new JMenuItem("NASTEPNA TURA");
        kolejnaTura.setAccelerator(KeyStroke.getKeyStroke("SPACE"));
        kolejnaTura.addActionListener(this);
        menuPasek.add(kolejnaTura);

        swiatNowy = new JMenu("Nowy świat");
        pasekSwiat.add(swiatNowy);

        nowyNaKracie = new JMenuItem("Plansza kwadratowa");
        nowyNaKracie.setAccelerator(KeyStroke.getKeyStroke("N"));
        nowyNaKracie.addActionListener(this);
        swiatNowy.add(nowyNaKracie);

        swiatNowy.addSeparator();

        nowyNaSzeciennych = new JMenuItem("Plansza hexagonalna");
        nowyNaSzeciennych.setAccelerator(KeyStroke.getKeyStroke("H"));
        nowyNaSzeciennych.addActionListener(this);
        swiatNowy.add(nowyNaSzeciennych);

        pasekSwiat.addSeparator();

        zapiszSwiat = new JMenuItem("Zapisz świat");
        zapiszSwiat.setAccelerator(KeyStroke.getKeyStroke("S"));
        zapiszSwiat.addActionListener(this);
        pasekSwiat.add(zapiszSwiat);

        pasekSwiat.addSeparator();

        wczytajSwiat = new JMenuItem("Wczytaj świat");
        wczytajSwiat.setAccelerator(KeyStroke.getKeyStroke("W"));
        wczytajSwiat.addActionListener(this);
        pasekSwiat.add(wczytajSwiat);

        pasekSwiat.addSeparator();

        zakonczSwiat = new JMenuItem("Zakończ świat");
        zakonczSwiat.setAccelerator(KeyStroke.getKeyStroke("Z"));
        zakonczSwiat.addActionListener(this);
        pasekSwiat.add(zakonczSwiat);

    }

    private void menuKlikniecia() {
        this.menuPostaci = new JPopupMenu();

        barszczSosnowskiego = new JMenuItem("Barszcz Sosnowskiego");
        barszczSosnowskiego.addActionListener(this);
        menuPostaci.add(barszczSosnowskiego);

        guarana = new JMenuItem("Guarana");
        guarana.addActionListener(this);
        menuPostaci.add(guarana);

        mlecz = new JMenuItem("Mlecz");
        mlecz.addActionListener(this);
        menuPostaci.add(mlecz);

        trawa = new JMenuItem("Trawa");
        trawa.addActionListener(this);
        menuPostaci.add(trawa);

        wilczeJagody = new JMenuItem("Wilcze Jagody");
        wilczeJagody.addActionListener(this);
        menuPostaci.add(wilczeJagody);

        menuPostaci.addSeparator();

        antylopa = new JMenuItem("Antylopa");
        antylopa.addActionListener(this);
        menuPostaci.add(antylopa);

        cyberOwca = new JMenuItem("Cyber Owca");
        cyberOwca.addActionListener(this);
        menuPostaci.add(cyberOwca);

        lis = new JMenuItem("Lis");
        lis.addActionListener(this);
        menuPostaci.add(lis);

        owca = new JMenuItem("Owca");
        owca.addActionListener(this);
        menuPostaci.add(owca);

        wilk = new JMenuItem("Wilk");
        wilk.addActionListener(this);
        menuPostaci.add(wilk);

        zolw = new JMenuItem("Zółw");
        zolw.addActionListener(this);
        menuPostaci.add(zolw);
    }

    public void aktualizujPanel() {
        setSize(panelGlowny.wezSzerokosc() + 20, panelGlowny.wezWysokosc() + 60);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object objekt = e.getSource();

        if (objekt == nowyNaKracie) {
            int wysokosc = 0, szerokosc = 0;

            try {
                wysokosc = Integer.parseInt(JOptionPane.showInputDialog(this, "Podaj wysokość mapy", "Wysokość nowego świata", JOptionPane.PLAIN_MESSAGE));
                szerokosc = Integer.parseInt(JOptionPane.showInputDialog(this, "Podaj szerokość mapy", "Szerkość nowego świata", JOptionPane.PLAIN_MESSAGE));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Proszę podać poprawne wartości", "BLAD: błędne dane wejściowe", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (wysokosc <= 0 || szerokosc <= 0) {
                JOptionPane.showMessageDialog(this, "Proszę podać poprawne wartości", "BLAD: ujemne dane wejściowe", JOptionPane.ERROR_MESSAGE);
                return;
            }

            swiat.nowySwiat(szerokosc, wysokosc, RodzajPlanszy.KWADRATOWA, true);
            panelGlowny.ustawSzerokosc(szerokosc);
            panelGlowny.ustawWysokosc(wysokosc);
            swiat.aktualizujWydarzenia(panelGlowny);
            aktualizujPanel();
            repaint();
        } else if (objekt == nowyNaSzeciennych) {
            // brak
        } else if (objekt == zapiszSwiat) {
            swiat.zapiszSwiat();
            aktualizujPanel();
        } else if (objekt == wczytajSwiat) {
            swiat.wczytajSwiat();
            panelGlowny.ustawSzerokosc(swiat.wezSzerokosc());
            panelGlowny.ustawWysokosc(swiat.wezWysokosc());
            swiat.aktualizujWydarzenia(panelGlowny);
            aktualizujPanel();
            swiat.dodajWydarzenie(swiat.wezWydarzenia().wczytanoSwiat());
            repaint();
        } else if (objekt == zakonczSwiat) {
            System.exit(0);
        } else if (objekt == kolejnaTura) {
            swiat.nastepnaTura();
            repaint();
        } else if (objekt == barszczSosnowskiego) {
            stworzOrganizm(new BarszczSosnowskiego(swiat, this.pozycjaKlikniecia, pozycjaKlikniecia.wezPunkt()));
        } else if (objekt == guarana) {
            stworzOrganizm(new Guarana(swiat, this.pozycjaKlikniecia, pozycjaKlikniecia.wezPunkt()));
        } else if (objekt == mlecz) {
            stworzOrganizm(new Mlecz(swiat, this.pozycjaKlikniecia, pozycjaKlikniecia.wezPunkt()));
        } else if (objekt == trawa) {
            stworzOrganizm(new Trawa(swiat, this.pozycjaKlikniecia, pozycjaKlikniecia.wezPunkt()));
        } else if (objekt == wilczeJagody) {
            stworzOrganizm(new WilczeJagody(swiat, this.pozycjaKlikniecia, pozycjaKlikniecia.wezPunkt()));
        } else if (objekt == antylopa) {
            stworzOrganizm(new Antylopa(swiat, this.pozycjaKlikniecia, pozycjaKlikniecia.wezPunkt()));
        } else if (objekt == cyberOwca) {
            stworzOrganizm(new CyberOwca(swiat, this.pozycjaKlikniecia, pozycjaKlikniecia.wezPunkt()));
        } else if (objekt == lis) {
            stworzOrganizm(new Lis(swiat, this.pozycjaKlikniecia, pozycjaKlikniecia.wezPunkt()));
        } else if (objekt == owca) {
            stworzOrganizm(new Owca(swiat, this.pozycjaKlikniecia, pozycjaKlikniecia.wezPunkt()));
        } else if (objekt == wilk) {
            stworzOrganizm(new Wilk(swiat, this.pozycjaKlikniecia, pozycjaKlikniecia.wezPunkt()));
        } else if (objekt == zolw) {
            stworzOrganizm(new Zolw(swiat, this.pozycjaKlikniecia, pozycjaKlikniecia.wezPunkt()));
        }
    }

    private void stworzOrganizm(Organizm organizm) {
        swiat.dodajOrganizm(organizm);
        this.pozycjaKlikniecia.ustawOrganizm(organizm);
        swiat.zaiinicjujNoweOrganizmy();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        swiat.wcisnietyKlawisz(e);
        if (swiat.czyRysowac())
            repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() != MouseEvent.BUTTON1)
            return;
        pozycjaKlikniecia = swiat.kliknietaPozycja(e);
        if (pozycjaKlikniecia == null || !pozycjaKlikniecia.czyPusta())
            return;
        menuPostaci.show(e.getComponent(), e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
