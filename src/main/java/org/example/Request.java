package org.example;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

public class Request {
    private final String path;
    private final List<NameValuePair> queryParams;

    public Request(String requestLine) throws URISyntaxException {
        URI uri = new URI(requestLine.split(" ")[1]);
        this.path = uri.getPath();
        this.queryParams = URLEncodedUtils.parse(uri, StandardCharsets.UTF_8);
    }

    public String getPath() {
        return path;
    }

    public List<NameValuePair> getQueryParams() {
        return queryParams;
    }

    public Optional<String> getQueryParam(String name) {
        return queryParams.stream()
                .filter(param -> param.getName().equals(name))
                .map(NameValuePair::getValue)
                .findFirst();
    }
}