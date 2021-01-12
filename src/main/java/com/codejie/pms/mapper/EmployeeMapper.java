package com.codejie.pms.mapper;

import com.codejie.pms.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工mapper
 */
public interface EmployeeMapper {

    /**
     * Description 个人加分信息
     */
    List<AddPoint> getAddPoint(AddPoint addPoint);

    /**
     * Description 个人加分信息
     */
    List<DeletePoint> getDeletePoint(DeletePoint deletePoint);

    /**
     * Description 个人惩罚信息
     */
    List<Punish> getPunishList(Punish punish);

    /**
     * Description 个人奖励信息
     */
    List<Reward> getRewardList(Reward reward);

    /**
     * Description 我的部门信息
     */
    Department getUserDepartment(String userId);

    /**
     * Description 所有部门信息
     */
    List<Department> getDepartmentList(Department department);

    /**
     * Description 优秀加分通报
     */
    List<UserExcellentKpi> getUserExcellentKpi(UserExcellentKpi userExcellentKpi);

    /**
     * 查看用户的加班信息
     */
    List<OverWork> getOverWorkByUserId(OverWork overWork);

    /**
     * 添加加班申请
     */
    int insertOverWork(OverWork overWork);

    /**
     * 根据用户Id查询用户信息
     */
    User selectUserInfo(String userId);

    /**
     * 查看用户的请假信息
     */
    List<EmployLeave> getEmployLeaveByUserId(EmployLeave employLeave);
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
    int signIn(CheckInfo checkInfo);
    /**
     * 员工签退
     */
    int signOut(CheckInfo checkInfo);
    /**
     * 查看个人考勤记录
     */
    List<CheckInfo> selectCheck(@Param("userId")String userId,@Param("thisDay")String thisDay);

    /**
     * 查看当天考勤信息是否打卡
     */
    CheckInfo checkSign(CheckInfo checkInfo);



}
