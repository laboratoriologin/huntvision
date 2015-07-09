package login.com.huntvision.utils;

import org.json.JSONException;
import org.json.JSONObject;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import login.com.huntvision.models.Agenda;
import login.com.huntvision.models.Cliente;
import login.com.huntvision.models.Imagem;
import login.com.huntvision.models.Item;
import login.com.huntvision.models.ItemLocal;
import login.com.huntvision.models.Local;
import login.com.huntvision.models.Questionario;
import login.com.huntvision.models.Resposta;
import login.com.huntvision.models.TipoQuestionario;
import login.com.huntvision.models.Usuario;

/**
 * Created by login on 17/03/15.
 */
public final class JsonUtil {

    private JsonUtil() {

    }

    public static List<ItemLocal> itensLocaisFromJsonObject(JSONObject jsonObject) {

        ItemLocal itemLocal = null;

        JSONObject jsonItemLocal = null;

        List<ItemLocal> itemLocalList = new ArrayList<ItemLocal>();

        try {

            for (int i = 0; i < Utilitarios.getAlwaysJsonArray(jsonObject, "").length(); i++) {

                jsonItemLocal = Utilitarios.getAlwaysJsonArray(jsonObject, "").getJSONObject(i);

                if (jsonItemLocal.has("itenslocais")) {

                    jsonItemLocal = jsonItemLocal.getJSONObject("itenslocais");

                    itemLocal = new ItemLocal();

                    itemLocal.setId(jsonItemLocal.has("id") ? jsonItemLocal.getString("id") : null);

                    itemLocal.setItem_id(jsonItemLocal.getJSONObject("item").getInt("id"));

                    itemLocal.setLocal_id(jsonItemLocal.getJSONObject("local").getInt("id"));

                    itemLocalList.add(itemLocal);

                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return itemLocalList;

    }

    public static List<Item> itemsFromJsonObject(JSONObject jsonObject) {

        Item item = null;

        JSONObject jsonItem = null;

        List<Item> itens = new ArrayList<Item>();

        try {

            for (int i = 0; i < Utilitarios.getAlwaysJsonArray(jsonObject, "").length(); i++) {

                jsonItem = Utilitarios.getAlwaysJsonArray(jsonObject, "").getJSONObject(i);

                if (jsonItem.has("item")) {

                    jsonItem = jsonItem.getJSONObject("item");

                    item = new Item();

                    item.setId(jsonItem.has("id") ? jsonItem.getString("id") : null);

                    item.setChave((jsonItem.has("chave") ? jsonItem.getString("chave") : "").toString());

                    item.setDescricao((jsonItem.has("descricao") ? jsonItem.getString("descricao") : "").toString());

                    itens.add(item);

                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return itens;

    }

    public static List<Usuario> usuariosFromJsonObject(JSONObject jsonObject) {

        List<Usuario> usuarios = new ArrayList<Usuario>();

        Usuario usuario = null;

        JSONObject jsonUsuario = null;


        try {

            for (int i = 0; i < Utilitarios.getAlwaysJsonArray(jsonObject, "").length(); i++) {

                jsonUsuario = Utilitarios.getAlwaysJsonArray(jsonObject, "").getJSONObject(i);

                if (jsonUsuario.has("usuario")) {

                    jsonUsuario = jsonUsuario.getJSONObject("usuario");

                    usuario = new Usuario();

                    usuario.setId(jsonUsuario.has("id") ? jsonUsuario.getString("id") : null);

                    usuario.setCelular(jsonUsuario.has("celular") ? jsonUsuario.getString("celular") : "");

                    usuario.setLogin(jsonUsuario.has("login") ? jsonUsuario.getString("login") : "");

                    usuario.setEmail(jsonUsuario.has("email") ? jsonUsuario.getString("email") : "");

                    usuario.setNome(jsonUsuario.has("nome") ? jsonUsuario.getString("nome") : "");

                    usuario.setSenha(jsonUsuario.has("senha") ? jsonUsuario.getString("senha") : "");

                    usuario.setFlagAtivo(1);

                    usuarios.add(usuario);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;

    }

    public static List<Cliente> clientesFromJsonObject(JSONObject jsonObject) {

        Cliente cliente = null;

        JSONObject jsonCliente = null;

        List<Cliente> clientes = new ArrayList<Cliente>();

        try {

            for (int i = 0; i < Utilitarios.getAlwaysJsonArray(jsonObject, "").length(); i++) {

                jsonCliente = Utilitarios.getAlwaysJsonArray(jsonObject, "").getJSONObject(i);

                if (jsonCliente.has("cliente")) {

                    jsonCliente = jsonCliente.getJSONObject("cliente");

                    cliente = new Cliente();

                    cliente.setId(jsonCliente.has("id") ? jsonCliente.getString("id") : null);

                    cliente.setCelular(jsonCliente.has("telefone") ? jsonCliente.getString("telefone") : "");

                    cliente.setPais(jsonCliente.has("pais") ? jsonCliente.getString("pais") : "");

                    cliente.setEstado(jsonCliente.has("estado") ? jsonCliente.getString("estado") : "");

                    cliente.setCep(jsonCliente.has("cep") ? jsonCliente.getString("cep") : "");

                    cliente.setCNPJ(jsonCliente.has("cnpj") ? jsonCliente.getString("cnpj") : "");

                    cliente.setBairro(jsonCliente.has("bairro") ? jsonCliente.getString("bairro") : "");

                    cliente.setEmail(jsonCliente.has("email") ? jsonCliente.getString("email") : "");

                    cliente.setNome(jsonCliente.has("nome") ? jsonCliente.getString("nome") : "");

                    cliente.setCidade(jsonCliente.has("cidade") ? jsonCliente.getString("cidade") : "");

                    cliente.setEndereco(jsonCliente.has("endereco") ? jsonCliente.getString("endereco") : "");

                    clientes.add(cliente);

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clientes;

    }

    public static List<Local> locaisFromJsonObject(JSONObject jsonObject) {

        Local local = null;

        JSONObject jsonLocal = null;

        List<Local> locais = new ArrayList<Local>();

        try {

            for (int i = 0; i < Utilitarios.getAlwaysJsonArray(jsonObject, "").length(); i++) {

                jsonLocal = Utilitarios.getAlwaysJsonArray(jsonObject, "").getJSONObject(i);

                if (jsonLocal.has("locais")) {

                    jsonLocal = jsonLocal.getJSONObject("locais");

                    local = new Local();

                    local.setId(jsonLocal.getString("id"));

                    local.setClienteId(jsonLocal.getJSONObject("cliente").getLong("id"));

                    local.setNomeLocal(jsonLocal.has("nomeLocal") ? jsonLocal.getString("nomeLocal") : "");

                    locais.add(local);

                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return locais;

    }

    public static List<Questionario> questionariosFromJsonObject(JSONObject jsonObject) {

        Questionario questionario = null;

        JSONObject jsonQuestionario = null;

        List<Questionario> questionarios = new ArrayList<Questionario>();

        try {

            for (int i = 0; i < Utilitarios.getAlwaysJsonArray(jsonObject, "").length(); i++) {

                jsonQuestionario = Utilitarios.getAlwaysJsonArray(jsonObject, "").getJSONObject(i);

                if (jsonQuestionario.has("questionarios")) {

                    jsonQuestionario = jsonQuestionario.getJSONObject("questionarios");

                    questionario = new Questionario();

                    questionario.setId(jsonQuestionario.getString("id"));

                    questionario.setUsuarioId(jsonQuestionario.getJSONObject("usuario").getLong("id"));

                    questionario.setTipoQuestionarioId(jsonQuestionario.getJSONObject("tipoQuestionario").getLong("id"));

                    questionario.setItemId(jsonQuestionario.getJSONObject("item").getLong("id"));


                    questionario.setStatus(jsonQuestionario.has("status") ? jsonQuestionario.getString("status") : "");

                    questionario.setPergunta(jsonQuestionario.has("pergunta") ? jsonQuestionario.getString("pergunta") : "");

                    questionario.setData(jsonQuestionario.has("data") ? jsonQuestionario.getString("data") : "");


                    questionarios.add(questionario);

                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return questionarios;

    }


    public static List<TipoQuestionario> tipoQuestionariosFromJsonObject(JSONObject jsonObject) {

        TipoQuestionario tipoQuestionario = null;

        JSONObject jsonTipoQuestionario = null;

        List<TipoQuestionario> tipoQuestionarios = new ArrayList<TipoQuestionario>();

        try {

            for (int i = 0; i < Utilitarios.getAlwaysJsonArray(jsonObject, "").length(); i++) {

                jsonTipoQuestionario = Utilitarios.getAlwaysJsonArray(jsonObject, "").getJSONObject(i);

                if (jsonTipoQuestionario.has("tipoquestionarios")) {

                    jsonTipoQuestionario = jsonTipoQuestionario.getJSONObject("tipoquestionarios");

                    tipoQuestionario = new TipoQuestionario();

                    tipoQuestionario.setId(jsonTipoQuestionario.getString("id"));

                    tipoQuestionario.setDescricao(jsonTipoQuestionario.has("descricao") ? jsonTipoQuestionario.getString("descricao") : "");


                    tipoQuestionarios.add(tipoQuestionario);

                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tipoQuestionarios;

    }

    public static List<Resposta> respostasFromJsonObject(JSONObject jsonObject) {

        Resposta resposta = null;

        JSONObject jsonResposta = null;

        List<Resposta> respostas = new ArrayList<Resposta>();

        try {

            for (int i = 0; i < Utilitarios.getAlwaysJsonArray(jsonObject, "").length(); i++) {

                jsonResposta = Utilitarios.getAlwaysJsonArray(jsonObject, "").getJSONObject(i);

                if (jsonResposta.has("respostas")) {

                    jsonResposta = jsonResposta.getJSONObject("respostas");

                    resposta = new Resposta();

                    resposta.setId(jsonResposta.getString("id"));

                    resposta.setDescricao(jsonResposta.has("descricao") ? jsonResposta.getString("descricao") : "");

                    resposta.setFlagRespostaCerta(jsonResposta.has("flagrespostacerta") ? jsonResposta.getBoolean("flagrespostacerta") : false);

                    resposta.setObservacao(jsonResposta.has("observacao") ? jsonResposta.getString("observacao") : "");

                    resposta.setQuestionarioId(jsonResposta.getJSONObject("questionario").getLong("id"));

                    respostas.add(resposta);

                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return respostas;

    }

    public static List<Agenda> agendasFromJsonObject(JSONObject jsonObject) {

        Agenda agenda = null;

        JSONObject jsonAgenda = null;

        List<Agenda> agendas = new ArrayList<Agenda>();

        try {

            for (int i = 0; i < Utilitarios.getAlwaysJsonArray(jsonObject, "").length(); i++) {

                jsonAgenda = Utilitarios.getAlwaysJsonArray(jsonObject, "").getJSONObject(i);

                if (jsonAgenda.has("agendas")) {

                    jsonAgenda = jsonAgenda.getJSONObject("agendas");

                    agenda = new Agenda();

                    agenda.setId(jsonAgenda.getString("id"));

                    agenda.setDescricao(jsonAgenda.has("descricao") ? jsonAgenda.getString("descricao") : "");

                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                    Date dateHora = formatter.parse(jsonAgenda.has("dataHoraFormatada") ? jsonAgenda.getString("dataHoraFormatada") : "");

                    agenda.setDataHora(dateHora);

                    Date dateHoraChegada = null;

                    if(jsonAgenda.has("dataHoraChegadaFormatada")) {
                        dateHoraChegada = formatter.parse(jsonAgenda.getString("dataHoraChegadaFormatada"));
                    }

                    agenda.setDataHoraChegada(dateHoraChegada);

                    Date dateHoraSaida = null;

                    if(jsonAgenda.has("dataHoraSaidaFormatada")) {
                        dateHoraSaida = formatter.parse(jsonAgenda.getString("dataHoraSaidaFormatada"));
                    }

                    agenda.setDataHoraSaida(dateHoraSaida);

                    agenda.setClienteId(jsonAgenda.getJSONObject("cliente").getLong("id"));

                    agenda.setUsuarioId(jsonAgenda.getJSONObject("usuario").getLong("id"));

                    agendas.add(agenda);

                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return agendas;

    }


}
