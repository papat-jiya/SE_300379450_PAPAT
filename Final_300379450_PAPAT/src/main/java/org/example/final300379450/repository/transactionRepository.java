package org.example.final300379450.repository;
//-----------------------------------------------
//Papat Jiyaworanant
//300379450

import org.example.final300379450.entities.transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface transactionRepository extends JpaRepository<transaction, Long>{
    List<transaction> findTransactionById (long kw);

}
