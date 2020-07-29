package pl.s180009.symulator.interfejs.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WprowadzDane extends JDialog implements ActionListener {

    private JLabel lWysokosc, lSzerokosc;
    private JTextField tWysokosc, tSzerokosc;
    private JButton rozpocznij, przerwij;
    private boolean poprawneDane;

    public WprowadzDane(JFrame wykonawca) {
        super(wykonawca, "Wprowadz dane świata", true);
        setSize(300, 200);
        setLayout(null);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        lWysokosc = new JLabel("Wysokość świata: ", JLabel.RIGHT);
        lSzerokosc = new JLabel("Szerokość świata: ", JLabel.RIGHT);
        lWysokosc.setBounds(0, 20, 170, 25);
        lSzerokosc.setBounds(0, 70, 170, 25);
        add(lWysokosc);
        add(lSzerokosc);

        tWysokosc = new JTextField();
        tSzerokosc = new JTextField();
        tWysokosc.setBounds(170, 20, 50, 25);
        tSzerokosc.setBounds(170, 70, 50, 25);
        add(tWysokosc);
        add(tSzerokosc);

        rozpocznij = new JButton("Rozpocznij");
        przerwij = new JButton("Przerwij");
        rozpocznij.setBounds(25, 120, 100, 25);
        przerwij.setBounds(150, 120, 100, 25);
        rozpocznij.addActionListener(this);
        przerwij.addActionListener(this);
        add(rozpocznij);
        add(przerwij);
    }

    public int wezSzerokosc() {
        return Integer.parseInt(tSzerokosc.getText());
    }

    public int wezWysokosc() {
        return Integer.parseInt(tWysokosc.getText());
    }

    public boolean czyPoprawneDane(){
        return poprawneDane;
    }

    public void ustawFokus(){
        tWysokosc.requestFocusInWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object objekt = e.getSource();

        if (objekt == rozpocznij) {
            poprawneDane = true;
        } else {
            poprawneDane=false;
        }

        setVisible(false);
    }
}
