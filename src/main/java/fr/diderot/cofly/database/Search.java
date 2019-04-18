package fr.diderot.cofly.database;

import static fr.diderot.cofly.database.HighLevelClient.client;
import fr.diderot.cofly.utils.Tuple;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.GetAliasesResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.PrefixQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class Search<T> {

    private static final String ID = "_id", TYPE = "doc";

    public static Set<String> getAllIndex() throws IOException {
        GetAliasesResponse response;
        Set<String> indices;

        HighLevelClient.connect();

        response = client.indices().getAlias(new GetAliasesRequest(),
                RequestOptions.DEFAULT);
        indices = response.getAliases().keySet();

        HighLevelClient.close();

        return indices;
    }

    private static <T> List<Tuple<String, T>>
            result(SearchResponse searchResponse, Class<T> tClass) {
        List<SearchHit> searchHits = Arrays.asList(searchResponse.getHits()
                .getHits());
        List<Tuple<String, T>> results = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        searchHits.forEach(hit -> {
            T obj;
            try {
                obj = objectMapper.readValue(hit.getSourceAsString(), tClass);
                Tuple<String, T> tuple = new Tuple<>(hit.getId(), obj);
                results.add(tuple);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        return results;
    }

    public static <T> String add(T obj, String table) throws IOException {
        String id, value;
        IndexRequest request;
        ObjectMapper objectMapper;

        HighLevelClient.connect();

        request = new IndexRequest(table);
        objectMapper = new ObjectMapper();
        value = objectMapper.writeValueAsString(obj);

        request.id(UUID.randomUUID().toString());
        request.type(TYPE);
        request.source(value, XContentType.JSON);

        id = client.index(request, RequestOptions.DEFAULT).getId();

        HighLevelClient.close();

        return id;
    }

    public static <T> List<Tuple<String, T>> find(String table, Class<T> tClass)
            throws IOException {
        List<Tuple<String, T>> rslt;
        SearchRequest searchRequest;
        SearchResponse searchResponse;

        HighLevelClient.connect();

        searchRequest = new SearchRequest(table);
        searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        rslt = result(searchResponse, tClass);

        HighLevelClient.close();

        return rslt;
    }

    public static <T> Tuple<String, T> find(UUID id, Class<T> type)
            throws IOException {
        SearchRequest searchRequest;
        SearchSourceBuilder searchSourceBuilder;
        MatchQueryBuilder matchQueryBuilder;
        ObjectMapper objectMapper;
        T obj;

        HighLevelClient.connect();

        searchRequest = new SearchRequest();
        matchQueryBuilder = new MatchQueryBuilder(ID, id.toString());
        searchSourceBuilder = new SearchSourceBuilder();
        objectMapper = new ObjectMapper();

        searchSourceBuilder.query(matchQueryBuilder);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest,
                RequestOptions.DEFAULT);
        SearchHit sh = searchResponse.getHits().getAt(0);
        obj = (T) objectMapper.readValue(sh.getSourceAsString(), type);

        HighLevelClient.close();

        return new Tuple<>(sh.getId(), obj);
    }

    public static boolean remove(String index) throws IOException {
        DeleteIndexRequest request;
        boolean isAcknowledged;

        HighLevelClient.connect();

        request = new DeleteIndexRequest(index);
        isAcknowledged = client.indices().delete(request, RequestOptions.DEFAULT).isAcknowledged();

        HighLevelClient.close();

        return isAcknowledged;
    }

    public static <T> boolean update(UUID id, T obj) {
        ObjectMapper objectMapper;
        String value;
        UpdateRequest request;
        UpdateResponse updateResponse;

        try {
            HighLevelClient.connect();

            objectMapper = new ObjectMapper();
            value = objectMapper.writeValueAsString(obj);
            request = new UpdateRequest().index(obj.getClass().getSimpleName().toLowerCase()).id(id.toString())
                    .doc(value, XContentType.JSON);
            updateResponse = client.update(request, RequestOptions.DEFAULT);

            HighLevelClient.close();

            return updateResponse.status() == RestStatus.OK;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public static <T> boolean removeById(String index, UUID id) throws IOException {
        DeleteRequest request;
        DeleteResponse response;

        HighLevelClient.connect();

        request = new DeleteRequest(index, TYPE, id.toString());
        response = client.delete(request, RequestOptions.DEFAULT);

        HighLevelClient.close();

        return !(response.getResult() == DocWriteResponse.Result.NOT_FOUND);
    }

    public static <T> boolean ExistFaild(String tag, String faild, String index) {
        TermQueryBuilder termQueryBuilder;
        SearchResponse response;
        SearchHit[] searchHit;
        try {
            HighLevelClient.connect();

            termQueryBuilder = QueryBuilders.termQuery(tag, faild);
            response = client.search(new SearchRequest(index)
                    .source(new SearchSourceBuilder().query(termQueryBuilder)),
                    RequestOptions.DEFAULT);
            searchHit = response.getHits().getHits();

            HighLevelClient.close();

            return (searchHit.length > 0);
        } catch (IOException ex) {
            return false;
        }
    }

    public static <T> List<Tuple<String, T>> searchAllFlight(String departure,
            String arrival, String date, Class<T> tClass) throws IOException {
        SearchRequest searchRequest;
        SearchSourceBuilder searchSourceBuilder;
        QueryBuilder queryBuilder;
        SearchResponse searchResponse;

        HighLevelClient.connect();

        searchRequest = new SearchRequest("flight");
        searchSourceBuilder = new SearchSourceBuilder();
        queryBuilder = new BoolQueryBuilder()
                .must(QueryBuilders.multiMatchQuery(departure, "departure")
                        .operator(Operator.AND))
                .must(QueryBuilders.multiMatchQuery(arrival, "arrival")
                        .operator(Operator.AND))
                .must(QueryBuilders.multiMatchQuery(date, "date")
                        .operator(Operator.AND));

        searchSourceBuilder.query(queryBuilder);
        searchRequest.source(searchSourceBuilder.query(queryBuilder));

        searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        HighLevelClient.close();

        return result(searchResponse, tClass);
    }

    public List<Tuple<String, T>> findByTag(String champ, String tag, Class<T> type) throws IOException {
        SearchRequest searchRequest;
        SearchResponse searchResponse;
        PrefixQueryBuilder prefixQueryBuilder;

        HighLevelClient.connect();

        searchRequest = new SearchRequest(type.getSimpleName().toLowerCase());
        prefixQueryBuilder = new PrefixQueryBuilder(champ, tag);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(prefixQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        HighLevelClient.close();

        if (searchResponse.getHits().totalHits == 0) {
            return null;
        }
        return result(searchResponse, type);
    }

    public static <T> Tuple<String, T> findByTagOneElement(String champ, String tag,
            Class<T> type) throws IOException {
        SearchRequest searchRequest;
        TermQueryBuilder termQueryBuilder;
        SearchSourceBuilder searchSourceBuilder;
        SearchResponse searchResponse;
        SearchHit sh;
        ObjectMapper objectMapper;
        T obj;

        HighLevelClient.connect();

        searchRequest = new SearchRequest();
        objectMapper = new ObjectMapper();
        termQueryBuilder = new TermQueryBuilder(champ, tag);
        searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(termQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        HighLevelClient.close();

        sh = searchResponse.getHits().getAt(0);

        obj = (T) objectMapper.readValue(sh.getSourceAsString(), type);

        return (new Tuple<>(sh.getId(), obj));
    }

    public static <T> List<Tuple<String, T>> findSimpleQueries(String table, String name,
            String text, Class<T> tClass) throws IOException {
        SearchRequest searchRequest;
        SearchResponse searchResponse;

        HighLevelClient.connect();

        /*SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.query(QueryBuilders.multiMatchQuery(text + "_type", name, "doc"));
        searchRequest.source(searchSourceBuilder);
         */
        searchRequest = new SearchRequest(table);
        searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        HighLevelClient.close();

        return result(searchResponse, tClass);
    }

    public static <T> List<Tuple<String, T>> searchAllFlightWithOutDate(String departure,
            String arrival, Class<T> tClass) throws IOException {
        SearchRequest searchRequest;
        SearchSourceBuilder searchSourceBuilder;
        QueryBuilder queryBuilder;
        SearchResponse searchResponse;

        HighLevelClient.connect();

        searchRequest = new SearchRequest("flight");
        searchSourceBuilder = new SearchSourceBuilder();
        queryBuilder = new BoolQueryBuilder()
                .must(QueryBuilders.multiMatchQuery(departure, "departure")
                        .operator(Operator.AND))
                .must(QueryBuilders.multiMatchQuery(arrival, "arrival")
                        .operator(Operator.AND))
                .must(QueryBuilders.rangeQuery("seats").from(1));
        searchSourceBuilder.query(queryBuilder);
        searchRequest.source(searchSourceBuilder.query(queryBuilder));
        searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        HighLevelClient.close();

        return result(searchResponse, tClass);
    }
}
