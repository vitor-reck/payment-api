package br.vitorreck.payment.services;

import br.vitorreck.payment.entities.dto.shopkeepers.ShopkeeperRequestDTO;
import br.vitorreck.payment.entities.dto.shopkeepers.ShopkeeperResponseDTO;
import br.vitorreck.payment.entities.model.Account;
import br.vitorreck.payment.entities.model.Shopkeeper;
import br.vitorreck.payment.entities.model.Email;
import br.vitorreck.payment.mappers.ShopkeeperMapper;
import br.vitorreck.payment.repositories.ShopkeeperRepository;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShopkeeperServiceTest {

  @InjectMocks private ShopkeeperService shopkeeperService;
  @Mock private ShopkeeperRepository shopkeeperRepository;
  @Spy private ShopkeeperMapper shopkeeperMapper = Mappers.getMapper(ShopkeeperMapper.class);

  private ShopkeeperService mockShopkeeperService;
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

    mockShopkeeperService = mock(ShopkeeperService.class);
  }

  @Test
  void testShouldReturn_ShopkeeperById() {
    when(shopkeeperRepository.findById(1L)).thenReturn(Optional.of(shopkeeper));

    ShopkeeperResponseDTO result = shopkeeperService.findShopkeeperById(1L);

    assertNotNull(result);
    assertEquals("Shopkeeper", result.fullName());
    assertNotEquals("gmail@gmail.com", result.email().getAddress());
  }

  @Test
  void testCreateShopkeeper_ShouldExecuteAtLeastOnce() {
    mockShopkeeperService.createShopkeeper(requestDTO);
    verify(mockShopkeeperService, times(1)).createShopkeeper(requestDTO);
  }

  @Test
  void testShouldThrow_NotFoundException() {
    doThrow(EntityNotFoundException.class)
        .when(mockShopkeeperService).removeShopkeeperById(1L);

    assertThrows(EntityNotFoundException.class, () -> mockShopkeeperService.removeShopkeeperById(1L));
  }
}
