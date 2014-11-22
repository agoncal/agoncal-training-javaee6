package org.agoncal.training.javaee.service;

import org.agoncal.training.javaee.domain.Book;
import org.agoncal.training.javaee.domain.CD;
import org.agoncal.training.javaee.domain.Language;

import javax.annotation.PostConstruct;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.logging.Logger;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 6 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
@Singleton
@Startup
@DataSourceDefinition(name = "java:global/jdbc/lab13DS",
        className = "org.apache.derby.jdbc.EmbeddedDriver",
        url = "jdbc:derby:memory:lab13DB;create=true;user=app;password=app"
)
public class DBPopulator {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    private ItemEJB itemEJB;

    private Logger logger = Logger.getLogger("org.agoncal.training.javaee6");

    // ======================================
    // =          Lifecycle methods         =
    // ======================================

    @PostConstruct
    private void createDummyData() {
        createBooks();
        createCDs();
    }

    // ======================================
    // =          Business methods          =
    // ======================================

    private void createBooks() {
        itemEJB.createBook(new Book("The Hitchhiker's Guide to the Galaxy", 23.99f, "Science fiction comedy book", 354, Boolean.TRUE, Language.ENGLISH));
        itemEJB.createBook(new Book("Harry Potter and the Goblet of Fire", 19.79f, "Science fiction (Book 4)", 734, Boolean.TRUE, Language.ENGLISH));
        itemEJB.createBook(new Book("The Elements of Style", 6.64f, "A masterpiece in the art of clear and concise writing.", 105, Boolean.FALSE, Language.ENGLISH));
        itemEJB.createBook(new Book("Harry Potter And The Order Of The Phoenix", 9.35f, "Science fiction (Book 1)", 870, Boolean.TRUE, Language.ENGLISH));
        itemEJB.createBook(new Book("The Difference Between God and Larry Ellison", 11.99f, "God Doesn't Think He's Larry Ellison", 420, Boolean.FALSE, Language.ENGLISH));
        itemEJB.createBook(new Book("The Children of Hurin", 17.16f, "The first complete book by J.R.R. Tolkien in three decades", 313, Boolean.TRUE, Language.ENGLISH));
        itemEJB.createBook(new Book("The Da Vinci Code", 17.79f, "Thriller", 454, Boolean.FALSE, Language.ENGLISH));
        itemEJB.createBook(new Book("La carte et le territoire", 24.99f, "Prix Goncourt 2010", 428, Boolean.FALSE, Language.FRENCH));
        itemEJB.createBook(new Book("Une forme de vie", 18.99f, "Amelie Nothomb", 168, Boolean.FALSE, Language.FRENCH));
        itemEJB.createBook(new Book("To Kill a Mockingbird", 5.99f, "American classic", 281, Boolean.FALSE, Language.ENGLISH));
        itemEJB.createBook(new Book("Fahrenheit 451", 7.99f, "Science fiction", 208, Boolean.FALSE, Language.ENGLISH));
        itemEJB.createBook(new Book("Lolita", 14.99f, "Nabovok masterpiece", 368, Boolean.TRUE, Language.RUSSIAN));
        itemEJB.createBook(new Book("A Midsummer Night's Dream", 14.99f, "Shakespeare masterpiece", 288, Boolean.TRUE, Language.ENGLISH));
        itemEJB.createBook(new Book("I bastioni del coraggio", 18.99f, "Anno domini 1548. Una grande storia d'amore, ...", 457, Boolean.FALSE, Language.ITALIAN));
        itemEJB.createBook(new Book("Harjunpaa ja pahan pappi", 32.49f, "Finns read crime novels", 300, Boolean.FALSE, Language.FINISH));
        itemEJB.createBook(new Book("El ingenioso hidalgo don Quijote de la Mancha", 13.99f, "Classical Don Quijote", 108, Boolean.TRUE, Language.SPANISH));
        itemEJB.createBook(new Book("The Lord of the Rings", 50.4f, "Science fiction comedy book", 1216, Boolean.TRUE, Language.ENGLISH));
        itemEJB.createBook(new Book("Java EE 6 with GlassFish 3", 31.49f, "Java!", 354, Boolean.TRUE, Language.ENGLISH));
        itemEJB.createBook(new Book("Heidis Lehr- und Wanderjahre", 68f, "Inhalt: Die Heidi-Bucher erzahlen...", 160, Boolean.TRUE, Language.DEUTSCH));
        itemEJB.createBook(new Book("l Nome della Rosa", 34.99f, "Science fiction comedy book", 354, Boolean.FALSE, Language.ITALIAN));
        logger.info("&&&&&&&&&&&&&& Inserted " + itemEJB.findAllBooks().size() + " Books");
    }

    private void createCDs() {
        itemEJB.createCD(new CD("Genesis", 12.99f, "Genesis are an English rock band that formed in 1967", "Atlantic", 1, 44.10f, "Prog"));
        itemEJB.createCD(new CD("The Dark Side of the Moon", 14.99f, "Pink Floyd were an English rock band", "Emi", 1, 42.59f, "Psyche"));
        itemEJB.createCD(new CD("Minor Swing", 1.99f, "Pioneering virtuoso jazz guitarist and composer", "None", 1, 3.18f, "Jazz"));
        itemEJB.createCD(new CD("Simon and Garfunkel in Central Park", 23.99f, "Live album recorded on September 19, 1981", "Island", 2, 75.51f, "Pop"));
        itemEJB.createCD(new CD("Blue Monday", 1.99f, "New Order's longest tracks ever to chart", "Factory", 1, 7.29f, "Jazz"));
        itemEJB.createCD(new CD("The Unforgettable Fire", 13.99f, "The Unforgettable Fire is the fourth studio album by Irish rock band U2", "Island", 1, 42.38f, "Jazz"));
        itemEJB.createCD(new CD("Synchronicity", 15.99f, "Fifth and final studio album by The Police, released in 1983", "Emi", 1, 44.32f, "Pop"));
        itemEJB.createCD(new CD("Bireli Lagrene & Gypsy Project", 16.98f, "has perhaps inherited more of Django's musical spirit", "Dreyfus", 2, 58.12f, "Jazz"));
        logger.info("&&&&&&&&&&&&&& Inserted " + itemEJB.findAllCDs().size() + " CDs");
    }


}
