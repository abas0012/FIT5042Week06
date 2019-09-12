/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.tutex.calculator.mbeans;

import javax.ejb.Remote;

/**
 *
 * @author Adhi Baskoro
 */

@Remote //Remote interface for ComparePropertySessionBeanRemote
public interface ComparePropertySessionRemote {
    
    public void addPropertyID(int propertID);
    
    public void removePropertyID(int propertyID);

    public int bestPerRoom();

}
