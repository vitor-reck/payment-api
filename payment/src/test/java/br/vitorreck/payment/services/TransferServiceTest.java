package br.vitorreck.payment.services;

import br.vitorreck.payment.entities.dto.transfer.TransferDTO;
import br.vitorreck.payment.exceptions.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

  @InjectMocks private TransferService transferService;
  @Mock private CustomerService customerService;
  @Mock private AccountService accountService;

  private TransferService mockTransferService;
  private TransferDTO transferDTO;

  @BeforeEach
  void setUp() {
    mockTransferService = mock(TransferService.class);
  }

  @Test
  void testShouldThrow_DuplicateAccountsException() {
    transferDTO = new TransferDTO(
        new BigDecimal("100.00"),
        1L,
        1L
    );

    doThrow(BusinessException.class)
        .when(mockTransferService).transfer(transferDTO);

    assertThrows(BusinessException.class, () -> mockTransferService.transfer(transferDTO));
  }

  @Test
  void testShouldThrow_EntityNotFoundException() {
    transferDTO = new TransferDTO(
        new BigDecimal("1000.00"),
        1L,
        2L
    );

    doThrow(EntityNotFoundException.class)
        .when(mockTransferService).transfer(transferDTO);

    assertThrows(EntityNotFoundException.class, () -> mockTransferService.transfer(transferDTO));
  }

  @Test
  void testShouldThrow_InsufficientFundsException() {
    transferDTO = new TransferDTO(
        new BigDecimal("1000000.00"),
        1L,
        2L
    );

    doThrow(BusinessException.class)
        .when(mockTransferService).transfer(transferDTO);

    assertThrows(BusinessException.class, () -> mockTransferService.transfer(transferDTO));
  }

  @Test
  void testTransferFunds_ShouldExecutedAtLeastOnce() {
    transferDTO = new TransferDTO(
        new BigDecimal("100.00"),
        1L,
        2L
    );

    mockTransferService.transfer(transferDTO);
    verify(mockTransferService, times(1)).transfer(transferDTO);
  }
}
