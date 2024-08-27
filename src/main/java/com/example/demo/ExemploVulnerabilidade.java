import java.util.Date;

import com.example.demo.configuration.AppUser;
import com.example.demo.configuration.LoggedInUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
public class ExemploVulnerabilidade {

    public static void main(String[] args) {
        String userInput = args[0]; // Supondo que userInput seja a entrada do usuário

        // Conexão com o banco de dados (apenas para fins de exemplo)
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banco", "usuario", "senha")) {
            Statement statement = connection.createStatement();
            
            // Consulta SQL vulnerável à injeção
            String query = "SELECT * FROM produtos WHERE nome = '" + userInput + "'";
            
            // Execução da consulta
            ResultSet resultSet = statement.executeQuery(query);

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
