package com.codejie.pms.mapper;

import com.codejie.pms.entity.*;
import java.util.List;

/**
 * 员工mapper
 */
public interface HrMapper {

    List<User> getStuMsg(User user);

    String haveId(String id);

    void kpAdd(AddPoint addPoint);

    void kpDel(DeletePoint deletePoint);

    void kpReward(Reward reward);

    void kpPunish(Punish punish);

    void addPoint(AddPoint addPoint);
    void delPoint(DeletePoint deletePoint);

    void addExcellentKpiTj(UserExcellentKpi userExcellentKpi);

    void addExcellentKpi(UserExcellentKpi userExcellentKpi);

    void delExcellentKpiTj(UserExcellentKpi userExcellentKpi);

    List<UserExcellentKpi> userExcellentKpi();
    List<UserExcellentKpi> userExcellentKpiTj();

    List<DeletePoint> allDel(DeletePoint dp);
    DeletePoint getDel(DeletePoint deletePoint);

    void upDel(DeletePoint deletePoint);
    void delThis(DeletePoint deletePoint);

    List<AddPoint> allAdd(AddPoint ap);
    AddPoint getAdd(AddPoint addPoint);

    void upAdd(AddPoint addPoint);
    void delThis2(AddPoint addPoint);

    List<WeakCheck> allCheck();

    /**
     * 分页查看待审核请假信息（主任-本部门）
     */
    List<EmployLeave> selectWaitDepartmentLeave(EmployLeave employLeave);

    /**
     * 分页查看待审核加班信息（主任-本部门）
     */
    List<OverWork> selectWaitDepartmentOverWork(OverWork overWork);
    /**
     * 分页查看本部门所有请假申请信息
     */
    List<EmployLeave> selectDepartmentLeave(EmployLeave employLeave);
    /**
     * 分页查看本部门所有加班申请信息
     */
    List<OverWork> selectDepartmentOverWork(OverWork overWork);
    /**
     * 分页查看本部门考勤记录
     */
    List<CheckInfo> selectDepartmentCheck(CheckInfo checkInfo);

}
