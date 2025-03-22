package com.example.harkkatyo;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * HarjoitustyoStage on tämä JavaFX ohjelman pääluokka, joka käynnistää ohjelman,
 * ja asettaa näkymään päävalikon.
 */
public class HarjoitustyoStage extends Application {
    /**
     * Start metodi määrittää päänätön ja sen ensimmäisen näkymän.
     * @param stage asettaa näkymäksi {@code MainMenuScene} luokan sisällön.
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Harjoitustyo");
        stage.setScene(new MainMenuScene(stage).getScene());
        stage.show();
    }

    /**
     * Sovelluksen päämetodi mikä käynnistää ohjelman.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}
