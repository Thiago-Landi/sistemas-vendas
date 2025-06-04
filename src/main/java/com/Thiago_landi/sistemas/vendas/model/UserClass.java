package com.Thiago_landi.sistemas.vendas.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_tb")
@Data
@NoArgsConstructor
public class UserClass implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column
    private String login;
	
	@Column
	private String name;
	
	@Column(name = "password_")
	private String password;
	
	@Column(name = "account_non_expired")
	private Boolean accountNonExpired;

	@Column(name = "account_non_locked")
	private Boolean accountNonLocked;

	@Column(name = "credentials_non_expired")
	private Boolean credentialsNonExpired;

	@Column
	private Boolean enabled;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_permission",
        joinColumns = {@JoinColumn (name = "id_user")},
        inverseJoinColumns = {@JoinColumn (name = "id_permission")}
    )
    private List<Permission> permissions;

	public List<String> getRoles() {
		List<String> roles = new ArrayList<>();
		for(Permission permission : permissions) {
			roles.add(permission.getDescription());
		}
		return roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissions;
	}

	 @Override
	 public String getPassword() {
	     return this.password;
	 }

	 @Override
	 public String getUsername() {
	     return this.login;
	 }

	 @Override
	 public boolean isAccountNonExpired() {
	     return this.accountNonExpired;
	 }

	 @Override
	 public boolean isAccountNonLocked() {
	     return this.accountNonLocked;
	 }

	 @Override
	 public boolean isCredentialsNonExpired() {
	     return this.credentialsNonExpired;
	 }

	 @Override
	 public boolean isEnabled() {
	     return this.enabled;
	 }
	       
}
