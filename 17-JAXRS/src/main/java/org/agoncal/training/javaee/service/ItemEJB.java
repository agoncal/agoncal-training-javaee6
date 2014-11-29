package org.agoncal.training.javaee.service;

import org.agoncal.training.javaee.model.Book;
import org.agoncal.training.javaee.model.CD;
import org.agoncal.training.javaee.model.Item;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 6 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
@Stateless
@Interceptors(LoggingInterceptor.class)
@Path("/items")
public class ItemEJB {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    private EntityManager em;

    @Inject
    @ThirteenDigits
    /*@EightDigits*/
    private NumberGenerator numberGenerator;

//    @Context
//    private UriInfo uriInfo;

    private Logger logger = Logger.getLogger("org.agoncal.training.javaee6");

    // ======================================
    // =          Business methods          =
    // ======================================

    public List<Item> findAllItems() {
        return em.createNamedQuery("findAllItems", Item.class).getResultList();
    }

//    @POST
//    @Path("book")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response createJaxbBook(JAXBElement<Book> bookJaxb) {
//        Book book = bookJaxb.getValue();
//        book.setIsbn(numberGenerator.generateNumber());
//        em.persist(book);
//        em.flush(); // to get the id
//        URI bookUri = uriInfo.getAbsolutePathBuilder().path(book.getId().toString()).build();
//        Response resp = Response.created(bookUri).build();
//        logger.fine("ItemEJB.createJaxbBook():" + resp.toString());
//        return resp;
//    }

    public Book createBook(Book book) {
        book.setIsbn(numberGenerator.generateNumber());
        em.persist(book);
        return book;
    }

    @GET
    @Path("book/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Book findBook(@PathParam("id") Long id) {
        return em.find(Book.class, id);
    }

    @DELETE
    @Path("book/{id}")
    public void removeBook(@PathParam("id") Long id) {
        em.remove(em.find(Book.class, id));
    }

    public void removeBook(Book book) {
        em.remove(em.merge(book));
    }

    @GET
    @Path("books")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Book> findAllBooks() {
        return em.createNamedQuery("findAllBooks", Book.class).getResultList();
    }

    public CD createCD(CD cd) {
        em.persist(cd);
        return cd;
    }

    @GET
    @Path("cd/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public CD findCD(@PathParam("id") Long id) {
        return em.find(CD.class, id);
    }

    public void removeCD(CD cd) {
        em.remove(em.merge(cd));
    }

    @GET
    @Path("cds")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<CD> findAllCDs() {
        return em.createNamedQuery("findAllCDs", CD.class).getResultList();
    }
}
