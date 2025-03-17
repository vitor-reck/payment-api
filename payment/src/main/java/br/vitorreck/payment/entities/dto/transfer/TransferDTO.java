package br.vitorreck.payment.entities.dto.transfer;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TransferDTO(

    @NotNull(message = "Value should not be null")
    BigDecimal value,

    @NotNull(message = "Payer ID should not be null")
    Long payer,

    @NotNull(message = "Payee ID should not be null")
    Long payee
) {}
