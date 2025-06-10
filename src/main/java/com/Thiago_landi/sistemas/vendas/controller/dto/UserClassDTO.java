package com.Thiago_landi.sistemas.vendas.controller.dto;

import java.util.List;

public record UserClassDTO ( 
		 String login,
		 String name,
		 String password,
		 Boolean accountNonExpired,
		 Boolean accountNonLocked,
		 Boolean credentialsNonExpired,
		 Boolean enabled,
		 List<String> permissions){

}
