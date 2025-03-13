package br.vitorreck.payment.services;

import br.vitorreck.payment.entities.model.Shopkeeper;
import br.vitorreck.payment.repositories.ShopkeeperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopkeeperService {

  private final ShopkeeperRepository shopkeeperRepository;

  public Shopkeeper findShopkeeperById(Long id) {
    return shopkeeperRepository.findById(id).get();
  }

  public void createShopkeeper(Shopkeeper shopkeeper) {
    shopkeeperRepository.save(shopkeeper);
  }

  public void removeShopkeeperById(Long id) {
    shopkeeperRepository.deleteById(id);
  }
}
