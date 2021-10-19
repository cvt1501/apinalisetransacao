package br.com.analisetransacao.entrypoint.handler;

import br.com.analisetransacao.domain.exception.GenericError;
import br.com.analisetransacao.entrypoint.exception.ValidacaoControllerException;
import br.com.analisetransacao.gateway.exception.GatewayException;
import br.com.analisetransacao.usecase.exception.DadosNaoEncontradoUsecaseException;
import br.com.analisetransacao.usecase.exception.ErroDeAtualizacaoUsecaseException;
import br.com.analisetransacao.usecase.exception.ErroDeCadastroUsecaseException;
import br.com.analisetransacao.usecase.exception.UsuarioNaoEncontradoUsecaseException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@ControllerAdvice
@Order(HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidacaoControllerException.class)
    @ResponseBody
    public ResponseEntity<GenericError> handlerValidacaoControllerException(final ValidacaoControllerException ex) {

        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST)
                .body(GenericError
                        .builder()
                        .codigo(HttpStatus.BAD_REQUEST.toString())
                        .mensagem(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(DadosNaoEncontradoUsecaseException.class)
    @ResponseBody
    public ResponseEntity<GenericError> handlerDadosNaoEncontradoUsecaseException(final DadosNaoEncontradoUsecaseException ex) {

        return ResponseEntity.
                status(HttpStatus.NOT_FOUND)
                .body(GenericError
                            .builder()
                            .codigo(HttpStatus.NOT_FOUND.toString())
                            .mensagem(ex.getMessage())
                            .build());
    }

    @ExceptionHandler(UsuarioNaoEncontradoUsecaseException.class)
    @ResponseBody
    public ResponseEntity<GenericError> handlerUsuarioNaoEncontradoUsecaseException(final UsuarioNaoEncontradoUsecaseException ex) {

        return ResponseEntity.
                status(HttpStatus.NOT_FOUND)
                .body(GenericError
                        .builder()
                        .codigo(HttpStatus.NOT_FOUND.toString())
                        .mensagem(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(ErroDeCadastroUsecaseException.class)
    @ResponseBody
    public ResponseEntity<GenericError> handlerErroDeCadastroException(final ErroDeCadastroUsecaseException ex) {

        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(GenericError
                        .builder()
                        .codigo(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                        .mensagem(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(ErroDeAtualizacaoUsecaseException.class)
    @ResponseBody
    public ResponseEntity<GenericError> handlerErroDeAtualizacaoUsecaseException(final ErroDeAtualizacaoUsecaseException ex) {

        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(GenericError
                        .builder()
                        .codigo(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                        .mensagem(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(GatewayException.class)
    @ResponseBody
    public ResponseEntity<GenericError> handlerGatewayException(final GatewayException ex) {

        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(GenericError
                        .builder()
                        .codigo(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                        .mensagem(ex.getMessage())
                        .build());
    }

}
