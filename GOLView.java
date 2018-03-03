package kaos.prosjekt;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class GOLView extends BorderPane {

    private Button reset, fyll, neste, start, stopp;
    private Slider hSlider, sSlider;
    private HBox meny;
    GameOfLife gol;

    public GOLView() {
        tegnCTab();
    }

    private void tegnCTab() {
        this.setStyle("-fx-background-color: #737373;");
        reset = new Button("Reset");
        reset.setOnAction(e -> gol.resetGOL((int)sSlider.getValue()));
        fyll = new Button("Fyll");
        fyll.setOnAction(e -> gol.fyllRandom());
        neste = new Button("Neste steg");
        neste.setOnAction(e -> gol.nesteGen());
        start = new Button("Start");
        start.setOnAction(e -> gol.start(hSlider.getValue()));
        stopp = new Button("Stopp");
        stopp.setOnAction(e -> gol.stopp());

        hSlider = new Slider(0, 200, 100);
        sSlider = new Slider(10, 200, 50);
        sSlider.setShowTickLabels(true);

        meny = new HBox(10);
        meny.getChildren().addAll(reset, fyll, neste, start, stopp, new Label("Hastighet"), hSlider, new Label("Størrelse"), sSlider);
        meny.setPadding(new Insets(10, 0, 10, 10));
        this.setBottom(meny);
        tegnGOL();
    }

    public void tegnGOL() {
        gol = new GameOfLife();
        this.setCenter(gol);

        gol.setOnMouseDragged(e -> gol.fyllRute(e.getX(), e.getY()));
        gol.setOnMouseClicked(e -> gol.fyllRute(e.getX(), e.getY()));
    }

}
