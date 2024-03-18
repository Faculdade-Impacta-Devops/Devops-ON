package com.example.demo;

import java.util.Date;

import com.example.demo.configuration.AppUser;
import com.example.demo.configuration.LoggedInUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		String userInput = args[0]; // Supondo que userInput seja a entrada do usuário

	    	Statement statement = connection.createStatement();
	        
	    	String query = "SELECT * FROM usuarios WHERE nome = '" + userInput + "'";
	            
	    	ResultSet resultSet = statement.executeQuery(query);

	   	 while (resultSet.next()) {
			String nome = resultSet.getString("nome");
			String email = resultSet.getString("email");
			System.out.println("Nome: " + nome + ", Email: " + email);
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
