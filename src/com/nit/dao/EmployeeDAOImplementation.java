package com.nit.dao;

import com.nit.bo.EmployeeBO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class EmployeeDAOImplementation implements EmployeeDAO {
    private static final String QUERY = "INSERT INTO employee_registration values (?,?,?,?,?,?,?)";
    private Connection getPooledJdbcConnection()throws Exception {
        Context initContext = new InitialContext();
       // Context envContext  = (Context)initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/TestDB");
        Connection connection = ds.getConnection();
        return connection;
    }

    @Override
    public int insert(EmployeeBO bo) throws Exception {
        //get pooled jdbc connection object
        Connection connection = getPooledJdbcConnection();
        //create preparedStatement object
        PreparedStatement preparedStatement=connection.prepareStatement(QUERY);
        //set values to query param
        preparedStatement.setLong(1,bo.getEmpNo());
        preparedStatement.setString(2, bo.getEmpName());
        preparedStatement.setString(3, bo.getEmpAddress());
        preparedStatement.setDate(4, bo.getDateOfJoin());
        preparedStatement.setFloat(5, bo.getBasicSalary());
        preparedStatement.setFloat(6, bo.getGrossSalary());
        preparedStatement.setFloat(7, bo.getNetSalary());
        //execute the query
        int count = preparedStatement.executeUpdate();
        //close the objects
        preparedStatement.close();
        connection.close();
        return count;
    }
}
