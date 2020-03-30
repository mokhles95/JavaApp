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
import Tools.UserSessions;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author asus
 */
public class AddBidController {
    

    private JFXTextField reference;
    private JFXTextField libelle;
    private JFXButton ajouter;
    BidService bidService = BidService.getInstance();
    @FXML
    private JFXSlider sliderTime;
    @FXML
    private JFXSlider sliderRate;
    @FXML
    private JFXButton cancelAdd;
    @FXML
    private JFXButton addBidButton;

    private int projectId;
    @FXML
  
    private HBox containerHBox;
    
    public void makeBid(ActionEvent event) throws IOException {
        
        Window owner = addBidButton.getScene().getWindow();

        int updateMinRat = (int) sliderRate.getValue();
        int updateDeliveryTime = (int) sliderTime.getValue();

        Bid bid = new Bid(updateMinRat,updateDeliveryTime,UserSessions.getInstance().getLoggedInUser().getId(),projectId);
        if (bidService.addBid(bid)) {
            SwitchView.getInstance().closeWindow(addBidButton); 
        } else {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "Error");
        }

    }
        public void loadData(int projectId) {
        this.projectId = projectId;
        setSliderRate(projectId);
        setSliderTime(projectId);

    }

        private void setSliderRate(int projectId) {
        Project project = bidService.findProjectById(projectId);
        int minBudgetSlider = project.getMinBudget();
        int maxBudgetSlider = project.getMaxBudget();
        sliderRate.setMin(minBudgetSlider);
        sliderRate.setMax(maxBudgetSlider);
        sliderRate.setMajorTickUnit(1);
        sliderRate.setMinorTickCount(0);
        sliderRate.setShowTickMarks(false);
        sliderRate.setShowTickLabels(false);
        sliderRate.setMinHeight(Slider.USE_PREF_SIZE);

    }

    private void setSliderTime(int projectId) {
        Project project = bidService.findProjectById(projectId);
        long maxSlider = this.getNumberOfDays(project.getValidityPeriod().toString());
        sliderTime.setMin(1);
        sliderTime.setMax(maxSlider);
        sliderTime.setMajorTickUnit(1);
        sliderTime.setMinorTickCount(0);
        sliderTime.setShowTickMarks(false);
        sliderTime.setShowTickLabels(false);
        sliderTime.setMinHeight(Slider.USE_PREF_SIZE);
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

    @FXML
    private void cancelAdd(ActionEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.close();
    }


}
