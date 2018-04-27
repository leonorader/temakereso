package temakereso.service;

import java.util.List;

import temakereso.entity.Category;

public interface CategoryService {
	
	/**
	 * Returns all the categories
	 * 
	 * @return a list of categories
	 */
	List<Category> getAll();
	
	/**
	 * Creates a new category
	 * 
	 * @param category
	 * @return the saved category
	 */
	Category createCategory(Category category);
	
	/**
	 * Modifies a category
	 * 
	 * @param category
	 * @return the modified category
	 */
	Category modifyCategory(Category category);

}
