/**
 * 
 */
package mconta.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author Marc
 *
 */
@Entity
@Table(name = "ROLE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Role implements Model {

	private static final long serialVersionUID = -263600385164623117L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROL_ID", nullable = false)
	private Long rol_id;
	
	@Column(name = "ROL_NAME", nullable = false, length = 25)
    private String rol_name;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.REMOVE, 
			mappedBy = "use_roles")
	private List<User> rol_users;
	
	/**
	 * @return the rol_users
	 */
	public List<User> getRol_users() {
		return rol_users;
	}

	/**
	 * @param rol_users the rol_users to set
	 */
	public void setRol_users(List<User> rol_users) {
		this.rol_users = rol_users;
	}

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
		ArrayList<User> arrayList = new ArrayList<User>();
		
		for(User user: getRol_users()) {
			user.setUse_roles(null);
			arrayList.add(user);
		}
		
		setRol_users(arrayList);
		
	}

}
