/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author carol
 */
public class TelaPrincipalController implements Initializable {

    @FXML
    Button btnVendedores;

    @FXML
    Button btnDpto;

    @FXML
    Button btnSair;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //abrido a tela apartir o botao departamento
        btnDpto.setOnAction((evt) -> {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/view/TelaConsultaDepartamentos.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setTitle("Consulta de departamentos");
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //fechando o programa
        btnSair.setOnAction((evt) -> {
            Stage stage = (Stage) btnSair.getScene().getWindow();
            stage.close();
        });

        btnVendedores.setOnAction((evt) -> {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/view/TelaConsultaVendedores.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setTitle("COnsulta de Vendedores");
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
