/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author 4g
 */
public class MenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button openFrmEtudiant;
    @FXML
    private VBox mContainer;
    @FXML
    private void etudiant(ActionEvent event)
    {
        Load("/vue/Etudiant.fxml");
    }
    
    private void Load(String L)
    {
        //System.out.println(""+L);
        Parent root=null;
        try {
            root=FXMLLoader.load(getClass().getResource(L));
        } catch (Exception e) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE,null,e);
        }
        
        mContainer.getChildren().add(root);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

