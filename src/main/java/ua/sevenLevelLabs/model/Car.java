package ua.sevenLevelLabs.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Tatiana Marchuk
 *         16.03.16 : 10:29
 */

@Entity
@Table(name = "car")
public class Car implements Serializable {

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private int id;
    @Column
    private String model;
    @Column
    private String name;
    @Column
    private int year;
    @Column
    private int price;


    public Car(String model, String name, int year, int price) {
        this.model = model;
        this.name = name;
        this.year = year;
        this.price = price;
    }

    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        if (getYear() != car.getYear()) return false;
        if (getPrice() != car.getPrice()) return false;
        if (getModel() != null ? !getModel().equals(car.getModel()) : car.getModel() != null) return false;
        return getName() != null ? getName().equals(car.getName()) : car.getName() == null;

    }

    @Override
    public int hashCode() {
        int result = getModel() != null ? getModel().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + getYear();
        result = 31 * result + getPrice();
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "| model='" + model + '\'' +
                "| name='" + name + '\'' +
                "| year=" + year +
                "| price=" + price +
                '}';
    }
}
