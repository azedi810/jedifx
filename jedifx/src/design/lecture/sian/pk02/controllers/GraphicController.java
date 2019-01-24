package design.lecture.sian.pk02.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;


/*
 * 	참고 사이트 : 
	http://aristatait.tistory.com/37?category=676359
*/
public class GraphicController implements Initializable {
	
    final MyNumber myNum = new MyNumber();
    
    @FXML
    private Label lblStatus;
    
    @FXML
    private ProgressBar pb;
    
    @FXML
    private ProgressIndicator pi;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myNum.setNumber(0);
        myNum.numberProperty().addListener(new ChangeListener<Object>() {
 
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                lblStatus.setText(new Double(myNum.getNumber()).toString());
                pb.progressProperty().bind(myNum.numberProperty());
                pi.progressProperty().bind(myNum.numberProperty());
            }
        });
        
    }
 
    public void BtnClick(ActionEvent event) {
        myNum.setNumber(myNum.getNumber() - 0.1);
    }
    
    public void Btn2Click(ActionEvent event) {
        myNum.setNumber(myNum.getNumber() + 0.1);
    }


	public class MyNumber {
	    
	    private DoubleProperty number;
	 
	    public double getNumber() {
	        if(number != null){
	            return number.get();
	        }else{
	            return 0;
	        }
	        
	    }
	 
	    public void setNumber(double number) {
	        this.numberProperty().set(number); 
	    }
	    
	    public final DoubleProperty numberProperty(){
	        if(number == null){
	            number = new SimpleDoubleProperty(0);
	        }
	        return number;
	    }
	    
	}
}




