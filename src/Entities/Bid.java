/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

public class Bid {

    private static final long serialVersionUID = 1L;

    private int id;

    private int minimalRate;

    private int deliveryTime;

    private int projectId;
    
    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    private int freelancerId;

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

    public Bid() {
    }

    public Bid(int id) {
        this.id = id;
    }

    public Bid(int minimalRate, int deliveryTime) {
        this.minimalRate = minimalRate;
        this.deliveryTime = deliveryTime;
    }

    public Bid(int id, int minimalRate, int deliveryTime, int projectId, int freelancerId) {
        this.id = id;
        this.minimalRate = minimalRate;
        this.deliveryTime = deliveryTime;
        this.freelancerId = freelancerId;
        this.projectId = projectId;
    }

    public Bid(int minimalRate, int deliveryTime, int freelancerId, int projectId) {
        this.minimalRate = minimalRate;
        this.deliveryTime = deliveryTime;
        this.freelancerId = freelancerId;
        this.projectId = projectId;
    }

    public Bid(int minimalRate, int deliveryTime, int projectId) {
        this.minimalRate = minimalRate;
        this.deliveryTime = deliveryTime;
        this.projectId = projectId;
    }

    public int getId() {
        return id;
    }



    public int getMinimalRate() {
        return minimalRate;
    }

    public void setMinimalRate(int minimalRate) {
        this.minimalRate = minimalRate;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    @Override
    public String toString() {
        return "Bid{" + "minimalRate=" + minimalRate + ", deliveryTime=" + deliveryTime + ", projectId=" + projectId + ", freelancerId=" + freelancerId + '}';
    }

}
