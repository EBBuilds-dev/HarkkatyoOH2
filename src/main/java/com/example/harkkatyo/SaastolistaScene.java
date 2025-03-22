package com.example.harkkatyo;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Luodaan Saastolista edustaa javaFX n'kymää, jossa näytetään {@code saastot.dat} tiedostoon {@code SaastoScene} luokalla tallennetut oliot
 * Käyttäjä voi tarkastella ja poistaa tallennettuja säästöjä toki jos päättää poistaa silloin poistetaan kaikki kerralla.
 */
public class SaastolistaScene {
    /**
     * Scene olio tallennettujen säästöjen näkymälle.
     */
    private Scene saastolistaScene;

    /**
     * Luo näkymän tallennettujen säästöjen tarkastelulle.
     * @param stage Stage olio, johon näkymä lisätään.
     */
    public SaastolistaScene(Stage stage) {
        VBox saastolistaLayout = new VBox(10);
        ListView<String> saastolistview = new ListView<>();
        Button tyhjennaSaastotBtn = new Button("Tyhjennä kaikki säästöt");

        tyhjennaSaastotBtn.setOnAction(e -> {
            saastolistview.getItems().clear();
            Tallennus.poistaSaastot();
        });

        Button takaisin4 = new Button("Takaisin");
        takaisin4.setOnAction(e -> stage.setScene(new MainMenuScene(stage).getScene()));

        saastolistview.getItems().clear();
        for (SaastoLaskuri saasto : Tallennus.luesaastot()) {
            saastolistview.getItems().add("Alkusumma " + saasto.getAlkusumma() + " €, Kuukausittain säästettävä summa " + saasto.getKuukausiSumma() + " €, Vuosikorko " + saasto.getVuosikorko() + " %," + " Vuodet " + saasto.getVuodet()+" Vuotta," +  " Kokonaissumma " + saasto.LaskeSaastoArvo() + " €");
        }

        saastolistaLayout.getChildren().addAll(new Label("Tallennetut säästöt"), saastolistview, tyhjennaSaastotBtn, takaisin4);
        saastolistaScene = new Scene(saastolistaLayout, 680, 600);
    }

    /**
     * Palauttaa tallennettujen säästöjen näkymän.
     * @return Tallennettujen säästöjen Scene olion.
     */
    public Scene getScene() {
        return saastolistaScene;
    }
}

