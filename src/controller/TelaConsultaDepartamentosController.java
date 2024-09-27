/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.classes.Departamento;
import model.services.DepartamentoService;

/**
 * FXML Controller class
 *
 * @author carol
 */
public class TelaConsultaDepartamentosController implements Initializable {

    @FXML
    Button btnNovo;

    @FXML
    Button btnExcluir;

    @FXML
    TableView<Departamento> tableViewDpto;

    @FXML
    TableColumn<Departamento, Integer> tableColumnCod;

    @FXML
    TableColumn<Departamento, String> tableColumnNome;

    List<Departamento> listaDpto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //configurano a colunas a tabela
        //informano ao tableColumn Cod que ele deve buscar o get do atributo coDpto da classe departamento
        tableColumnCod.setCellValueFactory(new PropertyValueFactory<>("codDpo"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nomeDpo"));

        //chamano o metodo atualiza tabela
        atualizaTabela();

        tableViewDpto.setOnMouseClicked((evt) -> {
            if (evt.getClickCount() == 2 && evt.getButton().equals(MouseButton.PRIMARY)) {
                //pega o dpto selecionado pelo usuario
                Departamento dpto = tableViewDpto.getSelectionModel().getSelectedItem();
                System.out.println(dpto);

                //criando a tela de cadastro e passando o dpto como parametro
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaCadastroDepartamento.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Cadastro de Departamento");
                    stage.initModality(Modality.WINDOW_MODAL);
                    //aqui iremos chamar o controller//pegamos o controller da tela com getController
                    TelaCadastroDepartamentoController cont = loader.getController();
                    cont.setDpto(dpto);
                    stage.showAndWait();
                    atualizaTabela();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        //criando o metodo do botao novo
        btnNovo.setOnAction((t) -> {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/view/TelaCadastroDepartamento.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setTitle("Cadastro de Departamentos");
                stage.setScene(scene);
                //criando a tela como modal
                //quem é o dono da tela que vou exibir
                stage.initOwner(btnNovo.getScene().getWindow());
                //trava a tela para que nao seja mais clicavel
                stage.initModality(Modality.WINDOW_MODAL);
                stage.showAndWait();
                atualizaTabela();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        btnExcluir.setOnAction((evt) -> {
            Departamento dptoSelecionado = tableViewDpto.getSelectionModel().getSelectedItem();
            if (dptoSelecionado != null) {
                Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                al.setTitle("Confirmação");
                al.setContentText(dptoSelecionado.getNomeDpo() + " será excluído! Tem certeza? ");
                if (al.showAndWait().get() == ButtonType.OK) {
                    //chamando o excluir do service passando o dpto selecionado
                    if (new DepartamentoService().excluir(dptoSelecionado)) {
                        Alert mens = new Alert(Alert.AlertType.INFORMATION);
                        mens.setTitle("Excluído");
                        mens.setContentText("Registro excluído com sucesso");
                        mens.showAndWait();
                        atualizaTabela();
                    }
                }
            }
        });

    }

    private void atualizaTabela() {
        //buscar os dados no banco e na tabela departamento
        listaDpto = new DepartamentoService().getAll();
        //ObersvableList
        //vinculao a lista observavel om a table view
        ObservableList<Departamento> listaObs = FXCollections.observableArrayList(listaDpto);
        tableViewDpto.setItems(listaObs);
    }

}
