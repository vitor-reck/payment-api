package br.vitorreck.payment.services;

import br.vitorreck.payment.entities.model.Shopkeeper;
import br.vitorreck.payment.repositories.ShopkeeperRepository;
import com.google.gson.Gson;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ShopkeeperService {

  private static final String SHOPKEEPER_NOT_FOUND = "Shopkeeper doesn't exist with given ID";
  private static final String SHOPKEEPER_ALREADY_EXISTS = "Shopkeeper already exists";
  private final Gson gson = new Gson();
  private final ShopkeeperRepository shopkeeperRepository;

  public Shopkeeper findShopkeeperById(Long id) {
    log.info("Retrieving shopkeeper with ID: {}", id);
    return shopkeeperRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(SHOPKEEPER_NOT_FOUND));
  }

  public void createShopkeeper(Shopkeeper shopkeeper) {
    shopkeeperRepository.findById(shopkeeper.getId())
        .ifPresentOrElse(s -> {
            throw new EntityExistsException(SHOPKEEPER_ALREADY_EXISTS);
          },
            () -> {
              log.info("Creating shopkeeper: {}", gson.toJson(shopkeeper));
              shopkeeperRepository.save(shopkeeper);
        });
  }

  public void removeShopkeeperById(Long id) {
    shopkeeperRepository.findById(id)
        .ifPresentOrElse(s -> {
          log.info("Removing shopkeeper with ID: {}", id);
          shopkeeperRepository.deleteById(id);
          },
            () -> {throw new EntityNotFoundException(SHOPKEEPER_NOT_FOUND);}
        );
  }
}
