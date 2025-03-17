package br.vitorreck.payment.controllers;

import br.vitorreck.payment.entities.dto.customer.CustomerRequestDTO;
import br.vitorreck.payment.entities.dto.customer.CustomerResponseDTO;
import br.vitorreck.payment.entities.model.Customer;
import br.vitorreck.payment.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

  private final CustomerService customerService;

  @GetMapping("{id}")
  public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(customerService.findCustomerById(id));
  }

  @PostMapping
  public ResponseEntity<String> postCustomer(@RequestBody CustomerRequestDTO requestDTO) {
    customerService.createCustomer(requestDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteCustomerById(@PathVariable Long id) {
    customerService.removeCustomerById(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
