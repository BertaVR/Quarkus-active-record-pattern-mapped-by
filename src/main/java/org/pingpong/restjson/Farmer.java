package org.pingpong.restjson;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "farmer")
@JsonPropertyOrder({ "name", "location" })
public class Farmer extends PanacheEntityBase {

    @Id
    @Column(unique = true)
    // @JsonProperty(value = "supplier") //Copiado de David
    public String name;

    @Column
    public String location;

    @JsonIgnore // esto es para que no coja el set en el Json
    @OneToMany(mappedBy = "farmer")
    public Set<Fruit> fruits = new HashSet<Fruit>();

    public Farmer() {
    }

    public Farmer(String name, String location) {
        this.name = name;
        this.location = location;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.location;
    }

}
