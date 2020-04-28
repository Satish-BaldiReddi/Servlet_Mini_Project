package com.nit.controller;

import com.nit.dto.EmployeeDTO;
import com.nit.service.EmployeeManagementService;
import com.nit.service.EmployeeManagementServiceImplementation;
import com.nit.vo.EmployeeVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

@WebServlet("/registerurl")
public class MainControllerServlet extends HttpServlet {
    private EmployeeManagementService service;

    public void init() {
        service = new EmployeeManagementServiceImplementation();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //gwt print writer
        PrintWriter printWriter = resp.getWriter();
        EmployeeVO vo = new EmployeeVO();
        EmployeeDTO dto = new EmployeeDTO();
        //read the form data store into VO class object
        vo.setEmpNo(req.getParameter("empNo"));
        vo.setEmpName(req.getParameter("empName"));
        vo.setEmpAddress(req.getParameter("empAddress"));
        vo.setDateOfJoin(req.getParameter("dateOfJoin"));
        vo.setBasicSalary(req.getParameter("basicSalary"));
        //convert VO class object to DTO class object
        dto.setEmpNo(Long.parseLong(vo.getEmpNo()));
        dto.setEmpName(vo.getEmpName());
        dto.setEmpAddress(vo.getEmpAddress());
        dto.setDateOfJoin(Date.valueOf(vo.getDateOfJoin()));
        dto.setBasicSalary(Float.parseFloat(vo.getBasicSalary()));
        //use service
        try {
            String result = service.register(dto);
            printWriter.println(result);
        } catch (Exception e) {
            printWriter.println("<h1 style=color:red>Internal DataBase problem i.e.,</h1>");
            printWriter.println("<b style=color:blue> " + e + "</b>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        doGet(req, resp);
    }

}
