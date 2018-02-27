package kaos.prosjekt;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Mandelbrot extends Canvas {

    final int MAX_ITERERING = 300;
    final int HØYDE = 500;
    final int BREDDE = 500;

    final Color svart = new Color(0, 0, 0, 1);
    
    GraphicsContext gc;
    double midtX, midtY, størrelse, pl;

    public Mandelbrot(double midtX, double midtY, double størrelse) {
        setHeight(HØYDE);
        setWidth(BREDDE);
        this.pl = HØYDE / størrelse; // Antall piksler for hver lengde

        this.midtX = midtX;
        this.midtY = midtX;
        this.størrelse = størrelse;

        gc = this.getGraphicsContext2D();

    }

    public void tegnMandel() {
        Color farge = svart;
        for (int rad = 0; rad < HØYDE; rad++) {
            for (int kolonne = 0; kolonne < BREDDE; kolonne++) {
                double imag = (rad - midtY) / pl;
                double real = (kolonne - midtX) / pl;
                Complex c = new Complex(real, imag);

                int antIter = tellIter(c);

                if (antIter < MAX_ITERERING) {
                    double b = (double) antIter / 300;
                    farge = new Color(0, b, b, 1);
                }
                gc.setFill(farge);
                gc.fillRect(rad, kolonne, 1, 1);
                farge = svart;
            }
        }
    }

    void zoomInn(double x, double y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void zoomUt(double x, double y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void panorer(double x, double y) {
        this.midtX = midtX - (BREDDE / 2) + x;
        this.midtY = midtY - (HØYDE / 2) + y;
        tegnMandel();
    }

    private int tellIter(Complex c) {
        Complex z = c;
        int i = 0;
        for (; i < MAX_ITERERING; i++) {
            z = z.gange(z).pluss(c);
            if (z.getReal() > 2) {
                break;
            }
        }
        return i;
    }
}
