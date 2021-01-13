package com.codejie.pms.service.impl;

import com.codejie.pms.entity.*;
import com.codejie.pms.mapper.EmployeeMapper;
import com.codejie.pms.mapper.SalaryMapper;
import com.codejie.pms.service.EmployeeService;
import com.codejie.pms.util.DateUtil;
import com.github.pagehelper.PageHelper;
import com.sun.tools.javac.comp.Check;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;
    @Resource
    private SalaryMapper salaryMapper;

    @Override
    public List<Salary> getSalaryPoint(Salary salary, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Salary> list = salaryMapper.getSalaryMsgByUserId(salary);
        return list;
    }

    @Override
    public List<AddPoint> getAddPoint(AddPoint addPoint, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AddPoint> list = employeeMapper.getAddPoint(addPoint);
        return list;
    }

    @Override
    public List<DeletePoint> getDeletePoint(DeletePoint deletePoint, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return employeeMapper.getDeletePoint(deletePoint);
    }

    @Override
    public List<Punish> getPunishList(Punish punish, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return employeeMapper.getPunishList(punish);
    }

    @Override
    public List<Reward> getRewardList(Reward reward, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return employeeMapper.getRewardList(reward);
    }

    @Override
    public Department getUserDepartment(String userId) {
        return employeeMapper.getUserDepartment(userId);
    }

    public List<Department> getDepartmentList(Department department, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return employeeMapper.getDepartmentList(department);
    }

    @Override
    public List<OverWork> getOverWorkByUserId(OverWork overWork, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<OverWork> list = employeeMapper.getOverWorkByUserId(overWork);
        return list;
    }

    @Override
    public int insertOverWork(OverWork overWork) {
        String userId = overWork.getUserId();
        User user = employeeMapper.selectUserInfo(userId);
        overWork.setUserName(user.getUserName());
        overWork.setDepartmentId(user.getDepartmentId());
        return employeeMapper.insertOverWork(overWork);
    }

    @Override
    public List<EmployLeave> getEmployLeaveByUserId(EmployLeave employLeave, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return employeeMapper.getEmployLeaveByUserId(employLeave);
    }

    @Override
    public int insertEmployLeave(EmployLeave employLeave) {
        return employeeMapper.insertEmployLeave(employLeave);
    }

    @Override
    public int updateLeaveStatus(EmployLeave employLeave) {
        return employeeMapper.updateLeaveStatus(employLeave);
    }

    @Override
    public int updateOverWorkStatus(OverWork overWork) {
        return employeeMapper.updateOverWorkStatus(overWork);
    }

    @Override
    public int signIn(CheckInfo checkInfo) throws ParseException {
        String signDate = DateUtil.getDateTime();
        String beginTime = DateUtil.getNowTime();
        String endTime = signDate+" 08:30:00";
        if(DateUtil.contrastBefore(beginTime,endTime)){ //未迟到
            checkInfo.setSignInStatus(1);
        }else {                                         //迟到
            checkInfo.setSignInStatus(2);
        }
        checkInfo.setSignDate(signDate);
        checkInfo.setSignInTime(DateUtil.getNowTime());
        CheckInfo result = employeeMapper.selectCheckToday(checkInfo);
        if(result==null){                               //如果没有记录就新建当天记录
            User user = employeeMapper.selectUserInfo(checkInfo.getUserId());
            checkInfo.setUserName(user.getUserName());
            checkInfo.setDepartmentId(user.getDepartmentId());
            employeeMapper.insertCheckInfo(checkInfo);
        }

        return employeeMapper.signIn(checkInfo);
    }

    @Override
    public int signOut(CheckInfo checkInfo) throws ParseException {
        String signDate = DateUtil.getDateTime();
        String beginTime = DateUtil.getNowTime();
        String endTime = signDate+" 17:30:00";
        if(DateUtil.contrastBefore(beginTime,endTime)){ //早退
            checkInfo.setSignOutStatus(2);
        }else {                                         //未早退
            checkInfo.setSignOutStatus(1);
        }
        checkInfo.setSignDate(signDate);
        checkInfo.setSignOutTime(DateUtil.getNowTime());
        return employeeMapper.signOut(checkInfo);
    }

    @Override
    public List<CheckInfo> selectCheck(CheckInfo checkInfo, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        String thisDay=DateUtil.getDateTime();
        String userId=checkInfo.getUserId();
        return  employeeMapper.selectCheck(userId,thisDay);


    }

    @Override
    public int checkSignIn(String userId) {
        String signDate = DateUtil.getDateTime();
        CheckInfo checkInfo = new CheckInfo();
        checkInfo.setSignDate(signDate);
        checkInfo.setUserId(userId);
        CheckInfo result =employeeMapper.checkSign(checkInfo);
        if(result!=null){
            return result.getSignInStatus();
        }else {
            return 0;
        }
    }

    @Override
    public int checkSignOut(String userId) {
        String signDate = DateUtil.getDateTime();
        CheckInfo checkInfo = new CheckInfo();
        checkInfo.setSignDate(signDate);
        checkInfo.setUserId(userId);
        CheckInfo result =employeeMapper.checkSign(checkInfo);
        if(result!=null){
            return result.getSignOutStatus();
        }else {
            return 0;
        }
    }

}
