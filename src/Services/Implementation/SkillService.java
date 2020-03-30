/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Implementation;

import Entities.Bid;
import Entities.Project;
import Services.Interface.BidServiceInterface;
import Services.Interface.SkillServiceInterface;
import Tools.DataSource;
import Tools.UserSessions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class SkillService {

    private static PreparedStatement preparedStatement;
    private static Statement statement;
    private static ResultSet resultSet;
    static Connection cnx = DataSource.dbConnexion();
    private static final Logger logger = Logger.getLogger(SkillService.class.getName());

    private static SkillService skillServiceInstance;

    private SkillService() {
    }

    public static SkillService getInstance() {
        if (skillServiceInstance == null) {
            skillServiceInstance = new SkillService();
        }
        return skillServiceInstance;
    }

    public static ArrayList<String> displaySkills() {
        ArrayList<String> skills = new ArrayList();
        try {
            String query = "SELECT skill_name FROM myskills WHERE freelancer_id = '" + UserSessions.getInstance().getLoggedInUser().getId() + "'";
            statement = cnx.createStatement();
            resultSet = statement.executeQuery(query);

            if (resultSet.next() == false) {
                System.out.println("ResultSet in empty in Java");
            } else {
                do {
                    skills.add(resultSet.getString("skill_name"));
 
                } while (resultSet.next());

                return skills;
            }
        } catch (SQLException exception) {
            logger.log(Level.SEVERE, exception.getMessage());

        }
        return skills;
    }


    public boolean deleteSkill(String name) {
        try {
            String query = "DELETE FROM myskills WHERE skill_name = '" + name + "'";
            preparedStatement = cnx.prepareStatement(query);
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            logger.log(Level.SEVERE, exception.getMessage());

        }
        return false;
    }



    public boolean addSkill(String skill) {

        try {
            cnx.setAutoCommit(false);
            String query = "INSERT INTO myskills VALUES (default,?,?) ";
            preparedStatement = cnx.prepareStatement(query);
            int counter = 1;
            preparedStatement.setInt(counter++, UserSessions.getInstance().getLoggedInUser().getId());
            preparedStatement.setString(counter++, skill);
            preparedStatement.executeUpdate();
            cnx.commit();
            return true;

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            logger.log(Level.SEVERE, exception.getMessage());

        }

        return false;
    }

}
