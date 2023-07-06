package com.example.demo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.model.HaveCategory;

public class CategoryDAO extends DAO{
	private static final String select_category = "SELECT * FROM category";
	private static final String select_havecategory = "SELECT * FROM havecategory";
	
	public List<Category> getCategories(){
		List<Category> cates = new ArrayList<Category>();
		String sql = select_category;
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
            	Category cate = new Category();
            	cate.setId(rs.getInt("id"));
            	cate.setName(rs.getString("name"));
            	cates.add(cate);
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
        return cates;
	}
	
	public Category getCategoryDetail(int id){
		Category cate = new Category();
		String sql = select_category + " WHERE id=?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, Integer.valueOf(id));
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
            	cate.setId(rs.getInt("id"));
            	cate.setName(rs.getString("name"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
        return cate;
	}
	
	public List<Integer> getIdCategoryByBook(int id){
		List<Integer> idCategories = new ArrayList<Integer>();
		String sql = "SELECT id FROM category INNER JOIN havecategory ON idCategory = id WHERE idBook=?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, Integer.valueOf(id));
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
            	idCategories.add(rs.getInt("id"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
        return idCategories;
	}
	
	public List<HaveCategory> getHaveCategories(int id){
		List<HaveCategory> havecategories = new ArrayList<HaveCategory>();
		String sql = "SELECT * FROM havecategory INNER JOIN category ON id = idCategory WHERE idBook=?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, Integer.valueOf(id));
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
            	Category category = new Category();
            	category.setId(rs.getInt("idCategory"));
            	category.setName(rs.getString("name"));
            	
            	Book book = new Book();
            	book.setId(id);
            	
            	HaveCategory havecategory = new HaveCategory();
            	havecategory.setBook(book);
            	havecategory.setCategory(category);
            	
            	havecategories.add(havecategory);
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
        return havecategories;
	}
}
