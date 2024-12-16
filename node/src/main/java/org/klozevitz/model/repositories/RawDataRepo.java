package org.klozevitz.model.repositories;

import org.klozevitz.model.entities.RawData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawDataRepo extends JpaRepository<RawData, Long> {
}