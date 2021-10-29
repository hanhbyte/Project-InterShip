package com.example.projectintership.repository.product;

import com.example.projectintership.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepo extends JpaRepository<Product, Long> {
  @Override
  Page<Product> findAll(Pageable pageable);

  Iterable<Product> findAllByNameContaining(String name);

  @Query("select a from Product a where a.price between ?1 and ?2")
  Iterable<Product> findAllByPriceBetween(double lowestPrice, double highestPrice);
}
