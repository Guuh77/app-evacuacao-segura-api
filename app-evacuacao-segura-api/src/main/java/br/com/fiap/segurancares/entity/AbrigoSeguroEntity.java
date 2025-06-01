package br.com.fiap.segurancares.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "AbrigoSeguro")
public class AbrigoSeguroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_abrigo")
    private Long idAbrigo;

    @Column(name = "nome_abrigo", nullable = false, length = 255)
    private String nomeAbrigo;

    @Lob // Para CLOB
    @Column(name = "endereco_completo", nullable = false)
    private String enderecoCompleto;

    @Column(name = "latitude_abrigo", nullable = false, precision = 10, scale = 8)
    private BigDecimal latitudeAbrigo;

    @Column(name = "longitude_abrigo", nullable = false, precision = 11, scale = 8)
    private BigDecimal longitudeAbrigo;

    @Column(name = "capacidade_maxima_pessoas")
    private Integer capacidadeMaximaPessoas;

    @Column(name = "vagas_disponiveis_atual")
    private Integer vagasDisponiveisAtual;

    @Lob
    @Column(name = "recursos_oferecidos")
    private String recursosOferecidos;

    @Column(name = "contato_responsavel_abrigo", length = 255)
    private String contatoResponsavelAbrigo;

    @Column(name = "telefone_contato_abrigo", length = 20)
    private String telefoneContatoAbrigo;

    @Column(name = "status_operacional", length = 50)
    private String statusOperacional;

    @Column(name = "data_abertura", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime dataAbertura;

    @Lob
    @Column(name = "observacoes_adicionais")
    private String observacoesAdicionais;

    // Construtores
    public AbrigoSeguroEntity() {
    }

    // Getters e Setters
    public Long getIdAbrigo() {
        return idAbrigo;
    }

    public void setIdAbrigo(Long idAbrigo) {
        this.idAbrigo = idAbrigo;
    }

    public String getNomeAbrigo() {
        return nomeAbrigo;
    }

    public void setNomeAbrigo(String nomeAbrigo) {
        this.nomeAbrigo = nomeAbrigo;
    }

    public String getEnderecoCompleto() {
        return enderecoCompleto;
    }

    public void setEnderecoCompleto(String enderecoCompleto) {
        this.enderecoCompleto = enderecoCompleto;
    }

    public BigDecimal getLatitudeAbrigo() {
        return latitudeAbrigo;
    }

    public void setLatitudeAbrigo(BigDecimal latitudeAbrigo) {
        this.latitudeAbrigo = latitudeAbrigo;
    }

    public BigDecimal getLongitudeAbrigo() {
        return longitudeAbrigo;
    }

    public void setLongitudeAbrigo(BigDecimal longitudeAbrigo) {
        this.longitudeAbrigo = longitudeAbrigo;
    }

    public Integer getCapacidadeMaximaPessoas() {
        return capacidadeMaximaPessoas;
    }

    public void setCapacidadeMaximaPessoas(Integer capacidadeMaximaPessoas) {
        this.capacidadeMaximaPessoas = capacidadeMaximaPessoas;
    }

    public Integer getVagasDisponiveisAtual() {
        return vagasDisponiveisAtual;
    }

    public void setVagasDisponiveisAtual(Integer vagasDisponiveisAtual) {
        this.vagasDisponiveisAtual = vagasDisponiveisAtual;
    }

    public String getRecursosOferecidos() {
        return recursosOferecidos;
    }

    public void setRecursosOferecidos(String recursosOferecidos) {
        this.recursosOferecidos = recursosOferecidos;
    }

    public String getContatoResponsavelAbrigo() {
        return contatoResponsavelAbrigo;
    }

    public void setContatoResponsavelAbrigo(String contatoResponsavelAbrigo) {
        this.contatoResponsavelAbrigo = contatoResponsavelAbrigo;
    }

    public String getTelefoneContatoAbrigo() {
        return telefoneContatoAbrigo;
    }

    public void setTelefoneContatoAbrigo(String telefoneContatoAbrigo) {
        this.telefoneContatoAbrigo = telefoneContatoAbrigo;
    }

    public String getStatusOperacional() {
        return statusOperacional;
    }

    public void setStatusOperacional(String statusOperacional) {
        this.statusOperacional = statusOperacional;
    }

    public OffsetDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(OffsetDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getObservacoesAdicionais() {
        return observacoesAdicionais;
    }

    public void setObservacoesAdicionais(String observacoesAdicionais) {
        this.observacoesAdicionais = observacoesAdicionais;
    }

    @PrePersist
    protected void onPersist() {
        if (this.statusOperacional == null || this.statusOperacional.isEmpty()) {
            this.statusOperacional = "aberto";
        }
    }
}