package com.example.harkkatyo;


import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * LainaScene luo uuden JavaFX näkymän, missä käyttäjä voi toteuttaa lainan kuukausierän ja kokonaiskustannuksen laskemisen
 * Käyttäjä voi syöttää lainamäärän, korkoprosentin ja laina-ajan vuosina.
 */
public class LainaScene {
    /**
     * javaFX objecti, joka edustaa lainan laskentamäkymää
     */
    private Scene lainaScene;
    /**
     * Lista, joka sisältää {@code Lainalaskuri} luokan avulla tehdyt lainalaskuri oliot.
     */
    private ArrayList<Lainalaskuri> lainat;

    /**
     * LainaScene luo lainan laskemis näkymän ja määrittelee sen elementit.
     * @param stage Stage- objecti, johon näkymä lisätään.
     */
    public LainaScene(Stage stage) {
        lainat = Tallennus.luelainat();

        VBox lainaLayout = new VBox(10);
        TextField lainamaarafield = new TextField();
        lainamaarafield.setPromptText("Lainamäärä €");

        TextField korkofield = new TextField();
        korkofield.setPromptText("Korkoprosentti %");


        TextField lainaAikafield = new TextField();
        lainaAikafield.setPromptText("Laina aika vuosina");

        Label lainatulos = new Label();
        Label lainaVirhe = new Label();
        Button laskelaina = new Button("Lainan kuukausierä");
        Button tallennaLainat = new Button("Tallenna laina");

        laskelaina.setOnAction(e -> {
            try {
                double maara = Double.parseDouble(lainamaarafield.getText());
                double korkoprosentti = Double.parseDouble(korkofield.getText());
                int Vuotta = Integer.parseInt(lainaAikafield.getText());


                Lainalaskuri laskuri = new Lainalaskuri(maara, korkoprosentti, Vuotta);
                lainat.add(laskuri);

                double kokonaiskustannus = laskuri.kokonaiskustannus();

                lainatulos.setText("Kuukausierä: " + laskuri.kuukausiErat() + "€\nKokonaiskustannus: " + kokonaiskustannus + " €");
                lainaVirhe.setText("");

            } catch (IllegalArgumentException ex) {
                lainaVirhe.setText("Virhe: syötä numerot oikein.");
                lainatulos.setText("");
            }
        });

        tallennaLainat.setOnAction(e -> {
            if (lainat.isEmpty()) {
                lainaVirhe.setText("Ei ole lainoja tallennettavaksi");
                lainatulos.setText("");
            }else {
                Tallennus.tallennaLainat(lainat);
                lainaVirhe.setText("");
                lainatulos.setText("Lainat tallennettu.");
            }
        });

        Button takaisin1 = new Button("Takaisin");
        takaisin1.setOnAction(e -> stage.setScene(new MainMenuScene(stage).getScene()));

        lainaLayout.getChildren().addAll(new Label("Lainan laskenta"), lainamaarafield, korkofield, lainaAikafield, laskelaina, lainatulos, lainaVirhe, tallennaLainat, takaisin1);
        lainaScene = new Scene(lainaLayout, 400, 400);
    }

    /**
     * Palauttaa luodun lainanlaskenta näkymän
     * @return Lainan laskennan Scene olion.
     */
    public Scene getScene() {
        return lainaScene;
    }
}
