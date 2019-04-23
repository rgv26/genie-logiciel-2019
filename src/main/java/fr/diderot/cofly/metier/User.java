package fr.diderot.cofly.metier;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class User extends Person {

    public User() {
        super();
    }

    public User(String email, String password, String firstName, String lastName, String birthDate, char sex,
                String numTel, String profileImage) {
        super(email, password, firstName, lastName, birthDate, sex, numTel, profileImage);

    }

    @Override
    public String toString() {
        return "User{" + super.toString() + "}";
    }

}
