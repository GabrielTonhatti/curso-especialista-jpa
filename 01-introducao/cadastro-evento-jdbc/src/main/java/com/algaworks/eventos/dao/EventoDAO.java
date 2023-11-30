package com.algaworks.eventos.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.algaworks.eventos.model.Evento;

public class EventoDAO {

    private final Connection connection;

    public EventoDAO(Connection connection) {
        this.connection = connection;
    }

    public Integer salvar(Evento evento) {
        String sql = "INSERT INTO EVENTO (NOME, DATA) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, evento.getNome());
            preparedStatement.setDate(2, new Date(evento.getData().getTime()));

            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                resultSet.next();

                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(Evento evento) {
        String sql = "UPDATE EVENTO SET NOME = ?, DATA = ? WHERE ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, evento.getNome());
            preparedStatement.setDate(2, new Date(evento.getData().getTime()));
            preparedStatement.setInt(3, evento.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Evento buscar(Integer id) {
        String sql = "SELECT * FROM EVENTO WHERE ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }

                return new Evento(id, resultSet.getString("nome"),
                        resultSet.getDate("data"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Evento> listar() {
        String sql = "SELECT * FROM EVENTO";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Evento> eventos = new ArrayList<Evento>();

                while (resultSet.next()) {
                    var evento = new Evento(
                            resultSet.getInt("id"),
                            resultSet.getString("nome"),
                            resultSet.getDate("data")
                    );
                    eventos.add(evento);
                }

                return eventos;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(Integer id) {
        String sql = "DELETE FROM EVENTO WHERE ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
