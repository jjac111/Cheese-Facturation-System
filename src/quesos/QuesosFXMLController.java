    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quesos;

import entidades.Cliente;
import entidades.Empresa;
import entidades.Factura;
import entidades.Queso;
import entidades.QuesoCilindrico;
import entidades.QuesoCilindricoHueco;
import entidades.QuesoEsferico;
import entidades.myException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Juan Javier
 */
public class QuesosFXMLController implements Initializable {

    private Empresa EMP = new Empresa();
    private Alert alert = new Alert(Alert.AlertType.ERROR);
    private Alert alertGood = new Alert(Alert.AlertType.INFORMATION);
    private Factura testFact = new Factura();
    private Queso testQueso;
    private Cliente testCliente = new Cliente();
    private DecimalFormat DF = new DecimalFormat("#.00");
    
    @FXML
    private ListView<String> clientesList;
    @FXML
    private Label clientesDireccionLabel;
    @FXML
    private Label clientesNombreLabel;
    @FXML
    private Label clientesTelefonoLabel;
    @FXML
    private TextField clientesNombreTxt;
    @FXML
    private TextField clientesDireccionTxt;
    @FXML
    private TextField clientesTelefonoTxt;
    @FXML
    private Button clientesAddButton;
    @FXML
    private Button clientesUpdateButton;
    @FXML
    private Button clientesRemoveButton;
    @FXML
    private TextArea mainMenuTxtArea;
    @FXML
    private ListView<Integer> mainMenuList;
    @FXML
    private Button mainMenuRemoveButton;
    @FXML
    private Button mainMenuCalcularButton;
    @FXML
    private ListView<String> facturacionList;
    @FXML
    private Button facturacionRemoveButton;
    @FXML
    private Button facturacionPButton;
    @FXML
    private Button facturacionMButton;
    @FXML
    private ChoiceBox<Integer> facturacionFacturasOptions;
    @FXML
    private Label facturacionIDLabel;
    @FXML
    private VBox FacturacionQuesoEsfBox;
    @FXML
    private TextField facturacionQuesoEsfRadioTxt;
    @FXML
    private VBox FacturacionQuesoCilBox;
    @FXML
    private TextField facturacionQuesoCilRadioTxt;
    @FXML
    private TextField facturacionQuesoCilLongitudTxt;
    @FXML
    private VBox FacturacionQuesoCilHueBox;
    @FXML
    private TextField facturacionQuesoCilHueRadioTxt;
    @FXML
    private TextField facturacionQuesoCilHueLongitudTxt;
    @FXML
    private TextField facturacionQuesoCilHueRadioIntTxt;
    @FXML
    private ChoiceBox<String> facturacionQuesosOptions;
    @FXML
    private ChoiceBox<String> facturacionClientesOptions;
    @FXML
    private TextField facturacionQuesoPrecioBaseTxt;
    @FXML
    private TextField facturacionQuesoPrecioUnitarioTxt;
    @FXML
    private TextField facturacionQuesosCantTxt;
    @FXML
    private Label facturacionClienteNombreLabel;
    @FXML
    private Label facturacionClienteDireccionLabel;
    @FXML
    private Label facturacionClienteTelefonoLabel;
    @FXML
    private TextField facturacionIDTxt;
    @FXML
    private Button facturacionNewButton;
    @FXML
    private Button facturacionAddButton;
    @FXML
    private Button facturacionGuardarFacturaButton;

