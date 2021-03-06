
package com.login.huntvision.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Constantes {

	private Constantes() {

	}

	public static final String KEY_SERVLET = "5Mu1tL0g1N";
	public static final String USUARIO_CONECTADO = "usuarioConectado";
	public static final String USUARIOS_CONECTADOS = "usuariosConectados";
	public static final Long GRUPO_ADMINISTRADOR = 7L;
	public static final Long GRUPO_TRANSALVADOR = 1L;
	public static final Long MENU_USUARIO = 3L;
	public static final Long TIPO_MIDIA_FOTO = 1L;
	public static final Long TIPO_MIDIA_VIDEO = 2L;

	public static final String CAMINHO_ARQUIVO = "E:\\arquivos_huntvision\\";
	// public static final String CAMINHO_ARQUIVO = "/Users/login/teste/";

	public static final String URL_YOUTUBE = "http://www.youtube.com/v/";
	public static final Long TIPO_BANNER_ROTATIVO = 1L;
	public static final Long TIPO_BANNER_INFERIOR = 2L;
	public static final Long TIPO_BANNER_LATERAL = 3L;

	public static final String REMETENTE_CLIENTE = "vistoria@ggold.com.br";
	public static final String REMETENTE = "laboratoriologin@gmail.com";
	public static final String SMTP_GMAIL = "smtp.gmail.com";
	public static final String PORTAL_GMAIL = "465";
	public static final String SENHA_GMAIL = "l0g1n.s3n41";
	public static final String ASSUNTO_EMAIL = "Hunt Vision - Relatório de Vistoria - " + getDataAtual();
 
	public static final String P12 = "CertificadoSemutDev.p12";
	public static final String CHAVE_P12 = "L0G1n.L@b";

	public static final Long SITUACAO_RESOLVIDA = 4L;

	public static final String PARAM_VER_OCORRENCIA = "ocorrencia";
	public static final Long MENU_OCORRENCIA = 3028L;
	public static final String AUTH_FACES = "autenticacaoFaces";

	public static final int TIPO_AGENDA_SEMANAL = 2;
	public static final int TIPO_AGENDA_MENSAL = 3;
	
	public static final Long MENU_MEU_ACOMPANHAMENTO = 111L;
	public static final Long MENU_ACOMPANHAMENTO_GERAL = 110L;
	public static final Long MENU_MEU_ACOMPANHAMENTO_OTIMIZE = 29L;
	public static final Long MENU_ACOMPANHAMENTO_GERAL_OTIMIZE = 30L;
	
	
	public static String getDataAtual() {  
		String data = new String();
	    SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
	    Date dataAtual = new Date(System.currentTimeMillis());
	    data = sd.format(dataAtual);        
	    return data;
	}
	
	public static String YELLOW_DOT = "http://maps.google.com/mapfiles/ms/micons/yellow-dot.png";
	public static String RED_DOT = "http://maps.google.com/mapfiles/ms/micons/red-dot.png";
	public static String BLUE_DOT = "http://maps.google.com/mapfiles/ms/micons/blue-dot.png";
	public static String GREEN_DOT = "http://maps.google.com/mapfiles/ms/micons/green-dot.png";

	
}

