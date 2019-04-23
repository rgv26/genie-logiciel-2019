package fr.diderot.cofly.database;

import fr.diderot.cofly.utils.Tuple;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.GetAliasesResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.*;

import static fr.diderot.cofly.database.HighLevelClient.client;

public class Search<T> {

    private static final String ID = "_id", TYPE = "doc";

    public static Set<String> getAllIndex() throws IOException {
        GetAliasesResponse response;

        HighLevelClient.connect();

        response = client.indices().getAlias(new GetAliasesRequest(),
                RequestOptions.DEFAULT);

        HighLevelClient.close();

        if (response.status() != RestStatus.OK) {
            return Collections.emptySet();
        }

        return response.getAliases().keySet();
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
        String value;
        IndexRequest request;
        IndexResponse response;
        ObjectMapper objectMapper;

        HighLevelClient.connect();

        request = new IndexRequest(table);
        objectMapper = new ObjectMapper();
        value = objectMapper.writeValueAsString(obj);

        request.id(UUID.randomUUID().toString());
        request.type(TYPE);
        request.source(value, XContentType.JSON);

        response = client.index(request, RequestOptions.DEFAULT);

        HighLevelClient.close();


        if (response.status() != RestStatus.CREATED) {
            return null;
        }

        return response.getId();
    }

    public static <T> List<Tuple<String, T>> find(String table, Class<T> tClass)
            throws IOException {
        SearchRequest request;
        SearchResponse response;

        HighLevelClient.connect();

        request = new SearchRequest(table);
        response = client.search(request, RequestOptions.DEFAULT);

        HighLevelClient.close();

        if (response.status() != RestStatus.OK) {
            return Collections.emptyList();
        }

        return result(response, tClass);
    }

    public static <T> Tuple<String, T> find(UUID id, Class<T> type)
            throws IOException {
        SearchRequest request;
        SearchSourceBuilder searchSourceBuilder;
        SearchResponse response;
        MatchQueryBuilder matchQueryBuilder;
        ObjectMapper objectMapper;
        SearchHit sh;
        T obj;

        HighLevelClient.connect();

        request = new SearchRequest();
        matchQueryBuilder = new MatchQueryBuilder(ID, id.toString());
        searchSourceBuilder = new SearchSourceBuilder();
        objectMapper = new ObjectMapper();

        searchSourceBuilder.query(matchQueryBuilder);
        request.source(searchSourceBuilder);

        response = client.search(request, RequestOptions.DEFAULT);
        sh = response.getHits().getAt(0);
        obj = (T) objectMapper.readValue(sh.getSourceAsString(), type);

        HighLevelClient.close();

        if (response.status() != RestStatus.OK) {
            return null;
        }

        return new Tuple<>(sh.getId(), obj);
    }

    public static boolean remove(String index) throws IOException {
        DeleteIndexRequest request;
        AcknowledgedResponse response;

        HighLevelClient.connect();

        request = new DeleteIndexRequest(index);
        response = client.indices().delete(request, RequestOptions.DEFAULT);

        HighLevelClient.close();

        return response.isAcknowledged();
    }

    public static <T> boolean update(UUID id, T obj) throws IOException {
        ObjectMapper objectMapper;
        String value;
        UpdateRequest request;
        UpdateResponse response;

        HighLevelClient.connect();

        request = new UpdateRequest();
        objectMapper = new ObjectMapper();
        value = objectMapper.writeValueAsString(obj);

        request.index(obj.getClass().getSimpleName().toLowerCase());
        request.id(id.toString());
        request.type("doc");
        request.doc(value, XContentType.JSON);

        response = client.update(request, RequestOptions.DEFAULT);

        HighLevelClient.close();

        return response.status() == RestStatus.OK;
    }

