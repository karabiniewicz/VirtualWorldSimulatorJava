package pl.s180009.symulator.swiat;

import pl.s180009.symulator.interfejs.PanelGlowny;
import pl.s180009.symulator.interfejs.Wydarzenia;
import pl.s180009.symulator.kierunek.KierunekKwadrat;
import pl.s180009.symulator.organizmy.Organizm;
import pl.s180009.symulator.organizmy.zwierzeta.*;
import pl.s180009.symulator.organizmy.rosliny.*;
import pl.s180009.symulator.plansza.Plansza;
import pl.s180009.symulator.plansza.PlanszaHexagonalna;
import pl.s180009.symulator.plansza.PlanszaKwadratowa;
import pl.s180009.symulator.plansza.RodzajPlanszy;
import pl.s180009.symulator.pozycja.Pozycja;
import pl.s180009.symulator.zapis.DaneZapisu;
import pl.s180009.symulator.zapis.KontrolaZapisu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;

public class Swiat {
    private int ZAKRES_SZANSY = 50;
    private int SZANSA_ORGANIZMOW = 10;
    private int DUZA_LICZBA = 1000000;
    private int tura;
    private boolean rysuj;
    private Plansza plansza;
    private Czlowiek czlowiek;
    private Wydarzenia wydarzenia;
    private JLabel autorSwiata;
    private JLabel wydarzenie;
    private RodzajPlanszy rodzajPlanszy = RodzajPlanszy.KWADRATOWA;
    private List<Organizm> organizmy = new LinkedList<Organizm>();
    private List<Organizm> wszystkieBarszcze = new LinkedList<Organizm>();
    private List<JLabel> wszystkieWydarzenia = new LinkedList<JLabel>();
    private LinkedList<Organizm> noweOrganizmy = new LinkedList<Organizm>();

    public Swiat() {
        this.tura = 1;
        this.rysuj = false;
        this.wydarzenia = new Wydarzenia();
    }

    public void stworzSwiat(int szerokosc, int wysokosc, RodzajPlanszy rodzajPlanszy, boolean czyZaludnic) {
        if (rodzajPlanszy == rodzajPlanszy.KWADRATOWA) {
            this.plansza = new PlanszaKwadratowa(szerokosc, wysokosc);
        } else {
            this.plansza = new PlanszaHexagonalna(szerokosc, wysokosc);
        }
        if (czyZaludnic)
            zaludnijSwiat();
    }

    public boolean nowySwiat(int szerokosc, int wysokosc, RodzajPlanszy rodzajPlanszy, boolean czyZaludnic) {
        if (szerokosc <= 0 || wysokosc <= 0)
            return false;
        poczatkowyStan(szerokosc, wysokosc);
        this.stworzSwiat(szerokosc, wysokosc, rodzajPlanszy, czyZaludnic);

        return true;
    }

    private void poczatkowyStan(int szerokosc, int wysokosc) {
        if (plansza == null)
            plansza = new PlanszaKwadratowa(szerokosc, wysokosc);
        else
            plansza.ustawRozmiar(szerokosc, wysokosc);

        this.tura = 1;
        organizmy = new LinkedList<>();
        noweOrganizmy = new LinkedList<>();
    }

