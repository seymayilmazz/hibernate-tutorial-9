package com.codegirl;

import com.codegirl.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by Şeyma Yılmaz on 6.8.2017.
 */
public class HibernateTest {
    public static void main(String[] args) {

        Configuration configuration=new Configuration();
        configuration.configure();

        SessionFactory sessionFactory=configuration.buildSessionFactory();
        Session session=sessionFactory.openSession();

        session.beginTransaction();

        /*
        Account account1 = new Account();
        account1.setEmail("seymylmz@gmail.com");
        account1.setPassword("123456");
        account1.setAccountStatus(AccountStatus.ENABLED);
        account1.setCreateOn(new Date());

        User user1 = new User();
        user1.setFirstName("Şeyma");
        user1.setLastName("Yılmaz");
        user1.setAge(23);

        account1.setUser(user1);
        */
        //user.setAccount(account);

        /*
        Account account2 = new Account();
        account2.setEmail("burakkokenn@gmail.com");
        account2.setPassword("123456");
        account2.setAccountStatus(AccountStatus.ENABLED);
        account2.setCreateOn(new Date());

        User user2 = new User();
        user2.setFirstName("Burak");
        user2.setLastName("Köken");
        user2.setAge(23);
        */

        //account2.setUser(user2);
        //user.setAccount(account);
        //user1.getFollowers().add(user2);

        //session.save(user1);
        //session.save(account1);
        //session.save(user2);
        //session.save(account2);

        /* select * from User  = from User*/
        Query query = session.createQuery("from User");
        List<User> userList = query.list();

        for(User user : userList){
            /*System.out.println("id : " + user.getId());
            System.out.println("name : " + user.getFirstName() + " " + user.getLastName());
            */
        }

        /* SELECT, AS
         * Select id, firstName, age = select u.id, u.firstName, u.age from User as u
         *
         */
        Query query2 = session.createQuery("select u.id, u.firstName, u.age from User as u");
        List userList2 = query2.list();

        for(Object object : userList2){
            Object[] userFields = (Object[]) object;
            /*
            System.out.println("id : " + userFields[0]);
            System.out.println("name : " + userFields[1]);
            System.out.println("age : " + userFields[2]);
            */
        }

        /*
         * WHERE(or, and)
         * select * from User age >= 21  -> from User where age >= 21
         * select * from User age >= 21  or firstName = 'Şeyma'
         *      -> from User where age >= 21 or firstName = 'Şeyma'
         */
        Query query3 = session.createQuery("from User where age >= 21 or firstName = 'Şeyma'");
        List<User> userList3 = query3.list();

        /*
         * WHERE with parameters
         */
        String firstName = "Şeyma";
        int age = 21;
        Query query4 = session.createQuery("from User where age >= ? or firstName = ?");
        query4.setParameter(0, age);
        query4.setParameter(1, firstName);
        List<User> userList4 = query4.list();

        Query query5 = session.createQuery("from User where age >= :age or firstName = :name");
        query5.setParameter("age", age);
        query5.setParameter("name", firstName);
        List<User> userList5 = query5.list();


        /*
         * ORDER BY
         * select * from user ordey by name (asc-desc) = from user ordey by name desc
         */
        Query query6 = session.createQuery("from User order by firstName");
        List<User> userList6 = query6.list();


        /*
         * GROUP BY
         * SELECT P.name, SUM(P.price)" +
         *      "FROM Product AS P " +
         *      "GROUP BY P.name";
         */

        /*
         * UPDATE
         *
         * UPDATE user SET lastName = 'köken' WHERE id = 1
         *  -> update user set lastName = :lastName where id = :id
         */
        Query query7 = session.createQuery("update User set lastName = :lastName where id = :id");
        query7.setParameter("lastName", "köken");
        query7.setParameter("id", 1L);
        query7.executeUpdate();

        /*
         * DELETE
         *
         * DELETE FROM user where id = 1
         *  -> delete user where id = :id
         */
        Query query8 = session.createQuery("delete User where id = :id");
        query8.setParameter("id", 2L);
        query8.executeUpdate();

        /*
         * INSERT(save metodunu kullanmalısın)
         * bir tablodaki kaydi baska tabloya aktarmak icin
         *
         * INSERT INTO ProductOrder (productId, name, price) " +
         *      "SELECT p.id, p.name, p.price FROM Product AS p WHERE p.id = 2";
         */


        /*
         * setFirstResult(2) - setMaxResults(3)
         * 2.kayittan 3 kayit ilerle bunlari dondur.?
         */
        Query query9 = session.createQuery("from User");
        query9.setFirstResult(2);
        query9.setMaxResults(3);
        List<User> userList9 = query9.list();

        /*
         *
         */
        Query query10 = session.createNamedQuery("User.findAll");
        List<User> userList10 = query10.list();

        session.close();

    }
}
