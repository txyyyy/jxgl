package com.codejie.pms.mapper;

import com.codejie.pms.entity.*;
import com.codejie.pms.entity.dto.DepartmentDelDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工mapper
 */
public interface AdminMapper {

    List<String> initSelect1();

    List<String> initSelect2();

    List<Department> allDepartment();

    List<WeakCheck> getWeek2();

    List<String> weekList(String date1, String date2);

    List<DepartmentDelDto> getWeekData(String date1, String date2, String data3);

    List<DepartmentDelDto> getWeekData2(String date1, String date2);

    String stuCount();

    List<DepartmentDelDto> getDepartmentCheckDel(String data1,String data2);
    void upDepartment(Department department);

    void kpAdd2(AddPoint addPoint);
    void addPoint2(AddPoint addPoint);

    Department selectDepartment(Department department);



    int countOverWorkByMonth(@Param("userId")String userId,@Param("month")String month);

    /**
     * Description 开发部kpi列表1
     */
    List<Kpi> selectKpiList4();

    void kpAdd4(AddPointDepartment addPointDepartment);
    void addPoint4(AddPointDepartment addPointDepartment);

    /**
     * Description 开发部kpi列表2
     */
    List<Kpi> selectKpiList5();

    void kpAdd5(AddPointDepartment addPointDepartment);
    void addPoint5(AddPointDepartment addPointDepartment);

    /**
     * Description 运营部kpi列表1
     */
    List<Kpi> selectKpiList6();

    void kpAdd6(AddPointDepartment addPointDepartment);
    void addPoint6(AddPointDepartment addPointDepartment);

    /**
     * Description 运营部kpi列表2
     */
    List<Kpi> selectKpiList7();

    void kpAdd7(AddPointDepartment addPointDepartment);
    void addPoint7(AddPointDepartment addPointDepartment);

    /**
     * Description 人事部kpi列表1
     */
    List<Kpi> selectKpiList8();

    void kpAdd8(AddPointDepartment addPointDepartment);
    void addPoint8(AddPointDepartment addPointDepartment);

    /**
     * Description 人事部kpi列表2
     */
    List<Kpi> selectKpiList9();

    void kpAdd9(AddPointDepartment addPointDepartment);
    void addPoint9(AddPointDepartment addPointDepartment);

    /**
     * Description市场部kpi列表1
     */
    List<Kpi> selectKpiList10();

    void kpAdd10(AddPointDepartment addPointDepartment);
    void addPoint10(AddPointDepartment addPointDepartment);

    /**
     * Description市场部kpi列表2
     */
    List<Kpi> selectKpiList11();

    void kpAdd11(AddPointDepartment addPointDepartment);
    void addPoint11(AddPointDepartment addPointDepartment);
    /**
     * Description财务部kpi列表1
     */
    List<Kpi> selectKpiList12();

    void kpAdd12(AddPointDepartment addPointDepartment);
    void addPoint12(AddPointDepartment addPointDepartment);

    /**
     * Description财务部kpi列表2
     */
    List<Kpi> selectKpiList13();

    void kpAdd13(AddPointDepartment addPointDepartment);
    void addPoint13(AddPointDepartment addPointDepartment);

    /**
     * 分页查看所有请假信息(管理员)
     */
    List<EmployLeave> selectAllEmployLeave();
    /**
     * 分页查看所有加班信息(管理员)
     */
    List<OverWork> selectAllOverWork();
    /**
     * 分页查看待审核请假信息(管理员)
     */
    List<EmployLeave> selectWaitEmployLeave();
    /**
     * 分页查看待审核加班信息（管理员）
     */
    List<OverWork> selectWaitOverWork();
    /**
     * 查看所有考勤记录
     */
    List<CheckInfo> selectAllCheck(String thisDay);

    /**
     * 根据月份查看用户的考勤信息
     */
    List<CheckInfo> selectCheckByMonth(@Param("month")String month, @Param("userId")String userId,@Param("thisDay")String thisDay);

    /**
     * 根据月份查看用户的工资列表
     */
    Salary selectUserSalaryByMonth(@Param("salaryMonth")String salaryMonth,@Param("userId")String userId);
}
