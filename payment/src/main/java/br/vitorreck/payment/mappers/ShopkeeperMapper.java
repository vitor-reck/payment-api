package br.vitorreck.payment.mappers;

import br.vitorreck.payment.entities.dto.shopkeepers.ShopkeeperRequestDTO;
import br.vitorreck.payment.entities.dto.shopkeepers.ShopkeeperResponseDTO;
import br.vitorreck.payment.entities.model.Shopkeeper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ShopkeeperMapper {

  ShopkeeperMapper MAPPER = Mappers.getMapper(ShopkeeperMapper.class);

  @Mapping(source = "shopkeeper", target = ".")
  ShopkeeperResponseDTO mapToDTO(Shopkeeper shopkeeper);

  @Mapping(source = "requestDTO", target = ".")
  Shopkeeper mapToEntity(ShopkeeperRequestDTO requestDTO);
}
