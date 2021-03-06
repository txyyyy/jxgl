package com.codejie.pms.entity;

public class OverWork {
    private int overworkId;
    private String userId;
    private String workDay;
    private String userName;
    private String workDesc;
    private String isRestDay;
    private String workTime;
    private String departmentId;
    private int status;

    public OverWork() {
    }

    public OverWork(int overworkId, String userId, String workDay, String userName, String workDesc, String isRestDay, String workTime, String departmentId, int status) {
        this.overworkId = overworkId;
        this.userId = userId;
        this.workDay = workDay;
        this.userName = userName;
        this.workDesc = workDesc;
        this.isRestDay = isRestDay;
        this.workTime = workTime;
        this.departmentId = departmentId;
        this.status = status;
    }

    public int getOverworkId() {
        return overworkId;
    }

    public void setOverworkId(int overworkId) {
        this.overworkId = overworkId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWorkDay() {
        return workDay;
    }

    public void setWorkDay(String workDay) {
        this.workDay = workDay;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWorkDesc() {
        return workDesc;
    }

    public void setWorkDesc(String workDesc) {
        this.workDesc = workDesc;
    }

    public String getIsRestDay() {
        return isRestDay;
    }

    public void setIsRestDay(String isRestDay) {
        this.isRestDay = isRestDay;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "OverWork{" +
                "overworkId=" + overworkId +
                ", userId='" + userId + '\'' +
                ", workDay='" + workDay + '\'' +
                ", userName='" + userName + '\'' +
                ", workDesc='" + workDesc + '\'' +
                ", isRestDay='" + isRestDay + '\'' +
                ", workTime='" + workTime + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", status=" + status +
                '}';
    }
}
