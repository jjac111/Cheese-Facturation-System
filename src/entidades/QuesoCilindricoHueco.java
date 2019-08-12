/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Juan Javier
 */
public class QuesoCilindricoHueco extends QuesoCilindrico {

    private int radioInterno;

    public QuesoCilindricoHueco() {
    }

    public QuesoCilindricoHueco(int pB, int pU, int r, int l, int rI) throws myException {
        super(pB, pU, r, l);
        if (r < rI) {
            throw new myException(2);
        }
        if (r == rI) {
            throw new myException(3);
        }
        radioInterno = rI;
    }

    public void setRadioInterno(int radioInterno) throws myException {
        if (radioInterno == 0) {
            throw new myException(0);
        }
        if (radioInterno < 0) {
            throw new myException(1);
        }
        this.radioInterno = radioInterno;
    }

    public int getRadioInterno() {
        return radioInterno;
    }

    @Override
    public double volumen() {
        return Math.PI * longitud * ((radio * radio) - (radioInterno * radioInterno));
    }
}
