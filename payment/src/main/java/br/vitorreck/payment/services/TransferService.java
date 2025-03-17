package br.vitorreck.payment.services;

import br.vitorreck.payment.entities.dto.transfer.TransferDTO;
import br.vitorreck.payment.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Log4j2
public class TransferService {

  private static final String url = "https://util.devi.tools/api";
  private static final String DUPLICATE_ACCOUNTS = "Payer ID and payee ID are equals";
  private final RestTemplate restTemplate = new RestTemplate();
  private final CustomerService customerService;
  private final AccountService accountService;

  @Transactional(rollbackFor = HttpClientErrorException.class)
  public void transfer(TransferDTO transferDTO) {
    if(transferDTO.payer().equals(transferDTO.payee())) {
      throw new BusinessException(DUPLICATE_ACCOUNTS);
    }

    customerService.findCpfById(transferDTO.payer());

    accountService.checkSufficientFunds(transferDTO.value(), transferDTO.payee());
    accountService.transferFunds(transferDTO.payer(), transferDTO.payee(), transferDTO.value());

    restTemplate.getForEntity(url + "/v2/authorize", null);
    restTemplate.postForEntity(url + "/v1/notify", null, null);
  }
}
