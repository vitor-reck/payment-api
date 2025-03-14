package br.vitorreck.payment.entities.dto.shopkeepers;

import br.vitorreck.payment.entities.model.Account;
import br.vitorreck.payment.entities.model.Email;
import jakarta.validation.constraints.NotNull;

public record ShopkeeperRequestDTO(
    Long id,

    @NotNull(message = "Full name should not be null")
    String fullName,

    @NotNull(message = "CNPJ should not be null")
    String cnpj,

    @NotNull(message = "E-mail name should not be null")
    Email email,

    @NotNull(message = "Password name should not be null")
    String password,

    @NotNull(message = "Account name should not be null")
    Account account
) {}
