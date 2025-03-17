package br.vitorreck.payment.services;

import br.vitorreck.payment.exceptions.BusinessException;
import br.vitorreck.payment.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

  @InjectMocks private AccountService accountService;
  @Mock private AccountRepository accountRepository;

  private AccountService mockAccountService;

  @BeforeEach
  void setUp() {
    mockAccountService = mock(AccountService.class);
  }

  @Test
  void testShouldThrow_BusinessException() {
    doThrow(BusinessException.class)
        .when(mockAccountService).checkSufficientFunds(new BigDecimal("1000.00"), 1L);

    assertThrows(BusinessException.class, () -> mockAccountService.checkSufficientFunds(new BigDecimal("1000.00"), 1L));
  }

  @Test
  void testTransferFunds_ShouldExecuteAtLeastOne() {
    mockAccountService.transferFunds(1L, 2L, new BigDecimal("1000.00"));
    verify(mockAccountService, times(1)).transferFunds(1L, 2L, new BigDecimal("1000.00"));
  }
}
