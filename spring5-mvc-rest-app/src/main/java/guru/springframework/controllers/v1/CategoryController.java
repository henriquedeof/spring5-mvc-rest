package guru.springframework.controllers.v1;

import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.services.CategoryService;
import guru.springframework.api.v1.model.CategoryListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//@Controller
//@RequestMapping({"/api/v1/categories/", "/api/v1/categories"})
@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/v1/categories";

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK) //@ResponseStatus(code = HttpStatus.OK)
    public CategoryListDTO getAllCategories(){      //public ResponseEntity<CategoryListDTO> getAllCategories(){ // Old method signature
        return new CategoryListDTO(this.categoryService.getAllCategories());
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable String name){  //public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name){// Old method signature
        return this.categoryService.getCategoryByName(name);
    }

}
