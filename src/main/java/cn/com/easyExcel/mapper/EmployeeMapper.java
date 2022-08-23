package cn.com.easyExcel.mapper;

import cn.com.easyExcel.pojo.Employee;

import java.util.List;

public interface EmployeeMapper extends BaseDaoMapper<Employee> {

    /**
     * 批量插入
     * @param employees 员工表
     */
    void batchInsert(List<Employee> employees);
}
