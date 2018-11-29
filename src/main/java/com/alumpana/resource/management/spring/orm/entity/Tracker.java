package com.alumpana.resource.management.spring.orm.entity;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class Tracker {

	private String creadedBy;
	private String modifiedBy;
	@Temporal(TemporalType.DATE)
	private Date createdOn;
	@Temporal(TemporalType.DATE)
	private Date modifiedOn;

	public String getCreadedBy() {
		return creadedBy;
	}

	public void setCreadedBy(String creadedBy) {
		this.creadedBy = creadedBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	@Override
	public String toString() {
		return "Tracker [creadedBy=" + creadedBy + ", modifiedBy=" + modifiedBy + ", createdOn=" + createdOn
				+ ", modifiedOn=" + modifiedOn + "]";
	}

}