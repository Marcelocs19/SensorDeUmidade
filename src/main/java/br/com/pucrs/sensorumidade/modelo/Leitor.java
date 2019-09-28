package br.com.pucrs.sensorumidade.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "leitores")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Leitor extends EntidadeBase{
	
	private static final long serialVersionUID = 1L;
	
	@Min(0)
	@Max(100)
	@Column(name = "UMIDADEATUAL", nullable = false)
	private int umidadeAtual;
	
     
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "data")
    private LocalDateTime data = LocalDateTime.now();
    
    @JsonProperty
	@ManyToOne
	@JoinColumn(name = "sensorId")
	private Sensor sensorId;
	  
	public Leitor() {
		super();
	}

	public Leitor(@Min(0) @Max(100) int umidadeAtual, LocalDateTime data, Sensor sensorId) {
		super();
		this.umidadeAtual = umidadeAtual;
		this.data = data;
		this.sensorId = sensorId;
	}

	public String getData() {
		String date = formatadorData();
		return date; 
	}

	private String formatadorData() {
		DateTimeFormatter formatar = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		String date = formatar.format(data).replace('T', ' ');
		date.substring(0, 19);
		return date;
	}

	public int getUmidadeAtual() {
		return umidadeAtual;
	}

	public void setUmidadeAtual(int umidadeAtual) {
		this.umidadeAtual = umidadeAtual;
	}

	public Sensor getSensorId() {
		return sensorId;
	}

	public void setSensorId(Sensor sensorId) {
		this.sensorId = sensorId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Leitor [umidadeAtual=" + umidadeAtual + ", data=" + data + ", sensorId=" + sensorId + ", isNew()="
				+ isNew() + "]";
	}
		    
}
