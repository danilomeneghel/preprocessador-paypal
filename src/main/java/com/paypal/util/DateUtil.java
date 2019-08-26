package com.paypal.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	/**
	 * Formato "dd/mm/yyyy".
	 */
	public static DateFormat BRAZIL_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	/**
	 * Formato "dd/mm/yy".
	 */
	public static DateFormat BRAZIL_SHORT_FORMAT = new SimpleDateFormat("dd/mm/yy");

	public static DateFormat YYYY = new SimpleDateFormat("yyyy");

	public static DateFormat mmyyyy = new SimpleDateFormat("MM/yyyy");

	/**
	 * Formato "dd/mm/yyyy hh:MM:ss SSS".
	 */
	public static DateFormat FORMAT_COMPLETO = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss SSS");
	public static DateFormat FORMAT_ddMMyyyyHHMM = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	/**
	 * Nao possivel instanciar DateUtil.
	 */
	public DateUtil() {
	}

	/**
	 * Retorna o valor do hor�rio minimo para a data de referencia passada.
	 * <BR>
	 * <BR>
	 * Por exemplo se a data for "30/01/2009 as 17h:33m:12s e 299ms" a data
	 * retornada por este metodo ser� "30/01/2009 as 00h:00m:00s e 000ms".
	 * 
	 * @param date
	 *            de referencia.
	 * @return {@link Date} que representa o hor�rio minimo para dia
	 *         informado.
	 */
	public static Date lowDateTime(Date date) {
		Calendar aux = Calendar.getInstance();
		aux.setTime(date);
		toOnlyDate(aux); // zera os parametros de hour,min,sec,milisec
		return aux.getTime();
	}

	/**
	 * Retorna o valor do hor�rio maximo para a data de referencia passada.
	 * <BR>
	 * <BR>
	 * Por exemplo se a data for "30/01/2009 as 17h:33m:12s e 299ms" a data
	 * retornada por este metodo ser� "30/01/2009 as 23h:59m:59s e 999ms".
	 * 
	 * @param date
	 *            de referencia.
	 * @return {@link Date} que representa o hor�rio maximo para dia
	 *         informado.
	 */
	public static Date highDateTime(Date date) {
		Calendar aux = Calendar.getInstance();
		aux.setTime(date);
		toOnlyDate(aux); // zera os parametros de hour,min,sec,milisec
		aux.set(Calendar.HOUR_OF_DAY, aux.getActualMaximum(Calendar.HOUR_OF_DAY));
		aux.set(Calendar.MINUTE, aux.getActualMaximum(Calendar.MINUTE));
		aux.set(Calendar.SECOND, aux.getActualMaximum(Calendar.SECOND));
		return aux.getTime();
	}

	public static Date firstDayDate(Date date) {
		Calendar dataAtual = Calendar.getInstance();

		dataAtual.setTime(date);

		Calendar dataPrimeiroDia = new GregorianCalendar(dataAtual.get(Calendar.YEAR), dataAtual.get(Calendar.MONTH),
				1);

		Calendar retorno = Calendar.getInstance();

		retorno.set(dataPrimeiroDia.get(Calendar.YEAR), dataPrimeiroDia.get(Calendar.MONTH),
				dataPrimeiroDia.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

		return retorno.getTime();
	}

	public static Date lastDayDate(Date date) {
		Calendar dataUltimoDia = Calendar.getInstance();
		dataUltimoDia.setTime(firstDayDate(date));
		dataUltimoDia.add(Calendar.MONTH, 1);
		dataUltimoDia.add(Calendar.DAY_OF_MONTH, -1);

		Calendar retorno = Calendar.getInstance();

		retorno.set(dataUltimoDia.get(Calendar.YEAR), dataUltimoDia.get(Calendar.MONTH),
				dataUltimoDia.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

		return retorno.getTime();
	}

	/**
	 * Zera todas as referencias de hora, minuto, segundo e milesegundo do
	 * {@link Calendar}.
	 * 
	 * @param date
	 *            a ser modificado.
	 */
	public static void toOnlyDate(Calendar date) {
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
	}

	/**
	 * Converte uma {@link String} no formato "dd/mm/yyyy" ou "dd/mm/yy" em um
	 * {@link Date}.
	 * 
	 * @param date
	 *            a {@link String} contendo a data.
	 * @return o objeto {@link Date} para a string passada.
	 * @throws IllegalArgumentException
	 *             caso a string n�o seja em dos dois formatos permitidos.
	 * @throws ParseException
	 */
	public static Date toDate(String date) throws ParseException {
		if (date == null)
			return null;
		try {
			return date.length() > 8 ? BRAZIL_FORMAT.parse(date) : BRAZIL_SHORT_FORMAT.parse(date);
		} catch (ParseException ex) {
			throw ex;
		}
	}

	/**
	 * Converte uma {@link String} no formato "mm/yyyy" em um {@link Date}.
	 * 
	 * @param date
	 *            a {@link String} contendo a data no formato mm/yyyy como
	 *            string.
	 * @return o objeto {@link Date} para a string passada.
	 * @throws IllegalArgumentException
	 *             caso a string n�o seja em dos dois formatos permitidos.
	 * @throws ParseException
	 */
	public static Date toDateMesAno(String date) throws ParseException {
		if (date == null)
			return null;
		try {
			return mmyyyy.parse(date);
		} catch (ParseException ex) {
			throw ex;
		}
	}

	/**
	 * Formata a data passada em uma {@link String} com o formato "dd/mm/yyyy".
	 * 
	 * @param date
	 *            a data a ser formatada.
	 * @return {@link String} com a data formatada.
	 */
	public static String toString(Date date) {
		if (date == null)
			return null;
		return BRAZIL_FORMAT.format(date);
	}

	/**
	 * Formata a data passada em uma {@link String} com <tt>shortFormat</tt>
	 * "dd/mm/yy", ou com o formato longo "dd/mm/yyyy".
	 * 
	 * @param date
	 *            a data a ser formatada.
	 * @param shortFormat
	 *            <tt>true</tt> se o formato � o curto, ou <tt>false</tt> caso
	 *            o formato seja o longo.
	 * @return {@link String} com a data formatada.
	 */
	public static String toString(Date date, boolean shortFormat) {
		if (date == null)
			return null;
		return shortFormat ? BRAZIL_SHORT_FORMAT.format(date) : BRAZIL_FORMAT.format(date);
	}

	/**
	 * 
	 * @return Data recebida por par�metro no formato
	 */
	public static String dataCompletaAtualFormatada() {
		return FORMAT_COMPLETO.format(new Date());
	}

	public static Date truncateToday() {
		return truncateDate(new Date());
	}

	public static Date truncateDate(Date date) {
		return truncateCalendar(date).getTime();
	}

	public static Date truncateDateFimDia(Date date) {
		return truncateCalendarFimDia(date).getTime();
	}

	public static Calendar truncateCalendar(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		return c;
	}

	public static Calendar truncateCalendarFimDia(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		return c;
	}

	public static Date retornaProximaDataFinanceira(Date data, int dias) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		// Retorna a quantidade de meses da carencia
		int meses = dias / 30;
		c.add(Calendar.MONTH, meses);

		int diasRestantes = dias - (meses * 30);

		int diaDoMes = c.get(Calendar.DAY_OF_MONTH);
		int restoDoMes = 30 - diaDoMes;

		if (diasRestantes > restoDoMes) {
			c.add(Calendar.MONTH, 1);
			c.add(Calendar.DAY_OF_MONTH, diasRestantes - restoDoMes);

		} else {
			c.add(Calendar.DAY_OF_MONTH, diasRestantes);
		}
		return c.getTime();
	}

	public static int diffInDays(Date d1, Date d2) {
		int MILLIS_IN_DAY = 86400000;

		Calendar c1 = truncateCalendar(d1);
		Calendar c2 = truncateCalendar(d2);

		return (int) ((c1.getTimeInMillis() - c2.getTimeInMillis()) / MILLIS_IN_DAY);
	}

	public static int diffInMinutes(Date d1, Date d2) {
		int minuto = 1000 * 60;

		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);

		return (int) ((c1.getTimeInMillis() - c2.getTimeInMillis()) / minuto);
	}

	public static Date somaDiminuiDias(Date data, int dias) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);

		c.add(Calendar.DAY_OF_MONTH, dias);
		return c.getTime();

	}

	public static Date somaDiminuiHoras(Date data, int horas) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);

		c.add(Calendar.HOUR_OF_DAY, horas);
		return c.getTime();

	}

	public static Date somaDiminuiSemanas(Date data, int semanas) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);

		c.add(Calendar.WEEK_OF_MONTH, semanas);
		return c.getTime();

	}

	public static Date somaDiminuiMeses(Date data, int meses) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);

		c.add(Calendar.MONTH, meses);
		return c.getTime();
	}

	public static Date somaDiminuiMinutos(Date data, int minutos) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);

		c.add(Calendar.MINUTE, minutos);
		return c.getTime();
	}

	/**
	 * Valida se uma data está contida dentro de um período.
	 * 
	 * @param min
	 *            A data inicial do período a ser comparado
	 * @param max
	 *            A data final do período a ser comparado
	 * @param date
	 *            A data que vai ser comparada com o período
	 * 
	 * @exception NullPointerException
	 *                se parâmetros nulos
	 * 
	 * @return True se data contida dentro do período passado, caso contrário,
	 *         false.
	 * 
	 */

	public static boolean between(Date min, Date max, Date date) {
		return (date.after(min) || date.equals(min)) && (date.before(max) || date.equals(max));
	}

	public static boolean mesmoAno(Date d1, Date d2) {
		return YYYY.format(d1).equals(YYYY.format(d2));
	}

	// retorna o dia da semana dada uma data
	public int retornarDiaSemana(Date data) {

		Calendar calendario = new GregorianCalendar();

		calendario.setTime(data);

		int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);

		return diaSemana;
	}

	public static String formataDataDDMMYYYYHHMM(Date data) {
		return FORMAT_ddMMyyyyHHMM.format(data);
	}

	public static Date atualizaHoraMinutoSegundo(Date data, int hora, int minuto, int segundo) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.set(Calendar.HOUR_OF_DAY, hora);
		c.set(Calendar.MINUTE, minuto);
		c.set(Calendar.SECOND, segundo);
		return c.getTime();
	}

	public String dataFormatada(String data) {
		String dataFormatada = null;
		if(data != null) {
			String array[] = new String[3];
			array = data.split("-|/");
			dataFormatada = array[2] + "-" + array[1] + "-" + array[0];
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar dataAtual = Calendar.getInstance();			
			dataAtual.add(Calendar.DAY_OF_MONTH, -1);
			
			dataFormatada = dateFormat.format(dataAtual.getTime());
		}
		
		return dataFormatada;
	}

	public String dataAtual() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date now = new Date();
	    String strDate = sdf.format(now);
	    
	    return strDate;
	}
	
	public String dataHoraAtual() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date now = new Date();
	    String strDate = sdf.format(now);
	    
	    return strDate;
	}
	
	public String dataHoraInicio() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date now = new Date();
		String strDate = sdf.format(now);
		
		return strDate;
	}
	
	public Date stringToDate(String data) throws ParseException {
		Date dateFormatada;
		if (data != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			dateFormatada = simpleDateFormat.parse(data);
		} else {
			dateFormatada = new Date();
		}
		return dateFormatada;
	}
	
	public String dateToString(Date data) throws ParseException {
		String dateFormatada;
		if (data != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			dateFormatada = simpleDateFormat.format(data);
		} else {
			String dataAtual = dataAtual();
			String array[] = new String[3];
			array = dataAtual.split("-");
			dateFormatada = array[0] + array[1] + array[2];
		}
		return dateFormatada;
	}
	
	public static Integer getAno(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		return c.get(Calendar.YEAR);
	}

	public static Integer getMes(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		return c.get(Calendar.MONTH);
	}

	public static Integer getDia(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	public static Integer getHora(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	public static Integer getMinuto(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		return c.get(Calendar.MINUTE);
	}
}
