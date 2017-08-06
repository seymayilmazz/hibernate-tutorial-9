package com.codegirl;

import com.codegirl.model.Account;
import com.codegirl.model.AccountStatus;
import com.codegirl.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;

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
        //user.setAccount(account);



        Account account2 = new Account();
        account2.setEmail("burakkokenn@gmail.com");
        account2.setPassword("123456");
        account2.setAccountStatus(AccountStatus.ENABLED);
        account2.setCreateOn(new Date());

        User user2 = new User();
        user2.setFirstName("Burak");
        user2.setLastName("Köken");
        user2.setAge(23);

        account2.setUser(user2);
        //user.setAccount(account);

        user1.getFollowers().add(user2);

        session.save(user1);
        session.save(account1);
        session.save(user2);
        session.save(account2);

        session.getTransaction().commit();
        session.close();

    }
}
