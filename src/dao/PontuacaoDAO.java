package dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import model.Usuario;
import model.Pontuacao;
import java.util.Date;

public class PontuacaoDAO {
    private final Connection con;
    
    public PontuacaoDAO(){
        this.con = new ConnectionFactory().getConnection();
    }
    
    public void registrarScore(Usuario u){
        String sql = "INSERT INTO pontuacao (email, pontos, data) VALUES (?, ?, ?)";
        Date dt = new Date();
        DateFormat dtf = DateFormat.getDateTimeInstance();
        String data = dtf.format(dt);
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, u.getEmail());
            stmt.setInt(2, u.getPontos());
            stmt.setString(3, data);
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public String getNomeByEmail(String email){
        String sql = "SELECT nome FROM usuario WHERE email = ?";
        PreparedStatement stmt;
        String nome = "";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                nome = rs.getString("nome");
            }
            stmt.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return nome;
    }
    
    public ArrayList<Pontuacao> carregarRankingGeral(){
        String sql = "SELECT DISTINCT email, codigo, pontos, data FROM pontuacao ORDER BY pontos DESC LIMIT 10";
        ArrayList<Pontuacao> pontuacoes = new ArrayList();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Pontuacao p = new Pontuacao();
                p.setCodigo(rs.getInt("codigo"));
                p.setEmail(rs.getString("email"));
                p.setPontos(rs.getInt("pontos"));
                p.setData(rs.getString("data"));
                p.setNome(this.getNomeByEmail(p.getEmail()));
                pontuacoes.add(p);
            }
            stmt.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return pontuacoes;
    }
    
    public ArrayList<Pontuacao> carregarRankingPessoal(Usuario u){
        String sql = "SELECT * FROM pontuacao WHERE email = ? ORDER BY pontos DESC LIMIT 10";
        ArrayList<Pontuacao> pontuacoes = new ArrayList();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, u.getEmail());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Pontuacao p = new Pontuacao();
                p.setCodigo(rs.getInt("codigo"));
                p.setEmail(rs.getString("email"));
                p.setPontos(rs.getInt("pontos"));
                p.setData(rs.getString("data"));
                pontuacoes.add(p);
            }
            stmt.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return pontuacoes;
    }
}