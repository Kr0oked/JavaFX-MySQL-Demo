package de.hsweingarten.dapro;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.hsweingarten.dapro.application.guice.CarRentalManagerModule;
import de.hsweingarten.dapro.view.overview.CarRentalOverviewView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Starts the Car Rental Application
 */
public class Main extends Application {

    /**
     * Entry Point of the Application
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the JavaFX Application
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        Injector injector = Guice.createInjector(new CarRentalManagerModule());
        CarRentalOverviewView eventOverview = injector.getInstance(CarRentalOverviewView.class);

        Pane eventOverViewPane = eventOverview.getView();

        Scene scene = new Scene(eventOverViewPane);
        scene.getStylesheets().add(Main.class.getResource("/CarRental.css").toExternalForm());

        eventOverViewPane.prefWidthProperty().bind(scene.widthProperty());
        eventOverViewPane.prefHeightProperty().bind(scene.heightProperty());

        primaryStage.setTitle("Car Rental");
        primaryStage.setScene(scene);
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.show();
    }
}
