package ru.anrusstrans.test.web;

import org.hibernate.Session;
import ru.anrusstrans.test.domain.Event;
import ru.anrusstrans.test.util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 1 on 17.03.14.
 */
public class EventManagerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
//            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//            session.beginTransaction();
            HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();

            PrintWriter out = response.getWriter();
            out.println("<html><head><title>CoolRestService</title></head><body>");
            try {
                // Handle actions
                if ("store".equals(request.getParameter("action"))) {
                    String eventTitle = request.getParameter("eventTitle");
                    String eventDate = request.getParameter("eventDate");

                    if ("".equals(eventTitle) || "".equals(eventDate)) {
                        out.println("<b><i>Please enter event title and date.</i></b>");
                    } else {
                        createAndStoreEvent(eventTitle, dateFormatter.parse(eventDate));
                        out.println("<b><i>Added event.</i></b>");
                    }
                }
            } catch (Exception e) {
                out.println("<h1>1 " + e + "</h1>");
            }

            try {
//                Print page
                printEventForm(out);
                listEvents(out, dateFormatter);

                // Write HTML footer
            } catch (Exception e) {
                out.println("<h1>12 " + e + "</h1>");

            }
            out.println("</body></html>");
            out.flush();
            out.close();

//            session.getTransaction().commit();
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
        } catch (Exception ex) {
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            if (ServletException.class.isInstance(ex)) {
                throw (ServletException) ex;
            } else {
                throw new ServletException(ex);
            }
        }
    }

    private void printEventForm(PrintWriter out) {
        out.println("<h2>Add new event:</h2>");
        out.println("<form>");
        out.println("Title: <input name='eventTitle' length='50'/><br/>");
        out.println("Date (e.g. 24.12.2009): <input name='eventDate' length='10'/><br/>");
        out.println("<input type='submit' name='action' value='store'/>");
        out.println("<input type='submit' name='action2' value='store2'/>");
        out.println("</form>");
    }

    private void listEvents(PrintWriter out, SimpleDateFormat dateFormatter) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//        List result = session.createCriteria(Event.class).list();
        List result = HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(Event.class).list();
        if (result.size() > 0) {
            out.println("<h2>Events in database111:</h2>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>Event title</th>");
            out.println("<th>Event date</th>");
            out.println("<th>Desription</th>");
            out.println("</tr>");
            Iterator it = result.iterator();
            while (it.hasNext()) {
                Event event = (Event) it.next();
                out.println("<tr>");
                out.println("<td>" + event.getTitle() + "</td>");
                out.println("<td>" + dateFormatter.format(event.getDate()) + "</td>");
                out.println("<td></td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }
//        session.getTransaction().commit();
    }

    protected void createAndStoreEvent(String title, Date theDate) {
        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(theDate);
        HibernateUtil.getSessionFactory().getCurrentSession().save(theEvent);
    }

}


