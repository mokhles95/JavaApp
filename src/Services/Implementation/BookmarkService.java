/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Implementation;

import Entities.Bid;
import Entities.Bookmark;
import Entities.Project;
import Entities.User;
import Services.Interface.BookmarkServiceInterface;
import Tools.CurrentDate;
import Tools.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class BookmarkService implements BookmarkServiceInterface {

    private static PreparedStatement preparedStatement;
    private static Statement statement;
    private static ResultSet resultSet;
    Connection cnx = DataSource.dbConnexion();
    private static final Logger logger = Logger.getLogger(AuthenticationService.class.getName());

    /*private static BookmarkService bookmarkServiceInstance;

    
    private BookmarkService(){}
    
    public static BookmarkService getInstance(){
        if(bookmarkServiceInstance==null) 
            bookmarkServiceInstance=new BookmarkService();
        return bookmarkServiceInstance;
    }
     */
    @Override
    public ArrayList<Bookmark> displayBookmarks() {
        ArrayList<Bookmark> bookmarks = new ArrayList();
        try {
            cnx.setAutoCommit(false);
            String query = "SELECT * FROM bookmark";
            statement = cnx.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next() == false) {
                System.out.println("ResultSet in empty in Java");
            } else {
                do {
                    //for (int i = 1; i <= resultMetaData.getColumnCount(); i++) {
                        Bookmark bookmark = new Bookmark(resultSet.getInt("id"),resultSet.getInt("project_id"),resultSet.getInt("freelancer_id"),resultSet.getDate("dateAdded"));
                        bookmarks.add(bookmark);
                    //}
                } while (resultSet.next());
                return bookmarks;

            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            logger.log(Level.SEVERE, exception.getMessage());

        }
        return bookmarks;
    }

    @Override
    public boolean deleteBookmark(Bookmark bookmark) {
        boolean deleted = false;
        try {
            int bookmarkId;
            if (bookmark != null) {
                bookmarkId = bookmark.getId();
                String query = "DELETE FROM bookmark WHERE id = ?";
                preparedStatement = cnx.prepareStatement(query);

                preparedStatement.setInt(1, bookmarkId);
                preparedStatement.executeUpdate();
                deleted = true;
            }

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            logger.log(Level.SEVERE, exception.getMessage());

        }
        return deleted;
    }

    @Override
    public boolean addBookmark(Bookmark bookmark) {
        try {
            //cnx.setAutoCommit(false);
            String query = "INSERT INTO bookmark VALUES(default,?,?,?)";
            preparedStatement = cnx.prepareStatement(query);
            //preparedStatement.addBatch();
            //preparedStatement.executeBatch();
            int counter = 1;
            Date date = CurrentDate.getCurrentDate();
            preparedStatement.setInt(counter++, bookmark.getFreelancerId());
            preparedStatement.setInt(counter++, bookmark.getProjectId());
            preparedStatement.setDate(counter++, date);

            if (preparedStatement.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            logger.log(Level.SEVERE, exception.getMessage());

        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BidService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (cnx != null) {
                try {
                    cnx.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BidService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return false;

    }

}
