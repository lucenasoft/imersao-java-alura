import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // Gerando figurinhas
        
        // Consumindo API IMDB
        String IMDB_API_KEY = "4o9boCMUJHxc3llG6MQEytnOBxashXqIAacggzd";
        // consumir API do RapidAi, conexão HTTP
        String url = "https://api.nasa.gov/planetary/apod?api_key=g" + IMDB_API_KEY;
        URI adress = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(adress).GET().build();
        var response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        
        // pegar so os dados que interessam (abstração).
        var parse = new JsonParser();
        List<Map<String, String>> listDeFilmes = parse.parse(body);
        
        // exibir e manipular os dados
        for (int i = 0; i < 20; i++) {
            Map<String,String> filme = listDeFilmes.get(i);
            System.out.println(filme.get("title"));
            System.out.println(filme.get("image"));
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int numeroEstrelinhas = (int) classificacao;
            for (int n = 1; n <= numeroEstrelinhas; n++) {
                System.out.print("*");
            }
            System.out.println();
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

            System.out.println("GERANDO FIGURINHA");
            GeradoraDeFigurinhas geradoraDeFigurinhas = new GeradoraDeFigurinhas();
            geradoraDeFigurinhas.cria(new URL(filme.get("image")).openStream()
            ,"saida/"+filme.get("title")+".png");
        }
    }
}
