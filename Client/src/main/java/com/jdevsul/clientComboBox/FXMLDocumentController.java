/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.clientComboBox;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author Eman-PC
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ColorPicker colorPicker;
    @FXML
    private ComboBox<String> fontNameComboBox;
    @FXML
    private ComboBox<String> fontSizeComboBox;

    String selectedColor;
    String selectedFontFamily;
    String selectedFontSize;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        colorPicker.setValue(Color.BLACK);
        colorPicker.setStyle("-fx-color-label-visible: false");
        colorPicker.setOnAction(new EventHandler() {
            @Override
            public void handle(Event t) {

                //hashCode() --> construct the 32bit integer representation of the color
                //toHexString() return #+6 symbols representation of the color
                selectedColor = toHexString(colorPicker.getValue().hashCode());
                System.out.println(selectedColor);
            }
        });

        //get list of installed font families
        ObservableList<String> fontFamilies = FXCollections.observableArrayList(Font.getFamilies());

        fontNameComboBox.setItems(fontFamilies);
        fontNameComboBox.setValue("Arial");
        fontNameComboBox.setOnAction(new EventHandler() {
            public void handle(Event t) {

                selectedFontFamily = fontNameComboBox.getValue();
                System.out.println(selectedFontFamily);
            }
        });

        ObservableList<String> fontSizes = FXCollections.observableArrayList("8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28");
        fontSizeComboBox.setItems(fontSizes);
        fontSizeComboBox.setValue("12");

        fontSizeComboBox.setOnAction(new EventHandler() {
            public void handle(Event t) {

                selectedFontSize = fontSizeComboBox.getValue();
                System.out.println(selectedFontSize);
            }
        });
    }

    public String toHexString(int pickedColor) {
        String color = "#" + Integer.toHexString(pickedColor).substring(0, 6);
        return color;
    }
}