    private void zaludnijSwiat() {
        Random losowa = new Random();
        int x = losowa.nextInt(plansza.wezSzerokosc());
        int y = losowa.nextInt(plansza.wezWysokosc());

        Pozycja pozycja = plansza.wezPozycje(x, y);
        if (pozycja.czyPusta()) {
            czlowiek = new Czlowiek(this, pozycja, new Point(x, y));
            pozycja.ustawOrganizm(czlowiek);
            organizmy.add(czlowiek);
        }

        for (x = 0; x < plansza.wezSzerokosc(); ++x) {
            for (y = 0; y < plansza.wezWysokosc(); ++y) {
                int i = losowa.nextInt(ZAKRES_SZANSY);
                if (i <= SZANSA_ORGANIZMOW) {
                    pozycja = plansza.wezPozycje(x, y);
                    if (pozycja.czyPusta()) {
                        Organizm organizm = null;
                        Point punkt = new Point(x, y);
                        if (i == 0) {
                            organizm = new BarszczSosnowskiego(this, pozycja, punkt);
                            dodajBarszcz(organizm);
                        } else if (i == 1)
                            organizm = new Antylopa(this, pozycja, punkt);
                        else if (i == 2)
                            organizm = new Guarana(this, pozycja, punkt);
                        else if (i == 3)
                            organizm = new CyberOwca(this, pozycja, punkt);
                        else if (i == 4)
                            organizm = new Mlecz(this, pozycja, punkt);
                        else if (i == 5)
                            organizm = new Lis(this, pozycja, punkt);
                        else if (i == 6)
                            organizm = new Trawa(this, pozycja, punkt);
                        else if (i == 7)
                            organizm = new Owca(this, pozycja, punkt);
                        else if (i == 8)
                            organizm = new WilczeJagody(this, pozycja, punkt);
                        else if (i == 9)
                            organizm = new Wilk(this, pozycja, punkt);
                        else if (i == 10)
                            organizm = new Zolw(this, pozycja, punkt);

                        pozycja.ustawOrganizm(organizm);
                        organizmy.add(organizm);
                    }
                }
            }
        }
        this.sortujOrganizmy();
    }

    public Wydarzenia wezWydarzenia() {
        return wydarzenia;
    }

    public void dodajWydarzenie(JLabel wydarzenie) {
        wszystkieWydarzenia.add(wydarzenie);
    }

    public void dodajAutora(JLabel wydarzenie) {
        autorSwiata = wydarzenie;
    }

    public void dodajWydarzenia(JLabel wydarzenie) {
        this.wydarzenie = wydarzenie;
    }

    public void aktualizujWydarzenia(PanelGlowny panelGlowny) {
        if (autorSwiata != null) {
            this.autorSwiata.setVisible(false);
            this.wydarzenie.setVisible(false);
        }
        wyczyscWydarzenia();
        wezWydarzenia().ustawPanelWydarzen(panelGlowny);
        dodajAutora(wezWydarzenia().przypnijAutora());
        dodajWydarzenia(wezWydarzenia().przypnijWydarzenia());
    }

    private void sortujOrganizmy() {
        organizmy.sort(Comparator.comparing(Organizm::wezInicjatywe).reversed());
    }

    public void nastepnaTura() {
        if (wezWydarzenia().czyWyczyscicWydarzenia()) {
            wyczyscWydarzenia();
        }
        wszystkieWydarzenia.add(wezWydarzenia().nowaTura(tura));
        ++tura;
        for (Organizm organizm : organizmy) {
            organizm.akcja();
        }
        this.walidujOrganizmy();
        this.zaiinicjujNoweOrganizmy();
        this.sortujOrganizmy();
    }

    private void wyczyscWydarzenia() {
        for (int i = 0; i < wszystkieWydarzenia.size(); ++i) {
            wszystkieWydarzenia.get(i).setVisible(false);
            wszystkieWydarzenia.remove(i);
            --i;
        }
    }

    private void walidujOrganizmy() {
        for (int i = 0; i < organizmy.size(); ++i) {
            if (!organizmy.get(i).czyZyje()) {
                organizmy.remove(i);
                --i;
            }
        }
        for (int i = 0; i < wszystkieBarszcze.size(); ++i) {
            if (!wszystkieBarszcze.get(i).czyZyje()) {
                wszystkieBarszcze.remove(i);
                --i;
            }
        }
    }

    public void rysujSwiat(Graphics grafika, int rozmiarKomorki, int zmianaX, int zmianaY) {
        for (Organizm organizm : organizmy) {
            organizm.narysujPostac(grafika, rozmiarKomorki, zmianaX, zmianaY);
        }
    }

    public void dodajOrganizm(Organizm organizm) {
        noweOrganizmy.add(organizm);
        if (organizm instanceof BarszczSosnowskiego)
            dodajBarszcz(organizm);
    }

