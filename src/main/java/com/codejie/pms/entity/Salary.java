package com.codejie.pms.entity;

import java.math.BigDecimal;

public class Salary {
    private int salaryId;

    private String userId;

    private String userName;

    private BigDecimal totalPay;

    private BigDecimal finalPay;

    private int lateCount;

    private BigDecimal lateCutPay;

    private int overTimeCount;

    private BigDecimal overTimePay;

    private String salaryMonth;

    public Salary() {

    }

    public Salary(int salaryId, String userId, String userName, BigDecimal totalPay, BigDecimal finalPay, int lateCount, BigDecimal lateCutPay, int overTimeCount, BigDecimal overTimePay, String salaryMonth) {
        this.salaryId = salaryId;
        this.userId = userId;
        this.userName = userName;
        this.totalPay = totalPay;
        this.finalPay = finalPay;
        this.lateCount = lateCount;
        this.lateCutPay = lateCutPay;
        this.overTimeCount = overTimeCount;
        this.overTimePay = overTimePay;
        this.salaryMonth = salaryMonth;
    }

    public int getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(BigDecimal totalPay) {
        this.totalPay = totalPay;
    }

    public BigDecimal getFinalPay() {
        return finalPay;
    }

    public void setFinalPay(BigDecimal finalPay) {
        this.finalPay = finalPay;
    }

    public int getLateCount() {
        return lateCount;
    }

    public void setLateCount(int lateCount) {
        this.lateCount = lateCount;
    }

    public BigDecimal getLateCutPay() {
        return lateCutPay;
    }

    public void setLateCutPay(BigDecimal lateCutPay) {
        this.lateCutPay = lateCutPay;
    }

    public int getOverTimeCount() {
        return overTimeCount;
    }

    public void setOverTimeCount(int overTimeCount) {
        this.overTimeCount = overTimeCount;
    }

    public BigDecimal getOverTimePay() {
        return overTimePay;
    }

    public void setOverTimePay(BigDecimal overTimePay) {
        this.overTimePay = overTimePay;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSalaryMonth() {
        return salaryMonth;
    }

    public void setSalaryMonth(String salaryMonth) {
        this.salaryMonth = salaryMonth;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "salaryId=" + salaryId +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", totalPay=" + totalPay +
                ", finalPay=" + finalPay +
                ", lateCount=" + lateCount +
                ", lateCutPay=" + lateCutPay +
                ", overTimeCount=" + overTimeCount +
                ", overTimePay=" + overTimePay +
                ", salaryMonth='" + salaryMonth + '\'' +
                '}';
    }
}
