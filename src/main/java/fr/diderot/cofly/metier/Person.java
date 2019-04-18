package fr.diderot.cofly.metier;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public abstract class Person {

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    protected UUID id;
    @NotNull
    @JsonSerialize
    protected String email;
    @NotNull
    protected byte[] password;
    @NotNull
    protected String firstName;
    @NotNull
    protected String lastName;
    protected char sex;
    protected String birthdate;
    protected String numTel;
    protected String token;

    public Person() {

    }

    public Person(String email, byte[] password, String firstName,
            String lastName, char sex, String birthdate, String numTel) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.birthdate = birthdate;
        this.numTel = numTel;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.email);
        hash = 89 * hash + Arrays.hashCode(this.password);
        hash = 89 * hash + Objects.hashCode(this.firstName);
        hash = 89 * hash + Objects.hashCode(this.lastName);
        hash = 89 * hash + this.sex;
        hash = 89 * hash + Objects.hashCode(this.birthdate);
        hash = 89 * hash + Objects.hashCode(this.numTel);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (this.sex != other.sex) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.birthdate, other.birthdate)) {
            return false;
        }
        if (!Objects.equals(this.numTel, other.numTel)) {
            return false;
        }
        return Arrays.equals(this.password, other.password);
    }

    @Override
    public String toString() {
        return "email=" + email + ", password=" + Arrays.toString(password) + ", firstName="
                + firstName + ", lastName=" + lastName + ", sex=" + sex
                + ", birthdate=" + birthdate + ", numTel=" + numTel;
    }

}
