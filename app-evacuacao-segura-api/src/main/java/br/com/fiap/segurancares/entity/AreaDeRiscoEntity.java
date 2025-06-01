package br.com.fiap.segurancares.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "AreaDeRisco")
public class AreaDeRiscoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area_risco")
    private Long idAreaRisco;

    @Column(name = "nome_area", nullable = false, length = 255)
    private String nomeArea;

    @Lob // Para CLOB
    @Column(name = "descricao_risco")
    private String descricaoRisco;

    @Column(name = "tipo_risco", nullable = false, length = 100)
    private String tipoRisco;

    @Column(name = "latitude_centro", nullable = false, precision = 10, scale = 8)
    private BigDecimal latitudeCentro;

    @Column(name = "longitude_centro", nullable = false, precision = 11, scale = 8)
    private BigDecimal longitudeCentro;

    @Column(name = "raio_km", precision = 5, scale = 2)
    private BigDecimal raioKm;

    @Lob
    @Column(name = "poligono_coordenadas")
    private String poligonoCoordenadas;

    @Column(name = "nivel_risco_permanente", length = 50)
    private String nivelRiscoPermanente;

    @Column(name = "data_identificacao", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime dataIdentificacao;

    // Construtores
    public AreaDeRiscoEntity() {
    }

    // Getters e Setters
    public Long getIdAreaRisco() {
        return idAreaRisco;
    }

    public void setIdAreaRisco(Long idAreaRisco) {
        this.idAreaRisco = idAreaRisco;
    }

    public String getNomeArea() {
        return nomeArea;
    }

    public void setNomeArea(String nomeArea) {
        this.nomeArea = nomeArea;
    }

    public String getDescricaoRisco() {
        return descricaoRisco;
    }

    public void setDescricaoRisco(String descricaoRisco) {
        this.descricaoRisco = descricaoRisco;
    }

    public String getTipoRisco() {
        return tipoRisco;
    }

    public void setTipoRisco(String tipoRisco) {
        this.tipoRisco = tipoRisco;
    }

    public BigDecimal getLatitudeCentro() {
        return latitudeCentro;
    }

    public void setLatitudeCentro(BigDecimal latitudeCentro) {
        this.latitudeCentro = latitudeCentro;
    }

    public BigDecimal getLongitudeCentro() {
        return longitudeCentro;
    }

    public void setLongitudeCentro(BigDecimal longitudeCentro) {
        this.longitudeCentro = longitudeCentro;
    }

    public BigDecimal getRaioKm() {
        return raioKm;
    }

    public void setRaioKm(BigDecimal raioKm) {
        this.raioKm = raioKm;
    }

    public String getPoligonoCoordenadas() {
        return poligonoCoordenadas;
    }

    public void setPoligonoCoordenadas(String poligonoCoordenadas) {
        this.poligonoCoordenadas = poligonoCoordenadas;
    }

    public String getNivelRiscoPermanente() {
        return nivelRiscoPermanente;
    }

    public void setNivelRiscoPermanente(String nivelRiscoPermanente) {
        this.nivelRiscoPermanente = nivelRiscoPermanente;
    }

    public OffsetDateTime getDataIdentificacao() {
        return dataIdentificacao;
    }

    public void setDataIdentificacao(OffsetDateTime dataIdentificacao) {
        this.dataIdentificacao = dataIdentificacao;
    }

    @PrePersist
    protected void onCreate() {
        if (this.dataIdentificacao == null) {
            this.dataIdentificacao = OffsetDateTime.now();
        }
    }
}