package com.medicfast.appmedicfast.model;

import java.io.Serializable;
import java.util.List;


public class PontoAtendimento implements Serializable {


    private Integer          id;
    private String         nome;
    private String          rua;
    private String       numero;
    private String       bairro;
    private String  complemento;
    private String     telefone;
    private List<Medico>       medico;
    private List<Medicamento> medicamento;
    
        
    //Get Set Id
    public void setId(Integer id) { this.id = id; }
    public Integer getId() { return id; }
    
    //Get Set Nome
    public void setNome(String nome) { this.nome = nome; }
    public String getNome() { return nome; }
    
    //Get Set Rua
    public void setRua(String rua) { this.rua = rua; }
    public String getRua() { return rua; }
    
    //Get Set Numero
    public void setNumero(String numero) { this.numero = numero; }
    public String getNumero() { return numero; }

    //Get Set Bairro
    public void setBairro(String bairro) { this.bairro = bairro; }
    public String getBairro() { return bairro;}

    //Get Set Complemento
    public void setComplemento(String complemento) { this.complemento = complemento; }
    public String getComplemento() { return complemento; }
    
    //Get Set Telefone
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getTelefone() { return telefone; }
    
    
    public List<Medico> getMedico() {
        return  medico;
    }
    public void setMedico(List<Medico> medico) {
        this.medico = (List<Medico>) medico;
    }

    public List<Medicamento> getMedicamento() {
        return  medicamento;
    }
    public void setMedicamento(List<Medicamento> medicamento) {
        this.medicamento = (List<Medicamento>) medicamento;
    }

    @Override
    public String toString() {
        return  nome + "\n" +
                "Rua: " + rua + "," + numero +" - "+ bairro;

    }
}
