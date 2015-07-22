package login.com.huntvision.utils;

import java.util.Collection;

/**
 * Created by Ricardo on 16/01/2015.
 */
public final class Constantes {

    public static final String IMAGE_CACHE = "thumbs";
    public static final String KEY_SERVLET = "5Mu1tL0g1N";
    public static final String SHARED_PREFS = "prefs";
    public static final String URL_WS_GOLD = "http://177.1.212.50:9004/HuntVisionWS";
    public static final String URL_WS_OTIMIZE = "http://177.1.212.50:9004/OtimizeWS";
    public static final String URL_WS_LOCAL = "http://10.0.0.102:8080/HuntVisionWS";
    public static final String KEYMOBILE = "123";
    public static final String ID_CATEGORIA_HUNTVISION = "id";
    public static final String KEY_HUNTVISION = "keyHuntVision";
    public static final String KEY_WS = "keyWS";
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
    public static final String SECURITY_KEY = "Senai.l@g1n";
    public static boolean isEmpty(Object value) {

        if (value == null) {
            return true;
        } else if (value instanceof Collection) {
            return ((Collection) value).isEmpty();
        } else if (value instanceof String) {
            return isEmpty((String) value);
        }

        return false;
    }
    public static String YELLOW_DOT = "http://maps.google.com/mapfiles/ms/micons/yellow-dot.png";
    public static String RED_DOT = "http://maps.google.com/mapfiles/ms/micons/red-dot.png";
    public static String BLUE_DOT = "http://maps.google.com/mapfiles/ms/micons/blue-dot.png";
    public static String GREEN_DOT = "http://maps.google.com/mapfiles/ms/micons/green-dot.png";

    private Constantes() {
    }


}