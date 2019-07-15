package ru.job4j.entity.annotations;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "cars_hibernate")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "engine", foreignKey = @ForeignKey(name = "engineId"))
    private Engine engine;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "body", foreignKey = @ForeignKey(name = "bodyId"))
    private Body body;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transmission", foreignKey = @ForeignKey(name = "transmissionId"))
    private Transmission transmission;

    public Car(int id, String name, Engine engine, Body body, Transmission transmission) {
        this.id = id;
        this.name = name;
        this.engine = engine;
        this.body = body;
        this.transmission = transmission;
    }

    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id
                && Objects.equals(name, car.name)
                && Objects.equals(engine, car.engine)
                && Objects.equals(body, car.body)
                && Objects.equals(transmission, car.transmission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, engine, body, transmission);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Car{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", engine=").append(engine);
        sb.append(", body=").append(body);
        sb.append(", transmission=").append(transmission);
        sb.append('}');
        return sb.toString();
    }
}
