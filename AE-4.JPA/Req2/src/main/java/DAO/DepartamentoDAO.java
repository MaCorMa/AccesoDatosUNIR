package DAO;

import database.HibernateUtil;
import model.Departamento;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class DepartamentoDAO {

    private Session session;

    public void altaDepartamento(Departamento departamento){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.persist(departamento);

        session.getTransaction().commit();
        session.close();
    }

    public List<Departamento> getAllDepartamentos(){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query<Departamento> query = session.createQuery("FROM Departamento", Departamento.class);
        List<Departamento> listaDepartamentos = query.list();

        session.getTransaction().commit();
        session.close();

        return listaDepartamentos;
    }
}
