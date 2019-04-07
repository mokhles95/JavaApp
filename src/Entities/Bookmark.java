/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Tools.CurrentDate;
import java.io.Serializable;
import java.util.Date;



public class Bookmark implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int id;
   
    private Date dateAdded;
    
    private int projectId;
    
    private int freelancerId;
    
    

    public Bookmark(int id, int projectId, int freelancerId) {
        this.id = id;
        this.dateAdded = CurrentDate.getCurrentDate();
        this.projectId = projectId;
        this.freelancerId = freelancerId;
    }

    public Bookmark(int projectId, int freelancerId) {
        this.projectId = projectId;
        this.freelancerId = freelancerId;
    }
    
    



    public Bookmark(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
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
    public String toString() {
        return "Bookmark{" + "id=" + id + ", dateAdded=" + dateAdded + ", projectId=" + projectId + ", freelancerId=" + freelancerId + '}';
    }


    
}
