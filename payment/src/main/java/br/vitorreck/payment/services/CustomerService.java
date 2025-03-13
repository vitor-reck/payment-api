package br.vitorreck.payment.services;

import br.vitorreck.payment.entities.model.Customer;
import br.vitorreck.payment.repositories.CustomerRepository;
import com.google.gson.Gson;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class CustomerService {

  private static final String CUSTOMER_NOT_FOUND = "Customer doesn't exist with given ID";
  private static final String CUSTOMER_ALREADY_EXISTS = "Customer already exists";
  private final Gson gson = new Gson();
  private final CustomerRepository customerRepository;

  public Customer findCustomerById(Long id) {
    log.info("Retrieving customer with ID: {}", id);
    return customerRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(CUSTOMER_NOT_FOUND));
  }

  public String findCpfById(Long id) {
    return customerRepository.findCpfById(id)
        .orElseThrow(() -> new EntityNotFoundException(CUSTOMER_NOT_FOUND));
  }

  public void createCustomer(Customer customer) {
    customerRepository.findById(customer.getId())
        .ifPresentOrElse(c -> {
            throw new EntityExistsException(CUSTOMER_ALREADY_EXISTS);
          },
            () -> {
              log.info("Creating customer: {}", gson.toJson(customer));
              customerRepository.save(customer);
        });

    customerRepository.save(customer);
  }

  public void removeCustomerById(Long id) {
    customerRepository.findById(id)
        .ifPresentOrElse(c -> {
          log.info("Removing customer with ID: {}", id);
          customerRepository.deleteById(id);
          },
            () -> {throw new EntityNotFoundException(CUSTOMER_NOT_FOUND);
        });
  }
}
