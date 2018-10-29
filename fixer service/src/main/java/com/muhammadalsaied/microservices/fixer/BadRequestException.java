package com.muhammadalsaied.microservices.fixer;

/**
 * Created by @author Muhammed Alsaied On Oct 26, 2018
 * muhammadalsaied96@gmail.com
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String msg) {
        super(msg);
    }

}
