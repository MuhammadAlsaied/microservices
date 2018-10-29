package com.muhammadalsaied.microservices.currencyexchange;

/**
 * Created by @author Muhammed Alsaied On Oct 26, 2018
 * muhammadalsaied96@gmail.com
 */
public class ExchangeValue {

    private final String from;
    private final String to;
    private final double multiplier;
    private final double quantity;
    private final double result;

    public ExchangeValue(String from, String to, double multiplier, double quantity) {
        this.from = from;
        this.to = to;
        this.multiplier = multiplier;
        this.quantity = quantity;
        this.result = quantity * multiplier;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getResult() {
        return result;
    }

}
