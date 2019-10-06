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
@Table(name = "leituras")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Leitura extends EntidadeBase{
	
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
	@JoinColumn(name = "sensor")
	private Sensor sensor;
	  
	public Leitura() {
		super();
	}

	public Leitura(@Min(0) @Max(100) int umidadeAtual, LocalDateTime data, Sensor sensor) {
		super();
		this.umidadeAtual = umidadeAtual;
		this.data = data;
		this.sensor = sensor;
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

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Leitura [umidadeAtual=" + umidadeAtual + ", data=" + data + ", sensor=" + sensor + ", isNew()="
				+ isNew() + "]";
	}
		    
}
