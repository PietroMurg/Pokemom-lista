package model;

import java.sql.SQLException;

public class User {
    //Atributos
    private int userId;
    private String userName;
    private String userPass;
    private String userNick;
    
    //Construtor
    public User(){}
    
    public User(String user, String pass) {
        this.userName = user;
        this.userPass = pass;
    }
    
    public User(int id, String user, String pass) {
        this.userId = id;
        this.userName = user;
        this.userPass = pass;
    }
        
    //Getters & Setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String name) {
        this.userName = name;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }
    
    //toString = Representação em formato String do Objeto
    @Override
    public String toString() {
        return "<hr>Nome: " +  this.userName +
                "<br>Apelido: "  + this.userNick +
                "<br>Senha: " + this.userPass;
    }
    
    //Métodos gerais (regras de negócio)
    public boolean isLogged() throws ClassNotFoundException, SQLException {
        UserDAO udao = new UserDAO();
        
        User uBanco = udao.listOneUser(this.userName);
        
        if(uBanco.getUserName() != null) {
            //Aqui, o usuário existe, então a senha é verificada
            return (this.userPass.equals(uBanco.getUserPass()));
        } else {
            //Aqui sabemos que o usuário não está registrado
            return false;
        }
    }
    
}
