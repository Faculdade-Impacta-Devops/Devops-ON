import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BrokenAccessControlExample {
    // Removido o método main
}

@RestController
class UserController {

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable("id") String id) {
        // Supondo que o ID do usuário seja passado diretamente na URL
        // e não há verificação de autorização para garantir que o usuário autenticado
        // tenha permissão para acessar os dados do usuário com o ID fornecido.
        
        // Consulta ao banco de dados para obter informações do usuário
        String userInfo = getUserInfoFromDatabase(id);
        
        return userInfo;
    }

    private String getUserInfoFromDatabase(String id) {
        // Simulação de uma consulta ao banco de dados
        // Em um cenário real, você faria uma consulta ao banco de dados aqui
        return "Informações do usuário com ID: " + id;
    }
}