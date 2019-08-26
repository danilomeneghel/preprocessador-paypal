package com.paypal.impl;

import java.util.List;
import java.util.regex.Pattern;

import com.paypal.dao.entities.LayoutOrigemArquivoDaoBean;
import com.paypal.dao.entities.OrigemArquivoDaoBean;
import com.paypal.entities.entity.LayoutInformacao;
import com.paypal.entities.entity.LayoutOrigemArquivo;
import com.paypal.entities.entity.LayoutTipoRegistro;
import com.paypal.entities.entity.OrigemArquivoPath;
import com.paypal.entities.enuns.TipoCampoEnum;
import com.paypal.entities.enuns.TipoRegistroEnum;
import com.paypal.entities.enuns.TipoSeparadorEnum;
import com.paypal.util.LayoutException;
import com.paypal.util.MetaException;

public class GerenciadorLayouts {
	
	private List<LayoutOrigemArquivo> layoutsArquivos;

	private LayoutOrigemArquivo layoutAtual;
	
	private Integer idAdquirente = null;
	private String nomeAdquirente = null;
	
	private LayoutOrigemArquivoDaoBean dao = new LayoutOrigemArquivoDaoBean();
	
	private OrigemArquivoDaoBean origemArquivoDao = new OrigemArquivoDaoBean();
	
	private String idArquivoAtual = null;
	
	private List<OrigemArquivoPath> paths;
	
	// Split da linha
	private String[] splitLinha = null;
	
	/**
	 * Carrega os layouts cadastrados no banco para o adquirente
	 * @param codigoOrigem
	 * @throws LayoutException
	 */
	
	public GerenciadorLayouts(String codigoOrigem) throws LayoutException, MetaException {
		carregarLayouts(codigoOrigem);
		carregarPaths(codigoOrigem);
	}
	
	private void carregarLayouts(String codigoOrigem) throws LayoutException {
		
		layoutsArquivos = dao.findLayoutsByIdAdquirente(codigoOrigem);

		if (layoutsArquivos == null) {
			throw new LayoutException(null, "Não existe layout cadastrado para a origem "+codigoOrigem);
		}
		if (layoutsArquivos.size() == 1) {
			layoutAtual = layoutsArquivos.iterator().next();
		}
	}
	
	private void carregarPaths(String codigoOrigem) throws MetaException {
		
		paths = origemArquivoDao.carregaPaths(codigoOrigem);

		if (paths == null) {
			throw new MetaException(null, "Não existe path cadastrado para a origem "+codigoOrigem);
		}
		
	} 
	
	/**
	 * Para a linha passada, procura qual é o layout que deve ser utilizado.
	 * Deverá entrar aqui sempre na primeira linha, quando for o Header. 
	 * Em casos onde tiver mais de um layout cadastrado e o arquivo que for processado não tiver Header, vai acabar dando a exception que não existe layout cadastrado para o arquivo.
	 * Para identificar o layout, é utilizado a informação do campo Arquivo Versão que tem no Header dos arquivos e comparado com o valor registrado no banco para cada arquivo
	 * 
	 * @param linha
	 * @return
	 * @throws LayoutException
	 */
	
	public LayoutOrigemArquivo carregaLayout(String linha) throws LayoutException {
		
		if (layoutAtual != null) {
			return layoutAtual;
		}
		
		if (layoutsArquivos.size() == 1) {
			layoutAtual = layoutsArquivos.iterator().next();
			return layoutAtual;
		}
		
		forPrincipal: for (LayoutOrigemArquivo layoutAdquirente : layoutsArquivos) {
			
			for (LayoutTipoRegistro tipo : layoutAdquirente.getTiposRegistros()) {
				
				if (tipo.getTipoRegistro().equals(TipoRegistroEnum.HEADER.getChave())) {
					for (LayoutInformacao info : tipo.getInformacoes()) {
						
						if (info.getLayoutCampo().getId().equals(TipoCampoEnum.ARQUIVO_VERSAO.getChave())) {
							
							if (linha != null && !linha.isEmpty() && info.getPosFim() != null && linha.length() < info.getPosFim()) {
								continue;
							}
							
							// Identifica qual é o arquivo, para casos de arquirente que tem mais de um arquivo
							if (extraiValorLinha(linha, info, layoutAdquirente).trim().equals(info.getValorFixo().trim())) {
								layoutAtual = layoutAdquirente;
								idArquivoAtual = extraiValorLinha(linha, info, layoutAdquirente);
								break forPrincipal;
							}
						}
						
					}
				}
				
			}
		}
		
		if (layoutAtual == null) {
			throw new LayoutException(null, "Não existe layout cadastrado para o arquivo ");
		}
		
		return layoutAtual;
	}
	
	private String extraiValorLinha(String linha, LayoutInformacao campo, LayoutOrigemArquivo layoutAdquirente) {
		
		if (isPosicional(layoutAdquirente)) {
			return linha.substring(campo.getPosIni()-1, campo.getPosFim());
		} else {
			if (splitLinha == null) {
				splitLinha = linha.split(Pattern.quote(separador(layoutAdquirente)));
			}
			
			return splitLinha[campo.getPosicao()-1];
		}
		
	}
	
