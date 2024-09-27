/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.services;

import java.util.List;
import model.classes.Vendedor;
import model.dao.VendedorDao;
import model.db.DB;

/**
 *
 * @author carol
 */
public class VendedorService {
    private VendedorDao dao;

    public VendedorService() {
        this.dao = new VendedorDao(DB.getConnection());
    }
    
    public List<Vendedor> getAll(){
        return dao.getAll();
    }
    
    public boolean salvarOuEditar(Vendedor vndd){
        if (vndd.getCodVendedor() <= 0) {
            return dao.inserirVendedor(vndd);
        }else{
            return dao.editar(vndd);
        }
    }
    
        public boolean excluir(Vendedor vndd){
        return dao.excluir(vndd);
    }
}
