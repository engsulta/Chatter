/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.server.mainui;

import com.jdevsul.server.graph.GraphsHandler;
import com.jdevsul.server.imp.ServerManagerImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sulta
 */
public class MainFXMLController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private JFXTabPane mainTabPane;
    @FXML
    private MaterialDesignIconView Power;
    @FXML
    private TextField PortNumber;
    @FXML
    private TextField Ip;
    @FXML
    private Tab showClientTab;
    @FXML
    private PieChart OnlineChart;
    @FXML
    private HBox client_info;
    @FXML
    private JFXTextField clientIDInput;
    @FXML
    private StackPane clientInfoContainer;
    @FXML
    private Text clientUserName;
    @FXML
    private Text clientEmail;
    @FXML
    private Text clientStatus;
    @FXML
    private PieChart GenderChart;
    @FXML
    private JFXButton renew;
    private GraphsHandler graphsHandler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        testGraphs();
        graphsHandler = new GraphsHandler();

        try {
            Registry reg = LocateRegistry.createRegistry(7474);
            reg.rebind("ChatService", new ServerManagerImpl());
            System.out.println("Server is up");

        } catch (RemoteException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void testGraphs() {
        final ObservableList<PieChart.Data> Onlinedetails = FXCollections.observableArrayList();
        final ObservableList<PieChart.Data> genderDetails = FXCollections.observableArrayList();

        ////onlinechart show data && GenderChart
        Onlinedetails.addAll(new PieChart.Data("Cost1", 20),
                new PieChart.Data("Cost2", 25),
                new PieChart.Data("Cost6", 15)
        );
        genderDetails.addAll(new PieChart.Data("Cost1", 20),
                new PieChart.Data("Cost2", 25),
                new PieChart.Data("Cost6", 15)
        );

        OnlineChart.setData(Onlinedetails);
        OnlineChart.setTitle("ChatPieChart");
        OnlineChart.setLegendSide(Side.RIGHT);
        OnlineChart.setLabelsVisible(true);
        OnlineChart.setStartAngle(90);
        OnlineChart.setAnimated(true);
        //----------------------------  -------------------------------//
        GenderChart.setData(Onlinedetails);
        GenderChart.setTitle("ChatPieChart");
        GenderChart.setLegendSide(Side.RIGHT);
        GenderChart.setLabelsVisible(true);
        GenderChart.setStartAngle(90);
        GenderChart.setAnimated(true);
    }

    private void initGraphs() {
        OnlineChart.setData(graphsHandler.getOnlineGraphStatistics());
        OnlineChart.setTitle("ChatPieChart");
        OnlineChart.setLegendSide(Side.RIGHT);
        OnlineChart.setLabelsVisible(true);
        OnlineChart.setStartAngle(90);
        OnlineChart.setAnimated(true);

        GenderChart.setData(graphsHandler.getGenderGraphStatistics());
        GenderChart.setTitle("ChatPieChart");
        GenderChart.setLegendSide(Side.RIGHT);
        GenderChart.setLabelsVisible(true);
        GenderChart.setStartAngle(90);
        GenderChart.setAnimated(true);

    }

    private void refreshGraphs() {
        OnlineChart.setData(graphsHandler.getOnlineGraphStatistics());
        GenderChart.setData(graphsHandler.getGenderGraphStatistics());
    }

    @FXML
    private void HandleOnPowerPressed(MouseEvent event) {

    }

    @FXML
    private void HandleOnShowPressed(MouseEvent event) {
    }

    @FXML
    private void HandleOnRenewPressed(MouseEvent event) {
    }

    @FXML
    private void HandleShowClient(ActionEvent event) {
    }

    @FXML
    private void HandleRenewAction(ActionEvent event) {
    }

    @FXML
    private void HandleStatisticsTab(Event event) {
    }

    @FXML
    private void handleMouseDragged(MouseEvent event) {
         loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        xoffset = loginStage.getX() - event.getScreenX();
        yoffset = loginStage.getY() - event.getScreenY();

    }

    @FXML
    private void handlePanePressed(MouseEvent event) {
        loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        xoffset = loginStage.getX() - event.getScreenX();
        yoffset = loginStage.getY() - event.getScreenY();

    }

}
