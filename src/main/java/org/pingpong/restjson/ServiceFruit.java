package org.pingpong.restjson;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ServiceFruit {


    public ServiceFruit() { 
        // CDI
    }

    public Set<Fruit> list() {
        Stream<Fruit> fruits = Fruit.streamAll(); //esto es porque Panache te devuelve streams
        return fruits.collect(Collectors.toSet());
    }

    public void add(Fruit fruit) {
        Optional<Farmer> supplier = Farmer.find("name", fruit.farmer.name).firstResultOptional();
        if (supplier.isPresent()) {
            fruit.farmer = supplier.get();
        } else {
            fruit.farmer.persist();
        }
        fruit.persist();
    }

    public void remove(String name) {
        Optional<Fruit> fruit = Fruit.find("name", name).firstResultOptional();
        if (fruit.isPresent()) {
        // "name" entrecomillado es la columna en la que busco, el otro name es el parámetro.
        fruit.get().delete();}
    }

    public Optional<Fruit> getFruit(String name) {
        return name.isBlank()? Optional.ofNullable(null) : Fruit.find("name",name).firstResultOptional();
    }
}
