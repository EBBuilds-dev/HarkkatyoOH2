package com.example.harkkatyo;


import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 * Tämä luokka laskee tulevan säästöjen arvon perustuen aloitussummaan,
 * kuukausittaisiin talletuksiin, vuotuiseen korkoprosenttiin ja vuosien määrään.
 * monien mutkien kautta päädytty bigdecimal tietotyyppiin.
 */
public class SaastoLaskuri implements Serializable {
    private static final long serialVersionUID = 2L;
    private BigDecimal Alkusumma;
    private BigDecimal KuukausiSumma;
    private BigDecimal Vuosikorko;
    private int Vuodet;

    /**
     *  parametrillinen alustaja, joka ottaa arvoiksi.
     * @param Alkusumma Alkusäästöjen summan
     * @param KuukausiSumma kuukausittain säästettävän summan
     * @param Vuodet Säästöaika vuosina
     * @param Vuosikorko Vuotuinen korkoprosentti
     */
    public SaastoLaskuri(BigDecimal Alkusumma, BigDecimal KuukausiSumma, int Vuodet, BigDecimal Vuosikorko) {
        if(Alkusumma == null || Alkusumma.compareTo(BigDecimal.ZERO) <=0){
            throw new IllegalArgumentException("Alkusumma ei voi olla 0 tai negatiivinen");
        }
        if(KuukausiSumma == null || KuukausiSumma.compareTo(BigDecimal.ZERO) <=0){
            throw new IllegalArgumentException("Kuukausittain säästettävä summa ei voi olla 0 tai negatiivinen");
        }
        if(Vuodet < 0){
            throw new IllegalArgumentException("Luku ei voi olla 0 tai negatiivinen");
        }
        if(Vuosikorko == null || Vuosikorko.compareTo(BigDecimal.ZERO) <=0){
            throw new IllegalArgumentException("Vuosikorko ei voi olla 0 tai negatiivinen");
        }
        this.Alkusumma = Alkusumma;
        this.KuukausiSumma = KuukausiSumma;
        this.Vuodet = Vuodet;
        this.Vuosikorko = Vuosikorko;
    }

    /**
     *  Hakee aluksi säästettävän summan
     * @return Alkusäästöjen summa
     */
    public BigDecimal getAlkusumma() {
        return Alkusumma;
    }

    /**
     * Asettaa uuden alkusäästäjen summan
     * @param alkusumma Alkusäästöjen summa
     */
    public void setAlkusumma(BigDecimal alkusumma) {
        Alkusumma = alkusumma;
    }

    /**
     *  Hakee kuukausittain säästetävän summan
     * @return kuukausittain säästöönm laitettava summa
     */
    public BigDecimal getKuukausiSumma() {
        return KuukausiSumma;
    }

    /**
     * Asettaa uuden kuukausittain säästettävän summan
     * @param kuukausiSumma kuukausittain säästettävä summa
     */
    public void setKuukausiSumma(BigDecimal kuukausiSumma) {
        KuukausiSumma = kuukausiSumma;
    }

    /**
     * hakee vuotuisen korkoprosentin
     * @return vuotuinen korkoprosentti
     */
    public BigDecimal getVuosikorko() {
        return Vuosikorko;
    }

    /**
     * asettaa vuotuisen korkoprosentin
     * @param vuosikorko vuotuinen korkoprosentti
     */
    public void setVuosikorko(BigDecimal vuosikorko) {
        Vuosikorko = vuosikorko;
    }

    /**
     *  hakee vuosien määrän eli säästöajan
     * @return säästöaika
     */
    public int getVuodet() {
        return Vuodet;
    }

    /**
     * asettaa säästöajan
     * @param vuodet säästöaika
     */
    public void setVuodet(int vuodet) {
        Vuodet = vuodet;
    }

    /**
     * Metodi, joka ottaa huomioon yllä annetut parametrit ja laskee niiden avulla kokonaissumman 10 decimaalin tarkkuudella
     * annetuilla arvoilla ja oikeita kaavoja käyttäen. Decimaaleista ja pyöristyksistä johtuen laskenta ei ole tarkin mahdollinen
     * @return palauttaa kokonaissumman mikä on kasassa annetuilla parametreilla.
     */
    public BigDecimal LaskeSaastoArvo(){
        BigDecimal kuukausikorko = Vuosikorko.divide(BigDecimal.valueOf(100*12),10, RoundingMode.HALF_UP);
        int kuukaudet = Vuodet * 12;

        BigDecimal yksi = BigDecimal.ONE;
        BigDecimal potenssi = yksi.add(kuukausikorko).pow(kuukaudet);

        BigDecimal tulevaArvo = Alkusumma.multiply(potenssi);

        BigDecimal talletuskerroin = potenssi.subtract(yksi).divide(kuukausikorko,10,RoundingMode.HALF_UP);
        BigDecimal talletusArvo = KuukausiSumma.multiply(talletuskerroin);

        return tulevaArvo.add(talletusArvo).setScale(2, RoundingMode.HALF_UP);
    }
}