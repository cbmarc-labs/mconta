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
@Table(name = "USERGROUP")
public class UserGroup extends Auditable {
	
	@Id
	@GeneratedValue
	@Column(name = "UGR_ID", nullable = false)
	private Long ugr_id;
	
	@Column(name = "UGR_NAME", length = 100)
	private String ugr_name;

	@Column(name = "UGR_ENABLED", length = 1)
	private Boolean ugr_enabled;
	
	public UserGroup() {
	}

	/**
	 * @return the ugr_id
	 */
	public Long getUgr_id() {
		return ugr_id;
	}

	/**
	 * @param ugr_id the ugr_id to set
	 */
	public void setUgr_id(Long ugr_id) {
		this.ugr_id = ugr_id;
	}

	/**
	 * @return the ugr_name
	 */
	public String getUgr_name() {
		return ugr_name;
	}

	/**
	 * @param ugr_name the ugr_name to set
	 */
	public void setUgr_name(String ugr_name) {
		this.ugr_name = ugr_name;
	}

	/**
	 * @return the ugr_enabled
	 */
	public Boolean getUgr_enabled() {
		return ugr_enabled;
	}

	/**
	 * @param ugr_enabled the ugr_enabled to set
	 */
	public void setUgr_enabled(Boolean ugr_enabled) {
		this.ugr_enabled = ugr_enabled;
	}
	
}
