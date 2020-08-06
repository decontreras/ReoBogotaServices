package RETOBOGOTA.Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l")
})
public class Location {
	
	@Id
	private String name;
	private int area_m2;
	private String parent_location;
	
	public Location(String name, int area_m2, String parent_location) {
		super();
		this.name = name;
		this.area_m2 = area_m2;
		this.parent_location = parent_location;
	}
	
	public Location() {
		super();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getArea_m2() {
		return area_m2;
	}
	public void setArea_m2(int area_m2) {
		this.area_m2 = area_m2;
	}
	public String getParent_location() {
		return parent_location;
	}
	public void setParent_location(String parent_location) {
		this.parent_location = parent_location;
	}

	@Override
	public String toString() {
		return "{name:" + name + ", area_m2:" + area_m2 + ", parent_location:" + parent_location + "}";
	}
}
