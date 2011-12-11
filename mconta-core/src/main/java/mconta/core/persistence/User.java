/**
 * 
 */
package mconta.core.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Marc
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "USER")
public class User extends Auditable {
	
	@Id
	@GeneratedValue
	@Column(name = "USE_ID", nullable = false)
	protected Long use_id;
	
	@Column(name = "USE_NAME", length = 100)
	protected String use_name;
	
	@Column(name = "USE_PASSWORD", length = 100)
	protected String use_password;
	
	@Column(name = "USE_ENABLED", length = 1)
	protected Boolean use_enabled;
	
	//@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	//private Set<Group> use_group = new HashSet<Group>(0);
	
	public User() {
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

}
