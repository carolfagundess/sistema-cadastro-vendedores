/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.classes.Departamento;
import model.classes.Vendedor;
import model.exceptions.ValidacaoException;
import model.services.DepartamentoService;
import model.services.VendedorService;

/**
 * FXML Controller class
 *
 * @author carol
 */
public class TelaCadastroVendedorController implements Initializable {

    @FXML
    Button btnSalvar;

    @FXML
    Button btnSair;

    @FXML
    TextField txtCodigo;

    @FXML
    TextField txtNome;

    @FXML
    DatePicker dtPickerNasc;

    @FXML
    TextField txtSalario;

    @FXML
    Label lblErrorNome;

    @FXML
    Label lblErroData;

    @FXML
    Label lblErrosSalario;

    @FXML
    Label lblErroDepartamento;

    @FXML
    ComboBox<Departamento> cbDpto;

    private Vendedor vndo;
    List<Departamento> listaDpto;

    //atributo vendedor usado para edicao
    public void setVndo(Vendedor vndo) {
        this.vndo = vndo;
        txtNome.setText(this.vndo.getNomeVendedor());
        txtCodigo.setText(String.valueOf(vndo.getCodVendedor()));
        dtPickerNasc.setValue(vndo.getDataNascimento());
        txtSalario.setText(String.valueOf(vndo.getSalarioBase()));
        cbDpto.setValue(vndo.getDpo());
    }

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        btnSair.setOnAction((evt) -> {
            ((Stage) btnSair.getScene().getWindow()).close();

        });

        listaDpto = new DepartamentoService().getAll();
        //ObersvableList
        //vinculao a lista observavel om a table view
        ObservableList<Departamento> listaObs = FXCollections.observableArrayList(listaDpto);
        cbDpto.setItems(listaObs);

        //botao salvar
        btnSalvar.setOnAction((evt) -> {
            try {
                ValidacaoException exc = new ValidacaoException("Erro validando!");
                if (vndo == null) {
                    vndo = new Vendedor();
                }
                //teste nome
                if (txtNome.getText() == null || txtNome.getText().equals("")) {
                    exc.adicionarErro("Nome", "O campo não pode estar vazio");
                } else {
                    vndo.setNomeVendedor(txtNome.getText());
                }
                //teste salario
                if (txtSalario.getText() == null || txtSalario.getText().equals("")) {
                    exc.adicionarErro("Salario", "O campo não pode estar vazio");
                } else {
                    try {
                        double salario = Double.valueOf(txtSalario.getText());
                        vndo.setSalarioBase(salario);
                    } catch (Exception e) {
                        exc.adicionarErro("Salario", "Informe um valor válido");
                    }
                }
                //teste data nacimento
                if (dtPickerNasc.getValue() == null) {
                    exc.adicionarErro("Data", "Informe uma data válida");
                }else{
                    vndo.setDataNascimento(dtPickerNasc.getValue());
                }
                //teste combo box departamentos
                if (cbDpto.getValue() == null) {
                    exc.adicionarErro("Departamento", "Informe um departamento");
                }else{
                    vndo.setDpo(cbDpto.getValue());
                }

                if (!exc.getErrors().isEmpty()) {
                    throw exc;
                }
                if (new VendedorService().salvarOuEditar(vndo)) {
                    ((Stage) btnSalvar.getScene().getWindow()).close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro no INSERT");
                    alert.setContentText("Ocorreu um erro ao inserir o VENDEDOR");
                    alert.showAndWait();
                }
            } catch (ValidacaoException e) {
                setErrorMessages(e.getErrors());
            }
        });
    }

    public void setErrorMessages(Map<String, String> errors) {
        //pega os erros do campo nome
        Set<String> campos = errors.keySet();
        //mostra o erro no label
        lblErrorNome.setText(campos.contains("Nome") ? errors.get("Nome") : "");
        lblErrosSalario.setText(campos.contains("Salario") ? errors.get("Salario") : "");
        lblErroData.setText(campos.contains("Data") ? errors.get("Data") : "");
        lblErroDepartamento.setText(campos.contains("Departamento") ? errors.get("Departamento") : "");
    }
}
