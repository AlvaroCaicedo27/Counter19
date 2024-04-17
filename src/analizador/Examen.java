/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador;

/**
 *
 * @author DESARROLLO
 */
public class Examen {
    
    String codigo;
    String fecha;
    String wbc;
    String lymph;
    String mid;
    String gran;
    String lymph1;
    String mid1;
    String gran1;
    String rbc;
    String hgb;
    String mchc;
    String mcv;
    String mch;
    String rdwcv;
    String hct;
    String plt;
    String mpv;
    String pdw;
    String pct;
    String rdwsd;
    

    // Constructor
    public Examen(String codigo, String fecha, String wbc, String lymph, String mid, String gran, String lymph1, String mid1,
                  String gran1, String rbc, String hgb, String mchc, String mcv, String mch, String rdwcv, String hct,
                  String plt, String mpv, String pdw, String pct, String rdwsd,String hola) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.wbc = wbc;
        this.lymph = lymph;
        this.mid = mid;
        this.gran = gran;
        this.lymph1 = lymph1;
        this.mid1 = mid1;
        this.gran1 = gran1;
        this.rbc = rbc;
        this.hgb = hgb;
        this.mchc = mchc;
        this.mcv = mcv;
        this.mch = mch;
        this.rdwcv = rdwcv;
        this.hct = hct;
        this.plt = plt;
        this.mpv = mpv;
        this.pdw = pdw;
        this.pct = pct;
        this.rdwsd = rdwsd;
        
    }

    public String getCodigo() {
        return codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public String getWbc() {
        return wbc;
    }

    public String getLymph() {
        return lymph;
    }

    public String getMid() {
        return mid;
    }

    public String getGran() {
        return gran;
    }

    public String getLymph1() {
        return lymph1;
    }

    public String getMid1() {
        return mid1;
    }

    public String getGran1() {
        return gran1;
    }

    public String getRbc() {
        return rbc;
    }

    public String getHgb() {
        return hgb;
    }

    public String getMchc() {
        return mchc;
    }

    public String getMcv() {
        return mcv;
    }

    public String getMch() {
        return mch;
    }

    public String getRdwcv() {
        return rdwcv;
    }

    public String getHct() {
        return hct;
    }

    public String getPlt() {
        return plt;
    }

    public String getMpv() {
        return mpv;
    }

    public String getPdw() {
        return pdw;
    }

    public String getPct() {
        return pct;
    }

    public String getRdwsd() {
        return rdwsd;
    }

    @Override
    public String toString() {
        return "Examen{" + "codigo=" + codigo + ", fecha=" + fecha + ", wbc=" + wbc + ", lymph=" + lymph + ", mid=" + mid + ", gran=" + gran + ", lymph1=" + lymph1 + ", mid1=" + mid1 + ", gran1=" + gran1 + ", rbc=" + rbc + ", hgb=" + hgb + ", mchc=" + mchc + ", mcv=" + mcv + ", mch=" + mch + ", rdwcv=" + rdwcv + ", hct=" + hct + ", plt=" + plt + ", mpv=" + mpv + ", pdw=" + pdw + ", pct=" + pct + ", rdwsd=" + rdwsd + '}';
    }

    
}
