package br.vitorreck.payment.repositories;

import br.vitorreck.payment.entities.model.Shopkeeper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopkeeperRepository extends JpaRepository<Shopkeeper, Long> {

  @Query(value = "SELECT cnpj FROM shopkeepers WHERE cnpj = 1?", nativeQuery = true)
  Optional<String> findCnpj(String cnpj);
}
