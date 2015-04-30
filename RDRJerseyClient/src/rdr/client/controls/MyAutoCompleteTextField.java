/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdr.client.controls;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * *
 * An implementation of AutoCompleteTextField for Raw Material and Packaging
 * Material
 *
 * @author Admin
 * @param <T>
 */
public class MyAutoCompleteTextField<T> extends VBox {

    T selected = null;
    ObservableList<T> items;
    TextField textField = new TextField();
    ListView listView = new ListView();

    public MyAutoCompleteTextField(ObservableList<T> items) {
        this.items = items;
        this.getChildren().add(textField);
        this.getChildren().add(listView);

        //set margin of nodes
        VBox.setMargin(textField, new Insets(0, 10, 0, 10));
        VBox.setMargin(listView, new Insets(0, 10, -100, 10));

        listView.setItems(items);
        listView.setVisible(false);

        //set how to display items in listView
        listView.setCellFactory(new Callback<ListView<T>, ListCell<T>>() {
            @Override
            public ListCell<T> call(ListView<T> p) {
                final ListCell<T> cell = new ListCell<T>() {
                    @Override
                    protected void updateItem(T t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.toString());
                            setStyle("-fx-background-color: white");
                        } else {
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });

        configureTextField();
        configureListView();

    }

    /**
     * Display suggestions on top of the textField.
     *
     * @param v
     */
    public void displaySuggestionsOnTop(boolean v) {
        if (v) {
            VBox.setMargin(listView, new Insets(-180, 10, 40, 10));
        } else {
            VBox.setMargin(listView, new Insets(0, 10, -100, 10));
        }
    }

    private void configureTextField() {
        /*  textField.setOnMouseClicked(e -> {
         listView.setVisible(true);
         listView.getSelectionModel().clearSelection();

         });
         */
        textField.focusedProperty().addListener((ob, ov, nv) -> {
            if (nv) {
                listView.setVisible(true);
                listView.getSelectionModel().clearSelection();
            } else {
                // listView.setVisible(false);
                listView.getSelectionModel().clearSelection();
            }
        });

        //filter items whenever there is a change in text field value
        textField.textProperty().addListener((ob, ov, nv) -> {
            ObservableList<T> filteredItems = listFilterer(items, nv);
            listView.setItems(filteredItems);
        });
    }

    private ObservableList<T> listFilterer(ObservableList<T> items, String filter) {
        ObservableList<T> filteredItems = FXCollections.observableArrayList();
        for (T item : items) {
            if (item.toString().toLowerCase().contains(filter.toLowerCase())) {
                filteredItems.add(item);
            }
        }
        return filteredItems;

    }

    private void configureListView() {
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<T>() {
            @Override
            public void changed(ObservableValue<? extends T> observable,
                    T oldValue, T newValue) {

                Platform.runLater(() -> {
                    if (newValue != null) {
                        selected = newValue;
                        textField.setText(newValue.toString());
                        listView.setVisible(false);
                        listView.getSelectionModel().clearSelection();
                    }
                });
            }
        });

    }

    public TextField getTextField() {
        return textField;
    }

    public ListView getListView() {
        return listView;
    }

    public T getSelectedItem() {
        return selected;
    }

    public void setSelectedItem(T item) {
        this.selected = item;
    }

    /**
     * Clear all textField values except for listView items.
     */
    public void clearAll() {
        this.listView.getSelectionModel().clearSelection();
        this.textField.setText("");
        this.selected = null;
    }
}
