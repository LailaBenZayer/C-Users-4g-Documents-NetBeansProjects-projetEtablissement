/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classe.Employe;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.EmployeService;

/**
 * FXML Controller class
 *
 * @author 4g
 */
public class LoginController implements Initializable {

    
    @FXML
    private JFXTextField emailField;
    
    @FXML 
    private void EmployeLogin(ActionEvent event) throws Exception
    {
        EmployeService es=new EmployeService();
        Employe emp=es.findByEmail(emailField.getText());
        if(emp != null)
        {
            Preferences userconfig=Preferences.userRoot();
            userconfig.put("userId", emp.getEtablissement().getNom());
//            ((Node) (event.getSource())).getScene().ge
            Parent parent=FXMLLoader.load(getClass().getResource("/vue/Menu.fxml"));
            Stage stage=new Stage();
            Scene scen=new Scene(parent);
            stage.setScene(scen);
            stage.setTitle("Etablissement");
            stage.show();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
