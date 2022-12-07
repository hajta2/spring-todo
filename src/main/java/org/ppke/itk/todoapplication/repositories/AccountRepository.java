package org.ppke.itk.todoapplication.repositories;

import java.util.Optional;

import org.ppke.itk.todoapplication.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findUserByEmailAndPassword(String email, String password);
}
