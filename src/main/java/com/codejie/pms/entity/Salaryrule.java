package com.codejie.pms.entity;

import java.math.BigDecimal;

public class Salaryrule {
    private int id;
    private BigDecimal latePay;
    private BigDecimal overTimePay;
    private BigDecimal queQinPay;

    public Salaryrule() {
    }

    public Salaryrule(int id, BigDecimal latePay, BigDecimal overTimePay, BigDecimal queQinPay) {
        this.id = id;
        this.latePay = latePay;
        this.overTimePay = overTimePay;
        this.queQinPay = queQinPay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getLatePay() {
        return latePay;
    }

    public void setLatePay(BigDecimal latePay) {
        this.latePay = latePay;
    }

    public BigDecimal getOverTimePay() {
        return overTimePay;
    }

    public void setOverTimePay(BigDecimal overTimePay) {
        this.overTimePay = overTimePay;
    }

    public BigDecimal getQueQinPay() {
        return queQinPay;
    }

    public void setQueQinPay(BigDecimal queQinPay) {
        this.queQinPay = queQinPay;
    }

    @Override
    public String toString() {
        return "Salaryrule{" +
                "id=" + id +
                ", latePay=" + latePay +
                ", overTimePay=" + overTimePay +
                ", queQinPay=" + queQinPay +
                '}';
    }
}
