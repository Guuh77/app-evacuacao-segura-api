package br.com.fiap.segurancares.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "Ocorrencia")
public class OcorrenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ocorrencia")
    private Long idOcorrencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alerta_relacionado")
    private AlertaEntity alertaRelacionado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_area_risco_afetada")
    private AreaDeRiscoEntity areaRiscoAfetada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_reportou")
    private UsuarioEntity usuarioReportou;

    @Column(name = "titulo_ocorrencia", nullable = false, length = 255)
    private String tituloOcorrencia;

    @Lob // Para CLOB
    @Column(name = "descricao_detalhada")
    private String descricaoDetalhada;

    @Column(name = "tipo_ocorrencia", nullable = false, length = 100)
    private String tipoOcorrencia;

    @Column(name = "latitude_ocorrencia", nullable = false, precision = 10, scale = 8)
    private BigDecimal latitudeOcorrencia;

    @Column(name = "longitude_ocorrencia", nullable = false, precision = 11, scale = 8)
    private BigDecimal longitudeOcorrencia;

    @Column(name = "data_hora_ocorrencia", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime dataHoraOcorrencia;

    @Column(name = "status_ocorrencia", length = 50)
    private String statusOcorrencia;

    @Lob
    @Column(name = "impacto_estimado")
    private String impactoEstimado;

    public OcorrenciaEntity() {
    }

    public Long getIdOcorrencia() {
        return idOcorrencia;
    }

    public void setIdOcorrencia(Long idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }

    public AlertaEntity getAlertaRelacionado() {
        return alertaRelacionado;
    }

    public void setAlertaRelacionado(AlertaEntity alertaRelacionado) {
        this.alertaRelacionado = alertaRelacionado;
    }

    public AreaDeRiscoEntity getAreaRiscoAfetada() {
        return areaRiscoAfetada;
    }

    public void setAreaRiscoAfetada(AreaDeRiscoEntity areaRiscoAfetada) {
        this.areaRiscoAfetada = areaRiscoAfetada;
    }

    public UsuarioEntity getUsuarioReportou() {
        return usuarioReportou;
    }

    public void setUsuarioReportou(UsuarioEntity usuarioReportou) {
        this.usuarioReportou = usuarioReportou;
    }

    public String getTituloOcorrencia() {
        return tituloOcorrencia;
    }

    public void setTituloOcorrencia(String tituloOcorrencia) {
        this.tituloOcorrencia = tituloOcorrencia;
    }

    public String getDescricaoDetalhada() {
        return descricaoDetalhada;
    }

    public void setDescricaoDetalhada(String descricaoDetalhada) {
        this.descricaoDetalhada = descricaoDetalhada;
    }

    public String getTipoOcorrencia() {
        return tipoOcorrencia;
    }

    public void setTipoOcorrencia(String tipoOcorrencia) {
        this.tipoOcorrencia = tipoOcorrencia;
    }

    public BigDecimal getLatitudeOcorrencia() {
        return latitudeOcorrencia;
    }

    public void setLatitudeOcorrencia(BigDecimal latitudeOcorrencia) {
        this.latitudeOcorrencia = latitudeOcorrencia;
    }

    public BigDecimal getLongitudeOcorrencia() {
        return longitudeOcorrencia;
    }

    public void setLongitudeOcorrencia(BigDecimal longitudeOcorrencia) {
        this.longitudeOcorrencia = longitudeOcorrencia;
    }

    public OffsetDateTime getDataHoraOcorrencia() {
        return dataHoraOcorrencia;
    }

    public void setDataHoraOcorrencia(OffsetDateTime dataHoraOcorrencia) {
        this.dataHoraOcorrencia = dataHoraOcorrencia;
    }

    public String getStatusOcorrencia() {
        return statusOcorrencia;
    }

    public void setStatusOcorrencia(String statusOcorrencia) {
        this.statusOcorrencia = statusOcorrencia;
    }

    public String getImpactoEstimado() {
        return impactoEstimado;
    }

    public void setImpactoEstimado(String impactoEstimado) {
        this.impactoEstimado = impactoEstimado;
    }

    @PrePersist
    protected void onPersist() {
        if (this.dataHoraOcorrencia == null) {
            this.dataHoraOcorrencia = OffsetDateTime.now();
        }
        if (this.statusOcorrencia == null || this.statusOcorrencia.isEmpty()) {
            this.statusOcorrencia = "ativa";
        }
    }
}