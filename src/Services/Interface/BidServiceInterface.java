/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Interface;

import Entities.Bid;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public interface BidServiceInterface {

    public ArrayList<Bid> displayBids();

    public boolean addBid(Bid bid);

    public boolean deleteBid(Bid bid);

    public boolean updateBid(Bid bid);
    
    //public boolean updateDeliveryTimeBid(Bid bid);
    
    //public boolean updateMinimalRateBid();

    public Bid findById(int id);

}
