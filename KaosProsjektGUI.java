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
    Label koordinater;
    RadioButton zoomInn, zoomUt, panorer;

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

        tabPane.getTabs().addAll(conwayTab, mandelbrotTab);
        tabRamme = new BorderPane();
        VBox root = new VBox();
        root.getChildren().addAll(tabPane, tabRamme);

        Scene scene = new Scene(root, 800, 750);
        tegnTab();
        primaryStage.setTitle("Kaos-prosjekt");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void tegnTab() {
        if (mandelbrotTab.isSelected()) {
            MandelbrotView mbp = new MandelbrotView();
            tabRamme.setTop(mbp);
        }
        if (conwayTab.isSelected()) {
            GOLView gv = new GOLView();
            tabRamme.setTop(gv);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
