package com.example.projectintership.service.product;

import com.example.projectintership.model.entity.Product;
import com.example.projectintership.repository.product.IProductRepo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

  @Autowired
  IProductRepo iProductRepo;

  @Override
  public Iterable<Product> findAll() {
    return iProductRepo.findAll();
  }

  @Override
  public Optional<Product> findById(Long id) {
    return iProductRepo.findById(id);
  }

  @Override
  public void save(Product product) {
    iProductRepo.save(product);
  }

  @Override
  public void remove(Long id) {
    iProductRepo.deleteById(id);
  }

  @Override
  public Iterable<Product> findAllByNameContaining(String name) {
    return iProductRepo.findAllByNameContaining(name);
  }

  @Override
  public Iterable<Product> findAllByPriceBetween(double lowestPrice, double highestPrice) {
    return iProductRepo.findAllByPriceBetween(lowestPrice, highestPrice);
  }

  @Override
  public Page<Product> findAll(Pageable pageable) {
    return iProductRepo.findAll(pageable);
  }
}
