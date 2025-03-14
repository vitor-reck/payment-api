package br.vitorreck.payment.entities.dto.customer;

import br.vitorreck.payment.entities.model.Email;

public record CustomerResponseDTO(
   String fullName,
   String cpf,
   Email email
) {}