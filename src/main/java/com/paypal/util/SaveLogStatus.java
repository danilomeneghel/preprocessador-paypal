package com.paypal.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveLogStatus {

	String dataHoraInicio;
	String arquivo;
	String status;

	public void salvarLog(ArrayList<SaveLogStatus> saveLog) {
		String nomeArquivo = "CONTROL-M_PAYPAL.log";

		try {
			File arquivo = new File(nomeArquivo);

			FileWriter writer = new FileWriter(arquivo);

			// Faz o loop nos dados do log e escreve no arquivo
			for (SaveLogStatus log : saveLog) {
				writer.append("Data: ");
				writer.append(log.getDataHoraInicio());
				if (log.getArquivo() != null) {
					writer.append(" - Arquivo: ");
					writer.append(log.getArquivo());
				}
				writer.append(" - Status: ");
				writer.append(log.getStatus());
				writer.append("\n");
			}

			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(String dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
