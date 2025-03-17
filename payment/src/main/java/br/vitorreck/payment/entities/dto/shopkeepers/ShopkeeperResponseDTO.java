package br.vitorreck.payment.entities.dto.shopkeepers;

import br.vitorreck.payment.entities.model.Email;

public record ShopkeeperResponseDTO(
    String fullName,
    String cnpj,
    Email email
) {}
