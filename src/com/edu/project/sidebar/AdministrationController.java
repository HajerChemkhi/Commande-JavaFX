/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.sidebar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author chaim
 */
public class AdministrationController implements Initializable {

    @FXML
    private BorderPane BorderPane;
    private AnchorPane anchoreview;
    @FXML
    private Button btncommande;
    @FXML
    private Button btnaddcommande;
    @FXML
    private Button btnmat;
    @FXML
    private Button btnddmat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        MainPage();
    }

    private void MainPage() {
        try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("../commande/commande.fxml"));
            BorderPane.setCenter(view);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void btncommande(ActionEvent event) {
        try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("../commande/commande.fxml"));
            BorderPane.setCenter(view);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnaddcommande(ActionEvent event) {
        try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("../commande/commandeadd.fxml"));
            BorderPane.setCenter(view);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnmat(ActionEvent event) {
        try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("../EtatCommande/EtatCommandeadd.fxml"));
            BorderPane.setCenter(view);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnshowmat(ActionEvent event) {
        try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("../etatetat.fxml"));
            BorderPane.setCenter(view);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
