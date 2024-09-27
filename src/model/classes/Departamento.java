/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.classes;

/**
 *
 * @author carol
 */
public class Departamento {
    private int codDpo;
    private String nomeDpo;

    public Departamento() {
    }

    public Departamento(int codDpo, String nomeDpo) {
        this.codDpo = codDpo;
        this.nomeDpo = nomeDpo;
    }

    public int getCodDpo() {
        return codDpo;
    }

    public void setCodDpo(int codDpo) {
        this.codDpo = codDpo;
    }

    public String getNomeDpo() {
        return nomeDpo;
    }

    public void setNomeDpo(String nomeDpo) {
        this.nomeDpo = nomeDpo;
    }

    @Override
    public String toString() {
        return nomeDpo;
    }
    
    
}
