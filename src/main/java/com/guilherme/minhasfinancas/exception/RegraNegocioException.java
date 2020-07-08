package com.guilherme.minhasfinancas.exception;

import com.guilherme.minhasfinancas.model.entity.Usuario;
import org.springframework.http.HttpStatus;

public class RegraNegocioException extends RuntimeException {
    public RegraNegocioException(String msg) {
        super(msg);
    }

}
