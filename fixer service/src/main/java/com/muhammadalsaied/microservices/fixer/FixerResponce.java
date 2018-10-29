package com.muhammadalsaied.microservices.fixer;

import java.util.Map;

/**
 * Created by @author Muhammed Alsaied On Oct 26, 2018
 * muhammadalsaied96@gmail.com
 */
public class FixerResponce {

    private boolean success = false;
    private Map<String, Double> rates;
    private Error error;

    @Override
    public String toString() {
        return "FixerResponce{" + "success=" + success + ", rates=" + rates + ", error=" + error + '}';
    }

    public boolean getSuccess() {
        return success;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public Error getError() {
        return error;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public void setError(Error error) {
        this.error = error;
    }

}

class Error {

    private int code;
    private String type;

    @Override
    public String toString() {
        return "Error{" + "code=" + code + ", type=" + type + '}';
    }

    public int getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setType(String type) {
        this.type = type;
    }

}
