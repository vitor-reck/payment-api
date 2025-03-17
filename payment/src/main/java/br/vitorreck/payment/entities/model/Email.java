package br.vitorreck.payment.entities.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "emails")
public class Email {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emails_seq")
  @SequenceGenerator(name = "emails_seq", allocationSize = 1)
  private Long id;

  @Column(unique = true, nullable = false)
  private String address;
}
