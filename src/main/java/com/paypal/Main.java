package com.paypal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.w3c.dom.Element;

import com.paypal.dao.entities.ArquivoDiarioDaoBean;
import com.paypal.dao.entities.ArquivoDiarioLojaDaoBean;
import com.paypal.dao.entities.ArquivoDiarioLojaLogDaoBean;
import com.paypal.dao.entities.ArquivoFisicoDaoBean;
import com.paypal.dao.entities.ArquivoLojaDaoBean;
import com.paypal.dao.entities.CodigoOrigemDaoBean;
import com.paypal.dao.entities.CodigosDTO;
import com.paypal.dao.entities.ConfiguracaoPaypalDaoBean;
import com.paypal.dao.entities.RegistroArquivoClobDaoBean;
import com.paypal.dao.entities.RegistroArquivoDaoBean;
import com.paypal.dao.generico.GerenciadorConexao;
import com.paypal.entities.entity.ArquivoDiario;
import com.paypal.entities.entity.ArquivoDiarioLoja;
import com.paypal.entities.entity.ArquivoDiarioLojaLog;
import com.paypal.entities.entity.ArquivoFisico;
import com.paypal.entities.entity.ArquivoLoja;
import com.paypal.entities.entity.RegistroArquivo;
import com.paypal.entities.entity.RegistroArquivoClob;
import com.paypal.entities.entity.TokenPaypal;
import com.paypal.entities.enuns.StatusArquivoEnum;
import com.paypal.entities.enuns.StatusProcessamentoEnum;
import com.paypal.impl.GerenciadorLayouts;
import com.paypal.util.LayoutException;
import com.paypal.util.MetaException;
import com.paypal.util.SaveLogTime;
import com.paypal.util.SaveLogStatus;
import com.paypal.util.DateUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.Base64;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.google.gson.Gson;

public class Main implements Serializable {

	private static final long serialVersionUID = -6540527454144644283L;

	private static final String DEFAULT_BASE_URL = "https://api.paypal.com";

	private static ArquivoFisico arquivoFisico;
	private static ArquivoDiario arquivoDiario;
	private static RegistroArquivo registroArquivo;
	private static RegistroArquivoClob registroArquivoClob;
	private static ArquivoLoja arqLoja;
	private static ArquivoDiarioLoja arquivoDiarioLoja;
	private static ArquivoDiarioLojaLog arquivoDiarioLojaLog;
	
	private static CodigoOrigemDaoBean dao = new CodigoOrigemDaoBean();
	private static ArquivoFisicoDaoBean arquivoFisicoDao = new ArquivoFisicoDaoBean();
	private static ArquivoDiarioDaoBean arquivoDiarioDao = new ArquivoDiarioDaoBean();
	private static RegistroArquivoDaoBean registroArquivoDao = new RegistroArquivoDaoBean();
	private static RegistroArquivoClobDaoBean registroArquivoClobDao = new RegistroArquivoClobDaoBean();
	private static ArquivoLojaDaoBean arquivoLojaDao = new ArquivoLojaDaoBean();
	private static ArquivoDiarioLojaDaoBean arquivoDiarioLojaDao = new ArquivoDiarioLojaDaoBean();
	private static ArquivoDiarioLojaLogDaoBean arquivoDiarioLojaLogDao = new ArquivoDiarioLojaLogDaoBean();
	private static ArrayList<SaveLogTime> saveLogTime = new ArrayList<SaveLogTime>();
	private static ArrayList<SaveLogStatus> saveLogStatus = new ArrayList<SaveLogStatus>();
	private static DateUtil dateUtil = new DateUtil();
	
	private static GerenciadorLayouts layouts;
	private static Integer linha = 0;
	private static String descrErro;
	private static String dataMovimento;
	private static String dataHoraInicio;
	private static String dataHoraFim;
	private static String periodo;
	private static String log = "";
	private static String nomeArquivo;
	
	final static Logger logger = Logger.getLogger(Main.class);
	
