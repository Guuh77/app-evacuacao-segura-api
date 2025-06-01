package br.com.fiap.segurancares.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "Relato")
public class RelatoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_relato")
    private Long idRelato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_autor", nullable = false)
    private UsuarioEntity usuarioAutor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ocorrencia_associada")
    private OcorrenciaEntity ocorrenciaAssociada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alerta_associado")
    private AlertaEntity alertaAssociado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_area_risco_associada")
    private AreaDeRiscoEntity areaRiscoAssociada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_abrigo_associado")
    private AbrigoSeguroEntity abrigoAssociado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_campanha_associada")
    private CampanhaEntity campanhaAssociada;

    @Column(name = "titulo_relato", length = 255)
    private String tituloRelato;

    @Lob
    @Column(name = "texto_relato", nullable = false)
    private String textoRelato;

    @Column(name = "data_hora_relato", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime dataHoraRelato;

    @Column(name = "latitude_relato", precision = 10, scale = 8)
    private BigDecimal latitudeRelato;

    @Column(name = "longitude_relato", precision = 11, scale = 8)
    private BigDecimal longitudeRelato;

    @Column(name = "tipo_relato", length = 100)
    private String tipoRelato;

    @Column(name = "status_validacao_relato", length = 50)
    private String statusValidacaoRelato;

    @Column(name = "anonimo", columnDefinition = "NUMBER(1) DEFAULT 0")
    private boolean anonimo;

    public RelatoEntity() {
    }

    public Long getIdRelato() {
        return idRelato;
    }

    public void setIdRelato(Long idRelato) {
        this.idRelato = idRelato;
    }

    public UsuarioEntity getUsuarioAutor() {
        return usuarioAutor;
    }

    public void setUsuarioAutor(UsuarioEntity usuarioAutor) {
        this.usuarioAutor = usuarioAutor;
    }

    public OcorrenciaEntity getOcorrenciaAssociada() {
        return ocorrenciaAssociada;
    }

    public void setOcorrenciaAssociada(OcorrenciaEntity ocorrenciaAssociada) {
        this.ocorrenciaAssociada = ocorrenciaAssociada;
    }

    public AlertaEntity getAlertaAssociado() {
        return alertaAssociado;
    }

    public void setAlertaAssociado(AlertaEntity alertaAssociado) {
        this.alertaAssociado = alertaAssociado;
    }

    public AreaDeRiscoEntity getAreaRiscoAssociada() {
        return areaRiscoAssociada;
    }

    public void setAreaRiscoAssociada(AreaDeRiscoEntity areaRiscoAssociada) {
        this.areaRiscoAssociada = areaRiscoAssociada;
    }

    public AbrigoSeguroEntity getAbrigoAssociado() {
        return abrigoAssociado;
    }

    public void setAbrigoAssociado(AbrigoSeguroEntity abrigoAssociado) {
        this.abrigoAssociado = abrigoAssociado;
    }

    public CampanhaEntity getCampanhaAssociada() {
        return campanhaAssociada;
    }

    public void setCampanhaAssociada(CampanhaEntity campanhaAssociada) {
        this.campanhaAssociada = campanhaAssociada;
    }

    public String getTituloRelato() {
        return tituloRelato;
    }

    public void setTituloRelato(String tituloRelato) {
        this.tituloRelato = tituloRelato;
    }

    public String getTextoRelato() {
        return textoRelato;
    }

    public void setTextoRelato(String textoRelato) {
        this.textoRelato = textoRelato;
    }

    public OffsetDateTime getDataHoraRelato() {
        return dataHoraRelato;
    }

    public void setDataHoraRelato(OffsetDateTime dataHoraRelato) {
        this.dataHoraRelato = dataHoraRelato;
    }

    public BigDecimal getLatitudeRelato() {
        return latitudeRelato;
    }

    public void setLatitudeRelato(BigDecimal latitudeRelato) {
        this.latitudeRelato = latitudeRelato;
    }

    public BigDecimal getLongitudeRelato() {
        return longitudeRelato;
    }

    public void setLongitudeRelato(BigDecimal longitudeRelato) {
        this.longitudeRelato = longitudeRelato;
    }

    public String getTipoRelato() {
        return tipoRelato;
    }

    public void setTipoRelato(String tipoRelato) {
        this.tipoRelato = tipoRelato;
    }

    public String getStatusValidacaoRelato() {
        return statusValidacaoRelato;
    }

    public void setStatusValidacaoRelato(String statusValidacaoRelato) {
        this.statusValidacaoRelato = statusValidacaoRelato;
    }

    public boolean isAnonimo() {
        return anonimo;
    }

    public void setAnonimo(boolean anonimo) {
        this.anonimo = anonimo;
    }

    @PrePersist
    protected void onPersist() {
        if (this.dataHoraRelato == null) {
            this.dataHoraRelato = OffsetDateTime.now();
        }
        if (this.statusValidacaoRelato == null || this.statusValidacaoRelato.isEmpty()) {
            this.statusValidacaoRelato = "pendente"; // Valor padrão
        }
    }
}