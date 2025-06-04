package com.Thiago_landi.sistemas.vendas.model;

import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "permission")
@Data
public class Permission implements GrantedAuthority {

		private static final long serialVersionUID = 1L;

		@Id
	    @GeneratedValue(strategy = GenerationType.UUID)
	    private UUID id;

	    @Column
	    private String description;

		@Override
		public String getAuthority() {
			return this.description;
		}
		
}
