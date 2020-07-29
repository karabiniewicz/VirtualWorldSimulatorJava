package pl.s180009.symulator.organizmy.rosliny;

import pl.s180009.symulator.organizmy.Organizm;
import pl.s180009.symulator.pozycja.Pozycja;
import pl.s180009.symulator.swiat.Swiat;

import java.awt.*;

public abstract class RoslinaTrujaca extends Roslina {

    public RoslinaTrujaca(Swiat swiat, Pozycja pozycja, Point punkt, Color kolor, int sila) {
        super(swiat, pozycja, punkt, kolor, sila);
    }

    @Override
    public boolean kolizja(Organizm organizm) {
        if (!czyZyje())
            return true;

        if (this.wezSile() > organizm.wezSile()) {
            organizm.smierc(this);
            return false;
        } else {
            this.smierc(organizm);
            return true;
        }
    }
}
