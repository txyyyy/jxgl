package com.codejie.pms.entity;

public class CheckInfo {
    private int id;

    private String userId;

    private String userName;

    private String signInTime;

    private String signOutTime;

    private String departmentId;

    private Integer signInStatus;

    private Integer signOutStatus;

    private String signDate;

    private String departmentName;

    public CheckInfo() {
    }

    public CheckInfo(int id, String userId, String userName, String signInTime, String signOutTime, String departmentId, Integer signInStatus, Integer signOutStatus, String signDate, String departmentName) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.signInTime = signInTime;
        this.signOutTime = signOutTime;
        this.departmentId = departmentId;
        this.signInStatus = signInStatus;
        this.signOutStatus = signOutStatus;
        this.signDate = signDate;
        this.departmentName = departmentName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(String signInTime) {
        this.signInTime = signInTime;
    }

    public String getSignOutTime() {
        return signOutTime;
    }

    public void setSignOutTime(String signOutTime) {
        this.signOutTime = signOutTime;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getSignInStatus() {
        return signInStatus;
    }

    public void setSignInStatus(Integer signInStatus) {
        this.signInStatus = signInStatus;
    }

    public Integer getSignOutStatus() {
        return signOutStatus;
    }

    public void setSignOutStatus(Integer signOutStatus) {
        this.signOutStatus = signOutStatus;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "CheckInfo{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", signInTime='" + signInTime + '\'' +
                ", signOutTime='" + signOutTime + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", signInStatus=" + signInStatus +
                ", signOutStatus=" + signOutStatus +
                ", signDate='" + signDate + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
