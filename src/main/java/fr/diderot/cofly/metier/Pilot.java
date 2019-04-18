package fr.diderot.cofly.metier;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pilot")
public class Pilot extends Person {

    private String experience;
    private String qualification;
    private String licence;
    private int flyingHours;

    public Pilot() {
        super();
    }

    public Pilot(String email, byte[] password, String firstName, String lastName,
            char sex, String birthdate, String numTel) {
        super(email, password, firstName, lastName, sex, birthdate, numTel);
    }

    public Pilot(String email, byte[] password, String firstName, String lastName,
            char sex, String birthdate, String numTel, String experience,
            String qualification, String licence, int flyingHours) {
        this(email, password, firstName, lastName, sex, birthdate, numTel);
        this.experience = experience;
        this.qualification = qualification;
        this.licence = licence;
        this.flyingHours = flyingHours;
    }

    /**
     * @return the experience
     */
    public String getExperience() {
        return experience;
    }

    /**
     * @param experience the experience to set
     */
    public void setExperience(String experience) {
        this.experience = experience;
    }

    /**
     * @return the qualification
     */
    public String getQualification() {
        return qualification;
    }

    /**
     * @param qualification the qualification to set
     */
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    /**
     * @return the licence
     */
    public String getLicence() {
        return licence;
    }

    /**
     * @param licence the licence to set
     */
    public void setLicence(String licence) {
        this.licence = licence;
    }

    /**
     * @return the flyingHours
     */
    public int getFlyingHours() {
        return flyingHours;
    }

    /**
     * @param flyingHours the flyingHours to set
     */
    public void setFlyingHours(int flyingHours) {
        this.flyingHours = flyingHours;
    }

}
