package br.vitorreck.payment.mappers;

import br.vitorreck.payment.entities.dto.customer.CustomerRequestDTO;
import br.vitorreck.payment.entities.dto.customer.CustomerResponseDTO;
import br.vitorreck.payment.entities.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper()
public interface CustomerMapper {

  CustomerMapper MAPPER = Mappers.getMapper(CustomerMapper.class);

  @Mapping(source = "customer", target = ".")
  CustomerResponseDTO maptoDTO(Customer customer);

  @Mapping(source = "requestDTO", target = ".")
  Customer mapToEntity(CustomerRequestDTO requestDTO);
}
