/**
 * 
 */
package mconta.core.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Marc
 *
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class Auditable implements Model {
	
	@Column(name = "AUD_CREATEDBY", length = 100)
	protected String aud_createdBy;
	
	@Column(name = "AUD_CREATEDON")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date aud_createdOn;
	
	@Column(name = "AUD_MODIFIEDBY", length = 100)
	protected String aud_modifiedBy;
	
	@Column(name = "AUD_MODIFIEDON")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date aud_modifiedOn;

	/**
	 * @return the aud_createdBy
	 */
	public String getAud_createdBy() {
		return aud_createdBy;
	}

	/**
	 * @param aud_createdBy the aud_createdBy to set
	 */
	public void setAud_createdBy(String aud_createdBy) {
		this.aud_createdBy = aud_createdBy;
	}

	/**
	 * @return the aud_createdOn
	 */
	public Date getAud_createdOn() {
		return aud_createdOn;
	}

	/**
	 * @param aud_createdOn the aud_createdOn to set
	 */
	public void setAud_createdOn(Date aud_createdOn) {
		this.aud_createdOn = aud_createdOn;
	}

	/**
	 * @return the aud_modifiedBy
	 */
	public String getAud_modifiedBy() {
		return aud_modifiedBy;
	}

	/**
	 * @param aud_modifiedBy the aud_modifiedBy to set
	 */
	public void setAud_modifiedBy(String aud_modifiedBy) {
		this.aud_modifiedBy = aud_modifiedBy;
	}

	/**
	 * @return the aud_modifiedOn
	 */
	public Date getAud_modifiedOn() {
		return aud_modifiedOn;
	}

	/**
	 * @param aud_modifiedOn the aud_modifiedOn to set
	 */
	public void setAud_modifiedOn(Date aud_modifiedOn) {
		this.aud_modifiedOn = aud_modifiedOn;
	}
	
}
