package br.vitorreck.payment.services;

import br.vitorreck.payment.entities.dto.transfer.TransferDTO;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransferService {

  private static final String url = "https://util.devi.tools/api";
  private final RestTemplate restTemplate = new RestTemplate();
  private final Gson gson = new Gson();
  private final CustomerService customerService;
  private final AccountService accountService;

  @Transactional(rollbackFor = HttpClientErrorException.class)
  public void transfer(TransferDTO transferDTO) {
    String cpf = customerService.findCpfById(transferDTO.payer());

    if (Objects.isNull(cpf)) {
      throw new RuntimeException("Payer is not a customer or doesn't exist");
    }

    Boolean checkedFunds = accountService.checkSufficientFunds(transferDTO.value(), transferDTO.payee());

    if(Boolean.FALSE.equals(checkedFunds)) {
      throw new RuntimeException("Payer doesn't have enough funds for the transaction");
    }

    accountService.transferFunds(transferDTO.payer(), transferDTO.payee(), transferDTO.value());
    restTemplate.getForEntity(url + "/v2/authorize", null);
    restTemplate.postForEntity(url + "/v1/notify", null, null);
  }
}
