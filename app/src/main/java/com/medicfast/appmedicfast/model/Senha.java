
package com.medicfast.appmedicfast.model;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Date;


public class Senha implements Serializable {

    private Integer numero;
    private PontoAtendimento pontoAtendimento;
    private Especialidade especialidade;
    private TipoOcorrencia ocorrencia;
    private String sintoma;
    private String observacao;
    private Boolean atendido;
    private String horario;
    private String data;
    private Boolean chamado;



    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getSintoma() {
        return sintoma;
    }

    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Boolean getAtendido() {
        return atendido;
    }

    public void setAtendido(Boolean atendido) {
        this.atendido = atendido;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public PontoAtendimento getPontoAtendimento() {
        return pontoAtendimento;
    }

    public void setPontoAtendimento(PontoAtendimento pontoAtendimento) {
        this.pontoAtendimento = pontoAtendimento;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

     public TipoOcorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(TipoOcorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }


    public Boolean getChamado() {
        return chamado;
    }

    public void setChamado(Boolean chamado) {
        this.chamado = chamado;
    }
    
    
}
