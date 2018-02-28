package kaos.prosjekt;

import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameOfLife extends Canvas {

    private final int HØYDE = 600;
    private final int BREDDE = 800;
    private double rutebredde;

    private boolean[][] gen;
    private boolean[][] nesteGen;

    private int breddeStr, høydeStr;
    private boolean kjører;
    private TimerTask task;

    private GraphicsContext gc;

    public GameOfLife(int breddeStr, int høydeStr) {
        setHeight(HØYDE);
        setWidth(BREDDE);
        this.breddeStr = breddeStr;
        this.høydeStr = høydeStr;

        rutebredde = (double) HØYDE / høydeStr;

        gen = new boolean[breddeStr][høydeStr];
        nesteGen = new boolean[breddeStr][høydeStr];

        gc = this.getGraphicsContext2D();
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, BREDDE, HØYDE);
        gen = tømMatrise(gen);
        resetGOL();
        repaint();
        kjører = false;
    }

    void resetGOL() {
        gen = tømMatrise(gen);
        nesteGen = tømMatrise(nesteGen);
        nesteGen = tømMatrise(nesteGen);
        repaint();
    }

    private void repaint() {
        for (int rad = 1; rad < breddeStr - 1; rad++) {
            for (int kol = 1; kol < høydeStr - 1; kol++) {
                double x = rutebredde * rad;
                double y = rutebredde * kol;
                if (gen[rad][kol] == true) {
                    gc.setFill(Color.RED);
                } else {
                    gc.setFill(Color.WHITE);
                }
                gc.fillRect(x, y, rutebredde - 2, rutebredde - 2);
            }
        }
    }

    void fyllRute(double x, double y) {
        int rad = (int) (x / rutebredde);
        int kol = (int) (y / rutebredde);
        gen[rad][kol] = true;

        gc.setFill(Color.RED);
        gc.fillRect(rad * rutebredde, kol * rutebredde, rutebredde - 2, rutebredde - 2);
    }

    void nesteGen() {
        for (int rad = 1; rad < breddeStr - 1; rad++) {
            for (int kol = 1; kol < høydeStr - 1; kol++) {
                testCelle(rad, kol);
            }
        }
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
                nesteGen[rad][kol] = false;
            } else {
                nesteGen[rad][kol] = true;
            }
        } else {
            if (antNaboer == 3) {
                nesteGen[rad][kol] = true;
            }
        }
    }
        public void start() {
        task = new TimerTask() {
            @Override
            public void run() {
                nesteGen();
            }
        };
        new Timer().schedule(task, 0, 50);
    }

    public void stopp() {
        if (task == null) {
            return;
        }
        task.cancel();
    }

    void fyllRandom() {
        for (int rad = 0; rad < breddeStr; rad++) {
            for (int kol = 0; kol < høydeStr; kol++) {
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

    private boolean[][] tømMatrise(boolean[][] matrise) {
        boolean tomMatrise[][] = new boolean[matrise.length][matrise.length];
        for (int rad = 0; rad < matrise.length; rad++) {
            for (int kol = 0; kol < matrise.length; kol++) {
                tomMatrise[rad][kol] = false;
            }
        }
        return tomMatrise;
    }

}
