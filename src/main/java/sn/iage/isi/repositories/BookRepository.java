package sn.iage.isi.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import sn.iage.isi.entities.Book;

import java.util.List;
import java.util.Random;


public class BookRepository {
    EntityManager em = JpaUtil.getEntityManager();

    private String generateIsbn() {
        // Préfixe ISBN-13 : 978 ou 979
        String[] prefixes = {"978", "979"};
        Random random = new Random();

        String prefix = prefixes[random.nextInt(2)];        // 978 ou 979
        String group = String.valueOf(random.nextInt(2));    // 0 ou 1 (groupe langue)
        String publisher = String.format("%04d", random.nextInt(10000));   // éditeur 4 chiffres
        String title    = String.format("%04d", random.nextInt(10000));    // titre   4 chiffres

        // Calcul du chiffre de contrôle (checksum ISBN-13)
        String base = prefix + group + publisher + title;   // 12 chiffres
        int checkDigit = computeIsbn13CheckDigit(base);

        String isbn = base + checkDigit;

        // Format lisible : 978-X-XXXX-XXXX-X
        return String.format("%s-%s-%s-%s-%d",
                prefix, group, publisher, title, checkDigit);
    }

    private int computeIsbn13CheckDigit(String base12) {
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = Character.getNumericValue(base12.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;   // alternance poids 1 et 3
        }
        int remainder = sum % 10;
        return remainder == 0 ? 0 : 10 - remainder;
    }

    public Book createBook(Book book) {
        EntityTransaction tx = em.getTransaction();
        Book b = Book.builder()
                .isbn(generateIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .countPages(book.getCountPages())
                .publicationYear(book.getPublicationYear())
                .category(book.getCategory())
                .build();
        b.setUserCreated("admin");
        b.setUserUpdated("admin");

        try
        {
            tx.begin();
            em.persist(b);
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }
        return b;
    }

    public List<Book> listAllBooks() {
        return  em.createQuery("select b from Book b", Book.class).getResultList();
    }

    public Book findBookById(int id) {
        return em.find(Book.class, id);
    }

    public Book findBookByIsbn(String isbn) {
        return em.createQuery("SELECT b FROM Book b WHERE b.isbn = :isbn", Book.class)
                .setParameter("isbn", isbn)
                .getSingleResult();
    }


    public Book updateBook(int id, Book newBook) {
        EntityTransaction tx = em.getTransaction();
        Book book = findBookById(id);
        book.setTitle(newBook.getTitle());
        book.setAuthor(newBook.getAuthor());
        book.setCountPages(newBook.getCountPages());
        book.setPublicationYear(newBook.getPublicationYear());
        book.setCategory(newBook.getCategory());
        book.setUserUpdated("admin");
        try {
            tx.begin();
            em.merge(book);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return book;
    }

    public void deleteBook(int id) {
        EntityTransaction tx = em.getTransaction();
        Book book = findBookById(id);
        try {
            tx.begin();
            em.remove(book);
            tx.commit();
        }catch (Exception e) {
            tx.rollback();
        }
    }

    public List<Book> ListeBooksByCategory(String categoryName) {
        return em.createQuery("SELECT b FROM Book b WHERE b.category.name = :name", Book.class)
                .setParameter("name", categoryName)
                .getResultList();
    }

    public List<Book> searchBooksByTitle(String keyword) {
        return em.createQuery("SELECT b FROM Book b WHERE LOWER(b.title) LIKE :kw", Book.class)
                .setParameter("kw", "%" + keyword.toLowerCase() + "%")
                .getResultList();
    }

    public List<Book> searchBooksByAuthor(String keyword) {
        return em.createQuery("SELECT b FROM Book b WHERE LOWER(b.author) LIKE :kw", Book.class)
                .setParameter("kw", "%" + keyword.toLowerCase() + "%")
                .getResultList();
    }

    public List<Book> searchBooksAfterYear(int year) {
        return em.createQuery("SELECT b FROM Book b WHERE b.publicationYear >= :year", Book.class)
                .setParameter("year", year)
                .getResultList();
    }

    public List<Object[]> countBooksByCategory() {
        return em.createQuery("SELECT b.category.name, COUNT(b) FROM Book b GROUP BY b.category.name")
                .getResultList();
    }

    public Long countAllBooks() {
        return em
                .createQuery("SELECT COUNT(b) FROM Book b", Long.class)
                .getSingleResult();
    }
}
