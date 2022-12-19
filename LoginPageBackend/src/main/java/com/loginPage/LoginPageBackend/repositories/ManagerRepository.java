package com.loginPage.LoginPageBackend.repositories;

import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;
import com.loginPage.LoginPageBackend.domain.Manager;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends FirestoreReactiveRepository<Manager> {
}
