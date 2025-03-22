package com.example.harkkatyo;


import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * MainMenuScene luokka edustaa ohjelman javaFX päänäkymää, josa käyttäjä voi valita mitä ohjelamalla tekee vai tekeekö mitään ja poistuu ohjelmasta.
 * Näkymästä käyttäjä voi valita lainojen ja säästöjen laskemisen, laskettujen lainojen ja säästöjen katselun tai sulkea sen.
 */
public class MainMenuScene {
    /**
     * Scene olio joka tekee päävalikon näkymän.
     */
    private Scene mainMenuScene;

    /**
     *
     * @param stage Luo päänäkymän ja määrittelee sen toiminnot
     */
    public MainMenuScene(Stage stage) {
        VBox mainMenuLayout = new VBox(10);
        Button lainabtn = new Button("Laske laina");
        Button saastobtn = new Button("Laske säästot");
        Button naytalainat = new Button("Näytä tallennetut lainat");
        Button naytasaastot = new Button("Näytä tallennetut säästöt");
        Button poistu = new Button("Lopeta ohjelman käyttö");

        lainabtn.setOnAction(e -> stage.setScene(new LainaScene(stage).getScene()));
        saastobtn.setOnAction(e -> stage.setScene(new SaastoScene(stage).getScene()));
        naytalainat.setOnAction(e -> stage.setScene(new LainalistaScene(stage).getScene()));
        naytasaastot.setOnAction(e -> stage.setScene(new SaastolistaScene(stage).getScene()));
        poistu.setOnAction(e -> Platform.exit());

        mainMenuLayout.getChildren().addAll(new Label("Valitse toiminto:"), lainabtn, saastobtn, naytalainat, naytasaastot, poistu);
        mainMenuScene = new Scene(mainMenuLayout, 400, 400);
    }

    /**
     * Palauttaa päänäkymän Scene olion.
     * @return Scene olio, joka edustaa päänäkymää.
     */
    public Scene getScene() {
        return mainMenuScene;
    }
}
