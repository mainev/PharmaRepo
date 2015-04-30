/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdr.client.controls;

import java.util.Objects;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import rdr.client.entity.TransferredPackagingMaterial;

/**
 *
 * @author Admin
 */
public class MyListView extends ListView<TransferredPackagingMaterial> {

    //ObservableList<TransferredPm> itemList = getItems();
    public MyListView() {

          //.bindContentBidirectional(this.getItems(),itemList);
    }

    public boolean isIn(TransferredPackagingMaterial tpm) {

        for (TransferredPackagingMaterial item : getItems()) {
            if (Objects.equals(tpm.getId(), item.getId())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Receives the available item.
     *
     * @param tpm - available item
     */
    public void addItem(TransferredPackagingMaterial tpm) {
        if (!getItems().isEmpty()) {
            if (this.isIn(tpm)) {
                TransferredPackagingMaterial temp = this.findItem(tpm.getId());
                temp.addQuantity(tpm.getQuantity());

                //----
                getItems().remove(temp);
                getItems().add(temp);
                return;
            }

        }

        getItems().add(tpm);

    }

    public TransferredPackagingMaterial findItem(int id) {
        for (TransferredPackagingMaterial item : getItems()) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Transfer the selected packaging material to outgoing packaging material
     * list.
     *
     * @param item - packaging material item
     * @param lv - outgoing packaging material list
     */
    public void transferItem(TransferredPackagingMaterial item, MyListView lv) {

        lv.addItem(item);
        getItems().remove(item);
    }

    public void partialTransferItem(TransferredPackagingMaterial availableItem, int qty, MyListView lv) {

        if (qty == availableItem.getQuantity()) {
            transferItem(availableItem, lv);
        } else if (qty < availableItem.getQuantity()) {
            TransferredPackagingMaterial outgoingItem = new TransferredPackagingMaterial();
            outgoingItem.copy(availableItem);
            int newQty = availableItem.getQuantity() - qty;
            availableItem.setQuantity(newQty);

            //---
            getItems().remove(availableItem);
            getItems().add(availableItem);
            outgoingItem.setQuantity(qty);
            lv.addItem(outgoingItem);
        }

    }

    public void transferItems(ObservableList<TransferredPackagingMaterial> availableItems, MyListView lv) {
        for (TransferredPackagingMaterial item : availableItems) {
            lv.addItem(item);
        }
        getItems().removeAll(availableItems);
    }

    public void returnOutgoingItem(TransferredPackagingMaterial outgoingItem, MyListView lv) {

        lv.addItem(outgoingItem);
        getItems().remove(outgoingItem);
    }

    public void returnOutgoingItems(ObservableList<TransferredPackagingMaterial> outgoingItems, MyListView lv) {
        for (TransferredPackagingMaterial item : outgoingItems) {
            lv.addItem(item);
        }
        getItems().removeAll(outgoingItems);
    }

}
