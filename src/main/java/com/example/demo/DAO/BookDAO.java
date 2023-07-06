package com.example.demo.DAO;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Account;
import com.example.demo.model.Book;
import com.example.demo.model.Comment;
import com.example.demo.model.HaveCategory;
import com.example.demo.model.Rate;

public class BookDAO extends DAO{
	
	private static final String select_book = "SELECT * FROM book";
	private static final String select_best_seller = "SELECT * FROM book ORDER BY amount LIMIT 1";
	
	public List<Book> getBooks(){
		List<Book> books = new ArrayList<Book>();
		String sql = select_book;
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
            	Book book = new Book();
            	book.setId(rs.getInt("id"));
            	book.setTitle(rs.getString("title"));
            	book.setAuthor(rs.getString("author"));
            	book.setDescription(rs.getString("description"));
            	book.setAmount(rs.getInt("amount"));
            	book.setPublishYear(rs.getInt("publishYear"));
            	book.setPublisher(rs.getString("publisher"));
            	book.setNumberOfPages(rs.getInt("numberOfPages"));
            	book.setCost(rs.getInt("cost"));
            	book.setPrice(rs.getInt("price"));
            	book.setCoverKind(rs.getString("coverKind"));
            	book.setImage(rs.getString("image"));
                books.add(book);
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
        return books;
	}
	
