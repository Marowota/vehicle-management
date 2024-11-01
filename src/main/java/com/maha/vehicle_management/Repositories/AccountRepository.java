package com.maha.vehicle_management.Repositories;

import com.maha.vehicle_management.Entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findOneById(long id);
}
