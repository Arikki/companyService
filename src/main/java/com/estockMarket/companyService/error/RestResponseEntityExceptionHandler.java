package com.estockMarket.companyService.error;
//package com.companyService.error;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.axonframework.axonserver.connector.query.AxonServerRemoteQueryHandlingException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.dao.DuplicateKeyException;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import com.companyService.filter.ReqFilter;
//import com.companyService.model.ErrorMessage;
//
//@ControllerAdvice
//@ResponseStatus
//public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
//	
//	private static final Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);
//
//	@ExceptionHandler(InvalidDataException.class)
//	public ResponseEntity<ErrorMessage> invalidDataException(InvalidDataException exception, WebRequest request) {
//
//		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage(), new ArrayList<>());
//		
//		logger.info("Exception occured:"+message.toString());
//
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
//
//	}
//
//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		List<String> errors = new ArrayList<String>();
//		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//			errors.add(error.getField() + ": " + error.getDefaultMessage());
//		}
//		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
//			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
//		}
//
//		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, "Invalid Input Data", errors);
//		logger.info("Exception occured:"+message.toString());
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
//	}
//
//	@ExceptionHandler(AxonServerRemoteQueryHandlingException.class)
//	public ResponseEntity<ErrorMessage> dupKeyException(AxonServerRemoteQueryHandlingException exception, WebRequest request) {
//
//		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage().substring(exception.getMessage().indexOf("Company")), new ArrayList<>());
//		logger.info("Exception occured:"+exception.getMessage().substring(exception.getMessage().indexOf("Company")));
//		
//		
//		
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
//
//	}
//}
