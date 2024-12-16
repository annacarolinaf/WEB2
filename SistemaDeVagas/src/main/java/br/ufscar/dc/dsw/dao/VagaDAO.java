package br.ufscar.dc.dsw.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;



public class VagaDAO extends GenericDAO {

    public void insert(Vaga vaga) {

        String sql = "INSERT INTO Vaga (salario, descricao_vaga, data_limite, empresa_id)  VALUES (?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setFloat(1, vaga.getSalario()); //salario
            statement.setString(2, vaga.getDescricao()); //descricao
            statement.setString(3, vaga.getData_limite()); //data_limite
            statement.setString(4, vaga.getEmpresa().getCnpj()); //empresa_id
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vaga> getAll() {

        List<Vaga> listaVagas = new ArrayList<>();

        String sql = "SELECT * FROM Vaga";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id_vaga = resultSet.getLong("id_vaga");
                float salario = resultSet.getFloat("salario");
                String descricao_vaga = resultSet.getString("descricao_vaga");
                String data_limite = resultSet.getString("data_limite");
                String empresa_id = resultSet.getString("empresa_id");
                String status_vaga = resultSet.getString("status_vaga");

                //String data_limite = "2024-07-22";
        
                // Formato da data
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                
                try {
                    // Converter a string para LocalDate
                    LocalDate inputDate = LocalDate.parse(data_limite, formatter);
                    
                    // Obter a data atual
                    LocalDate currentDate = LocalDate.now();
                    
                    // Comparar as datas
                    if (inputDate.isBefore(currentDate)) {
                        //System.out.println("A data fornecida é antes da data atual.");
                        status_vaga = "ENCERRADA";
                    } else if (inputDate.isAfter(currentDate)) {
                        //System.out.println("A data fornecida é depois da data atual.");
                        status_vaga = "ABERTA";
                    } else {
                        //System.out.println("A data fornecida é igual à data atual.");
                        status_vaga = "ENCERRADA";
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Formato de data inválido: " + e.getMessage());
                }

                Empresa empresa = new EmpresaDAO().get(empresa_id);

                Vaga vaga = new Vaga(id_vaga, salario, descricao_vaga, data_limite, empresa, status_vaga);

                listaVagas.add(vaga);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaVagas;
    }


    public void delete(Vaga vaga) {
        String sql = "DELETE FROM Vaga where id_vaga = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, vaga.getId_vaga());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Vaga vaga) {
        String sql = "UPDATE Vaga SET salario = ?, descricao_vaga = ?, data_limite = ?, status_vaga = ?";
        sql += ", empresa_id = ? WHERE id_vaga = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setFloat(1, vaga.getSalario());//salario
            statement.setString(2, vaga.getDescricao());//descricao
            statement.setString(3, vaga.getData_limite());//data_limite
            statement.setString(4, vaga.getEmpresa().getCnpj());//empresa_id = cnpj
            statement.setLong(5, vaga.getId_vaga()); //id da vaga
            statement.setString(6, vaga.getStatus_vaga());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Vaga get(Long id) {
        Vaga vaga = null;

        String sql = "SELECT * from Vaga v, Empresa e where v.id_vaga = ? and v.empresa_id = e.cnpj";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                Float salario = resultSet.getFloat("salario");
                String descricao_vaga = resultSet.getString("descricao_vaga");
                String data_limite = resultSet.getString("data_limite");
                String empresaID = resultSet.getString("empresa_id");
                String status_vaga = resultSet.getString("status_vaga");


                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                
                try {
                    LocalDate inputDate = LocalDate.parse(data_limite, formatter);
                    
                    LocalDate currentDate = LocalDate.now();
                
                    if (inputDate.isBefore(currentDate)) {
                        status_vaga = "ENCERRADA";
                    } else if (inputDate.isAfter(currentDate)) {
                        status_vaga = "ABERTA";
                    } else {
                        status_vaga = "ENCERRADA";
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Formato de data inválido: " + e.getMessage());
                }

                Empresa empresa = new EmpresaDAO().get(empresaID);

                vaga = new Vaga(id, salario, descricao_vaga, data_limite, empresa, status_vaga);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vaga;
    }

    public List<Vaga> getAllVagasEmpresa(Usuario usuario) {

        List<Vaga> listaVagas = new ArrayList<>();

        String sql = "SELECT * from Vaga v, Empresa e, Usuario u where e.id_usuario = ? and v.empresa_id = e.cnpj and u.id = e.id_usuario";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, usuario.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                Long id = resultSet.getLong("id_vaga");
                Float salario = resultSet.getFloat("salario");
                String descricao_vaga = resultSet.getString("descricao_vaga");
                String data_limite = resultSet.getString("data_limite");
                String empresa_id = resultSet.getString("empresa_id");
                String status_vaga = resultSet.getString("status_vaga");

                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                
                try {
                    LocalDate inputDate = LocalDate.parse(data_limite, formatter);
                    
                    LocalDate currentDate = LocalDate.now();
                
                    if (inputDate.isBefore(currentDate)) {
                        status_vaga = "ENCERRADA";
                    } else if (inputDate.isAfter(currentDate)) {
                        status_vaga = "ABERTA";
                    } else {
                        status_vaga = "ENCERRADA";
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Formato de data inválido: " + e.getMessage());
                }

                Empresa empresa = new EmpresaDAO().get(empresa_id);
                

                Vaga vaga = new Vaga(id, salario, descricao_vaga, data_limite, empresa, status_vaga);

                listaVagas.add(vaga);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaVagas;
    }

    public List<Vaga> getPorCidade(String cidade) {
        List<Vaga> listaVagas = new ArrayList<>();
        String sql = "SELECT * FROM Vaga v JOIN Empresa e ON v.empresa_id = e.cnpj WHERE e.cidade = ?";

        try (Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cidade); // Configura o parâmetro da consulta
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                float salario = resultSet.getFloat("salario");
                String descricao_vaga = resultSet.getString("descricao_vaga");
                String data_limite = resultSet.getString("data_limite");
                String empresa_id = resultSet.getString("empresa_id");
                String status_vaga = resultSet.getString("status_vaga");

                EmpresaDAO empresaDAO = new EmpresaDAO();
                Empresa empresa = empresaDAO.get(empresa_id);

                Vaga vaga = new Vaga(salario, descricao_vaga, data_limite, empresa, status_vaga);
                listaVagas.add(vaga);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaVagas;
    }

}