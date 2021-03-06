/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;

 
public class Portfolio implements Serializable {

    private static final long serialVersionUID = 1L;
   
    private Integer id;
    
    private String skills;
    
    private String description;
    
    private String previousExperiences;
    
    private String importedFile;
    
    private Date updatedAt;
    
    private int freelancerId;

    public Portfolio() {
    }

    public Portfolio(Integer id) {
        this.id = id;
    }

    public Portfolio(Integer id, String skills, String description, String previousExperiences) {
        this.id = id;
        this.skills = skills;
        this.description = description;
        this.previousExperiences = previousExperiences;
    }

    /**
     *
     * @param skills
     * @param description
     * @param previousExperiences
     * @param freelancer_id
     */
    public Portfolio(String skills, String description, String previousExperiences,int freelancer_id) {
        this.skills = skills;
        this.description = description;
        this.previousExperiences = previousExperiences;
        this.freelancerId=freelancer_id;
    }
    
     public Portfolio(String skills, String description, String previousExperiences) {
        this.skills = skills;
        this.description = description;
        this.previousExperiences = previousExperiences;
    }
    
    
    
    

   
    
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPreviousExperiences() {
        return previousExperiences;
    }

    public void setPreviousExperiences(String previousExperiences) {
        this.previousExperiences = previousExperiences;
    }

    public String getImportedFile() {
        return importedFile;
    }

    public void setImportedFile(String importedFile) {
        this.importedFile = importedFile;
    }

    

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(int freelancerId) {
        this.freelancerId = freelancerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Portfolio)) {
            return false;
        }
        Portfolio other = (Portfolio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Portfolio[ id=" + id + " ]";
    }
    
}
