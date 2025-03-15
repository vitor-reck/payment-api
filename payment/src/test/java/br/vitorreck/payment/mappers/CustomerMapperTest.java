package br.vitorreck.payment.mappers;

import br.vitorreck.payment.entities.dto.customer.CustomerRequestDTO;
import br.vitorreck.payment.entities.dto.customer.CustomerResponseDTO;
import br.vitorreck.payment.entities.model.Account;
import br.vitorreck.payment.entities.model.Customer;
import br.vitorreck.payment.entities.model.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerMapperTest {

  @Spy private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

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

    customer = Customer.builder()
        .fullName("Customer")
        .cpf("111.111.111-11")
        .email(email)
        .build();

    requestDTO = CustomerRequestDTO.builder()
        .id(1L)
        .fullName("Customer")
        .cpf("111.111.111-11")
        .email(email)
        .password("password")
        .account(account)
        .build();
  }

  @Test
  void testShouldConvertToDTO_WhenMapperIsInvoked() {
    CustomerResponseDTO result = CustomerMapper.INSTANCE.toDTO(customer);

    assertNotNull(result);
    assertEquals("Customer", result.fullName());
    assertEquals("111.111.111-11", result.cpf());
    assertEquals(email, result.email());
  }

  @Test
  void testShouldConvertToEntity_WhenMapperIsInvoked() {
    Customer result = CustomerMapper.INSTANCE.toEntity(requestDTO);

    assertNotNull(result);
    assertEquals("Customer", result.getFullName());
    assertEquals(email, result.getEmail());
    assertEquals(account, result.getAccount());
  }
}
