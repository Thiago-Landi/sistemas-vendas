package com.Thiago_landi.sistemas.vendas.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "city")
@Data
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(nullable = false, length = 9)
	private String cep;
	
	@ManyToOne
	@JoinColumn(name = "id_state", nullable = false)
	private State state;
	
}
