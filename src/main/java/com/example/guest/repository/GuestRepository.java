package com.example.guest.repository;

import com.example.guest.model.entity.Guest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface GuestRepository extends CrudRepository<Guest, BigInteger> {

}
