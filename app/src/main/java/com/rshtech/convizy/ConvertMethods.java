package com.rshtech.convizy;

import android.content.Context;

/**
 * Created by Rahul Shaw on 25-06-2016.
 */
public class ConvertMethods {

    public float convertctof(float celcius) {
        return ((9 * celcius) / 5 + 32);
    }

    public float convertctok(float celkel) {
        return (celkel + 273);
    }

    public float convertftoc(float farcel) {
        return ((5 * (farcel - 32)) / 9);
    }

    public float convertftok(float farkel) {
        return (273 + convertftoc(farkel));
    }

    public float convertktoc(float kelcel) {
        return (kelcel - 273);
    }

    public float convertktof(float kelfahr) {
        return (((9 * (kelfahr - 273)) / 5 + 32));
    }

    public double convertmtocm(double mcm) {
        return (100 * mcm);
    }

    public double convertmtokm(double mkm) {
        return (mkm / 1000);
    }

    public double convertmtomil(double mmil) {
        return (mmil * (0.00062));
    }

    public double convertcmtom(double cmm) {
        return (cmm * 0.01);
    }

    public double convertcmtokm(double cmkm) {
        return (cmkm * 0.00001);
    }

    public double convertcmtomil(double cmmil) {
        return (cmmil * (0.000006));
    }

    public double convertkmtom(double kmm) {
        return (kmm * 1000);
    }

    public double convertkmtocm(double kmcm) {
        return (kmcm * 100000);
    }

    public double convertkmtomil(double kmmil) {
        return (kmmil * 0.621371);
    }

    public double convertmiltomet(double milm) {
        return (milm * 1609.34);
    }

    public double convertmiltocm(double milcm) {
        return (milcm * 160934);
    }

    public double convertmiltokm(double milkm) {
        return (milkm * 1.60934);
    }

    public double convertftoin(double fin) {
        return (12 * fin);
    }

    public double convertftoy(double fy) {
        return (0.33 * fy);
    }

    public double convertftome(double fme) {
        return (fme / 3.28);
    }

    public double convertftomi(double fmi) {
        return (fmi / 5280);
    }

    public double convertitoft(double ift) {
        return (ift / 12);
    }

    public double convertitoy(double iy) {
        return (iy / 36);
    }

    public double convertitome(double ime) {
        return (ime / 39.37);
    }

    public double convertitomi(double imi) {
        return (imi / 63360);
    }

    public double convertytoft(double yft) {
        return (yft * 3);
    }

    public double convertytoin(double yin) {
        return (yin * 36);
    }

    public double convertytome(double yme) {
        return (yme / 1.09);
    }

    public double convertytomi(double ymi) {
        return (ymi / 1760);
    }

    public double convertmetoft(double mft) {
        return (3.28 * mft);
    }

    public double convertmetoin(double mei) {
        return (mei * 39.37);
    }

    public double convertmetoy(double mey) {
        return (mey * 1.09);
    }

    public double convertmitoft(double mif) {
        return (mif * 5280);
    }

    public double convertmitoin(double mii) {
        return (mii * 63360);
    }

    public double convertmitoy(double miy) {
        return (miy * 1760);

    }
    public double convertgtokg(double gk) {
        return (gk/1000);
    }
    public double convertgtop(double gp) {
        return (gp/453.592);
    }
    public double convertgtoo(double go) {
        return (go/28.3495);
    }
    public double convertgtot(double gt) {
        return (gt/1000000);
    }
    public double convertgtoq(double gq) {
        return (gq/100000);
    }
    public double convertktop(double kp) {
        return (kp * 2.20462);
    }
    public double convertktoo(double ko) {
        return (ko * 35.274);
    }
    public double convertktot(double kt) {
        return (kt/1000);
    }
    public double convertktoq(double kq) {
        return (kq/100);
    }
    public double convertktog(double kg) {
        return (kg * 1000);
    }
    public double convertptog(double pg) {
        return (pg * 453.592);
    }
    public double convertptok(double pk) {
        return (pk * 0.453592);
    }
    public double convertptoo(double po) {
        return (po * 16);
    }
    public double convertptot(double pt) {
        return (pt/2204.62);
    }
    public double convertptoq(double pq) {
        return (pq / 220.462);
    }
    public double convertotog(double og){
        return (og * 28.3495);
    }
    public double convertotok(double ok) {
        return (ok / 35.274);
    }
    public double convertotop(double op) {
        return (op / 16);
    }
    public double convertotot(double ot) {
        return (ot/35274);
    }
    public double convertotoq(double oq) {
        return (oq / 3527.4);
    }
    public double convertttog(double tg) {
        return (tg * 1000000);
    }
    public double convertttok(double tk) {
        return (tk * 1000);
    }
    public double convertttoo(double to) {
        return (to * 35274);
    }
    public double convertttop(double tp) {
        return (tp * 2204.62);
    }
    public double convertttoq(double tq) {
        return (tq * 10);
    }
    public double convertqtok(double qk) {
        return (qk * 100);
    }
    public double convertqtog(double qg) {
        return (qg * 100000);
    }
    public double convertqtop(double qp) {
        return (qp * 220.462);
    }
    public double convertqtoo(double qo) {
        return (qo * 3527.4);
    }
    public double convertqtot(double qt) {
        return (qt/10);
    }
    public double convertkphtoms(double khms){
        return (0.277778 * khms);
    }
    public double convertkphtomih(double kmi){
        return (0.621371 * kmi);
    }
    public double convertmstokh(double mskh){
        return (3.60 * mskh);
    }
    public double convertmstomih(double mmi){
        return (2.23694 * mmi);
    }
    public double convertmihtokh(double mik){
        return (1.60934 * mik);
    }
    public double convertmihtoms(double mim){
        return (mim/2.23694);
    }


}
