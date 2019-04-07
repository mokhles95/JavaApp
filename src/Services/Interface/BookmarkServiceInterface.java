/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Interface;

import Entities.Bookmark;
import Entities.Project;
import Entities.User;

/**
 *
 * @author asus
 */
public interface BookmarkServiceInterface {
    public boolean addBookmark(Bookmark bookmark);
    public boolean deleteBookmark(Bookmark bookmark);
    public void displayBookmarks();
}
