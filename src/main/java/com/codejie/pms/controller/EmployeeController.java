package com.codejie.pms.controller;

import com.codejie.pms.entity.*;
import com.codejie.pms.service.EmployeeService;
import com.codejie.pms.service.UserService;
import com.github.pagehelper.PageInfo;
import com.sun.xml.internal.rngom.ast.util.CheckingSchemaBuilder;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

/**
 * 员工controller
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @Resource
    private UserService userService;

    /**
     * Description 获取个人信息
     */
    @RequestMapping(value = "/getMyName")
    public User getMyName(String userId) {
        User user = new User();
        user.setUserId(userId);
        User user1 = userService.selectUser(user);
        return user1;
    }

    /**
     * Description 打开我的个人信息页面
     */
    @RequestMapping(value = "/getMyMsg",method = RequestMethod.GET)
    public ModelAndView getMyMsg(String userId) {
        ModelAndView mv = new ModelAndView("my_msg");
        User user = new User();
        user.setUserId(userId);
        User user1 = userService.selectUser(user);
        mv.addObject("user",user1);
        return mv;
    }

    /**
     * Description 打开我的绩效信息页面
     */
    @RequestMapping(value = "/myKp",method = RequestMethod.GET)
    public ModelAndView myKp(String userId) {
        ModelAndView mv = new ModelAndView("my_kp");
        mv.addObject("userId",userId);
        return mv;
    }

    /**
     * Description 打开修改个人信息页面
     */
    @RequestMapping("/change_msg")
    public ModelAndView change_msg(String userId,String userName) {
        ModelAndView mv = new ModelAndView("change_msg");
        mv.addObject("userId",userId);
        mv.addObject("userName",userName);
        return mv;
    }

    /**
     * Description 打开部门信息页面
     */
    @RequestMapping("/departmentMsg")
    public ModelAndView departmentMsg() {
        ModelAndView mv = new ModelAndView("department_msg");
        return mv;
    }

    /**
     * Description 修改个人信息页面
     */
    @RequestMapping("/changeMyMsg")
    public ModelAndView changeMyMsg(User user) {
        ModelAndView mv = new ModelAndView("redirect:/employee/getMyMsg?userId="+user.getUserId());
        if(user.getPwd()!=null&&!"".equals(user.getPwd())){
            mv.setViewName("redirect:/");
        }
        userService.updateUserBySelf(user);
        return mv;
    }
    /**
     * 个人薪资信息
     */
    @RequestMapping("/getSalaryPoint")
    @ResponseBody
    public PageInfo<Salary> getSalaryPoint(String salaryMonth, String userId,
                                          @RequestParam(defaultValue = "1") int pageNum,
                                          @RequestParam(defaultValue = "1") int pageSize) {
        Salary salary = new Salary();
        salary.setSalaryMonth(salaryMonth);
        salary.setUserId(userId);
        List<Salary> list = employeeService.getSalaryPoint(salary, pageNum, pageSize);
        PageInfo<Salary> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    /**
     * Description 个人加分信息
     */
    @RequestMapping("/getAddPoint")
    @ResponseBody
    public PageInfo<AddPoint> getAddPoint(String addDesc, String userId,
                                          @RequestParam(defaultValue = "1") int pageNum,
                                          @RequestParam(defaultValue = "5") int pageSize) {
        AddPoint addPoint = new AddPoint();
        addPoint.setAddDesc(addDesc);
        addPoint.setUserId(userId);
        List<AddPoint> list = employeeService.getAddPoint(addPoint, pageNum, pageSize);
        PageInfo<AddPoint> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * Description 个人扣分信息
     */
    @RequestMapping("/getDeletePoint")
    @ResponseBody
    public PageInfo<DeletePoint> getDeletePoint(String deleteDesc,String userId,
                                            @RequestParam(defaultValue = "1") int pageNum,
                                            @RequestParam(defaultValue = "5") int pageSize) {

        DeletePoint deletePoint = new DeletePoint();
        deletePoint.setUserId(userId);
        deletePoint.setDeleteDesc(deleteDesc);
        List<DeletePoint> list = employeeService.getDeletePoint(deletePoint, pageNum, pageSize);
        PageInfo<DeletePoint> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * Description 个人惩罚信息
     */
    @RequestMapping("/getPunishList")
    @ResponseBody
    public PageInfo<Punish> getPunishList(String punishReason, String userId,
                                          @RequestParam(defaultValue = "1") int pageNum,
                                          @RequestParam(defaultValue = "5") int pageSize) {
        Punish punish = new Punish();
        punish.setPunishReason(punishReason);
        punish.setUserId(userId);
        List<Punish> list = employeeService.getPunishList(punish, pageNum, pageSize);
        PageInfo<Punish> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * Description 个人奖励信息
     */
    @RequestMapping("/getRewardList")
    @ResponseBody
    public PageInfo<Reward> getRewardList(String rewardReason, String userId,
                                          @RequestParam(defaultValue = "1") int pageNum,
                                          @RequestParam(defaultValue = "5") int pageSize) {
        Reward reward = new Reward();
        reward.setRewardReason(rewardReason);
        reward.setUserId(userId);
        List<Reward> list = employeeService.getRewardList(reward, pageNum, pageSize);
        PageInfo<Reward> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * Description 我的部门信息
     */
    @RequestMapping("/getUserDepartment")
    @ResponseBody
    public Department getUserDepartment(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return employeeService.getUserDepartment(user.getUserId());
    }

    /**
     * Description 打开所有部门信息页面
     */
    @RequestMapping("/department_list")
    public ModelAndView DepartmentList() {
        ModelAndView mv = new ModelAndView("department_list");
        return mv;
    }

    /**
     * Description 所有部门信息
     */
    @RequestMapping("/getDepartmentList")
    @ResponseBody
    public PageInfo<Department> getDepartmentList(@RequestParam(defaultValue = "")String departmentName,
                                            @RequestParam(defaultValue = "")String departmentId,
                                            @RequestParam(defaultValue = "1") int pageNum,
                                            @RequestParam(defaultValue = "10") int pageSize) {
        Department department = new Department();
        department.setDepartmentName(departmentName);
        department.setDepartmentId(departmentId);
        List<Department> list = employeeService.getDepartmentList(department,pageNum, pageSize);
        PageInfo<Department> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * Description 打开所有人信息页面
     */
    @RequestMapping("/user_list")
    public ModelAndView userList(String userName,String userId,String departmentId,String departmentName) {
        ModelAndView mv = new ModelAndView("department_list");
        return mv;
    }

    /**
     * 加班管理
     * @return
     */
    @RequestMapping(value="/overTimeWorkMsg",method = RequestMethod.GET)
    public ModelAndView overTimeWorkList(String userId) {
        ModelAndView mv = new ModelAndView("over_time_work");
        mv.addObject("userId",userId);
        return mv;
    }

    /**
     * 考勤管理
     * @return
     */
    @RequestMapping(value="/myKq",method = RequestMethod.GET)
    public ModelAndView myKq(String userId) {
        ModelAndView mv = new ModelAndView("my_kq");
        mv.addObject("userId",userId);
        return mv;
    }

    /**
     * 请假管理
     * @return
     */
    @RequestMapping(value="/myQj",method = RequestMethod.GET)
    public ModelAndView myQj(String userId) {
        ModelAndView mv = new ModelAndView("my_qj");
        mv.addObject("userId",userId);
        return mv;
    }
    @RequestMapping("/leaveManagementMsg")
    public ModelAndView LeaveManagementList() {
        ModelAndView mv = new ModelAndView("leave_management");
        return mv;
    }

    /**
     * Description 我的加班信息
     */
    @RequestMapping("/getOverWorkMsg")
    @ResponseBody
    public PageInfo<OverWork> getOverWorkMsg(String userId,
                                           @RequestParam(defaultValue = "1") int pageNum,
                                           @RequestParam(defaultValue = "15") int pageSize) {
        OverWork overWork = new OverWork();
        overWork.setUserId(userId);
        List<OverWork> list = employeeService.getOverWorkByUserId(overWork, pageNum, pageSize);
        PageInfo<OverWork> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    /**
     * 添加加班信息
     */
    @RequestMapping("/insertOverWork")
    public int insertOverWork(OverWork overWork) {
        return employeeService.insertOverWork(overWork);
    }
    /**
     * 我的请假信息
     */
    @RequestMapping("/getEmployeeLeave")
    @ResponseBody
    public PageInfo<EmployLeave> getEmployeeLeave(String userId,
                                             @RequestParam(defaultValue = "1") int pageNum,
                                             @RequestParam(defaultValue = "10") int pageSize) {
        EmployLeave employLeave = new EmployLeave();
        employLeave.setJobNumber(userId);
        List<EmployLeave> list = employeeService.getEmployLeaveByUserId(employLeave, pageNum, pageSize);
        PageInfo<EmployLeave> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    /**
     * 添加请假信息
     */
    @RequestMapping("/insertEmoloyeeLeave")
    public int insertEmoloyeeLeave(EmployLeave employLeave) {
        return employeeService.insertEmployLeave(employLeave);
    }
    /**
     * 审核请假信息(修改状态)
     */
    @RequestMapping("/updateLeaveStatus")
    public int updateLeaveStatus(EmployLeave employLeave) {
        return employeeService.updateLeaveStatus(employLeave);
    }
    /**
     * 审核加班信息(修改状态)
     */
    @RequestMapping("/updateOverWorkStatus")
    public int updateOverWorkStatus(OverWork overWork) {
        return employeeService.updateOverWorkStatus(overWork);
    }
    /**
     * 员工签到打卡
     */
    @RequestMapping("/signIn")
    public int signIn(CheckInfo checkInfo) throws ParseException {
        return employeeService.signIn(checkInfo);
    }
    /**
     * 员工签退打卡
     */
    @RequestMapping("/signOut")
    public int signOut(CheckInfo checkInfo) throws ParseException  {
        return employeeService.signOut(checkInfo);
    }
    /**
     * 查看个人考勤信息
     */
    @RequestMapping("/getCheckInfoByUserId")
    @ResponseBody
    public PageInfo<CheckInfo> getCheckInfoByUserId(String userId,
                                                  @RequestParam(defaultValue = "1") int pageNum,
                                                  @RequestParam(defaultValue = "10") int pageSize) {
        if(userId!=null){
            CheckInfo checkInfo = new CheckInfo();
            checkInfo.setUserId(userId);
            List<CheckInfo> list = employeeService.selectCheck(checkInfo, pageNum, pageSize);
            PageInfo<CheckInfo> pageInfo = new PageInfo<>(list);
            return pageInfo;
        }else {
            return null;
        }

    }
    /**
     * 检验员工当天是否打卡
     */
    @RequestMapping("/checkSignInStatus")
    public int checkSignInStatus(String userId) {
        return employeeService.checkSignIn(userId);
    }
    /**
     * 检验员工是否签退
     */
    @RequestMapping("/checkSignOutStatus")
    public int checkSignOutStatus(String userId) {
        return employeeService.checkSignOut(userId);
    }

}
