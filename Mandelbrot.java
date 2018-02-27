package kaos.prosjekt;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Mandelbrot extends Canvas {

    final int MAX_ITERERING = 200;
    final int HØYDE = 700;
    final int BREDDE = 700;

    final Color svart = new Color(0, 0, 0, 1);
    
    GraphicsContext gc;
    double midtX, midtY, størrelse, pl;

    public Mandelbrot() {
        setHeight(HØYDE);
        setWidth(BREDDE);
        this.midtX = BREDDE/2;
        this.midtY = HØYDE/2;
        this.størrelse = 3;

        gc = this.getGraphicsContext2D();

    }

    public void tegnMandel() {
        this.pl = HØYDE / størrelse; // Antall piksler for hver lengde
        Color farge = svart;
        for (int rad = 0; rad < HØYDE; rad++) {
            double real = (rad - midtX) / pl;
            for (int kolonne = 0; kolonne < BREDDE; kolonne++) {
                double imag = (kolonne - midtY) / pl;
                Complex c = new Complex(real, imag);

                int antIter = tellIter(c);

                if (antIter < MAX_ITERERING) {
                    double b = (double) antIter / MAX_ITERERING;
                    farge = new Color(b, 0, b, 1);
                }
                gc.setFill(farge);
                gc.fillRect(rad, kolonne, 1, 1);
                farge = svart;
            }
        }
    }

    private int tellIter(Complex c) {
        Complex z = c;
        int i = 0;
        for (; i < MAX_ITERERING; i++) {
            z = z.gange(z).pluss(c);
            if (z.abs() > 4) { 
                break;
            }
        }
        return i;
    }
    
    void zoomInn(double x, double y) {
        midtX = midtX + (BREDDE / 2) - x;
        midtY = midtY + (HØYDE / 2) - y;
        this.størrelse = størrelse*0.8;
        tegnMandel();
    }

    void zoomUt(double x, double y) {
        this.størrelse = størrelse*1.2;
        tegnMandel();
    }

    void panorer(double x, double y) {
        midtX = midtX + (BREDDE / 2) - x;
        midtY = midtY + (HØYDE / 2) - y;
        tegnMandel();
    }
}