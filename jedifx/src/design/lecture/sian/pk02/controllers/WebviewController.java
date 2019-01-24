package design.lecture.sian.pk02.controllers;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/*
 * ¿•∫‰ ∞°¿ÃµÂ
 * http://aristatait.tistory.com/44?category=676359
*/
public class WebviewController implements Initializable {
	

    @FXML private WebView webView;
    
    private WebEngine engine;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        engine = webView.getEngine();
        
    }
    
    public void btn1(ActionEvent event){
        engine.load("https://www.youtube.com/watch?v=fJ9rUzIMcZQ&start_radio=1&list=RDEMbHaAxpOZhcVmmF6I3y0siA");
    }
    
    public void btn2(ActionEvent event) throws IOException, SQLException{
    	Dbconnect db = new Dbconnect();
    	db.dbTest();

        engine.executeScript("window.location = \"http://www.naver.com//\";");
    }
 
    public void btn3(ActionEvent event){
        engine.loadContent("<html><body><h1>Hello World</h1></body></html>");
    }
 
    public void btn4(ActionEvent event){
        engine.reload();
    }


}