	public static void main(String[] args) throws Exception {
		String codigoOrigem = null;
		Integer codEmpresa = null;
		Integer idLoja = null;
		Integer termoAceite = null;
		boolean help = false;
		
		//Pega os argumentos passados pela linha de comando
		for (int x = 0; x < (args.length - 1); x++) {
			if(args[x].equals("-d")) 
				dataMovimento = args[x+1];
			
			if(args[x].equals("-p")) 
				periodo = args[x+1];
			
			if(args[x].equals("-e")) 
				codigoOrigem = args[x+1];
			
			if(args[x].equals("-l")) 
				log = args[x+1];
			
			if(args[x].equals("-h")) 
				help = true;
		}
		
		if(args.length != 0 && (args[0].equals("-help") || args[0].equals("/?") || help)) {
			System.out.println("Bem-vindo ao Pré-processador Paypal da ShellBox!\n");
			System.out.println("Para rodar o serviço, digite o seguinte comando:");
			System.out.println("java -jar -Dpre_processador_path=C:\\sce\\preprocessamento\\Config paypal.jar\n");
			System.out.println("Abaixo segue os parâmetros válidos:");
			System.out.println("-d 'data movimento (dd/mm/yyyy)'");
			System.out.println("-p 'data movimento por período (dd/mm/yyyy-dd/mm/yyyy)'");
			System.out.println("-e 'estabelecimento (codigoOrigem)'");
			System.out.println("-l 'mostra msg do log (habilita ou desabilita)'");
			System.exit(0);
		}
		
		//Registra o status de início no log
		logStatus(dateUtil.dataHoraInicio(), null, "0");
		
		try {
			layouts = new GerenciadorLayouts("PP");
		} catch (Exception e) {
			String msgErro = (descrErro != null) ? descrErro : "Erro: "+e.getMessage();
			
			//Registra o status de erro no log
			logStatus(dateUtil.dataHoraInicio(), nomeArquivo, "3 - "+msgErro);
		} 
		
		Date dt1;
		Date dt2;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if(dataMovimento == null && periodo != null) {
			String[] p = periodo.split("-");
	        dt1 = dateUtil.stringToDate(dateUtil.dataFormatada(p[0]));
	        dt2 = dateUtil.stringToDate(dateUtil.dataFormatada(p[1]));
		} else {
			dt1 = dateUtil.stringToDate(dateUtil.dataFormatada(dataMovimento));
			dt2 = dateUtil.stringToDate(dateUtil.dataFormatada(dataMovimento));
		}
		Calendar cal = Calendar.getInstance();
        cal.setTime (dt1);
		
        List<CodigosDTO> listaCodigos = dao.findCodigosOrigem();
        
        for (Date dt = dt1; dt.compareTo(dt2) <= 0; ) {
        	dataMovimento = df.format(dt);
        	
        	if(codigoOrigem == null) {
        		
				for (CodigosDTO codigoDTO : listaCodigos) {
					codigoOrigem = codigoDTO.getCodigoOrigem();
					codEmpresa = codigoDTO.getEmpresa();
					idLoja = codigoDTO.getIdLoja();
					termoAceite = codigoDTO.getTermoAceite();
					
					salvarDadosAPI(codigoOrigem, codEmpresa, idLoja, termoAceite);
				}
				codigoOrigem = null;
        	} else {
				CodigosDTO estabelecimento = dao.findEstabelecimento(codigoOrigem);
				idLoja = estabelecimento.getIdLoja();
				codEmpresa = estabelecimento.getEmpresa();
				termoAceite = estabelecimento.getTermoAceite();
				
				if(idLoja == null || idLoja == 0 || codEmpresa == null || codEmpresa == 0) {
					System.out.println("Nenhum dado foi pré-processado.\nPor favor, verifique se a data informada não é maior que a data atual.\n");
					System.exit(0);
				}
				
				salvarDadosAPI(codigoOrigem, codEmpresa, idLoja, termoAceite);
			}
        	
        	cal.add(Calendar.DATE, +1);
        	dt = cal.getTime();
		}
		
		//Habilita ou desabilita a geração do arquivo de log
		if(log.equals("habilita")) 
			System.out.println("Gerando arquivo de log... ("+dateUtil.dataHoraAtual()+")");
		
		//Salva o log de tempo de execução no arquivo csv
		SaveLogTime slt = new SaveLogTime();
		slt.salvarLogCSV(dataMovimento, saveLogTime);
		
		//Salva o log de status no arquivo log
		SaveLogStatus sls = new SaveLogStatus();
		sls.salvarLog(saveLogStatus);
		
		GerenciadorConexao.close();
		System.out.println("Pré-processamento concluído com sucesso!");
	}

