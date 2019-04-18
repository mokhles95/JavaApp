/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Implementation;

import Entities.Project;
import Services.Interface.ProjectServiceInterface;
import Tools.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class ProjectService implements ProjectServiceInterface {
    private static PreparedStatement preparedStatement;
    private static Statement statement;
    private static ResultSet resultSet;
    static Connection cnx = DataSource.dbConnexion();
    private static final Logger logger = Logger.getLogger(ProjectService.class.getName());


    @Override
    public ArrayList<Project> displayProject() {
        ArrayList<Project> projects = new ArrayList();
        //Array values[] = new Array();
        try {
            String query = "SELECT * FROM project";
            statement = cnx.createStatement();

            resultSet = statement.executeQuery(query);

            if (resultSet.next() == false) {
                System.out.println("ResultSet in empty in Java");
            } else {
                do {
                    //for (int i = 1; i <= resultMetaData.getColumnCount(); i++) {
                    /*minRate = resultSet.getInt("minimalRate");
                    deliveryTime = resultSet.getInt("deliveryTime");
                    projectId = resultSet.getInt("project_id");
                    freelancerId = resultSet.getInt("freelancer_id");

                    id = resultSet.getInt("id");*/
                    Project project = new Project();
                    projects.add(project);

                    //Bid bid = (Bid) resultSet.getObject(i);
                    //}  
                } while (resultSet.next());
                //System.out.println(bids);

                return projects;
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            logger.log(Level.SEVERE, exception.getMessage());

        }
        return projects;
    }

}
