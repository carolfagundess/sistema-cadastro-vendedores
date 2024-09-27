/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.classes;

import java.time.LocalDate;

/**
 *
 * @author carol
 */
public class Vendedor {
    private int codVendedor;
    private String nomeVendedor;
    private LocalDate dataNascimento;
    private double salarioBase;
    private Departamento dpo;

    public Vendedor(int codVendedor, String nomeVendedor, LocalDate dataNascimento, double salarioBase, Departamento dpo) {
        this.codVendedor = codVendedor;
        this.nomeVendedor = nomeVendedor;
        this.dataNascimento = dataNascimento;
        this.salarioBase = salarioBase;
        this.dpo = dpo;
    }

    public Vendedor() {
    }
    
    

    public int getCodVendedor() {
        return codVendedor;
    }

    public void setCodVendedor(int codVendedor) {
        this.codVendedor = codVendedor;
    }

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public Departamento getDpo() {
        return dpo;
    }
    
    public String getNomeDpto(){
       return dpo.getNomeDpo();
    }

    public void setDpo(Departamento dpo) {
        this.dpo = dpo;
    }
    
    
}
