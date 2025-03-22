package com.example.harkkatyo;

import java.io.Serializable;

/**
 *tämä luokka laskee lainaan liittyviä arvoja. Sisältää kuukausierien ja kokonaiskustannusten laskennan
 */
public class Lainalaskuri implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * lainan pääoma, lainan korkoprosentti ja lainan kokonaiskesto vuosina.
     */
    private double lainaMaara;
    private double korkoProsentti;
    private int lainaAika;

    /**
     * Konstruktori, jolla luodaan uusi LainaLaskuri-olio annetuilla arvoilla,
     * @param lainaMaara Lainan määrä
     * @param korkoProsentti Lainan korko
     * @param lainaAika Lainan aika vuosina
     */
    public Lainalaskuri(double lainaMaara, double korkoProsentti, int lainaAika) {
        if (lainaMaara <= 0 || korkoProsentti <= 0 || lainaAika <= 0){
            throw new IllegalArgumentException("Arvojen tulee olla positiivisia ja suurempia kuin 0.");
        }
        this.lainaMaara = lainaMaara;
        this.korkoProsentti = korkoProsentti;
        this.lainaAika = lainaAika;
    }

    /**
     *  Hakee lainan määrän
     * @return  Lainattu rahamäärä
     */
    public double getLainaMaara() {
        return lainaMaara;
    }

    /**
     * Asettaa uuden lainan määrän
     * @param lainaMaara uusi lainattu määrä
     */
    public void setLainaMaara(double lainaMaara) {
        this.lainaMaara = lainaMaara;
    }

    /**
     * Hakee lainan korkoprosentin
     * @return Lainan korkoprosentti
     */
    public double getKorkoProsentti() {
        return korkoProsentti;
    }

    /**
     *  Asettaa uuden lainan korkoprosentin
     * @param korkoProsentti
     */
    public void setKorkoProsentti(double korkoProsentti) {
        this.korkoProsentti = korkoProsentti;
    }

    /**
     * Hakee lainan kokonaiskeston
     * @return
     */
    public int getLainaAika() {
        return lainaAika;
    }

    /**
     *  asettaa lainalle kokonaiskeston
     * @param lainaAika
     */
    public void setLainaAika(int lainaAika) {
        this.lainaAika = lainaAika;
    }

    /**
     * Metodi Lainan kuukausierän laskemiseen, käyttää annettua lainamäärää, korkoprosenttia ja laina-aikaa
     * @return palauttaa lainalle lasketun kuukausierän määrän
     * ohjelma on tehty laskemaan annuiteettilainaa. eli muihin lainatyyppeihin se ei kykene. siitä syystä käytän math.pow metodia
     * jonka avulla 1+kuukauskorko saadaan korotettua -kuukaudet verran potenssiin ja saadaan annuitettiilaina laskentaan tarvittava logiikka toimimaan.
     */
    public double kuukausiErat(){
        double KuukausiKorko = (korkoProsentti / 100) / 12;
        int kuukaudet = lainaAika * 12;
        double kuukausiErat = (lainaMaara * KuukausiKorko) / (1 - Math.pow(1 + KuukausiKorko, -kuukaudet));
        return Math.round(kuukausiErat * 100.0) / 100.0;
    }
    public double kokonaiskustannus() {
        int kuukaudet = lainaAika * 12;
        double kuukausiErat = kuukausiErat();
        return Math.round(kuukaudet*kuukausiErat*100.0) / 100.0;
    }
}