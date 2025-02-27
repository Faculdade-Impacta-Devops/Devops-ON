import java.util.Date;

import com.example.demo.configuration.AppUser;
import com.example.demo.configuration.LoggedInUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExemploVulnerabilidade {

    private static final Logger LOGGER = Logger.getLogger(ExemploVulnerabilidade.class.getName());

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Por favor, forneça um nome de usuário.");
            return;
        }

        String userInput = args[0]; // Supondo que userInput seja a entrada do usuário

        // Validação básica da entrada do usuário
        if (userInput == null || userInput.trim().isEmpty()) {
            System.out.println("Entrada de usuário inválida.");
            return;
        }

        // Conexão com o banco de dados (apenas para fins de exemplo)
        try (Connection connection = DriverManager.getConnection(System.getenv("DB_URL"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"))) {
            // Consulta SQL usando PreparedStatement para evitar injeção de SQL
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
            LOGGER.log(Level.SEVERE, "Erro ao conectar ao banco de dados", e);
        }
    }
}
