package com.example.nPlusOne.persistenceContext.transaction;

import jakarta.transaction.TransactionManager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class Notion {

    CrudRepository crudRepository;

    TransactionManager transactionManager;

}
