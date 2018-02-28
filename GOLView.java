package kaos.prosjekt;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class GOLView extends BorderPane {

    private Button reset, fyll, neste, start, stopp;
    private HBox meny;
    GameOfLife gol;
    
    public GOLView() {
        tegnCTab();
    }
    
    private void tegnCTab() {
        reset = new Button("Reset");
        reset.setOnAction(e -> gol.resetGOL());
        fyll = new Button("Fyll");
        fyll.setOnAction(e -> gol.fyllRandom());
        neste = new Button("Neste steg");
        neste.setOnAction(e -> gol.nesteGen());
        start = new Button("Start");
        start.setOnAction(e -> gol.start());
        stopp = new Button("Stopp");
        stopp.setOnAction(e -> gol.stopp());

        meny = new HBox(10);
        meny.getChildren().addAll(reset, fyll, neste, start, stopp);
        meny.setPadding(new Insets(10, 0, 10, 10));
        this.setBottom(meny);
        tegnGOL(100,80);
    }

    public void tegnGOL(int bredde, int høyde) {
        gol = new GameOfLife(bredde, høyde);
        this.setCenter(gol);
        
        gol.setOnMouseDragged(e -> gol.fyllRute(e.getX(), e.getY()));
        gol.setOnMouseClicked(e -> gol.fyllRute(e.getX(), e.getY()));
    }
    
}
