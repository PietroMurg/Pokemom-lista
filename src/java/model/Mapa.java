package model;

public class Mapa {
        private int idMapa;
        private String nome;
        private String missao;
        private String clima;

    public Mapa(){}    
        
    public Mapa(String nome, String missao, String clima) {
        this.nome = nome;
        this.missao = missao;
        this.clima = clima;
    }
    
    public Mapa(int id, String nome, String missao, String clima) {
        this.idMapa = id;
        this.nome = nome;
        this.missao = missao;
        this.clima = clima;
    }

    public int getIdMapa() {
        return idMapa;
    }

    public void setIdMapa(int idMapa) {
        this.idMapa = idMapa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMissao() {
        return missao;
    }

    public void setMissao(String missao) {
        this.missao = missao;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }
        
        
}
