package br.com.fiap.segurancares.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Campanha")
public class CampanhaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_campanha")
    private Long idCampanha;

    @Column(name = "nome_campanha", nullable = false, length = 255)
    private String nomeCampanha;

    @Lob // Para CLOB
    @Column(name = "descricao_campanha")
    private String descricaoCampanha;

    @Column(name = "tipo_campanha", nullable = false, length = 100)
    private String tipoCampanha;

    @Column(name = "data_inicio_campanha", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate dataInicioCampanha;

    @Column(name = "data_fim_campanha")
    @Temporal(TemporalType.DATE)
    private LocalDate dataFimCampanha;

    @Column(name = "publico_alvo", length = 255)
    private String publicoAlvo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_organizador_usuario")
    private UsuarioEntity organizadorUsuario;

    @Column(name = "entidade_organizadora", length = 255)
    private String entidadeOrganizadora;

    @Column(name = "status_campanha", length = 50)
    private String statusCampanha;

    @Lob
    @Column(name = "meta_campanha")
    private String metaCampanha;

    @Lob
    @Column(name = "local_principal_campanha")
    private String localPrincipalCampanha;

    // Construtores
    public CampanhaEntity() {
    }

    // Getters e Setters
    public Long getIdCampanha() {
        return idCampanha;
    }

    public void setIdCampanha(Long idCampanha) {
        this.idCampanha = idCampanha;
    }

    public String getNomeCampanha() {
        return nomeCampanha;
    }

    public void setNomeCampanha(String nomeCampanha) {
        this.nomeCampanha = nomeCampanha;
    }

    public String getDescricaoCampanha() {
        return descricaoCampanha;
    }

    public void setDescricaoCampanha(String descricaoCampanha) {
        this.descricaoCampanha = descricaoCampanha;
    }

    public String getTipoCampanha() {
        return tipoCampanha;
    }

    public void setTipoCampanha(String tipoCampanha) {
        this.tipoCampanha = tipoCampanha;
    }

    public LocalDate getDataInicioCampanha() {
        return dataInicioCampanha;
    }

    public void setDataInicioCampanha(LocalDate dataInicioCampanha) {
        this.dataInicioCampanha = dataInicioCampanha;
    }

    public LocalDate getDataFimCampanha() {
        return dataFimCampanha;
    }

    public void setDataFimCampanha(LocalDate dataFimCampanha) {
        this.dataFimCampanha = dataFimCampanha;
    }

    public String getPublicoAlvo() {
        return publicoAlvo;
    }

    public void setPublicoAlvo(String publicoAlvo) {
        this.publicoAlvo = publicoAlvo;
    }

    public UsuarioEntity getOrganizadorUsuario() {
        return organizadorUsuario;
    }

    public void setOrganizadorUsuario(UsuarioEntity organizadorUsuario) {
        this.organizadorUsuario = organizadorUsuario;
    }

    public String getEntidadeOrganizadora() {
        return entidadeOrganizadora;
    }

    public void setEntidadeOrganizadora(String entidadeOrganizadora) {
        this.entidadeOrganizadora = entidadeOrganizadora;
    }

    public String getStatusCampanha() {
        return statusCampanha;
    }

    public void setStatusCampanha(String statusCampanha) {
        this.statusCampanha = statusCampanha;
    }

    public String getMetaCampanha() {
        return metaCampanha;
    }

    public void setMetaCampanha(String metaCampanha) {
        this.metaCampanha = metaCampanha;
    }

    public String getLocalPrincipalCampanha() {
        return localPrincipalCampanha;
    }

    public void setLocalPrincipalCampanha(String localPrincipalCampanha) {
        this.localPrincipalCampanha = localPrincipalCampanha;
    }

    @PrePersist
    protected void onPersist() {
        if (this.statusCampanha == null || this.statusCampanha.isEmpty()) {
            this.statusCampanha = "planejamento";
        }
    }
}