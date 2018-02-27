package kaos.prosjekt;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class KaosProsjektGUI extends Application {

    TabPane tabPane;
    Tab mandelbrotTab, conwayTab;
    BorderPane tabRamme;
    Mandelbrot mandelbrot;
    ToggleGroup knappGruppe;
    RadioButton zoomInn, zoomUt, panorer;
    Label koordinater;

    @Override
    public void start(Stage primaryStage) {

        tabPane = new TabPane();
        mandelbrotTab = new Tab();
        mandelbrotTab.setText("Mandelbrot");
        mandelbrotTab.setClosable(false);
        tabPane.setOnMouseClicked(e -> tegnTab());
        conwayTab = new Tab();
        conwayTab.setClosable(false);
        conwayTab.setText("Conway");

        tabPane.getTabs().addAll(mandelbrotTab, conwayTab);
        tabRamme = new BorderPane();
        VBox root = new VBox();
        root.getChildren().addAll(tabPane, tabRamme);

        Scene scene = new Scene(root, 800, 600);
        tegnTab();
        primaryStage.setTitle("Kaos-prosjekt");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void tegnTab() {
        if (mandelbrotTab.isSelected()) {
            tegnMTab();
        }
        if (conwayTab.isSelected()) {
        }
    }

    private void tegnMTab() {
        knappGruppe = new ToggleGroup();
        zoomInn = new RadioButton();
        zoomUt = new RadioButton();
        panorer = new RadioButton();
        zoomInn.setToggleGroup(knappGruppe);
        zoomUt.setToggleGroup(knappGruppe);
        panorer.setToggleGroup(knappGruppe);
        
        koordinater = new Label();

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 0, 10, 10));
        grid.addRow(0, new Label("Zoom inn"), zoomInn);
        grid.addRow(1, new Label("Zoom ut"), zoomUt);
        grid.addRow(2, new Label("Panorer"), panorer);

        Button tegn = new Button("Tegn mandelbrot");
        tegn.setOnAction(e -> tegnMandelbrot());

        grid.add(tegn, 0, 3);
        grid.add(koordinater, 0, 5);
        tabRamme.setLeft(grid);
        tegnMandelbrot();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void tegnMandelbrot() {
        mandelbrot = new Mandelbrot(250, 250, 3);
        mandelbrot.tegnMandel();
        mandelbrot.setOnMouseClicked(e -> mandelbrotKlikk(e.getX(), e.getY()));
        mandelbrot.setOnMouseMoved(e -> koordinater.setText("x : " + e.getX() + " y : " + e.getY()));
        tabRamme.setCenter(mandelbrot);
    }
        private void mandelbrotKlikk(double x, double y) {
        if (zoomInn.isSelected()) {
            mandelbrot.zoomInn(x, y);
        }
        if (zoomUt.isSelected()) {
            mandelbrot.zoomUt(x, y);
        }
        if (panorer.isSelected()) {
            mandelbrot.panorer(x, y);
        }
    }

}