    @FXML
    protected void handlerClientesAddButton(ActionEvent event){
        try{
            Cliente cli = new Cliente(clientesNombreTxt.getText(), clientesDireccionTxt.getText(), Integer.parseInt(clientesTelefonoTxt.getText()));
            EMP.addCliente(cli);
            updateClientesList();
        }
        catch(Exception e){
            alert.setTitle("Warning");
            alert.setHeaderText("Datos Inválidos");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    protected void handlerClientesUpdateButton(ActionEvent event){
        try{
            if(!clientesList.getSelectionModel().isEmpty())
                for(Cliente cli: EMP.getClientes())
                    if(cli.getNombre().equals(clientesList.getSelectionModel().getSelectedItem())){
                        EMP.updateCliente(cli, new Cliente(clientesNombreTxt.getText(), clientesDireccionTxt.getText(), Integer.parseInt(clientesTelefonoTxt.getText())));
                    }
            updateClientesList();
        }
        catch(Exception e){
            alert.setTitle("Warning");
            alert.setHeaderText("Datos Inválidos");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    protected void handlerClientesRemoveButton(ActionEvent event){
        try{
            if(!clientesList.getSelectionModel().isEmpty())
                for(Cliente cli: EMP.getClientes())
                    if(cli.getNombre().equals(clientesList.getSelectionModel().getSelectedItem())){
                        EMP.removeCliente(cli);
                        break;
                    }
            updateClientesList();
        }
        catch(Exception e){
            alert.setTitle("Warning");
            alert.setHeaderText("Datos Inválidos");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    protected void handlerFacturacionNewButton(ActionEvent event){
        try{
            EMP.addFactura(new Factura(Integer.parseInt(facturacionIDTxt.getText())));
            updateFacturas();
        }
        catch(Exception e){
            alert.setTitle("Warning");
            alert.setHeaderText("Datos Inválidos");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    protected void handlerFacturacionAddButton(ActionEvent event){
        try{
            if(!facturacionQuesoPrecioBaseTxt.getText().isEmpty() && 
               !facturacionQuesoPrecioUnitarioTxt.getText().isEmpty() &&
               !facturacionQuesosCantTxt.getText().isEmpty() &&
               (!facturacionQuesoEsfRadioTxt.getText().isEmpty() || 
               (!facturacionQuesoCilRadioTxt.getText().isEmpty() &&
               !facturacionQuesoCilLongitudTxt.getText().isEmpty()) ||
               (!facturacionQuesoCilHueRadioTxt.getText().isEmpty() &&
               !facturacionQuesoCilHueLongitudTxt.getText().isEmpty() &&
               !facturacionQuesoCilHueRadioIntTxt.getText().isEmpty()))){    
                if(facturacionQuesosOptions.getSelectionModel().getSelectedItem().equals("Queso Esférico"))
                    testQueso = new QuesoEsferico(Integer.parseInt(facturacionQuesoPrecioBaseTxt.getText()), Integer.parseInt(facturacionQuesoPrecioUnitarioTxt.getText()), Integer.parseInt(facturacionQuesoEsfRadioTxt.getText()));
                if(facturacionQuesosOptions.getSelectionModel().getSelectedItem().equals("Queso Cilíndrico"))
                    testQueso = new QuesoCilindrico(Integer.parseInt(facturacionQuesoPrecioBaseTxt.getText()), Integer.parseInt(facturacionQuesoPrecioUnitarioTxt.getText()), Integer.parseInt(facturacionQuesoCilRadioTxt.getText()), Integer.parseInt(facturacionQuesoCilLongitudTxt.getText()));
                if(facturacionQuesosOptions.getSelectionModel().getSelectedItem().equals("Queso Cilíndrico Hueco"))
                    testQueso = new QuesoCilindricoHueco(Integer.parseInt(facturacionQuesoPrecioBaseTxt.getText()), Integer.parseInt(facturacionQuesoPrecioUnitarioTxt.getText()), Integer.parseInt(facturacionQuesoCilHueRadioTxt.getText()), Integer.parseInt(facturacionQuesoCilHueLongitudTxt.getText()), Integer.parseInt(facturacionQuesoCilHueRadioIntTxt.getText()));
                testFact.addProducto(testQueso, Integer.parseInt(facturacionQuesosCantTxt.getText()));
                facturacionQuesoPrecioBaseTxt.clear();
                facturacionQuesoPrecioUnitarioTxt.clear();
                facturacionQuesoEsfRadioTxt.clear();
                facturacionQuesoCilRadioTxt.clear();
                facturacionQuesoCilLongitudTxt.clear();
                facturacionQuesoCilHueRadioTxt.clear();
                facturacionQuesoCilHueLongitudTxt.clear();
                facturacionQuesoCilHueRadioIntTxt.clear();
                facturacionQuesosCantTxt.clear();
                updateFacturacionList();
            }
        }
        catch(Exception e){
            alert.setTitle("Warning");
            alert.setHeaderText("Datos Inválidos");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    protected void handlerFacturacionRemoveButton(ActionEvent event){
        try{
            if(!facturacionList.getSelectionModel().isEmpty() && !facturacionFacturasOptions.getSelectionModel().isEmpty()){
                for(Map.Entry entry: EMP.getFacturas().entrySet()){
                    if(((Integer)entry.getKey()).equals(facturacionFacturasOptions.getSelectionModel().getSelectedItem())){
                        for(Map.Entry entryQuesos: EMP.getFacturas().get(facturacionFacturasOptions.getSelectionModel().getSelectedItem()).getProductos().entrySet()){
                            if(facturacionList.getSelectionModel().getSelectedItem().equals("Queso Esférico " + ((QuesoEsferico)entryQuesos.getKey()).getRadio() + "cm\t\tX" + Integer.toString((Integer)entryQuesos.getValue()) + "\t$" + ((QuesoEsferico)entryQuesos.getKey()).costo()))
                                testQueso = (QuesoEsferico)entryQuesos.getKey();
                            if(facturacionList.getSelectionModel().getSelectedItem().equals("Queso Cilíndrico " + ((QuesoCilindrico)entryQuesos.getKey()).getRadio() + "x" + ((QuesoCilindrico)entryQuesos.getKey()).getLongitud() + "cm\t\tX" + Integer.toString((Integer)entryQuesos.getValue()) + "\t$" + ((QuesoCilindrico)entryQuesos.getKey()).costo()))
                                testQueso = (QuesoEsferico)entryQuesos.getKey();
                            if(facturacionList.getSelectionModel().getSelectedItem().equals("Queso Cilíndrico Hueco " + ((QuesoCilindricoHueco)entryQuesos.getKey()).getRadio() + "x(" + ((QuesoCilindricoHueco)entryQuesos.getKey()).getLongitud() + "-" + ((QuesoCilindricoHueco)entryQuesos.getKey()).getRadioInterno() + ")cm\t\tX" + Integer.toString((Integer)entryQuesos.getValue()) + "\t$" + ((QuesoCilindricoHueco)entryQuesos.getKey()).costo()))
                                testQueso = (QuesoEsferico)entryQuesos.getKey();
                        }
                        testFact.removeProducto(testQueso);
                    }
                }
                updateFacturacionList();
            }
        }
        catch(Exception e){
            alert.setTitle("Warning");
            alert.setHeaderText("Datos Inválidos");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    protected void handlerFacturacionPButton(ActionEvent event){
        try{
            if(!facturacionList.getSelectionModel().isEmpty() && !facturacionFacturasOptions.getSelectionModel().isEmpty()){
                for(Map.Entry entry: EMP.getFacturas().entrySet()){
                    if(((Integer)entry.getKey()).equals(facturacionFacturasOptions.getSelectionModel().getSelectedItem())){
                        for(Map.Entry entryQuesos: EMP.getFacturas().get(facturacionFacturasOptions.getSelectionModel().getSelectedItem()).getProductos().entrySet()){
                            if(entryQuesos.getKey() instanceof QuesoEsferico)    
                                if(facturacionList.getSelectionModel().getSelectedItem().equals("Queso Esférico " + ((QuesoEsferico)entryQuesos.getKey()).getRadio() + "cm\t\tX" + Integer.toString((Integer)entryQuesos.getValue()) + "\t$" + ((QuesoEsferico)entryQuesos.getKey()).costo()))
                                    testQueso = (QuesoEsferico)entryQuesos.getKey();
                            if(entryQuesos.getKey() instanceof QuesoCilindrico)
                                if(facturacionList.getSelectionModel().getSelectedItem().equals("Queso Cilíndrico " + ((QuesoCilindrico)entryQuesos.getKey()).getRadio() + "x" + ((QuesoCilindrico)entryQuesos.getKey()).getLongitud() + "cm\t\tX" + Integer.toString((Integer)entryQuesos.getValue()) + "\t$" + ((QuesoCilindrico)entryQuesos.getKey()).costo()))
                                    testQueso = (QuesoCilindrico)entryQuesos.getKey();
                            if(entryQuesos.getKey() instanceof QuesoCilindricoHueco)
                                if(facturacionList.getSelectionModel().getSelectedItem().equals("Queso Cilíndrico Hueco " + ((QuesoCilindricoHueco)entryQuesos.getKey()).getRadio() + "x(" + ((QuesoCilindricoHueco)entryQuesos.getKey()).getLongitud() + "-" + ((QuesoCilindricoHueco)entryQuesos.getKey()).getRadioInterno() + ")cm\t\tX" + Integer.toString((Integer)entryQuesos.getValue()) + "\t$" + ((QuesoCilindricoHueco)entryQuesos.getKey()).costo()))
                                    testQueso = (QuesoCilindricoHueco)entryQuesos.getKey();
                        }
                        testFact.changeProducto(testQueso, EMP.getFacturas().get((Integer)entry.getKey()).getProductos().get(testQueso)+1);
                    }
                }
                updateFacturacionList();
            }
        }
        catch(Exception e){
            alert.setTitle("Warning");
            alert.setHeaderText("Datos Inválidos");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    protected void handlerFacturacionMButton(ActionEvent event){
        try{
            if(!facturacionList.getSelectionModel().isEmpty() && !facturacionFacturasOptions.getSelectionModel().isEmpty()){
                for(Map.Entry entry: EMP.getFacturas().entrySet()){
                    if(((Integer)entry.getKey()).equals(facturacionFacturasOptions.getSelectionModel().getSelectedItem())){
                        for(Map.Entry entryQuesos: EMP.getFacturas().get(facturacionFacturasOptions.getSelectionModel().getSelectedItem()).getProductos().entrySet()){
                            if(entryQuesos.getKey() instanceof QuesoEsferico)    
                                if(facturacionList.getSelectionModel().getSelectedItem().equals("Queso Esférico " + ((QuesoEsferico)entryQuesos.getKey()).getRadio() + "cm\t\tX" + Integer.toString((Integer)entryQuesos.getValue()) + "\t$" + ((QuesoEsferico)entryQuesos.getKey()).costo()))
                                    testQueso = (QuesoEsferico)entryQuesos.getKey();
                            if(entryQuesos.getKey() instanceof QuesoCilindrico)
                                if(facturacionList.getSelectionModel().getSelectedItem().equals("Queso Cilíndrico " + ((QuesoCilindrico)entryQuesos.getKey()).getRadio() + "x" + ((QuesoCilindrico)entryQuesos.getKey()).getLongitud() + "cm\t\tX" + Integer.toString((Integer)entryQuesos.getValue()) + "\t$" + ((QuesoCilindrico)entryQuesos.getKey()).costo()))
                                    testQueso = (QuesoCilindrico)entryQuesos.getKey();
                            if(entryQuesos.getKey() instanceof QuesoCilindricoHueco)
                                if(facturacionList.getSelectionModel().getSelectedItem().equals("Queso Cilíndrico Hueco " + ((QuesoCilindricoHueco)entryQuesos.getKey()).getRadio() + "x(" + ((QuesoCilindricoHueco)entryQuesos.getKey()).getLongitud() + "-" + ((QuesoCilindricoHueco)entryQuesos.getKey()).getRadioInterno() + ")cm\t\tX" + Integer.toString((Integer)entryQuesos.getValue()) + "\t$" + ((QuesoCilindricoHueco)entryQuesos.getKey()).costo()))
                                    testQueso = (QuesoCilindricoHueco)entryQuesos.getKey();
                        }
                        if(testFact.getProductos().get(testQueso)-1 <= 0)
                            testFact.removeProducto(testQueso);
                        else
                            testFact.changeProducto(testQueso, EMP.getFacturas().get((Integer)entry.getKey()).getProductos().get(testQueso)-1);
                    }
                }
                updateFacturacionList();
            }
        }
        catch(Exception e){
            alert.setTitle("Warning");
            alert.setHeaderText("Datos Inválidos");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    protected void handlerFacturacionGuardarFacturaButton(ActionEvent event){
        try{
            if(!facturacionFacturasOptions.getSelectionModel().isEmpty() && !facturacionClientesOptions.getSelectionModel().isEmpty()){
                testFact.setCliente(EMP.getClientes().get(facturacionClientesOptions.getSelectionModel().getSelectedIndex()));
                EMP.addFactura(testFact);
                alertGood.setTitle("Information");
                alertGood.setHeaderText("Factura Guardada");
                alertGood.setContentText("La factura se ha guardado con éxito.");
                alertGood.showAndWait();
                updateMainMenuList();
            }
        }
        catch(Exception e){
            alert.setTitle("Warning");
            alert.setHeaderText("Datos Inválidos");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    protected void handlerMainMenuRemoveButton(ActionEvent event){
        try{
            if(!mainMenuList.getSelectionModel().isEmpty()){
                EMP.removeFactura(mainMenuList.getSelectionModel().getSelectedItem());
                updateFacturas();
            }
        }
        catch(Exception e){
            alert.setTitle("Warning");
            alert.setHeaderText("Datos Inválidos");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    protected void handlerMainMenuCalcularButton(ActionEvent event){
        try{
            alertGood.setTitle("Information");
            alertGood.setHeaderText("Ingresos Totales");
            alertGood.setContentText("$" + DF.format(EMP.ingresos()));
            alertGood.showAndWait();
        }
        catch(Exception e){
            alert.setTitle("Warning");
            alert.setHeaderText("Datos Inválidos");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    
    
    private void updateClientesList(){
        ArrayList<String> names = new ArrayList<>();
        for(Cliente cli: EMP.getClientes())
            names.add(cli.getNombre());
        clientesList.setItems(FXCollections.observableArrayList(names));
        facturacionClientesOptions.setItems(FXCollections.observableArrayList(names));
    }
    private void updateFacturacionList(){   //Añadir only if selection model != null
        if(!facturacionFacturasOptions.getSelectionModel().isEmpty()){
            if(testFact.getProductos().isEmpty()){
                facturacionList.getItems().clear();
                return;
            }
            ArrayList<String> prods = new ArrayList<>();
            for(Map.Entry e: testFact.getProductos().entrySet()){
                if(((Queso)e.getKey()) instanceof QuesoEsferico)
                    prods.add("Queso Esférico " + ((QuesoEsferico)e.getKey()).getRadio() + "cm\t\tX" + Integer.toString((Integer)e.getValue()) + "\t$" + ((QuesoEsferico)e.getKey()).costo());
                if(((Queso)e.getKey()) instanceof QuesoCilindrico){
                    if(((Queso)e.getKey()) instanceof QuesoCilindricoHueco)
                        prods.add("Queso Cilíndrico Hueco " + ((QuesoCilindricoHueco)e.getKey()).getRadio() + "x(" + ((QuesoCilindricoHueco)e.getKey()).getLongitud() + "-" + ((QuesoCilindricoHueco)e.getKey()).getRadioInterno() + ")cm\t\tX" + Integer.toString((Integer)e.getValue()) + "\t$" + ((QuesoCilindricoHueco)e.getKey()).costo());
                    else
                    prods.add("Queso Cilíndrico " + ((QuesoCilindrico)e.getKey()).getRadio() + "x" + ((QuesoCilindrico)e.getKey()).getLongitud() + "cm\t\tX" + Integer.toString((Integer)e.getValue()) + "\t$" + ((QuesoCilindrico)e.getKey()).costo());    
                }
            }
            facturacionList.setItems(FXCollections.observableArrayList(prods));
        }
    }
    private void updateFacturas(){
        ArrayList<Integer> ids = new ArrayList<>();
        for(Map.Entry entry: EMP.getFacturas().entrySet())
            ids.add((Integer)entry.getKey());
        facturacionFacturasOptions.setItems(FXCollections.observableArrayList(ids));
        mainMenuList.setItems(FXCollections.observableArrayList(ids));
    }
    private void updateMainMenuList(){
        ArrayList<Integer> facts = new ArrayList<>();
        for(Map.Entry e: EMP.getFacturas().entrySet())
            facts.add((Integer)e.getKey());
        mainMenuList.setItems(FXCollections.observableArrayList(facts));
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> quesosOpts = new ArrayList<>();
        quesosOpts.add("Queso Esférico");
        quesosOpts.add("Queso Cilíndrico");
        quesosOpts.add("Queso Cilíndrico Hueco");
        try {
            EMP.addCliente(new Cliente("Consumidor Final", "-", 999999999));
        } catch (myException ex) {
            Logger.getLogger(QuesosFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        updateClientesList();
        facturacionQuesosOptions.setItems(FXCollections.observableArrayList(quesosOpts));
        facturacionQuesosOptions.getSelectionModel().selectedItemProperty().addListener((Observable observable) -> {
            if(facturacionQuesosOptions.getSelectionModel().getSelectedItem().equals("Queso Esférico")){
                FacturacionQuesoEsfBox.setVisible(true);
                FacturacionQuesoCilBox.setVisible(false);
                FacturacionQuesoCilHueBox.setVisible(false);
            }
            if(facturacionQuesosOptions.getSelectionModel().getSelectedItem().equals("Queso Cilíndrico")){
                FacturacionQuesoEsfBox.setVisible(false);
                FacturacionQuesoCilBox.setVisible(true);
                FacturacionQuesoCilHueBox.setVisible(false);
            }
            if(facturacionQuesosOptions.getSelectionModel().getSelectedItem().equals("Queso Cilíndrico Hueco")){
                FacturacionQuesoEsfBox.setVisible(false);
                FacturacionQuesoCilBox.setVisible(false);
                FacturacionQuesoCilHueBox.setVisible(true);
            }
        });
        clientesList.getSelectionModel().selectedItemProperty().addListener((Observable observable) -> {
            if(clientesList.getSelectionModel().isEmpty()){
                clientesNombreLabel.setText("");
                clientesDireccionLabel.setText("");
                clientesTelefonoLabel.setText("");
            }
            else{
                testCliente = EMP.getClientes().get(clientesList.getSelectionModel().getSelectedIndex());
                clientesNombreLabel.setText(testCliente.getNombre());
                clientesDireccionLabel.setText(testCliente.getDireccion());
                clientesTelefonoLabel.setText(Integer.toString(testCliente.getTelefono()));
            }
        });
        facturacionFacturasOptions.getSelectionModel().selectedItemProperty().addListener((Observable observable) -> {
            if(facturacionFacturasOptions.getSelectionModel().isEmpty()){
                facturacionIDLabel.setText("");
                facturacionList.getItems().clear();
            }
            else{
                facturacionIDLabel.setText(Integer.toString(facturacionFacturasOptions.getSelectionModel().getSelectedItem()));
                testFact = EMP.getFacturas().get(facturacionFacturasOptions.getSelectionModel().getSelectedItem());
                updateFacturacionList();
            }
        });
        facturacionClientesOptions.getSelectionModel().selectedItemProperty().addListener((Observable observable) -> {
            if(facturacionClientesOptions.getSelectionModel().isEmpty()){
                facturacionClienteNombreLabel.setText("");
                facturacionClienteDireccionLabel.setText("");
                facturacionClienteTelefonoLabel.setText("");
            }
            else{
                testCliente = EMP.getClientes().get(facturacionClientesOptions.getSelectionModel().getSelectedIndex());
                facturacionClienteNombreLabel.setText(testCliente.getNombre());
                facturacionClienteDireccionLabel.setText(testCliente.getDireccion());
                facturacionClienteTelefonoLabel.setText(Integer.toString(testCliente.getTelefono()));
            }
        });
        mainMenuList.getSelectionModel().selectedItemProperty().addListener((Observable observable) -> {
            if(mainMenuList.getSelectionModel().isEmpty())
                mainMenuTxtArea.clear();
            else
                mainMenuTxtArea.setText(EMP.getFacturas().get(mainMenuList.getSelectionModel().getSelectedItem()).print());
        });
    }    
    
}
