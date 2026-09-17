package com.famrut.farmer_management_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.famrut.farmer_management_api.entity.Language;

@Repository
public interface LanguageRepository
        extends JpaRepository<Language, Long> {

    // write these two derived queries yourself

    boolean existsByName(String name);

    List<Language> findAllByActiveTrueOrderByNameAsc();

    boolean existsByCodeIgnoreCase(String code);

    Optional<Language> findById(Long id);

}