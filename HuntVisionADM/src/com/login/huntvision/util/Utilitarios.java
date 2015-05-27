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



		email.append(" <body>	<div id='geral' style='font-family:Gotham, Helvetica Neue, Helvetica, Arial, sans-serif; font-size: 14px; height: 300px; width: 600px;'>  <div id='linhaAzul' style='font-family:Gotham, Helvetica Neue, Helvetica, Arial, sans-serif;  font-size: 14px;  height: 12px;  width: 600px; background-color:#2980BA;'></div>  <div id='corpoEmail' style='font-family:Gotham, Helvetica Neue, Helvetica, Arial, sans-serif;  font-size: 14px; height: 200px;  width: 600px; background-color:#d9d9d9;'>    <img id='logo' src='http://177.1.212.50:9004/HuntVisionADM/resources/images/logo_hunt_vision_260px.png' width='100px'height='100px' style='font-family:Gotham, Helvetica Neue, Helvetica, Arial, sans-serif; font-size: 14px; background-color:#d9d9d9;' /> ")
		.append(" <div id='texto' style=' font-family:Gotham, 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; height: 100px; width: 600px; background-color:#d9d9d9; position:relative;'>    <div class='textoCentralizado' style='width: 400; padding-left: 116px; color:#00000;'> ")
		.append(" <p>Prezado(a) ").append(cliente.getNome()).append(" </p> ")

		.append(" <p>O resultado da vistoria realizada dia ").append(data).append(" pode ser encontrado clicando no link abaixo ou se preferir acessar nossa página. </p>  </div> <div style='width: 400; padding-left: 116px;'>  Clique <a  href='").append(url).append("'>aqui</a></div></div>  </div> ")
		.append("  <div id='mensagemAntiSpan' style='font-family:Gotham, Helvetica Neue, Helvetica, Arial, sans-serif;  color:#868686;  font-size: 12px;  height: 76px;  width: 600px;  background-color: #d9d9d9;  text-align: left;  padding-top: 8px;'> <div class='textoCentralizado' style='width: 400; padding-left: 116px;'> ")
		.append(" <p>Esta mensagem foi enviada para o e-mail que você cadastrou no sistema HuntVision. Caso prefira não mais receber esta mensagem solicite a remoção ao admistrador do sistema.</p> ")
		.append("   </div> <div id='linhaLaranja' style='font-family:Gotham, Helvetica Neue, Helvetica, Arial, sans-serif; font-size:14px;   height: 12px;  width: 600px;  background-color: #e67e22;'></div> </div> ")
		.append(" </body> ");

		

		return email.toString();

	}

	public static String getNovaSenhaEmailMessage(Usuario usuario) {

		StringBuilder email = new StringBuilder();

		email.append("Prezado(a) ").append(usuario.getNome()).append(", <br/>").append("A sua nova senha é: <br/>").append(usuario.getSenha());

		return email.toString();

	}

}