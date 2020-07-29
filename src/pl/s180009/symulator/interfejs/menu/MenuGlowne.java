package pl.s180009.symulator.interfejs.menu;

import pl.s180009.symulator.interfejs.Rozgrywka;
import pl.s180009.symulator.plansza.RodzajPlanszy;
import pl.s180009.symulator.swiat.Swiat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGlowne extends JFrame implements ActionListener {
    private static final int R_KLASYCZNY = 20;

    private Swiat swiat;
    private Rozgrywka rozgrywka;
    private WprowadzDane wprowadzDane;
    private JButton bKlasycznySwiat, bWlasnySwiat, bWczytajSwiat, bZakoncz;
    private JLabel lKlasycznySwiat, lWlasnySwiat;

    public MenuGlowne(Swiat swiat) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        this.swiat = swiat;
        setTitle(" Symulator Swiata - Menu Główne");
        setSize(600, 400);
        setResizable(false);
        setLayout(null);
        ustawStylProjektu();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        bKlasycznySwiat = new JButton("Klasyczny Świat");
        bKlasycznySwiat.setBounds(30, 50, 250, 60);
        bKlasycznySwiat.addActionListener(this);
        bKlasycznySwiat.setFont(new Font("Impact", Font.PLAIN, 18));
        add(bKlasycznySwiat);

        bWlasnySwiat = new JButton("Własny Świat");
        bWlasnySwiat.setBounds(310, 50, 250, 60);
        bWlasnySwiat.setFont(new Font("Impact", Font.PLAIN, 18));
        bWlasnySwiat.addActionListener(this);
        add(bWlasnySwiat);

        lKlasycznySwiat = new JLabel("Nowy Świat - plansza kwadratowa - 20 x 20", JLabel.CENTER);
        lWlasnySwiat = new JLabel("Nowy Świat - wybór planszy - wybór rozmiaru", JLabel.CENTER);
        lKlasycznySwiat.setBounds(30, 90, 250, 60);
        lWlasnySwiat.setBounds(310, 90, 250, 60);
        add(lKlasycznySwiat);
        add(lWlasnySwiat);

        bWczytajSwiat = new JButton("Wczytaj Świat");
        bWczytajSwiat.setBounds(30, 200, 250, 60);
        bWczytajSwiat.addActionListener(this);
        bWczytajSwiat.setFont(new Font("Impact", Font.PLAIN, 18));
        add(bWczytajSwiat);

        bZakoncz = new JButton("Zakończ");
        bZakoncz.setBounds(310, 200, 250, 60);
        bZakoncz.setFont(new Font("Impact", Font.PLAIN, 18));
        bZakoncz.addActionListener(this);
        add(bZakoncz);
    }

    private void ustawStylProjektu() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void wysrodkuj() {
        Dimension wymiarEkranu = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(wymiarEkranu.width / 2 - this.getSize().width / 2, wymiarEkranu.height / 2 - this.getSize().height / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object objekt = e.getSource();

        if (objekt == bZakoncz) {
            System.exit(0);
        } else if (objekt == bKlasycznySwiat) {
            setVisible(false);
            swiat.stworzSwiat(R_KLASYCZNY, R_KLASYCZNY, RodzajPlanszy.KWADRATOWA, true);
            rozgrywka = new Rozgrywka(swiat);
            rozgrywka.setVisible(true);
        } else if (objekt == bWlasnySwiat) {
            if (wprowadzDane == null) {
                wprowadzDane = new WprowadzDane(this);
            }
            wprowadzDane.setVisible(true);
            wprowadzDane.ustawFokus();

            if (wprowadzDane.czyPoprawneDane()) {
                int szerokosc = 0, wysokosc = 0;

                try {
                    szerokosc = wprowadzDane.wezSzerokosc();
                    wysokosc = wprowadzDane.wezWysokosc();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Proszę podać poprawne wartości", "BLAD: błędne dane wejściowe", JOptionPane.ERROR_MESSAGE);
                    wprowadzDane = null;
                    return;
                }
                if (szerokosc <= 0 || wysokosc <= 0) {
                    JOptionPane.showMessageDialog(this, "Proszę podać poprawne wartości", "BLAD: ujemne dane wejściowe", JOptionPane.ERROR_MESSAGE);
                    wprowadzDane = null;
                    return;
                }
                setVisible(false);
                swiat.stworzSwiat(szerokosc, wysokosc, RodzajPlanszy.KWADRATOWA, true);
                rozgrywka = new Rozgrywka(swiat);

                rozgrywka.setVisible(true);
                wprowadzDane = null;
            }

        } else if (objekt == bWczytajSwiat) {
            setVisible(false);
            swiat.wczytajSwiat();
            rozgrywka = new Rozgrywka(swiat);
            rozgrywka.setVisible(true);
            swiat.dodajWydarzenie(swiat.wezWydarzenia().wczytanoSwiat());
            repaint();
        }
    }
}