	public static void salvarDadosAPI(String codigoOrigem, Integer codEmpresa, Integer idLoja, Integer termoAceite) throws UnsupportedEncodingException, JSONException, ParseException {
		nomeArquivo = "PP_" + codEmpresa + "_" + idLoja + "_" + dataMovimento;
		
		dataHoraInicio = dateUtil.dataHoraAtual();
		// Inicia a chamada dos dados do Paypal via API
		JSONObject dados = getDadosPaypal(1, codigoOrigem, dataMovimento);
		
		//Registra o tempo da api no log
		dataHoraFim = dateUtil.dataHoraAtual();		
		logTime("API", codEmpresa.toString(), "Consulta API", dataHoraInicio, dataHoraFim);
		
		Integer totalPages = 0;
		if(descrErro == null) 
			totalPages = dados.getInt("total_pages");

		arquivoDiario = new ArquivoDiario();
		arquivoDiario.setDataInicioPreProc(new Date());

		Integer status = null; 
		if (totalPages > 0) 
			status = StatusArquivoEnum.ARQUIVO_OK.getChave();
		else if (descrErro != null) 
			status = StatusArquivoEnum.ARQ_ERRO.getChave();
		else
			status = StatusArquivoEnum.ARQ_VAZIO.getChave();
		
		Date dataMov = dateUtil.stringToDate(dataMovimento);
		Date dataAtual = dateUtil.stringToDate(dateUtil.dataAtual());
		
		if(dataMov.compareTo(dataAtual) < 0) {
			//Registra o status de começo do pré-processamento no log
			logStatus(dateUtil.dataHoraInicio(), nomeArquivo, "1");
			
			GerenciadorConexao.getEntityManager().clear();
			GerenciadorConexao.beginTransaction();
			Boolean pesqArqFisico = pesquisarArquivoFisico(codEmpresa, idLoja, dateUtil.stringToDate(dataMovimento), nomeArquivo, status);
			
			if(pesqArqFisico) {
				atualizaDadosArquivosDiarios(status, termoAceite);
				pesquisarArquivoLoja(idLoja);
				
				try {
					//Pré-processa as transações
					linha = 1;
	
					if (totalPages > 0) {
						// Faz o loop nos dados de acordo com o número de páginas
						for (int contPage = 1; contPage <= totalPages; contPage++) {
							JSONArray transaction;
							if (contPage == 1) {
								transaction = dados.getJSONArray("transaction_details");
							} else {
								JSONObject dadosPaypal = getDadosPaypal(contPage, codigoOrigem, dataMovimento);
								transaction = dadosPaypal.getJSONArray("transaction_details");
							}
							
							for (int i = 0; i < transaction.length(); i++) {
								// Atualiza os dados da chamada
								atualizaDadosRegistroArquivoClob(transaction.getJSONObject(i).toString());
								atualizaDadosRegistroArquivo(codigoOrigem, linha);
								
								//Habilita ou desabilita a lista de lojas pré-processadas
								if(log.equals("habilita")) 
									System.out.println("Api - codEmpresa: "+codEmpresa+" idLoja: "+idLoja+" dataMov: "+dataMovimento+" transação: "+linha+" ("+dateUtil.dataHoraAtual()+")");
								
								linha++;
							}
						}
					} else {
						System.out.println("Nenhum dado foi pré-processado para o codEmpresa: "+codEmpresa+" e idLoja: "+idLoja+".\nPor favor, verifique os parâmetros enviados se estão corretos.\n");
					}
				} catch (Exception e) {
					String msgErro = (descrErro != null) ? descrErro : "Erro: "+e.getMessage();
					
					//Registra o status de erro no log
					logStatus(dateUtil.dataHoraInicio(), nomeArquivo, "3 - "+msgErro);
					
					//Salva o erro no banco
					atualizaDadosArquivoDiarioLojaLog(msgErro);
				}
				
				//Registra o tempo do banco no log
				dataHoraFim = dateUtil.dataHoraAtual();		
				logTime("API", codEmpresa.toString(), "Gravação dos dados no Banco", dataHoraInicio, dataHoraFim);
				
				atualizaDadosArquivoDiarioLoja(status, termoAceite);
				
				//Caso tenha alguma mensagem de erro, salva na tabela de log
				if(descrErro != null)
					atualizaDadosArquivoDiarioLojaLog(descrErro);
				
				GerenciadorConexao.commitTransaction();
				
				//Registra o status de sucesso no log
				logStatus(dateUtil.dataHoraInicio(), nomeArquivo, "2");
			}
		} else {
			System.out.println("Nenhum dado foi pré-processado.\nPor favor, verifique se a data informada não é maior que a data atual.\n");
			System.exit(0);
		}
	}
	
