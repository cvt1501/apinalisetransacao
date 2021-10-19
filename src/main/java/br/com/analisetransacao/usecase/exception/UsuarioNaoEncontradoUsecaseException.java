package br.com.analisetransacao.usecase.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UsuarioNaoEncontradoUsecaseException extends UsernameNotFoundException {

    public UsuarioNaoEncontradoUsecaseException(String message) {
        super(message);
    }

}
