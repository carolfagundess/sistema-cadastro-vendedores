/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.classes.Departamento;
import model.classes.Vendedor;
import model.db.DB;

/**
 *
 * @author carol
 */
public class VendedorDao {

    private Connection con;

    public VendedorDao(Connection con) {
        this.con = con;
    }

    public List<Vendedor> getAll() {

        List<Vendedor> list = new ArrayList<>();

        ResultSet resul = null;

        PreparedStatement stmt = null;

        try {
            String sql = "select vendedor.*, departamento.nomedpto from vendedor"
                    + " join departamento on (departamento.coddpto = vendedor.coddpto)";

            stmt = con.prepareStatement(sql);

            resul = stmt.executeQuery();
            while (resul.next()) {
                Vendedor vendedor = new Vendedor(resul.getInt("codvendedor"),
                        resul.getString("nomevendedor"),
                        resul.getDate("datanascimento").toLocalDate(),
                        resul.getDouble("salariobase"),
                        new Departamento(resul.getInt("coddpto"), resul.getString("nomedpto")));
                System.out.println(vendedor);
                list.add(vendedor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(stmt);
            DB.closeResultSet(resul);
        }

        return list;
    }
    //metodos para inserir, excluir e editar no banco de dados

//metodo de inserir no banco de dados
    public boolean inserirVendedor(Vendedor vendedor) {
        PreparedStatement stmt = null;

        boolean result = false;

        try {
            String sql = "insert into vendedor (nomevendedor, datanascimento, salariobase, coddpto) values (?,?,?,?)";
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, vendedor.getNomeVendedor());
            stmt.setDate(2, Date.valueOf(vendedor.getDataNascimento()));
            stmt.setDouble(3, vendedor.getSalarioBase());
            stmt.setInt(4, vendedor.getDpo().getCodDpo());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    vendedor.setCodVendedor(id);
                    result = true;
                }
            } else {
                throw new SQLException("Não foi possivel inserir");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(stmt);
        }
        return result;
    }

    public boolean excluir(Vendedor vendedor) {
        PreparedStatement stmt = null;
        boolean result = false;

        try {
            String sql = "delete from vendedor where codvendedor = ?";

            //deletando do banco de dados atraves do comando sql
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, vendedor.getCodVendedor());
            stmt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(stmt);
        }
        return result;
    }

    //metodo para editar dentro do banco de dados
    public boolean editar(Vendedor vendedor) {
        PreparedStatement stmt = null;
        boolean result = false;

        try {
            String sql = "update vendedor set nomevendedor = ?,datanascimento = ?, salariobase = ?, coddpto = ? where codvendedor = ?";

            //realizando o update dentro do banco de dados
            stmt = con.prepareStatement(sql);
            stmt.setInt(5, vendedor.getCodVendedor());
            stmt.setString(1, vendedor.getNomeVendedor());
            stmt.setDate(2, Date.valueOf(vendedor.getDataNascimento()));
            stmt.setDouble(3, vendedor.getSalarioBase());
            stmt.setInt(4, vendedor.getDpo().getCodDpo());
            
            stmt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(stmt);
        }
        return result;
    }

}
