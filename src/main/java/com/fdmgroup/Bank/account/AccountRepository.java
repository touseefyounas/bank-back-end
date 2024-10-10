package com.fdmgroup.Bank.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	@Query("SELECT a FROM Account a")
	public List<Account> findAllAccounts();
	
	@Query("SELECT s from SavingsAccount s")
	public List<SavingsAccount> findAllSavingsAccounts();

	@Query("SELECT c FROM CheckingAccount c")
	public List<CheckingAccount> findAllCheckingAccounts();
	
	@Query("SELECT a FROM Account a JOIN a.customer c JOIN c.address q WHERE lower(q.city) =lower(:city)")
	public List<Account> findAccountsByCityName(@Param("city") String city);
}
