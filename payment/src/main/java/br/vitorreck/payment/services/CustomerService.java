package br.vitorreck.payment.services;

import br.vitorreck.payment.entities.model.Customer;
import br.vitorreck.payment.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;

  public Customer findCustomerById(Long id) {
    return customerRepository.findById(id).get();
  }

  public void createCustomer(Customer customer) {
    customerRepository.save(customer);
  }

  public void removeCustomerById(Long id) {
    customerRepository.deleteById(id);
  }
}
