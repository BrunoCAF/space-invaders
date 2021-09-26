package dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Usuario;


public class UsuarioDAO {
    private final Connection con;
    
    public UsuarioDAO(){
        this.con = new ConnectionFactory().getConnection();
    }
    
    public void inserir(Usuario u){
        String sql = "INSERT INTO usuario (email, nome, senha) VALUES (?,?,?)";
        
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, u.getEmail());
            stmt.setString(2, u.getNome());
            stmt.setString(3, u.getSenha());
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public boolean validateEmail(String email){
        String sql = "SELECT email FROM usuario";
        
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<String> emails = new ArrayList<>();
            while(rs.next()){
                String e = rs.getString("email");
                emails.add(e);
            }
            stmt.close();
            return !(emails.contains(email));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
    }
    
    
    public Usuario logar(Usuario u){
        String sql = "SELECT * FROM usuario WHERE email = ?";
        
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, u.getEmail());
            ResultSet rs = stmt.executeQuery();
            Usuario user = new Usuario();
            while(rs.next()){
                user.setEmail(rs.getString("email"));
                user.setNome(rs.getString("nome"));
                user.setSenha(rs.getString("senha"));
                user.setPontos(0);
                user.setLogado(true);
            }
            stmt.close();
            if(user.getSenha().equals(u.getSenha())){
                return new Usuario(user);
            }else{
                user.setLogado(false);
                return new Usuario(user);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}