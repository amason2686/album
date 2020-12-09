package org.example;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
//.URI will Provide methods from creating URI instances from compnents or by parsing the string form of those compnents
//.HttpClient is used to send requests and retrieve their responsed. Created through a builder. Configurable for more specific requests, we use the default here.
//.HttpResponse allows us to access the status code, headers, response body, and the Request corresponding to the Response.


public class App
{
    private static final String POSTS_APU_URL = "https://jsonplaceholder.typicode.com/photos";
    public static void main( String[] args ) throws IOException, InterruptedException {
        //*The next 3 lines are for search functionality
        //Scanner input = new Scanner(System.in);
        //System.out.println("Which album would you like to find? Please enter a whole number from 1 - 100");
        //String albumQuery = input.nextLine();


        //creating the request for data on the webpage
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(POSTS_APU_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //if (albumQuery != null) {
        //running parse method on response given
            parse(response.body());
        //}

    }

    //Using the external library org.json maven we can make an array from the JSON data in response
    public static void parse(String responseBody) {
        JSONArray albums = new JSONArray(responseBody);
        for (int i = 0; i < albums.length(); i++) {
            JSONObject album = albums.getJSONObject(i);
            int id = album.getInt("id");
            int albumID = album.getInt("albumId");
            String title = album.getString("title");
            System.out.println(id + "  " + title + "  " + albumID);
        }
    }
}

