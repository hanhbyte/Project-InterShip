package com.example.projectintership.service.product;

import com.example.projectintership.model.entity.Product;
import com.example.projectintership.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService extends IGeneralService<Product> {
  Iterable<Product> findAllByNameContaining(String name);
  Iterable<Product> findAllByPriceBetween(double lowestPrice, double highestPrice);
  Page<Product> findAll(Pageable pageable);
}
