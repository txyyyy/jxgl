package com.codejie.pms.entity.dto;

import com.codejie.pms.entity.CheckInfo;

import java.util.List;

public class JXDto {
    private String userId;
    private String departmentId;
    private String departmentName;
    private String userName;
    private int overWorkNum;
    private int leaveNum;
    private int lateNum;
    private int beforeNum;
    private int queqinNum;
    private String month;
    private List<CheckInfo> checkInfos;

    public JXDto() {
    }

    public JXDto(String userId, String departmentId, String departmentName, String userName, int overWorkNum, int leaveNum, int lateNum, int beforeNum, int queqinNum, String month, List<CheckInfo> checkInfos) {
        this.userId = userId;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.userName = userName;
        this.overWorkNum = overWorkNum;
        this.leaveNum = leaveNum;
        this.lateNum = lateNum;
        this.beforeNum = beforeNum;
        this.queqinNum = queqinNum;
        this.month = month;
        this.checkInfos = checkInfos;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getOverWorkNum() {
        return overWorkNum;
    }

    public void setOverWorkNum(int overWorkNum) {
        this.overWorkNum = overWorkNum;
    }

    public int getLeaveNum() {
        return leaveNum;
    }

    public void setLeaveNum(int leaveNum) {
        this.leaveNum = leaveNum;
    }

    public int getLateNum() {
        return lateNum;
    }

    public void setLateNum(int lateNum) {
        this.lateNum = lateNum;
    }

    public int getBeforeNum() {
        return beforeNum;
    }

    public void setBeforeNum(int beforeNum) {
        this.beforeNum = beforeNum;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<CheckInfo> getCheckInfos() {
        return checkInfos;
    }

    public void setCheckInfos(List<CheckInfo> checkInfos) {
        this.checkInfos = checkInfos;
    }

    public int getQueqinNum() {
        return queqinNum;
    }

    public void setQueqinNum(int queqinNum) {
        this.queqinNum = queqinNum;
    }

    @Override
    public String toString() {
        return "JXDto{" +
                "userId='" + userId + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", userName='" + userName + '\'' +
                ", overWorkNum=" + overWorkNum +
                ", leaveNum=" + leaveNum +
                ", lateNum=" + lateNum +
                ", beforeNum=" + beforeNum +
                ", queqinNum=" + queqinNum +
                ", month='" + month + '\'' +
                ", checkInfos=" + checkInfos +
                '}';
    }
}
