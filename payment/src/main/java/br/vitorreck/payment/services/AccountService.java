package br.vitorreck.payment.services;

import br.vitorreck.payment.exceptions.BusinessException;
import br.vitorreck.payment.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Log4j2
public class AccountService {

  private static final String INSUFFICIENT_FUNDS = "Insufficient funds, transaction will be aborted";
  private final AccountRepository accountRepository;

  public Boolean checkSufficientFunds(BigDecimal value, Long payerId) {
    return accountRepository.checkBalance(value, payerId)
        .filter(Boolean.TRUE::equals)
        .orElseThrow(() -> new BusinessException(INSUFFICIENT_FUNDS));
  }

  @Transactional
  public void transferFunds(Long payerId, Long payeeId, BigDecimal value) {
    log.info("Starting transaction with payer ID: {}, payee ID: {} and value: R${}", payerId, payeeId, value);
    accountRepository.transferFunds(payerId, payeeId, value);
  }
}
