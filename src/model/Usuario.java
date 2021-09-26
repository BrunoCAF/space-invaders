package model;


public class Usuario {
    private String email;
    private String nome;
    private String senha;
    private int pontos;
    private boolean logado;
    
    public Usuario(){
        
    }
    
    public Usuario(Usuario u){
        this.email = u.getEmail();
        this.nome = u.getNome();
        this.senha = u.getSenha();
        this.pontos = u.getPontos();
        this.logado = u.isLogado();
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }
    
    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}