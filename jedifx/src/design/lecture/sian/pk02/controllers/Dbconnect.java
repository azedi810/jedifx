package design.lecture.sian.pk02.controllers;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class Dbconnect {
	
	private SqlMapClient sqlMap;
	
	 public Dbconnect() {
        
        try {
        	System.out.println(" SqlMapConfig.xml  reading.............  " );
        	Reader reader = Resources.getResourceAsReader("./config/ibatis/test/SqlMapConfig.xml");
            sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
            

        } catch (IOException e) {
            throw new RuntimeException("  ###  IOException  vv 에러 1 :### " + e, e);
        } catch (Exception e){
            //throw new RuntimeException("  ### Exception 에러 vv  2 : ###" + e, e);
            e.printStackTrace();
        }
	}
	
	public void dbTest() throws IOException, SQLException {

		
		/*
		 * FXMLLoader loader = new FXMLLoader();
		 * loader.setLocation(Dbconnect.class.getResource("SqlMapConfig.xml"));
		 * BorderPane mainItems = loader.load();
		 */
		
        List list = sqlMap.queryForList("MyLottoSql.getList","a");
        for(int i=0; i < list.size(); i++) {
        	System.out.println("--> " + list.get(i) );
        }

	}

}
