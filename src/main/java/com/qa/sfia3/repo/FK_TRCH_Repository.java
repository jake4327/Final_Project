package com.qa.sfia3.repo;

import com.qa.sfia3.domain.FK_TRCH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FK_TRCH_Repository extends JpaRepository<FK_TRCH, Long> {
}
