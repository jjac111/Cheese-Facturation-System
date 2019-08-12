package entidades;


import java.text.DecimalFormat;
import java.util.*;

public class Factura {

    private int id;

    private Cliente cliente;

    private HashMap<Queso, Integer> productos;

    
    
    public Factura() {
        id = 0;
        cliente = new Cliente();
        productos = new HashMap<>();
    }
    
    public Factura(int id) throws myException{
        if(id == 0)
            throw new myException(0);
        if(id < 0)
            throw new myException(1);
        this.id = id;
        cliente = new Cliente();
        productos = new HashMap<>();
    }

    public Factura(int id, Cliente cli, HashMap<Queso, Integer> pro) throws myException {   //add exception to id if exists.
        if(id == 0)
            throw new myException(0);
        if(id < 0)
            throw new myException(1);
        this.id = id;
        cliente = cli;
        productos = pro;
    }

    public void setId(int id) throws myException {
        if(id == 0)
            throw new myException(0);
        if(id < 0)
            throw new myException(1);
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setProductos(HashMap<Queso, Integer> productos) {
        this.productos = productos;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public HashMap<Queso, Integer> getProductos() {
        return productos;
    }
    
    public void addProducto(Queso key, Integer cant) throws myException {
        if(cant == 0)
            throw new myException(0);
        if(cant < 0)
            throw new myException(1);
        productos.put(key, cant);
    }

    public void changeProducto(Queso key, Integer cant) throws myException {
        if(cant == 0)
            throw new myException(0);
        if(cant < 0)
            throw new myException(1);
        productos.put(key, cant);
    }
    
    public void removeProducto(Queso key){
        productos.remove(key);
    }

    public double totalACancelar() {
        double pagar = 0;
        for(Map.Entry e: productos.entrySet())
            pagar += (((Queso)e.getKey()).costo())*((Integer)e.getValue());
        return pagar;
    }
    
    public String print(){
        DecimalFormat DF = new DecimalFormat("#.00");
        String message = "";
        message += "ID :\t" + Integer.toString(id) + "\n\n" + cliente.getNombre() + "\n" + cliente.getDireccion() + "\n" + Integer.toString(cliente.getTelefono()) + "\n\n";
        message += "-----------------------------------\n\n";
        for(Map.Entry e: productos.entrySet()){
            if(((Queso)e.getKey()) instanceof QuesoEsferico)
                message += "Queso Esférico " + ((QuesoEsferico)e.getKey()).getRadio() + "cm\t\t\tX" + Integer.toString((Integer)e.getValue()) + "\t$" + DF.format(((QuesoEsferico)e.getKey()).costo()) + "\n";
            if(((Queso)e.getKey()) instanceof QuesoCilindrico){
                if(((Queso)e.getKey()) instanceof QuesoCilindricoHueco)
                    message += "Queso Cilíndrico Hueco " + ((QuesoCilindricoHueco)e.getKey()).getRadio() + "x(" + ((QuesoCilindricoHueco)e.getKey()).getLongitud() + "-" + ((QuesoCilindricoHueco)e.getKey()).getRadioInterno() + ")cm\t\tX" + Integer.toString((Integer)e.getValue()) + "\t$" + DF.format(((QuesoCilindricoHueco)e.getKey()).costo()) + "\n";
                else
                message += "Queso Cilíndrico " + ((QuesoCilindrico)e.getKey()).getRadio() + "x" + ((QuesoCilindrico)e.getKey()).getLongitud() + "cm\t\tX" + Integer.toString((Integer)e.getValue()) + "\t$" + DF.format(((QuesoCilindrico)e.getKey()).costo()) + "\n";    
            }
        }
        message += "\n\nTOTAL A CANCELAR: \t\t\t$" + DF.format(totalACancelar());
        return message;
    }

}