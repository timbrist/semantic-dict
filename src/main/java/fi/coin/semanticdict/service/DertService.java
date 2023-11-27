package fi.coin.semanticdict.service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;
public class DertService {

    private static final String  API_URL = "https://api-inference.huggingface.co/models/facebook/detr-resnet-50";
    private static final String API_TOKEN = "";

    public JSONObject query(byte[] imageData) {
        try {
//            byte[] data = Files.readAllBytes(Paths.get(filename));

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Authorization", "Bearer " + API_TOKEN)
                    .header("Content-Type", "application/octet-stream")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(imageData))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String resBody = response.body();
            resBody = resBody.replace("[","")
                    .replace("]","");
            System.out.println(resBody);
            JSONObject result = new JSONObject(resBody);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Or handle the exception as appropriate
        }
    }
}
