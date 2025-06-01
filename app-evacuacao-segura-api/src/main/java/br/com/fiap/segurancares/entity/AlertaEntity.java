package br.com.fiap.segurancares.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "Alerta")
public class AlertaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alerta")
    private Long idAlerta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_area_risco_associada")
    private AreaDeRiscoEntity areaDeRiscoAssociada;

    @Column(name = "titulo", nullable = false, length = 255)
    private String titulo;

    @Lob
    @Column(name = "descricao_completa", nullable = false)
    private String descricaoCompleta;

    @Column(name = "tipo_evento_alerta", nullable = false, length = 100)
    private String tipoEventoAlerta;

    @Column(name = "nivel_severidade_alerta", nullable = false, length = 50)
    private String nivelSeveridadeAlerta;

    @Column(name = "data_hora_emissao", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime dataHoraEmissao;

    @Column(name = "data_hora_expiracao", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime dataHoraExpiracao;

    @Lob
    @Column(name = "instrucoes_seguranca")
    private String instrucoesSeguranca;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emitido_por_usuario_id")
    private UsuarioEntity emitidoPorUsuario; // Referência à entidade Usuario

    // Construtores
    public AlertaEntity() {
    }

    // Getters e Setters
    public Long getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(Long idAlerta) {
        this.idAlerta = idAlerta;
    }

    public AreaDeRiscoEntity getAreaDeRiscoAssociada() {
        return areaDeRiscoAssociada;
    }

    public void setAreaDeRiscoAssociada(AreaDeRiscoEntity areaDeRiscoAssociada) {
        this.areaDeRiscoAssociada = areaDeRiscoAssociada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricaoCompleta() {
        return descricaoCompleta;
    }

    public void setDescricaoCompleta(String descricaoCompleta) {
        this.descricaoCompleta = descricaoCompleta;
    }

    public String getTipoEventoAlerta() {
        return tipoEventoAlerta;
    }

    public void setTipoEventoAlerta(String tipoEventoAlerta) {
        this.tipoEventoAlerta = tipoEventoAlerta;
    }

    public String getNivelSeveridadeAlerta() {
        return nivelSeveridadeAlerta;
    }

    public void setNivelSeveridadeAlerta(String nivelSeveridadeAlerta) {
        this.nivelSeveridadeAlerta = nivelSeveridadeAlerta;
    }

    public OffsetDateTime getDataHoraEmissao() {
        return dataHoraEmissao;
    }

    public void setDataHoraEmissao(OffsetDateTime dataHoraEmissao) {
        this.dataHoraEmissao = dataHoraEmissao;
    }

    public OffsetDateTime getDataHoraExpiracao() {
        return dataHoraExpiracao;
    }

    public void setDataHoraExpiracao(OffsetDateTime dataHoraExpiracao) {
        this.dataHoraExpiracao = dataHoraExpiracao;
    }

    public String getInstrucoesSeguranca() {
        return instrucoesSeguranca;
    }

    public void setInstrucoesSeguranca(String instrucoesSeguranca) {
        this.instrucoesSeguranca = instrucoesSeguranca;
    }

    public UsuarioEntity getEmitidoPorUsuario() {
        return emitidoPorUsuario;
    }

    public void setEmitidoPorUsuario(UsuarioEntity emitidoPorUsuario) {
        this.emitidoPorUsuario = emitidoPorUsuario;
    }

    @PrePersist
    protected void onCreate() {
        if (this.dataHoraEmissao == null) {
            this.dataHoraEmissao = OffsetDateTime.now();
        }
    }
}