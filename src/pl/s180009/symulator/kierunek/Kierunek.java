package pl.s180009.symulator.kierunek;

public interface Kierunek {
    public int naLiczbe();

    public int wezIloscKierunkow();

    public Kierunek wezNastepnyKierunek();

    public void reset();

    public int wezX();

    public int wezY();
}
