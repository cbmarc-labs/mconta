/**
 * 
 */
package mconta.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author Marc
 * 
 */
@Entity
@Table(name = "USER")
public class User implements Model {

	private static final long serialVersionUID = -5371370414590720273L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USE_ID", nullable = false)
	protected Long use_id;

	@Column(name = "USE_NAME", nullable = false, length = 25)
	protected String use_name;

	@Column(name = "USE_PASSWORD", nullable = false, length = 25)
	protected String use_password;

	@Column(name = "USE_ENABLED", length = 1)
	protected Boolean use_enabled;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="USER_ROLE",
		joinColumns={ @JoinColumn(name="USE_ID", referencedColumnName="USE_ID") },
		inverseJoinColumns={ @JoinColumn(name="ROL_ID", referencedColumnName="ROL_ID") })
	protected List<Role> use_roles;

	public User() {
	}

	public User(String use_name, String use_password, Boolean use_enabled,
			List<Role> use_roles) {
		this.use_name = use_name;
		this.use_password = use_password;
		this.use_enabled = use_enabled;
		this.use_roles = use_roles;
	}

	/**
	 * @return the use_id
	 */
	public Long getUse_id() {
		return use_id;
	}

	/**
	 * @param use_id the use_id to set
	 */
	public void setUse_id(Long use_id) {
		this.use_id = use_id;
	}

	/**
	 * @return the use_name
	 */
	public String getUse_name() {
		return use_name;
	}

	/**
	 * @param use_name the use_name to set
	 */
	public void setUse_name(String use_name) {
		this.use_name = use_name;
	}

	/**
	 * @return the use_password
	 */
	public String getUse_password() {
		return use_password;
	}

	/**
	 * @param use_password the use_password to set
	 */
	public void setUse_password(String use_password) {
		this.use_password = use_password;
	}

	/**
	 * @return the use_enabled
	 */
	public Boolean getUse_enabled() {
		return use_enabled;
	}

	/**
	 * @param use_enabled the use_enabled to set
	 */
	public void setUse_enabled(Boolean use_enabled) {
		this.use_enabled = use_enabled;
	}

	/**
	 * @return the use_role
	 */
	public List<Role> getUse_roles() {
		return use_roles;
	}

	/**
	 * @param use_role the use_role to set
	 */
	public void setUse_roles(List<Role> use_roles) {
		this.use_roles = use_roles;
	}
	
	public void deHibernate() {
		ArrayList<Role> arrayList = new ArrayList<Role>();
		
		for(Role role: getUse_roles()) {
			role.setRol_users(null);
			arrayList.add(role);
		}
		
		setUse_roles(arrayList);
	}
	
	public String toString() { 
		StringBuilder build = new StringBuilder();
		
		return build.append("id: ").append(use_id)
				.append("name: ").append(use_name).toString();
	}

}
