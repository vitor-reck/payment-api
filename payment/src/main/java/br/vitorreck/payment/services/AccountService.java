package br.vitorreck.payment.services;

import br.vitorreck.payment.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountService {

  private final AccountRepository accountRepository;

  public Boolean checkSufficientFunds(BigDecimal value, Long payerId) {
    return accountRepository.checkBalance(value, payerId);
  }

  @Transactional
  public void transferFunds(Long payerId, Long payeeId, BigDecimal value) {
    accountRepository.transferFunds(payerId, payeeId, value);
  }
}
