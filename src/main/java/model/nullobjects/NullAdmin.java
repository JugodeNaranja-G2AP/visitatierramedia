package model.nullobjects;

import model.Admin;

public class NullAdmin extends Admin {
	
	public static Admin build() {
		return new NullAdmin();
	}
	
	public NullAdmin() {
		super(0, "", "", "");
	}
	
	public boolean isNull() {
		return true;
	}

}
