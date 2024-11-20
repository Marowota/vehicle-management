package com.maha.vehicle_management.Repositories;

import com.maha.vehicle_management.Entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findOneById(long id);
    Account findOneByUsername(String username);
    Account findOneByEmail(String email);
    void removeByUsername(String username);
    List<Account> findByUsername(String username);
}
