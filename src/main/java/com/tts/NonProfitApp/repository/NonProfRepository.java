package com.tts.NonProfitApp.repository;

import com.tts.NonProfitApp.model.NonProf;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NonProfRepository extends CrudRepository<NonProf, Long> {
    NonProf findByNonProfitName(String nonProfitName);

    List<NonProf> findAllByZipcodeOrderByNonProfitNameDesc(int zipcode);
}
