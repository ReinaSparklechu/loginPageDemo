package com.loginPage.LoginPageBackend.repositories;

import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;
import com.loginPage.LoginPageBackend.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends FirestoreReactiveRepository<User> {
}