	public Book getBook(int id){
		Book book = new Book();
		String sql = select_book + " WHERE id=?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
            	book.setId(rs.getInt("id"));
            	book.setTitle(rs.getString("title"));
            	book.setAuthor(rs.getString("author"));
            	book.setDescription(rs.getString("description"));
            	book.setAmount(rs.getInt("amount"));
            	book.setPublishYear(rs.getInt("publishYear"));
            	book.setPublisher(rs.getString("publisher"));
            	book.setNumberOfPages(rs.getInt("numberOfPages"));
            	book.setCost(rs.getInt("cost"));
            	book.setPrice(rs.getInt("price"));
            	book.setCoverKind(rs.getString("coverKind"));
            	book.setImage(rs.getString("image"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
        return book;
	}
	
	public List<Book> getBooksByTitle(String name){
		List<Book> books = new ArrayList<Book>();
		String sql = select_book + " WHERE title LIKE '%" + name + "%'";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
            	Book book = new Book();
            	book.setId(rs.getInt("id"));
            	book.setTitle(rs.getString("title"));
            	book.setAuthor(rs.getString("author"));
            	book.setDescription(rs.getString("description"));
            	book.setAmount(rs.getInt("amount"));
            	book.setPublishYear(rs.getInt("publishYear"));
            	book.setPublisher(rs.getString("publisher"));
            	book.setNumberOfPages(rs.getInt("numberOfPages"));
            	book.setCost(rs.getInt("cost"));
            	book.setPrice(rs.getInt("price"));
            	book.setCoverKind(rs.getString("coverKind"));
            	book.setImage(rs.getString("image"));
                books.add(book);
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
        return books;
	}
	
	public List<Book> getBooksOrderByAmount(){
		List<Book> books = new ArrayList<Book>();
		String sql = select_book + " ORDER BY amount";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
            	Book book = new Book();
            	book.setId(rs.getInt("id"));
            	book.setTitle(rs.getString("title"));
            	book.setAuthor(rs.getString("author"));
            	book.setDescription(rs.getString("description"));
            	book.setAmount(rs.getInt("amount"));
            	book.setPublishYear(rs.getInt("publishYear"));
            	book.setPublisher(rs.getString("publisher"));
            	book.setNumberOfPages(rs.getInt("numberOfPages"));
            	book.setCost(rs.getInt("cost"));
            	book.setPrice(rs.getInt("price"));
            	book.setCoverKind(rs.getString("coverKind"));
            	book.setImage(rs.getString("image"));
                books.add(book);
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
        return books;
	}
	
	public List<Book> getTop10OrderByAmount(){
		List<Book> books = new ArrayList<Book>();
		String sql = select_book + " ORDER BY amount LIMIT 10";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
            	Book book = new Book();
            	book.setId(rs.getInt("id"));
            	book.setTitle(rs.getString("title"));
            	book.setAuthor(rs.getString("author"));
            	book.setDescription(rs.getString("description"));
            	book.setAmount(rs.getInt("amount"));
            	book.setPublishYear(rs.getInt("publishYear"));
            	book.setPublisher(rs.getString("publisher"));
            	book.setNumberOfPages(rs.getInt("numberOfPages"));
            	book.setCost(rs.getInt("cost"));
            	book.setPrice(rs.getInt("price"));
            	book.setCoverKind(rs.getString("coverKind"));
            	book.setImage(rs.getString("image"));
                books.add(book);
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
        return books;
	}
	
	public Book getBestSellerByAmount(){
		Book book = new Book();
		String sql = select_best_seller;
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
            	book.setId(rs.getInt("id"));
            	book.setTitle(rs.getString("title"));
            	book.setAuthor(rs.getString("author"));
            	book.setDescription(rs.getString("description"));
            	book.setAmount(rs.getInt("amount"));
            	book.setPublishYear(rs.getInt("publishYear"));
            	book.setPublisher(rs.getString("publisher"));
            	book.setNumberOfPages(rs.getInt("numberOfPages"));
            	book.setCost(rs.getInt("cost"));
            	book.setPrice(rs.getInt("price"));
            	book.setCoverKind(rs.getString("coverKind"));
            	book.setImage(rs.getString("image"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
        return book;
	}
	
	public List<Book> getBooksOrderByPublishYear(){
		List<Book> books = new ArrayList<Book>();
		String sql = select_book + " ORDER BY publishYear";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
            	Book book = new Book();
            	book.setId(rs.getInt("id"));
            	book.setTitle(rs.getString("title"));
            	book.setAuthor(rs.getString("author"));
            	book.setDescription(rs.getString("description"));
            	book.setAmount(rs.getInt("amount"));
            	book.setPublishYear(rs.getInt("publishYear"));
            	book.setPublisher(rs.getString("publisher"));
            	book.setNumberOfPages(rs.getInt("numberOfPages"));
            	book.setCost(rs.getInt("cost"));
            	book.setPrice(rs.getInt("price"));
            	book.setCoverKind(rs.getString("coverKind"));
            	book.setImage(rs.getString("image"));
                books.add(book);
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
        return books;
	}
	
	public List<Book> getTop10OrderByPublishYear(){
		List<Book> books = new ArrayList<Book>();
		String sql = select_book + " ORDER BY publishYear LIMIT 10";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
            	Book book = new Book();
            	book.setId(rs.getInt("id"));
            	book.setTitle(rs.getString("title"));
            	book.setAuthor(rs.getString("author"));
            	book.setDescription(rs.getString("description"));
            	book.setAmount(rs.getInt("amount"));
            	book.setPublishYear(rs.getInt("publishYear"));
            	book.setPublisher(rs.getString("publisher"));
            	book.setNumberOfPages(rs.getInt("numberOfPages"));
            	book.setCost(rs.getInt("cost"));
            	book.setPrice(rs.getInt("price"));
            	book.setCoverKind(rs.getString("coverKind"));
            	book.setImage(rs.getString("image"));
                books.add(book);
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
        return books;
	}
	
	public List<Book> getBooksByCategory(int id){
		List<Book> books = new ArrayList<Book>();
		String sql = select_book;
		if(id != -1) sql += " INNER JOIN havecategory ON idBook=book.id WHERE idCategory=?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            if(id != -1) ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
            	Book book = new Book();
            	book.setId(rs.getInt("id"));
            	book.setTitle(rs.getString("title"));
            	book.setAuthor(rs.getString("author"));
            	book.setDescription(rs.getString("description"));
            	book.setAmount(rs.getInt("amount"));
            	book.setPublishYear(rs.getInt("publishYear"));
            	book.setPublisher(rs.getString("publisher"));
            	book.setNumberOfPages(rs.getInt("numberOfPages"));
            	book.setCost(rs.getInt("cost"));
            	book.setPrice(rs.getInt("price"));
            	book.setCoverKind(rs.getString("coverKind"));
            	book.setImage(rs.getString("image"));
                books.add(book);
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
        return books;
	}
	
	public Book getBestSellerByCategory(int id){
		Book book = new Book();
		String sql = select_best_seller;
		if(id != -1) sql = select_book + " INNER JOIN havecategory ON idBook=book.id WHERE idCategory=? ORDER BY book.amount LIMIT 1";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            if(id != -1) ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
            	book.setId(rs.getInt("id"));
            	book.setTitle(rs.getString("title"));
            	book.setAuthor(rs.getString("author"));
            	book.setDescription(rs.getString("description"));
            	book.setAmount(rs.getInt("amount"));
            	book.setPublishYear(rs.getInt("publishYear"));
            	book.setPublisher(rs.getString("publisher"));
            	book.setNumberOfPages(rs.getInt("numberOfPages"));
            	book.setCost(rs.getInt("cost"));
            	book.setPrice(rs.getInt("price"));
            	book.setCoverKind(rs.getString("coverKind"));
            	book.setImage(rs.getString("image"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
        return book;
	}
	
	public Book getBestSellerByTitle(String name){
		Book book = new Book();
		String sql = select_book + " WHERE title LIKE '%" + name + "%' ORDER BY book.amount LIMIT 1";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
            	book.setId(rs.getInt("id"));
            	book.setTitle(rs.getString("title"));
            	book.setAuthor(rs.getString("author"));
            	book.setDescription(rs.getString("description"));
            	book.setAmount(rs.getInt("amount"));
            	book.setPublishYear(rs.getInt("publishYear"));
            	book.setPublisher(rs.getString("publisher"));
            	book.setNumberOfPages(rs.getInt("numberOfPages"));
            	book.setCost(rs.getInt("cost"));
            	book.setPrice(rs.getInt("price"));
            	book.setCoverKind(rs.getString("coverKind"));
            	book.setImage(rs.getString("image"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
        return book;
	}
	
	public List<Comment> getComments(Book book){
		List<Comment> comments = new ArrayList<Comment>();
		String sql = "SELECT * FROM comment INNER JOIN account ON account.id = idAccount WHERE idBook=? ORDER BY comment.time DESC, comment.id DESC";
		try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, book.getId());
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
            	Comment comment = new Comment();
            	comment.setId(rs.getInt("id"));
            	comment.setContent(rs.getString("content"));
            	comment.setStar(rs.getInt("star"));
            	comment.setTime(rs.getDate("time"));
            	comment.setBook(book);
            	
            	Account account = new Account();
            	account.setId(rs.getInt("idAccount"));
            	account.setUsername(rs.getString("username"));
            	account.setPassword(rs.getString("password"));
            	account.setFullname(rs.getString("fullname"));
            	account.setEmail(rs.getString("email"));
            	account.setPhone(rs.getString("phone"));
            	account.setAddress(rs.getString("address"));
            	comment.setAccount(account);
                comments.add(comment);
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
        return comments;
	}
	
	
	public List<Rate> getRates(Book book){
		List<Rate> rates = new ArrayList<Rate>();
		String sql = "SELECT * FROM rate INNER JOIN account ON account.id = idAccount WHERE idBook=? ORDER BY rate.time DESC, rate.id DESC";
		try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, book.getId());
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
            	Rate rate = new Rate();
            	rate.setId(rs.getInt("id"));
            	rate.setStar(rs.getInt("star"));
            	rate.setTime(rs.getDate("time"));
            	rate.setBook(book);
            	
            	Account account = new Account();
            	account.setId(rs.getInt("idAccount"));
            	account.setUsername(rs.getString("username"));
            	account.setPassword(rs.getString("password"));
            	account.setFullname(rs.getString("fullname"));
            	account.setEmail(rs.getString("email"));
            	account.setPhone(rs.getString("phone"));
            	account.setAddress(rs.getString("address"));
            	rate.setAccount(account);
            	rates.add(rate);
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
        return rates;
	}
	
	public void addBook(Book book) {
		String sql = "INSERT INTO book(title, author, description, amount, publishYear, publisher, numberOfPages, cost, price, coverKind, image) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		try{
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getDescription());
            ps.setInt(4, book.getAmount());
            ps.setInt(5, book.getPublishYear());
            ps.setString(6, book.getPublisher());
            ps.setInt(7, book.getNumberOfPages());
            ps.setInt(8, book.getCost());
            ps.setInt(9, book.getPrice());
            ps.setString(10, book.getCoverKind());
            ps.setString(11, book.getImage());
           
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
            	book.setId(rs.getInt(1));
            	sql = "INSERT INTO havecategory(idBook, idCategory) VALUES(?,?)";
            	
                ps = connection.prepareStatement(sql);
                for(HaveCategory havecategory : book.getHaveCategories()) {
                	ps.setInt(1, book.getId());
                    ps.setInt(2, havecategory.getCategory().getId());
                   
                    ps.executeUpdate();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
	}
	
	public boolean checkBookExist(Book book) {
		String sql = "SELECT * FROM book WHERE title=? AND author=? AND id NOT IN(?)";
		boolean result = false;
		try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getId());
            ResultSet rs = ps.executeQuery();
 
            if(rs.next()){
            	result = true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
		return result;
	}
	
	public void deleteBook(Book book) {
		String deleteHaveCategory = "DELETE FROM havecategory WHERE idBook=?";
		String deleteBook = "DELETE FROM book WHERE id=?";
		try{
	        PreparedStatement ps = connection.prepareStatement(deleteHaveCategory);
	        ps.setInt(1, book.getId());
	        ps.executeUpdate();
	        
	        ps = connection.prepareStatement(deleteBook);
	        ps.setInt(1, book.getId());
	        ps.executeUpdate();
	        
	        String absolutePath = "D:/EProject/web_book/src/main/resources/static" + book.getImage(); // Đường dẫn tuyệt đối của thư mục chứa ảnh
	        Path path = Paths.get(absolutePath);
	        File file = path.toFile();
	
	        if (file.delete()) {
	            System.out.println("Đã xóa tệp tin thành công: " + file.getAbsolutePath());
	        } else {
	            System.out.println("Không thể xóa tệp tin: " + file.getAbsolutePath());
	        }
        } catch(Exception e) {
            e.printStackTrace();
        }
	}
	
	public void updateBook(Book book) {
		String sql = "UPDATE book SET title=?, author=?, description=?, amount=?, publishYear=?, publisher=?, numberOfPages=?, cost=?, price=?, coverKind=?, image=? WHERE id=?";
		try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getDescription());
            ps.setInt(4, book.getAmount());
            ps.setInt(5, book.getPublishYear());
            ps.setString(6, book.getPublisher());
            ps.setInt(7, book.getNumberOfPages());
            ps.setInt(8, book.getCost());
            ps.setInt(9, book.getPrice());
            ps.setString(10, book.getCoverKind());
            ps.setString(11, book.getImage());
            ps.setInt(12, book.getId());
           
            ps.executeUpdate();
        
        	sql = "DELETE FROM havecategory WHERE idBook=?";

        	ps = connection.prepareStatement(sql);
            ps.setInt(1, book.getId());
            ps.executeUpdate();
            
            sql = "INSERT INTO havecategory VALUES(?,?)";
            ps = connection.prepareStatement(sql);
            
            for(HaveCategory havecategory : book.getHaveCategories()) {
            	ps.setInt(1, book.getId());
                ps.setInt(2, havecategory.getCategory().getId());
               
                ps.executeUpdate();
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
	}
}
