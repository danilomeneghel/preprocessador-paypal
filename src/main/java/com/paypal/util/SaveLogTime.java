package com.paypal.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveLogTime {

	String tipoChamada;
	String codEmpresa;
	String descricao;
	String dataHoraInicio;
	String dataHoraFim;

	public void salvarLogCSV(String data, ArrayList<SaveLogTime> saveLogTime) {
		String nomeArquivo = "log_" + data + ".csv";

		try {
			File diretorio = new File("logs");
			File arquivo = new File("logs", nomeArquivo);

			if (!diretorio.isDirectory()) {
				diretorio.mkdir();
			}

			FileWriter writer = new FileWriter(arquivo);

			// Cria o cabeçalho
			writer.append("Tipo Chamada");
			writer.append(';');
			writer.append("Cód. Empresa");
			writer.append(';');
			writer.append("Descrição");
			writer.append(';');
			writer.append("Data Hora Início");
			writer.append(';');
			writer.append("Data Hora Fim");
			writer.append(';');
			writer.append('\n');

			// Faz o loop nos dados do log e escreve no arquivo
			for (SaveLogTime log : saveLogTime) {
				writer.append(log.getTipoChamada());
				writer.append(';');
				writer.append(log.getCodEmpresa());
				writer.append(';');
				writer.append(log.getDescricao());
				writer.append(';');
				writer.append(log.getDataHoraInicio());
				writer.append(';');
				writer.append(log.getDataHoraFim());
				writer.append(';');
				writer.append('\n');
			}

			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getTipoChamada() {
		return tipoChamada;
	}

	public void setTipoChamada(String tipoChamada) {
		this.tipoChamada = tipoChamada;
	}

	public String getCodEmpresa() {
		return codEmpresa;
	}

	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(String dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public String getDataHoraFim() {
		return dataHoraFim;
	}

	public void setDataHoraFim(String dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

}
