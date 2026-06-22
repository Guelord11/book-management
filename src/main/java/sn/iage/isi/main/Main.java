package sn.iage.isi.main;

import sn.iage.isi.entities.Book;
import sn.iage.isi.entities.Category;
import sn.iage.isi.repositories.BookRepository;
import sn.iage.isi.repositories.CategoryRepository;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        BookRepository bookRepository = new BookRepository();
        CategoryRepository categoryRepository = new CategoryRepository();

//        // Récupérer une catégorie existante (id=1) pour l'associer au livre
//        Category c = categoryRepository.getById(2);
//
//        // 1. Créer un livre
//        Book book = Book.builder()
//                .title("Les aventures de Guelord")
//                .author("Guelord")
//                .publicationYear(2022)
//                .countPages(140)
//                .category(c)
//                .build();
//        System.out.println(bookRepository.createBook(book));

//        // 2. Lister tous les livres
//        System.out.println(bookRepository.listAllBooks());
//
//        // 3. Trouver par id
//        System.out.println(bookRepository.findBookById(1));
//
//        // 4. Trouver par ISBN
//        System.out.println(bookRepository.findBookByIsbn("979-1-6587-3465-2"));
//
//        // 5. Recherche par titre
//        System.out.println(bookRepository.searchBooksByTitle("tintin"));
//
//        // 6. Recherche par auteur
//        System.out.println(bookRepository.searchBooksByAuthor("GuElOrD"));
//
//        // 7. Livres publiés à partir d'une année
//        System.out.println(bookRepository.searchBooksAfterYear(1920));
//
//        // 8. Livres d'une catégorie
//        System.out.println(bookRepository.ListeBooksByCategory("Nouvelle"));
//
//        // 9. Compter tous les livres
//        System.out.println(bookRepository.countAllBooks());
//
//        // 10. Compter par catégorie
//        for (Object[] row : bookRepository.countBooksByCategory()) {
//            System.out.println(row[0] + " : " + row[1]);
//        }
//
//        // 11. Mettre à jour un livre
//        Book bookMAJ = Book.builder()
//                .title("Tintin au Tibet")
//                .author("Hergé")
//                .publicationYear(1960)
//                .countPages(64)
//                .category(c)
//                .build();
//        System.out.println(bookRepository.updateBook(1, bookMAJ));
//
        // 12. Supprimer un livre
        bookRepository.deleteBook(2);
    }
}
