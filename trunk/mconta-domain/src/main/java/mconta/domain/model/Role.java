/**
 * 
 */
package mconta.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Marc
 *
 */
@Entity
@Table(name = "ROLE")
public class Role implements Model {

	private static final long serialVersionUID = -263600385164623117L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROL_ID", nullable = false)
	private Long rol_id;
	
	@Column(name = "ROL_NAME", nullable = false, length = 25)
    private String rol_name;
	
	public Role() {
    }

    public Role(String rol_name) {
            this.rol_name = rol_name;
    }

	/**
	 * @return the rol_id
	 */
	public Long getRol_id() {
		return rol_id;
	}

	/**
	 * @param rol_id the rol_id to set
	 */
	public void setRol_id(Long rol_id) {
		this.rol_id = rol_id;
	}

	/**
	 * @return the rol_name
	 */
	public String getRol_name() {
		return rol_name;
	}

	/**
	 * @param rol_name the rol_name to set
	 */
	public void setRol_name(String rol_name) {
		this.rol_name = rol_name;
	}
	
	public String toString() { 
		StringBuilder build = new StringBuilder();
		
		return build.append("id: ").append(rol_id)
				.append("name: ").append(rol_name).toString();
	}

	@Override
	public void deHibernate() {
		// TODO Auto-generated method stub
		
	}

}
