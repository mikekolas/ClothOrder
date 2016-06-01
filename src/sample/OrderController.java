package sample;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.lang.String;

public class OrderController implements Initializable {

    private IntegerProperty index = new SimpleIntegerProperty();

    ObservableList<String> sizeList = FXCollections.observableArrayList("XS","S","M","L","XL","XXL");

    ObservableList<String> typeList = FXCollections.observableArrayList("T-shirt","Trousers","Jacket");

    ObservableList<String> colorList = FXCollections.observableArrayList("Black","Red","Blue","Green","Yellow","White");

    ObservableList<Clothes> clothesList;

    @FXML
    public TableView<Clothes> tblOrder;
    @FXML
    public ComboBox cbSize;
    @FXML
    public ComboBox cbType;
    @FXML
    public ComboBox cbColor;
    @FXML
    public TextField txtQty;

    @FXML
    public TableColumn<Clothes, String> typeCol=  new TableColumn("Type");
    @FXML
    public TableColumn<Clothes, String> sizeCol =  new TableColumn("Size");
    @FXML
    public TableColumn<Clothes, String> colorCol =  new TableColumn("Color");
    @FXML
    public TableColumn<Clothes, String> qtyCol =  new TableColumn("Quantity");


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cbType.setItems(typeList);
        cbSize.setItems(sizeList);
        cbColor.setItems(colorList);
        txtQty.setText("");

        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        colorCol.setCellValueFactory(new PropertyValueFactory<>("color"));

        clothesList = FXCollections.observableArrayList();

        //tblOrder.getColumns().addAll(typeCol,sizeCol,colorCol,qtyCol);
        //tblOrder.setItems(clothesList);

    }

    public void addItem(Event event) {
        clothesList.add(new Clothes(cbSize.getValue().toString(),cbType.getValue().toString(),cbColor.getValue().toString(),txtQty.getText()));

       tblOrder.setItems(clothesList);

        //tblOrder.getColumns().addAll(cbSize.getValue(),cbType.getValue(),cbColor.getValue(),txtQty.getText());

        //clothesList.add(new Clothes(cbSize.getValue().toString(),cbType.getValue().toString(),cbColor.getValue().toString(),txtQty.getText()));
        //clothesList.add(new Clothes("","","",txtQty.getText()));
    }

    public void delItem(Event event) {
        clothesList.remove(index.get());
        tblOrder.getSelectionModel().clearSelection();
    }
}
