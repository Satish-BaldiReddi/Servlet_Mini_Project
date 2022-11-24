package com.nit.dao;

import com.nit.bo.EmployeeBO;

//Dao class
public interface EmployeeDAO {
    int insert(EmployeeBO bo) throws Exception;
}
