package fr.diderot.cofly.database;

import java.io.IOException;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public abstract class HighLevelClient {

    private static final String HOST = "localhost", SCHEME = "http";
    private static final int PORT = 9200;

    public static RestHighLevelClient client = null;

    public static RestHighLevelClient connect() {
        if (client == null) {  
            client = new RestHighLevelClient(RestClient.builder(new HttpHost(HOST, PORT, SCHEME)));
        }
        return client;
    }

    public static void close() {
        try {
            client.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        client = null;
    }
}
