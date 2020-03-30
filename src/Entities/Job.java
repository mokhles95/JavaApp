/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;




public class Job  {

    
   
    private Integer id;
    
    
    private String titre;
    
    private String type;
    
    private String location;
    
    private double minSalary;
    
    private double maxSalary;
    
    private String description;
    
    private int category_id;
    
    private int employer_Id;
    


    public Job() {
    }

    public Job(Integer id) {
        this.id = id;
    }

    public Job(Integer id, String title, String type, String location, double minSalary, double maxSalary, String description) {
        this.id = id;
        this.titre = title;
        this.type = type;
        this.location = location;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.description = description;
     
        
    }
    
    public Job(String title, String type, String location, double minSalary, double maxSalary, String description,int employer) {
        
        this.titre = title;
        this.type = type;
        this.location = location;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.description = description;
        this.employer_Id = employer;

    }
    
    public Job(String title, String type, String location, double minSalary, double maxSalary, String description) {
        
        this.titre = title;
        this.type = type;
        this.location = location;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.description = description;
    }

   
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getTitre() {
        return titre;
    }

    public void setTitre(String title) {
        this.titre = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getMinSal() {
        return minSalary;
    }

    public void setMinSal(double minSalary) {
        this.minSalary = minSalary;
    }

    public double getMaxSal() {
        return maxSalary;
    }

    public void setMaxSal(double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   
  

   

    

   
    

    

    public int getEmployer_id() {
        return employer_Id;
    }

    public void setEmployer_id(int employer_id) {
        this.employer_Id = employer_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
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
        if (!(object instanceof Job)) {
            return false;
        }
        Job other = (Job) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Job[ id=" + id + " ]";
    }
    
}
