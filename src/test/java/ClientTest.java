import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class ClientTest {

    @Before
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "maku", "maku");
    }

    @After
    public void tearDown() {
        try(Connection con = DB.sql2o.open()) {
            String deleteClientsQuery = "DELETE FROM clients *;";
            String deleteStylistsQuery = "DELETE FROM stylists *;";
            con.createQuery(deleteClientsQuery).executeUpdate();
            con.createQuery(deleteStylistsQuery).executeUpdate();
        }
    }

    //compare objects we retrieve from the database with our code in the all() method
    @Test
    public void equals_returnsTrueIfDescriptionsAretheSame() {
        Client firstClient = new Client("Mama Tendo", 1);
        Client secondClient = new Client("Morine", 2);
        assertTrue(firstClient.equals(secondClient));
    }

    //save objects into our database
    @Test
    public void save_returnsTrueIfDescriptionsAretheSame() {
        Client myClient = new Client("Mama Tendo", 1);
        myClient.save();
        assertTrue(Client.all().get(0).equals(myClient));
    }

    @Test
    public void all_returnsAllInstancesOfTask_true() {
        Client firstClient = new Client("Mama Tendo", 1);
        firstClient.save();
        Client secondClient = new Client("Morine", 2);
        secondClient.save();
        assertEquals(true, Client.all().get(0).equals(firstClient));
        assertEquals(true, Client.all().get(1).equals(secondClient));
    }

    //testing the id
    @Test
    public void save_assignsIdToObject() {
        Client myClient = new Client("Mama Tendo", 1);
        myClient.save();
        Client savedClient = Client.all().get(0);
        assertEquals(myClient.getId(), savedClient.getId());
    }

    //test for getid
    @Test
    public void getId_clientsInstantiateWithAnID() {
        Client myClient = new Client("Mama Tendo", 1);
        myClient.save();
        assertTrue(myClient.getId() > 0);
    }

    //finding specific objects
    @Test
    public void find_returnsClientWithSameId_secondClient() {
        Client firstClient = new Client("Oliver", 1);
        firstClient.save();
        Client secondClient = new Client("Mary", 2);
        secondClient.save();
        assertEquals(Client.find(secondClient.getId()), secondClient);
    }

    //update stylist
    @Test
    public void update_updatesClientName_true() {
        Client myClient= new Client("Mow the lawn", 1);
        myClient.save();
        myClient.update("Take a nap");
        assertEquals("Take a nap", Client.find(myClient.getId()).getName());
    }

    @Test
    public void delete_deletesClient_true() {
        Client myClient = new Client("Mow the lawn", 1);
        myClient.save();
        int myClientId = myClient.getId();
        myClient.delete();
        assertEquals(null, Client.find(myClientId));
    }
}