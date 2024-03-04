import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

import ed.ArmazenadorPrincipal;
/**
 * Escreva uma descri��o da classe Gravador aqui.
 * 
 * @author Cinthia Alves Barreto, Isabel Cavalcante Motta, Isabella Rubio Venancio
 * @version (um n�mero da vers�o ou uma data)
 */
public class Gravador
{
    // Criar a estrutura JSON conforme o formato desejado
    JSONObject arquivo = new JSONObject();
        
    /**
     * Construtor para objetos da classe Gravador
     */
    public Gravador(ArmazenadorPrincipal ed)
    {        
        arquivo.put("desenho", ed.gravarArmazenadorPrincipal());

        // Escrever o JSON em um arquivo
        try (FileWriter file = new FileWriter("teste.json")) {
            file.write(arquivo.toString(4)); // O argumento 4 indica a quantidade de espa�os de recuo para a formata��o
            System.out.println("Arquivo JSON criado com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    

    
    
    
}
