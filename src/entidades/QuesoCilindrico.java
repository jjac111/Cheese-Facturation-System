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
public class QuesoCilindrico extends Queso {
    protected int longitud;
    
    public QuesoCilindrico() {}
    
    public QuesoCilindrico(int pB, int pU, int r, int l) throws myException{
        super(pB, pU, r);
        if(l == 0)
            throw new myException(0);
        if(l < 0)
            throw new myException(1);
        longitud = l;
    }

    public void setLongitud(int longitud) throws myException {
        if(longitud == 0)
            throw new myException(0);
        if(longitud < 0)
            throw new myException(1);
        this.longitud = longitud;
    }
    
    public int getLongitud() {
        return longitud;
    }

    @Override
    public double volumen(){
        return radio*radio*Math.PI*longitud;
    }
}
