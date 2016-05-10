package org.codeplay.presentation.util;

import javax.ws.rs.core.Response;

import org.codeplay.presentation.entities.ResponseObj;

/**
 The utility class is used to map the Exception instances to the particular functions and to accordingly set the require values.
 */

public class ServiceExceptionMapper {

	public static ResponseObj toResponse(Exception exceptionObj) {
		ResponseObj respObj = new ResponseObj();
		if (exceptionObj instanceof ServiceException) {
			System.out.println("EMapperIn");
			respObj.setErrorStatus("FAILURE");
			respObj.setErrorCode(exceptionObj.getMessage());
			System.out.println("EMapperOut");
			return respObj;
		} else {
			return null;
		}

	}

}
