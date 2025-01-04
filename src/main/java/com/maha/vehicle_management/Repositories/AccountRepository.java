package com.maha.vehicle_management.Repositories;

import com.maha.vehicle_management.Entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findOneById(long id);
    Account findOneByUsername(String username);
    Account findOneByEmail(String email);
    void removeByUsername(String username);
    List<Account> findByUsername(String username);
    @Modifying
    @Transactional
    @Query("update Account a " +
            "set a.username = :#{#oldAcc.username}, " +
            "a.email = :#{#oldAcc.email}, " +
            "a.name = :#{#oldAcc.name}, " +
            "a.role = :#{#oldAcc.role} " +
            "where a.id = :#{#oldAcc.id}")
    void changeInfo(Account oldAcc);
}
