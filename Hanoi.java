/*
 * Autor: Mathias Kesting und Maren Clemens
 * Datum: 30. März 2020
 */
import java.awt.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Hanoi {

    public Hanoi() {
        Scanner s = new Scanner(System.in);
        boolean ready = false;
        while(!ready) {
            System.out.println("Wie viele Scheiben sollen bewegt werden? ");
            try {
                int scheiben = s.nextInt();
                if(scheiben > 0 && scheiben < 11) {
                    ready = true;
                    vorbereiten(scheiben);
                } else {
                    System.out.println("Die Zahl muss im Raum von 1 und 10 liegen!");
                }
            } catch(InputMismatchException e) {
                System.out.println("Das ist leider keine Zahl!");
            }
        }
    }

    private void vorbereiten(int scheiben)
    {
        Turm A = new Turm(scheiben);
        Turm B = new Turm(0);
        Turm C = new Turm(0);
        leeren();
        hanoi(A, B, C, scheiben);
    }
    // Start: Der Turm von dem die Scheibe kommt
    // Aux: Der Turm, mit dem nichts passiert
    // Ziel: Der Turm auf die die Scheibe drauf soll
    private void hanoi(Turm start, Turm aux, Turm ziel, int scheiben) {

        if (scheiben == 1) {
            // Temporäres Scheibenobjekt deklarieren
            Scheibe zuBewegen;
            // Entfernte Scheibe vom Startturm speichern
            zuBewegen = start.scheibeEntfernen();
            int startX = zuBewegen.gibX();
            int startY = zuBewegen.gibY();
            // Scheibe zum Zielturm hinzufügen
            ziel.scheibeHinzufügen(zuBewegen, false);
            // Scheibenänderung animieren
            animation(zuBewegen.gibGroesse(), startX, startY, zuBewegen.gibX(), zuBewegen.gibY());
            ziel.scheibenErneuern();
        } else {
            hanoi(start, ziel, aux, scheiben - 1);
            hanoi(start, aux, ziel, 1);
            hanoi(aux, start, ziel, scheiben - 1);
        }
    }

    // Bewegung der Scheiben
    private void animation(int groesse, int startX, int startY, int zielX, int zielY) {
        Scheibe s = new Scheibe(groesse);
        s.farbeAendern("blau");
        s.positionAendern(startX, startY);
        s.sichtbarMachen();
        while (s.gibY() > 30) {
            s.vertikalBewegen(-5);
        }
        if(s.gibX() < zielX) {
            while (s.gibX() < zielX) {
                s.horizontalBewegen(5);
            }
        } else if(s.gibX() > zielX) {
            while (s.gibX() > zielX) {
                s.horizontalBewegen(-5);
            }
        }

        while (s.gibY() < zielY) {
            s.vertikalBewegen(5);
        }
        Leinwand.gibLeinwand().entferne(s);
    }
    
    public void leeren()
    {
        Leinwand.gibLeinwand().loeschen();
    }

}
