/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdr.client;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import rdr.client.controls.MyNotifications;
import rdr.client.domain.PackagingMaterialStockCard;
import rdr.client.domain.RawMaterialStockCard;
import rdr.client.service.ReceivedPackagingMaterialService;
import rdr.client.service.ReceivedRawMaterialService;
import rdr.client.utils.DateConverter;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ReportController implements Initializable {

    //toggle group for radio buttons
    final ToggleGroup group = new ToggleGroup();

    @FXML
    DatePicker datePickerStartDate;
    @FXML
    DatePicker datePickerEndDate;
    @FXML
    RadioButton radioButtonRawMaterial;
    @FXML
    RadioButton radioButtonPackagingMaterial;


    /**
     * Initializes the controller class.
     */
    private static final ReceivedRawMaterialService receivedRmService = new ReceivedRawMaterialService();
    private static final ReceivedPackagingMaterialService receivedPmService = new ReceivedPackagingMaterialService();
    private final DateConverter dc = new DateConverter();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setRadioButtonToggleGroup();
    }

    private void setRadioButtonToggleGroup() {
        radioButtonRawMaterial.setToggleGroup(group);
        radioButtonPackagingMaterial.setToggleGroup(group);
        radioButtonRawMaterial.setSelected(true);
    }

    @FXML
    private void handlePreviewButton() throws JRException {
        if (datePickerStartDate.getValue() != null && datePickerEndDate.getValue() != null) {
            Date startDate = dc.convertLocalDateToDate(datePickerStartDate.getValue());
            Date endDate = dc.convertLocalDateToDate(datePickerEndDate.getValue());

            //generate reports for raw material only
            if (radioButtonRawMaterial.isSelected()) {

                List<RawMaterialStockCard> rmsList = receivedRmService.getReceivedRmBetweenDates(startDate, endDate);

                if (!rmsList.isEmpty()) {
                    
//                    rmsList.forEach(s->{
//                        System.out.println("INVENTORY 2: "+s.getInventory2());
//                        System.out.println("ISSUANCE: "+s.getIssuance());
//                        System.out.println("INVENTORY 1: "+s.getInventory1());
//                        System.out.println("UNIT COST: "+s.getUnitCost());
//                        System.out.println("TOTAL COST: "+s.getTotalCost());  
//                        System.out.println();
//                    });
                  //  MyNotifications.displayInformation("Generating reports for raw material.");
                   
                    generateRmReports(rmsList, startDate, endDate);
                  

                } else {
                    MyNotifications.displayError("No results found.");
                   
                }
                
            } 

            //generate reports for packaging material only
            else {
                List<PackagingMaterialStockCard> pmsList = receivedPmService.getReceivedPmBetweenDates(startDate, endDate);

                if (!pmsList.isEmpty()) {
                //    MyNotifications.displayInformation("Generating reports for packaging material.");
                    generatePmReports(pmsList, startDate, endDate);
                  
                }
                else
                       MyNotifications.displayError("No results found.");
                   
               
            }
            
        } else {
             MyNotifications.displayWarning("Please provide a period.");
        }

    }

    /**
     * Generates a jasper report of raw material stock card.
     *
     * @param rmsList
     * @throws JRException
     */
    private void generateRmReports(List<RawMaterialStockCard> rmsList, Date startDate, Date endDate) throws JRException {
        //parameters for report
        Map<String, Object> params = new HashMap();
        params.put("start_date", new SimpleDateFormat("MMM dd yyyy").format(startDate));
        params.put("end_date", new SimpleDateFormat("MMM dd yyyy").format(endDate));

        JasperPrint jasperPrint = JasperFillManager.fillReport("reports/rm_stockcard.jasper",
                params, new JRBeanCollectionDataSource(rmsList));

        JasperViewer.viewReport(jasperPrint, false);
    }

    /**
     * Generates a jasper report of packaging material stock card list.
     *
     * @param pmsList
     * @throws JRException
     */
    private void generatePmReports(List<PackagingMaterialStockCard> pmsList, Date startDate, Date endDate) throws JRException {
        //parameters for report
        Map<String, Object> params = new HashMap();
        params.put("start_date", new SimpleDateFormat("MMM dd yyyy").format(startDate));
        params.put("end_date", new SimpleDateFormat("MMM dd yyyy").format(endDate));

        JasperPrint jasperPrint = JasperFillManager.fillReport("reports/pm_stockcard.jasper",
                params, new JRBeanCollectionDataSource(pmsList));

        JasperViewer.viewReport(jasperPrint, false);
    }
}
