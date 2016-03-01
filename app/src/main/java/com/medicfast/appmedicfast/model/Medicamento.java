
package com.medicfast.appmedicfast.model;

import java.io.Serializable;


public class Medicamento implements Serializable{
    

    private Integer         id;
    private  String  nome;
    private  String fabricante;
    private  String peso;
    private  Integer quantidade;
    private String obs;
    private String sintoma;

    public void setObs(String obs) { this.obs = obs; }
    public String getObs() {
        return obs;
    }

    public String getSintoma() {
        return sintoma;
    }
    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }

    public String getPeso() {
        return peso;
    }
    public void setPeso(String peso) {
        this.peso = peso;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
     public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    ////Get e Set Id
    public void setId(Integer id) { this.id = id; }
    public Integer getId() { return id; }

    //Get Set Descricao
    public void setNome(String nome) { this.nome = nome; }
    public String getNome() { return nome; }
    
    //Get Set Fabricante
    public void setFabricante(String fabricante) { this.fabricante = fabricante;}
    public String getFabricante() { return fabricante; }

    @Override
    public String toString() {
        return  nome ;
    }
    
}
