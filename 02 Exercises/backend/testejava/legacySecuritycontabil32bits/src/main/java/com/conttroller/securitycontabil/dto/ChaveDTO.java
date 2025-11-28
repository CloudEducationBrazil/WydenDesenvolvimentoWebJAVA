package com.conttroller.securitycontabil.dto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

import com.conttroller.securitycontabil.config.OffsetDateTimeWithoutOffsetDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChaveDTO {

    private String id;

    private String cnpj;
    
    @JsonDeserialize(using = OffsetDateTimeWithoutOffsetDeserializer.class)
    private OffsetDateTime validade;
    
    private ServidorDTO servidor;
    private String assinatura;

    // Getters e Setters da classe principal
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public OffsetDateTime getValidade() {
        return validade;
    }
    public void setValidade(OffsetDateTime validade) {
        this.validade = validade;
    }

    public ServidorDTO getServidor() {
        return servidor;
    }
    public void setServidor(ServidorDTO servidor) {
        this.servidor = servidor;
    }

    public String getAssinatura() {
        return assinatura;
    }
    public void setAssinatura(String assinatura) {
        this.assinatura = assinatura;
    }
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ServidorDTO {

    	private String id; // <-- Adicione este cam
        private String idServidor;
        private String grupo;
        private String nome;
        
        @JsonDeserialize(using = OffsetDateTimeWithoutOffsetDeserializer.class)
        private OffsetDateTime criacao;
        
        @JsonProperty("listaCNPJ")
        private List<String> listaCNPJ;
        private List<SistemaDTO> sistemas;

        // Getters e Setters
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }       
        
		public String getIdServidor() {
			return idServidor;
		}
		public void setIdServidor(String idServidor) {
			this.idServidor = idServidor;
		}
		public String getGrupo() {
			return grupo;
		}
		public void setGrupo(String grupo) {
			this.grupo = grupo;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public OffsetDateTime getCriacao() {
			return criacao;
		}
		public void setCriacao(OffsetDateTime criacao) {
			this.criacao = criacao;
		}
		public List<String> getListaCNPJ() {
			return listaCNPJ;
		}
		public void setListaCNPJ(List<String> listaCNPJ) {
			this.listaCNPJ = listaCNPJ;
		}
		public List<SistemaDTO> getSistemas() {
			return sistemas;
		}
		public void setSistemas(List<SistemaDTO> sistemas) {
			this.sistemas = sistemas;
		}
		@Override
		public String toString() {
			return "ServidorDTO [id=" + id + ", idServidor=" + idServidor + ", grupo=" + grupo + ", nome=" + nome
					+ ", criacao=" + criacao + ", listaCNPJ=" + listaCNPJ + ", sistemas=" + sistemas + "]";
		}
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SistemaDTO {

        private String id;
        private String sistema;
        private List<ModuloDTO> modulos;

        // Getters e Setters
        public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getSistema() {
			return sistema;
		}
		public void setSistema(String sistema) {
			this.sistema = sistema;
		}
		public List<ModuloDTO> getModulos() {
			return modulos;
		}
		public void setModulos(List<ModuloDTO> modulos) {
			this.modulos = modulos;
		}
		@Override
		public String toString() {
			return "SistemaDTO [id=" + id + ", sistema=" + sistema + ", modulos=" + modulos + "]";
		}
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ModuloDTO {

        private String id;
        private String modulo;
        private int status;
        private List<String> listaCNPJ;
        private SettingsDTO settings;

        // Getters e Setters
        public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getModulo() {
			return modulo;
		}
		public void setModulo(String modulo) {
			this.modulo = modulo;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public List<String> getListaCNPJ() {
			return listaCNPJ;
		}
		public void setListaCNPJ(List<String> listaCNPJ) {
			this.listaCNPJ = listaCNPJ;
		}
		public SettingsDTO getSettings() {
			return settings;
		}
		public void setSettings(SettingsDTO settings) {
			this.settings = settings;
		}
		@Override
		public String toString() {
			return "ModuloDTO [id=" + id + ", modulo=" + modulo + ", status=" + status + ", listaCNPJ=" + listaCNPJ
					+ ", settings=" + settings + "]";
		}
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SettingsDTO {

        private String idModulo;
        private String idSistema;
        private String qtdeUsers;
        private String statusFin;
        private String habilitado;
        private String observacao;

        // Getters e Setters
        public String getIdModulo() {
			return idModulo;
		}
		public void setIdModulo(String idModulo) {
			this.idModulo = idModulo;
		}
		public String getIdSistema() {
			return idSistema;
		}
		public void setIdSistema(String idSistema) {
			this.idSistema = idSistema;
		}
		public String getQtdeUsers() {
			return qtdeUsers;
		}
		public void setQtdeUsers(String qtdeUsers) {
			this.qtdeUsers = qtdeUsers;
		}
		public String getStatusFin() {
			return statusFin;
		}
		public void setStatusFin(String statusFin) {
			this.statusFin = statusFin;
		}
		public String getHabilitado() {
			return habilitado;
		}
		public void setHabilitado(String habilitado) {
			this.habilitado = habilitado;
		}
		public String getObservacao() {
			return observacao;
		}
		public void setObservacao(String observacao) {
			this.observacao = observacao;
		}
		@Override
		public String toString() {
			return "SettingsDTO [idModulo=" + idModulo + ", idSistema=" + idSistema + ", qtdeUsers=" + qtdeUsers
					+ ", statusFin=" + statusFin + ", habilitado=" + habilitado + ", observacao=" + observacao + "]";
		}
    }

	@Override
	public String toString() {
		return "TokenRetornoApiContabilidadeDTO [id=" + id + ", cnpj=" + cnpj + ", validade=" + validade + ", servidor="
				+ servidor + ", assinatura=" + assinatura + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChaveDTO other = (ChaveDTO) obj;
		return Objects.equals(id, other.id);
	}
}