package ru.anrusstrans.test.domain;
/**
 * Created by 1 on 11.03.14.
 * Author DMILK
 */

import org.hibernate.Session;
import ru.anrusstrans.test.util.HibernateUtil;

import java.util.Date;
import java.util.List;

public class EventManager {
    public static void main(String[] args) {
        EventManager mgr = new EventManager();
//        if (args[0].equals("store")) {
//            mgr.createAndStoreEvent("MyEvent", new Date());
//        }
//        Long eventId = mgr.createAndStoreEvent("Test3",new Date());
//        Long personId = mgr.createAndStorePerson("Вася","Пупкин");
//        mgr.addPersonToEvent(personId,eventId);
//        mgr.addEmailToPerson(5L,"vasya@mail.ru");
//        mgr.test2(1L, 9L);
//        mgr.test3();

//        HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
//        System.out.println(HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().isActive());

//        System.out.println(session.getTransaction().isActive());
//        System.out.println(HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().isActive());


        mgr.test2(10L,10L);

        HibernateUtil.getSessionFactory().close();

    }

    private Long createAndStoreEvent(String title, Date theDate) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(theDate);
        System.out.println(theEvent.getTitle());
        session.save(theEvent);
        session.getTransaction().commit();
        return theEvent.getId();
    }

    private Long createAndStorePerson(String firstName, String lastName) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person thePerson = new Person();
        thePerson.setFirstname(firstName);
        thePerson.setLastname(lastName);
        thePerson.setAge(0);
        session.save(thePerson);
        session.getTransaction().commit();
        return thePerson.getId();
    }

    private void addPersonToEvent(Long personId, Long eventId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Person aPerson = (Person) session
                .createQuery("select p from Person p left join fetch p.events where p.id = :pid")
                .setParameter("pid", personId)
                .uniqueResult(); // Eager fetch the collection so we can use it detached
        Event anEvent = (Event) session.load(Event.class, eventId);

        session.getTransaction().commit();

        // End of first unit of work
        aPerson.getEvents().add(anEvent); // aPerson (and its collection) is detached

        // Begin second unit of work

        Session session2 = HibernateUtil.getSessionFactory().getCurrentSession();
        session2.beginTransaction();
        session2.update(aPerson); // Reattachment of aPerson

        session2.getTransaction().commit();


    }

    private void addEmailToPerson(Long personId, String emailAddress) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Person aPerson = (Person) session.load(Person.class, personId);
        aPerson.getEmailAddresses().add(emailAddress);
        session.getTransaction().commit();
    }

    private void printParticipants(Long eventId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Event aEvent = (Event) session.load(Event.class, eventId);
        System.out.println(aEvent.getParticipants());
        session.getTransaction().commit();
    }

    private void test2(Long personId, Long eventId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Event aEvent = (Event) session.load(Event.class, eventId);
        System.out.println(aEvent);

        session.getTransaction().commit();
    }

    private void test3() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List result = session.createCriteria(Event.class).list();

        System.out.println(result);

        session.getTransaction().commit();

    }

    private void test4() {
        System.out.println(HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().isActive());
    }

}
