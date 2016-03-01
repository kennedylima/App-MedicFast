
package com.medicfast.appmedicfast.model;

import java.io.Serializable;
import java.util.List;


public class Medico implements Serializable{

    private Integer      id;
    private String     nome;
    private String    email;
    private String telefone;
    private String CRM;
    private String endereco;
    private List<Especialidade> especialidade;
    private String informacao;
    private Especialidade atendendoComo;
    private PontoAtendimento LocalAtendimento;

    //Get e Set Id
    public void setId(Integer id) { this.id = id; }
    public Integer getId() { return id; }

    //Get e Set Nome
    public void setNome(String nome) { this.nome = nome; }
    public String getNome() { return nome; }
    
    //Get e Set Email
    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return email; }
    
    //Get e Set Telefone
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getTelefone() { return telefone; }
    
    //Get e Set Endereço
    public String getEndereco() {return endereco;}
    public void setEndereco(String endereco) {this.endereco = endereco; }
    
    //Get e Set Especialidade
    public List<Especialidade> getEspecialidade() { return especialidade;}
    public void setEspecialidade(List<Especialidade> especialidade) {this.especialidade = (List<Especialidade>) especialidade;}
 
    //Get e Set CRM
    public String getCRM() {return CRM;}
    public void setCRM(String CRM) {this.CRM = CRM;}

    //Get e Set Infromação
    public String getInformacao() {return informacao;}
    public void setInformacao(String informacao) {this.informacao = informacao;}

    //Get e Set AtendendoComo
    public Especialidade getAtendendoComo() {return atendendoComo; }
    public void setAtendendoComo(Especialidade atendendoComo) {this.atendendoComo = atendendoComo;}

    //Get e Set LocalAtendimento
    public PontoAtendimento getLocalAtendimento() {return LocalAtendimento;}
    public void setLocalAtendimento(PontoAtendimento LocalAtendimento) {this.LocalAtendimento = LocalAtendimento;}

    @Override
    public String toString() {
        return  nome ;
    }
    
}
