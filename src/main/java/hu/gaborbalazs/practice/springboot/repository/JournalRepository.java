package hu.gaborbalazs.practice.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.gaborbalazs.practice.springboot.domain.Journal;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Long>{

}
