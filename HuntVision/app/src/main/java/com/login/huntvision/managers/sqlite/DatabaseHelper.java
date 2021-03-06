package com.login.huntvision.managers.sqlite;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import com.login.huntvision.R;
import com.login.huntvision.models.Acao;
import com.login.huntvision.models.Agenda;
import com.login.huntvision.models.Cliente;
import com.login.huntvision.models.Imagem;
import com.login.huntvision.models.Item;
import com.login.huntvision.models.ItemLocal;
import com.login.huntvision.models.Local;
import com.login.huntvision.models.Protocolo;
import com.login.huntvision.models.ProtocoloAcao;
import com.login.huntvision.models.Questionario;
import com.login.huntvision.models.Resposta;
import com.login.huntvision.models.TipoQuestionario;
import com.login.huntvision.models.Usuario;
import com.login.huntvision.models.Vistoria;
import com.login.huntvision.models.VistoriaResposta;

/**
 * Database helper class used to manage the creation and upgrading of your database. This class also usually provides
 * the DAOs used by the other classes.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "huntvision.db";
	private static final int DATABASE_VERSION = 1;

	private RuntimeExceptionDao<Cliente, String> clienteRuntimeDAO = null;

    private RuntimeExceptionDao<Usuario, String> usuarioRuntimeDAO = null;

    private RuntimeExceptionDao<Item, String> itemRuntimeDAO = null;

    private RuntimeExceptionDao<Local, String> localRuntimeDAO = null;

    private RuntimeExceptionDao<ItemLocal, String> itemLocalRuntimeDAO = null;

    private RuntimeExceptionDao<Questionario, String> questionarioRuntimeDAO = null;

    private RuntimeExceptionDao<Vistoria, Long> vistoriaRuntimeDAO = null;

    private RuntimeExceptionDao<VistoriaResposta, String> vistoriaRespostaRuntimeDAO = null;

    private RuntimeExceptionDao<TipoQuestionario, String> tipoQuestionarioRuntimeDAO = null;

    private RuntimeExceptionDao<Imagem, String> imagemRuntimeDAO = null;

    private RuntimeExceptionDao<Resposta, String> respostaRuntimeDAO = null;

    private RuntimeExceptionDao<Agenda, String> agendaRuntimeDAO = null;

    private RuntimeExceptionDao<Acao, String> acaoRuntimeDAO = null;
    private RuntimeExceptionDao<ProtocoloAcao, String> protocoloAcaoRuntimeDAO = null;

    private RuntimeExceptionDao<Protocolo, String> protocoloRuntimeDAO = null;


    public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
	}

	/**
	 * This is called when the database is first created. Usually you should call createTable statements here to create
	 * the tables that will store your data.
	 */
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onCreate");
			TableUtils.createTable(connectionSource, Cliente.class);
            TableUtils.createTable(connectionSource, Usuario.class);
            TableUtils.createTable(connectionSource, Item.class);
            TableUtils.createTable(connectionSource, Local.class);
            TableUtils.createTable(connectionSource, Questionario.class);
            TableUtils.createTable(connectionSource, TipoQuestionario.class);
            TableUtils.createTable(connectionSource, Vistoria.class);
            TableUtils.createTable(connectionSource, VistoriaResposta.class);
            TableUtils.createTable(connectionSource, ItemLocal.class);
            TableUtils.createTable(connectionSource, Imagem.class);
            TableUtils.createTable(connectionSource, Resposta.class);
            TableUtils.createTable(connectionSource, Agenda.class);
            TableUtils.createTable(connectionSource, Acao.class);
            TableUtils.createTable(connectionSource, Protocolo.class);

            TableUtils.createTable(connectionSource, ProtocoloAcao.class);


        } catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
	 * the various data to match the new version number.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onUpgrade");
			TableUtils.dropTable(connectionSource, Cliente.class, true);
            TableUtils.dropTable(connectionSource, Usuario.class, true);
            TableUtils.dropTable(connectionSource, Item.class, true);
            TableUtils.dropTable(connectionSource, Local.class, true);
            TableUtils.dropTable(connectionSource, Questionario.class, true);
            TableUtils.dropTable(connectionSource, TipoQuestionario.class, true);
            TableUtils.dropTable(connectionSource, Imagem.class, true);
            TableUtils.dropTable(connectionSource, Vistoria.class, true);
            TableUtils.dropTable(connectionSource, VistoriaResposta.class, true);
            TableUtils.dropTable(connectionSource, ItemLocal.class, true);
            TableUtils.dropTable(connectionSource, Resposta.class, true);
            TableUtils.dropTable(connectionSource, Agenda.class, true);
            TableUtils.dropTable(connectionSource, Acao.class, true);
            TableUtils.dropTable(connectionSource, Protocolo.class, true);
            TableUtils.dropTable(connectionSource, ProtocoloAcao.class, true);
            // after we drop the old databases, we create the new ones
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}

	public RuntimeExceptionDao<Cliente, String> getClienteRuntimeDAO() {
		if (clienteRuntimeDAO == null) {
            clienteRuntimeDAO = getRuntimeExceptionDao(Cliente.class);
		}
		return clienteRuntimeDAO;
	}

    public RuntimeExceptionDao<Usuario, String> getUsuarioRuntimeDAO() {
        if (usuarioRuntimeDAO == null) {
            usuarioRuntimeDAO = getRuntimeExceptionDao(Usuario.class);
        }
        return usuarioRuntimeDAO;
    }

    public RuntimeExceptionDao<Item, String> getItemRuntimeDAO() {
        if (itemRuntimeDAO == null) {
            itemRuntimeDAO  = getRuntimeExceptionDao(Item.class);
        }
        return itemRuntimeDAO;
    }

    public RuntimeExceptionDao<Local, String> getLocalRuntimeDAO() {
        if (localRuntimeDAO == null) {
            localRuntimeDAO = getRuntimeExceptionDao(Local.class);
        }
        return localRuntimeDAO;
    }

    public RuntimeExceptionDao<ItemLocal, String> getItemLocalRuntimeDAO() {
        if (itemLocalRuntimeDAO == null) {
            itemLocalRuntimeDAO = getRuntimeExceptionDao(ItemLocal.class);
        }
        return itemLocalRuntimeDAO;
    }

    public RuntimeExceptionDao<Questionario, String> getQuestionarioRuntimeDAO() {
        if (questionarioRuntimeDAO == null) {
            questionarioRuntimeDAO  = getRuntimeExceptionDao(Questionario.class);
        }
        return questionarioRuntimeDAO ;
    }

    public RuntimeExceptionDao<Vistoria, Long> getVistoriaRuntimeDAO() {
        if (vistoriaRuntimeDAO == null) {
            vistoriaRuntimeDAO = getRuntimeExceptionDao(Vistoria.class);
        }
        return vistoriaRuntimeDAO;
    }

    public RuntimeExceptionDao<VistoriaResposta, String> getVistoriaRespostaRuntimeDAO() {
        if (vistoriaRespostaRuntimeDAO == null) {
            vistoriaRespostaRuntimeDAO = getRuntimeExceptionDao(VistoriaResposta.class);
        }
        return vistoriaRespostaRuntimeDAO;
    }


    public RuntimeExceptionDao<Imagem, String> getImagemRuntimeDAO() {
        if (imagemRuntimeDAO == null) {
            imagemRuntimeDAO = getRuntimeExceptionDao(Imagem.class);
        }
        return imagemRuntimeDAO;
    }

    public RuntimeExceptionDao<TipoQuestionario, String> getTipoQuestionarioRuntimeDAO() {
        if (tipoQuestionarioRuntimeDAO == null) {
            tipoQuestionarioRuntimeDAO = getRuntimeExceptionDao(TipoQuestionario.class);
        }
        return tipoQuestionarioRuntimeDAO;
    }

    public RuntimeExceptionDao<Resposta, String> getRespostaRuntimeDAO() {
        if (respostaRuntimeDAO == null) {
            respostaRuntimeDAO = getRuntimeExceptionDao(Resposta.class);
        }
        return respostaRuntimeDAO;
    }


	@Override
	public void close() {
		super.close();
		clienteRuntimeDAO = null;
	}


    public RuntimeExceptionDao<Agenda, String> getAgendaRuntimeDAO() {
        if (agendaRuntimeDAO == null) {
            agendaRuntimeDAO = getRuntimeExceptionDao(Agenda.class);
        }
        return agendaRuntimeDAO;
    }

    public RuntimeExceptionDao<Acao, String> getAcaoRuntimeDAO() {
        return acaoRuntimeDAO;
    }

    public void setAcaoRuntimeDAO(RuntimeExceptionDao<Acao, String> acaoRuntimeDAO) {
        this.acaoRuntimeDAO = acaoRuntimeDAO;
    }

    public RuntimeExceptionDao<ProtocoloAcao, String> getProtocoloAcaoRuntimeDAO() {
        return protocoloAcaoRuntimeDAO;
    }

    public void setProtocoloAcaoRuntimeDAO(RuntimeExceptionDao<ProtocoloAcao, String> protocoloAcaoRuntimeDAO) {
        this.protocoloAcaoRuntimeDAO = protocoloAcaoRuntimeDAO;
    }

    public RuntimeExceptionDao<Protocolo, String> getProtocoloRuntimeDAO() {
        return protocoloRuntimeDAO;
    }

    public void setProtocoloRuntimeDAO(RuntimeExceptionDao<Protocolo, String> protocoloRuntimeDAO) {
        this.protocoloRuntimeDAO = protocoloRuntimeDAO;
    }
}
