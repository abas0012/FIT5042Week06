/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.tutex.calculator;

import fit5042.tutex.calculator.mbeans.ComparePropertySessionRemote;
import fit5042.tutex.repository.entities.Property;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.CreateException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implementation class of ComparePropertySessionBeanRemote
 * @author Adhi Baskoro
 */
@Stateful //Being stateful means that this class will be bound to a client when EJB is instantiated.
public class ComparePropertySessionBean implements ComparePropertySessionRemote {
    
    @PersistenceContext
    private EntityManager entityManager;
    private ArrayList<Integer> roomList;

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void addPropertyID(int propertyID) {
        roomList.add(propertyID);
    }

    @Override
    public void removePropertyID(int propertyID) {
        roomList.remove(new Integer(propertyID));
    }    

    @Override
    public int bestPerRoom() {
        int bestPropertyID = 0;
        Property property;
        int numberOfRooms;
        double price;
        double bestPerRoom = 100000000.00;
        for (Integer propertyID : roomList) //for each id in roomList
        {
            property=entityManager.find(Property.class, propertyID);
            numberOfRooms=property.getNumberOfBedrooms();
            price=property.getPrice();
            
            if(price/numberOfRooms < bestPerRoom) //if Price per number of rooms less than bestPerRoom (a very large number)
            {
                bestPerRoom = price/numberOfRooms;
                bestPropertyID = property.getPropertyId();
            }
        }
        return bestPropertyID;
    }

    /**
     *
     * @return 
     * @throws javax.ejb.CreateException
     * @throws java.rmi.RemoteException
     */
    @PostConstruct //Postconstruc of EJB instance
    public void init() {
        roomList=new ArrayList<>(); //iitialist the ArrayList
    }

    public ComparePropertySessionRemote create() throws CreateException, RemoteException {
        return null;
    }

    public void ejbCreate() throws CreateException {
    }
    
}
