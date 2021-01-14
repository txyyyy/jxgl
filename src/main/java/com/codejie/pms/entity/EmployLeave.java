package com.codejie.pms.entity;

public class EmployLeave {
    private int id;
    private String jobNumber;
    private String userName;
    private String departmentId;
    private String leaveTime;
    private String leaveReason;
    private int leaveDuration;
    private int status;

    public EmployLeave() {
    }

    public EmployLeave(int id, String jobNumber, String userName, String departmentId, String leaveTime, String leaveReason, int leaveDuration, int status) {
        this.id = id;
        this.jobNumber = jobNumber;
        this.userName = userName;
        this.departmentId = departmentId;
        this.leaveTime = leaveTime;
        this.leaveReason = leaveReason;
        this.leaveDuration = leaveDuration;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public float getLeaveDuration() {
        return leaveDuration;
    }

    public void setLeaveDuration(int leaveDuration) {
        this.leaveDuration = leaveDuration;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "EmployLeave{" +
                "id=" + id +
                ", jobNumber='" + jobNumber + '\'' +
                ", userName='" + userName + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", leaveTime='" + leaveTime + '\'' +
                ", leaveReason='" + leaveReason + '\'' +
                ", leaveDuration=" + leaveDuration +
                ", status=" + status +
                '}';
    }
}
