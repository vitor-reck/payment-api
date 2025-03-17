package br.vitorreck.payment.entities.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customers")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
  @SequenceGenerator(name = "users_seq", allocationSize = 1)
  private Long id;

  @Column(name = "full_name", nullable = false)
  private String fullName;

  @Column(unique = true, nullable = false)
  private String cpf;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_email", unique = true, nullable = false)
  private Email email;

  @Column(nullable = false)
  private String password;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_account", unique = true, nullable = false)
  private Account account;
}
