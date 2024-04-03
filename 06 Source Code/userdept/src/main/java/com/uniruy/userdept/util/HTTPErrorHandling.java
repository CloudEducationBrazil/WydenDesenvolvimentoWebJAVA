package com.uniruy.userdept.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPErrorHandling{
	private String endpoint;
	public String resposta;
	
	public HTTPErrorHandling(String endpoint) {
		this.endpoint = endpoint;  
	}
	
    public String retornoURL() {
        try {
            // URL da solicitação
            URL url = new URL(endpoint); //new URL("http://example.com");
            
            // Abrindo conexão
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            // Configurando método de solicitação
            connection.setRequestMethod("GET");
            
            // Lendo a resposta
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Se a resposta for bem-sucedida (código 200)
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                // Processar a resposta aqui, se necessário
                //System.out.println("Resposta: " + response.toString());
                resposta = "Resposta: " + response.toString();
            } else {
                // Se ocorrer um erro HTTP
                //System.out.println("Erro HTTP: " + responseCode);
                resposta = "Erro HTTP: " + responseCode;
            }
            
            // Fechar conexão
            connection.disconnect();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resposta;
    }

	@Override
	public String toString() {
		return "HTTPErrorHandling [endpoint=" + endpoint + ", resposta=" + resposta + "]";
	}
}