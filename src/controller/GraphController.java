/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author 4g
 */
public class GraphController implements Initializable {

    
    @FXML
    private CategoryAxis xAxes = new CategoryAxis();

    @FXML
    private NumberAxis yAxes = new NumberAxis();
    
    @FXML
    private BarChart<String, Number> mChart = new BarChart<String, Number>(xAxes, yAxes);
    
    /**
     * Initializes the controller class.
     */
    
    public void setChart()
    {
        mChart.getData().clear();
        
        XYChart.Series chartSeries = new XYChart.Series();
        
        chartSeries.getData().add(new XYChart.Data("de", 120));
        chartSeries.getData().add(new XYChart.Data("zee", 60));
        chartSeries.getData().add(new XYChart.Data("ae", 90));
        
        mChart.getData().addAll(chartSeries);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
