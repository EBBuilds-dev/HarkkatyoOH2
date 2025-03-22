package com.example.harkkatyo;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * SaastoScene luokka edustaa javaFX näkymää, jossa käyttäjä voi laskea säästön tulevan arvon itse antamiensa arvojen perusteella
 * Näkymä mahdollistaa alkusumman, kuukausittaisen summa, vuosikoron ja säästöajan vuosina asettamisen.
 * lasketun säästön voi myös tallentaa {@code Tallennus} luokan metodejen avulla.
 * näkymä käyttää {@code SaastoLaskuri} luokassa rakennettuja metodeja.
 */
public class SaastoScene {
    /**
     * Scene olio, joka edustaa säästölaskurin näkymää
     */
    private Scene saastoScene;
    /**
     * Lista johon tallennetaan {@code SaastoLaskuri} luokan SaastoLaskuri oliot.
     */
    private ArrayList<SaastoLaskuri> saastot;

    /**
     *
     * @param stage luo SaastoScene näkymän ja määrittelee sen toiminnot.
     */
    public SaastoScene(Stage stage) {
        saastot = Tallennus.luesaastot();

        VBox saastoLayout = new VBox(10);
        TextField alkusummafield = new TextField();
        alkusummafield.setPromptText("Alkusumma €");

        TextField kuukausimmasummafield = new TextField();
        kuukausimmasummafield.setPromptText("Kuukausittain säästö €");

        TextField vuosikorkofield = new TextField();
        vuosikorkofield.setPromptText("Vuosikorko %");

        TextField vuodetfield = new TextField();
        vuodetfield.setPromptText("Vuodet");

        Label saastotulos = new Label();
        Label saastoVirhe = new Label();
        Button laskesaastobtn = new Button("Laske säästöjen tuleva arvo €");
        Button tallennaSaastot = new Button("Tallenna Säästö");

        laskesaastobtn.setOnAction(e -> {
            try {
                BigDecimal Alkusumma = new BigDecimal(alkusummafield.getText());
                BigDecimal KuukausiSumma = new BigDecimal(kuukausimmasummafield.getText());
                BigDecimal Vuosikorko = new BigDecimal(vuosikorkofield.getText());
                int Vuodet = Integer.parseInt(vuodetfield.getText());

                SaastoLaskuri laskuri = new SaastoLaskuri(Alkusumma, KuukausiSumma, Vuodet, Vuosikorko);

                saastot.add(laskuri);
                saastotulos.setText("Säästöarvo: " + laskuri.LaskeSaastoArvo() + "€");
                saastoVirhe.setText("");

            } catch (NumberFormatException ex) {
                saastoVirhe.setText("Virhe: Vain positiivisia numeroita.");
                saastotulos.setText("");
            }catch (IllegalArgumentException ex) {
                saastoVirhe.setText("Virhe: Vain posiitivisia numeroita.");
                saastotulos.setText("");
            }
        });

        tallennaSaastot.setOnAction(e -> {
            if (saastot.isEmpty()) {
                saastoVirhe.setText("Ei ole säästöjä tallennettaviksi.");
                saastotulos.setText("");
            }else {
                Tallennus.tallennaSaastot(saastot);
                saastoVirhe.setText("");
                saastotulos.setText("Säästöt tallennettu.");
            }
        });

        Button takaisin2 = new Button("Takaisin");
        takaisin2.setOnAction(e -> stage.setScene(new MainMenuScene(stage).getScene()));

        saastoLayout.getChildren().addAll(new Label("Säästöjen laskenta"), alkusummafield, kuukausimmasummafield, vuosikorkofield, vuodetfield, laskesaastobtn,saastotulos,saastoVirhe, tallennaSaastot, takaisin2);
        saastoScene = new Scene(saastoLayout, 400, 400);
    }

    /**
     * Palauttaa luodun säästöjem laskennan Scene olion.
     * @return Scene olio, joka edustaa Säästöjen laskennan näkymää.
     */
    public Scene getScene() {
        return saastoScene;
    }
}


