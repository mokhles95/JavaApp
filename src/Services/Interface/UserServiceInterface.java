/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Interface;

import Entities.User;
import java.util.List;

/**
 *
 * @author asus
 */
public interface UserServiceInterface {

    public User ajouterUser(User u);

    public User modifierUser(User u);

    public List<User> getUsers();

    public User getUserByEmail(String email);

    public User getUserById(int idUser);

    public User getUserByUsername(String username);

    public List<User> getAllUsers();
}
