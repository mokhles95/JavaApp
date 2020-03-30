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
import static Services.Implementation.BidService.cnx;
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
    private static final Logger logger = Logger.getLogger(BookmarkService.class.getName());

    private static BookmarkService bookmarkServiceInstance;

    private BookmarkService() {
    }

    public static BookmarkService getInstance() {
        if (bookmarkServiceInstance == null) {
            bookmarkServiceInstance = new BookmarkService();
        }
        return bookmarkServiceInstance;
    }

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
                    Bookmark bookmark = new Bookmark(resultSet.getInt("id"), resultSet.getInt("project_id"), resultSet.getInt("freelancer_id"), resultSet.getDate("dateAdded"));
                    bookmarks.add(bookmark);
                } while (resultSet.next());
                System.out.println(bookmarks.toString());
                return bookmarks;

            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            logger.log(Level.SEVERE, exception.getMessage());

        }
        return bookmarks;
    }

    @Override
    public boolean deleteBookmark(int BookmarkId) {
        try {
            String query = "DELETE FROM bookmark WHERE id = '" + BookmarkId + "'";
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
    public boolean addBookmark(Bookmark bookmark) {
        try {

            String query = "INSERT INTO bookmark VALUES (default,?,?,?)";
            preparedStatement = cnx.prepareStatement(query);
            Date date = CurrentDate.getCurrentDate();
            preparedStatement.setInt(1, bookmark.getFreelancerId());
            preparedStatement.setInt(2, bookmark.getProjectId());
            preparedStatement.setDate(3, date);
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            logger.log(Level.SEVERE, exception.getMessage());

        }
        return false;

    }
}
