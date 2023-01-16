package com.example.backend.computercomponents.repository;

import com.example.backend.computercomponents.dto.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Long> {

}
