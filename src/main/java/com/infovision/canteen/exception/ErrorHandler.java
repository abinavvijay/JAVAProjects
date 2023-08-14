package com.infovision.canteen.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorHandler {
	
	private final String status = "error"; 
	
	private int code;
	
	private String message ;
	

}