    public void wcisnietyKlawisz(KeyEvent event) {
        int key = event.getKeyCode();

        switch (key) {
            case KeyEvent.VK_UP:
                czlowiek.ustawKierunek(KierunekKwadrat.GORA);
                break;
            case KeyEvent.VK_RIGHT:
                czlowiek.ustawKierunek(KierunekKwadrat.PRAWO);
                break;
            case KeyEvent.VK_DOWN:
                czlowiek.ustawKierunek(KierunekKwadrat.DOL);
                break;
            case KeyEvent.VK_LEFT:
                czlowiek.ustawKierunek(KierunekKwadrat.LEWO);
                break;
            case KeyEvent.VK_C:
                czlowiek.aktywacjaZdolnosci();
                rysuj = true;
                break;
        }
    }

    public void zaiinicjujNoweOrganizmy() {
        while (!noweOrganizmy.isEmpty())
            organizmy.add(noweOrganizmy.removeFirst());
    }

    public boolean czyRysowac() {
        if (rysuj) {
            rysuj = false;
            return true;
        }
        return false;
    }

    public void zapiszSwiat() {
        DaneZapisu dane = new DaneZapisu();
        dane.organizmy = new LinkedList<String>();
        dane.szerokoscPlanszy = plansza.wezSzerokosc();
        dane.wysokoscPlanszy = plansza.wezWysokosc();
        dane.tura = tura;
        for (Organizm organizm : organizmy) {
            dane.organizmy.add(organizm.toString());
        }
        try {
            KontrolaZapisu.zapisz(dane, "ZapisGry");
            wszystkieWydarzenia.add(wezWydarzenia().zapisanoSwiat());
        } catch (Exception e) {
            System.out.println("Nie można zapisać pliku: " + e.getMessage());
        }
    }

    public void wczytajSwiat() {
        try {
            DaneZapisu dane = (DaneZapisu) KontrolaZapisu.wczytaj("ZapisGry");
            int szerokosc = dane.szerokoscPlanszy;
            int wysokosc = dane.wysokoscPlanszy;
            this.poczatkowyStan(szerokosc, wysokosc);
            this.tura = dane.tura;
            for (String organizm : dane.organizmy) {
                wczytajOrganizm(organizm);
            }
            this.stworzSwiat(szerokosc, wysokosc, rodzajPlanszy.KWADRATOWA, false);
        } catch (Exception e) {
            System.out.println("Nie można wczytać pliku: " + e.getMessage());
        }
    }

