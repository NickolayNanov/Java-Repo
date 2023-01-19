package com.example.carspace.repositories;

import com.example.carspace.authentication.User;
import com.example.carspace.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface CarRepository extends CrudRepository<Car, Long> {
    @Query("SELECT c FROM Car c")
    public Collection<Car> listAll();

    @Query("SELECT c FROM Car c WHERE c.id = ?1")
    public Car findById(long id);
}
