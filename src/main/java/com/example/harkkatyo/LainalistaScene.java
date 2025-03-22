package com.example.harkkatyo;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * LainalistaScene edustaa javaFX näkymää, missä käyttäjä voi tarkastella {@code lainat.dat} tiedostoon tallennettuja
 * {@code LainaScene } luokan tekemiä olioita.
 * Käyttäjä voi tarkastella lainoja ja niiden parametreja, poistaa kaikki tai palata päävalikkoon.
 */
public class LainalistaScene {
    /**
     * Scene olio tallenttujen lainojen näkymälle.
     */
    private Scene lainalistaScene;

    /**
     * LainalistaScene luo näkymän tallenttujen lainojen tarkasteluun tai poistamiseen.
     * @param stage Stage olio tallentuille lainoille
     */
    public LainalistaScene(Stage stage) {
        VBox lainalistaLayout = new VBox(10);
        ListView<String> lainalistview = new ListView<>();

        Button tyhjennaLainatBtn = new Button("Tyhjennä kaikki lainat");
        tyhjennaLainatBtn.setOnAction(e -> {
            lainalistview.getItems().clear();
            Tallennus.poistaLainat();
        });

        Button takaisin3 = new Button("Takaisin");
        takaisin3.setOnAction(e -> stage.setScene(new MainMenuScene(stage).getScene()));

        lainalistview.getItems().clear();
        for (Lainalaskuri laina : Tallennus.luelainat()) {
            lainalistview.getItems().add("Laina " + laina.getLainaMaara() + " €, Korko: " + laina.getKorkoProsentti() + " %, Kuukausierä: " + laina.kuukausiErat() + " €," + " Laina aika, "+ laina.getLainaAika() + " Vuotta," + " Kokonaiskustannus: " + laina.kokonaiskustannus()+ " €");
        }

        lainalistaLayout.getChildren().addAll(new Label("Tallennetut lainat"), lainalistview, tyhjennaLainatBtn, takaisin3);
        lainalistaScene = new Scene(lainalistaLayout, 600, 600);
    }

    /**
     * Palauttaa tallennettujen lainojen näkymän.
     * @return Scene olio tallennetuille lainoille.
     */
    public Scene getScene() {
        return lainalistaScene;
    }
}

