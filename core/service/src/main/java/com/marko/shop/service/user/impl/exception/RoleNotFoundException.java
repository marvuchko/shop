package com.marko.shop.service.user.impl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.marko.shop.infrastructure.common.exception.AbstractHttpRuntimeException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoleNotFoundException extends AbstractHttpRuntimeException {

    private static final long serialVersionUID = 6261824713950299751L;
    
    private static final String ROLE_NOT_FOUND = "Role not found!";

    public RoleNotFoundException(String message, Throwable cause) {
	super(message, cause);
    }

    public RoleNotFoundException(String message) {
	super(message);
    }

    public RoleNotFoundException(Throwable cause) {
	super(cause);
    }
    
    public RoleNotFoundException() {
	super(ROLE_NOT_FOUND);
    }

}
