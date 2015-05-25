package com.login.huntvision.util;

import java.text.Normalizer;
import java.util.Calendar;
import java.util.List;

import com.login.huntvision.model.Cliente;
import com.login.huntvision.model.Usuario;

import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSCryptoUtil;
import br.com.topsys.util.TSUtil;

public final class Utilitarios {

	private Utilitarios() {

	}

	public static String tratarString(String valor) {
		if (!TSUtil.isEmpty(valor)) {
			valor = valor.trim().replaceAll("  ", " ");
		}

		return valor;
	}

	public static Long tratarLong(Long valor) {
		if ((!TSUtil.isEmpty(valor)) && (valor.equals(Long.valueOf(0L)))) {
			valor = null;
		}

		return valor;
	}

	public static String removerAcentos(String palavra) {
		if (TSUtil.isEmpty(palavra)) {
			return null;
		}

		return Normalizer.normalize(new StringBuilder(palavra), Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}

	public static String gerarHash(String valor) {
		if (!TSUtil.isEmpty(valor)) {
			valor = TSCryptoUtil.gerarHash(valor, "md5");
		}

		return valor;
	}

	public static String gerarSenha() {
		Calendar rightNow = Calendar.getInstance();

		int diaAtual = rightNow.get(Calendar.DAY_OF_MONTH);
		int mesAtual = rightNow.get(Calendar.MONTH) + 1;
		int anoAtual = rightNow.get(Calendar.YEAR);
		int horaAtual = rightNow.get(Calendar.HOUR);
		int minutoAtual = rightNow.get(Calendar.MINUTE);
		int segAtual = rightNow.get(Calendar.SECOND);
		int miliAtual = rightNow.get(Calendar.MILLISECOND);

		String senha = anoAtual + mesAtual + diaAtual + horaAtual + minutoAtual + segAtual + miliAtual + "";

		return senha;
	}

	public static String gerarNomeArquivo() {

		return String.valueOf(System.currentTimeMillis());
	}

	public static String getSituacao(Boolean flagAtivo) {
		if ((!TSUtil.isEmpty(flagAtivo)) && (flagAtivo.booleanValue())) {
			return "Ativo";
		}

		return "Inativo";
	}

	public static String getVistoriaEmailMessage(Cliente cliente, String url , String data, String imageContext) {

		StringBuilder email = new StringBuilder();

//		email.append("Prezado(a) ").append(cliente.getNome()).append(", <br/>").append("O resultado da vistoria pode ser encontrado clicando <a href='").append(url).append("'>aqui</a>");

		email.append(" <html><head><meta charset='utf-8'><style rel='stylesheet' type='text/css'> ")

		.append(" #geral { ").append("font-family:Gotham, 'Helvetica Neue', Helvetica, Arial, sans-serif; ").append("font-size: 20px; ").append("height: 300px; ").append(" width: 600px; ").append("} ")

		.append("#linhaAzul { ").append("  font-family:Gotham, 'Helvetica Neue', Helvetica, Arial, sans-serif; ").append("  font-size: 20px; ").append("  height: 12px; ").append("  width: 600px; ").append(" background-color: #2980BA; ").append(" } ")

		.append("#corpoEmail { ").append(" font-family:Gotham, 'Helvetica Neue', Helvetica, Arial, sans-serif; ").append(" font-size: 20px; ").append(" height: 200px; ").append(" width: 600px; ").append(" background-color:#d9d9d9; ").append("} ")

		.append("#logo { ").append(" font-family:Gotham, 'Helvetica Neue', Helvetica, Arial, sans-serif; ").append(" font-size: 20px; ").append(" height: 100px; ").append("  width: 600px; ").append("  background-color:#d9d9d9; ").append("} ")

		.append("#texto { ").append("  font-family:Gotham, 'Helvetica Neue', Helvetica, Arial, sans-serif; ").append(" font-size: 14px; ").append(" height: 100px; ").append(" width: 600px; ").append("background-color:#d9d9d9; ").append("position:relative; ").append("color: #3C3C3B; ")

		.append("} ")

		.append(".textoCentralizado { ").append(" width: 400; ").append("padding-left: 116px; ").append("} ")

		.append("#mensagemAntiSpan { ").append("font-family:Gotham, 'Helvetica Neue', Helvetica, Arial, sans-serif; ").append(" color:#868686; ").append(" font-size: 12px; ").append("  height: 76px; ").append(" width: 600px; ").append(" background-color: #d9d9d9; ").append(" text-align: left; ").append(" padding-top: 8px; ").append("} ")

		.append(" #linhaLaranja { ").append("   font-family:Gotham, 'Helvetica Neue', Helvetica, Arial, sans-serif; ").append("  font-size: 20px; ").append(" height: 12px; ").append(" width: 600px; ").append(" background-color: #e67e22; ").append(" } ")

		.append(" </style> ").append(" <title>Huntivision - Info Mail</title></head> ")

		.append(" <body>	<div id='geral'>  <div id='linhaAzul'></div>  <div id='corpoEmail'>    <img src='").append(imageContext).append("resources/images/logo_hunt_vision_260px.png' width='100' height='100'  alt=''/> ").append("   <div id='texto'>    <div class='textoCentralizado'> ").append(" <p>Prezado(a) ").append(cliente.getNome()).append("</p>  ")

		.append(" 	<p>O resultado da vistoria realizada dia " +  data  + "  pode ser encontrado clicando no link abaixo ou se preferir acessar nossa página. </p> <p> Acesse o relatório clicando <a href='").append(url).append("'>aqui</a></p>  </div></div>  </div> ").append("  <div id='mensagemAntiSpan'> <div class='textoCentralizado'>  <p>Esta mensagem foi enviada para o e-mail que você cadastrou no sistema HuntVision. Caso prefira não mais receber esta mensagem solicite a remoção ao administrador do sistema.</p> ").append("   </div> <div id='linhaLaranja'></div> </div> ").append(" </body> ").append(" </html> ");

		return email.toString();

	}

	public static String getNovaSenhaEmailMessage(Usuario usuario) {

		StringBuilder email = new StringBuilder();

		email.append("Prezado(a) ").append(usuario.getNome()).append(", <br/>").append("A sua nova senha é: <br/>").append(usuario.getSenha());

		return email.toString();

	}

}