    public static <T> boolean removeById(String index, UUID id) throws IOException {
        DeleteRequest request;
        DeleteResponse response;

        HighLevelClient.connect();

        request = new DeleteRequest(index, TYPE, id.toString());
        response = client.delete(request, RequestOptions.DEFAULT);

        HighLevelClient.close();

        if (response.status() != RestStatus.OK) {
            return false;
        }

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

            if (response.status() != RestStatus.OK) {
                return false;
            }

            return (searchHit.length > 0);
        } catch (IOException ex) {
            return false;
        }
    }

    public static <T> List<Tuple<String, T>> searchAllFlight(String departure,
                                                             String arrival, String date, Class<T> tClass) throws IOException {
        SearchRequest request;
        SearchSourceBuilder builder;
        QueryBuilder queryBuilder;
        SearchResponse response;

        HighLevelClient.connect();

        request = new SearchRequest("flight");
        builder = new SearchSourceBuilder();
        queryBuilder = new BoolQueryBuilder()
                .must(QueryBuilders.multiMatchQuery(departure, "departure")
                        .operator(Operator.AND))
                .must(QueryBuilders.multiMatchQuery(arrival, "arrival")
                        .operator(Operator.AND))
                .must(QueryBuilders.multiMatchQuery(date, "date")
                        .operator(Operator.AND));

        builder.query(queryBuilder);
        request.source(builder.query(queryBuilder));

        response = client.search(request, RequestOptions.DEFAULT);

        HighLevelClient.close();

        if (response.status() != RestStatus.OK) {
            return Collections.emptyList();
        }

        return result(response, tClass);
    }

    public static <T> List<Tuple<String, T>> findByTag(String champ, String tag, Class<T> type) throws IOException {
        SearchRequest request;
        SearchResponse response;
        SearchSourceBuilder builder;

        HighLevelClient.connect();

        request = new SearchRequest(type.getSimpleName().toLowerCase());
        builder = new SearchSourceBuilder();

        builder.query(QueryBuilders.queryStringQuery(tag).field(champ));
        request.source(builder);

        response = client.search(request, RequestOptions.DEFAULT);

        HighLevelClient.close();

        if (response.status() != RestStatus.OK) {
            return Collections.emptyList();
        }

        return result(response, type);
    }

    public static <T> Tuple<String, T> findByTagOneElement(String champ, String tag,
                                                           Class<T> type) throws IOException {
        List<Tuple<String, T>> rslt;

        rslt = findByTag(champ, tag, type);
        if (rslt.size() == 0) {
            return null;
        }

        return rslt.get(0);
    }

    public static <T> List<Tuple<String, T>> findSimpleQueries(String table, String name,
                                                               String text, Class<T> tClass) throws IOException {
        SearchRequest request;
        SearchResponse response;
        SearchSourceBuilder searchSourceBuilder;

        HighLevelClient.connect();

        request = new SearchRequest(table);
        searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.query(QueryBuilders.multiMatchQuery(text + "_type", name, TYPE));
        request.source(searchSourceBuilder);

        response = client.search(request, RequestOptions.DEFAULT);

        HighLevelClient.close();

        if (response.status() != RestStatus.OK) {
            return Collections.emptyList();
        }

        return result(response, tClass);
    }

    public static <T> List<Tuple<String, T>> searchAllFlightWithOutDate(String departure,
                                                                        String arrival, Class<T> tClass) throws IOException {
        SearchRequest request;
        SearchSourceBuilder searchSourceBuilder;
        QueryBuilder queryBuilder;
        SearchResponse response;

        HighLevelClient.connect();

        request = new SearchRequest("flight");
        searchSourceBuilder = new SearchSourceBuilder();
        queryBuilder = new BoolQueryBuilder()
                .must(QueryBuilders.multiMatchQuery(departure, "departure")
                        .operator(Operator.AND))
                .must(QueryBuilders.multiMatchQuery(arrival, "arrival")
                        .operator(Operator.AND))
                .must(QueryBuilders.rangeQuery("seats").from(1));

        searchSourceBuilder.query(queryBuilder);
        request.source(searchSourceBuilder.query(queryBuilder));

        response = client.search(request, RequestOptions.DEFAULT);

        HighLevelClient.close();

        if (response.status() != RestStatus.OK) {
            return Collections.emptyList();
        }

        return result(response, tClass);
    }
}
