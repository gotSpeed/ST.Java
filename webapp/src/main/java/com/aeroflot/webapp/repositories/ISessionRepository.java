package com.aeroflot.webapp.repositories;


import com.aeroflot.webapp.models.personrelated.Session;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ISessionRepository extends JpaRepository<Session, String> {

}
