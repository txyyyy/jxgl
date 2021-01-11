package com.codejie.pms.mapper;

import com.codejie.pms.entity.Salary;
import com.codejie.pms.entity.User;

import java.util.List;

public interface SalaryMapper {
    List<Salary> getSalaryMsg();
    List<Salary> getSalaryMsgByUserId(Salary salary);

}
