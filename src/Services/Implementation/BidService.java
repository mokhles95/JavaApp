/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Implementation;

import Entities.Bid;
import Entities.Project;
import Services.Interface.BidServiceInterface;
import Tools.DataSource;
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
public class BidService implements BidServiceInterface {

    private static PreparedStatement preparedStatement;
    private static Statement statement;
    private static ResultSet resultSet;
    static Connection cnx = DataSource.dbConnexion();
    private static final Logger logger = Logger.getLogger(BidService.class.getName());

    private static BidService bidServiceInstance;

    private BidService() {
    }

    public static BidService getInstance() {
        if (bidServiceInstance == null) {
            bidServiceInstance = new BidService();
        }
        return bidServiceInstance;
    }

    @Override
    public Bid findById(int id) {
        try {
            String query = "SELECT * FROM bid WHERE id = '" + id + "'";
            preparedStatement = cnx.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next() == false) {
                System.out.println("ResultSet in empty in Java");
            } else {
                do {
                    Bid bid = new Bid(resultSet.getInt("id"), resultSet.getInt("minimalRate"), resultSet.getInt("deliveryTime"), resultSet.getInt("project_id"), resultSet.getInt("freelancer_id"));
                    return bid;

                } while (resultSet.next());
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            logger.log(Level.SEVERE, exception.getMessage());

        }
        return null;

    }

    public Project findProjectById(int id) {
        Project project;
        try {
            String query = "SELECT projectName,minBudget,maxBudget,validityPeriod FROM project WHERE id = '" + id + "'";
            preparedStatement = cnx.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next() == false) {
                System.out.println("ResultSet in empty in Java");
            } else {
                do {
                    project = new Project(id, resultSet.getString("projectName"), resultSet.getInt("minBudget"), resultSet.getInt("maxBudget"), resultSet.getDate("validityPeriod"));
                    return project;
                } while (resultSet.next());
            }
        } catch (SQLException exception) {
            System.out.println( exception.getMessage());

        }
        return null;

    }

    public static ArrayList<Bid> displayBids() {
        ArrayList<Bid> bids = new ArrayList();
        try {
            String query = "SELECT id,minimalRate,deliveryTime,freelancer_id,project_id FROM bid";
            statement = cnx.createStatement();
            resultSet = statement.executeQuery(query);

            if (resultSet.next() == false) {
                System.out.println("ResultSet in empty in Java");
            } else {
                do {
                    Bid bid = new Bid(resultSet.getInt("id"), resultSet.getInt("minimalRate"), resultSet.getInt("deliveryTime"), resultSet.getInt("project_id"),resultSet.getInt("freelancer_id"));
                    bids.add(bid);
 
                } while (resultSet.next());

                return bids;
            }
        } catch (SQLException exception) {
            System.out.println("lala" + exception.getMessage());
            logger.log(Level.SEVERE, exception.getMessage());

        }
        return bids;
    }

    @Override
    public boolean addBid(Bid bid) {
        try {
            cnx.setAutoCommit(false);
            String query = "INSERT INTO bid VALUES (default,?,?,?,?) ";
            preparedStatement = cnx.prepareStatement(query);
            int counter = 1;
            preparedStatement.setInt(counter++, bid.getFreelancerId());
            preparedStatement.setInt(counter++, bid.getProjectId());
            preparedStatement.setInt(counter++, bid.getMinimalRate());
            preparedStatement.setInt(counter++, bid.getDeliveryTime());
            preparedStatement.executeUpdate();
            cnx.commit();
            return true;

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            logger.log(Level.SEVERE, exception.getMessage());

        }

        return false;
    }

    @Override
    public boolean deleteBid(int bidId) {
        try {
            String query = "DELETE FROM bid WHERE id = '" + bidId + "'";
            preparedStatement = cnx.prepareStatement(query);
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            logger.log(Level.SEVERE, exception.getMessage());

        }
        return false;
    }

    @Override
    public boolean updateBid(int bidId, int updateMinRat, int updateDeliveryTime) {
        try {
            String query = "UPDATE bid SET minimalRate = '" + updateMinRat + "',deliveryTime = '" + updateDeliveryTime + "' WHERE id = '" + bidId + "'";

            preparedStatement = cnx.prepareStatement(query);
            preparedStatement.executeUpdate();
            System.out.println("bid updated");
            return true;

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            logger.log(Level.SEVERE, exception.getMessage());
            return false;
        }

    }

}
