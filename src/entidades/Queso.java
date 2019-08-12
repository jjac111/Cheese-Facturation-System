/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author estudiante
 */
public abstract class Queso {
    protected int precioBase;
    protected int precioUnitario;
    protected int radio;
    
    public Queso(){}
    
    public Queso(int pB, int pU, int r) throws myException{
        if(pB == 0 || pU == 0 || r == 0)
            throw new myException(0);
        if(pB < 0 || pU < 0 || r < 0)
            throw new myException(1);
        precioBase = pB;
        precioUnitario = pU;
        radio = r;
    }

    public void setPrecioBase(int precioBase) throws myException {
        if(precioBase == 0)
            throw new myException(0);
        if(precioBase < 0)
            throw new myException(1);
        this.precioBase = precioBase;
    }

    public void setPrecioUnitario(int precioUnitario) throws myException {
        if(precioUnitario == 0)
            throw new myException(0);
        if(precioUnitario < 0)
            throw new myException(1);
        this.precioUnitario = precioUnitario;
    }

    public void setRadio(int radio) throws myException {
        if(radio == 0)
            throw new myException(0);
        if(radio < 0)
            throw new myException(1);
        this.radio = radio;
    }

    public int getPrecioBase() {
        return precioBase;
    }

    public int getPrecioUnitario() {
        return precioUnitario;
    }

    public int getRadio() {
        return radio;
    }
    
    public double costo(){
        return volumen()*precioUnitario + precioBase;
    }
    
    public abstract double volumen();
    
}
