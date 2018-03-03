package kaos.prosjekt;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class GameOfLife extends Canvas {

    private final int HØYDE = 700;
    private final int BREDDE = 800;
    private double rutebredde;

    private boolean[][] gen, nesteGen;

    private int str;
    private int antGen;

    private Timeline loop;

    private GraphicsContext gc;

    public GameOfLife() {
        setHeight(HØYDE);
        setWidth(BREDDE);
        this.str = 100;
        this.antGen = 0;

        gen = new boolean[str][str];
        nesteGen = new boolean[str][str];

        gc = this.getGraphicsContext2D();

        loop = new Timeline(new KeyFrame(Duration.millis(50), e -> nesteGen()));
        resetGOL(str);
    }

    void nesteGen() {
        for (int rad = 1; rad < str - 1; rad++) {
            for (int kol = 1; kol < str - 1; kol++) {
                testCelle(rad, kol);
            }
        }
        antGen++;
        gen = nesteGen;
        nesteGen = tømMatrise(nesteGen);
        repaint();
    }

    private void testCelle(int rad, int kol) {
        int antNaboer = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (gen[rad + i][kol + j] == true) {
                    antNaboer++;
                }
            }
        }
        if (gen[rad][kol] == true) {
            antNaboer--;
            if (antNaboer < 2 || antNaboer > 3) {
                nesteGen[rad][kol] = false; // Dør
            } else {
                nesteGen[rad][kol] = true; // Lever
            }
        } else {
            if (antNaboer == 3) {
                nesteGen[rad][kol] = true; // Blir født
            }
        }
    }

    private void repaint() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, BREDDE, HØYDE);
        for (int rad = 1; rad < str - 1; rad++) {
            for (int kol = 1; kol < str - 1; kol++) {
                double x = rutebredde * rad;
                double y = rutebredde * kol;
                if (gen[rad][kol] == true) {
                    gc.setFill(Color.GREENYELLOW);
                } else {
                    gc.setFill(Color.BLACK);
                }
                gc.fillRect(x, y, rutebredde+1, rutebredde+1);
            }
        }
    }

    public void start(double h) {
        double hastighet = (1 / h) * 10000;
        loop.stop();
        loop = new Timeline(new KeyFrame(Duration.millis(hastighet), e -> nesteGen()));
        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
    }

    public void stopp() {
        loop.stop();
    }

    void fyllRute(double x, double y) {
        int rad = (int) (x / rutebredde);
        int kol = (int) (y / rutebredde);

        gen[rad][kol] = true;
        gc.setFill(Color.GREENYELLOW);
        gc.fillRect(rad * rutebredde, kol * rutebredde, rutebredde, rutebredde);
    }

    void fyllRandom() {
        for (int rad = 0; rad < str; rad++) {
            for (int kol = 0; kol < str; kol++) {
                double random = Math.random() * 10 / 5;
                int rundet = (int) random;
                if (rundet == 0) {
                    gen[rad][kol] = true;
                } else {
                    gen[rad][kol] = false;
                }
            }
        }
        repaint();
    }

    void resetGOL(int s) {
        str = s;
        gen = new boolean[str][str];
        nesteGen = new boolean[str][str];
        rutebredde = (double) HØYDE / str;
        gen = tømMatrise(gen);
        nesteGen = tømMatrise(nesteGen);
        repaint();
    }

    private boolean[][] tømMatrise(boolean[][] matrise) {
        boolean tomMatrise[][] = new boolean[matrise.length][matrise.length];
        for (int rad = 0; rad < matrise.length; rad++) {
            for (int kol = 0; kol < matrise.length; kol++) {
                tomMatrise[rad][kol] = false;
            }
        }
        return tomMatrise;
    }

    public int getAntGen() {
        return antGen;
    }

}
