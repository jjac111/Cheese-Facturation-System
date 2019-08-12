/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Juan Javier
 */
public class Empresa {

    private HashMap<Integer, Factura> facturas;
    private ArrayList<Cliente> clientes;                

    public Empresa() {
        facturas = new HashMap<>();
        clientes = new ArrayList<>();
    }

    public Empresa(HashMap<Integer, Factura> facturas) {      
        this.facturas = facturas;
    }

    public HashMap<Integer, Factura> getFacturas() {
        return facturas;
    }

    public ArrayList<Cliente> getClientes() {           
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setFacturas(HashMap<Integer, Factura> facturas) {
        this.facturas = facturas;
    }

    public void addFactura(Factura factura) throws myException {
        facturas.put(factura.getId(), factura);
    }

    public void addCliente(Cliente cliente) throws myException {
        if (clientes.contains(cliente)) {
            throw new myException(5);
        }
        clientes.add(cliente);
    }

    public void removeCliente(Cliente cli) {
        clientes.remove(cli);
    }

    public void updateCliente(Cliente cli, Cliente newCli) {
        clientes.set(clientes.indexOf(cli), newCli);
    }

    public void removeFactura(Integer id) {
        facturas.remove(id);
    }

    public Double ingresos() {
        Double ingresos = 0d;
        for (Map.Entry e : facturas.entrySet()) {
            ingresos += ((Factura) e.getValue()).totalACancelar();
        }
        return ingresos;
    }
}
