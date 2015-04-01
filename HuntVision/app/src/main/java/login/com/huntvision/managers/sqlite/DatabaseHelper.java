package login.com.huntvision.managers.sqlite;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import login.com.huntvision.R;
import login.com.huntvision.models.Cliente;
import login.com.huntvision.models.Imagem;
import login.com.huntvision.models.Item;
import login.com.huntvision.models.ItemLocal;
import login.com.huntvision.models.Local;
import login.com.huntvision.models.Questionario;
import login.com.huntvision.models.Resposta;
import login.com.huntvision.models.TipoQuestionario;
import login.com.huntvision.models.Usuario;
import login.com.huntvision.models.Vistoria;
import login.com.huntvision.models.VistoriaResposta;

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

    public RuntimeExceptionDao<TipoQuestionario, String> getTipoQuestionarioRuntimeDAO() {
        if (tipoQuestionarioRuntimeDAO == null) {
            tipoQuestionarioRuntimeDAO = getRuntimeExceptionDao(TipoQuestionario.class);
        }
        return tipoQuestionarioRuntimeDAO;
    }


    public RuntimeExceptionDao<Imagem, String> getImagemRuntimeDAO() {
        if (imagemRuntimeDAO == null) {
            imagemRuntimeDAO = getRuntimeExceptionDao(Imagem.class);
        }
        return imagemRuntimeDAO;
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


}
