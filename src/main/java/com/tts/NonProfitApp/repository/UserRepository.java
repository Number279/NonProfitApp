package com.tts.NonProfitApp.repository;

import com.tts.NonProfitApp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByNonProfitName(String nonProfitName);

    List<User> findAllByZipcodeOrderByNonProfitNameDesc(int zipcode);
    List<User> findAllByCityOrderByNonProfitNameDesc(String city);
}
