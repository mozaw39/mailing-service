package org.service.mailing.modal;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class SenderInfo {
    private String address;
    private String password;
    private String firstName;
    private String lastName;
    private String subject;
    private String poste;
    private String body;


    public SenderInfo(String address, String password, String firstName, String lastName, String subject, String poste, String body) {
        this.address = address;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
        this.poste = poste;
        this.body = body;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public String getSubject() {
        return subject;
    }

    public String getPoste() {
        return poste;
    }

    public String getBody() {
        return body;
    }

    public SenderInfo() {
    }

    @Override
    public String toString() {
        return "SenderInfo{" +
                "address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", subject='" + subject + '\'' +
                ", poste='" + poste + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
