package org.pingpong.restjson;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name="fruit")
@JsonPropertyOrder({"name", "decription", "farmer_name"})
public class Fruit extends PanacheEntityBase {

    // los propiedades han de ser publicas para que jackson
    // pueda acceder a ellar por reflection

    @NotBlank
    @Id
    @Column(unique = true)
    public String name;

    @NotEmpty
    @Column
    public String description;
    @ManyToOne
    @JoinColumn(name= "farmer_name")
    public Farmer farmer;

    public Fruit() {
    }

    public Fruit(String name, String description, Farmer farmer) {
        this.name = name;
        this.description = description;
        this.farmer = farmer;

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return this.description;
    }

    public Farmer getFarmer() {
        return this.farmer;
    }

    /*
    // substituit getName por este metodo en
    // la serializacion a JSON
    @JsonGetter("name")
    public String nombre() {
        return "UMAMI";
    }*/

}