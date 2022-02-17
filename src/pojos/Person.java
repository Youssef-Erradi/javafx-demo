package pojos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Person {
	private final String id;
	private String fullName;
	private String sex;
	private Date birthday;

	public Person() {
		this.id = UUID.randomUUID().toString().split("-")[0];
	}

	public Person(String fullName, String sex, Date birthday) {
		this();
		this.fullName = fullName;
		this.sex = sex;
		this.birthday = birthday;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}
	
	public String getFormattedBirthday() {
		return new SimpleDateFormat("dd/MM/yyyy").format(birthday);
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthday, fullName, id, sex);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(birthday, other.birthday) && Objects.equals(fullName, other.fullName)
				&& Objects.equals(id, other.id) && Objects.equals(sex, other.sex);
	}

	@Override
	public String toString() {
		return "id : " + id + "\nNom complet : " + fullName + "\nSexe : " + sex + "\nDate de naissance : "
				+ getFormattedBirthday();
	}

}
