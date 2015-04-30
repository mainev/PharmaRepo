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
//        TreeItem<String> itemRawMatNode = new TreeItem<>("Raw Material");
//        TreeItem<String> itemPackMatNode = new TreeItem<>("Packaging Material");
        TreeItem<String> itemReportNode = new TreeItem<>("Report");
        TreeItem<String> itemTransferNode = new TreeItem("Transfer");
        TreeItem<String> itemReceiveNode = new TreeItem("Receive");
        TreeItem<String> itemStockcardNode = new TreeItem("Stockcard");
        root.getChildren().addAll( itemReportNode, itemReceiveNode, itemTransferNode, itemStockcardNode);

        this.setRoot(root);
        this.setShowRoot(false);

        //set item for raw mat node
//        TreeItem<String> itemReceiveRm = new TreeItem("Receive");
//        TreeItem<String> itemTransferRm = new TreeItem("Transfer");
//        TreeItem<String> itemRmStockcard = new TreeItem("Stockcard");
//        itemRawMatNode.getChildren().addAll(itemReceiveRm, itemTransferRm, itemRmStockcard);
//
//        //set item for pack mat node
//        TreeItem<String> itemReceivePm = new TreeItem("Receive");
//        TreeItem<String> itemTransferPm = new TreeItem("Transfer");
//        TreeItem<String> itemPmStockcard = new TreeItem("Stockcard");
//        itemPackMatNode.getChildren().addAll(itemReceivePm, itemTransferPm, itemPmStockcard);
//        
        //set item for transfer node
        
    }
}