	private boolean isPosicional(LayoutOrigemArquivo layoutAdquirente) {
		return TipoSeparadorEnum.POSICIONAL.getNome().toLowerCase().equals(layoutAdquirente.getTipoSeparador().toLowerCase());
	}
	
	private String separador(LayoutOrigemArquivo layoutAdquirente) {
		return layoutAdquirente.getSeparador();
	}
	
	public LayoutInformacao layoutInformacao(TipoRegistroEnum tipoReg, TipoCampoEnum tipoCampo) throws MetaException {
		return layoutInformacao(tipoReg, tipoCampo, null, null);
	}
	
	/**
	 * 
	 * Extrai da linha a informação conforme o Tipo de Registro passado e o tipo do campo. Essas infomações estão registradas no banco para cada arquivo.
	 * 
	 * Por exemplo, para um tipo de registro pode se passar Header e para o campo Tipo de Registro. 
	 * Com isso, vai procurar no layout atual do arquivo as configurações registradas no banco para o tipo header e campo Tipo de Registro. Quando encontrar, vai utilizar as posições no banco 
	 * que indicam onde na linha está a informação de tipo de registro. 
	 * 
	 * @param tipoReg
	 * @param tipoCampo
	 * @param linha
	 * @param layoutTipoReg
	 * @return
	 * @throws LayoutException 
	 */
	
	public LayoutInformacao layoutInformacao(TipoRegistroEnum tipoReg, TipoCampoEnum tipoCampo, String linha, LayoutTipoRegistro layoutTipoReg) throws LayoutException  {
		
		if (layoutAtual != null) {
			for (LayoutTipoRegistro tipo : layoutAtual.getTiposRegistros()) {
				if (tipo.getTipoRegistro().equals(tipoReg.getChave())) {
					if (layoutTipoReg != null && !tipo.equals(layoutTipoReg)) {
						continue;
					}
					for (LayoutInformacao info : tipo.getInformacoes()) {
						
						if (info.getLayoutCampo().getId().equals(tipoCampo.getChave())) {
							if (linha != null && !linha.isEmpty()) {
								// Se não for o tipo de registro da linha passada, cai fora.
								if (!extraiValorLinha(linha, info, layoutAtual).equals(info.getValorFixo())) {
									continue;
								}
							}
							return info;
						}
					}
				}
			}
		}
		
		if(camposOpcionais(tipoCampo)){
			return null;
		}
		
		throw new LayoutException(null, "Layout não cadastrado para o tipo de Registro "+tipoReg.getDescricao() +" e tipo de campo "+tipoCampo.getDescricao());
		
	}
	
	private boolean camposOpcionais(TipoCampoEnum tipoCampo){
		if(tipoCampo.getChave().equals(TipoCampoEnum.VALIDA_QUANTIDADE_REGISTRO.getChave())){
			return true;
		}
		return false;
	}
	
	public LayoutOrigemArquivo layoutAtual() {
		return layoutAtual;
	}
	
//	public String pathArquivos() {
//		return layoutsArquivos.iterator().next().getOrigemArquivoArq().getLocalPreProcessamento();
//	}
//	
//	public String pathErro() {
//		return layoutsArquivos.iterator().next().getOrigemArquivoArq().getLocalPreProcessamentoErro();
//	}
//	
//	public String pathProcessado() {
//		return layoutsArquivos.iterator().next().getOrigemArquivoArq().getLocalDestino();
//	}
	
	/**
	 * Path será utilizando como local de pré-processamento. Antes de pré-processar, vou copiar tudo para estar pasta.
	 */
	
//	public String pathOrigem() {
//		return layoutsArquivos.iterator().next().getOrigemArquivoArq().getLocalOrigem();
//	}
	
	public Integer getIdAdquirente() {
		if (layoutAtual != null) {
			idAdquirente = layoutAtual.getOrigemArquivoArq().getOrigemArquivo().getIdAdquirente();
		}
			
		return idAdquirente;
	}

	public String getNomeAdquirente() {
		if (layoutAtual != null) {
			if (layoutAtual.getOrigemArquivoArq().getOrigemArquivo().getAdquirente() != null) {
				nomeAdquirente = layoutAtual.getOrigemArquivoArq().getOrigemArquivo().getAdquirente().getNome();
			}
		}
			
		return nomeAdquirente;
	}
	public void limpaLayoutAtual() {
		layoutAtual = null;
	}

	public String getIdArquivoAtual() {
		return idArquivoAtual;
	}
	
	public Integer getIdOrigemArquivo() {
		return  layoutsArquivos.iterator().next().getOrigemArquivoArq().getOrigemArquivo().getId();
	}

	public List<OrigemArquivoPath> getPaths() {
		return paths;
	}

	public void setPaths(List<OrigemArquivoPath> paths) {
		this.paths = paths;
	}

	public String[] getSplitLinha() {
		return splitLinha;
	}

	public void setSplitLinha(String[] splitLinha) {
		this.splitLinha = splitLinha;
	}
	
}
