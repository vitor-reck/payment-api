package br.vitorreck.payment.mappers;

import br.vitorreck.payment.entities.dto.shopkeepers.ShopkeeperRequestDTO;
import br.vitorreck.payment.entities.dto.shopkeepers.ShopkeeperResponseDTO;
import br.vitorreck.payment.entities.model.Account;
import br.vitorreck.payment.entities.model.Shopkeeper;
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

@ExtendWith(MockitoExtension.class)
class ShopkeeperMapperTest {

  @Spy private ShopkeeperMapper shopkeeperMapper = Mappers.getMapper(ShopkeeperMapper.class);

  private Shopkeeper shopkeeper;
  private Email email;
  private Account account;
  private ShopkeeperRequestDTO requestDTO;

  @BeforeEach
  void setUp() {
    email = Email.builder()
        .address("shopkeeper@gmail.com")
        .build();

    account = Account.builder()
        .balance(BigDecimal.valueOf(1000.00))
        .build();

    shopkeeper = Shopkeeper.builder()
        .fullName("Shopkeeper")
        .cnpj("11.111.111/0001-11")
        .email(email)
        .build();

    requestDTO = ShopkeeperRequestDTO.builder()
        .id(1L)
        .fullName("Shopkeeper")
        .cnpj("11.111.111/0001-11")
        .email(email)
        .password("password")
        .account(account)
        .build();
  }

  @Test
  void testShouldConvertToDTO_WhenMapperIsInvoked() {
    ShopkeeperResponseDTO result = ShopkeeperMapper.INSTANCE.toDTO(shopkeeper);

    assertNotNull(result);
    assertEquals("Shopkeeper", result.fullName());
    assertEquals("11.111.111/0001-11", result.cnpj());
    assertEquals(email, result.email());
  }

  @Test
  void testShouldConvertToEntity_WhenMapperIsInvoked() {
    Shopkeeper result = ShopkeeperMapper.INSTANCE.toEntity(requestDTO);

    assertNotNull(result);
    assertEquals("Shopkeeper", result.getFullName());
    assertEquals(email, result.getEmail());
    assertEquals(account, result.getAccount());
  }
}

