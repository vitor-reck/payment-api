package br.vitorreck.payment.mappers;

import br.vitorreck.payment.entities.dto.customer.CustomerRequestDTO;
import br.vitorreck.payment.entities.dto.customer.CustomerResponseDTO;
import br.vitorreck.payment.entities.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

  CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

  @Mapping(source = "customer", target = ".")
  CustomerResponseDTO toDTO(Customer customer);

  @Mapping(source = "requestDTO", target = ".")
  Customer toEntity(CustomerRequestDTO requestDTO);
}
