package br.vitorreck.payment.entities.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
