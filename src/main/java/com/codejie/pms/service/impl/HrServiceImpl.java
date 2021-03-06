package com.codejie.pms.service.impl;

import com.codejie.pms.entity.*;
import com.codejie.pms.mapper.EmployeeMapper;
import com.codejie.pms.util.DateUtil;
import com.github.pagehelper.PageHelper;
import com.codejie.pms.mapper.HrMapper;
import com.codejie.pms.service.HrService;
import com.sun.xml.internal.rngom.ast.util.CheckingSchemaBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class HrServiceImpl implements HrService {

    @Resource
    private HrMapper hrMapper;
    @Resource
    private EmployeeMapper employeeMapper;
    @Override
    public String haveId(String id) {
        String userId = hrMapper.haveId(id);
        if (StringUtils.isEmpty(userId)) {
            return "false";
        }
        return "true";
    }

    @Override
    public void kpAdd(AddPoint addPoint) throws Exception {
        hrMapper.addPoint(addPoint);
        hrMapper.kpAdd(addPoint);
    }

    @Override
    public void kpDel(DeletePoint deletePoint) throws Exception {
        hrMapper.delPoint(deletePoint);
        hrMapper.kpDel(deletePoint);
    }

    @Override
    public void kpReward(Reward reward) throws Exception {
        hrMapper.kpReward(reward);
    }

    @Override
    public void kpPunish(Punish punish) throws Exception {
        hrMapper.kpPunish(punish);
    }

    @Override
    public List<UserExcellentKpi> userExcellentKpi() {
        return hrMapper.userExcellentKpi();
    }

    @Override
    public void addExcellentKpiTj(UserExcellentKpi userExcellentKpi) {
        hrMapper.addExcellentKpiTj(userExcellentKpi);
    }

    @Override
    public List<UserExcellentKpi> userExcellentKpiTj() {
        return hrMapper.userExcellentKpiTj();
    }

    @Override
    public void addExcellentKpi(UserExcellentKpi userExcellentKpi) {
        AddPoint addPoint = new AddPoint();
        addPoint.setUserId(userExcellentKpi.getUserId());
        addPoint.setUserName(userExcellentKpi.getUserName());
        addPoint.setAddPoint(userExcellentKpi.getKpiPoint());
        hrMapper.addPoint(addPoint);
        hrMapper.addExcellentKpi(userExcellentKpi);
        hrMapper.delExcellentKpiTj(userExcellentKpi);

      /*  DeletePoint deletePoint = new DeletePoint();
        deletePoint.setUserId(userExcellentKpi.getUserId());
        deletePoint.setUserName(userExcellentKpi.getUserName());
        deletePoint.setDeletePoint(userExcellentKpi.getKpiPoint());
        hrMapper.delPoint(deletePoint);
        hrMapper.addExcellentKpi(userExcellentKpi);
        hrMapper.delExcellentKpiTj(userExcellentKpi);*/
    }

    @Override
    public void delExcellentKpiTj(UserExcellentKpi userExcellentKpi) {
        hrMapper.delExcellentKpiTj(userExcellentKpi);
    }

    @Override
    public List<DeletePoint> allDel(DeletePoint dp, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return hrMapper.allDel(dp);
    }
    @Override
    public List<AddPoint> allAdd(AddPoint ap, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return hrMapper.allAdd(ap);

    }
    @Override
    public DeletePoint getDel(DeletePoint deletePoint) {
        return hrMapper.getDel(deletePoint);
    }
    @Override
    public AddPoint getAdd(AddPoint addPoint) {
        return hrMapper.getAdd(addPoint);

    }
    @Override
    public void upDel(DeletePoint deletePoint) {
        hrMapper.upDel(deletePoint);
    }
    @Override
    public void upAdd(AddPoint addPoint) {
        hrMapper.upAdd(addPoint);
    }
    @Override
    public void delThis(DeletePoint deletePoint) {
        AddPoint addPoint = new AddPoint();
        addPoint.setUserId(deletePoint.getUserId());
        addPoint.setAddPoint(deletePoint.getDeletePoint());
        hrMapper.addPoint(addPoint);
        hrMapper.delThis(deletePoint);
    }
    @Override
    public void delThis2(AddPoint addPoint) {
        DeletePoint deletePoint = new DeletePoint();
        deletePoint.setUserId(addPoint.getUserId());
        deletePoint.setDeletePoint(addPoint.getAddPoint());
        hrMapper.delPoint(deletePoint);
        hrMapper.delThis2(addPoint);
    }









    @Override
    public List<WeakCheck> allCheck() {
        return hrMapper.allCheck();
    }

    @Override
    public List<EmployLeave> selectWaitDepartmentLeave(String userId, int pageNum, int pageSize) {
        User user = employeeMapper.selectUserInfo(userId);
        EmployLeave employLeave = new EmployLeave();
        employLeave.setDepartmentId(user.getDepartmentId());
        PageHelper.startPage(pageNum, pageSize);
        return hrMapper.selectWaitDepartmentLeave(employLeave);
    }

    @Override
    public List<OverWork> selectWaitDepartmentOverWork(String userId, int pageNum, int pageSize) {
        User user = employeeMapper.selectUserInfo(userId);
        OverWork overWork = new OverWork();
        overWork.setDepartmentId(user.getDepartmentId());
        PageHelper.startPage(pageNum, pageSize);
        return hrMapper.selectWaitDepartmentOverWork(overWork);
    }

    @Override
    public List<EmployLeave> selectDepartmentLeave(String userId, int pageNum, int pageSize) {
        User user = employeeMapper.selectUserInfo(userId);
        EmployLeave employLeave = new EmployLeave();
        employLeave.setDepartmentId(user.getDepartmentId());
        PageHelper.startPage(pageNum, pageSize);
        return hrMapper.selectDepartmentLeave(employLeave);
    }

    @Override
    public List<OverWork> selectDepartmentOverWork(String userId, int pageNum, int pageSize) {
        User user = employeeMapper.selectUserInfo(userId);
        OverWork overWork = new OverWork();
        overWork.setDepartmentId(user.getDepartmentId());
        PageHelper.startPage(pageNum, pageSize);
        return hrMapper.selectDepartmentOverWork(overWork);
    }

    @Override
    public List<CheckInfo> selectDepartmentCheck(String userId, int pageNum, int pageSize) {
        User user = employeeMapper.selectUserInfo(userId);
        List<EmployLeave> leaveList=new ArrayList<>();
        PageHelper.startPage(pageNum,pageSize);
        String thisDay= DateUtil.getDateTime();
        List<CheckInfo> result = hrMapper.selectDepartmentCheck(user.getDepartmentId(),thisDay);//考勤信息集合
        List<EmployLeave> leaveMsg = hrMapper.selectEmployLeaveByDepartment(user.getDepartmentId());//请假信息集合
        if(result.size()>0 || result!=null){
            if(leaveMsg!=null){
                for(int i=0;i<leaveMsg.size();i++) {
                    String leaveDate = leaveMsg.get(i).getLeaveTime();
                    for(int j=0;j<leaveMsg.get(i).getLeaveDuration();j++) { //将请假天数拆成每一个日期
                        EmployLeave employLeave = new EmployLeave();
                        employLeave.setLeaveTime(DateUtil.addDay(leaveDate,j));
                        leaveList.add(employLeave);
                    }
                }

                for(int i=0;i<result.size();i++){  //遍历请假的日期集合，将考勤表对应的日期的状态改成请假

                    for(int j=0;j<leaveList.size();j++){
                        System.out.println(leaveList.get(j).getLeaveTime());
                        if(result.get(i).getSignDate().equals(leaveList.get(j).getLeaveTime())){
                            result.get(i).setSignInStatus(3);
                            result.get(i).setSignOutStatus(3);
                            result.get(i).setSignInTime("");
                            result.get(i).setSignOutTime("");
                        }
                    }
                }
                return result;

            }else {
                return result;
            }
        }

        return null;
    }
}
