/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.services;

import java.util.List;
import model.classes.Departamento;
import model.dao.DepartamentoDao;
import model.db.DB;

/**
 *
 * @author carol
 * classe para serviços para a tela do pacote view
 * e intermedia a comunicacao com a classe DAO
 */
public class DepartamentoService {
    private DepartamentoDao dao;

    public DepartamentoService() {
        dao = new DepartamentoDao(DB.getConnection());
    }
    
    public List<Departamento> getAll(){
        return dao.getAll();
    }
   
    public boolean salvarOuAtualizar(Departamento dpto){
        //testar updt ou salvar
        if(dpto.getCodDpo() <= 0){
            //inclusao
            return dao.inserirDepartamento(dpto);
        }else{
            //alteracao
            return dao.editar(dpto);
        }
    }
    
    public boolean excluir(Departamento dpto){
        return dao.excluir(dpto);
    }
}
