package com.codejie.pms.service;

import com.codejie.pms.entity.*;

import java.text.ParseException;
import java.util.List;

public interface EmployeeService {

    /**
     * 个人薪资信息
     */
    List<Salary> getSalaryPoint(Salary salary, int pageNum, int pageSize);

    /**
     * Description 个人加分信息
     */
    List<AddPoint> getAddPoint(AddPoint addPoint, int pageNum, int pageSize);

    /**
     * Description 个人加分信息
     */
    List<DeletePoint> getDeletePoint(DeletePoint deletePoint, int pageNum, int pageSize);

    /**
     * Description 个人惩罚信息
     */
    List<Punish> getPunishList(Punish punish, int pageNum, int pageSize);

    /**
     * Description 个人奖励信息
     */
    List<Reward> getRewardList(Reward reward, int pageNum, int pageSize);

    /**
     * Description 我的部门信息
     */
    Department getUserDepartment(String userId);

    /**
     * Description 所有部门信息
     */
    List<Department> getDepartmentList(Department department, int pageNum, int pageSize);

    /**
     * Description 个人加班信息
     */
    List<OverWork> getOverWorkByUserId(OverWork overWork,int pageNum,int pageSize);
    /**
     * 添加加班信息
     */
    int insertOverWork(OverWork overWork);
    /**
     * 查看用户的请假信息
     */
    List<EmployLeave> getEmployLeaveByUserId(EmployLeave employLeave, int pageNum, int pageSize);
    /**
     * 添加请假申请
     */
    int insertEmployLeave(EmployLeave employLeave);

    /**
     * 修改请假状态
     */
    int updateLeaveStatus(EmployLeave employLeave);
    /**
     * 修改加班状态
     */
    int updateOverWorkStatus(OverWork overWork);
    /**
     * 员工签到
     */
    int signIn(CheckInfo checkInfo) throws ParseException;
    /**
     * 员工签退
     */
    int signOut(CheckInfo checkInfo) throws ParseException;
    /**
     * 查看个人考勤记录
     */
    List<CheckInfo> selectCheck(CheckInfo checkInfo, int pageNum, int pageSize);
    /**
     * 检查是否签到
     */
    int checkSignIn(String userId);
    /**
     * 检查是否签退
     */
    int checkSignOut(String userId);


}
