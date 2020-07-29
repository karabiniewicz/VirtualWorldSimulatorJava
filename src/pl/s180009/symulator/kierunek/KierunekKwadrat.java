package pl.s180009.symulator.kierunek;

public enum KierunekKwadrat implements Kierunek {
    GORA(0, -1),
    PRAWO(1, 0),
    DOL(0, 1),
    LEWO(-1, 0);

    private int x, y;
    final private int iloscKierunkow = 4;

    KierunekKwadrat(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int naLiczbe() {
        if (x == 0 && y == -1)
            return 0;
        else if (x == 1 && y == 0)
            return 1;
        else if (x == 0 && y == 1)
            return 2;
        else if (x == -1 && y == 0)
            return 3;
        else return -1;
    }

    @Override
    public int wezIloscKierunkow() {
        return iloscKierunkow;
    }

    @Override
    public int wezX() {
        return x;
    }

    @Override
    public int wezY() {
        return y;
    }

    @Override
    public Kierunek wezNastepnyKierunek() {
        switch (naLiczbe()) {
            case 0:
                return PRAWO;
            case 1:
                return DOL;
            case 2:
                return LEWO;
            case 3:
            default:
                return null;
        }
    }

    @Override
    public void reset() {
        x = 0;
        y = -1;
    }
}
