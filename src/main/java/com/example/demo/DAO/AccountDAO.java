package com.example.demo.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Account;
import com.example.demo.model.Book;
import com.example.demo.model.Cart;
import com.example.demo.model.Comment;
import com.example.demo.model.Rate;

public class AccountDAO extends DAO{
	
	public Account checkLogin(String username, String password){
		Account account = new Account();
		String sql = "SELECT * FROM account WHERE username=? AND password=?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
 
            if(rs.next()){
            	account.setId(rs.getInt("id"));
            	account.setFullname(rs.getString("fullname"));
            	account.setUsername(rs.getString("username"));
            	account.setPassword(rs.getString("password"));
            	account.setEmail(rs.getString("email"));
            	account.setPhone(rs.getString("phone"));
            	account.setAddress(rs.getString("address"));
            	account.setPosition(rs.getString("position"));
            }
            else {
            	return null;
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
        return account;
	}
	
	public boolean checkAccountExist(String username) {
		String sql = "SELECT * FROM account WHERE username=?";
		boolean result = false;
		try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
 
            if(rs.next()){
            	result = true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
		return result;
	}
	
	public void addAccount(Account account) {
		String sql = "INSERT INTO account(username, password, fullname, email, phone, address, position) VALUES(?,?,?,?,?,?, ?)";
		try{
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getFullname());
            ps.setString(4, account.getEmail());
            ps.setString(5, account.getPhone());
            ps.setString(6, account.getAddress());
            ps.setString(7, "user");
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
            	account.setId(rs.getInt(1));
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
	}
	
	public List<Cart> getCarts(int id){
		List<Cart> carts = new ArrayList<Cart>();
		String sql = "SELECT * FROM cart INNER JOIN book ON idBook=book.id WHERE idAccount=?";
		try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
            	Cart cart = new Cart();
            	cart.setId(rs.getInt("id"));
            	cart.setAmount(rs.getInt("amount"));
            	cart.setTotal(rs.getInt("total"));
            	
            	Book book = new Book();
            	book.setId(rs.getInt("idBook"));
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
            	
            	cart.setBook(book);
            	carts.add(cart);
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
		return carts;
	}
	
	public boolean checkCartExist(Cart cart){
		String sql = "SELECT * FROM cart WHERE idAccount=? AND idBook=?";
		boolean result = true;
		try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cart.getAccount().getId());
            ps.setInt(2, cart.getBook().getId());
            ResultSet rs = ps.executeQuery();
 
            if(!rs.next()){
            	result = false;
            }
        }catch(Exception e){
        	result = false;
            e.printStackTrace();
        }   
		return result;
	}
	
	public void addToCart(Cart cart) {
		String sql = "INSERT INTO cart(amount, total, idBook, idAccount) VALUES(?, ?, ?, ?)";
		try{
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cart.getAmount());
            ps.setInt(2, cart.getBook().getPrice() * cart.getAmount());
            ps.setInt(3, cart.getBook().getId());
            ps.setInt(4, cart.getAccount().getId());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
            	cart.setId(rs.getInt(1));
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
	}
	
	public void updateCart(Cart cart) {
		String sql = "UPDATE cart SET amount=?, total=? WHERE idBook=? AND idAccount=?";
		try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cart.getAmount());
            ps.setInt(2, cart.getBook().getPrice() * cart.getAmount());
            ps.setInt(3, cart.getBook().getId());
            ps.setInt(4, cart.getAccount().getId());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }   
	}
	
	public void deleteCart(int id) {
		String sql = "DELETE FROM cart WHERE id=?";
		try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }   
	}
	
	public boolean checkRateExist(Rate rate){
		String sql = "SELECT * FROM rate INNER JOIN account ON account.id = idAccount WHERE idBook=? AND idAccount=?";
		boolean result = true;
		try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, rate.getBook().getId());
            ps.setInt(2, rate.getAccount().getId());
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()){
            	result = false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
		return result;
	}
	
	public void addRate(Rate rate) {
		String sql = "INSERT INTO rate(star, time, idBook, idAccount) VALUES(?,?,?,?)";
		try{
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, rate.getStar());
            ps.setDate(2, new Date(System.currentTimeMillis()));
            ps.setInt(3, rate.getBook().getId());
            ps.setInt(4, rate.getAccount().getId());
           
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
            	rate.setId(rs.getInt(1));
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
	}
	
	public void updateRate(Rate rate) {
		String sql = "UPDATE rate SET star=?, time=? WHERE idBook=? AND idAccount=?";
		try{
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, rate.getStar());
            ps.setDate(2, new Date(System.currentTimeMillis()));
            ps.setInt(3, rate.getBook().getId());
            ps.setInt(4, rate.getAccount().getId());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }   
	}
	
	public boolean checkCommentExist(Comment comment){
		String sql = "SELECT * FROM comment INNER JOIN account ON account.id = idAccount WHERE idBook=? AND idAccount=?";
		boolean result = true;
		try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, comment.getBook().getId());
            ps.setInt(2, comment.getAccount().getId());
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()){
            	result = false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
		return result;
	}

	public void addComment(Comment comment) {
		String sql = "INSERT INTO comment(content, star, time, idBook, idAccount) VALUES(?,?,?,?,?)";
		try{
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, comment.getContent());
            ps.setInt(2, comment.getStar());
            ps.setDate(3, new Date(System.currentTimeMillis()));
            ps.setInt(4, comment.getBook().getId());
            ps.setInt(5, comment.getAccount().getId());
           
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
            	comment.setId(rs.getInt(1));
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
	}
	
	public void updateComment(Comment comment) {
		String sql = "UPDATE comment SET content=?, star=?, time=? WHERE idBook=? AND idAccount=?";
		try{
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, comment.getContent());
            ps.setInt(2, comment.getStar());
            ps.setDate(3, new Date(System.currentTimeMillis()));
            ps.setInt(4, comment.getBook().getId());
            ps.setInt(5, comment.getAccount().getId());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }   
	}
}
