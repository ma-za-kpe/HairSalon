import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.Assert.*;

public class StylistsTest {

    @Before
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "maku", "maku");
    }

    @After
    public void tearDown() {
        try (Connection con = DB.sql2o.open()) {
            String deleteClientsQuery = "DELETE FROM clients *;";
            String deleteStylistsQuery = "DELETE FROM stylists *;";
            con.createQuery(deleteClientsQuery).executeUpdate();
            con.createQuery(deleteStylistsQuery).executeUpdate();
        }
    }

    @Test
    public void equals_returnsTrueIfNamesAretheSame() {
        Stylists firstStylists = new Stylists("Macus");
        Stylists secondStylists = new Stylists("Luke");
        assertTrue(firstStylists.equals(secondStylists));
    }

    //saving new stylist objects in the database
    @Test
    public void save_savesIntoDatabase_true() {
        Stylists myStylists = new Stylists("Macus");
        myStylists.save();
        assertTrue(Stylists.all().get(0).equals(myStylists));
    }

    @Test
    public void all_returnsAllInstancesOfCategory_true() {
        Stylists firstStylists = new Stylists("Macus");
        firstStylists.save();
        Stylists secondStylists = new Stylists("Luke");
        secondStylists.save();
        assertEquals(true, Stylists.all().get(0).equals(firstStylists));
        assertEquals(true, Stylists.all().get(1).equals(secondStylists));
    }

    //assigning unique ids
    @Test
    public void save_assignsIdToObject() {
        Stylists myStylists = new Stylists("Luke");
        myStylists.save();
        Stylists savedmyStylists = Stylists.all().get(0);
        assertEquals(myStylists.getId(), savedmyStylists.getId());
    }

    @Test
    public void getId_categoriesInstantiateWithAnId_1() {
        Stylists testStylists = new Stylists("Luke");
        testStylists.save();
        assertTrue(testStylists.getId() > 0);
    }

    @Test
    public void find_returnsCategoryWithSameId_secondCategory() {
        Stylists firstStylists = new Stylists("Macus");
        firstStylists.save();
        Stylists secondStylists = new Stylists("Luke");
        secondStylists.save();
        assertEquals(Stylists.find(secondStylists.getId()), secondStylists);
    }

    // save a stylistId into our clients table, thereby associating a Client with its Stylist

    @Test
    public void save_savesStylistIdIntoDB_true() {
        Stylists myStylists = new Stylists("Macus");
        myStylists.save();
        Client myClient = new Client("Mama Tendo", 1);
        myClient.save();
        Client savedClient = Client.find(myClient.getId());
        assertEquals(savedClient.getStylistId(), myStylists.getId());
    }


    @Test
    public void getTasks_retrievesALlTasksFromDatabase_tasksList() {
        Stylists myStylists = new Stylists("Macus");
        myStylists.save();
        Client firstClient = new Client("Macus", myStylists.getId());
        firstClient.save();
        Client secondClient = new Client("Mama Tendo", myStylists.getId());
        secondClient.save();
        Client[] clients = new Client[]{firstClient, secondClient};
        assertTrue(myStylists.getClient().containsAll(Arrays.asList(clients)));
    }

}