	public static String getTokenPaypal(String codigoOrigem) throws UnsupportedEncodingException {
		String url = DEFAULT_BASE_URL + "/v1/oauth2/token";
		
		ConfiguracaoPaypalDaoBean cp = new ConfiguracaoPaypalDaoBean();
		
		String CLIENTID = cp.findClientID();
		String SECRETID = cp.findSecretID();
		
		String base64ClientID = generateBase64String(CLIENTID + ":" + SECRETID);
		
		Client client = Client.create();
		WebResource webResource = client.resource(url);
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		queryParams.add("grant_type", "client_credentials");
		queryParams.add("target_subject", codigoOrigem);
		ClientResponse response = webResource.accept("application/json")
				.header("Authorization", "Basic " + base64ClientID).type("application/x-www-form-urlencoded")
				.post(ClientResponse.class, queryParams);
		
		if(response.getStatus() != 200) {
			descrErro = statusError(response.getStatus());
			
			//Registra o status de erro no log
			logStatus(dateUtil.dataHoraInicio(), nomeArquivo, "3 - Erro ao conectar a API da Paypal para gerar o Token ("+descrErro+")");
		}
		
		String entityBody = response.getEntity(String.class);
		
		Gson gson = new Gson();
		TokenPaypal token = gson.fromJson(entityBody, TokenPaypal.class);
		
		return token.getAccess_token();
	}

	private static String generateBase64String(String clientCredentials) throws UnsupportedEncodingException {
		String base64ClientID = null;
		byte[] encoded = null;

		encoded = Base64.encode(clientCredentials.getBytes("UTF-8"));
		base64ClientID = new String(encoded, "UTF-8");

		return base64ClientID;
	}

	public static JSONObject getDadosPaypal(Integer page, String codigoOrigem, String dataMovimento) throws UnsupportedEncodingException, JSONException {
		//Habilita ou desabilita o log no console
		if(log.equals("habilita")) 
			System.out.println("Consulta na API - "+dateUtil.dataHoraAtual());
		
		// Gera o Token
		String accessToken = getTokenPaypal(codigoOrigem);
		
		// URL
		String url = DEFAULT_BASE_URL + "/v1/reporting/transactions?start_date=" + dataMovimento + "T00:00:00Z&end_date="
				+ dataMovimento + "T23:59:59Z&fields=all&page_size=100&page=" + page;
		
		Client restClient = Client.create();
		WebResource webResource = restClient.resource(url);
		ClientResponse response = webResource.accept("application/json").header("Authorization", "Bearer " + accessToken)
				.get(ClientResponse.class);

		if(response.getStatus() != 200) {
			descrErro = statusError(response.getStatus());
			
			//Registra o status de erro no log
			logStatus(dateUtil.dataHoraInicio(), nomeArquivo, "3 - Erro ao conectar a API da Paypal para trazer os dados ("+descrErro+")");
		}
		
		String entityBody = response.getEntity(String.class);
			
		JSONObject dadosPaypal = new JSONObject(entityBody);
		
		return dadosPaypal;
	}

	private static String statusError(Integer status) {
		if(status == 400)
			descrErro = "Bad Request - Está faltando um valor para o parâmetro de data de referência ou data inválida.";
		else if(status == 401)
			descrErro = "Unauthorized - Valores de autenticação incorretos.";
		else if(status == 500)
			descrErro = "Internal Server Error - servidor encontrou uma situação que não soube como manipular a informação.";
		else if(status == 503)
			descrErro = "Service Unavailable - Atualmente, o serviço não está disponível para atualizar seus dados, tente novamente mais tarde.";
		
		return descrErro;
	}
	
