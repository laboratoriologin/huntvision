package com.login.huntvision.model;

import javax.annotation.PostConstruct;



import br.com.topsys.database.hibernate.TSActiveRecordAb;


public final class Home  extends TSActiveRecordAb<Home> { 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	

	public Home(){}

	public Home(String id){
		this.id = Long.valueOf(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}