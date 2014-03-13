package ru.anrusstrans.test.domain;
/**
 * Created by 1 on 11.03.14.
 * Author DMILK
 */

import org.hibernate.Session;
import ru.anrusstrans.test.util.HibernateUtil;

import java.util.Date;

public class EventManager {
    public static void main(String[] args) {
        EventManager mgr = new EventManager();
//        if (args[0].equals("store")) {
//            mgr.createAndStoreEvent("MyEvent", new Date());
//        }
        mgr.addPersonToEvent2(4L, 9L);
        HibernateUtil.getSessionFactory().close();

    }

    private void createAndStoreEvent(String title, Date theDate) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(theDate);
        System.out.println(theEvent.getTitle());
        session.save(theEvent);
//        session.getTransaction().commit();
    }

    private void addPersonToEvent(Long personId, Long eventId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person aPerson = (Person) session.load(Person.class, personId);
        Event anEvent = (Event) session.load(Event.class, eventId);
        System.out.println(aPerson.getLastname());
        System.out.println(anEvent.getDate());
        System.out.println(aPerson.getEvents());
        aPerson.setFirstname(aPerson.getFirstname()+"1");
        session.getTransaction().commit();

    }
    private void addPersonToEvent2(Long personId, Long eventId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        //Person aPerson = (Person) session.load(Person.class, personId);
        Person aPerson = (Person) session
                .createQuery("select p from Person p left join fetch p.events where p.id = :pid")
                .setParameter("pid", personId)
                .uniqueResult(); // Eager fetch the collection so we can use it detached
        Event anEvent = (Event) session.load(Event.class, eventId);

        session.getTransaction().commit();

        // End of first unit of work

        aPerson.getEvents().add(anEvent); // aPerson (and its collection) is detached
//        anEvent.getId();
        System.out.println(anEvent.getId());

        // Begin second unit of work

//        Session session2 = HibernateUtil.getSessionFactory().getCurrentSession();
//        session2.beginTransaction();
//        session2.update(aPerson); // Reattachment of aPerson
//
//        session2.getTransaction().commit();


    }
}
