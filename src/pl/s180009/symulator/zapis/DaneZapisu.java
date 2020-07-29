package pl.s180009.symulator.zapis;

import java.util.List;

public class DaneZapisu implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    public int wysokoscPlanszy;
    public int szerokoscPlanszy;
    public int tura;
    public List<String> organizmy;
}