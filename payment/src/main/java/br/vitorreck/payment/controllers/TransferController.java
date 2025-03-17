package br.vitorreck.payment.controllers;

import br.vitorreck.payment.entities.dto.transfer.TransferDTO;
import br.vitorreck.payment.services.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transfer")
public class TransferController {

  private final TransferService transferService;

  @PostMapping
  public ResponseEntity<String> postTransfer(@Valid @RequestBody TransferDTO transferDTO) {
    transferService.transfer(transferDTO);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
