package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Inscricao;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Vaga;

public class InscricaoDAO extends GenericDAO {

    public Inscricao getbyID(Long id_inscricao) {
        Inscricao inscricao = null;
        String sql = "SELECT * from Inscricao WHERE id_inscricao = ?";
    
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id_inscricao);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String profissionalID = resultSet.getString("cpf_id");
                Long vagaID = resultSet.getLong("vaga_id");
                Integer resultado = resultSet.getInt("resultado");
                String qualificacao = resultSet.getString("qualificacao");
                
                Profissional profissional = new ProfissionalDAO().get(profissionalID);
                Vaga vaga = new VagaDAO().get(vagaID);

                inscricao = new Inscricao(id_inscricao, profissional, vaga, resultado, qualificacao);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return inscricao;
    }

    public Inscricao getbyIDVagaIDCpf(String cpf_id, Long vaga_id) {
        Inscricao inscricao = null;
        String sql = "SELECT * FROM Inscricao WHERE cpf_id = ? AND vaga_id = ?";
    
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, cpf_id);
            statement.setLong(2, vaga_id);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Long id_inscricao = resultSet.getLong("id_inscricao");
                    Integer resultado = resultSet.getInt("resultado");
                    String qualificacao = resultSet.getString("qualificacao");
    
                    Profissional profissional = new ProfissionalDAO().get(cpf_id);
                    Vaga vaga = new VagaDAO().get(vaga_id);
    
                    inscricao = new Inscricao(id_inscricao, profissional, vaga, resultado, qualificacao);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return inscricao;
    }
    
    public List<Inscricao> getAllProfissional(Profissional profissional) {

        List<Inscricao> listaInscricao = new ArrayList<>();

        String sql = "SELECT * FROM Inscricao i, Profissional p, Vaga v WHERE i.cpf_id = p.cpf and i.vaga_id = v.id_vaga";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getCpf());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long vaga_id = resultSet.getLong("vaga_id");
                Vaga vaga = new VagaDAO().get(vaga_id);

                Integer resultado = resultSet.getInt("resultado");
                String qualificacao = resultSet.getString("qualificacao");

                Inscricao inscricao = new Inscricao(profissional, vaga, resultado, qualificacao);

                listaInscricao.add(inscricao);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaInscricao;
    }

    public List<Inscricao> getAllEmpresa(Empresa empresa) {

        List<Inscricao> listaInscricao = new ArrayList<>();

        String sql = "SELECT * FROM Inscricao i, Vaga v, Empresa e WHERE e.cnpj = ? and e.cnpj = v.empresa_id and v.id_vaga = i.vaga_id";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, empresa.getCnpj());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long vaga_id = resultSet.getLong("vaga_id");
                Vaga vaga = new VagaDAO().get(vaga_id);

                String cpf_id = resultSet.getString("cpf_id");
                Profissional profissional = new ProfissionalDAO().get(cpf_id);

                Integer resultado = resultSet.getInt("resultado");
                String qualificacao = resultSet.getString("qualificacao");

                Inscricao inscricao = new Inscricao(profissional, vaga, resultado, qualificacao);

                listaInscricao.add(inscricao);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaInscricao;
    }

    public List<Inscricao> getAllInscritosnaVaga(Long id_vaga, Empresa empresa) {

        List<Inscricao> listaInscricao = new ArrayList<>();

        String sql = "SELECT * FROM Inscricao i, Vaga v, Empresa e WHERE e.cnpj = ? and e.cnpj = v.empresa_id and v.id_vaga = i.vaga_id AND v.id_vaga = ?";

        
        Vaga vaga = new VagaDAO().get(id_vaga);
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, empresa.getCnpj());
            statement.setLong(2, vaga.getId_vaga());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long vaga_id = resultSet.getLong("vaga_id");

                String cpf_id = resultSet.getString("cpf_id");
                Profissional profissional = new ProfissionalDAO().get(cpf_id);

                Integer resultado = resultSet.getInt("resultado");
                String qualificacao = resultSet.getString("qualificacao");

                Inscricao inscricao = new Inscricao(profissional, vaga, resultado, qualificacao);

                listaInscricao.add(inscricao);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaInscricao;
    }

    public void insert(Inscricao inscricao) {
        String sql = "INSERT INTO Inscricao (cpf_id, vaga_id, resultado, qualificacao) VALUES (?, ?, 2, ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ;
            statement = conn.prepareStatement(sql);
            statement.setString(1, inscricao.getProfissional().getCpf());
            statement.setLong(2, inscricao.getVaga().getId_vaga());
            statement.setString(3, inscricao.getQualificacao());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Inscricao inscricao) {
        String sql = "DELETE FROM Inscricao WHERE cpf_id = ? and vaga_id";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, inscricao.getProfissional().getCpf());
            statement.setLong(2, inscricao.getVaga().getId_vaga());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void update(Inscricao inscricao) {
        String sql = "UPDATE Inscricao SET resultado = ?, qualificacao = ? WHERE cpf_id = ? and vaga_id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, inscricao.getResultado());
            statement.setString(2, inscricao.getQualificacao());
            statement.setString(3, inscricao.getProfissional().getCpf());
            statement.setLong(4, inscricao.getVaga().getId_vaga());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
