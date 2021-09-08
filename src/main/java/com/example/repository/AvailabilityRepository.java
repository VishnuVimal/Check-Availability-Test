package com.example.repository;

import com.example.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {

    @Query(value = "Select a.availability from Availability a join a.product p join a.location l where a.location.cityName= :cityName and a.product.name= :productName")
    Integer getAvailabilityCheck(@Param("cityName") String cityName, @Param("productName")String productName);
}
