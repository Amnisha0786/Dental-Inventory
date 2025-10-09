package com.example.springbootapp.repository;

import com.example.springbootapp.model.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, Integer> {

    List<StockMovement> findByProductIdOrderByCreatedAtDesc(Integer productId);

    List<StockMovement> findByMovementType(String movementType);

    List<StockMovement> findAllByOrderByCreatedAtDesc();
}
