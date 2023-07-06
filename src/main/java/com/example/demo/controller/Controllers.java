package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.DAO.AccountDAO;
import com.example.demo.DAO.BookDAO;
import com.example.demo.DAO.CategoryDAO;
import com.example.demo.model.Account;
import com.example.demo.model.Book;
import com.example.demo.model.Cart;
import com.example.demo.model.Category;
import com.example.demo.model.Comment;
import com.example.demo.model.HaveCategory;
import com.example.demo.model.Rate;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class Controllers {
	BookDAO bd = new BookDAO();
	CategoryDAO cd = new CategoryDAO();
	AccountDAO ad = new AccountDAO();
	private int checkLogin = 0;
	private String messageLogin = "", messageRegister = "", messageNew = "";
	private int editOrSave = 0;
	private String image;
	
	@GetMapping("/home")
	public String getHome(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
        Account data = (Account) session.getAttribute("account");
        model.addAttribute("account", data);
        
        messageLogin = "";
        messageRegister = "";
		
		model.addAttribute("checkLogin", checkLogin);
		
		Book top1 = bd.getBestSellerByAmount();
		model.addAttribute("top1", top1);
		
		List<Book> top10 = bd.getTop10OrderByAmount();
		model.addAttribute("top10", top10);
		
		List<Book> newest = new ArrayList<Book>();
		newest = bd.getTop10OrderByPublishYear();
		model.addAttribute("new1", newest.get(1));
		model.addAttribute("new2", newest.get(2));
		model.addAttribute("newests", newest);
		
		List<Category> cates = cd.getCategories();
		model.addAttribute("categories", cates);
		return "index";
	}
	
	@GetMapping("/products/category/{id}")
	public String getProductsByCategory(Model model, @PathVariable int id, HttpServletRequest request) {
		HttpSession session = request.getSession();
        Account data = (Account) session.getAttribute("account");
        model.addAttribute("account", data);
        
        messageLogin = "";
        messageRegister = "";
		
		model.addAttribute("checkLogin", checkLogin);
		
		model.addAttribute("id", id);
		
		List<Book> books = bd.getBooksByCategory(id);
		model.addAttribute("number", books.size());
		model.addAttribute("books", books);
		
		Book book = bd.getBestSellerByCategory(id);
		model.addAttribute("top1", book);
		
		Category category = cd.getCategoryDetail(id);
		model.addAttribute("category", category);
		
		List<Category> cates = cd.getCategories();
		model.addAttribute("categories", cates);
		return "product";
	}
	
	@GetMapping("/products/title")
	public String getProductsByName(Model model, HttpServletRequest request, @RequestParam("name") String name) {
		HttpSession session = request.getSession();
        Account data = (Account) session.getAttribute("account");
        model.addAttribute("account", data);
        
        messageLogin = "";
        messageRegister = "";
		
		model.addAttribute("checkLogin", checkLogin);
		
		System.out.println("hello " + name);
		
		List<Book> books = bd.getBooksByTitle(name);
		model.addAttribute("number", books.size());
		model.addAttribute("books", books);
		
		Book book = bd.getBestSellerByTitle(name);
		model.addAttribute("top1", book);
		
		Category cate = new Category();
		cate.setId(0);
		cate.setName("RESULTS");
		model.addAttribute("category", cate);
		
		List<Category> cates = cd.getCategories();
		model.addAttribute("categories", cates);
		return "product";
	}
	
	@GetMapping("/details/{id}")
	public String getProductDetails(Model model, @PathVariable int id, HttpServletRequest request) {
		HttpSession session = request.getSession();
        Account data = (Account) session.getAttribute("account");
        model.addAttribute("account", data);
        
        messageLogin = "";
        messageRegister = "";
		
		model.addAttribute("checkLogin", checkLogin);
		
		Comment comment = new Comment();
		model.addAttribute("comment", comment);
		
		Book top1 = bd.getBestSellerByAmount();
		model.addAttribute("top1", top1);
		
		List<Book> top = bd.getBooksOrderByAmount();
		model.addAttribute("top6", top);
		
		List<Category> cates = cd.getCategories();
		model.addAttribute("categories", cates);
		
		Book book = bd.getBook(id);
		model.addAttribute("product", book);
		
		List<HaveCategory> haveCategories = cd.getHaveCategories(id);
		model.addAttribute("haveCategories", haveCategories);
		
		List<Comment> comments = bd.getComments(book);
		model.addAttribute("comments", comments);
		model.addAttribute("number", comments.size());
		book.setComments(comments);
		model.addAttribute("averageStar", Math.round(book.getAverageStar() * 10.0) / 10.0);
		
		for(Comment c : comments) System.out.println(c.getBook().getTitle() + " " + c.getAccount().getFullname() + " " + c.getStar());
		
		return "single";
	}
	
	@GetMapping("/login")
	public String getLogin(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
        Account data = (Account) session.getAttribute("account");
        model.addAttribute("account", data);
        
        messageRegister = "";
		
		model.addAttribute("checkLogin", checkLogin);
		
		model.addAttribute("message", messageLogin);
		
		List<Category> cates = cd.getCategories();
		model.addAttribute("categories", cates);
		
		Book top1 = bd.getBestSellerByAmount();
		model.addAttribute("top1", top1);
		return "login";
	}
	
	@GetMapping("/logout")
	public String getLogout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("account");
		checkLogin = 0;
		messageLogin = "";
		
		return "redirect:/home";
	}
	
	@PostMapping("/login/check")
	public String checkLogin(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password) {
		HttpSession session = request.getSession();
		Account account = ad.checkLogin(username, password);

		if(account != null) {
			checkLogin = 1;
			session.setAttribute("account", account);
			return "redirect:/home";
		}
		
		messageLogin = "* Wrong username or password!";
		
		return "redirect:/login";
	}
	
	@GetMapping("/register")
	public String getRegister(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
        Account data = (Account) session.getAttribute("account");
        model.addAttribute("account", data);
        
        messageLogin = "";
		
		model.addAttribute("checkLogin", checkLogin);
		
		model.addAttribute("message", messageRegister);

		Account account = new Account();
		model.addAttribute("acc", account);
		
		List<Category> cates = cd.getCategories();
		model.addAttribute("categories", cates);
		
		Book top1 = bd.getBestSellerByAmount();
		model.addAttribute("top1", top1);
		return "register";
	}
	
	@PostMapping("/sign-up")
	public String createAccount(Account acc, Model model, HttpServletRequest request) {
		System.out.println(acc.getFullname() + " " + acc.getEmail() + " " + acc.getPhone() + " " + acc.getAddress() + " " + acc.getUsername() + " " + acc.getPassword());
		
		if(ad.checkAccountExist(acc.getUsername())) {
			messageRegister = "* Username is existed!";
			return "redirect:/register";
		}
		ad.addAccount(acc);
		HttpSession session = request.getSession();
        session.setAttribute("account", acc);
        model.addAttribute("account", acc);
        checkLogin = 1;
		return "redirect:/home";
	}
	
	@GetMapping("/cart")
	public String getCart(Model model, HttpServletRequest request) {
		if(checkLogin == 0) return "redirect:/home";
		
		HttpSession session = request.getSession();
        Account data = (Account) session.getAttribute("account");
        model.addAttribute("account", data);
		
		model.addAttribute("checkLogin", checkLogin);
		
		List<Cart> carts = ad.getCarts(data.getId());
		data.setCarts(carts);
		model.addAttribute("carts", carts);
		model.addAttribute("subTotal", data.getSubTotal());
		model.addAttribute("shipping", 3000 * carts.size());
		return "cart";
	}
	
	@GetMapping("/add/cart/{id}")
	public String addToCart(Model model, HttpServletRequest request, @PathVariable int id, @RequestParam("quantity") int quantity) {
		if(checkLogin == 0) return "redirect:/login";
		System.out.println(id);
		
		HttpSession session = request.getSession();
        Account data = (Account) session.getAttribute("account");
        model.addAttribute("account", data);
		
		model.addAttribute("checkLogin", checkLogin);
		
		Cart cart = new Cart();
		Book book = bd.getBook(id);
		cart.setBook(book);
		cart.setAccount(data);
		cart.setAmount(quantity);
		
		if(ad.checkCartExist(cart)) {
			ad.updateCart(cart);
			return "redirect:/cart";
		}
		else {
			ad.addToCart(cart);
			return "redirect:/cart";
		}
	}
	
	@GetMapping("/delete-cart/{id}")
	public String dateleCart(Model model, HttpServletRequest request, @PathVariable int id) {
		HttpSession session = request.getSession();
        Account data = (Account) session.getAttribute("account");
        model.addAttribute("account", data);
		
		model.addAttribute("checkLogin", checkLogin);
		
		ad.deleteCart(id);
		return "redirect:/cart";
	}
	
	@GetMapping("/add/comment/{id}")
	public String addComment(Comment comment, HttpServletRequest request, @PathVariable int id) {
		System.out.println(comment.getContent() + " " + " " + id);
		
		HttpSession session = request.getSession();
        Account data = (Account) session.getAttribute("account");
        comment.setAccount(data);
        
        Book book = bd.getBook(id);
        comment.setBook(book);
        
        ad.addComment(comment);
		return "redirect:/details/" + id;
	}
	
	@GetMapping("/add/star/{id}")
	public String addStar(Rate rate, HttpServletRequest request, @PathVariable int id) {
		HttpSession session = request.getSession();
        Account data = (Account) session.getAttribute("account");
        rate.setAccount(data);
        
        Book book = bd.getBook(id);
        rate.setBook(book);
        
        if(ad.checkRateExist(rate)) ad.updateRate(rate);
        else ad.addRate(rate);
		return "redirect:/details/" + id;
	}
	
	@GetMapping("/admin")
	public String getAdmin(Model model, HttpServletRequest request) {
		if(checkLogin == 0) return "redirect:/home";
		
		messageNew = "";
		editOrSave = 0;
		
		HttpSession session = request.getSession();
        Account data = (Account) session.getAttribute("account");
        model.addAttribute("account", data);
		
		model.addAttribute("checkLogin", checkLogin);
		
		List<Category> categories = cd.getCategories();
		model.addAttribute("categories", categories);List<Book> books = bd.getBooks();
		for(Book book : books) {
			List<HaveCategory> havecategories = cd.getHaveCategories(book.getId());
			book.setHaveCategories(havecategories);
		}
		model.addAttribute("books", books);
		
		return "listbooks";
	}
	
	@GetMapping("/admin/book/{id}")
	public String getAdminBook(Model model, HttpServletRequest request, @PathVariable int id) {
		HttpSession session = request.getSession();
        Account data = (Account) session.getAttribute("account");
        model.addAttribute("account", data);
		
		model.addAttribute("checkLogin", checkLogin);
		
		model.addAttribute("message", messageNew);
		
		if(id > 0 && editOrSave < 2) editOrSave += 1;
		model.addAttribute("editOrSave", editOrSave);
		
		List<Category> categories = cd.getCategories();
		model.addAttribute("categories", categories);
		
		Book book = bd.getBook(id);
		model.addAttribute("book", book);
		
		System.out.println("1" + book.getImage());
		image = book.getImage();
	
		List<Integer> idCategoriesByBook = cd.getIdCategoryByBook(id);
		model.addAttribute("idCategoriesByBook", idCategoriesByBook);
		
		return "bookdetails";
	}
	
	@GetMapping("/admin/save/{id}")
	public String updateBook(Book book, Model model, @PathVariable int id, @RequestParam("checkbox") List<Integer> selectedCategories) {
		List<HaveCategory> haveCategories = new ArrayList<HaveCategory>();
		
		editOrSave = 0;
		
		model.addAttribute("checkLogin", checkLogin);
		
		model.addAttribute("editOrSave", editOrSave);
		
		if(bd.checkBookExist(book)) {
			messageNew = "* BOOK IS EXISTED!";
			return "redirect:/admin/book/" + id;
		}
		
		for(int idCate : selectedCategories) {
			Category category = new Category();
			category.setId(idCate);
			
			HaveCategory haveCategory = new HaveCategory();
			haveCategory.setCategory(category);
			
			haveCategories.add(haveCategory);
		}
		book.setHaveCategories(haveCategories);
		
		String src = "/images/" + book.getImage();
		if(src.equals("/images/")) book.setImage(image);
		else book.setImage(src);
		
		System.out.println(book.toString());
		for(HaveCategory hc : book.getHaveCategories()) {
			System.out.println(hc.getCategory().getId());
		}
		bd.updateBook(book);
		
		return "redirect:/admin";
	}
	
	@PostMapping("/admin/save/{id}")
	public String addBook(Book book, Model model, @PathVariable int id, @RequestParam("checkbox") List<Integer> selectedCategories) {
		List<HaveCategory> haveCategories = new ArrayList<HaveCategory>();
		
		if(bd.checkBookExist(book)) {
			messageNew = "* BOOK IS EXISTED!";
			return "redirect:/admin/book/" + id;
		}
		
		for(int idCate : selectedCategories) {
			Category category = new Category();
			category.setId(idCate);
			
			HaveCategory haveCategory = new HaveCategory();
			haveCategory.setCategory(category);
			
			haveCategories.add(haveCategory);
		}
		book.setHaveCategories(haveCategories);
		book.setImage("/images/" + book.getImage());
		
		System.out.println(book.toString());
		for(HaveCategory hc : book.getHaveCategories()) {
			System.out.println(hc.getCategory().getId());
		}
		bd.addBook(book);
		
		return "redirect:/admin";
	}
	
	@GetMapping("/admin/delete/{id}")
	public String deleteBook(@PathVariable int id) {
		Book book = bd.getBook(id);
		
		bd.deleteBook(book);
		
		return "redirect:/admin";
	}
	
	@GetMapping("/add/comment-star/{id}")
	public String addCommentRating(Comment comment, HttpServletRequest request, @PathVariable int id) {
		HttpSession session = request.getSession();
        Account data = (Account) session.getAttribute("account");
        comment.setAccount(data);
        
        Book book = bd.getBook(id);
        comment.setBook(book);
        
        if(ad.checkCommentExist(comment)) ad.updateComment(comment);
        else ad.addComment(comment);
        
        return "redirect:/details/" + id;
	}
}
