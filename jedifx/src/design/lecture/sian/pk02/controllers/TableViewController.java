package design.lecture.sian.pk02.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/*
 * JavaFX 23. TableView  가이드
 * http://aristatait.tistory.com/42?category=676359
*/
public class TableViewController implements Initializable {
	
    @FXML private TableView<Student> table;
    @FXML private TableColumn<Student, Integer> id;
    @FXML private TableColumn<Student, String> name;
    @FXML private TableColumn<Student, String> surname;
    @FXML private TableColumn<Student, Integer> age;
    
    public ObservableList<Student> list = FXCollections.observableArrayList(
                new Student(1, "지홍구", "Queen - Bohemian Rhapsody (Official Video) ", 22),
                new Student(2, "서미나", "surname2", 30),
                new Student(3, "지영규", "surname3", 15),
                new Student(4, "지서윤", "surname4", 65),
                new Student(5, "Tomy", "surname5", 44),
                new Student(6, "Jack", "surname6", 22)
            );
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
        age.setCellValueFactory(new PropertyValueFactory<Student, Integer>("age"));
        table.setItems(list);
        
    }


}
