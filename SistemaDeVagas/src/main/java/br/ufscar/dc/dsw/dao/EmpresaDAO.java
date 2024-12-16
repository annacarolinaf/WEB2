package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Usuario;

public class EmpresaDAO extends GenericDAO {

    public Empresa getByIdUsuario(Usuario usuario){
        Empresa Empresa = null;
        
        String sql = "SELECT * FROM Empresa e WHERE e.id_usuario = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, usuario.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String cidade = resultSet.getString("cidade");
                String descricao = resultSet.getString("descricao");
                String cnpj = resultSet.getString("cnpj");

                Empresa = new Empresa(cnpj, cidade, descricao, usuario);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Empresa;
    }
    public Empresa get(String cnpj) {
        Empresa Empresa = null;
        
        String sql = "SELECT * FROM Empresa e WHERE e.cnpj = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cnpj);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String cidade = resultSet.getString("cidade");
                String descricao = resultSet.getString("descricao");
                Long id_usuario = resultSet.getLong("id_usuario");

                Usuario usuario = new UsuarioDAO().getbyID(id_usuario);

                Empresa = new Empresa(cnpj, cidade, descricao, usuario);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Empresa;
    }

    public List<Empresa> getAll() {   
        List<Empresa> listaEmpresas = new ArrayList<>();
        String sql = "SELECT * from Empresa";
        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String cnpj = resultSet.getString("cnpj");
                String cidade = resultSet.getString("cidade");
                String descricao = resultSet.getString("descricao");
                Long id_usuario = resultSet.getLong("id_usuario");

                Usuario usuario = new UsuarioDAO().getbyID(id_usuario);

                Empresa empresa = new Empresa(cnpj, cidade, descricao, usuario);
            
                listaEmpresas.add(empresa);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return listaEmpresas;
    }
    

    public void insert(Empresa empresa) {    
        String sql = "INSERT INTO Empresa (cnpj, cidade, descricao, id_usuario) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;    
            statement = conn.prepareStatement(sql);
            statement.setString(1, empresa.getCnpj());
            statement.setString(2, empresa.getCidade());
            statement.setString(3, empresa.getDescricao());
            statement.setLong(4, empresa.getUsuario().getId());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Empresa empresa){
        String sql_inscricao = "DELETE FROM Inscricao WHERE vaga_id IN (SELECT id_vaga FROM Vaga WHERE empresa_id = ?);";
        String sql_vagas = "DELETE FROM Vaga WHERE empresa_id = ?;";
        String sql_empresa = "DELETE FROM Empresa WHERE cnpj = ?";
        String sql_usuario = "DELETE FROM Usuario WHERE id = ?";

        Long id = empresa.getUsuario().getId();

        try {
            Connection conn = this.getConnection();

            PreparedStatement statement = conn.prepareStatement(sql_inscricao);
    
            statement.setString(1, empresa.getCnpj());
            statement.executeUpdate();

            statement = conn.prepareStatement(sql_vagas);
    
            statement.setString(1, empresa.getCnpj());
            statement.executeUpdate();
    

            statement = conn.prepareStatement(sql_empresa);
    
            statement.setString(1, empresa.getCnpj());
            statement.executeUpdate();
    
            statement = conn.prepareStatement(sql_usuario);
    
            statement.setLong(1, id);
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void update(Empresa empresa) {
        String sql = "UPDATE Empresa SET cnpj = ?, cidade = ?, descricao = ?";
        sql += " WHERE id_usuario = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, empresa.getCnpj());
            statement.setString(2, empresa.getCidade());
            statement.setString(3, empresa.getDescricao());
            statement.setLong(4, empresa.getUsuario().getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
