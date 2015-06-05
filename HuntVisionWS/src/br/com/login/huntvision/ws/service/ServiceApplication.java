package br.com.login.huntvision.ws.service;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

public class ServiceApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	public ServiceApplication() {

 		this.singletons.add(new ClienteService());

 		this.singletons.add(new ItensLocaisService());

 		this.singletons.add(new GrupoUsuarioService());

 		this.singletons.add(new ItemService());

 		this.singletons.add(new LocaisService());

 		this.singletons.add(new MenuService());

 		this.singletons.add(new PermissaoService());

 		this.singletons.add(new QuestionariosService());

 		this.singletons.add(new RespostasService());

 		this.singletons.add(new TipoQuestionariosService());

 		this.singletons.add(new UsuarioService());
 		
 		this.singletons.add(new VistoriaService());
 		
 		this.singletons.add(new ProtocoloService());

	}

@	Override
	public Set<Class<?>> getClasses() {
		return this.empty;
	}

@	Override
	public Set<Object> getSingletons() {
		return this.singletons;
	}

}