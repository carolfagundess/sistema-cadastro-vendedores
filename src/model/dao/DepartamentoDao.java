/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.classes.Departamento;
import model.db.DB;

/**
 *
 * @author carol transforma os registro do banco em objetos e ao contrario DAO -
 * DATA - ACESS OBJECT
 */
public class DepartamentoDao {

    private Connection con;

    public DepartamentoDao(Connection con) {
        this.con = con;
    }

    public List<Departamento> getAll() {
        //lista temporario do departamento
        List<Departamento> list = new ArrayList<>();
        //listagem dos resul do banco
        ResultSet resul = null;
        //objeto que executa o script sql
        PreparedStatement stmt = null;
        try {
            String sql = "select * from departamento";
            //preparand a string sql para a execução
            stmt = con.prepareStatement(sql);

            //executar o script sql e guarda o resultado dentro do resul 
            resul = stmt.executeQuery();
            //percorrer o res e criar objeto
            while (resul.next()) {
                Departamento dpt = new Departamento(resul.getInt("coddpto"), resul.getString("nomedpto"));
                System.out.println(dpt);
                list.add(dpt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(stmt);
            DB.closeResultSet(resul);
        }
        return list;
    }

    //metodo de insercoes no banco
    public boolean inserirDepartamento(Departamento dpto) {
        PreparedStatement stmt = null;
        boolean result = false;

        try {
            String sql = "insert into departamento (nomeDpto) values (?)";
            //RETORNA A CHAVE PRIMARIA GERADA NO MOMENTO DO INSERT
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //trocando as interrogacoes por valores validos
            stmt.setString(1, dpto.getNomeDpo());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                //deu certo
                //pegando o codigo gerado no insert do banco de dados
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    //pega o codigo que foi gerado e que esta no primeiro campo do resultset 
                    int id = rs.getInt(1);
                    //atualiza o codigo dpto 
                    dpto.setCodDpo(id);
                    result = true;
                    //retorna para o finally
                }
            } else {
                //codigo ira direto para o catch
                throw new SQLException("Não foi possivel inserir");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(stmt);
        }
        return result;
    }

    public boolean excluir(Departamento dpto) {
        PreparedStatement stmt = null;
        boolean result = false;
        try {
            String sql = "delete from departamento where codDpto = ?";
            //prepara o statement
            stmt = con.prepareStatement(sql);
            //troca os parametros
            stmt.setInt(1, dpto.getCodDpo());
            //executa
            stmt.executeUpdate();
            result = true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(stmt);
        }
        return result;
    }

    public boolean editar(Departamento dpto) {
        PreparedStatement stmt = null;
        boolean result = false;
        try {
            String sql = "update departamento set nomeDpto = ? where codDpto = ?";
            //prepara o statement
            stmt = con.prepareStatement(sql);
            //troca os parametros
            stmt.setString(1, dpto.getNomeDpo());
            stmt.setInt(2, dpto.getCodDpo());
            //executa
            stmt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(stmt);
        }
        return result;
    }
;
}
