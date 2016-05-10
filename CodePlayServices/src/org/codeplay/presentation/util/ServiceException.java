package org.codeplay.presentation.util;

import java.io.Serializable;

/**
 * ContentParsingException
 */
public class ServiceException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;
    public ServiceException() {
        super();
    }
    public ServiceException(String msg)   {
        super(msg);
    }
    public ServiceException(String msg, Exception e)  {
        super(msg, e);
    }
}
