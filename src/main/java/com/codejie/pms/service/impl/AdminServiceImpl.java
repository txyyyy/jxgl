package com.codejie.pms.service.impl;

import com.codejie.pms.entity.*;
import com.codejie.pms.entity.dto.JXDto;
import com.codejie.pms.entity.dto.NameValueDto;
import com.codejie.pms.entity.dto.SalaryDto;
import com.codejie.pms.mapper.AdminMapper;
import com.codejie.pms.mapper.EmployeeMapper;
import com.codejie.pms.entity.dto.DepartmentDelDto;
import com.codejie.pms.mapper.HrMapper;
import com.codejie.pms.mapper.UserMapper;
import com.codejie.pms.service.AdminService;
import com.codejie.pms.util.DateUtil;
import com.github.pagehelper.PageHelper;
import com.sun.tools.javac.comp.Check;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;
    @Resource
    private EmployeeMapper employeeMapper;
    @Resource
    private UserMapper userMapper;



    @Override
    public Map<String, List> initSelect() {
        Map<String, List> list = new HashMap<>(3);
        list.put("quarter", adminMapper.initSelect1());
        list.put("weekNum", adminMapper.initSelect2());
        list.put("qdList", adminMapper.allDepartment());
        return list;
    }

    @Override
    public List<WeakCheck> getWeek2() {
        return adminMapper.getWeek2();
    }

    @Override
    public Map<String, List> getWeekData(String quarter, String weekNum, String departmentId) {

        Department department = new Department();
        List<DepartmentDelDto> dataList = adminMapper.getDepartmentCheckDel(quarter, weekNum);
        List<Department> departmentList = employeeMapper.getDepartmentList(department);
        List<DepartmentDelDto> dtoList = new ArrayList<>();
        departmentList.forEach(e -> {
            DepartmentDelDto dto = new DepartmentDelDto();
            dto.setDepartmentName(e.getDepartmentName());
            dto.setDepartmentId(e.getDepartmentId());
            dto.setCountDepartment(0);
            for (DepartmentDelDto t : dataList) {
                if (e.getDepartmentId().equals(t.getDepartmentId())) {
                    dto.setCountDepartment(t.getCountDepartment());
                    //break;
                }
            }
            dtoList.add(dto);
        });
        Map<String, List> maps = new HashMap<>();
        maps.put("checkList", departmentList);
        maps.put("dataList", dtoList);
        return maps;
    }

    @Override
    public Map<String, List> getWeekData2(String quarter, String weekNum) {

        List<String> weekList = adminMapper.weekList(quarter, weekNum);
        List<DepartmentDelDto> dataList = adminMapper.getWeekData2(quarter, weekNum);
        List<DepartmentDelDto> dtoList = new ArrayList<>();
        weekList.forEach(e -> {
            DepartmentDelDto dto = new DepartmentDelDto();
            java.sql.Date sqlDate = new java.sql.Date(DateUtil.stringToDate(e, "yyyy-MM-dd").getTime());
            dto.setAddTime(sqlDate);
            dto.setCountDepartment(0);
            for (DepartmentDelDto t : dataList) {
                if (e.equals(DateUtil.dateToString(t.getAddTime(), "yyyy-MM-dd"))) {
                    dto.setCountDepartment(t.getCountDepartment());
                    break;
                }
            }
            dtoList.add(dto);
        });
        Map<String, List> maps = new HashMap<>();
        maps.put("checkList", weekList);
        maps.put("dataList", dtoList);
        return maps;
    }

    @Override
    public List<NameValueDto> getWeekData3(String quarter, String weekNum, String departmentId) {
        List<DepartmentDelDto> dataList = adminMapper.getWeekData(quarter, weekNum, departmentId);
        List<WeakCheck> checkList = adminMapper.getWeek2();
        List<NameValueDto> dtoList = new ArrayList<>();
        checkList.forEach(e -> {
            for (DepartmentDelDto t : dataList) {
                NameValueDto dto = new NameValueDto();
                if (e.getCheckId() == t.getCheckId()) {
                    dto.setName(e.getCheckName());
                    dto.setValue(t.getCountDepartment().toString());
                    dtoList.add(dto);
                    break;
                }
            }
        });
        return dtoList;
    }

    @Override
    public List<NameValueDto> getWeekData4(String quarter, String weekNum) {
        List<String> weekList = adminMapper.weekList(quarter, weekNum);
        List<DepartmentDelDto> dataList = adminMapper.getWeekData2(quarter, weekNum);

        List<NameValueDto> dtoList = new ArrayList<>();
        weekList.forEach(e -> {
            for (DepartmentDelDto t : dataList) {
                NameValueDto dto = new NameValueDto();
                if (e.equals(DateUtil.dateToString(t.getAddTime(), "yyyy-MM-dd"))) {
                    dto.setName(e);
                    dto.setValue(t.getCountDepartment().toString());
                    dtoList.add(dto);
                    break;
                }
            }
        });
        return dtoList;
    }

    @Override
    public String stuCount() {
        return adminMapper.stuCount();
    }

    @Override
    public void upDepartment() {
        Department t = new Department();
        List<Department> departmentList = employeeMapper.getDepartmentList(t);
        departmentList.forEach(e -> adminMapper.upDepartment(e));
    }

    @Override
    public void kpAdd2(AddPoint addPoint) throws Exception {
        adminMapper.addPoint2(addPoint);
        adminMapper.kpAdd2(addPoint);
    }

    @Override
    public Department selectDepartment(Department department) {
        return adminMapper.selectDepartment(department);
    }





    @Override
    public List<Kpi> selectKpiList4(Kpi kpi, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return adminMapper.selectKpiList4();
    }
    @Override
    public List<Kpi> selectKpiList4() {
        return adminMapper.selectKpiList4();
    }

    @Override
    public void kpAdd4(AddPointDepartment addPointDepartment) throws Exception {
        adminMapper.addPoint4(addPointDepartment);
        adminMapper.kpAdd4(addPointDepartment);
    }

    @Override
    public List<Kpi> selectKpiList5(Kpi kpi, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return adminMapper.selectKpiList5();
    }
    @Override
    public List<Kpi> selectKpiList5() {
        return adminMapper.selectKpiList5();
    }

    public void kpAdd5(AddPointDepartment addPointDepartment) throws Exception {
        adminMapper.addPoint5(addPointDepartment);
        adminMapper.kpAdd5(addPointDepartment);
    }

    @Override
    public List<Kpi> selectKpiList6(Kpi kpi, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return adminMapper.selectKpiList6();
    }
    @Override
    public List<Kpi> selectKpiList6() {
        return adminMapper.selectKpiList6();
    }

    public void kpAdd6(AddPointDepartment addPointDepartment) throws Exception {
        adminMapper.addPoint6(addPointDepartment);
        adminMapper.kpAdd6(addPointDepartment);
    }

    @Override
    public List<Kpi> selectKpiList7(Kpi kpi, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return adminMapper.selectKpiList7();
    }
    @Override
    public List<Kpi> selectKpiList7() {
        return adminMapper.selectKpiList7();
    }

    public void kpAdd7(AddPointDepartment addPointDepartment) throws Exception {
        adminMapper.addPoint7(addPointDepartment);
        adminMapper.kpAdd6(addPointDepartment);
    }

    @Override
    public List<Kpi> selectKpiList8(Kpi kpi, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return adminMapper.selectKpiList8();
    }
    @Override
    public List<Kpi> selectKpiList8() {
        return adminMapper.selectKpiList8();
    }

    public void kpAdd8(AddPointDepartment addPointDepartment) throws Exception {
        adminMapper.addPoint8(addPointDepartment);
        adminMapper.kpAdd8(addPointDepartment);
    }

    @Override
    public List<Kpi> selectKpiList9(Kpi kpi, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return adminMapper.selectKpiList9();
    }
    @Override
    public List<Kpi> selectKpiList9() {
        return adminMapper.selectKpiList9();
    }

    public void kpAdd9(AddPointDepartment addPointDepartment) throws Exception {
        adminMapper.addPoint9(addPointDepartment);
        adminMapper.kpAdd9(addPointDepartment);
    }

    @Override
    public List<Kpi> selectKpiList10(Kpi kpi, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return adminMapper.selectKpiList10();
    }
    @Override
    public List<Kpi> selectKpiList10() {
        return adminMapper.selectKpiList10();
    }

    public void kpAdd10(AddPointDepartment addPointDepartment) throws Exception {
        adminMapper.addPoint10(addPointDepartment);
        adminMapper.kpAdd10(addPointDepartment);
    }

    @Override
    public List<Kpi> selectKpiList11(Kpi kpi, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return adminMapper.selectKpiList11();
    }
    @Override
    public List<Kpi> selectKpiList11() {
        return adminMapper.selectKpiList11();
    }

    public void kpAdd11(AddPointDepartment addPointDepartment) throws Exception {
        adminMapper.addPoint11(addPointDepartment);
        adminMapper.kpAdd11(addPointDepartment);
    }

    @Override
    public List<Kpi> selectKpiList12(Kpi kpi, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return adminMapper.selectKpiList12();
    }
    @Override
    public List<Kpi> selectKpiList12() {
        return adminMapper.selectKpiList12();
    }

    public void kpAdd12(AddPointDepartment addPointDepartment) throws Exception {
        adminMapper.addPoint12(addPointDepartment);
        adminMapper.kpAdd12(addPointDepartment);
    }

    @Override
    public List<Kpi> selectKpiList13(Kpi kpi, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return adminMapper.selectKpiList13();
    }
    @Override
    public List<Kpi> selectKpiList13() {
        return adminMapper.selectKpiList13();
    }

    public void kpAdd13(AddPointDepartment addPointDepartment) throws Exception {
        adminMapper.addPoint13(addPointDepartment);
        adminMapper.kpAdd13(addPointDepartment);
    }

    @Override
    public List<EmployLeave> selectAllEmployLeave(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return adminMapper.selectAllEmployLeave();
    }

    @Override
    public List<OverWork> selectAllOverWork(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return adminMapper.selectAllOverWork();
    }

    @Override
    public List<EmployLeave> selectWaitEmployLeave(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return adminMapper.selectWaitEmployLeave();
    }

    @Override
    public List<OverWork> selectWaitOverWork(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return adminMapper.selectWaitOverWork();
    }

    @Override
    public List<CheckInfo> selectAllCheck(int pageNum, int pageSize) {

        List<EmployLeave> leaveList=new ArrayList<>();
        PageHelper.startPage(pageNum,pageSize);
        String thisDay=DateUtil.getDateTime();
        List<CheckInfo> result = adminMapper.selectAllCheck(thisDay);//考勤信息集合
        List<EmployLeave> leaveMsg = employeeMapper.selectAllEmployLeave();//请假信息集合
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

    @Override
    public List<JXDto> getAllCheckCountInfo(String month,int pageNum, int pageSize) {
        List<User> list = userMapper.selectAllUserInfo();
        List<JXDto> jxDtos = new ArrayList<>();
        if(list==null||list.size()==0){
            return null;
        }
        for(int i=0;i<list.size();i++){
            int lateNum=0;
            int beforeNum=0;
            int leaveNum=0;
            int overWorkNum=0;
            int queqinNum=0;
            if(list.get(i).getPermissionDegree()!=1 && list.get(i).getPermissionDegree()!=2){
                Department department = new Department();
                department.setDepartmentId(list.get(i).getDepartmentId());
                JXDto jxDto = new JXDto();
                List<CheckInfo> checkInfos = new ArrayList<>();
                String userId=list.get(i).getUserId();
                String thisDay = DateUtil.getDateTime();
                checkInfos=adminMapper.selectCheckByMonth(month,userId,thisDay);
                jxDto.setMonth(month);
                jxDto.setUserId(userId);
                jxDto.setDepartmentId(list.get(i).getDepartmentId());
                jxDto.setUserName(list.get(i).getUserName());
                Department resultDepartment = adminMapper.selectDepartment(department);
                jxDto.setDepartmentName(resultDepartment.getDepartmentName());
                if(checkInfos!=null && checkInfos.size()!=0){
                    for(int j=0;j<checkInfos.size();j++){
                        if(checkInfos.get(j).getSignInStatus()==0){
                            queqinNum++;
                        }else if(checkInfos.get(j).getSignInStatus()==2){
                            lateNum++;
                        }else if(checkInfos.get(j).getSignInStatus()==3){
                            leaveNum++;
                        }
                        if(checkInfos.get(j).getSignOutStatus()==0){
                            queqinNum++;
                        }else if(checkInfos.get(j).getSignOutStatus()==2){
                            beforeNum++;
                        }
                    }
                }

                jxDto.setBeforeNum(beforeNum);
                jxDto.setLateNum(lateNum);
                jxDto.setLeaveNum(leaveNum);
                jxDto.setQueqinNum(queqinNum);
                overWorkNum=adminMapper.countOverWorkByMonth(userId,month);
                jxDto.setOverWorkNum(overWorkNum);
                jxDto.setCheckInfos(checkInfos);
                jxDtos.add(jxDto);
            }
        }
        return jxDtos;
    }

    @Override
    public List<SalaryDto> checkAllSalaryInfosByMonth(String month, int pageNum, int pageSize) {
        List<User> users = userMapper.selectAllUserInfo();
        List<SalaryDto> salaryDtos = new ArrayList<>();
        PageHelper.startPage(pageNum,pageSize);
        if(users==null||users.size()==0){
            return null;
        }
        for(int i =0;i<users.size();i++){
            SalaryDto salaryDto = new SalaryDto();
            Department department = new Department();
            User user = users.get(i);
            salaryDto.setDepartmentId(user.getDepartmentId());
            salaryDto.setUserId(user.getUserId());
            salaryDto.setUserName(user.getUserName());
            department.setDepartmentId(user.getDepartmentId());
            Department resultDepartment = adminMapper.selectDepartment(department);
            salaryDto.setDepartmentName(resultDepartment.getDepartmentName());
            Salary salary =adminMapper.selectUserSalaryByMonth(month,user.getUserId());
            if(salary!=null){
                salaryDto.setFinalPay(salary.getFinalPay());
                salaryDto.setLateCutPay(salary.getLateCutPay());
                salaryDto.setOverTimePay(salary.getOverTimePay());
                salaryDto.setTotalPay(salary.getTotalPay());
            }
            salaryDtos.add(salaryDto);

        }
        return salaryDtos;
    }

    @Override
    public int updateSalaryRule(BigDecimal latePay, BigDecimal overTimepay) {
        return adminMapper.updateSalaryRule(latePay,overTimepay);
    }

    @Override
    public Salaryrule selectSalaryRule() {
        return adminMapper.selectSalaryRule();
    }

    @Override
    public Boolean selectMonthSalary(String month) {
        int count = adminMapper.selectMonthSalary(month);
        if(count>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int insertSalaryByMonth(String month) {
        List<User> users=userMapper.selectAllUserInfo();
        if(users==null||users.size()==0){
            return 0;
        }
        for(int i =0;i<users.size();i++){
            User user = users.get(i);
            String userId = user.getUserId();
            int queQinComeCount=0;
            int queQinGoCount=0;
            int queQinCount=0;
            int late=0;
            int before=0;
            int lateCount=0;

            Salaryrule salaryrule = adminMapper.selectSalaryRule();
            BigDecimal latePay=salaryrule.getLatePay();
            BigDecimal overTimeAddPay = salaryrule.getOverTimePay();
            BigDecimal queQinPay = salaryrule.getQueQinPay();
            List<CheckInfo> checkInfos = adminMapper.selectCheckInfoByUserIdOnMonth(userId,month);//获得当月缺勤次数
            int overworkTime = 0;
            List<OverWork> overWorks = adminMapper.selectOverWorkOnMonth(userId,month);
            if(checkInfos.size()>0&&checkInfos!=null){
                for(int j=0;j<checkInfos.size();j++){
                    if(checkInfos.get(j).getSignInStatus()==0){
                        queQinComeCount++;
                    }else if(checkInfos.get(j).getSignInStatus()==2){
                        late++;
                    }
                    if(checkInfos.get(j).getSignOutStatus()==0){
                        queQinGoCount++;
                    }else if(checkInfos.get(j).getSignOutStatus()==2){
                        before++;
                    }
                }
            }
            for(int k=0;k<overWorks.size();k++){
                overworkTime=overworkTime+Integer.parseInt(overWorks.get(k).getWorkTime());
            }
                    lateCount=late+before;
                    queQinCount=queQinComeCount+queQinGoCount;

                    BigDecimal lateCutPay= salaryrule.getLatePay().multiply(BigDecimal.valueOf(lateCount));
                    BigDecimal overTimePay=overTimeAddPay.multiply(BigDecimal.valueOf(overworkTime));
                    Salary salary = new Salary();
                    salary.setLateCount(lateCount);
                    salary.setSalaryMonth(month);
                    salary.setUserId(userId);
                    salary.setLateCutPay(lateCutPay);
                    salary.setOverTimePay(overTimePay);
                    salary.setOverTimeCount(overworkTime);
                    salary.setTotalPay(user.getSalary());
                    salary.setFinalPay(user.getSalary().add(overTimePay).subtract(lateCutPay));
                    adminMapper.createSalary(salary);


        }
        return 1;
    }

    @Override
    public int updateSalary(String month) {
        List<User> users=userMapper.selectAllUserInfo();
        if(users==null||users.size()==0){
            return 0;
        }
        for(int i=0;i<users.size();i++){
            User user = users.get(i);
            String userId=user.getUserId();
            Salary checkSalary = adminMapper.selectByMonthAndUserId(userId,month);
            if(checkSalary==null){
                int queQinComeCount=0;
                int queQinGoCount=0;
                int queQinCount=0;
                int late=0;
                int before=0;
                int lateCount=0;

                Salaryrule salaryrule = adminMapper.selectSalaryRule();
                BigDecimal latePay=salaryrule.getLatePay();
                BigDecimal overTimeAddPay = salaryrule.getOverTimePay();
                BigDecimal queQinPay = salaryrule.getQueQinPay();
                List<CheckInfo> checkInfos = adminMapper.selectCheckInfoByUserIdOnMonth(userId,month);//获得当月缺勤次数
                int overworkTime = 0;
                List<OverWork> overWorks = adminMapper.selectOverWorkOnMonth(userId,month);
                if(checkInfos.size()>0&&checkInfos!=null){
                    for(int j=0;j<checkInfos.size();j++){
                        if(checkInfos.get(j).getSignInStatus()==0){
                            queQinComeCount++;
                        }else if(checkInfos.get(j).getSignInStatus()==2){
                            late++;
                        }
                        if(checkInfos.get(j).getSignOutStatus()==0){
                            queQinGoCount++;
                        }else if(checkInfos.get(j).getSignOutStatus()==2){
                            before++;
                        }
                    }
                }
                for(int k=0;k<overWorks.size();k++){
                    overworkTime=overworkTime+Integer.parseInt(overWorks.get(k).getWorkTime());
                }
                lateCount=late+before;
                queQinCount=queQinComeCount+queQinGoCount;

                BigDecimal lateCutPay= salaryrule.getLatePay().multiply(BigDecimal.valueOf(lateCount));
                BigDecimal overTimePay=overTimeAddPay.multiply(BigDecimal.valueOf(overworkTime));
                Salary salary = new Salary();
                salary.setLateCount(lateCount);
                salary.setSalaryMonth(month);
                salary.setUserId(userId);
                salary.setLateCutPay(lateCutPay);
                salary.setOverTimePay(overTimePay);
                salary.setOverTimeCount(overworkTime);
                salary.setTotalPay(user.getSalary());
                salary.setFinalPay(user.getSalary().add(overTimePay).subtract(lateCutPay));
                adminMapper.createSalary(salary);


            }else {
                int queQinComeCount=0;
                int queQinGoCount=0;
                int queQinCount=0;
                int late=0;
                int before=0;
                int lateCount=0;

                Salaryrule salaryrule = adminMapper.selectSalaryRule();
                BigDecimal latePay=salaryrule.getLatePay();
                BigDecimal overTimeAddPay = salaryrule.getOverTimePay();
                BigDecimal queQinPay = salaryrule.getQueQinPay();
                List<CheckInfo> checkInfos = adminMapper.selectCheckInfoByUserIdOnMonth(userId,month);//获得当月缺勤次数
                int overworkTime = 0;
                List<OverWork> overWorks = adminMapper.selectOverWorkOnMonth(userId,month);
                if(checkInfos.size()>0&&checkInfos!=null){
                    for(int j=0;j<checkInfos.size();j++){
                        if(checkInfos.get(j).getSignInStatus()==0){
                            queQinComeCount++;
                        }else if(checkInfos.get(j).getSignInStatus()==2){
                            late++;
                        }
                        if(checkInfos.get(j).getSignOutStatus()==0){
                            queQinGoCount++;
                        }else if(checkInfos.get(j).getSignOutStatus()==2){
                            before++;
                        }
                    }
                }
                for(int k=0;k<overWorks.size();k++){
                    overworkTime=overworkTime+Integer.parseInt(overWorks.get(k).getWorkTime());
                }
                lateCount=late+before;
                queQinCount=queQinComeCount+queQinGoCount;

                BigDecimal lateCutPay= salaryrule.getLatePay().multiply(BigDecimal.valueOf(lateCount));
                BigDecimal overTimePay=overTimeAddPay.multiply(BigDecimal.valueOf(overworkTime));
                Salary salary = new Salary();
                salary.setLateCount(lateCount);
                salary.setSalaryMonth(month);
                salary.setUserId(userId);
                salary.setLateCutPay(lateCutPay);
                salary.setOverTimePay(overTimePay);
                salary.setOverTimeCount(overworkTime);
                salary.setTotalPay(user.getSalary());
                salary.setFinalPay(user.getSalary().add(overTimePay).subtract(lateCutPay));
                adminMapper.updateSalary(salary);
            }
        }
        return 0;
    }
}
