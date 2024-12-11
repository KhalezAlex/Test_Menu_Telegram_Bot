package org.klozevitz.model.repositories;

import org.klozevitz.model.entities.RawData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawDataRepository extends JpaRepository<RawData, Long> {
}