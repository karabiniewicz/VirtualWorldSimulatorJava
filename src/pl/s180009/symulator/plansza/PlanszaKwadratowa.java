package pl.s180009.symulator.plansza;

import pl.s180009.symulator.kierunek.Kierunek;
import pl.s180009.symulator.kierunek.KierunekKwadrat;
import pl.s180009.symulator.pozycja.Pozycja;
import pl.s180009.symulator.pozycja.PozycjaKwadratowa;

import java.awt.*;

public class PlanszaKwadratowa extends Plansza {

    public PlanszaKwadratowa(int szerokosc, int wysokosc) {
        super(szerokosc, wysokosc);
        this.stworzPlansze();
    }

    @Override
    public void stworzPlansze() {
        plansza = new PozycjaKwadratowa[szerokosc][wysokosc];
        for (int i = 0; i < this.szerokosc; ++i) {
            for (int j = 0; j < this.wysokosc; ++j) {
                plansza[i][j] = new PozycjaKwadratowa(new Point(i, j), null);
            }
        }
        przypiszPozycjeBoczne();
    }

    private void przypiszPozycjeBoczne() {
        for (int i = 0; i < this.szerokosc; ++i) {
            for (int j = 0; j < this.wysokosc; ++j) {
                Kierunek kierunek = KierunekKwadrat.GORA;
                Pozycja pozycjeBoczne[] = new Pozycja[kierunek.wezIloscKierunkow()];

                while (true) {
                    pozycjeBoczne[kierunek.naLiczbe()] = wezPozycje(i + kierunek.wezX(), j + kierunek.wezY());
                    kierunek = kierunek.wezNastepnyKierunek();
                    if (kierunek == null)
                        break;
                }

                plansza[i][j].ustawPozycjeBoczne(pozycjeBoczne);
            }
        }
    }
}
