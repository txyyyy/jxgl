package com.codejie.pms.entity.dto;

import java.math.BigDecimal;

public class SalaryDto {
    private String userId;
    private String userName;
    private String departmentId;
    private String departmentName;
    private BigDecimal totalPay;
    private BigDecimal finalPay;
    private BigDecimal overTimePay;
    private BigDecimal lateCutPay;

    public SalaryDto() {
    }

    public SalaryDto(String userId, String userName, String departmentId, String departmentName, BigDecimal totalPay, BigDecimal finalPay, BigDecimal overTimePay, BigDecimal lateCutPay) {
        this.userId = userId;
        this.userName = userName;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.totalPay = totalPay;
        this.finalPay = finalPay;
        this.overTimePay = overTimePay;
        this.lateCutPay = lateCutPay;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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

    public BigDecimal getOverTimePay() {
        return overTimePay;
    }

    public void setOverTimePay(BigDecimal overTimePay) {
        this.overTimePay = overTimePay;
    }

    public BigDecimal getLateCutPay() {
        return lateCutPay;
    }

    public void setLateCutPay(BigDecimal lateCutPay) {
        this.lateCutPay = lateCutPay;
    }

    @Override
    public String toString() {
        return "SalaryDto{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", totalPay=" + totalPay +
                ", finalPay=" + finalPay +
                ", overTimePay=" + overTimePay +
                ", lateCutPay=" + lateCutPay +
                '}';
    }
}