	private static void logTime(String tipoChamada, String codEmpresa, String descricao, String dataHoraInicio, String dataHoraFim) {
		SaveLogTime sl = new SaveLogTime();
		sl.setTipoChamada(tipoChamada);
		sl.setCodEmpresa(codEmpresa);
		sl.setDescricao(descricao);
		sl.setDataHoraInicio(dataHoraInicio);
		sl.setDataHoraFim(dataHoraFim);
		saveLogTime.add(sl);
	}
	
	private static void logStatus(String dataHoraInicio, String arquivo, String status) {
		SaveLogStatus sl = new SaveLogStatus();
		sl.setDataHoraInicio(dataHoraInicio);
		sl.setArquivo(arquivo);
		sl.setStatus(status);
		saveLogStatus.add(sl);
	}
	
	private static void atualizaDadosArquivoDiarioLoja(Integer status, Integer termoAceite) {
		arquivoDiarioLoja = new ArquivoDiarioLoja();
		arquivoDiarioLoja.setArquivoDiario(arquivoDiario);
		arquivoDiarioLoja.setArquivoLoja(arqLoja);
		if(termoAceite == null || termoAceite == 0){
			arquivoDiarioLoja.setStatusProc(StatusProcessamentoEnum.AGUARDANDO_AUTORIZACAO.getChave());
		} else {
			if(status == StatusArquivoEnum.ARQ_VAZIO.getChave() || linha == 1) {
				arquivoDiarioLoja.setStatusProc(StatusProcessamentoEnum.PROCESSADO_OK.getChave());
			} if(status == StatusArquivoEnum.ARQ_ERRO.getChave()) {
				arquivoDiarioLoja.setStatusProc(StatusProcessamentoEnum.ERRO_PRE_PROC.getChave());
			} else {
				arquivoDiarioLoja.setStatusProc(StatusProcessamentoEnum.AGUARDANDO_PROCESSAMENTO.getChave());
			}
		}
		
		arquivoDiarioLoja.setQtsRegPro(null);
		arquivoDiarioLoja.setQtsRegDesc(null);
		try {
			arquivoDiarioLoja = arquivoDiarioLojaDao.saveOrUpdate(arquivoDiarioLoja);
		} catch (Exception y) {
			y.printStackTrace();
		}
	}
	
	private static void atualizaDadosArquivoDiarioLojaLog(String descrErro) {
		arquivoDiarioLojaLog = new ArquivoDiarioLojaLog();
		arquivoDiarioLojaLog.setArquivoDiarioLoja(arquivoDiarioLoja);
		arquivoDiarioLojaLog.setArquivoDiario(arquivoDiario);
		arquivoDiarioLojaLog.setDataLog(new Date());
		arquivoDiarioLojaLog.setTxtDescricaoLog(descrErro);
		arquivoDiarioLojaLog.setEnviouEmail(false);
		try {
			arquivoDiarioLojaLog = arquivoDiarioLojaLogDao.saveOrUpdate(arquivoDiarioLojaLog);
		} catch (Exception y) {
			y.printStackTrace();
		}
	}

