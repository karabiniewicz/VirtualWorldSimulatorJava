package pl.s180009.symulator.organizmy.zwierzeta;

import pl.s180009.symulator.kierunek.KierunekKwadrat;
import pl.s180009.symulator.organizmy.Organizm;
import pl.s180009.symulator.organizmy.rosliny.BarszczSosnowskiego;
import pl.s180009.symulator.pozycja.Pozycja;
import pl.s180009.symulator.swiat.Swiat;

import java.awt.*;

public class CyberOwca extends Owca {
    private static final Color kolor = new Color(124, 179, 219);
    private static final int sila = 11;
    private static final int inicjatywa = 4;

    public CyberOwca(Swiat swiat, Pozycja pozycja, Point punkt) {
        this(swiat, pozycja, punkt, kolor, sila, inicjatywa);
    }

    public CyberOwca(Swiat swiat, Pozycja pozycja, Point punkt, Color kolor, int sila, int inicjatywa) {
        super(swiat, pozycja, punkt, kolor, sila, inicjatywa);
        this.zaladujZdjecie("zdjecia/CyberOwca.jpg");
    }

    @Override
    protected Pozycja wezDocelowaPozycje() {
        Organizm organizm = swiat.najblizszyBarszcz(this.wezPunkt().getX(), this.wezPunkt().getY());
        if (organizm == null)
            return super.wezDocelowaPozycje();

        int szansa = generuj.nextInt(2);
        if (szansa == 0) {
            if (organizm.wezPunkt().getX() < this.wezPunkt().getX())
                return this.wezPozycje().wezDanaPozycje(KierunekKwadrat.LEWO);
            else if (organizm.wezPunkt().getX() > this.wezPunkt().getX())
                return this.wezPozycje().wezDanaPozycje(KierunekKwadrat.PRAWO);
            else if (organizm.wezPunkt().getY() < this.wezPunkt().getY())
                return this.wezPozycje().wezDanaPozycje(KierunekKwadrat.GORA);
            else
                return this.wezPozycje().wezDanaPozycje(KierunekKwadrat.LEWO);
        } else {
            if (organizm.wezPunkt().getY() < this.wezPunkt().getY())
                return this.wezPozycje().wezDanaPozycje(KierunekKwadrat.GORA);
            else if (organizm.wezPunkt().getY() > this.wezPunkt().getY())
                return this.wezPozycje().wezDanaPozycje(KierunekKwadrat.DOL);
            else if (organizm.wezPunkt().getX() < this.wezPunkt().getX())
                return this.wezPozycje().wezDanaPozycje(KierunekKwadrat.LEWO);
            else
                return this.wezPozycje().wezDanaPozycje(KierunekKwadrat.PRAWO);
        }
    }

    @Override
    public boolean smierc(Organizm organizm) {
        if (organizm instanceof BarszczSosnowskiego)
            return false;
        else
            return super.smierc(organizm);
    }

    @Override
    public Organizm stworzDziecko() {
        return new CyberOwca(swiat, wezPozycje(), wezPunkt());
    }

    @Override
    protected boolean czyTenSamGatunek(Organizm organizm) {
        return organizm instanceof CyberOwca;
    }

    @Override
    public String wezNazwe() {
        return "CyberOwca";
    }
}
