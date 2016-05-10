package org.codeplay.presentation.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * JAX-RS supports exception inheritance as well. In case an exception is thrown
 * JAX-RS will try and map an ExceptionMapper for that exception type, if it
 * cannot then it will try and find an exception mapper for the exception's
 * superclass. It will continue till there are no more classes left in the
 * heirarchy.
 */
@Provider
public class ServiceExceptionMapper implements ExceptionMapper<RuntimeException> {
	@Override
    public Response toResponse(RuntimeException exceptionObj) {

        if (exceptionObj instanceof ServiceException) {
            //return Response.status(ContentServiceConstants.EXPERT_OPINION_MISSING).build();
           return Response.status(00).entity(exceptionObj.getMessage()).build();
        } 
        else {
            return Response.status(01).build();
        }

    }

}
