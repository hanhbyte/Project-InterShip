package com.example.projectintership.service.com;

import com.example.projectintership.model.entity.Compound;
import com.example.projectintership.repository.com.ICompoundRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompoundService {

  @Autowired
  private ICompoundRepo iCompoundRepo;

  public void save(Compound compound){
    iCompoundRepo.save(compound);
  }
}
