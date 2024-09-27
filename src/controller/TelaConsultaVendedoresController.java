/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.Observable;
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
import model.classes.Vendedor;
import model.services.DepartamentoService;
import model.services.VendedorService;

/**
 * FXML Controller class
 *
 * @author carol
 */
public class TelaConsultaVendedoresController implements Initializable {

    @FXML
    Button btnNovo;

    @FXML
    Button btnExcluir;

    @FXML
    Button btnDptos;

    @FXML
    private ObservableList<Vendedor> listaTabela;

    @FXML
    TableView<Vendedor> tableViewVend;

    @FXML
    TableColumn<Vendedor, Integer> tableColumnCod;

    @FXML
    TableColumn<Vendedor, String> tableColumnNome;

    @FXML
    TableColumn<Vendedor, LocalDate> tableColumnData;

    @FXML
    TableColumn<Vendedor, Double> tableColumnSalario;

    @FXML
    TableColumn<Vendedor, Double> tableColumnDpto;

    List<Vendedor> listaVdd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableColumnCod.setCellValueFactory(new PropertyValueFactory<>("codVendedor"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nomeVendedor"));
        tableColumnData.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        tableColumnSalario.setCellValueFactory(new PropertyValueFactory<>("salarioBase"));
        tableColumnDpto.setCellValueFactory(new PropertyValueFactory<>("nomeDpto"));

        atualizaTabela();

        tableViewVend.setOnMouseClicked((evt) -> {
            if (evt.getClickCount() == 2 && evt.getButton().equals(MouseButton.PRIMARY)) {
                Vendedor vdd = tableViewVend.getSelectionModel().getSelectedItem();
                System.out.println(vdd.toString());

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaCadastroVendedor.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Cadastro de Vendedor");
                    stage.initModality(Modality.WINDOW_MODAL);
                    TelaCadastroVendedorController cont = loader.getController();
                    cont.setVndo(vdd);
                    stage.showAndWait();
                    atualizaTabela();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnNovo.setOnAction((evt) -> {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/view/TelaCadastroVendedor.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setTitle("Cadastro de Vendedores");
                stage.setScene(scene);
                stage.initOwner(btnNovo.getScene().getWindow());
                stage.initModality(Modality.WINDOW_MODAL);
                stage.showAndWait();
                atualizaTabela();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        btnExcluir.setOnAction((evt) -> {
            Vendedor vnddSelecionado = tableViewVend.getSelectionModel().getSelectedItem();
            if (vnddSelecionado != null) {
                Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                al.setTitle("Confirmação");
                al.setContentText(vnddSelecionado.getNomeVendedor() + " será excluído! Tem certeza? ");
                if (al.showAndWait().get() == ButtonType.OK) {
                    if (new VendedorService().excluir(vnddSelecionado)) {
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
        listaVdd = new VendedorService().getAll();
        listaTabela = FXCollections.observableArrayList(listaVdd);
        tableViewVend.setItems(listaTabela);
    }

}
