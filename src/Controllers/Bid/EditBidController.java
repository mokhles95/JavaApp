/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Bid;

import Entities.Bid;
import Entities.Project;
import Services.Implementation.BidService;
import Tools.AlertHelper;
import Tools.CurrentDate;
import Tools.SwitchView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class EditBidController implements Initializable {

    BidService bidService = BidService.getInstance();

    @FXML
    private JFXButton cancelUpdate;
    @FXML
    private JFXButton updateBidButton;
    @FXML
    private HBox containerHBox;

    Label label = new Label();
    @FXML
    private JFXSlider sliderTime;
    @FXML
    private JFXSlider sliderRate;

    private int bidId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void loadData(int bidId) {
        this.bidId = bidId;
        setSliderRate(bidId);
        setSliderTime(bidId);

    }

    private void setSliderRate(int bidId) {
        Bid bid = bidService.findById(bidId);
        Project project = bidService.findProjectById(bid.getProjectId());
        int minBudgetSlider = project.getMinBudget();
        int maxBudgetSlider = project.getMaxBudget();
        sliderRate.setMin(minBudgetSlider);
        sliderRate.setMax(maxBudgetSlider);
        sliderRate.setMajorTickUnit(1);
        sliderRate.setMinorTickCount(0);
        sliderRate.setShowTickMarks(false);
        sliderRate.setShowTickLabels(false);
        sliderRate.setMinHeight(Slider.USE_PREF_SIZE);
        sliderRate.setValue(bid.getMinimalRate());

    }

    private void setSliderTime(int bidId) {
        Bid bid = bidService.findById(bidId);
        Project project = bidService.findProjectById(bid.getProjectId());
        long maxSlider = this.getNumberOfDays(project.getValidityPeriod().toString());

        sliderTime.setMin(1);
        sliderTime.setMax(maxSlider);
        sliderTime.setMajorTickUnit(1);
        sliderTime.setMinorTickCount(0);
        sliderTime.setShowTickMarks(false);
        sliderTime.setShowTickLabels(false);
        sliderTime.setMinHeight(Slider.USE_PREF_SIZE);
        sliderTime.setValue(bid.getDeliveryTime());
    }

    @FXML
    private void cancelUpdate(ActionEvent event) {
        SwitchView.getInstance().closeWindow(cancelUpdate);
    }

    @FXML
    private void updateBid(ActionEvent event) {
        Window owner = updateBidButton.getScene().getWindow();

        int updateMinRat = (int) sliderRate.getValue();
        int updateDeliveryTime = (int) sliderTime.getValue();

        System.out.println(sliderTime.getValue());
        if (bidService.updateBid(this.bidId, updateMinRat, updateDeliveryTime)) {
            SwitchView.getInstance().closeWindow(updateBidButton); 
                    
        } else {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "Error");
        }

    }

    private long getNumberOfDays(String date) {
        long diff = 0;
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String inputString1 = date;
        String inputString2 = CurrentDate.Date();

        try {
            Date date2 = myFormat.parse(inputString2);
            Date date1 = myFormat.parse(inputString1);
            diff = date1.getTime() - date2.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (diff / (60 * 60 * 24 * 1000)) % 365;

    }

}
