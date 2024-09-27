/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.classes.Departamento;
import model.exceptions.ValidacaoException;
import model.services.DepartamentoService;

/**
 * FXML Controller class
 *
 * @author carol
 */
public class TelaCadastroDepartamentoController implements Initializable {

    @FXML
    Button btnSalvar;

    @FXML
    Button btnSair;

    @FXML
    TextField txtCodigo;

    @FXML
    TextField txtNome;

    @FXML
    Label lblErrorNome;

    //atributo departamento usado para edicao
    private Departamento dpto;
    
    //metodo set para editar
    public void setDpto(Departamento dpto) {
        this.dpto = dpto;
        //informacoes para o campo da tela
        txtCodigo.setText(String.valueOf(dpto.getCodDpo()));
        txtNome.setText(dpto.getNomeDpo());
    }
    

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        btnSair.setOnAction((evt) -> {
            ((Stage) btnSair.getScene().getWindow()).close();

        });

        //botao salvar
        btnSalvar.setOnAction((evt) -> {
            try {
                ValidacaoException exc = new ValidacaoException("Erro validando! ");
                if (dpto == null) {
                    dpto = new Departamento();
                }
                //teste de todos os campos da tela
                if (txtNome.getText() == null || txtNome.getText().equals("")) {
                    exc.adicionarErro("Nome", "O campo não pode estar vazio");
                } else {
                    dpto.setNomeDpo(txtNome.getText());
                }

                if (!exc.getErrors().isEmpty()) {
                    throw exc;
                }
                //criado objeto dpto service, chamando metodo salvaratualzar e passando dpto como parametro, retorna um boolena como true ou false para se der certo ou nao
                if(new DepartamentoService().salvarOuAtualizar(dpto)){
                    //deu certo
                    //posso fechar ajanela
                   ((Stage)btnSalvar.getScene().getWindow()).close();
                }else{
                    //retorno do boolean deu false, erro no insert do banco
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro no INSERT");
                    alert.setContentText("Ocorreu um erro ao inserir o departamento");
                    alert.showAndWait();
                }
                
            } catch (ValidacaoException e) {
                //necessario criar metodo para mostrar os erros
                setErrorMessages(e.getErrors());
            }
        });
    }
    
    public void setErrorMessages(Map<String, String> errors){
        //pega os erros do campo nome
        Set<String> campos = errors.keySet();
        //mostra o erro no label
        lblErrorNome.setText(campos.contains("Nome") ? errors.get("Nome") : "");
    }
}
