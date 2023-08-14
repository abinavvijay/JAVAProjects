//package com.infovision.canteen.controller;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//// Servlet implementation class SearchServlet
//@WebServlet("/searchServlet")
//public class PageRedirect extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//		
//	public PageRedirect() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//	
//	// @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		// set response content type
//		response.setContentType("text/html");
//
//		// New location to be redirected, it is an example
//		String site = new String("https://payment10.azurewebsites.net");
//			
//		// We have different response status types.
//		// It is an optional also. Here it is a valid site
//		// and hence it comes with response.SC_ACCEPTED
//		response.setStatus(response.SC_MOVED_TEMPORARILY);
//		response.setHeader("Location", site);
//		response.sendRedirect(site);
//		return;
//	}
//
//}
//
