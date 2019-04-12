/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Implementation;

import Entities.Bid;
import Services.Interface.BidServiceInterface;
import Tools.DataSource;
import java.sql.Array;
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
    private static final Logger logger = Logger.getLogger(AuthenticationService.class.getName());

    /*
    private static BidService bidServiceInstance;
    
    private BidService() {
    }

    public static BidService getInstance() {
        if (bidServiceInstance == null) {
            bidServiceInstance = new BidService();
        }
        return bidServiceInstance;
    }
     */
    @Override
    public ArrayList<Bid> displayBids() {
        ArrayList<Bid> bids = new ArrayList();
        //Array values[] = new Array();
        try {
            String query = "SELECT * FROM bid";
            statement = cnx.createStatement();
            //preparedStatement.setString(counter++, email);
            //preparedStatement.setString(counter++, username);
            resultSet = statement.executeQuery(query);
            ResultSetMetaData resultMetaData = resultSet.getMetaData();
            
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
                    Bid bid = new Bid(resultSet.getInt("id"), resultSet.getInt("minimalRate"), resultSet.getInt("deliveryTime"), resultSet.getInt("project_id"), resultSet.getInt("freelancer_id"));
                    bids.add(bid);

                    //Bid bid = (Bid) resultSet.getObject(i);
                    //}  
                } while (resultSet.next());
                //System.out.println(bids);

                return bids;
            }
        } 
        catch (SQLException exception) {
            System.out.println(exception.getMessage());
            logger.log(Level.SEVERE, exception.getMessage());

        }
        return bids;
    }

    @Override
    public boolean addBid(Bid bid
    ) {
        try {
            cnx.setAutoCommit(false);
            String query = "INSERT INTO bid VALUES (default,?,?,?,?) ";
            preparedStatement = cnx.prepareStatement(query);
            int counter = 1;
            preparedStatement.setInt(counter++, bid.getMinimalRate());
            preparedStatement.setInt(counter++, bid.getDeliveryTime());
            preparedStatement.setInt(counter++, bid.getFreelancerId());
            preparedStatement.setInt(counter++, bid.getProjectId());
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
    public boolean deleteBid(Bid bid) {
        try {
            //cnx.setAutoCommit(false);
            String query = "DELETE FROM bid WHERE id = ?";
            preparedStatement = cnx.prepareStatement(query);
            //int counter = 1;
            preparedStatement.setInt(1, bid.getId());
            //preparedStatement.setString(counter++, username);
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            logger.log(Level.SEVERE, exception.getMessage());

        }
        return false;
    }

    @Override
    public boolean updateBid(Bid bid
    ) {
        try {
            //cnx.setAutoCommit(false);
            String query = "INSERT INTO bid VALUES (?,?,?,?) WHERE id = ? ";
            preparedStatement = cnx.prepareStatement(query);
            int counter = 1;
            preparedStatement.setInt(counter++, bid.getFreelancerId());
            preparedStatement.setInt(counter++, bid.getProjectId());
            preparedStatement.setInt(counter++, bid.getDeliveryTime());
            preparedStatement.setInt(counter++, bid.getMinimalRate());
            //resultSet = preparedStatement.executeUpdate();
            return true;

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            logger.log(Level.SEVERE, exception.getMessage());

        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();

                } catch (SQLException ex) {
                    Logger.getLogger(BidService.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (cnx != null) {
                try {
                    cnx.close();

                } catch (SQLException ex) {
                    Logger.getLogger(BidService.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return false;
    }

}
