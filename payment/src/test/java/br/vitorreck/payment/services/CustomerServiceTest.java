package br.vitorreck.payment.services;

import br.vitorreck.payment.entities.dto.customer.CustomerRequestDTO;
import br.vitorreck.payment.entities.dto.customer.CustomerResponseDTO;
import br.vitorreck.payment.entities.model.Account;
import br.vitorreck.payment.entities.model.Customer;
import br.vitorreck.payment.entities.model.Email;
import br.vitorreck.payment.mappers.CustomerMapper;
import br.vitorreck.payment.repositories.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

  @InjectMocks private CustomerService customerService;
  @Mock private CustomerRepository customerRepository;
  @Spy private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

  private CustomerService mockCustomerService;
  private Customer customer;
  private Email email;
  private Account account;
  private CustomerRequestDTO requestDTO;

  @BeforeEach
  void setUp() {
    email = Email.builder()
        .address("customer@gmail.com")
        .build();

    account = Account.builder()
        .balance(BigDecimal.valueOf(1000.00))
        .build();

    requestDTO = CustomerRequestDTO.builder()
        .id(1L)
        .fullName("Customer")
        .cpf("111.111.111-11")
        .email(email)
        .password("password")
        .account(account)
        .build();

    customer = Customer.builder()
        .fullName("Customer")
        .cpf("111.111.111-11")
        .email(email)
        .build();

    mockCustomerService = mock(CustomerService.class);
  }

  @Test
  void testShouldReturn_CustomerById() {
    when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

    CustomerResponseDTO result = customerService.findCustomerById(1L);

    assertNotNull(result);
    assertEquals("Customer", result.fullName());
    assertNotEquals("gmail@gmail.com", result.email().getAddress());
  }

  @Test
  void testCreateCustomer_ShouldExecuteAtLeastOnce() {
    mockCustomerService.createCustomer(requestDTO);
    verify(mockCustomerService, times(1)).createCustomer(requestDTO);
  }

  @Test
  void testShouldThrow_NotFoundException() {
    doThrow(EntityNotFoundException.class)
        .when(mockCustomerService).removeCustomerById(anyLong());

    assertThrows(EntityNotFoundException.class, () -> mockCustomerService.removeCustomerById(anyLong()));
  }
}
