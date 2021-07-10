package com.aeroflot.webapp.repositories;


import com.aeroflot.webapp.models.countryrelated.Country;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ICountryRepository extends JpaRepository<Country, Short> {

}
