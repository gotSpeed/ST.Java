package com.aeroflot.webapp.repositories;


import com.aeroflot.webapp.models.personrelated.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface IUserRepository extends JpaRepository<User, Long> {

}
