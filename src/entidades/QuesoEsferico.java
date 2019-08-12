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
public class QuesoEsferico extends Queso {
    public QuesoEsferico(){}
    
    public QuesoEsferico(int pB, int pU, int r) throws myException{
        super(pB, pU, r);
    }
    
    @Override
    public double volumen(){
        return radio*radio*radio*(4/3)*Math.PI;
    }
    
}
