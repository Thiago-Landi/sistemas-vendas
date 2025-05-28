package com.Thiago_landi.sistemas.vendas.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Thiago_landi.sistemas.vendas.model.State;

public interface StateRepository extends JpaRepository<State, UUID> {

	Optional<State> findByNameAndAbbreviation(
			String name, String abbreviation);
}
