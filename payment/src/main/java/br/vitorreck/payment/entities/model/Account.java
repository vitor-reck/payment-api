package br.vitorreck.payment.entities.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "accounts")
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accounts_seq")
  @SequenceGenerator(name = "accounts_seq", allocationSize = 1)
  private Long id;

  @Column(nullable = false)
  private BigDecimal balance;
}
