package com.codejie.pms.service;

import com.codejie.pms.entity.*;

import java.util.List;

public interface HrService {

    String haveId(String id);

    void kpAdd(AddPoint addPoint)throws Exception;

    void kpDel(DeletePoint deletePoint)throws Exception;

    void kpReward(Reward reward)throws Exception;

    void kpPunish(Punish punish)throws Exception;

    List<UserExcellentKpi> userExcellentKpi();

    void addExcellentKpiTj(UserExcellentKpi userExcellentKpi);

    List<UserExcellentKpi> userExcellentKpiTj();

    void addExcellentKpi(UserExcellentKpi userExcellentKpi);

    void delExcellentKpiTj(UserExcellentKpi userExcellentKpi);

    List<DeletePoint> allDel(DeletePoint dp, int pageNum, int pageSize);
    DeletePoint getDel(DeletePoint deletePoint);

    void upDel(DeletePoint deletePoint);
    void delThis(DeletePoint deletePoint);

    List<AddPoint> allAdd(AddPoint ap, int pageNum, int pageSize);
    AddPoint getAdd(AddPoint addPoint);

    void upAdd(AddPoint addPoint);
    void delThis2(AddPoint addPoint);


    List<WeakCheck> allCheck();
    /**
     * 分页查看待审核请假信息（主任-本部门）
     */
    List<EmployLeave> selectWaitDepartmentLeave(String userId, int pageNum, int pageSize);

    /**
     * 分页查看待审核加班信息（主任-本部门）
     */
    List<OverWork> selectWaitDepartmentOverWork(String userId, int pageNum, int pageSize);

    /**
     * 分页查看本部门所有请假申请信息
     */
    List<EmployLeave> selectDepartmentLeave(String userId, int pageNum, int pageSize);
    /**
     * 分页查看本部门所有加班申请信息
     */
    List<OverWork> selectDepartmentOverWork(String userId, int pageNum, int pageSize);
    /**
     * 分页查看本部门考勤记录
     */
    List<CheckInfo> selectDepartmentCheck(String userId, int pageNum, int pageSize);
}
