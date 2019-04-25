package org.kingsski.kaas.client;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ResultsServiceClientFactory {

    private static final Map<ResultsServiceClient, OkHttpClient> clients = new HashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger("results-service-network-log");

    public static ResultsServiceClient newInstance(String baseUrl) {
        return newInstance(baseUrl, null);
    }

    public static ResultsServiceClient newInstance(String baseUrl, Collection<Interceptor> interceptors) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                // Set user agent
                .addInterceptor(chain -> {
                    Request request = chain.request();

                    request = request.newBuilder()
                            .addHeader("User-Agent", "results-service-sdk")
                            .build();

                    return chain.proceed(request);
                })
                // Network call log (alternatively use addNetworkInterceptor)
                .addInterceptor(chain -> {
                    Request request = chain.request();

                    long t1 = System.nanoTime();
                    LOGGER.info(String.format(">>>>%nSending request %s%n%s%n>>>>",
                            request.url(), request.headers()));

                    Response response = chain.proceed(request);

                    long t2 = System.nanoTime();
                    LOGGER.info(String.format("<<<<%nReceived response for %s in %.1fms%n%s%n<<<<",
                            response.request().url(), (t2 - t1) / 1_000_000d, response.headers()));

                    return response;
                });

        if (interceptors != null) {
            for (Interceptor interceptor : interceptors) {
                clientBuilder.addInterceptor(interceptor);
            }
        }

        OkHttpClient client = clientBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        ResultsServiceClient resultsServiceClient = retrofit.create(ResultsServiceClient.class);

        clients.put(resultsServiceClient, client);

        return resultsServiceClient;
    }

    public static void gracefulCloseClient(ResultsServiceClient resultsServiceClient) {
        OkHttpClient client = clients.get(resultsServiceClient);
        gracefulCloseOkHttpClient(client);
    }

    public static void gracefulCloseAllClients() {
        for (OkHttpClient client : clients.values()) {
            gracefulCloseOkHttpClient(client);
        }
    }

    private static void gracefulCloseOkHttpClient(OkHttpClient client) {
        client.dispatcher().executorService().shutdown();
        client.connectionPool().evictAll();
    }
}
