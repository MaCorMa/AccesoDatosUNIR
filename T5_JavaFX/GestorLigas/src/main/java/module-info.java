module org.mcm.gestorligas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires static lombok;



    opens org.mcm.gestorligas to javafx.fxml, jakarta.persistence, org.hibernate.orm.core;
    exports org.mcm.gestorligas;

    opens org.mcm.gestorligas.model to javafx.fxml, jakarta.persistence, org.hibernate.orm.core;
    exports org.mcm.gestorligas.model;

    opens org.mcm.gestorligas.controller to javafx.fxml, jakarta.persistence, org.hibernate.orm.core;
    exports org.mcm.gestorligas.controller;
    
    exports org.mcm.gestorligas.database;
    opens org.mcm.gestorligas.database to jakarta.persistence, javafx.fxml, org.hibernate.orm.core;

    exports org.mcm.gestorligas.dao;
    opens org.mcm.gestorligas.dao to jakarta.persistence, javafx.fxml, org.hibernate.orm.core;
}