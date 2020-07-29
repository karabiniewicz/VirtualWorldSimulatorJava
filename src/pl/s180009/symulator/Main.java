package pl.s180009.symulator;

import pl.s180009.symulator.interfejs.menu.MenuGlowne;
import pl.s180009.symulator.swiat.Swiat;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        Swiat swiat = new Swiat();

        MenuGlowne menuGlowne = new MenuGlowne(swiat);
        menuGlowne.wysrodkuj();
        menuGlowne.setVisible(true);
    }
}
