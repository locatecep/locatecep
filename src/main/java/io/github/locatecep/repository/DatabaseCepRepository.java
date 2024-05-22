package io.github.locatecep.repository;

import io.github.locatecep.config.Database;
import io.github.locatecep.model.Cep;
import io.github.locatecep.service.exception.DatabaseConnectionError;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseCepRepository implements CepRepository {

    public Cep buscarCep(String numeracao) {
        Connection conn = Database.getConnection();

        try {
            String sql = "SELECT uf, cidade FROM faixas_cep WHERE ?::bigint BETWEEN CAST(cep_inicio AS bigint) AND CAST(cep_fim AS bigint) OFFSET 1 LIMIT 1";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, numeracao);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Cep cep = new Cep();
                cep.setUf(rs.getString("uf"));
                cep.setCidade(rs.getString("cidade"));
                cep.setNumeracao(numeracao);
                return cep;
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionError(e.getMessage());
        } finally {
            Database.closeConnection(conn);
        }
        return null;
    }
}
