package com.example.demo.model;

import java.util.List;

public class Book {
	private int id;
	private String title;
	private String author;
	private String description;
	private int amount;
	private int publishYear;
	private String publisher;
	private int numberOfPages;
	private int cost;
	private int price;
	private String coverKind;
	private String image;
	private List<HaveCategory> haveCategories;
	private List<Comment> comments;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCoverKind() {
		return coverKind;
	}

	public void setCoverKind(String coverKind) {
		this.coverKind = coverKind;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<HaveCategory> getHaveCategories() {
		return haveCategories;
	}

	public void setHaveCategories(List<HaveCategory> haveCategories) {
		this.haveCategories = haveCategories;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public float getAverageStar() {
		float averageStar = 0;
		float size = comments.size();
		for(Comment comment : comments) {
			if(comment.getStar() == 0) {
				size -= 1;
				continue;
			}
			averageStar += (float)comment.getStar();
		}
		averageStar /= size;
		return averageStar;
	}
	
	@Override
	public String toString() {
		return title + " " + author + " " + description + " " + amount + " " + publishYear + " " + publisher + " " + numberOfPages + " " + cost + " " + price + " " + coverKind + " " + image;
	}
}
