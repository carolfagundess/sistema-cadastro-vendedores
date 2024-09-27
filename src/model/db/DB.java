/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.db;
import java.sql.*;

/**
 *
 * @author carol
 * CLASSE QUE CONECTA NO BANCO DE DADOS
 */
public class DB {
    public static Connection getConnection(){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vendedores", "root", "");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void closeStatement(Statement stm){
        try {
            if (stm != null) {
                stm.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void closeResultSet(ResultSet rs){
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
