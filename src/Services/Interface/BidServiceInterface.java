/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Interface;

import Entities.Bid;

/**
 *
 * @author asus
 */
public interface BidServiceInterface {
    public String  displayBids();
    public boolean  addBid(Bid bid);
    public boolean deleteBid(int idBid);
    public boolean updateBid(Bid bid);
}
