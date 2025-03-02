package org.mcm.gestorligas.dao;

import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.mcm.gestorligas.database.HibernateUtils;
import org.mcm.gestorligas.model.Usuario;

@NoArgsConstructor
public class UsuarioDao {

    private Session session;

    public Usuario getUusarioLogin(String correo, String pass){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        //opcion mal y rapida
        //SELECT * -> foreach / filter -> si hay uno en la lista ->loginOK

        //opcion real
        //SELECT * FROM usuarios WHERE username = correo AND password = pass

        //hsql
        Query<Usuario> queryUsuario = session.createQuery("FROM Usuario u WHERE u.correo = :correo AND u.pass = :pass");
        queryUsuario.setParameter("correo",correo);
        queryUsuario.setParameter("pass", pass);

        Usuario usuario = queryUsuario.getSingleResult();

        session.getTransaction().commit();
        session.close();

        return usuario;
    }

}
