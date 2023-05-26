package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MapaDAO {
        private static Connection conn;
        
        public MapaDAO() throws ClassNotFoundException, SQLException {
            conn = MyConnection.getConnection();
        }
        
        //INSERT
        public void insertMapa(Mapa mapa) throws SQLException{
           //Criando a query genérica
            String sql = "INSERT INTO mapas(nome, missao, clima)"
                                        + "VALUES (?, ?, ?)";
            
            //Instanciando o objeto de conexão informando a query
            PreparedStatement prep = conn.prepareStatement(sql);
            
            //Informando os parâmetros enviados para a query
            prep.setString(1, mapa.getNome());
            prep.setString(2, mapa.getMissao());
            prep.setString(3, mapa.getClima());
            
            prep.execute(); //Lançando o SQL pronto na base de dados
            prep.close();
        }//Fim do método insertMapa
        
        
        //SELECT
        public ArrayList<Mapa> listMapa() throws SQLException {
            //Criação de uma lista vazia (específica de Mapas)
            ArrayList<Mapa> list = new ArrayList<>();
            
            String sql = "SELECT * FROM mapas";
            PreparedStatement prep = conn.prepareStatement(sql);
            
            ResultSet result = prep.executeQuery();
            
            //Enquanto existirem registros, executará este laço de repetição
            while(result.next()) {
                //Criar um objeto vazio da classe Mapa
                Mapa mapa = new Mapa();
                
                //Inserir os atributos a partir dos dados de cada coluna
                mapa.setIdMapa(result.getInt("cod_mapa"));
                mapa.setNome(result.getString("nome"));
                mapa.setMissao(result.getString("missao"));
                mapa.setClima(result.getString("clima"));
                
                //Inserir o objeto preenchido na lista
                list.add(mapa);
            }
            
            return list;
        }//Fim do método listMapa
        
        
        //DELETE
        public void deleteMapa(int id) throws SQLException {
            String sql = "DELETE FROM mapas WHERE cod_mapa = " + id;
            
            PreparedStatement prep = conn.prepareStatement(sql);
            
            prep.execute();
            prep.close();
        }//Fim do método deleteMapa
        
        
        //SELECT BY ID
        public Mapa listOneMapa(int cod) throws SQLException {
             String query = "SELECT * FROM mapas WHERE cod_mapa = " + cod;
             
             PreparedStatement prep = conn.prepareStatement(query);
             
             ResultSet result = prep.executeQuery();
             
             Mapa mapa = new Mapa();
             
             if(result.next()){
                mapa = new Mapa(
                        result.getInt("cod_mapa"),
                        result.getString("nome"),
                        result.getString("missao"),
                        result.getString("clima")
                );
             }             
             return mapa;
        }
        
        
        //UPDATE
        public void updateMapa(Mapa mapa) throws SQLException {
            //Query genérica
            String query = "UPDATE mapas SET nome = ?, "
                    + "missao = ?, clima = ? "
                    + "WHERE cod_mapa = ?";
            
            //Preparando a query para executar no BD
            PreparedStatement prep = conn.prepareStatement(query);
            
            //Trocar as interrogações por valores do objeto enviado
            prep.setString(1, mapa.getNome());
            prep.setString(2, mapa.getMissao());
            prep.setString(3, mapa.getClima());
            prep.setInt(4, mapa.getIdMapa());
            
            //Executar query pronta
            prep.execute();
            prep.close();
        }
        
}//Fim da classe
