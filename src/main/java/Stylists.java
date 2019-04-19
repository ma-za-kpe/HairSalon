import org.sql2o.Connection;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Stylists {

    private String name;
    private int id;

    public Stylists(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    //return all stylists
    public static List<Stylists> all() {
        String sql = "SELECT id, name FROM stylists";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Stylists.class);
        }
    }

    public List<Client> getClient() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM clients where stylistid=:id";
            return con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeAndFetch(Client.class);
        }
    }

    public static Stylists find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM stylists where id=:id";
            Stylists category = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Stylists.class);
            return category;
        }
    }


    @Override
    public boolean equals(Object otherStylist) {
        if (!(otherStylist instanceof Stylists)) {
            return false;
        } else {
            Stylists newStylists = (Stylists) otherStylist;
            return this.getName().equals(newStylists.getName()) &&
                    this.getId() == newStylists.getId();
        }
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO stylists (name) VALUES (:name)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .executeUpdate()
                    .getKey();
        }
    }

    public void update(String name) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE stylists SET name = :name WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM stylists WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

}


