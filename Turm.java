/*
 * Autor: Mathias Kesting und Maren Clemens
 * Datum: 30. März 2020
 */
import java.util.Stack;

public class Turm extends Rechteck
{

    private Stack<Scheibe> stack = new Stack<>();

    public Turm(int scheiben)
    {
        farbeAendern("schwarz");
        groesseAendern(150, 20);

        // grundlegende Anfangsposition
        int xPos = 50;
        // Berechnung der Anfangsposition
        for(Object o : Leinwand.gibLeinwand().gibFiguren()) {
            if(o instanceof Turm) {
                xPos += 200;
            }
        }
        // Setzen der Anfangsposition
        positionAendern(xPos, 300);
        // Scheiben hinzufügen
        for(int i = 0; i < scheiben; i++) {
            scheibeHinzufügen(new Scheibe(scheiben - i - 1), true);
        }
        // Anzeigen des neuen Turmes
        sichtbarMachen();
    }

    public void scheibeHinzufügen(Scheibe s, boolean visible) {
        if(stack.size() == 0 || stack.peek().gibGroesse() > s.gibGroesse()) {
            // Hinzufügen der Scheibe zum Stack
            stack.push(s);
            // Wenn die Scheibe angezeigt werden soll, wird der Turm neu geladen, sonst ohne die neue Scheibe
            if(visible) scheibenErneuern();
            else scheibenErneuern(s.gibGroesse());
        } else {
            System.out.println("Die Scheibe " + stack.peek().gibGroesse() + " ist kleiner als " + s.gibGroesse());
        }
    }

    public Scheibe scheibeEntfernen() {
        Scheibe r = stack.pop();
        scheibenErneuern();
        return r;
    }

    public void scheibenErneuern() {
        for(int i = 0; i < stack.size(); i++) {
                stack.get(i).positionAendern(gibX() + ((gibBreite() - stack.get(i).gibBreite()) / 2), gibY() - (30 + i * 30));
                stack.get(i).sichtbarMachen();
        }
    }

    public void scheibenErneuern(int exception) {
        for(int i = 0; i < stack.size(); i++) {
            if(stack.get(i).gibGroesse() == exception) {
                stack.get(i).unsichtbarMachen();
                stack.get(i).positionAendern(gibX() + ((gibBreite() - stack.get(i).gibBreite()) / 2), gibY() - (30 + i * 30));
            } else {
                stack.get(i).positionAendern(gibX() + ((gibBreite() - stack.get(i).gibBreite()) / 2), gibY() - (30 + i * 30));
                stack.get(i).sichtbarMachen();
            }
        }
    }


    public Scheibe gibScheibe(int groesse) {
        return stack.get(groesse);
    }

}
