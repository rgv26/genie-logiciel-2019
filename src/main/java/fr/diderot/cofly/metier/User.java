package fr.diderot.cofly.metier;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class User extends Person {

    public User() {
        super();
    }

    public User(String email, byte[] password, String firstName, String lastName,
            char sex, String birthdate, String numTel) {
        super(email, password, firstName, lastName, sex, birthdate, numTel);
    }

    @Override
    public String toString() {
        return "User{" + super.toString() + "}";
    }

}
