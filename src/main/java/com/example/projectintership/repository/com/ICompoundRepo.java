package com.example.projectintership.repository.com;

import com.example.projectintership.model.entity.Compound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompoundRepo extends JpaRepository<Compound, Long> {
}
