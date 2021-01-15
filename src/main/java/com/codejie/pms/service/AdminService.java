package com.codejie.pms.service;

import com.codejie.pms.entity.*;
import com.codejie.pms.entity.dto.JXDto;
import com.codejie.pms.entity.dto.NameValueDto;
import com.codejie.pms.entity.dto.SalaryDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface AdminService {

    Map<String, List> initSelect();

    List<WeakCheck> getWeek2();

    Map<String, List> getWeekData(String quarter, String weekNum, String departmentId);

    Map<String, List> getWeekData2(String quarter, String weekNum);

    List<NameValueDto> getWeekData3(String quarter, String weekNum, String departmentId);

    List<NameValueDto> getWeekData4(String quarter, String weekNum);

    String stuCount();
    void upDepartment();

    void kpAdd2(AddPoint addPoint)throws Exception;

    Department selectDepartment(Department department);




    /**
     * Description 开发部kpi列表1
     */
    List<Kpi> selectKpiList4(Kpi kpi, int pageNum, int pageSize);
    List<Kpi> selectKpiList4();

    void kpAdd4(AddPointDepartment addPointDepartment)throws Exception;

    /**
     * Description 开发部kpi列表2
     */
    List<Kpi> selectKpiList5(Kpi kpi, int pageNum, int pageSize);
    List<Kpi> selectKpiList5();

    void kpAdd5(AddPointDepartment addPointDepartment)throws Exception;

    /**
     * Description 运营部kpi列表1
     */
    List<Kpi> selectKpiList6(Kpi kpi, int pageNum, int pageSize);
    List<Kpi> selectKpiList6();

    void kpAdd6(AddPointDepartment addPointDepartment)throws Exception;

    /**
     * Description 运营部kpi列表2
     */
    List<Kpi> selectKpiList7(Kpi kpi, int pageNum, int pageSize);
    List<Kpi> selectKpiList7();

    void kpAdd7(AddPointDepartment addPointDepartment)throws Exception;

    /**
     * Description 人事部kpi列表1
     */
    List<Kpi> selectKpiList8(Kpi kpi, int pageNum, int pageSize);
    List<Kpi> selectKpiList8();

    void kpAdd8(AddPointDepartment addPointDepartment)throws Exception;

    /**
     * Description 人事部kpi列表2
     */
    List<Kpi> selectKpiList9(Kpi kpi, int pageNum, int pageSize);
    List<Kpi> selectKpiList9();

    void kpAdd9(AddPointDepartment addPointDepartment)throws Exception;
    /**
     * Description 市场部kpi列表1
     */
    List<Kpi> selectKpiList10(Kpi kpi, int pageNum, int pageSize);
    List<Kpi> selectKpiList10();

    void kpAdd10(AddPointDepartment addPointDepartment)throws Exception;
    /**
     * Description 市场部kpi列表2
     */
    List<Kpi> selectKpiList11(Kpi kpi, int pageNum, int pageSize);
    List<Kpi> selectKpiList11();

    void kpAdd11(AddPointDepartment addPointDepartment)throws Exception;
    /**
     * Description 财务部kpi列表1
     */
    List<Kpi> selectKpiList12(Kpi kpi, int pageNum, int pageSize);
    List<Kpi> selectKpiList12();

    void kpAdd12(AddPointDepartment addPointDepartment)throws Exception;
    /**
     * Description 财务部kpi列表2
     */
    List<Kpi> selectKpiList13(Kpi kpi, int pageNum, int pageSize);
    List<Kpi> selectKpiList13();

    void kpAdd13(AddPointDepartment addPointDepartment)throws Exception;
    /**
     * 分页查看所有请假信息(管理员)
     */
    List<EmployLeave> selectAllEmployLeave(int pageNum, int pageSize);
    /**
     * 分页查看所有加班信息(管理员)
     */
    List<OverWork> selectAllOverWork(int pageNum, int pageSize);
    /**
     * 分页查看待审核请假信息(管理员)
     */
    List<EmployLeave> selectWaitEmployLeave(int pageNum, int pageSize);
    /**
     * 分页查看待审核加班信息（管理员）
     */
    List<OverWork> selectWaitOverWork(int pageNum, int pageSize);
    /**
     * 查看所有考勤记录
     */
    List<CheckInfo> selectAllCheck(int pageNum, int pageSize);

    /**
     * 查看所有考勤统计
     */
    List<JXDto> getAllCheckCountInfo(String month,int pageNum,int pageSize);

    /**
     * 根据月份查看工资信息
     */
    List<SalaryDto> checkAllSalaryInfosByMonth(String month,int pageNum,int pageSize);
    /**
     * 修改工资配置信息
     */
    int updateSalaryRule(BigDecimal latePay,BigDecimal overTimepay);
    /**
     * 查看工资配置信息
     */
    Salaryrule selectSalaryRule();

    /**
     * 查询是否有当月工资
     * @return
     */
    Boolean selectMonthSalary(String month);
    /**
     * 生成本月工资
     */
    int insertSalaryByMonth(String month);
}
