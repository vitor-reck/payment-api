package br.vitorreck.payment.repositories;

import br.vitorreck.payment.entities.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  @Query(value = "SELECT cpf FROM customers WHERE id = ?1", nativeQuery = true)
  String findCpfById(Long id);
}
