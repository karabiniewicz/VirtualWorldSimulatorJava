package pl.s180009.symulator.zapis;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class KontrolaZapisu {
    public static void zapisz(Serializable dane, String nazwaPliku) throws Exception {
        try (ObjectOutputStream zapis = new ObjectOutputStream(Files.newOutputStream(Paths.get(nazwaPliku)))) {
            zapis.writeObject(dane);
        }
    }

    public static Object wczytaj(String nazwaPliku) throws Exception {
        try (ObjectInputStream wejscie = new ObjectInputStream(Files.newInputStream(Paths.get(nazwaPliku)))) {
            return wejscie.readObject();
        }
    }
}