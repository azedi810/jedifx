package design.lecture.sian.pk02.controllers;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/*
 * JavaFX 23. TableView  ∞°¿ÃµÂ
 * http://aristatait.tistory.com/42?category=676359
*/
public class Student  {
	

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty surname;
    private final SimpleIntegerProperty age;
    public Student(Integer id, String name, String surname, Integer age) {
        super();
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty (name);
        this.surname = new SimpleStringProperty (surname);
        this.age = new SimpleIntegerProperty(age);
    }
    public Integer getId() {
        return id.get();
    }
    public String getName() {
        return name.get();
    }
    public String getSurname() {
        return surname.get();
    }
    public Integer getAge() {
        return age.get();
    }
 




}
