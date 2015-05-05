/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdr.client.controls;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 *
 * @author Admin
 */
public class MyTreeView extends TreeView<String> {

    TreeItem<String> root = new TreeItem("Root");

    public MyTreeView() {
        TreeItem<String> itemReportNode = new TreeItem<>("Report");
        TreeItem<String> itemTransferNode = new TreeItem("Transfer");
        TreeItem<String> itemReceiveNode = new TreeItem("Receive");
        TreeItem<String> itemStockcardNode = new TreeItem("Stockcard");
        root.getChildren().addAll( itemReportNode, itemReceiveNode, itemTransferNode, itemStockcardNode);

        this.setRoot(root);
        this.setShowRoot(false);
        
    }
}
