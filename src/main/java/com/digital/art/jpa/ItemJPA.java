package com.digital.art.jpa;

import com.digital.art.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemJPA extends JpaRepository<Item, Integer> {
}
