package com.servicosmsc.apitestes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servicosmsc.apitestes.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
