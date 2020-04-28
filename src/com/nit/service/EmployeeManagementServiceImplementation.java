package com.nit.service;

import com.nit.bo.EmployeeBO;
import com.nit.dao.EmployeeDAO;
import com.nit.dao.EmployeeDAOImplementation;
import com.nit.dto.EmployeeDTO;

public class EmployeeManagementServiceImplementation implements EmployeeManagementService {
    private final EmployeeDAO dao;

    public EmployeeManagementServiceImplementation() {
        dao = new EmployeeDAOImplementation();
    }

    @Override
    public String register(EmployeeDTO dto) throws Exception {
        float grossSalary ;
        float netSalary ;
        //write business logic
        grossSalary = dto.getBasicSalary() + dto.getBasicSalary() * 0.4f;
        netSalary = grossSalary - (grossSalary * 0.2f);
        //create BO class object having persistable data
        EmployeeBO bo = new EmployeeBO();
        bo.setEmpNo(dto.getEmpNo());
        bo.setEmpName(dto.getEmpName());
        bo.setEmpAddress(dto.getEmpAddress());
        bo.setDateOfJoin(dto.getDateOfJoin());
        bo.setBasicSalary(dto.getBasicSalary());
        bo.setGrossSalary(grossSalary);
        bo.setNetSalary(netSalary);
        //use DAO
        int count = dao.insert(bo);
        //process the result
        if (count == 0) {
            return "<h1 style=color:Red;text-align:center>Registration failed</h1>";
        } else {
            return  "<h1 style=color:Green;text-align:center>Registration successful</h1>";
        }
    }
}
