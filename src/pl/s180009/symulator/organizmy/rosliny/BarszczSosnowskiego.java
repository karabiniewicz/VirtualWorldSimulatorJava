package pl.s180009.symulator.organizmy.rosliny;

import pl.s180009.symulator.organizmy.Organizm;
import pl.s180009.symulator.organizmy.zwierzeta.Zwierze;
import pl.s180009.symulator.pozycja.Pozycja;
import pl.s180009.symulator.swiat.Swiat;

import java.awt.*;

public class BarszczSosnowskiego extends RoslinaTrujaca {
    private static final int sila = 10;
    private static final Color kolor = new Color(213, 255, 88);

    public BarszczSosnowskiego(Swiat swiat, Pozycja pozycja, Point punkt) {
        super(swiat, pozycja, punkt, kolor, sila);
        this.zaladujZdjecie("zdjecia/BarszczSosnowskiego.jpg");
    }

    @Override
    public Organizm stworzDziecko() {
        return new BarszczSosnowskiego(swiat, this.wezPozycje(), this.wezPunkt());
    }

    @Override
    public void akcja() {
        if (!czyZyje())
            return;

        Pozycja pozycjeBoczne[] = this.wezPozycje().wezZajetePozycjeBoczne();
        for (Pozycja pozycja : pozycjeBoczne) {
            Organizm organizm = pozycja.wezOrganizm();
            if (organizm instanceof Zwierze)
                organizm.smierc(this);
        }
    }

    @Override
    public String wezNazwe() {
        return "Barszcz";
    }
}