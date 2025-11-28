package com.conttroller.securitycontabil.services;

import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.*;
import java.time.Duration;

@Service
public class LocalizacaoService {

    // ========================================================
    // HttpClient com timeout + HTTP/2
    // ========================================================
    private static final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .version(HttpClient.Version.HTTP_2)
            .build();

    // ========================================================
    // API 1: IP público (ipify)
    // ========================================================
    public static String getPublicIP() throws Exception {

    	//https://api64.ipify.org/?format=json
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api64.ipify.org?format=json"))
                .timeout(Duration.ofSeconds(5))
                .GET().build();

        String js = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        return extract(js, "\"ip\":\"", "\"");
    }

    // ========================================================
    // API 2: Geolocalização via ipinfo.io + fallback ip-api
    // ========================================================
    public static GeoInfo getGeoInfo(String ip) throws Exception {

        GeoInfo info = new GeoInfo();
        info.ip = ip;

        // -------------------------
        // 1) TENTA ipinfo
        // -------------------------
        try {
        	// https://ipinfo.io/187.44.228.230/json
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://ipinfo.io/" + ip + "/json"))
                    .timeout(Duration.ofSeconds(5))
                    .GET().build();

            String js = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

            info.city    = extract(js, "\"city\":\"", "\"");
            info.region  = extract(js, "\"region\":\"", "\"");
            info.country = extract(js, "\"country\":\"", "\"");
            info.zip     = extract(js, "\"postal\":\"", "\"");

            String loc = extract(js, "\"loc\":\"", "\"");
            if (!isBlank(loc) && loc.contains(",")) {
                info.lat = loc.split(",")[0];
                info.lon = loc.split(",")[1];
            }
        }
        catch (Exception e) {
            // Falha ipinfo -> fallback automático
        }

        // -------------------------
        // 2) FALLBACK ip-api.com
        // -------------------------
        if ((isBlank(info.city) && isBlank(info.region) && isBlank(info.country)) || isBlank(info.zip)) {

        	// http://ip-api.com/json/187.44.228.230
            HttpRequest req2 = HttpRequest.newBuilder()
                    .uri(URI.create("http://ip-api.com/json/" + ip +
                            "?fields=status,country,regionName,city,zip,lat,lon"))
                    .timeout(Duration.ofSeconds(5))
                    .GET().build();

            String js2 = client.send(req2, HttpResponse.BodyHandlers.ofString()).body();

            if (js2.contains("\"status\":\"success\"")) {
                info.country = extract(js2, "\"country\":\"", "\"");
                info.region  = extract(js2, "\"regionName\":\"", "\"");
                info.city    = extract(js2, "\"city\":\"", "\"");
                info.zip     = extract(js2, "\"zip\":\"", "\"");
                info.lat     = extract(js2, "\"lat\":", ",");
                info.lon     = extract(js2, "\"lon\":", "}");
            }
        }

        return info;
    }

    // ========================================================
    // API 3: ViaCEP
    // ========================================================
    public static Endereco getEnderecoFromCEP(String cep) throws Exception {

        cep = cep.replace("-", "").trim();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://viacep.com.br/ws/" + cep + "/json/"))
                .timeout(Duration.ofSeconds(5))
                .GET().build();

        String js = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        if (js.contains("\"erro\": true")) return null;

        Endereco e = new Endereco();
        e.logradouro = extract(js, "\"logradouro\":\"", "\"");
        e.bairro     = extract(js, "\"bairro\":\"", "\"");
        e.cidade     = extract(js, "\"localidade\":\"", "\"");
        e.estado     = extract(js, "\"uf\":\"", "\"");

        return e;
    }

    // ========================================================
    // Função principal
    // ========================================================
    public static LocalizacaoCompleta getLocalizacaoCompleta() throws Exception {

        String ip = getPublicIP();
        GeoInfo geo = getGeoInfo(ip);

        Endereco end = null;

        if (geo.zip != null && !geo.zip.isBlank() && !geo.zip.startsWith("40000")) {
            end = getEnderecoFromCEP(geo.zip);
        }

        if (end == null) {
            end = new Endereco();
            end.logradouro = "";
            end.bairro = "";
            end.cidade = geo.city != null ? geo.city : "";
            end.estado = geo.region != null ? geo.region : "";
        }

        return new LocalizacaoCompleta(ip, geo, end);
    }

    // ========================================================
    // Utilitários
    // ========================================================
    private static String extract(String json, String prefix, String suffix) {
        int start = json.indexOf(prefix);
        if (start == -1) return "";
        start += prefix.length();
        int end = json.indexOf(suffix, start);
        if (end == -1) return "";
        return json.substring(start, end);
    }

    private static boolean isBlank(String v) {
        return v == null || v.isBlank();
    }

    public static String empty(String v) {
        return (v == null || v.isBlank()) ? "(não disponível)" : v;
    }

    // ========================================================
    // Classes DTO internas
    // ========================================================
    public static class GeoInfo {
        public String ip, country, region, city, zip, lat, lon;
    }

    public static class Endereco {
        public String logradouro, bairro, cidade, estado;
    }

    public static class LocalizacaoCompleta {
        public final String ip;
        public final GeoInfo geo;
        public final Endereco endereco;

        public LocalizacaoCompleta(String ip, GeoInfo geo, Endereco endereco) {
            this.ip = ip;
            this.geo = geo;
            this.endereco = endereco;
        }
    }
}