import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    private static final HttpClient httpclient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    public static void main(String[] args) {
        try {
            sendPostRequest("http://localhost:8080/students", "{\"name\": \"Mikolaj\", \"lastName\": \"Kowalski\", \"age\": 21, \"studentIndex\": \"123241\"}");

            sendGetrequest("http://localhost:8080/students");
        }catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
    private static void sendGetrequest(String url)throws IOException,InterruptedException{
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpResponse<String> response = httpclient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println("StatusCode: "+response.statusCode());
        System.out.println("ResponseBody: "+response.body());
    }

    private static void sendPostRequest(String url, String requestBody)throws IOException,InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = httpclient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status code: " + response.statusCode());
        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            System.out.println("Request successful");
            System.out.println("Response body: " + response.body());
        } else if (response.statusCode() >= 400 && response.statusCode() < 500) {
            System.out.println("Client error: " + response.statusCode());
        } else if (response.statusCode() >= 500 && response.statusCode() < 600) {
            System.out.println("Server error: " + response.statusCode());
        } else {
            System.out.println("Unexpected status code: " + response.statusCode());
        }
    }

}