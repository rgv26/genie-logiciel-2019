package fr.diderot.cofly.metier;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public abstract class Person {

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    protected long id;
    @NotNull
    @JsonSerialize
    protected String email;
    @NotNull
    protected String password;
    @NotNull
    protected String firstName;
    @NotNull
    protected String lastName;
    protected String profileImage;
    protected String birthDate;
    protected String numTel;
    protected char sex;
    protected String token;
    protected int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person() {

    }

    public Person(String email, String password, String firstName, String lastName, String birthDate, char sex,
            String numTel, String profileImage) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.numTel = numTel;
        this.profileImage = profileImage;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public char getSex() {
        return this.sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
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

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {

        this.birthDate = birthDate;

    }

    @Override
    public String toString() {
        return "email='" + email + '\'' + ", password='" + new String(password) + '\'' + ", firstName='" + firstName
                + '\'' + ", lastName='" + lastName + '\'' + ", sex=" + sex + (token != null ? '\'' + ", token='" + token : "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Person user = (Person) o;
        return Objects.equals(email, user.email) && Objects.equals(password, user.password)
                && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName)
                && Objects.equals(birthDate, user.birthDate) && Objects.equals(numTel, user.numTel)
                && Objects.equals(sex, user.sex);
    }

    @Override
    public int hashCode() {

        return Objects.hash(email, password, firstName, lastName, numTel, sex, birthDate);

    }
}
