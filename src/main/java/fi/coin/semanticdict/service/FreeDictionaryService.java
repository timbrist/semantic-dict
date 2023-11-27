package fi.coin.semanticdict.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
public class FreeDictionaryService {

    private static final String  API_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/";

    public JSONObject query(String word) {
        try {

            // Create a URL object
            URL url = new URL(API_URL + word);
            // Open a connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod("GET");

            // Set request properties, if any (like User-Agent, Headers, etc.)

            // Reading the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            String resBody = response.toString();
            System.out.println(resBody);
            JSONObject result = new JSONObject(resBody);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Or handle the exception as appropriate
        }
    }
}
