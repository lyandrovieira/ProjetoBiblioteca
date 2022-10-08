/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author lyand
 */
public class Emprestimos {
    
    private int id;
    private String numChamada;
    private String usuario;
    private String dataEmp;
    private String dataDev;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumChamada() {
        return numChamada;
    }

    public void setNumChamada(String numChamada) {
        this.numChamada = numChamada;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDataEmp() {
        return dataEmp;
    }

    public void setDataEmp(String dataEmp) {
        this.dataEmp = dataEmp;
    }

    public String getDataDev() {
        return dataDev;
    }

    public void setDataDev(String dataDev) {
        this.dataDev = dataDev;
    }
    
    
}
