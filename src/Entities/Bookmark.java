/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;



public class Bookmark implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
   
    private Date dateAdded;
    
    private int projectId;
    
    private int freelancerId;

    public Bookmark(Integer id, Date dateAdded, int projectId, int freelancerId) {
        this.id = id;
        this.dateAdded = dateAdded;
        this.projectId = projectId;
        this.freelancerId = freelancerId;
    }

    public Bookmark(int projectId, int freelancerId) {
        this.projectId = projectId;
        this.freelancerId = freelancerId;
    }
    
    



    public Bookmark(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
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
        if (!(object instanceof Bookmark)) {
            return false;
        }
        Bookmark other = (Bookmark) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Bookmark[ id=" + id + " ]";
    }
    
}
