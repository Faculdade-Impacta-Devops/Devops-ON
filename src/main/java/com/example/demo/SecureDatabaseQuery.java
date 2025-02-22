package com.example.demo;

import java.sql.*;

public class SecureDatabaseQuery {

    public static void main(String[] args) {
        String userInput = args[0]; // Supondo que userInput seja a entrada do usuário

        // Conexão com o banco de dados (apenas para fins de exemplo)
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banco", "usuario", "senha")) {
            // Consulta SQL segura usando PreparedStatement
            String query = "SELECT * FROM usuarios WHERE nome = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userInput);

            // Execução da consulta
            ResultSet resultSet = preparedStatement.executeQuery();

            // Processamento dos resultados
            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                System.out.println("Nome: " + nome + ", Email: " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}