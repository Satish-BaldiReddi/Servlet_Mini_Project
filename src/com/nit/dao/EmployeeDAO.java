package com.nit.dao;

import com.nit.bo.EmployeeBO;

public interface EmployeeDAO {
    int insert(EmployeeBO bo) throws Exception;
}
