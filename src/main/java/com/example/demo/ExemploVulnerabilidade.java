import java.util.Date;

import com.example.demo.configuration.AppUser;
import com.example.demo.configuration.LoggedInUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import org.apache.commons.text.StringEscapeUtils;

public class ExemploVulnerabilidade {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Erro: Nenhuma entrada fornecida.");
            return;
        }

        String userInput = args[0]; // Supondo que userInput seja a entrada do usuário

        // Validação básica da entrada do usuário
        if (userInput == null || userInput.trim().isEmpty()) {
            System.err.println("Erro: Entrada inválida.");
            return;
        }

        // Conexão com o banco de dados (apenas para fins de exemplo)
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banco", "usuario", "senha")) {
            // Consulta SQL segura usando PreparedStatement
            String query = "SELECT * FROM produtos WHERE nome = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userInput);
            
            // Execução da consulta
            ResultSet resultSet = preparedStatement.executeQuery();

            // Processamento dos resultados
            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                
                // Sanitização da saída para evitar XSS
                String safeNome = StringEscapeUtils.escapeHtml4(nome);
                String safeEmail = StringEscapeUtils.escapeHtml4(email);
                
                System.out.println("Nome: " + safeNome + ", Email: " + safeEmail);
            }
        } catch (SQLException e) {
            // Registro adequado do erro
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }
}