    private boolean wczytajOrganizm(String string) {
        int spacja = string.indexOf('[');

        String nazwaOrganizmu = string.substring(0, spacja);
        String wszystkieParametry = string.replaceAll("[^-?0-9]+", " ");
        String argumenty[] = wszystkieParametry.split(" ");

        int pozycjaX = Integer.parseInt(argumenty[1]);
        int pozycjaY = Integer.parseInt(argumenty[2]);

        int sila = Integer.parseInt(argumenty[3]);

        int pozostalyCzasZdolnosci = -1;
        int pozostalyCzasBlokady = -1;

        if (argumenty.length > 4) {
            pozostalyCzasZdolnosci = Integer.parseInt(argumenty[4]);
            pozostalyCzasBlokady = Integer.parseInt(argumenty[5]);
        }

        Organizm organizm = null;
        switch (nazwaOrganizmu) {
            case "Barszcz":
                organizm = new BarszczSosnowskiego(this, plansza.wezPozycje(pozycjaX, pozycjaY), new Point(pozycjaX, pozycjaY));
                wszystkieBarszcze.add(organizm);
                break;
            case "Guarana":
                organizm = new Guarana(this, plansza.wezPozycje(pozycjaX, pozycjaY), new Point(pozycjaX, pozycjaY));
                break;
            case "Mlecz":
                organizm = new Mlecz(this, plansza.wezPozycje(pozycjaX, pozycjaY), new Point(pozycjaX, pozycjaY));
                break;
            case "Trawa":
                organizm = new Trawa(this, plansza.wezPozycje(pozycjaX, pozycjaY), new Point(pozycjaX, pozycjaY));
                break;
            case "WilczeJagody":
                organizm = new WilczeJagody(this, plansza.wezPozycje(pozycjaX, pozycjaY), new Point(pozycjaX, pozycjaY));
                break;

            case "Antylopa":
                organizm = new Antylopa(this, plansza.wezPozycje(pozycjaX, pozycjaY), new Point(pozycjaX, pozycjaY));
                break;
            case "CyberOwca":
                organizm = new CyberOwca(this, plansza.wezPozycje(pozycjaX, pozycjaY), new Point(pozycjaX, pozycjaY));
                break;
            case "Czlowiek":
                organizm = new Czlowiek(this, plansza.wezPozycje(pozycjaX, pozycjaY), new Point(pozycjaX, pozycjaY));
                ((Czlowiek) organizm).ustawPozostalyCzasZdolnosci(pozostalyCzasZdolnosci);
                ((Czlowiek) organizm).ustawPozostalyCzasBlokady(pozostalyCzasBlokady);
                czlowiek = (Czlowiek) organizm;
                break;
            case "Lis":
                organizm = new Lis(this, plansza.wezPozycje(pozycjaX, pozycjaY), new Point(pozycjaX, pozycjaY));
                break;
            case "Owca":
                organizm = new Owca(this, plansza.wezPozycje(pozycjaX, pozycjaY), new Point(pozycjaX, pozycjaY));
                break;
            case "Wilk":
                organizm = new Wilk(this, plansza.wezPozycje(pozycjaX, pozycjaY), new Point(pozycjaX, pozycjaY));
                break;
            case "Zolw":
                organizm = new Zolw(this, plansza.wezPozycje(pozycjaX, pozycjaY), new Point(pozycjaX, pozycjaY));
                break;
            default:
                System.out.println("Nieznany organizm: " + nazwaOrganizmu);
                System.out.println("Line: " + string);
                return true;
        }
        organizm.zwiekszSile(sila - organizm.wezSile());
        organizm.wezPozycje().ustawOrganizm(organizm);
        organizmy.add(organizm);
        return true;
    }

    public int wezSzerokosc() {
        return plansza.wezSzerokosc();
    }

    public int wezWysokosc() {
        return plansza.wezWysokosc();
    }

    public Pozycja kliknietaPozycja(MouseEvent event) {
        int x = event.getX() - PanelGlowny.wezPozycjePaneluX();
        int y = event.getY() - PanelGlowny.wezPozycjePaneluY();

        if (x < 0 || x > plansza.wezSzerokosc() * PanelGlowny.wezRozmiarKomorki()
                || y < 0 || y > plansza.wezWysokosc() * PanelGlowny.wezRozmiarKomorki()) {
            return null;
        }

        int pozycjaX = x / PanelGlowny.wezRozmiarKomorki();
        int pozycjaY = y / PanelGlowny.wezRozmiarKomorki();

        int pozaPlanszaX = x % PanelGlowny.wezRozmiarKomorki();
        int pozaPlanszaY = y % PanelGlowny.wezRozmiarKomorki();
        if (pozaPlanszaX == 0 || pozaPlanszaY == 0)
            return null;

        wszystkieWydarzenia.add(wezWydarzenia().kliknietaPozycja(pozycjaX, pozycjaY));

        return plansza.wezPozycje(pozycjaX, pozycjaY);
    }


    public void dodajBarszcz(Organizm organizm) {
        wszystkieBarszcze.add(organizm);
    }

    public Organizm najblizszyBarszcz(double pozycjaX, double pozycjaY) {
        double minOdleglosc = DUZA_LICZBA;
        Organizm barszczSosnowskiego = null;
        for (Organizm organizm : wszystkieBarszcze) {
            double x = Math.pow((pozycjaX - organizm.wezPunkt().getX()), 2);
            double y = Math.pow((pozycjaY - organizm.wezPunkt().getY()), 2);
            double odleglosc = Math.sqrt(x + y);
            if (odleglosc < minOdleglosc) {
                barszczSosnowskiego = organizm;
                minOdleglosc = odleglosc;
            }
        }
        return barszczSosnowskiego;
    }
}
