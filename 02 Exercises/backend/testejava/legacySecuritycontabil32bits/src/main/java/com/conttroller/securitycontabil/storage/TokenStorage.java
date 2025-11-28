package com.conttroller.securitycontabil.storage;

import java.io.IOException;
import java.util.Optional;

public interface TokenStorage {
    /**
     * Persiste o JSON (criptografado internamente conforme implementação).
     * @param key chave lógica (ex: "TokensDPAPI" or filename)
     * @param json JSON a salvar
     */
    void save(String key, String json) throws IOException;

    /**
     * Lê o JSON (descriptografado) se existir.
     * @param key chave lógica
     * @return Optional JSON
     */
    Optional<String> load(String key) throws IOException;

    /**
     * Remove o valor, se suportado.
     */
    void delete(String key) throws IOException;
}