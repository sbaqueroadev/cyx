
/* Archivo: TeacherInterface.java
* Fecha: 21/12/2017
* Todos los derechos de propiedad intelectual e industrial sobre esta
* aplicacion son de propiedad exclusiva de Sergio Baquero Ariza
* Su uso, alteracion, reproduccion o modificacion sin la debida
* consentimiento por escrito de Sergio Baquero Ariza.
* 
* Este programa se encuentra protegido por las disposiciones de la
* Ley 23 de 1982 y demas normas concordantes sobre derechos de autor y
* propiedad intelectual. Su uso no autorizado dará lugar a las sanciones
* previstas en la Ley.
*/
	
package co.com.sbaqueroadev.cyxtera.model;

import co.com.sbaqueroadev.cyxtera.model.implementation.Role;

/*
* @author: gasdsba - sbaqueroa@gmail.com
* TeacherInterface:  
*/
public interface RoleInterface {
	public Role findByName(String name);
}
