package br.vitorreck.payment.controllers;

import br.vitorreck.payment.entities.model.Shopkeeper;
import br.vitorreck.payment.services.ShopkeeperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shopkeepers")
public class ShopkeeperController {

  private final ShopkeeperService shopkeeperService;

  @GetMapping("{id}")
  public ResponseEntity<Shopkeeper> getShopkeeperById(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(shopkeeperService.findShopkeeperById(id));
  }

  @PostMapping
  public ResponseEntity<String> postShopkeeper(@RequestBody Shopkeeper shopkeeper) {
    shopkeeperService.createShopkeeper(shopkeeper);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteShopkeeperById(@PathVariable Long id) {
    shopkeeperService.removeShopkeeperById(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
