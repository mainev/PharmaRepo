/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmd.client.controls;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 *
 * @author maine
 */
public class MyTreeView extends TreeView<String> {

    TreeItem<String> root = new TreeItem("Root");

    public MyTreeView() {
         TreeItem<String> itemReceiveNode = new TreeItem("Receive");
        TreeItem<String> itemTransferNode = new TreeItem("Issue");
        root.getChildren().addAll(  itemReceiveNode,itemTransferNode);

        this.setRoot(root);
        this.setShowRoot(false);
        
    }
}