	private static void pesquisarArquivoLoja(Integer idLojaAtual) {
		try {
			arqLoja = arquivoLojaDao.findArquivoLoja(idLojaAtual, layouts.getIdOrigemArquivo());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (arqLoja == null) {
			gerarArquivoLoja(idLojaAtual);
		}
	}

	private static void gerarArquivoLoja(Integer idLoja) {
		arqLoja = new ArquivoLoja();
		arqLoja.setIdAdquirente(layouts.getIdAdquirente());
		arqLoja.setIdLoja(idLoja);
		arqLoja.setIdOriArq(layouts.layoutAtual().getOrigemArquivoArq().getOrigemArquivo().getId());
		arqLoja.setAgrupado(false);
		arqLoja.setIdLojaAgrupadora(idLoja);
				
		try {
			arqLoja = arquivoLojaDao.saveOrUpdate(arqLoja);
		} catch (Exception y) {
			y.printStackTrace();
		}

	}

	private static void atualizaDadosRegistroArquivoClob(String conteudoLinha) {
		registroArquivoClob = new RegistroArquivoClob();
		registroArquivoClob.setConteudoLinhaClob(conteudoLinha);
		registroArquivoClob.setTipoRegistro(3);
		try {
			registroArquivoClob = registroArquivoClobDao.saveOrUpdate(registroArquivoClob);
		} catch (Exception y) {
			y.printStackTrace();
		}
	}

	private static void atualizaDadosRegistroArquivo(String codigoOrigem, Integer numeroLinha) {
		registroArquivo = new RegistroArquivo();
		registroArquivo.setArquivoFisico(arquivoFisico);
		registroArquivo.setCodigoOrigem(codigoOrigem);
		registroArquivo.setRegistroArquivoClob(registroArquivoClob);
		registroArquivo.setConteudoLinha(numeroLinha.toString());
		registroArquivo.setNumeroLinha(numeroLinha);
		registroArquivo.setTipoRegistro("RE");
		registroArquivo.setIdStatusRegistro(1);
		registroArquivo.setReprocessar(false);
		registroArquivo.setArquivoLoja(arqLoja);
		try {
			registroArquivo = registroArquivoDao.saveOrUpdate(registroArquivo);
		} catch (Exception y) {
			y.printStackTrace();
		}
	}

	private static void atualizaDadosArquivosDiarios(Integer status , Integer termoAceite) {
		arquivoDiario.setArquivoFisico(arquivoFisico);
		arquivoDiario.setDataFimPreProc(new Date());
		arquivoDiario.setNumSeqArquivo(1);
		if(termoAceite == null || termoAceite == 0){
			arquivoDiario.setStatusProc(StatusProcessamentoEnum.AGUARDANDO_AUTORIZACAO.getChave());
		} else {
			if(status == StatusArquivoEnum.ARQ_VAZIO.getChave() || linha == 1) {
				arquivoDiario.setStatusProc(StatusProcessamentoEnum.PROCESSADO_OK.getChave());
			} if(status == StatusArquivoEnum.ARQ_ERRO.getChave()) {
				arquivoDiario.setStatusProc(StatusProcessamentoEnum.ERRO_PRE_PROC.getChave());
			} else {
				arquivoDiario.setStatusProc(StatusProcessamentoEnum.AGUARDANDO_PROCESSAMENTO.getChave());
			}
		}
		
		arquivoDiario.setIdOriArqArq(layouts.layoutAtual().getOrigemArquivoArq().getId());
		arquivoDiario.setQtsRegPro(null);
		arquivoDiario.setQtsRegDesc(null);
		try {
			arquivoDiario = arquivoDiarioDao.saveOrUpdate(arquivoDiario);
		} catch (Exception y) {
			y.printStackTrace();
		}
	}

	private static Boolean pesquisarArquivoFisico(Integer codEmpresa, Integer idLoja, Date dataMovTran, String nomeArquivo, Integer status) throws ParseException {
		try {
			arquivoFisico = arquivoFisicoDao.existeArquivoFisico(nomeArquivo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (arquivoFisico == null) {
			atualizaDadosArquivoFisico(codEmpresa, idLoja, dataMovTran, nomeArquivo, status);
			return true;
		} else {
			System.out.println("Arquivo Físico já existente para a data: "+dataMovimento+"\nPor favor, tente pré-processar outra data.\n");
			return false;
		}
	}
	
	private static void atualizaDadosArquivoFisico(Integer codEmpresa, Integer idLoja, Date dataMovTran, String nomeArquivo, Integer status) throws ParseException {
		arquivoFisico = new ArquivoFisico();
		arquivoFisico.setDataChegada(new Date());
		arquivoFisico.setDataMovimento(dataMovTran);
		arquivoFisico.setDataTransacao(dataMovTran);
		arquivoFisico.setDataInclusao(new Date());
		arquivoFisico.setNomeArquivo(nomeArquivo);
		arquivoFisico.setStatusArquivo(status);
		arquivoFisico.setTamanho(10000L);
		arquivoFisico.setIdOrigemArquivo(layouts.getIdOrigemArquivo());
		try {
			arquivoFisico = arquivoFisicoDao.saveOrUpdate(arquivoFisico);
		} catch (Exception y) {
			y.printStackTrace();
		}
	}
}