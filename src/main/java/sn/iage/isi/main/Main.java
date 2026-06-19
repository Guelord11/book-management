package sn.iage.isi.main;

import sn.iage.isi.entities.Category;
import sn.iage.isi.repositories.CategoryRepository;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        CategoryRepository categoryRepository = new CategoryRepository();
        for (Category category : categoryRepository.getAll()) {
            System.out.println(category);
        }
        }
    }
