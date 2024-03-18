package com.example.demo;

import java.util.Date;

import com.example.demo.configuration.AppUser;
import com.example.demo.configuration.LoggedInUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banco", "usuario", "senha")) {
	            Statement statement = connection.createStatement();
	            
	            // Consulta SQL vulnerável à injeção
	            String query = "SELECT * FROM usuarios WHERE nome = '" + userInput + "'";
	            
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
       
	@GetMapping("/")
        public String healthCheck(){
                return "HEALTH CHECK OK!";
        }

	@GetMapping("/secured")
	public Object secured(@LoggedInUser AppUser appUser){
		return appUser.getUser();
	}

	@GetMapping("/secured-admin")
	@PreAuthorize("hasRole('ROLE_admin')")
	public String securedAdmin(){
		return "Only admin can see  this";
	}
	
	@GetMapping("/public")
	public String pub(){
		
		return "This is public endpoint";
	}

	@GetMapping("/what-is-the-time")
	String time(){
		return new Date().toString();
	}
	
	@GetMapping("/devops")
	String turma(){
		return "DEVOPS - VERSAO 1.0";

	}
}	
