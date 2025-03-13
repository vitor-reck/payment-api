package br.vitorreck.payment.repositories;

import br.vitorreck.payment.entities.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

  @Transactional
  @Modifying
  @Query(value = "UPDATE accounts SET balance = " +
      "CASE WHEN id = ?1 THEN balance - ?3 " +
      "WHEN id = ?2 THEN balance + ?3 END " +
      "WHERE id IN (?1, ?2)", nativeQuery = true)
  void transferFunds(Long payerId, Long payeeId, BigDecimal value);

  @Query(value = "SELECT balance >= ?1 FROM accounts WHERE id = ?2", nativeQuery = true)
  Boolean checkBalance(BigDecimal value, Long id);
}
