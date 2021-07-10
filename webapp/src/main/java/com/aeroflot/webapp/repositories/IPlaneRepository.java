package com.aeroflot.webapp.repositories;


import com.aeroflot.webapp.models.transportrelated.Plane;
import org.springframework.data.jpa.repository.JpaRepository;



public interface IPlaneRepository extends JpaRepository<Plane, Long> {

}
