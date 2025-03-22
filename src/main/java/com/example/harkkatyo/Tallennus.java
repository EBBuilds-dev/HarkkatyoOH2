package com.example.harkkatyo;

import java.io.*;
import java.util.ArrayList;

/**
 * Tallenus- luokka luo toiminnon main luokassa käsiteltyjen tietojen tallentamiseen ja tallennettujen tietojen lataamiseen main ohjelman käyttöön
 * Tämä luokka käyttää serialisointia tietojen tallentamiseen ja lukemiseen.
 */
public class Tallennus {
    private static final String Taloustiedot = "lainat.dat";
    private static final String Taloustiedot2 = "saastot.dat";

    /**
     * Tallentaa annetut lainat tiedostoon {@code lainat.dat}
     * @param lainat lainat lista {@code Lainalaskuri- olioita} jotka tallennetaan lainat.dat tiedostoon.
     */
    public static void tallennaLainat(ArrayList<Lainalaskuri> lainat) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Taloustiedot))) {
            oos.writeObject(lainat);
            System.out.println("Lainat tallenennettu tiedostoon.");
        } catch (IOException e){
            System.out.println("Virhe tallentaessa");
        }
    }

    /**
     * Lukee lainat.dat tiedostoon tallennetut {@code Lainalaskuri} olioita.
     * @return Lista yllämainittuja olioita tai tyhjä lista, jos lukeminen epäonnistui.
     */
    public static ArrayList<Lainalaskuri> luelainat(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Taloustiedot))) {
            return (ArrayList<Lainalaskuri>) ois.readObject();
        }catch (FileNotFoundException e){
            System.out.println("Tiedostoa ei löydy");
        }catch (IOException | ClassNotFoundException e){
            System.out.println("virhe luettaessa");
        }
        return new ArrayList<>();
    }

    /**
     * tyhjentää lainat.dat tiedoston kokonaisuudessaan.
     */
    public static void poistaLainat(){
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(Taloustiedot))){
            os.writeObject(new ArrayList<Lainalaskuri>());
            System.out.println("Kaikki lainat poistettu.");
        } catch (IOException e){
            System.out.println("Virhe poistaessa lainoja.");
        }
    }

    /**
     * Tallentaa annetut säästöt tiedostoon {@code saastot.dat} ja palauttaa listan {@code SaastoLaskuri olioita. }
     * @param saastot Lista {@code SaastoLaskuri} olioita
     */
    public static void tallennaSaastot(ArrayList<SaastoLaskuri> saastot) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(Taloustiedot2))){
            os.writeObject(saastot);
            System.out.println("Saastot tallennettu tiedostoon.");
        } catch (IOException e){
            System.out.println("Virhe tallentaessa");
        }
    }

    /**
     * Lukee tiedostosta {@code saastot.dat} ja palauttaa listan  {@code SaastoLaskuri} olioita, tai tyhjän listan jos lukeminen epäonnistui.
     * @return Lista {@code SaastoLaskuri} olioita.
     */
    public static ArrayList<SaastoLaskuri> luesaastot(){
        try (ObjectInputStream oiss = new ObjectInputStream(new FileInputStream(Taloustiedot2))){
            return (ArrayList<SaastoLaskuri>) oiss.readObject();
        } catch (FileNotFoundException e){
            System.out.println("tallenttua säästöä ei löydy");
        }catch (IOException | ClassNotFoundException e){
            System.out.println("virhe luettaessa");
        }
        return new ArrayList<>();
    }

    /**
     * tyhjentää saastot.dat tiedoston kokonaisuudessaan.
     */
    public static void poistaSaastot(){
        try (ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(Taloustiedot2))){
            ous.writeObject(new ArrayList<SaastoLaskuri>());
            System.out.println("Kaikki säästöt poistettu.");
        }catch (IOException e){
            System.out.println("Virhe poistaessa säästöjä.");
        }
    }
}
