/*
 * Autor: Mathias Kesting und Maren Clemens
 * Datum: 30. März 2020
 */
public class Scheibe extends Rechteck
{
    // Größe der Scheibe als Rangfolge (0 - Scheibenanzahl)
    private int groesse;

    public Scheibe(int groesse)
    {
        this.groesse = groesse;
        // Festlegen der wirklichen Größe berechnet durch den vorherigen Größenwert
        groesseAendern(groesse * 10 + 50, 20);
        farbeAendern("blau");
    }

    public int gibGroesse() {
        return groesse;
    }

}
