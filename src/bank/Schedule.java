package bank;

public class Schedule extends BaseEntity{
	
	private String mon;
	private String tue;
	private String wed;
	private String thu;
	private String fri;
	private String sat;
	private String sun;
	
//	private Set<Department> departments;
	
//	public Set<Department> getDepartments() {
//		if (this.departments == null) {
//			this.departments = new HashSet<Department>();
//		}
//		return this.departments;
//	}
//
//
//	public void setDepartments(Set<Department> departments) {
//		if (this.departments == null) {
//			this.departments = new HashSet<Department>();
//		}
//		this.departments.addAll(departments);
//	}
	public void setMon(String mon) {
	    this.mon = mon;
	}

	public String getMon() {
	    return this.mon;
	}
	public void setTue(String tue) {
	    this.tue = tue;
	}

	public String getTue() {
	    return this.tue;
	}
	public void setWed(String wed) {
	    this.wed = wed;
	}

	public String getWed() {
	    return this.wed;
	}
	public void setThu(String thu) {
	    this.thu = thu;
	}

	public String getThu() {
	    return this.thu;
	}
	public void setFri(String fri) {
	    this.fri = fri;
	}

	public String getFri() {
	    return this.fri;
	}
	public void setSat(String sat) {
	    this.sat = sat;
	}

	public String getSat() {
	    return this.sat;
	}
	public void setSun(String sun) {
	    this.sun = sun;
	}

	public String getSun() {
	    return this.sun;
	}

}
