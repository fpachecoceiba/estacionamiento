package com.co.ceiba.parqueadero.controlador.excepcion;

import java.util.Calendar;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.co.ceiba.parqueadero.dominio.RespuestaErrorDTO;
import com.co.ceiba.parqueadero.dominio.excepcion.RegistroNoExisteException;

@ControllerAdvice
@CrossOrigin("*")
public class ControlErrorHandler { 
 
	@ExceptionHandler(RegistroNoExisteException.class)
	public ResponseEntity<RespuestaErrorDTO> handleException(RegistroNoExisteException ex, WebRequest request) {
		RespuestaErrorDTO excepcion = new RespuestaErrorDTO(Calendar.getInstance().getTime(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(excepcion, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<RespuestaErrorDTO> handleException(Exception ex, WebRequest request) {
		RespuestaErrorDTO excepcion = new RespuestaErrorDTO(Calendar.getInstance().getTime(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(excepcion, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<RespuestaErrorDTO> handleException(HttpRequestMethodNotSupportedException ex,
			WebRequest request) {
		RespuestaErrorDTO excepcion = new RespuestaErrorDTO(Calendar.getInstance().getTime(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(excepcion, HttpStatus.NOT_FOUND);
	}
}
