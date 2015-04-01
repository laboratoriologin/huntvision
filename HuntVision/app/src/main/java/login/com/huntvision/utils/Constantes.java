package login.com.huntvision.utils;

/**
 * Created by Ricardo on 16/01/2015.
 */
public final class Constantes {

    public static final String IMAGE_CACHE = "thumbs";
    public static final String KEY_SERVLET = "5Mu1tL0g1N";
    public static final String SHARED_PREFS = "prefs";
    public static final String URL = "http://10.0.0.135:8080/HuntVisionADM/servlet";

    // public static final String URL = "http://177.1.212.50:9004/HuntVisionADM/servlet";
    // public static final String URL_IMG = "http://177.1.212.50:9004/arquivos_cardapio/";
    public static final String URL_HUNTVISION = URL + "/cardapio_app?empresa=";
    public static final String URL_CHAVE_HUNTVISION_EMPRESA = URL + "/key_cardapio_empresa?empresa=";
    public static final String URL_SOBRE_RESTAURANTE = URL + "/html?empresa=";

    //	public static final String URL = "http://177.1.212.50:9004/HuntVisionADM/servlet";
//	public static final String URL_IMG = "http://177.1.212.50:9004/arquivos_cardapio/";
//	public static final String URL_WS = "http://177.1.212.50:9004/HuntVisionWS";
    public static final String URL_ITEM_HUNTVISION = URL + "/item_cardapio_app?";
    public static final String URL_ADD_CONTA = URL + "/conta_app?";
    public static final String URL_CHK_CONTA_ABERTA = URL + "/check_conta_aberta_app?";
    public static final String URL_IMG = "http://10.0.0.135:8080/arquivos_cardapio/";
    public static final String URL_WS = "http://177.1.212.50:9004/HuntVisionWS";
    //public static final String URL_WS = "http://10.0.0.109:8080/HuntVisionWS";
    public static final String KEYMOBILE = "123";
    public static final String ID_CATEGORIA_HUNTVISION = "id";
    public static final String KEY_HUNTVISION = "keyHuntVision";
    public static final String KEY_USUARIO = "keyUsuario";
    public static final String QTD_MESA = "qtdMesa";
    public static final String DADOS_EMPRESA = "dadosEmpresa";
    public static final String ITEM_HUNTVISION = "item";
    public static final String ARG_CATEGORIA_HUNTVISION = "argCategoriaHuntVision";
    public static final String ARG_ITEM_HUNTVISION = "argItemHuntVision";
    public static final String MSG_ERRO_GRAVAR_DADOS = "Ops! Um erro ocorreu ao tentar \n gravar os dados, reinstale o aplicativo!";
    public static final String MSG_OK = "Operação realizada com sucesso!";
    public static final String MSG_ERRO_NET = "Ops! Ocorreu uma falha, \n verifique sua conexão e tente novamente!";
    public static final String MSG_ERRO_GRAVE_SISTEMA = "Ops! Falha grave no sistema!";
    public static final String MSG_ERRO_VALIDACAO_SISTEMA = "Ops! Ocorreu um erro no sistema, tente novamente!";
    public static final String MSG_ERRO_READ_QR_CODE = "Erro de leitura, tente novamente!";
    public static final String MSG_ERRO_PARSE = "Erro no parse do objeto!";
    public static final String MSG_SAUDACAO_UM = "Preparando pratos para você!";
    public static final String MSG_SAUDACAO_DOIS = "Buscando atualizações!";
    public static final String INPUTSTREAM = "INPUTSTREAM";
    public static final String FILETYPE = "FILETYPE";
    public static final String FILENAME = "FILENAME";

    private Constantes() {
    }
}