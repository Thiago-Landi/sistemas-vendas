package com.Thiago_landi.sistemas.vendas.controller.common;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Thiago_landi.sistemas.vendas.controller.dto.ErrorField;
import com.Thiago_landi.sistemas.vendas.controller.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	//captura o @Valid
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ErrorResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		List<FieldError> fieldErrors = e.getFieldErrors();	
		List<ErrorField> listErrors = fieldErrors.stream()
				.collect(Collectors.toMap(
				        FieldError::getField,
				        FieldError::getDefaultMessage,
				        (existing, replacement) -> existing // mantém a primeira mensagem
				    ))
				    .entrySet().stream()
				    .map(entry -> new ErrorField(entry.getKey(), entry.getValue()))
				    .collect(Collectors.toList());
		
		return new ErrorResponse(
				 HttpStatus.UNPROCESSABLE_ENTITY.value(),
				 "Erro de Validação", 
				 listErrors);
	}
}
