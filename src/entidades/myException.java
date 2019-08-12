package entidades;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author noel
 */
public class myException extends Exception {
    private String msg;
    private int code;
    public myException(String message) {
        super(message);
    }
    public myException(int code) {
        super("");
        this.code = code;
    }
    int getCode(){return code;}
    
    @Override
    public String getMessage() {
        switch (code){
            case 0:
                msg = "ERROR: Los valores ingresados no pueden ser cero."; break;
            case 1:
                msg = "ERROR: Los valores ingresados no pueden ser negativos."; break;
            case 2:
                msg = "ERROR: El radio interno no puede exceder el externo."; break;
            case 3:
                msg = "ERROR: El radio interno no puede ser igual al externo."; break;
            case 4:
                msg = "ERROR: Ya existe una factura con igual código."; break;
            case 5:
                msg = "ERROR: Ya existe el cliente."; break;
        }
        return msg;
    }
}
