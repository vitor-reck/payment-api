package br.vitorreck.payment.entities.dto.customer;

import br.vitorreck.payment.entities.model.Account;
import br.vitorreck.payment.entities.model.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequestDTO(

    Long id,

    @NotNull(message = "Full name should not be null")
    String fullName,

    @NotNull(message = "CPF should not be null")
    String cpf,

    @NotNull(message = "E-mail name should not be null")
    Email email,

    @NotNull(message = "Password name should not be null")
    String password,

    @NotNull(message = "Account name should not be null")
    Account account
) {}
