package com.store.cars.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Response<E> {

    private int status;
    private String errorCode;
    private String stacktrace;
    private List<String> messages;
    private List<E> data;

    /**
     * Construtor necessário para chamadas rest com o restTemplate.
     * Dessa forma, podemos colocar o objecto de retorno como um "Response"
     */
    Response(){}

    private Response(Builder<E> builder) {
        super();
        this.status = builder.status;
        this.errorCode = builder.errorCode;
        this.stacktrace = builder.stacktrace;
        this.messages = builder.messages;
        this.data = builder.data;
    }

    public static <E> Builder<E> status(int status, Class<E> clazz) {
        return new Builder<E>(status);
    }

    public int getStatus() {
        return this.status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public List<String> getMessages() {
        return this.messages;
    }

    public List<E> getData() {
        return this.data;
    }

    public final static class Builder<E> {

        private int status;
        private String errorCode;
        private String stacktrace;
        private Exception exception;
        private List<String> messages = new ArrayList<String>();
        private List<E> data = new ArrayList<E>();

        @SuppressWarnings("unused")
        private Builder() {
            super();
        }

        private Builder(int status) {
            this.status = status;
        }

        public Builder<E> exception(Exception exception) {
            this.exception = exception;
            return this;
        }

        public Builder<E> messages(List<String> messages) {
            this.messages = messages;
            return this;
        }

        public Builder<E> message(String message) {
            this.messages.add(message);
            return this;
        }

        public Builder<E> data(E data) {
            this.data.add(data);
            return this;
        }

        public Builder<E> data(List<E> data) {
            this.data.addAll(data);
            return this;
        }

        public ResponseEntity<Response<E>> build() {

            if ( this.status >= 400 ) {
                this.errorCode = generateErrorCode();
            }

//          Desativado retorno de stackTrace
//            if ( this.exception != null ) {
//                this.stacktrace = ExceptionUtils.getStackTrace(this.exception);
//            }

            return new ResponseEntity<Response<E>>(new Response<E>(this), HttpStatus.valueOf(this.status));
        }

        public ResponseEntity<Object> buildObject() {
            if ( this.status >= 400 ) {
                this.errorCode = generateErrorCode();
            }

            return new ResponseEntity<Object>(new Response<E>(this), HttpStatus.valueOf(this.status));
        }

    }

    private static String generateErrorCode() {
        Random rand = new Random(System.nanoTime());

        return Long.toHexString(rand.nextLong()).toUpperCase();
    }

}
