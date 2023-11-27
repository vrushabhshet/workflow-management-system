package com.workflow.model;

import javax.persistence.*;

@Entity
@Table(name = "employeeLeaves")
public class EmployeeLeave {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "employee_id")
	private long employeeId;

	@Column(name = "from_date")
	private String fromDate;

	@Column(name = "to_date")
	private String toDate;

	@Column(name = "reason")
	private String reason;

	public EmployeeLeave() {
		super();
	}

	public EmployeeLeave(long id, long employeeId, String fromDate, String toDate, String reason) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.reason = reason;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "EmployeeLeave [id=" + id + ", employeeId=" + employeeId + ", fromDate=" + fromDate + ", toDate="
				+ toDate + ", reason=" + reason + "]";
	}

}
